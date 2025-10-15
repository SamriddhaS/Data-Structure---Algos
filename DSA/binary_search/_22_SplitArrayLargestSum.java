package binary_search;

/**
 * Problem Link :
 * 1. Split Array Largest Sum : https://leetcode.com/problems/split-array-largest-sum/
 * 2. Painters Partition : https://www.naukri.com/code360/problems/painter-s-partition-problem_1089557
 * Video Explanation : https://www.youtube.com/watch?v=thUd_WJn6wk
 *
 * @see _21_AllocateMinNumberOfPages same probelem.
 *
 * 410. Split Array Largest Sum
 * Solved
 * Hard
 *
 * Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
 *
 * Return the minimized largest sum of the split.
 *
 * A subarray is a contiguous part of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [7,2,5,10,8], k = 2
 * Output: 18
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 9
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 1000
 *     0 <= nums[i] <= 106
 *     1 <= k <= min(50, nums.length)
 *
 * ======================================================================================================================
 *
 *
 * Painter's Partition Problem
 *
 * Given an array/list of length ‘n’, where the array/list represents the boards and each
 * element of the given array/list represents the length of each board. Some ‘k’ numbers
 * of painters are available to paint these boards. Consider that each unit of a board takes 1
 * unit of time to paint.
 *
 * You are supposed to return the area of the minimum time to get this job done of
 * painting all the ‘n’ boards under a constraint that any painter will only paint
 * the continuous sections of boards.
 *
 * Example :
 * Input: arr = [2, 1, 5, 6, 2, 3], k = 2
 * Output: 11
 *
 * Explanation:
 * First painter can paint boards 1 to 3 in 8 units of time and the second painter can paint boards 4-6 in 11 units of time. Thus both painters will paint all the boards in max(8,11) = 11 units of time. It can be shown that all the boards can't be painted in less than 11 units of time.
 *
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * 4 2
 * 10 20 30 40
 *
 * Sample Output 1 :
 * 60
 *
 * Explanation For Sample Input 1 :
 * In this test case, we can divide the first 3 boards for one painter and the last board for the second painter.
 *
 * Sample Input 2 :
 * 2 2
 * 48 90
 *
 * Sample Output 2 :
 * 90
 *
 * Expected Time Complexity: Try to do this in O(n*log(n)).
 *
 * Constraints :
 * 1 <= n <= 10^5
 * 1 <= k <= n
 * 1 <= arr[i] <= 10^9
 *
 * Time Limit: 1 sec.
 */
public class _22_SplitArrayLargestSum {

    private int possibleSubArraysWithSum(int[] nums,int maxAllowedSubArraySum){
        int subArrayCount=1;
        long currentSum=0;
        for (int i = 0; i < nums.length; i++) {
            if (currentSum+nums[i]<=maxAllowedSubArraySum){
                currentSum+=nums[i];
            }else {
                subArrayCount++;
                currentSum = nums[i];
            }
        }
        return subArrayCount;
    }

    /**
     *
     * Time Complexity: O(n × log(maxSubArray - minSubArray))
     * Binary search: O(log(maxSubArray - minSubArray)) iterations
     * Each iteration calls possibleSubArraysWithSum: O(n)
     * Total: O(n × log(maxSubArray - minSubArray))
     *
     * Space Complexity: O(1)
     * Only uses constant extra space for variables
     *
     * */
    public int splitArray(int[] nums, int k) {
        int minSubArray = nums[0];
        int maxSubArray = 0;
        for (int i = 0; i < nums.length; i++) {
            minSubArray = Math.max(minSubArray,nums[i]);
            maxSubArray+=nums[i];
        }
        while(minSubArray<=maxSubArray){
            int maxAllowedSubArraySum = (minSubArray+maxSubArray)/2;
            int subArrayCount = possibleSubArraysWithSum(nums,maxAllowedSubArraySum);
            if (subArrayCount<=k){
                maxSubArray = maxAllowedSubArraySum-1;
            }else {
                minSubArray = maxAllowedSubArraySum+1;
            }
        }
        return minSubArray;
    }

    public static void main(String[] args) {

        _22_SplitArrayLargestSum obj = new _22_SplitArrayLargestSum();

        // Test Case 1: Original example
        int[] nums = {7,2,5,10,8};
        int k = 2;
        System.out.println("Test 1 - Expected: 113, Got: " + obj.splitArray(nums, k));

        // Test Case 2: Original example
        int[] nums2 = {1,2,3,4,5};
        int k2 = 2;
        System.out.println("Test 2 - Expected: 71, Got: " + obj.splitArray(nums2, k2));

    }
}