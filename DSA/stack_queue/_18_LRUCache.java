package stack_queue;


import java.util.*;

/**
 * Problem Link : https://leetcode.com/problems/lru-cache/description/
 * Video Explanation :
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
public class _18_LRUCache {

    class LRUData {
        int key, priority;

        LRUData(int key, int priority) {
            this.key = key;
            this.priority = priority;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            LRUData other = (LRUData) obj;
            return this.key == other.key && this.priority == other.priority;
        }
    }

    HashMap<Integer, LRUData> map;
    PriorityQueue<LRUData> priorityQueue;
    int size;
    int maxPriority;

    public _18_LRUCache(int capacity) {
        map = new HashMap<>();
        size = capacity;
        maxPriority = 0;
        priorityQueue = new PriorityQueue<>((o1, o2) -> o1.priority - o2.priority);
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        LRUData data = map.get(key);// find the data we are looking to get

        // remove the data as we need to change its priority
        priorityQueue.remove(new LRUData(key,data.priority));
        map.remove(key);

        // increase priority of the item both in queue and data map.
        map.put(key,new LRUData(data.key, ++maxPriority));
        priorityQueue.add(new LRUData(key, maxPriority));

        return data.key;
    }

    public void put(int key, int value) {
        if (map.size() >= size) {
            // get the lowest priority item.
            LRUData data = priorityQueue.poll();
            // remove it from map.
            map.remove(data.key);
            // now our map has empty space to add the new item with highest priority
        }
        map.put(key,new LRUData(value,++maxPriority));
        priorityQueue.add(new LRUData(key, maxPriority));
    }

    public static void main(String[] args) {

        _18_LRUCache lRUCache = new _18_LRUCache(2);

        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4

    }
}