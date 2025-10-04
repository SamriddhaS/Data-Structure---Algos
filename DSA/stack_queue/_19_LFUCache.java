package stack_queue;

import java.util.HashMap;

/**
 * Problem Link : https://leetcode.com/problems/lru-cache/description/
 * Video Explanation : https://www.youtube.com/watch?v=7ABFKPK2hD4
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

    public _19_LFUCache(int capacity) {
        size=capacity;
        minFrequency=0;
        dataMap = new HashMap<>();
        frequencyLists = new HashMap<>();
    }


    public int get(int key) {
        if(!dataMap.containsKey(key)) return -1;
        // Get the existing node
        Node node = dataMap.get(key);
        int oldFreq = node.frequency;

        // Remove from frequencyLists
        frequencyLists.get(oldFreq).remove(node);

        // update the existing node, no need to create a new one.
        int freq = oldFreq+1;
        node.frequency = freq;

        if (!frequencyLists.containsKey(freq)){
            frequencyLists.put(freq,new DoublyLinkedList());
        }

        frequencyLists.get(freq).addFirst(node);

        if (oldFreq == minFrequency && frequencyLists.get(oldFreq).isEmpty()) {
            minFrequency = freq;
        }

        return node.data;
    }


    public void put(int key, int value) {

        if (size==0) return;

        int freq=1;
        Node newNode;

        //updating existing element
        if(dataMap.containsKey(key)){
            // Get the existing node
            Node node = dataMap.get(key);
            int oldFreq = node.frequency;

            // Remove from frequencyLists
            frequencyLists.get(oldFreq).remove(node);

            // update the existing node, no need to create a new one.
            freq = oldFreq+1;
            node.frequency = freq;
            node.data = value;
            newNode = node;

            // Update minFrequency if needed
            if (oldFreq == minFrequency && frequencyLists.get(oldFreq).isEmpty()) {
                minFrequency = freq;
            }
        }else{

            // If the cache is full remove the LFU node.
            if (dataMap.size()>=size) {
                // remove least frequently used node.
                Node node = frequencyLists.get(minFrequency).removeLast();
                dataMap.remove(node.key);
            }

            newNode = new Node(key,value,freq);
            minFrequency=1;
        }

        // inserting new element
        dataMap.put(key,newNode);
        if (!frequencyLists.containsKey(freq)){
            frequencyLists.put(freq,new DoublyLinkedList());
        }
        frequencyLists.get(freq).addFirst(newNode);

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