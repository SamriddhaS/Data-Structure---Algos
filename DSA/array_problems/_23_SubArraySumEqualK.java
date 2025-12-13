package array_problems;

import java.util.HashMap;

/**
 * Problem Link : https://leetcode.com/problems/subarray-sum-equals-k/
 * Video : https://www.youtube.com/watch?v=frf7qxiN2qU
 * Topic : TArrays,TPrefixSum
 * @see _22_LongestSubArrayWithSumK this is essintaialy the same problem as this one.
 *
 * 560. Subarray Sum Equals K
 * Attempted
 * Medium
 *
 * Given an array of integers nums and an integer k, return the total number of
 * subarrays whose sum equals to k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 * Constraints:
 *     1 <= nums.length <= 2 * 104
 *     -1000 <= nums[i] <= 1000
 *     -107 <= k <= 107
 *
* */
class _23_SubArraySumEqualK {

    /**
     * Solution 1 : Brute Force.
     * Time Complexity : O(n^2)
     * Space Complexity : O(1)
    * */
    public int subarraySum(int[] nums, int k) {
        int answer=0;
        for (int i = 0; i < nums.length; i++) {
            int sum=0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum==k){
                    answer++;
                }
            }
        }
        return answer;
    }

    /**
     * Solution 2 : Better solution using hashing and prefix sum.
     *
     * Time Complexity: O(n)
     * - Single pass through the array (n elements)
     * - HashMap operations (put, get, containsKey) are O(1) average case
     * - Overall: O(n)
     *
     * Space Complexity: O(n)
     * - HashMap stores prefix sums with their indices
     * - Worst case: all prefix sums are unique, storing n entries
     * - Overall: O(n)
     *
    * */
    public int subarraySum1(int[] nums, int k) {
        int answer=0;
        long sum= 0L;
        HashMap<Long,Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
            if (sum==k){
                answer++;
            }
            long diff = sum-k;
            if (map.containsKey(diff)){
                answer+= map.get(diff);
            }

            if (!map.containsKey(sum)){
                map.put(sum,1);
            } else {
                int curr = map.get(sum);
                map.put(sum,++curr);
            }
        }
        return answer;
    }

    /**
    * Revisited on 13th December 2025
    * */
    /*public int subarraySum(int[] nums, int k) {
        int count=0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            int req = sum-k;
            if(map.containsKey(req)){
                count+=map.get(req);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return count;
    }*/

    public static void main(String[] args) {

        _23_SubArraySumEqualK solution = new _23_SubArraySumEqualK();

        int[] input = {1,1,1};
        int k=3;
        System.out.println("Answer : "+ solution.subarraySum(input,k));
        System.out.println("Answer : "+ solution.subarraySum1(input,k));


        int[] input1 = {1,2,3};
        int k1=2;
        System.out.println("Answer : "+ solution.subarraySum(input1,k1));
        System.out.println("Answer : "+ solution.subarraySum1(input1,k1));


        int[] input2 = {1,2,1,2,1};
        int k2=3;
        System.out.println("Answer : "+ solution.subarraySum(input2,k2));
        System.out.println("Answer : "+ solution.subarraySum1(input2,k2));

        int[] input3 = {1,-2,1,5,3,-7};
        int k3=2;
        System.out.println("Answer : "+ solution.subarraySum(input3,k3));
        System.out.println("Answer : "+ solution.subarraySum1(input3,k3));

        int[] input4 = {0,0,0,0,0,0,0,0,0,0};
        int k4=0;
        System.out.println("Answer : "+ solution.subarraySum(input4,k4));
        System.out.println("Answer : "+ solution.subarraySum1(input4,k4));

    }
}
