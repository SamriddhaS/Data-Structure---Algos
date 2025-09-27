package array_problems;


import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Problem Link : https://leetcode.com/problems/single-number/description/
 * Video :
 * Topic : TArrays
 *
 * 136. Single Number
 * Easy
 * Topics
 *
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 * Input: nums = [2,2,1]
 * Output: 1
 *
 * Example 2:
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [1]
 * Output: 1
 *
 * Constraints:
 *     1 <= nums.length <= 3 * 104
 *     -3 * 104 <= nums[i] <= 3 * 104
 *     Each element in the array appears twice except for one element which appears only once.
 *
* */
class _17_SingleNumber {

    /**
     * Solution 1 : Brute Force
     * Time Complexity: O(n log n)
     *
     * The Arrays.sort(nums) operation dominates the time complexity at O(n log n)
     * The for loop runs at most n/2 iterations, which is O(n)
     * Overall: O(n log n) + O(n) = O(n log n)
     *
     * Space Complexity: O(1) or O(log n)
     *
     * This depends on the sorting algorithm implementation
     * Arrays.sort() uses a variant of quicksort/mergesort, which typically requires O(log n) space for the call stack
     * If we consider the sorting as using constant extra space (ignoring the recursion stack), it would be O(1)
     * Most analyses consider it O(log n) due to the sorting overhead
    * */
    public int singleNumber(int[] nums) {
        if (nums.length==1) return nums[0];
        Arrays.sort(nums);
        for (int i=0;i<nums.length-1;i+=2){
            if (nums[i]!=nums[i+1]) return nums[i];
        }
        return nums[nums.length-1];
    }

    /**
     * Time Complexity: O(n)
     *
     * - The algorithm makes a single pass through the array
     * - Each XOR operation takes constant time O(1)
     * - Total: n iterations × O(1) per iteration = O(n)
     *
     * Space Complexity: O(1)
     *
     * - Only uses one extra variable (answer) regardless of input size
     * - No additional data structures or recursion stack
     * - Memory usage remains constant
     *
     *
     * Why XOR works for this problem:
     *
     * XOR has two key mathematical properties that solve this perfectly:
     *
     * Self-cancellation: A ^ A = 0 - Any number XORed with itself equals zero
     * Identity: A ^ 0 = A - Any number XORed with zero equals itself
     *
     * The logic:
     * - Every duplicate number appears exactly twice in the array
     * - When you XOR all numbers together, each pair of duplicates cancels out to 0
     * - Only the single number remains because it has no pair to cancel with
     *
     * Example: [4,1,2,1,2]
     * Conceptually: 4 ^ 1 ^ 2 ^ 1 ^ 2
     * Rearranging: 4 ^ (1 ^ 1) ^ (2 ^ 2)
     * Simplifying: 4 ^ 0 ^ 0 = 4
    * */
    public int singleNumber1(int[] nums) {
        int answer=0;
        for (int i=0;i<nums.length;i++){
            answer^=nums[i];
        }
        return answer;
    }

    public static void main(String[] args) {

        _17_SingleNumber solution = new _17_SingleNumber();

        int[] input = {2,2,1};
        System.out.println("Answer : "+solution.singleNumber(input));
        System.out.println("Answer : "+solution.singleNumber1(input));

        int[] input1 = {4,1,2,1,2};
        System.out.println("Answer : "+solution.singleNumber(input1));
        System.out.println("Answer : "+solution.singleNumber1(input1));

        int[] input2 = {1};
        System.out.println("Answer : "+solution.singleNumber(input2));
        System.out.println("Answer : "+solution.singleNumber1(input2));

    }
}
