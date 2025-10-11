package binary_search;

/**
 * Problem Link : https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * Video Explanation : https://www.youtube.com/watch?v=eneo_XPUEj0
 *
 * @see _7_FindMinInRotatedSortedArray this problem uses the techneque to find the min element in a sorted
 * array.
 *
 * @see _8_SearchInRotatedSortedArray very similar to this problem.
 *
 * 81. Search in Rotated Sorted Array II
 * Attempted
 * Medium
 * Topics
 *
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 *
 * Given the array nums after the rotation and an integer target, return true if target is in nums,
 * or false if it is not in nums.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 * Example 1:
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 *
 * Example 2:
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 *
 * Constraints:
 *     1 <= nums.length <= 5000
 *     -104 <= nums[i] <= 104
 *     nums is guaranteed to be rotated at some pivot.
 *     -104 <= target <= 104
 *
 * Follow up: This problem is similar to Search in Rotated Sorted Array,
 * but nums may contain duplicates. Would this affect the runtime complexity? How and why?
 */
public class _9_SearchInRotatedSortedArrayII {

    private int searchMinElement(int[] arr){
        int start=0,end=arr.length-1;
        while(start<end&&arr[start]==arr[start+1]) start++;
        while(start<end&&arr[end]==arr[end-1]) end--;
        while(start<end){
            int mid = (start+end)/2;
            if(arr[mid]>arr[end]){
                start = mid+1;
            }else{
                end = mid;
            }
        }
        return end;
    }

    private boolean binarySearch(int[] arr,int start,int end,int target){
        while(start<=end){
            int mid = (start+end)/2;
            if(arr[mid]>target){
                end = mid-1;
            }else if(arr[mid]<target){
                start = mid+1;
            }else{
                return true;
            }
        }
        return false;
    }

    /**
     * Worst Case: O(n) - When array has many duplicates (e.g., [1,1,1,1,1,1,1,0,1])
     * Average/Best Case: O(log n) - When duplicates are minimal or absent
     * Space: O(1) - No extra space used
     *
     * Why O(n) in worst case?
     *
     * initial while loops:
     * while(start<end&&arr[start]==arr[start+1]) start++;
     * while(start<end&&arr[end]==arr[end-1]) end--;
     *
     * These can iterate through nearly the entire array when duplicates dominate.
     * For example, with [2,2,2,2,2,2,2,0,2], you'd scan almost all
     * elements before finding the rotation point.
     *
    * */
    public boolean search(int[] nums, int target) {
        if(nums.length==0) return false;

        //1. search for min element index.
        int minEle = searchMinElement(nums);

        //2. run binary search 1st half of the array. 0 - (mid-1)
        boolean res = binarySearch(nums,0,minEle-1,target);

        //3.Found the target in 1st half only
        if(res) return true;

        //4. run binary search 2nd half of the array. mid - last
        return binarySearch(nums,minEle,nums.length-1,target);
    }

    public static void main(String[] args) {

        _9_SearchInRotatedSortedArrayII obj = new _9_SearchInRotatedSortedArrayII();

        int[] input = {2,5,6,0,0,1,2};
        int target = 3;
        System.out.println("Out : "+ obj.search(input, target));

        int[] input1 = {2,5,6,0,0,1,2};
        int target1 = 0;
        System.out.println("Out : "+ obj.search(input1, target1));

        int[] input2 = {1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
        int target2 = 2;
        System.out.println("Out : "+ obj.search(input2, target2));
    }
}