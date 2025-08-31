package DSA.array_problems;


/**
 *
 * Question link : https://www.naukri.com/code360/problems/count-inversions_615?leftPanelTabValue=PROBLEM
 * Video Explenation : https://www.youtube.com/watch?v=AseUmwVNaoY&t=358s
 *
 * Count Inversions
 *
 * For a given integer array/list 'ARR' of size 'N' containing all distinct values, find the total number of 'Inversions' that may exist.
 * An inversion is defined for a pair of integers in the array/list when the following two conditions are met.
 * A pair ('ARR[i]', 'ARR[j]') is said to be an inversion when:
 *
 * 1. 'ARR[i] > 'ARR[j]'
 * 2. 'i' < 'j'
 *
 * Where 'i' and 'j' denote the indices ranging from (0, 'N').
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= N <= 10^5
 * 1 <= ARR[i] <= 10^9
 *
 * Where 'ARR[i]' denotes the array element at 'ith' index.
 *
 * Time Limit: 1 sec
 *
 *
 * Sample Input 1 :
 *
 * 3
 * 3 2 1
 *
 * Sample Output 1 :
 *
 * 3
 *
 * Explanation of Sample Output 1:
 *
 * We have a total of 3 pairs which satisfy the condition of inversion. (3, 2), (2, 1) and (3, 1).
 *
 * Sample Input 2 :
 *
 * 5
 * 2 5 1 3 4
 *
 * Sample Output 2 :
 *
 * 4
 *
 * Explanation of Sample Output 1:
 *
 * We have a total of 4 pairs which satisfy the condition of inversion. (2, 1), (5, 1), (5, 3) and (5, 4).
 *
 *
 * Hints:
 *
 * 1. Start with the brute force approach.
 * 2. Use a modified merge sort.
 * 3. Iterate through the elements in sorted order and use a Fenwick tree to track the inversions.
 *
 * ############ Brude force solution #######################################
 *
 * Initial solution that I came up with is to run two loops and check every possible inversion exist there in the array.
 * Time complexity --> O(n^2)
 *
 * Code :
 * #include <bits/stdc++.h>
 * long long getInversions(long long *arr, int n){
 *     int numOfInversions = 0;
 *     for (int i = 0; i < n - 1; i++) {
 *         int firstElement = arr[i];
 *         for (int j = i+1; j < n ; j++) {
 *             int secondElement = arr[j];
 *             if(firstElement>secondElement) numOfInversions++;
 *         }
 *     }
 *     return numOfInversions;
 * }
 *
* */
public class _5_CountInversions {

    private int countInversions(int[] nums,int start,int end,int mid){
        int answer=0;
        int leftPointer = start;
        int rightPointer = mid+1;
        int[] sorted = new int[end-start+1];
        int index=0;
        while(leftPointer<=mid&&rightPointer<=end){
            if (nums[leftPointer]>nums[rightPointer]){
                sorted[index] = nums[rightPointer];
                rightPointer++;
                answer+=(mid-leftPointer+1);
            }else {
                sorted[index] = nums[leftPointer];
                leftPointer++;
            }
            index++;
        }

        while (leftPointer<=mid){
            sorted[index] = nums[leftPointer];
            leftPointer++;
            index++;
        }

        while (rightPointer<=end){
            sorted[index] = nums[rightPointer];
            rightPointer++;
            index++;
        }

        index=0;
        while(start<=end){
            nums[start] = sorted[index];
            index++;
            start++;
        }

        return answer;
    }

    /**
     * Better Solution using merge sort
    * */
    private int countInversionUsingMergeSort(int[] nums,int start,int end){
        int answer=0;
        //base case
        if (start==end) return answer;

        //divide
        int mid = (start+end)/2;
        answer=countInversionUsingMergeSort(nums,start,mid);
        answer+=countInversionUsingMergeSort(nums,mid+1,end);

        answer+=countInversions(nums,start,end,mid);

        return answer;

    }

    public int countInversion(int[] nums) {
        return countInversionUsingMergeSort(nums,0, nums.length-1);
    }

    public static void main(String[] args) {

        _5_CountInversions solution = new _5_CountInversions();

        int[] inputArr = new int[]{5,3,2,1,4};
        int result = solution.countInversion(inputArr);
        System.out.println(result);

        int[] inputArr1 = new int[]{5,4,3,2,1};
        int result1 = solution.countInversion(inputArr1);
        System.out.println(result1);

    }
}
