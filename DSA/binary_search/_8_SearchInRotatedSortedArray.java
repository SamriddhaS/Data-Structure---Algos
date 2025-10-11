package binary_search;

/**
 * Problem Link : https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 * Video Explanation : https://www.youtube.com/watch?v=U1VsdRgVevY
 *
 * @see _7_FindMinInRotatedSortedArray this problem uses the techneque to find the min element in a sorted
 * array.
 *
 * 33. Search in Rotated Sorted Array
 * Medium
 * Topics
 *
 * There is an integer array nums sorted in ascending order (with distinct values).
 * Prior to being passed to your function, nums is possibly left rotated at an unknown
 * index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
 * Given the array nums after the possible rotation and an integer target,
 * return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Example 3:
 * Input: nums = [1], target = 0
 * Output: -1
 *
 * Constraints:
 *     1 <= nums.length <= 5000
 *     -104 <= nums[i] <= 104
 *     All values of nums are unique.
 *     nums is an ascending array that is possibly rotated.
 *     -104 <= target <= 104
 */
public class _8_SearchInRotatedSortedArray {

    private int findMinElement(int[] nums){
        int start = 0,end = nums.length-1;
        while(start<end){
            int mid = (start+end)/2;
            if(nums[mid]>nums[end]){
                start = mid+1;
            }else {
                end = mid;
            }
        }
        return end;
    }

    private int binarySearch(int[] arr,int start,int end,int target){
        while (start<=end){
            int mid = (start+end)/2;
            if (arr[mid]>target){
                end=mid-1;
            } else if (arr[mid]<target) {
                start=mid+1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
    *
     * Time Complexity: O(log n)
     * findMinElement(): O(log n) - binary search to find pivot
     * binarySearch() (first call): O(log n) - searches first half
     * binarySearch() (second call): O(log n) - searches second half
     * Total: O(log n) + O(log n) + O(log n) = O(log n)
     *
     * Space Complexity: O(1)
     *
     * Only uses a constant number of variables (start, end, mid, index)
     * No recursion or additional data structures
    * */
    public int search(int[] nums, int target) {
        if (nums.length==0) return -1;
        int min = findMinElement(nums);

        //1. first binary search till the min-1 element.
        int index = binarySearch(nums,0,min-1,target);
        if (index!=-1&&nums[index]==target) return index; // target is found in the 1st half only

        //2. second binary search till the min - last element.
        return binarySearch(nums,min,nums.length-1,target);
    }

    public static void main(String[] args) {

        _8_SearchInRotatedSortedArray obj = new _8_SearchInRotatedSortedArray();

        int[] input = {4,5,6,7,0,1,2};
        int target = 6;
        System.out.println("Out : "+ obj.search(input, target));

        int[] input1 = {4,5,6,7,0,1,2};
        int target1 = 2;
        System.out.println("Out : "+ obj.search(input1, target1));

    }
}