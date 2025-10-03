package array_problems;

import java.util.HashMap;

/**
 * Problem Link : https://takeuforward.org/data-structure/length-of-the-longest-subarray-with-zero-sum/
 * Video : https://www.youtube.com/watch?v=frf7qxiN2qU
 * Topic : TArrays
 *
 * Variant 1: Longest Sub-array with Sum K (Positive Integers Only)
 * Problem: Given an array nums of size n containing only positive integers and an integer k,
 * find the length of the longest sub-array that sums to k. If no such sub-array exists, return 0.
 *
 * Examples:
 *     Input: nums = [10, 5, 2, 7, 1, 9], k = 15
 *         Output: 4
 *         Explanation: The longest sub-array is [5, 2, 7, 1], with a length of 4.
 *
 *     Input: nums = [1, 2, 3], k = 6
 *         Output: 3
 *         Explanation: The sub-array [1, 2, 3] sums to 6 and has a length of 3.
 *
 * Constraints:
 *     1 <= n <= 10^5
 *     1 <= nums[i] <= 10^5
 *     1 <= k <= 10^9
 *
 *
 *
 * Variant 2: Longest Sub-array with Sum K (Positive and Negative Integers)
 *
 * Problem: Given an array nums of size n containing positive and negative integers,
 * and an integer k, find the length of the longest sub-array that sums to k.
 * If no such sub-array exists, return 0.
 *
 * Examples:
 *     Input: nums = [10, 5, 2, 7, 1, 9], k = 15
 *         Output: 4
 *         Explanation: The longest sub-array is [5, 2, 7, 1], with a length of 4.
 *
 *     Input: nums = [-3, 2, 1], k = 6
 *         Output: 0
 *         Explanation: No sub-array in the given array sums to 6.
 *
 *     Input: nums = [2, -2, 4, 3, -1, 5], k = 7
 *         Output: 4
 *         Explanation: The longest sub-array [2, -2, 4, 3] sums to 7 and has a length of 4.
 *
 * Constraints:
 *     1 <= n <= 10^5
 *     -10^5 <= nums[i] <= 10^5
 *     -10^9 <= k <= 10^9
 *
* */
class _22_LongestSubArrayWithSumK {

    /**
     * Solution 1 : Brute Force.
     * Time Complexity : O(n^2)
     * Space Complexity : O(1)
     * Will work for both variant 1 & 2.
    * */
    public int longestSubArrayWithSumK(int[] nums, int k) {
        int answer=0;
        for (int i = 0; i < nums.length; i++) {
            int sum=0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum==k){
                    answer = Math.max(answer,j-i+1);
                }
            }
        }
        return answer;
    }

    /**
     * Solution 2 : Better solution using hashing and prefix sum.
     * This will be optimal solution for Variant 2 (positive and negative numbers).
     *
     * Time Complexity: O(n)
     * - Single pass through the array (n elements)
     * - HashMap operations (put, get, containsKey) are O(1) average case
     * - Overall: O(n)
     *
     * Space Complexity: O(n)
     * - HashMap stores prefix sums with their indices
     * - Worst case: all prefix sums are unique, storing n entries
     * - Overall: O(n)
     *
    * */
    public int longestSubArrayWithSumK1(int[] nums, int k) {
        int answer=0;
        long sum= 0L;
        HashMap<Long,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
            if (sum==k){
                answer = Math.max(answer,i+1);
            }
            long diff = sum-k;
            if (map.containsKey(diff)){
                int length = i - map.get(diff);
                answer = Math.max(length,answer);
            }
            if (!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        return answer;
    }

    /**
     * Solution 3 : Optimal solution for variant 1. (Only positive numbers)
     *
     * Time Complexity: O(n)
     *
     * The right pointer traverses the array once: O(n)
     * The left pointer also moves at most n times across the entire execution (not per iteration)
     * Each element is visited at most twice (once by right, once by left)
     * Overall: O(n)
     *
     * Space Complexity: O(1)
     *
     * Only uses a constant number of variables (answer, sum, left, right)
     * No additional data structures that grow with input size
     * Overall: O(1)
    * */
    public int longestSubArrayWithSumK2(int[] nums, int k) {
        int answer=0;
        long sum= 0L;
        int left=0,right=0;
        while(right<nums.length){
            sum+=nums[right];
            while (left<right&&sum>k) {
                sum -= nums[left];
                left++;
            }
            if (sum==k){
                answer = Math.max(answer,right-left+1);
            }
            right++;
        }
        return answer;
    }

    public static void main(String[] args) {

        _22_LongestSubArrayWithSumK solution = new _22_LongestSubArrayWithSumK();

        int[] input = {1,1,1};
        int k=3;
        System.out.println("Answer : "+ solution.longestSubArrayWithSumK(input,k));
        System.out.println("Answer : "+ solution.longestSubArrayWithSumK1(input,k));
        System.out.println("Answer : "+ solution.longestSubArrayWithSumK2(input,k));

        int[] input1 = {1,2,3};
        int k1=2;
        System.out.println("Answer : "+ solution.longestSubArrayWithSumK(input1,k1));
        System.out.println("Answer : "+ solution.longestSubArrayWithSumK1(input1,k1));
        System.out.println("Answer : "+ solution.longestSubArrayWithSumK2(input1,k1));

        int[] input2 = {1,2,1,2,1};
        int k2=3;
        System.out.println("Answer : "+ solution.longestSubArrayWithSumK(input2,k2));
        System.out.println("Answer : "+ solution.longestSubArrayWithSumK1(input2,k2));
        System.out.println("Answer : "+ solution.longestSubArrayWithSumK2(input2,k2));

        int[] input3 = {1,-2,1,5,3,-7};
        int k3=2;
        System.out.println("Answer : "+ solution.longestSubArrayWithSumK(input3,k3));
        System.out.println("Answer : "+ solution.longestSubArrayWithSumK1(input3,k3));

    }
}
