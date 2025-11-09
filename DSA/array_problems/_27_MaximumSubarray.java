package array_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Problem Link : https://leetcode.com/problems/maximum-subarray/description/
 * Video : https://www.youtube.com/watch?v=5WZl3MMT0Eg
 * Topic : TArrays
 *
 *
 * 53. Maximum Subarray
 * Solved
 * Medium
 *
 * Given an integer array nums, find the
 * with the largest sum, and return its sum.
 *
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 *
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 *
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 * Constraints:
 *
 *     1 <= nums.length <= 105
 *     -104 <= nums[i] <= 104
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the
 * divide and conquer approach, which is more subtle.
 *
* */
class _27_MaximumSubarray {


    public int maxSubArray(int[] nums) {
        int maxSubArray = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int currentSum = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                currentSum+=nums[j];
                maxSubArray = Math.max(currentSum,maxSubArray);
            }
        }
        return maxSubArray;
    }

    /**
     * Solution 2 : Optimal - Kadanes Algorithm
     *
     * Time Complexity: O(n)
     * Single pass through the array with constant-time operations in each iteration
     * No nested loops or recursive calls
     *
     * Space Complexity: O(1)
     *
     * Uses only a fixed number of variables (maxSubArray, currentSum, startIndex, endIndex)
     * No additional data structures that scale with input size
     *
     * Intuition:
     * The intuition of the algorithm is not to consider the subarray as a part of the answer if its sum is less than 0. A subarray with a sum less than 0 will always reduce our answer and so this type of subarray cannot be a part of the subarray with maximum sum.
     * Here, we will iterate the given array with a single loop and while iterating we will add the elements in a sum variable. Now, if at any point the sum becomes less than 0, we will set the sum as 0 as we are not going to consider any subarray with a negative sum. Among all the sums calculated, we will consider the maximum one.
     * Thus we can solve this problem with a single loop.
     *
     * In a variation to this question if it is asked to print the subarray we can add two more variables there
     * to store the start index and end index we can use the startIndex,endIndex variables for the same.
    * */
    public int maxSubArray1(int[] nums) {
        int maxSubArray=nums[0];
        int currentSum=0;
        int startIndex=0;
        int endIndex=0;
        for (int i = 0; i < nums.length; i++) {
            if (currentSum<0){
                currentSum=0;
                startIndex = i;
            }
            currentSum+=nums[i];
            if (currentSum>maxSubArray) endIndex = i;
            maxSubArray = Math.max(maxSubArray,currentSum);
        }
        System.out.println("Start - End : "+startIndex+" "+endIndex);
        return maxSubArray;
    }

    public static void main(String[] args) {

        _27_MaximumSubarray solution = new _27_MaximumSubarray();

        int[] input = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("Answer : "+ solution.maxSubArray(input));
        System.out.println("Answer : "+ solution.maxSubArray1(input));

        int[] input1 = {5,4,-1,7,8};
        System.out.println("Answer : "+ solution.maxSubArray(input1));
        System.out.println("Answer : "+ solution.maxSubArray1(input1));
    }
}
