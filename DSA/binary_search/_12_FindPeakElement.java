package binary_search;

/**
 * Problem Link : https://leetcode.com/problems/find-peak-element/description/
 * Video Explanation :
 * video 1 : https://www.youtube.com/watch?v=Op07kT-LoH8&pp=ygUWMTYyLiBGaW5kIFBlYWsgRWxlbWVudA%3D%3D
 * video 2 : https://www.youtube.com/watch?v=kMzJy9es7Hc
 *
 * 162. Find Peak Element
 * Solved
 * Medium
 *
 * A peak element is an element that is strictly greater than its neighbors.
 * Given a 0-indexed integer array nums, find a peak element, and return its index.
 * If the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 *
 * Example 2:
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 *
 * Constraints:
 *     1 <= nums.length <= 1000
 *     -231 <= nums[i] <= 231 - 1
 *     nums[i] != nums[i + 1] for all valid i.
 *
 */
public class _12_FindPeakElement {

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
     * @see _11_PeakIndexInMountainArray
     * Essentially we are using same intuition like the above linked probelem to find one peek(we have multiple peeks).
     * Even if we have multiple peeks we just see whatever slop our mid is at and try to climb to the top of
     * that particular slop.
     *
     * Time: O(log n) - Binary search halving the search space each iteration
     * Space: O(1) - Only uses constant extra space for variables
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

        _12_FindPeakElement obj = new _12_FindPeakElement();

        int[] input = {1,2,5,3,2,6,4,1};
        System.out.println("Out : "+ obj.findPeakElement(input));
        System.out.println("Out : "+ obj.findPeakElement1(input));

        int[] input1 = {1,2,3,1};
        System.out.println("Out : "+ obj.findPeakElement(input1));
        System.out.println("Out : "+ obj.findPeakElement1(input1));

        int[] input2 = {2,1};
        System.out.println("Out : "+ obj.findPeakElement(input2));
        System.out.println("Out : "+ obj.findPeakElement1(input2));

        int[] input3 = {3,2,1};
        System.out.println("Out : "+ obj.findPeakElement(input3));
        System.out.println("Out : "+ obj.findPeakElement1(input3));
    }
}