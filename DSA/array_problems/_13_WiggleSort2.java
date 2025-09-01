package DSA.array_problems;


import java.util.Arrays;

/**
 * Problem Link : https://leetcode.com/problems/wiggle-sort-ii/description/
 * Video :
 * Topic : TArrays
 *
 * 324. Wiggle Sort II
 *
 * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * You may assume the input array always has a valid answer.
 *
 * Example 1:
 * Input: nums = [1,5,1,1,6,4]
 * Output: [1,6,1,5,1,4]
 * Explanation: [1,4,1,5,1,6] is also accepted.
 *
 * Example 2:
 * Input: nums = [1,3,2,2,3,1]
 * Output: [2,3,1,3,1,2]
 *
 * Constraints:
 *     1 <= nums.length <= 5 * 104
 *     0 <= nums[i] <= 5000
 *     It is guaranteed that there will be an answer for the given input nums.
 *
 *
 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 *
* */
class _13_WiggleSort2 {


    /**
     * Time Complexity: O(n log n)
     * Sorting: O(n log n) - dominates the complexity
     * Array operations: O(n) - copying and reconstruction
     *
     * Space Complexity: O(n)
     * Auxiliary arrays: O(n) - firstHalf and secondHalf together store all n elements
     * Other variables: O(1)
     *
     * Bottom line: Sorting dominates time, auxiliary arrays dominate space.
    * */
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int firstHalfLen = (n / 2)+(n%2);
        int secondHalfLen = (n / 2);

        // Declare new arrays
        int[] firstHalf = new int[firstHalfLen];
        int[] secondHalf = new int[secondHalfLen];

        // Copy elements using System.arraycopy
        System.arraycopy(nums, 0, firstHalf, 0, firstHalfLen);
        System.arraycopy(nums, firstHalfLen, secondHalf, 0, secondHalfLen);

//        System.out.println(Arrays.toString(firstHalf));
//        System.out.println(Arrays.toString(secondHalf));

        int index = 0 ;
        int i=firstHalf.length-1;
        int j=secondHalf.length-1;
        while (index< nums.length){
            if (index%2==0){
                nums[index] = firstHalf[i];
                i--;
            }else {
                nums[index] = secondHalf[j];
                j--;
            }
            index++;
        }

    }

    public static void main(String[] args) {

        _13_WiggleSort2 solution = new _13_WiggleSort2();

        int[] inputArr = new int[]{1,1,2,1,2,2,1};
        solution.wiggleSort(inputArr);
        System.out.println(Arrays.toString(inputArr));

    }
}
