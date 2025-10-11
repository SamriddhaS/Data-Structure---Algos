package binary_search;

import java.util.Arrays;

/**
 * Problem Link : https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 * Video Explanation : https://www.youtube.com/watch?v=hjR1IYVx9lY
 * Problem 2 : https://takeuforward.org/data-structure/count-occurrences-in-sorted-array/ (this solution can be
 * used for this solution as well)
 *
 * 34. Find First and Last Position of Element in Sorted Array
 * Medium
 * Topics
 *
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending
 * position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 * Example 3:
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 * Constraints:
 *     0 <= nums.length <= 105
 *     -109 <= nums[i] <= 109
 *     nums is a non-decreasing array.
 *     -109 <= target <= 109
 *
 */
public class _6_FindFirstAndLastPositionOfElementInSortedArray {

    /**
     * Solution 1 : First one I came up with.
     * Not an optimal solution as in worst case if all the elements are target elements we iterate
     * through all the elements so O(n).
     *
     * Time: O(n) worst case - Binary search finds target in O(log n), but linear
     * expansion in both directions takes O(n) when many duplicates exist
     * Space: O(1)
     *
    * */
    public int[] searchRange(int[] nums, int target) {
        if(nums.length==0) return new int[]{-1,-1};
        int start=0,end=nums.length-1;
        int startP=-1,endP=-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(nums[mid]<target){
                start = mid+1;
            } else if (nums[mid]>target) {
                end = mid-1;
            }else {
                startP=mid;
                endP=mid;
                while (startP-1>=0 && nums[startP-1]==nums[mid]){
                    startP--;
                }
                while (endP+1<nums.length&&nums[endP+1]==nums[mid]){
                    endP++;
                }
                return new int[]{startP,endP};
            }
        }
        return new int[]{-1,-1};
    }

    /**
     *
     * Time Complexity: O(log n)
     *
     * First binary search (lower bound):
     * log(n)
     * Second binary search (upper bound):
     * Again log₂(n) iterations
     * - Total: log₂(n) + log₂(n) = 2·log₂(n) = O(log n)
     *
     * Space Complexity: O(1)
     *
     * Only uses fixed variables: start, end, mid, lower, upper
     * No recursion stack, no additional data structures
     * Space usage doesn't grow with input size
     *
    * */
    public int[] searchRange1(int[] nums, int target) {
        if(nums.length==0) return new int[]{-1,-1};
        int start=0,end=nums.length-1;
        int lower=-1;
        // find the lower bound : left most element that is = target.
        while(start<=end){
            int mid = (start+end)/2;
            if(nums[mid]<target){
                start = mid+1;
            } else if (nums[mid]>target) {
                end = mid-1;
            }else {
                lower = mid;
                end = mid-1;
            }
        }

        // element is not present return -1.
        if (lower==-1||nums[lower]!=target) return new int[]{-1,-1};

        // find the upper bound : right most element = target.
        start=0;
        end=nums.length-1;
        int upper = nums.length;
        while(start<=end){
            int mid = (start+end)/2;
            if(nums[mid]<target){
                start = mid+1;
            } else if (nums[mid]>target) {
                end = mid-1;
            }else {
                upper = mid;
                start = mid+1;
            }
        }
        return new int[]{lower,upper};
    }

    public static void main(String[] args) {

        _6_FindFirstAndLastPositionOfElementInSortedArray obj = new _6_FindFirstAndLastPositionOfElementInSortedArray();

        int[] input = {5,7,7,8,8,10};
        int target = 8;
        System.out.println("Out : "+ Arrays.toString(obj.searchRange(input, target)));
        System.out.println("Out : "+ Arrays.toString(obj.searchRange1(input, target)));

        int[] input1 = {5,7,7,8,8,9,9,9,9,9,10};
        int target1 = 9;
        System.out.println("Out : "+ Arrays.toString(obj.searchRange(input1, target1)));
        System.out.println("Out : "+ Arrays.toString(obj.searchRange1(input1, target1)));

    }
}