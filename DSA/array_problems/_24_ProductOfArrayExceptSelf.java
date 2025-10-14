package array_problems;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Problem Link : https://leetcode.com/problems/product-of-array-except-self/description/
 * Video :
 * Topic : TArrays,TPrefixSum
 *
 *
 * 238. Product of Array Except Self
 * Solved
 * Medium
 * Topics
 *
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product
 * of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 * Constraints:
 *     2 <= nums.length <= 105
 *     -30 <= nums[i] <= 30
 *     The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity?
 * (The output array does not count as extra space for space complexity analysis.)
 *
* */
class _24_ProductOfArrayExceptSelf {

    /**
     * Solution 1 : Brute Force.
     * Time Complexity : O(n^2)
     * Space Complexity : O(1)
    * */
    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int product=1;
            for (int j = 0; j < nums.length; j++) {
                if (i!=j) product*=nums[j];
            }
            answer[i]=product;
        }
        return answer;
    }

    /**
     * Solution 2 : Better - Using Prefix and Postfix Sum technique.
     *
     * Time Complexity: O(n)
     * Three sequential loops through the array (length n)
     * Each loop performs constant-time operations
     * 3n operations → O(n)
     *
     * Space Complexity: O(n)
     * predecessorProducts: O(n)
     * successorProducts: O(n)
     * answer: O(n) - We don't need to count this one as its mentioned answer is not considered in space
     * complexity analysis.
     *
     * Total: 2n → O(n)
    * */
    public int[] productExceptSelf1(int[] nums) {
        int[] answer = new int[nums.length];
        int[] predecessorProducts = new int[nums.length];
        Arrays.fill(predecessorProducts,1);
        int[] successorProducts = new int[nums.length];
        Arrays.fill(successorProducts,1);

        int products=1;
        for (int i = 1; i < nums.length; i++) {
            products*=nums[i-1];
            predecessorProducts[i] = products;
        }

        products=1;
        for (int i = nums.length-2; i >= 0 ; i--) {
            products*=nums[i+1];
            successorProducts[i] = products;
        }

        for (int i = 0; i < nums.length; i++) {
            answer[i] = predecessorProducts[i]*successorProducts[i];
        }

        return answer;
    }

    /**
     * Solution 3 : Same time complexity but O(1) space complexity as we don't need to consider answer array.
     *
     * Time Complexity: O(n)
     *
     * Two sequential loops through the array
     * Each loop performs constant-time operations
     * 2n operations → O(n)
     *
     * Space Complexity: O(1)
     *
     * Only uses two scalar variables (products and prefixProducts)
     * answer array excluded as it's the required output
     * No auxiliary data structures
    * */
    public int[] productExceptSelf2(int[] nums) {

        int[] answer = new int[nums.length];
        Arrays.fill(answer,1);

        int products=1;
        for (int i = nums.length-2; i >= 0 ; i--) {
            products*=nums[i+1];
            answer[i] = products;
        }

        int prefixProducts=1;
        for (int i = 0; i < nums.length; i++) {
            if (i>0) prefixProducts*=nums[i-1];
            answer[i] = prefixProducts*answer[i];
        }

        return answer;
    }

    public static void main(String[] args) {

        _24_ProductOfArrayExceptSelf solution = new _24_ProductOfArrayExceptSelf();

        int[] input = {1,2,3,4};
        System.out.println("Answer : "+ Arrays.toString(solution.productExceptSelf(input)));
        System.out.println("Answer : "+ Arrays.toString(solution.productExceptSelf1(input)));
        System.out.println("Answer : "+ Arrays.toString(solution.productExceptSelf2(input)));


        int[] input1 = {-1,1,0,-3,3};
        System.out.println("Answer : "+ Arrays.toString(solution.productExceptSelf(input1)));
        System.out.println("Answer : "+ Arrays.toString(solution.productExceptSelf1(input1)));
        System.out.println("Answer : "+ Arrays.toString(solution.productExceptSelf2(input1)));

    }
}
