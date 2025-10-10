package binary_search;

import java.util.Arrays;

/**
 * Problem Link : https://www.naukri.com/code360/problems/ceiling-in-a-sorted-array_1825401
 * Video Explanation : https://takeuforward.org/arrays/floor-and-ceil-in-sorted-array/
 * @see _2_LowerBoundUsingBinarySearch
 * @see _3_UpperBoundUsingBinarySearch see these two problems to understand how to find upper & lower
 * bounds in a sorted array.
 *
 * Problem statement :  Ceil The Floor
 * You're given a sorted array 'a' of 'n' integers and an integer 'x'.
 * Find the floor and ceiling of 'x' in 'a[0..n-1]'.
 *
 * Note:
 * Floor of 'x' is the largest element in the array which is smaller than or equal to 'x'.
 * Ceiling of 'x' is the smallest element in the array greater than or equal to 'x'.
 *
 * Example:
 *
 * Input:
 * n=6, x=5, a=[3, 4, 7, 8, 8, 10]
 *
 * Output:
 * 4
 *
 * Explanation:
 * The floor and ceiling of 'x' = 5 are 4 and 7, respectively.
 *
 *
 *
 * Sample Input 1 :
 * 6 8
 * 3 4 4 7 8 10
 *
 * Sample Output 1 :
 * 8 8
 *
 * Explanation of sample input 1 :
 * Since x = 8 is present in the array, it will be both floor and ceiling.
 *
 *
 * Sample Input 2 :
 * 6 2
 * 3 4 4 7 8 10
 *
 * Sample Output 2 :
 * -1 3
 *
 * Explanation of sample input 2 :
 * Since no number is less than or equal to x = 2 in the array, its floor does not exist. The ceiling will be 3.
 *
 * Constraints :
 * 1 <= n <= 2 * 10^5
 * 1 <= a[i] <= 10^9
 * Time limit: 1 sec
 *
 */
public class _5_FloorAndCeilInSortedArray {


    /**
     * Time Complexity: O(log n) - Binary search
     * Space Complexity: O(1) - Only uses a constant amount of extra space (variables: floor, ceil, start, end, mid)
     *
    * */
    public int[] getFloorAndCeil(int[] a, int n, int x) {
        int floor=-1, ceil=-1;
        int start=0,end=n-1;
        while(start<=end){
            int mid = (start+end)/2;
            if (x<a[mid]){
                end = mid-1;
                ceil=a[mid];
            }else if(x>a[mid]){
                start=mid+1;
                floor=a[mid];
            }else {
                return new int[]{a[mid],a[mid]};
            }
        }
        return new int[]{floor,ceil};
    }

    public static void main(String[] args) {

        _5_FloorAndCeilInSortedArray obj = new _5_FloorAndCeilInSortedArray();

        int[] input = {3, 4, 4, 7, 8, 10};
        int target = 2;
        System.out.println("Out : "+ Arrays.toString(obj.getFloorAndCeil(input, input.length, target)));

    }
}