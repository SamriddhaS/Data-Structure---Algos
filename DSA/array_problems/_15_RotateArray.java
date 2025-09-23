package array_problems;


/**
 * Problem Link : https://leetcode.com/problems/rotate-array/description/
 * Video : https://www.youtube.com/watch?v=BHr381Guz3Y
 * Topic : TArrays
 *
 * 189. Rotate Array
 * Medium
 *
 * Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 *
 *
 * Follow up:
 *
 * Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 *
* */
class _15_RotateArray {

    /**
     * - Time Complexity: O(n) - Single loop through the array
     * - Space Complexity: O(n) - Creates auxiliary array of same size
    * */
    public void rotate(int[] nums, int k) {
        int[] answer = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int shiftedIndex = (i+k)%nums.length;
            answer[shiftedIndex] = nums[i];
        }
        System.arraycopy(answer, 0, nums, 0, nums.length);
    }

    private void reversePositions(int[] nums,int left,int right){
        while(left<right){
            int temp = nums[right];
            nums[right] = nums[left];
            nums[left] = temp;
            right--;
            left++;
        }
    }

    /**
     * Time Complexity: O(n) - Three reverse operations, each O(n)
     * Space Complexity: O(1) - Only uses constant extra space for temp variables
    * */
    public void rotate1(int[] nums, int k) {
        k = k % nums.length;
        // Step 1 : reverse the entire array.
        reversePositions(nums,0,nums.length-1);
        // Now reverse the first part 0 - (k-1)
        reversePositions(nums,0,k-1);
        // Now reverse the second part k - end
        reversePositions(nums,k, nums.length-1);
    }

    public static void main(String[] args) {

        _15_RotateArray solution = new _15_RotateArray();

        int[] nums = new int[]{1,2,3,4,5,6,7};
        solution.rotate(nums,3);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(" "+nums[i]);
        }

        int[] nums2 = new int[]{1,2,3,4,5,6,7};
        solution.rotate1(nums2,3);
        for (int i = 0; i < nums2.length; i++) {
            System.out.print(" "+nums2[i]);
        }

    }
}
