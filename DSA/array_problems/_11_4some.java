package array_problems;

import java.util.*;

/**
*
 * 18. 4Sum
 * Problem Link : https://leetcode.com/problems/4sum/
 * Video Explanation : https://www.youtube.com/watch?v=eD95WRfh81c
 *
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 *
 *     0 <= a, b, c, d < n
 *     a, b, c, and d are distinct.
 *     nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 *
 * Constraints:
 *
 *     1 <= nums.length <= 200
 *     -109 <= nums[i] <= 109
 *     -109 <= target <= 109
 *
 *
* */
class MyClass {

    /**
    * Time Complexity - O(n^4)
    * */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int size = nums.length;
        Set<List<Integer>> answer = new HashSet();
        for(int i=0;i<size;i++){
            for(int j=i+1;j<size;j++){
                for(int k=j+1;k<size;k++){
                    for(int l=k+1;l<size;l++){
                        long currentSum = (long)nums[i] + nums[j] + nums[k]+nums[l];
                        if(currentSum==target){
                            List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            Collections.sort(temp);
                            answer.add(temp);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(answer);
    }


    /**
     *
     * TC -> O(n3 * log(n))
     * SC -> O(n) [For the hasset] + O(2 * no. of the quadruplets)
    * */
    public static List<List<Integer>> fourSumWithHashing(int[] nums, int target) {
        int size = nums.length;
        Set<List<Integer>> answer = new HashSet();
        for(int i=0;i<size;i++){
            for(int j=i+1;j<size;j++){
                HashSet<Integer> myMap = new HashSet<>();
                for(int k=j+1;k<size;k++){
                    long currentSum = (long)nums[i] + nums[j] + nums[k];
                    long neededNumLong = (long)target - currentSum;
                    if (neededNumLong>=Integer.MIN_VALUE&&neededNumLong<=Integer.MAX_VALUE){
                        if (myMap.contains((int)neededNumLong)){
                            List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], (int)neededNumLong);
                            Collections.sort(temp);
                            answer.add(temp);
                        }
                    }
                    myMap.add(nums[k]);
                }
            }
        }
        return new ArrayList<>(answer);
    }

    /**
     * TC -> O(n3)
     * SC -> O(2 * no. of the quadruplets) - Only for returning the answer O(1) for the actual algo.
    * */
    public static List<List<Integer>> fourSumWithTwoPointersApproach(int[] nums, int target) {
        ArrayList<ArrayList<Integer>> answers = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for(int i=0;i< n;i++){
            if (i>0 && nums[i]==nums[i-1]) continue;
            for(int j=i+1;j<n;j++){
                if (j>i+1 && nums[j]==nums[j-1]) continue;
                int k = j+1;
                int l = nums.length-1;
                while(k<l){
                    long sum = (long) nums[i]+nums[j]+nums[k]+nums[l];
                    if (sum>target){
                        l--;
                    } else if(sum<target){
                        k++;
                    }else {
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        temp.add(nums[l]);
                        answers.add(temp);
                        k++;
                        l--;
                        while(k<l && nums[k]==nums[k-1]) k++;
                        while(k<l && nums[l]==nums[l+1]) l--;
                    }
                }
            }
        }
        return new ArrayList<>(answers);
    }

    public static void main(String[] args) {
        int[] nums = { 1,0,2,3,1,5,6,2,1,0,0,1,9,0};
        int target = 9;

        // Time the fourSum function
        System.out.println("Executing fourSum...");
        long startTime1 = System.nanoTime();
        List<List<Integer>> ans1 = fourSum(nums, target);
        long endTime1 = System.nanoTime();
        long duration1 = (endTime1 - startTime1);
        double durationMs1 = (double) duration1 / 1_000_000;
        System.out.println("Time taken by fourSum: " + durationMs1 + " ms");
        System.out.println("The quadruplets from fourSum are: " + ans1);

        System.out.println("\n-----------------------------------\n");

        // Time the fourSumWithHashing function
        System.out.println("Executing fourSumWithHashing...");
        long startTime2 = System.nanoTime();
        List<List<Integer>> ans2 = fourSumWithHashing(nums, target);
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2);
        double durationMs2 = (double) duration2 / 1_000_000;
        System.out.println("Time taken by fourSumWithHashing: " + durationMs2 + " ms");
        System.out.println("The quadruplets from fourSumWithHashing are: " + ans2);

        System.out.println("\n-----------------------------------\n");

        System.out.println("Executing fourSumWithHashing...");
        long startTime3 = System.nanoTime();
        List<List<Integer>> ans3 = fourSumWithTwoPointersApproach(nums, target);
        long endTime3 = System.nanoTime();
        long duration3 = (endTime3 - startTime3);
        double durationMs3 = (double) duration3 / 1_000_000;
        System.out.println("Time taken by fourSumWithTwoPointers: " + durationMs3 + " ms");
        System.out.println("The quadruplets from fourSumWithTwoPointers are: " + ans3);
    }
}