package stack_queue;

import java.util.HashMap;

/**
 * Problem Link : https://leetcode.com/problems/lfu-cache/
 * Video Explanation :
 * Video 1 : https://www.youtube.com/watch?v=-Vch0tHAsOM (understand the base intuition from here)
 * Video 2 : https://www.youtube.com/watch?v=bLEIHn-DgoA (implementation is similar to this one)
 *
 * 460. LFU Cache
 * Hard
 * Topics
 *
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 *     LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 *     int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 *     void put(int key, int value) Update the value of the key if present, or inserts the
 *     key if not already present. When the cache reaches its capacity, it should invalidate
 *     and remove the least frequently used key before inserting a new item. For this problem,
 *     when there is a tie (i.e., two or more keys with the same frequency), the least recently
 *     used key would be invalidated.
 *
 * To determine the least frequently used key, a use counter is maintained for
 * each key in the cache. The key with the smallest use counter is the least
 * frequently used key.
 *
 * When a key is first inserted into the cache, its use counter is set to 1
 * (due to the put operation). The use counter for a key in the cache is incremented either
 * a get or put operation is called on it.
 *
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Example 1:
 * Input
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 * Explanation
 * // cnt(x) = the use counter for key x
 * // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // return 1
 *                  // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
 *                  // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // return 4
 *                  // cache=[4,3], cnt(4)=2, cnt(3)=3
 *
 * Constraints:
 *     1 <= capacity <= 104
 *     0 <= key <= 105
 *     0 <= value <= 109
 *
 *     At most 2 * 105 calls will be made to get and put.
 *
 */
public class _19_LFUCache {

    class Node{
        Node next,pre;
        int key,data,frequency;
        Node(int key,int data,int frequency){
            this.key=key;
            this.data=data;
            this.frequency=frequency;
            next=null;
            pre=null;
        }
    }

    class DoublyLinkedList {
        Node head;  // Dummy head (most recently used)
        Node tail;  // Dummy tail (least recently used)
        int size;

        DoublyLinkedList() {
            head = new Node(-1, -1, -1);
            tail = new Node(-1, -1, -1);
            head.next = tail;
            tail.pre = head;
            size = 0;
        }

        // Add node right after head (most recently used position)
        void addFirst(Node node) {
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
            size++;
        }

        // Remove a specific node from the list
        void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size--;
        }

        // Remove and return the last node (least recently used)
        Node removeLast() {
            if (isEmpty()) {
                return null;
            }
            Node lastNode = tail.pre;
            remove(lastNode);
            return lastNode;
        }

        // Check if list is empty
        boolean isEmpty() {
            return size == 0;
        }

