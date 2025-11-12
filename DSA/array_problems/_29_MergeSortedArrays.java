package array_problems;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Problem Link : https://leetcode.com/problems/merge-sorted-array/description/
 * Video : https://www.youtube.com/watch?v=P1Ic85RarKY
 * Topic : TArrays
 *
 * 88. Merge Sorted Array
 * Solved
 * Easy
 * Topics
 * premium lock iconCompanies
 * Hint
 *
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, a
 * nd two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
 * and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 *
 * Example 1:
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 *
 * Example 2:
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 * Explanation: The arrays we are merging are [1] and [].
 * The result of the merge is [1].
 *
 * Example 3:
 * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 * Output: [1]
 * Explanation: The arrays we are merging are [] and [1].
 * The result of the merge is [1].
 * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
 *
 * Constraints:
 *     nums1.length == m + n
 *     nums2.length == n
 *     0 <= m, n <= 200
 *     1 <= m + n <= 200
 *     -109 <= nums1[i], nums2[j] <= 109
 *
 * Follow up: Can you come up with an algorithm that runs in O(m + n) time?
* */
class _29_MergeSortedArrays {

    /**
     * Time Complexity: O(m + n)
     * Main merge loop processes each element from both arrays once
     * Two cleanup loops handle remaining elements
     * Final copy loop: O(m + n)
     * Overall: O(m + n)
     *
     * Space Complexity: O(m + n)
     * Creates auxiliary array answer of size nums1.length (which is m + n)
     * All other variables use constant space
     * Overall: O(m + n)
     *
    * */
    public int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int[] answer = new int[nums1.length];
        int i=0;
        int a=0;
        int b=0;
        if(n==0) return nums1;
        while(a<m&&b<n){
            if (nums1[a]<nums2[b]){
                answer[i] = nums1[a];
                a++;
            } else if (nums1[a] >= nums2[b]) {
                answer[i] = nums2[b];
                b++;
            }
            i++;
        }

        while(a<m){
            answer[i]=nums1[a];
            a++;
            i++;
        }

        while(b<n){
            answer[i]=nums2[b];
            b++;
            i++;
        }

        // Copy answer back into nums1
        for(int j = 0; j < answer.length; j++) {
            nums1[j] = answer[j];
        }
        return answer;
    }

    /**
     * Solution 2 : optimal
     *
     * Time Complexity: O(m + n)
     *
     * Main merge loop processes elements from both arrays once
     * Cleanup loop handles remaining elements from nums2
     * Overall: O(m + n)
     *
     * Space Complexity: O(1)
     *
     * Merges in-place directly into nums1
     * Only uses a few pointer variables (constant space)
     * Overall: O(1)
    * */
    public int[] merge1(int[] nums1, int m, int[] nums2, int n) {
        int a=m-1;
        int b=n-1;
        int i = nums1.length-1;
        while(a>=0&&b>=0){
            if (nums1[a]>nums2[b]){
                nums1[i] = nums1[a];
                a--;
            } else if (nums2[b]>=nums1[a]) {
                nums1[i] = nums2[b];
                b--;
            }
            i--;
        }

        while(b>=0){
            nums1[i] = nums2[b];
            b--;
            i--;
        }

        return nums1;
    }

    public static void main(String[] args) {

        _29_MergeSortedArrays solution = new _29_MergeSortedArrays();

        int[] input = {1,2,3,0,0,0};
        int[] input1 = {2,5,6};
        //System.out.println("Answer : "+ Arrays.toString(solution.merge(input, 3, input1, 3)));
        System.out.println("Answer : "+ Arrays.toString(solution.merge1(input, 3, input1, 3)));

        int[] input2 = {0};
        int[] input3 = {1};
        //System.out.println("Answer : "+ Arrays.toString(solution.merge(input2, 0, input3, 1)));
        System.out.println("Answer : "+ Arrays.toString(solution.merge1(input2, 0, input3, 1)));

    }
}
