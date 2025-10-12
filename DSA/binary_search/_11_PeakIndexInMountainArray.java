package binary_search;

/**
 * Problem Link : https://leetcode.com/problems/peak-index-in-a-mountain-array/description/
 * Video Explanation : https://www.youtube.com/watch?v=Op07kT-LoH8&pp=ygUWMTYyLiBGaW5kIFBlYWsgRWxlbWVudA%3D%3D
 *
 * 852. Peak Index in a Mountain Array
 * Solved
 * Medium
 *
 * You are given an integer mountain array arr of length n where the values increase to a
 * peak element and then decrease.
 *
 * Return the index of the peak element.
 *
 * Your task is to solve it in O(log(n)) time complexity.
 *
 * Example 1:
 * Input: arr = [0,1,0]
 * Output: 1
 *
 * Example 2:
 * Input: arr = [0,2,1,0]
 * Output: 1
 *
 * Example 3:
 * Input: arr = [0,10,5,2]
 * Output: 1
 *
 * Constraints:
 *     3 <= arr.length <= 105
 *     0 <= arr[i] <= 106
 *     arr is guaranteed to be a mountain array.
 *
 */
public class _11_PeakIndexInMountainArray {

    /**
     * Brute Force : Iterate through each element and check for a peek element.
     * Time complexity : O(n)
     * Space complexity : O(1)
    * */
    public int findPeakElement(int[] nums) {
        int n = nums.length-1;
        if(n==0) return 0;
        if (nums[0]>nums[1]) return 0;
        if (nums[n-1]<nums[n]) return n;
        for (int i = 1; i < n; i++) {
            if (nums[i]>nums[i-1]&&nums[i]>nums[i+1]) return i;
        }
        return -1;
    }


    /**
     * Time & Space Complexity
     *
     * Time: O(log n) - Binary search halving the search space each iteration
     * Space: O(1) - Only uses constant extra space for variables
     *
     * Intuition
     * This finds a peak element (element greater than its neighbors) using binary search:
     *
     * - Key insight: If nums[mid] > nums[mid+1], there must be a peak on the left side (including mid),
     * because we're on a "downward slope"
     * - If nums[mid] < nums[mid+1], we're on an "upward slope", so there must be a peak on the right side
     * - The algorithm keeps moving toward the higher neighbor,
     * guaranteeing we'll eventually reach a peak (even at array boundaries,
     * since we can treat out-of-bounds as negative infinity)
     *
     * Why it works: By always moving toward the higher side, we're climbing uphill
     * and must eventually reach a local maximum.
    * */
    public int findPeakElement1(int[] nums) {
        int start=0,end= nums.length-1;
        while (start<end){
            int mid = (start+end)/2;
            if (nums[mid]>nums[mid+1]){
                end = mid;
            }else {
                start = mid+1;
            }
        }
        return end;
    }

    public static void main(String[] args) {

        _11_PeakIndexInMountainArray obj = new _11_PeakIndexInMountainArray();

        int[] input3 = {1,2,3,2,1};
        System.out.println("Out : "+ obj.findPeakElement(input3));
        System.out.println("Out : "+ obj.findPeakElement1(input3));

    }
}