package array_problems;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Problem Link : https://leetcode.com/problems/find-the-duplicate-number/
 * Video : https://www.youtube.com/watch?v=wjYnzkAhcNk
 * Topic : TArrays, Floyd's Cycle Detection
 *
 * 287. Find the Duplicate Number
 * Attempted
 * Medium
 * Topics
 * Companies
 *
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * There is only one repeated number in nums, return this repeated number.
 * You must solve the problem without modifying the array nums and using only constant extra space.
 *
 * Example 1:
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 * Example 3:
 * Input: nums = [3,3,3,3,3]
 * Output: 3
 *
 * Constraints:
 *     1 <= n <= 105
 *     nums.length == n + 1
 *     1 <= nums[i] <= n
 *
 *     All the integers in nums appear only once except for precisely one integer which
 *     appears two or more times.
 *
* */
class _20_FindTheDuplicateNumber {

    /**
     * Solution 1 : Brute force solution using extra space.
     * Clearly we need to optimise this as its stated in the question that we need to solve using
     * constant space.
     *
     * Time Complexity: O(n)
     * - Single pass through the array
     * - HashMap operations (containsKey, put) are O(1) average case
     * - Overall: O(n) where n is the length of the array
     *
     * Space Complexity: O(n)
     * - HashMap stores up to n-1 elements in the worst case (when duplicate is the last element)
     * - Space grows linearly with input size
     *
    * */
    public int findDuplicate(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0;i< nums.length;i++){
            int ele = nums[i];
            if (map.containsKey(ele)) return ele;
            map.put(ele,i);
        }
        return 0;
    }

    /**
    * Solution 2 : Optimal solution using fast & slow pointer / Floyd's Cycle Detection / find circle in
     * linked list problem.
     *
     * Time Complexity: O(n)
     *
     * First loop: Finds intersection point in the cycle - O(n)
     * Second loop: Finds cycle entrance (duplicate) - O(n)
     * Overall: O(n)
     *
     * Space Complexity: O(1)
     *
     * Only uses 3 integer variables (slow, fast, slow2)
     * No additional data structures
     *
    * */
    public int findDuplicate1(int[] nums) {
        int slow=0;
        int fast=0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (slow!=fast);

        int slow2 = 0;
        do {
            slow = nums[slow];
            slow2 = nums[slow2];
        }while (slow!=slow2);

        return slow;
    }

    public static void main(String[] args) {

        _20_FindTheDuplicateNumber solution = new _20_FindTheDuplicateNumber();

        int[] input = {1,3,4,2,2};
        System.out.println("Answer : "+ solution.findDuplicate(input));
        System.out.println("Answer : "+ solution.findDuplicate1(input));

        int[] input1 = {1,3,4,2,2};
        System.out.println("Answer : "+ solution.findDuplicate(input1));
        System.out.println("Answer : "+ solution.findDuplicate1(input1));

        int[] input2 = {3,3,3,3,3};
        System.out.println("Answer : "+ solution.findDuplicate(input2));
        System.out.println("Answer : "+ solution.findDuplicate1(input2));

        int[] input3 = {2,6,4,1,3,1,5};
        System.out.println("Answer : "+ solution.findDuplicate(input3));
        System.out.println("Answer : "+ solution.findDuplicate1(input3));

        int[] input4 = {2,5,9,6,9,3,8,9,7,1};
        System.out.println("Answer : "+ solution.findDuplicate(input4));
        System.out.println("Answer : "+ solution.findDuplicate1(input4));

        int[] input5 = {3,1,3,4,2};
        System.out.println("Answer : "+ solution.findDuplicate(input5));
        System.out.println("Answer : "+ solution.findDuplicate1(input5));
    }
}
