package array_problems;


/**
 * Problem Link : https://leetcode.com/problems/move-zeroes/description/
 * Video : https://www.youtube.com/watch?v=aayNRwUN3Do
 * Topic : TArrays
 *
 * 283. Move Zeroes
 *
 * Hint
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * Note that you must do this in-place without making a copy of the array.
 *
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * Follow up: Could you minimize the total number of operations done?
 *
* */
class _16_MoveZeroes {

    /**
     * Initial solution.
     *
     * Time Complexity: O(n)
     * Two separate loops, each iterating through the array once: O(n) + O(n) = O(n)
     *
     * Space Complexity: O(n)
     * Creates an additional array answer of size n to store the result
    * */
    public void moveZeroes(int[] nums) {
        int[] answer = new int[nums.length];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]!=0){
                answer[index] = nums[i];
                index++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = answer[i];
        }
    }

    /**
     * - Time Complexity: O(n) - single pass through the array
     * - Space Complexity: O(1) - only uses a few variables
    * */
    public void moveZeroes1(int[] nums) {
        int zeroStartIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==0&&zeroStartIndex==-1) zeroStartIndex = i;
            if (nums[i]!=0&&zeroStartIndex!=-1){
                nums[zeroStartIndex] = nums[i];
                nums[i] = 0;
                zeroStartIndex++;
            }
        }
    }

    public static void main(String[] args) {

        _16_MoveZeroes solution = new _16_MoveZeroes();

        int[] nums = new int[]{1,0,3,0,5,6,7};
        solution.moveZeroes1(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(" "+nums[i]);
        }

        System.out.println(" ");
        int[] nums2 = new int[]{1,12,0,0,3,0,5,6,7,0,0,9};
        solution.moveZeroes1(nums2);
        for (int i = 0; i < nums2.length; i++) {
            System.out.print(" "+nums2[i]);
        }

    }
}
