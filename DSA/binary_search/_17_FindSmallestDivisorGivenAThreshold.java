package binary_search;

/**
 * Problem Link : https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/
 * Video Explanation :
 * @see _16_MinNumberOfDaysToMakeMBouquets similar problem.
 * @see _15_KokoEatingBananas similar problem. See the explanations of these solutions to understand.
 *
 *
 * 1283. Find the Smallest Divisor Given a Threshold
 * Solved
 * Medium
 * Topics
 *
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor,
 * divide all the array by it, and sum the division's result. Find the smallest divisor such that
 * the result mentioned above is less than or equal to threshold.
 *
 * Each result of the division is rounded to the nearest integer greater than
 * or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
 *
 * The test cases are generated so that there will be an answer.
 *
 * Example 1:
 *
 * Input: nums = [1,2,5,9], threshold = 6
 * Output: 5
 * Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
 * If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
 *
 * Example 2:
 *
 * Input: nums = [44,22,33,11,1], threshold = 5
 * Output: 44
 *
 * Constraints:
 *     1 <= nums.length <= 5 * 104
 *     1 <= nums[i] <= 106
 *     nums.length <= threshold <= 106
 *
 */
public class _17_FindSmallestDivisorGivenAThreshold {

    private boolean isDivisible(int[] nums,int divisor,int threshold){
        long totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int res = (int) Math.ceil((double) nums[i]/divisor);
            totalSum+=res;
            if (totalSum>threshold) return false;
        }
        return true;
    }

    /**
     * Solution 1 : Brute Force
     *
     * Time Complexity: O(n × m)
     * m = range of values (max - min)
     * n = array length
     * Outer loop iterates through all divisors from min to max: O(m)
     * isDivisible checks all array elements: O(n)
     * Combined: O(n × m)
     * In worst case, m can be as large as max value in array, making this O(n × max_value)
     *
     * Space Complexity: O(1)
     *
     * Uses only a few variables (totalSum, min, max, res)
     * No additional data structures that scale with input size
    * */
    public int smallestDivisor(int[] nums, int threshold) {
        int max=nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max,nums[i]);
        }

        for (int i = 1; i <= max; i++) {
            if (isDivisible(nums,i,threshold)) return i;
        }

        return -1;
    }


    /**
     * Solution 2 : Optimal
     *
     * Time Complexity: O(n × log(max))
     * Binary search on divisor range [1, max]: O(log(max))
     * Each iteration calls isDivisible: O(n)
     * Combined: O(n × log(max))
     *
     * Space Complexity: O(1)
     * Only uses constant extra space
     *
    * */
    public int smallestDivisor1(int[] nums, int threshold) {
        int min=1;
        int max=nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max,nums[i]);
        }

        while (min<=max) {
            int divisor = (min+max)/2;
            if (isDivisible(nums,divisor,threshold)){
                max=divisor-1;
            }else {
                min = divisor+1;
            }
        }

        return min;
    }

    public static void main(String[] args) {

        _17_FindSmallestDivisorGivenAThreshold obj = new _17_FindSmallestDivisorGivenAThreshold();

        int[] piles = {1,2,5,9};
        int threshold = 6;
        System.out.println("Possible K : "+obj.smallestDivisor(piles,threshold));
        System.out.println("Possible K : "+obj.smallestDivisor1(piles,threshold));

        int[] piles1 = {44,22,33,11,1};
        int threshold1 = 5;
        System.out.println("Possible K : "+obj.smallestDivisor(piles1,threshold1));
        System.out.println("Possible K : "+obj.smallestDivisor1(piles1,threshold1));

    }
}