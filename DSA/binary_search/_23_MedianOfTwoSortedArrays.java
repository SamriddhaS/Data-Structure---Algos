package binary_search;

import java.util.Arrays;

/**
 * Problem Link : https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * Video Explanation : https://www.youtube.com/watch?v=6D9T2ZY8h5c
 * https://www.youtube.com/watch?v=7nABqJCEMuY
 *
 * 4. Median of Two Sorted Arrays
 * Solved
 * Hard
 *
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 *
 * Example 2:
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 * Constraints:
 *     nums1.length == m
 *     nums2.length == n
 *     0 <= m <= 1000
 *     0 <= n <= 1000
 *     1 <= m + n <= 2000
 *     -106 <= nums1[i], nums2[i] <= 106
 *
 */
public class _23_MedianOfTwoSortedArrays {


    /**
     * Solution 1 : Brute Force
     *
     * Time Complexity: O(m + n)
     * - Merges both arrays by iterating through each element exactly once
     * - Where m = length of nums1, n = length of nums2
     *
     * Space Complexity: O(m + n)
     * - Creates a new merged array of size (m + n)
     * - Uses constant extra space for loop variables (i, j, k)
    * */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] mergedArray = new int[nums1.length+nums2.length];
        int i=0,j=0,k=0;
        while(i<nums1.length&&j<nums2.length){
            if (nums1[i]<nums2[j]){
                mergedArray[k] = nums1[i];
                i++;
            }else {
                mergedArray[k] = nums2[j];
                j++;
            }
            k++;
        }

        // copy remaining elements if any
        while(i<nums1.length){
            mergedArray[k] = nums1[i];
            i++;
            k++;
        }

        // copy remaining elements if any
        while(j<nums2.length){
            mergedArray[k] = nums2[j];
            j++;
            k++;
        }

        int medianIndex =  mergedArray.length/2;
        if (mergedArray.length%2==1){
            return mergedArray[medianIndex];
        }else {
            return (double) (mergedArray[medianIndex] + mergedArray[medianIndex - 1]) /2;
        }
    }

    /**
     * Solution 2 : We get rid of the mergedArray by storing the median & median-1 values inside variables.
     * This helps us to get rid of the extra space.
     *
     * Time Complexity: O(m + n)
     *
     * Space Complexity: O(1)
     * Only uses a constant number of variables (i, j, k, median1, median2, n, medianIndex)
     * No auxiliary array created
    * */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int i=0,j=0,k=0;
        int median1=-1,median2=-1;
        int n = (nums1.length + nums2.length);
        int medianIndex = n/2;

        while(i<nums1.length&&j<nums2.length){
            if (nums1[i]<nums2[j]){
                if (k==medianIndex-1) median1 = nums1[i];
                if (k==medianIndex) median2 = nums1[i];
                i++;
            } else {
                if (k==medianIndex-1) median1 = nums2[j];
                if (k==medianIndex) median2 = nums2[j];
                j++;
            }
            k++;
        }

        while(k<=medianIndex&&i<nums1.length){
            if (k==medianIndex-1) median1 = nums1[i];
            if (k==medianIndex) median2 = nums1[i];
            i++;
            k++;
        }

        while(k<=medianIndex&&j<nums2.length){
            if (k==medianIndex-1) median1 = nums2[j];
            if (k==medianIndex) median2 = nums2[j];
            j++;
            k++;
        }

        if (n%2==1){
            return median2;
        }else {
            return (double) (median1 + median2) /2;
        }
    }

    /**
     * Solution 3 : Optimal solution
     * Time Complexity: O(log(min(m, n)))
     * - Performs binary search on the smaller array
     * - Each iteration halves the search space
     * - Where m = length of nums1, n = length of nums2
     *
     * Space Complexity: O(1)
     * - Uses only constant extra space for variables (low, high, Px, Py, l1, l2, r1, r2, etc.)
     * - Recursive call adds O(1) stack space (only swaps arrays once if needed)
    * */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m>n){
            return findMedianSortedArrays2(nums2,nums1);
        }

        int low=0;
        int high=m;
        int partitionSize = (m+n+1)/2; // divide the array in this size
        while(low<=high){
            int Px = (low+high)/2; // Left half - This many items will be picked from num1
            int Py = partitionSize-Px; // Left half - Remaining items will be picked from num2

            // left half - smaller half
            int l1 = (Px==0) ? Integer.MIN_VALUE : nums1[Px-1]; // a2 // x1
            int l2 = (Py==0) ? Integer.MIN_VALUE : nums2[Py-1]; // b1 // x2

            // right half - larger half
            int r1 = (Px==m) ? Integer.MAX_VALUE : nums1[Px]; // a3 // x3
            int r2 = (Py==n) ? Integer.MAX_VALUE : nums2[Py]; // b2 // x4

            if(l1<=r2 && l2<=r1){
                // got the correct partitions - return result.
                if ((m+n)%2==1) return Math.max(l1,l2); // For odd size only 1 element is the median.
                return (Math.max(l2, l1) + Math.min(r1, r2))/2.0; // For even size avarage of middle 2 elements are median
            }

            if (l1>r2){
                // need to pick fewer element from num1.
                high = Px-1;
            }else {
                low = Px+1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        _23_MedianOfTwoSortedArrays obj = new _23_MedianOfTwoSortedArrays();

        int[] nums = {1,3};
        int[] nums1 = {2};
        System.out.println("Median " +obj.findMedianSortedArrays(nums, nums1));
        System.out.println("Median " +obj.findMedianSortedArrays1(nums, nums1));
        System.out.println("Median " +obj.findMedianSortedArrays2(nums, nums1));

        int[] nums2 = {1,2};
        int[] nums3 = {3,4};
        System.out.println("Median " +obj.findMedianSortedArrays(nums2, nums3));
        System.out.println("Median " +obj.findMedianSortedArrays1(nums2, nums3));
        System.out.println("Median " +obj.findMedianSortedArrays2(nums2, nums3));

    }
}