        // Get size
        int getSize() {
            return size;
        }
    }


    int size;
    int minFrequency;
    HashMap<Integer,Node> dataMap;
    HashMap<Integer,DoublyLinkedList> frequencyLists;

    /**
     * Overall Class Complexity
     *
     * Time Complexity: O(1)
     *
     * Both get() and put() operations run in constant time O(1)
     * All core operations (insert, delete, update, lookup) are O(1)
     *
     * Space Complexity: O(capacity)
     * Where capacity is the maximum cache size:
     *
     * dataMap: stores at most capacity nodes → O(capacity)
     * frequencyLists: stores at most capacity nodes across all lists → O(capacity)
     * Each node appears exactly once in dataMap and once in a frequency list
     * Total: O(capacity) space
     *
    * */
    public _19_LFUCache(int capacity) {
        size=capacity;
        minFrequency=0;
        dataMap = new HashMap<>();
        frequencyLists = new HashMap<>();
    }

    private Node updateNodeFrequency(Node node){
        // get old frequency
        int oldFreq = node.frequency;

        // Remove from frequencyLists
        frequencyLists.get(oldFreq).remove(node);

        // update the existing node, no need to create a new one.
        int freq = oldFreq+1;
        node.frequency = freq;

        //update minFreq if needed
        if (oldFreq == minFrequency && frequencyLists.get(oldFreq).isEmpty()) {
            minFrequency = freq;
        }

        return node;
    }

    private void addNodeToFrequencyList(Node node, int freq) {
        if (!frequencyLists.containsKey(freq)){
            frequencyLists.put(freq,new DoublyLinkedList());
        }
        frequencyLists.get(freq).addFirst(node);
    }

    private void evictLFUNode() {
        Node lfuNode = frequencyLists.get(minFrequency).removeLast();
        dataMap.remove(lfuNode.key);
    }

    /**
     * Time Complexity: O(1)
     *
     * dataMap.containsKey(key) → O(1) HashMap lookup
     * dataMap.get(key) → O(1) HashMap access
     *
     * updateNodeFrequency():
     * frequencyLists.get(oldFreq).remove(node) → O(1) doubly linked list
     * removal (we have direct node reference)
     * Frequency increment → O(1)
     * isEmpty() check → O(1)
     *
     * addNodeToFrequencyList():
     * containsKey() and put() → O(1) HashMap operations
     * addFirst() → O(1) doubly linked list insertion
     *
     * Space Complexity: O(1)
     *
     * No additional data structures created
     * Only a few local variables
    * */
    public int get(int key) {
        if(!dataMap.containsKey(key)) return -1;

        // Get the existing node
        Node oldNode = dataMap.get(key);

        //update the node freq and remove the old node from map.
        Node node = updateNodeFrequency(oldNode);

        //add the updated node to freq map.
        addNodeToFrequencyList(node, node.frequency);

        return node.data;
    }

    /**
     * Time Complexity: O(1)
     *
     * dataMap.containsKey(key) → O(1) HashMap lookup
     * Update existing key path:
     *  - dataMap.get(key) → O(1)
     *  - updateNodeFrequency() → O(1) (as analyzed above)
     *
     * Insert new key path:
     *  - dataMap.size() → O(1)
     *
     * evictLFUNode():
     *  - removeLast() → O(1) doubly linked list removal
     * dataMap.remove() → O(1) HashMap removal
     *
     * - new Node() → O(1)
     *
     * dataMap.put() → O(1) HashMap insertion
     * addNodeToFrequencyList() → O(1) (as analyzed above)
     *
     * Space Complexity: O(1)
     *
     * Only creates one new Node object when inserting (not updating)
     * May create one new DoublyLinkedList if frequency doesn't exist
     * Both are constant space per operation
    * */
    public void put(int key, int value) {

        if (size==0) return;

        Node newNode;

        //updating existing element
        if(dataMap.containsKey(key)){
            // Get the existing node
            Node oldNode = dataMap.get(key);
            newNode = updateNodeFrequency(oldNode);
            newNode.data = value;

        }else{

            // If the cache is full remove the LFU node.
            if (dataMap.size()>=size) {
                // remove least frequently used node.
                evictLFUNode();
            }

            //Crate the new node
            newNode = new Node(key,value,1);
            //update minFrequency to 1.
            minFrequency=1;
        }

        // inserting new/updated node.
        dataMap.put(key,newNode); // to data map
        addNodeToFrequencyList(newNode, newNode.frequency); //to frequency map
    }

    public static void main(String[] args) {


        //["LFUCache","put","put","get","put","get","get","put","get","get","get"]
        //[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
        _19_LFUCache lfuCache = new _19_LFUCache(2);
        lfuCache.put(1, 1);  // cache=[1,_], cnt(1)=1
        lfuCache.put(2, 2); // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lfuCache.get(1)); // return 1 // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfuCache.put(3, 3); // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        System.out.println(lfuCache.get(2)); // return -1 (not found)
        System.out.println(lfuCache.get(3)); // return 3  // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfuCache.put(4, 4); // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        System.out.println(lfuCache.get(1));    // return -1 (not found)
        System.out.println(lfuCache.get(3));    // return 3 // cache=[3,4], cnt(4)=1, cnt(3)=3
        System.out.println(lfuCache.get(4));    // return 4 // cache=[4,3], cnt(4)=2, cnt(3)=3

    }
}