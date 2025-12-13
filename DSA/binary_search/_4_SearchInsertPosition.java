package binary_search;

/**
 * Problem Link : https://leetcode.com/problems/search-insert-position/description/
 * Video Explanation : https://www.youtube.com/watch?v=K-RYzDZkzCI
 *
 * @see _3_UpperBoundUsingBinarySearch this probelm use's same techneque used in this problem
 * to find out the index if element is not present.
 *
 * 35. Search Insert Position
 * Solved
 * Easy
 * Topics
 *
 * Given a sorted array of distinct integers and a target value,
 * return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 *
 * Example 3:
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 *
 * Constraints:
 *     1 <= nums.length <= 104
 *     -104 <= nums[i] <= 104
 *     nums contains distinct values sorted in ascending order.
 *     -104 <= target <= 104
 *
 */
public class _4_SearchInsertPosition {


    /**
     * Time Complexity: O(log N)
     * Binary search divides the search space in half with each iteration
     * Maximum iterations = log₂(N)
     *
     * Space Complexity: O(1)
     * Only uses a constant amount of extra variables (start, end, mid, ans)
     * No recursion or additional data structures
    * */
    public int searchInsert(int[] nums, int target) {
        int start=0,end=nums.length-1;
        int answer=nums.length;
        while(start<=end){
            int mid = (start+end)/2;
            if(nums[mid]<target){
                start=mid+1;
            }else if(nums[mid]>target){
                end=mid-1;
                answer=mid;
            }else{
                return mid;
            }
        }
        return answer;
    }

    /*
    * Revisited - 12th December
    * */
    /*public int searchInsert(int[] nums, int target) {
        int start=0;
        int end = nums.length-1;
        int mid=(start+end)/2;
        if(target<nums[start]) return 0;
        if(target>nums[end]) return end+1;
        int answer=0;
        while(start<=end){
            mid = (start+end)/2;
            if(nums[mid]<target){
                start = mid+1;
            }else if(nums[mid]>target){
                end = mid-1;
                answer=mid;
            }else{
                return mid;
            }
        }
        return answer;
    }*/

    public static void main(String[] args) {

        _4_SearchInsertPosition obj = new _4_SearchInsertPosition();

        int[] input = {3,5,8,9,15,19};
        int target = 9;
        System.out.println("Out : "+obj.searchInsert(input,target));

        int[] input1 = {1, 2, 2, 2, 5, 7};
        int target1 = 2;
        System.out.println("Out : "+obj.searchInsert(input1,target1));

        int[] input2 = {1,3,5,6,6,6,8,9};
        int target2 = 6;
        System.out.println("Out : "+obj.searchInsert(input2,target2));

        int[] input3 = {1,2,2,3};
        int target3 = 2;
        System.out.println("Out : "+obj.searchInsert(input3,target3));

    }
}