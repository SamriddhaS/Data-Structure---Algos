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

    /**
     * Partially working solution. Some test cases are failing
     * */

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
            return this.key == other.key;
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
        if (map.size() >= size && !map.containsKey(key)) {
            // get the lowest priority item.
            LRUData data = priorityQueue.poll();
            // remove it from map.
            map.remove(data.key);
            // now our map has empty space to add the new item with highest priority
        } else if (map.size() >= size && map.containsKey(key)) {
            // As we are updating the value of an existing key, we still need to
            // update its priority.
            LRUData data = map.get(key);
            priorityQueue.remove(new LRUData(key,data.priority));
            map.remove(key);
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

        System.out.println("==========================================================");

        //["LRUCache","get","put","get","put","put","get","get"]
        //[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
        //Expected : [null,-1,null,-1,null,null,2,6]

        _18_LRUCache lRUCache1 = new _18_LRUCache(2);

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

        _18_LRUCache lRUCache2 = new _18_LRUCache(2);
        lRUCache2.put(2, 1);
        lRUCache2.put(1, 1);
        lRUCache2.put(2, 3);
        lRUCache2.put(4, 1);
        System.out.println(lRUCache2.get(1));
        System.out.println(lRUCache2.get(2));


        System.out.println("========== BUG TEST CASE ==========");

        String[] operations = {"LRUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"};

        int[][] values = {{10},{10,13},{3,17},{6,11},{10,5},{9,10},{13},{2,19},{2},{3},{5,25},{8},{9,22},{5,5},{1,30},{11},{9,12},{7},{5},{8},{9},{4,30},{9,3},{9},{10},{10},{6,14},{3,1},{3},{10,11},{8},{2,14},{1},{5},{4},{11,4},{12,24},    {5,18},{13},{7,23},{8},{12}    ,{3,27},{2,12},{5},{2,9},{13,4},{8,18},{1,7},{6},{9,29},{8,21},{5},{6,30},{1,12},{10},{4,15},{7,22},{11,26},{8,17},{9,29},{5},{3,4},{11,30},{12},{4,29},{3},{9},{6},{3,4},{1},{10},{3,29},{10,28},{1,20},{11,13},{3},{3,12},{3,8},{10,9},{3,26},{8},{7},{5},{13,17},{2,27},{11,15},{12},{9,19},{2,15},{3,16},{1},{12,17},{9,1},{6,19},{4},{5},{5},{8,1},{11,7},{5,2},{9,28},{1},{2,2},{7,4},{4,22},{7,24},{9,26},{13,28},{11,26}};

        _18_LRUCache cache = null;

        System.out.println("Executing operations:");
        for (int i = 0; i < operations.length; i++) {
            String op = operations[i];

            if (op.equals("LRUCache")) {
                cache = new _18_LRUCache(values[i][0]);
                System.out.println(i + ": LRUCache(" + values[i][0] + ") -> null");
            } else if (op.equals("put")) {
                cache.put(values[i][0], values[i][1]);
                System.out.println(i + ": put(" + values[i][0] + ", " + values[i][1] + ") -> null");
            } else if (op.equals("get")) {
                int result = cache.get(values[i][0]);
                System.out.println(i + ": get(" + values[i][0] + ") -> " + result);
            }
        }
    }
}