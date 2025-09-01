package DSA.array_problems;


import java.util.Arrays;

/**
 * Problem Link : https://leetcode.com/problems/sort-colors/description/
 * Video : https://www.youtube.com/watch?v=tp8JIuCXBaU&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=21
 * Topic : TDutchNationalFlagAlgo TArrays
 *
 * 75. Sort Colors
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 *
 * Example 1:
 *
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Example 2:
 *
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 *
 * Constraints:
 *     n == nums.length
 *     1 <= n <= 300
 *     nums[i] is either 0, 1, or 2.
 *
 * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 *
* */
class _13_SortColors {

    /**
     *
     * ### Brute Force Answer ####
     * Time Complexity:
     * The time complexity is O(n), where 'n' is the number of elements in the nums array.
     * This is because the algorithm consists of two passes through the array:
     *
     * The total time is O(n) + O(n), which simplifies to O(n).
     *
     * Space Complexity :
     * The space complexity is O(1).
     *
    * */
    public void sortColors(int[] nums) {
        int length = nums.length;
        int numOfZero = 0;
        int numOfOne = 0;
        int numOfTwo = 0;

        for (int i = 0; i < length; i++) {
            if (nums[i] == 0) {
                numOfZero++;
            } else if (nums[i] == 1) {
                numOfOne++;
            } else if (nums[i] == 2) {
                numOfTwo++;
            }
        }

        for (int i = 0; i < length; i++) {
            if (numOfZero > 0) {
                nums[i] = 0;
                numOfZero--;
            } else if (numOfOne > 0) {
                nums[i] = 1;
                numOfOne--;
            } else if (numOfTwo > 0) {
                nums[i] = 2;
                numOfTwo--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * ##### Doing it in one pass : Dutch National Flag Algo #######
     * Time Complexity: O(n)
     * - Single pass through the array with three pointers
     * - Each element is examined at most once by the whiteBoundry pointer
     * - Constant work per iteration
     *
     * Space Complexity: O(1)
     * - Only using a few extra variables (pointers and temp)
     * - Sorting in-place without additional data structures
    * */
    public void sortColorsUsingDutchNationalFlag(int[] nums){
        int red = 0;
        int white = 1;
        int blue = 2;
        int redPointer=0,whitePointer=0;
        int bluePointer = nums.length-1;
        while(whitePointer<=bluePointer){
            if (nums[whitePointer]==red){
                //its a red so move it in the red zone. All the elements <redPointer are red.
                swap(nums,redPointer,whitePointer);
                whitePointer++;
                redPointer++;
            }else if (nums[whitePointer]==blue){
                //its a blue so move it in the blue zone. All the elements >= bluePointer are blue.
                swap(nums,bluePointer,whitePointer);
                bluePointer--;
            }else {
                //its a white no need swap anything as white needs to be in the middle so just increment white++
                whitePointer++;
            }
        }

    }


    public static void main(String[] args) {

        _13_SortColors solution = new _13_SortColors();

        int[] inputArr = new int[]{2,0,2,1,1,0};
        solution.sortColorsUsingDutchNationalFlag(inputArr);
        System.out.println(Arrays.toString(inputArr));


    }
}
