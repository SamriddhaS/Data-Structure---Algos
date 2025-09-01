package DSA.array_problems;


import java.util.Arrays;

/**
 * Problem Link : https://leetcode.com/problems/wiggle-sort-ii/description/
 * Video :
 * Topic : TArrays
 *
 *
* */
class _13_WiggleSort2 {


    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int halfLength = n / 2;

        // Declare new arrays
        int[] firstHalf = new int[halfLength];
        int[] secondHalf = new int[halfLength];

        // Copy elements using System.arraycopy
        System.arraycopy(nums, 0, firstHalf, 0, halfLength);
        System.arraycopy(nums, halfLength, secondHalf, 0, halfLength);

        int index = 0 ;
        int i=0;
        int j=0;
        while (index< nums.length){
            if (index%2==0){
                nums[index] = firstHalf[i];
                i++;
            }else {
                nums[index] = secondHalf[j];
                j++;
            }
            index++;
        }

    }

    public static void main(String[] args) {

        _13_WiggleSort2 solution = new _13_WiggleSort2();

        int[] inputArr = new int[]{1,3,2,2,3,1};
        solution.wiggleSort(inputArr);
        System.out.println(Arrays.toString(inputArr));

    }
}
