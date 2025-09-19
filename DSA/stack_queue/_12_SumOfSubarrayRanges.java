package stack_queue;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/sum-of-subarray-ranges/
 * Video Explanation :
 *
 */
public class _12_SumOfSubarrayRanges {

    /**
     * Solution 1 : Brute force solution.
     *
     * Time Complexity: O(n²)
     * - Nested loops: outer loop runs n times, inner loop runs up to n-1 times
     * - Total iterations: n + (n-1) + (n-2) + ... + 1 = n(n+1)/2 ≈ O(n²)
     *
     * Space Complexity: O(1)
     * - Only uses constant extra space for variables (answer, min, max, i, j)
     * - No additional data structures that grow with input size
     *
    * */
    public long subArrayRanges(int[] nums) {
        long answer = 0;
        for (int i = 0; i < nums.length; i++) {
            long min = nums[i];
            long max = nums[i];
            for (int j=i+1;j< nums.length;j++){
                min = Math.min(nums[j],min);
                max = Math.max(nums[j],max);
                answer+=max-min;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        _12_SumOfSubarrayRanges obj = new _12_SumOfSubarrayRanges();
        int[] nums2 = {1,2,3};
        System.out.println("The Answer : " + obj.subArrayRanges(nums2));
    }
}