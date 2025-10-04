package stack_queue;

import java.util.HashMap;

/**
 * Problem Link : https://leetcode.com/problems/lru-cache/description/
 * Video Explanation : https://www.youtube.com/watch?v=7ABFKPK2hD4
 *
 * 146. LRU Cache
 * Attempted
 * Medium
 *
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *     LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 *     int get(int key) Return the value of the key if the key exists, otherwise return -1.
 *     void put(int key, int value) Update the value of the key if the key exists. Otherwise,
 *     add the key-value pair to the cache. If the number of keys exceeds the capacity from
 *     this operation, evict the least recently used key.
 *
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Example 1:
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 * Constraints:
 *     1 <= capacity <= 3000
 *     0 <= key <= 104
 *     0 <= value <= 105
 *     At most 2 * 105 calls will be made to get and put.
 *
 *
 */
public class _18_LRUCache1 {

    class Node{
        Node pre,next;
        int key,data;
        Node(int key,int data){
            this.key = key;
            this.data=data;
            pre=null;
            next=null;
        }
    }

    Node head; // Points to most recently used item.
    Node tail; // Least Recently used item.
    int size;
    HashMap<Integer,Node> dataMap;

    public _18_LRUCache1(int capacity) {
        size=capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.pre = head;
        dataMap = new HashMap<>();
    }

    private void delete(Node node){
        Node previousNode = node.pre;
        Node nextNode = node.next;
        if (previousNode!=null) previousNode.next = nextNode;
        nextNode.pre = previousNode;
    }

    private void insert(Node node){
        Node previousNode = head;
        Node nextNode = head.next;
        previousNode.next = node;
        nextNode.pre = node;
        node.next = nextNode;
        node.pre = previousNode;
    }

    /**
     * Time Complexity: O(1)
     * - HashMap containsKey check: O(1)
     * - HashMap get operation: O(1)
     * - delete() operation: O(1) - updates 2 pointers
     * - insert() operation: O(1) - updates 4 pointers
     *
     * Space Complexity: O(1)
     * - No additional space allocated
     * - Only uses existing references
     */
    public int get(int key) {
        if (!dataMap.containsKey(key)) return -1;
        Node node = dataMap.get(key);
        //remove the previous links node
        delete(node);
        //move the node towards head - most recently used.
        insert(node);
        return node.data;
    }

    /**
     * Time Complexity: O(1)
     * - HashMap containsKey check: O(1)
     * - HashMap get/put operations: O(1)
     * - delete() operation: O(1) - updates 2 pointers
     * - insert() operation: O(1) - updates 4 pointers
     * - HashMap remove (eviction): O(1)
     *
     * Space Complexity: O(1)
     * - Creates one new Node object per call
     * - Old node is removed if capacity exceeded
     * - Net space change is O(1) per operation
     */
    public void put(int key, int value) {

        if (dataMap.containsKey(key)){
            // If the key is existing then remove that one first.
            delete(dataMap.get(key));
        }

        Node node = new Node(key,value);
        dataMap.put(key,node);
        insert(node);

        // if size > max capacity then delete/evict items from least recently used node.
        if (dataMap.size()>size){
            Node tobeDeleted = tail.pre;
            delete(tobeDeleted); // delete the node.
            dataMap.remove(tobeDeleted.key); // remove from map.
        }
    }

    public static void main(String[] args) {

        _18_LRUCache1 lRUCache = new _18_LRUCache1(2);

        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4

        System.out.println("==========================================================");

        //["LRUCache","get","put","get","put","put","get","get"]
        //[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
        //Expected : [null,-1,null,-1,null,null,2,6]

        _18_LRUCache1 lRUCache1 = new _18_LRUCache1(2);

        System.out.println(lRUCache1.get(2));
        lRUCache1.put(2, 6);
        System.out.println(lRUCache1.get(1));
        lRUCache1.put(1, 5);
        lRUCache1.put(1, 2);
        System.out.println(lRUCache1.get(1));
        System.out.println(lRUCache1.get(2));

        System.out.println("==========================================================");

        //["LRUCache","put","put","put","put","get","get"]
        //[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
        //Expected : [null,null,null,null,null,-1,3]

        _18_LRUCache1 lRUCache2 = new _18_LRUCache1(2);
        lRUCache2.put(2, 1);
        lRUCache2.put(1, 1);
        lRUCache2.put(2, 3);
        lRUCache2.put(4, 1);
        System.out.println(lRUCache2.get(1));
        System.out.println(lRUCache2.get(2));
    }
}