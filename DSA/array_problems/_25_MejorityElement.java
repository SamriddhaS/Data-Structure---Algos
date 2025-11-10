package array_problems;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Problem Link : https://leetcode.com/problems/majority-element/description/
 * Video : https://www.youtube.com/watch?v=Q6L5SoS-fTo
 * Topic : TArrays
 * @see _26_MejorityElement2
 *
 * 169. Majority Element
 * Solved
 * Easy
 *
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 * Constraints:
 *     n == nums.length
 *     1 <= n <= 5 * 104
 *     -109 <= nums[i] <= 109
 *
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
* */
class _25_MejorityElement {

    /**
     * Solution 1 : Brute Force
     *
     * Time Complexity: O(n²)
     *
     * Outer loop runs n times
     * Inner loop runs n times for each outer iteration
     * Total: n × n = n² operations
     *
     * Space Complexity: O(1)
     *
     * Uses only a few variables (element, count)
     * No additional data structures
    * */
    public int majorityElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int element = nums[i];
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (element==nums[j]) count++;
            }
            if (count> nums.length/2) return element;
        }
        return 0;
    }

    /**
     * Solution 2 : Better using extra space
     *
     * Time Complexity: O(n)
     *
     * First loop: O(n) to build the HashMap
     * Second loop: O(k) where k = number of unique elements ≤ n
     * Total: O(n)
     *
     * Space Complexity: O(n)
     *
     * HashMap stores up to n entries in worst case (all unique elements)
     * */
    public int majorityElement1(int[] nums) {
        HashMap<Integer,Integer> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i],countMap.getOrDefault(nums[i],0)+1);
        }

        for (Integer key: countMap.keySet()){
            if (countMap.get(key)> nums.length/2){
                return key;
            }
        }

        return -1;
    }

    /**
     * Optimal Solution :  Boyer-Moore Voting Algorithm
     *
     * Time Complexity: O(n)
     *
     * First loop: O(n) to find candidate element
     * Second loop: O(n) to verify the candidate
     * Total: O(n) + O(n) = O(n)
     *
     * Space Complexity: O(1)
     * Only uses a few integer variables (element, count, eleCount)
     * No additional data structures that scale with input size
     *
     * Intuition:
     * If the array contains a majority element, its occurrence must be greater than the floor(N/2). Now, we can say that
     * the count of minority elements and majority elements is equal up to a certain point in the array.
     * So when we traverse through the array we try to keep track of the count of elements and the element
     * itself for which we are tracking the count.
     *
     * After traversing the whole array, we will check the element stored in the variable.
     * If the question states that the array must contain a majority element, the stored element
     * will be that one but if the question does not state so, then we need to check if the stored
     * element is the majority element or not. If not, then the array does not contain any majority element.
    * */
    public int majorityElement2(int[] nums) {
        int element = nums[0];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count==0){
                element = nums[i];
                count++;
            }else if(element==nums[i]){
                count++;
            }else {
                count--;
            }
        }

        int eleCount=0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==element){
                eleCount++;
            }
        }

        if (eleCount> nums.length/2) return element;

        return -1;
    }

    public static void main(String[] args) {

        _25_MejorityElement solution = new _25_MejorityElement();

        int[] input = {3,2,3};
        System.out.println("Answer : "+ solution.majorityElement(input));
        System.out.println("Answer : "+ solution.majorityElement1(input));

        int[] input1 = {2,2,1,1,1,2,2};
        System.out.println("Answer : "+ solution.majorityElement(input1));
        System.out.println("Answer : "+ solution.majorityElement1(input1));
    }
}
