package DSA.array_problems;


import java.awt.event.AdjustmentEvent;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Problem Link : https://leetcode.com/problems/reverse-pairs/description/
 * Video : https://www.youtube.com/watch?v=0e4bZaP3MDI
 * Topic : TMergeSort, _5_CountInversions, _1_MergedSort
 *
 * 493. Reverse Pairs
 * Given an integer array nums, return the number of reverse pairs in the array.
 * A reverse pair is a pair (i, j) where:
 *
 *     0 <= i < j < nums.length and
 *     nums[i] > 2 * nums[j].
 *
 * Example 1:
 *
 * Input: nums = [1,3,2,3,1]
 * Output: 2
 * Explanation: The reverse pairs are:
 * (1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
 * (3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1
 *
 * Example 2:
 *
 * Input: nums = [2,4,3,5,1]
 * Output: 3
 * Explanation: The reverse pairs are:
 * (1, 4) --> nums[1] = 4, nums[4] = 1, 4 > 2 * 1
 * (2, 4) --> nums[2] = 3, nums[4] = 1, 3 > 2 * 1
 * (3, 4) --> nums[3] = 5, nums[4] = 1, 5 > 2 * 1
 *
 * Constraints:
 *     1 <= nums.length <= 5 * 104
 *     -231 <= nums[i] <= 231 - 1
 *
* */
class _12_ReversePairs {

    /**
     * Brute force check each and every combination.
     * Will give TLE error in leetcode.
    * */
    public int reversePairs(int[] nums) {
        int answer=0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > (long)2 * nums[j]) answer++;
            }
        }
        return answer;
    }

    private void merge(int[] nums,int start,int end,int mid){
        int left=start;
        int right=mid+1;
        int[] sortedArr = new int[end-start+1];
        int index = 0;
        while (left<=mid&&right<=end){
            if (nums[left]>nums[right]){
                sortedArr[index] = nums[right];
                right++;
            }else {
                sortedArr[index] = nums[left];
                left++;
            }
            index++;
        }
        while(left<=mid){
            sortedArr[index] = nums[left];
            left++;
            index++;
        }
        while(right<=end){
            sortedArr[index] = nums[right];
            right++;
            index++;
        }
        index=0;
        int tempStart=start;
        while (tempStart<=end){
            nums[tempStart] = sortedArr[index];
            index++;
            tempStart++;
        }
    }

    private int findPairs(int[] nums,int start,int end,int mid){
        int answer=0;
        int left=start;
        int right=mid+1;
        while (left<=mid){
            while(right<=end && nums[left] > (long)2 * nums[right]) right++;
            answer+= right-(mid+1);
            left++;
        }
        return answer;
    }

    private int divideAndCountReversePairs(int[] nums,int start,int end){
        int answer=0;

        if (start==end) return answer;

        int mid = (start+end)/2;

        // divide
        answer = divideAndCountReversePairs(nums, start, mid);
        answer+= divideAndCountReversePairs(nums, mid+1, end);

        // Now lets try to find the pairs in sorted array;
        answer+=findPairs(nums,start,end,mid);

        // compare & merge
        merge(nums,start,end,mid);

        return answer;

    }

    /**
     * Time Complexity: O(n log n)
     *
     * Breakdown:
     * - Divide: O(log n) levels in the recursion tree
     * - Count pairs: O(n) per level - each element is visited by both pointers at most once across all calls at each level
     * - Merge: O(n) per level - standard merge operation
     * Total: O(n log n)
     *
     * The key insight is that the right pointer never resets, so across all pair-finding operations at
     * each recursion level, each element is processed at most once.
     *
     *
     * Space Complexity: O(n)
     * Breakdown:
     * - Recursion stack: O(log n) depth
     * - Temporary merge array: O(n) for the largest merge operation
     * - Total: O(n) - dominated by the merge array
     *
     * The space complexity is linear because while we create temporary arrays during merging, they don't
     * accumulate (each recursive call's array is freed before the next merge at the same level).
     *
    * */
    public int reversePairsUsingMergeSort(int[] nums){
        int answer = divideAndCountReversePairs(nums,0,nums.length-1);
        return answer;
    }

    public static void main(String[] args) {

        _12_ReversePairs solution = new _12_ReversePairs();

        int[] inputArr = new int[]{1,3,2,3,1};
        int result = solution.reversePairs(inputArr);
        int _result = solution.reversePairsUsingMergeSort(inputArr);
        System.out.println(result);
        System.out.println(_result);

        int[] inputArr1 = new int[]{2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        int result1 = solution.reversePairs(inputArr1);
        int _result1 = solution.reversePairsUsingMergeSort(inputArr1);
        System.out.println(result1);
        System.out.println(_result1);

        int[] inputArr2 = new int[]{-5,-5};
        int result2 = solution.reversePairs(inputArr2);
        int _result2 = solution.reversePairsUsingMergeSort(inputArr2);
        System.out.println(result2);
        System.out.println(_result2);

    }
}
