package binary_search;

/**
 * Problem Link : https://leetcode.com/problems/binary-search/description/
 * Video Explanation :
 *
 * 704. Binary Search
 * Solved
 * Easy
 *
 * Given an array of integers nums which is sorted in ascending order,
 * and an integer target, write a function to search target in nums.
 * If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 *
 * Example 2:
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 *
 * Constraints:
 *     1 <= nums.length <= 104
 *     -104 < nums[i], target < 104
 *     All the integers in nums are unique.
 *     nums is sorted in ascending order.
 *
 */
public class _1_BinarySearch {

    /**
     *
     * Time Complexity: O(log n)
     * - The search space is halved in each iteration
     * - For an array of size n, it takes at most log₂(n) iterations to find the target or determine it doesn't exist
     *
     * Space Complexity: O(1)
     * - Only uses a constant amount of extra space (three integer variables: start, end, mid)
     * - No recursion or additional data structures
    * */
    public int search(int[] nums, int target) {
        int start=0,end = nums.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(nums[mid]>target){
                end = mid-1;
            }else if(nums[mid]<target){
                start = mid+1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        _1_BinarySearch obj = new _1_BinarySearch();

        int[] input = {-1,0,3,5,9,12};
        int target = 2;
        System.out.println("Out : "+obj.search(input,target));
    }
}