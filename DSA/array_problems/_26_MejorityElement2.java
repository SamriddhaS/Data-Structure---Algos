package array_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Problem Link : https://leetcode.com/problems/majority-element-ii/description/
 * Video : https://www.youtube.com/watch?v=Q6L5SoS-fTo
 * Topic : TArrays
 * @see _25_MejorityElement
 *
 * 229. Majority Element II
 * Solved
 * Medium
 *
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: [3]
 *
 * Example 2:
 * Input: nums = [1]
 * Output: [1]
 *
 * Example 3:
 * Input: nums = [1,2]
 * Output: [1,2]
 *
 * Constraints:
 *
 *     1 <= nums.length <= 5 * 104
 *     -109 <= nums[i] <= 109
 *
 * Follow up: Could you solve the problem in linear time and in O(1) space?
 *
* */
class _26_MejorityElement2 {

    /**
     * Solution 1 : Brute Force
     *
     * Time Complexity: O(n²)
     *
     * Outer loop runs n times
     * Inner loop runs n times for each outer iteration
     * Total: n × n = n² operations
     *
     * Space Complexity: O(1)
     *
     * Uses only a few variables (element, count)
     * No additional data structures
    * */
    public List<Integer> majorityElement(int[] nums) {
        HashSet<Integer> answerSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int element = nums[i];
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (element==nums[j]) count++;
            }
            if (count> nums.length/3) answerSet.add(element);
        }
        List<Integer> answer = new ArrayList<>(answerSet);
        return answer;
    }

    /**
     * Solution 2 : Better using extra space
     *
     * Time Complexity: O(n)
     *
     * First loop: O(n) to build the HashMap
     * Second loop: O(k) where k = number of unique elements ≤ n
     * Total: O(n)
     *
     * Space Complexity: O(n)
     *
     * HashMap stores up to n entries in worst case (all unique elements)
     * */
    public List<Integer> majorityElement1(int[] nums) {
        HashMap<Integer,Integer> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i],countMap.getOrDefault(nums[i],0)+1);
        }

        List<Integer> answer = new ArrayList<>();
        for (Integer key: countMap.keySet()){
            if (countMap.get(key)> nums.length/3){
                answer.add(key);
            }
        }

        return answer;
    }

    /**
     * Optimal Solution :  Boyer-Moore Voting Algorithm
     *
     * Time Complexity: O(n)
     * First loop: O(n) to find two candidate elements
     * Second loop: O(n) to verify both candidates
     * Total: O(n) + O(n) = O(n)
     *
     * Space Complexity: O(1)
     * Only uses a constant number of variables
     * Output list not counted in auxiliary space analysis
     *
     * This is the Boyer-Moore Voting Algorithm extended for finding elements appearing more than ⌊n/3⌋ times.
     * At most 2 such elements can exist.
     *
    * */
    public List<Integer> majorityElement2(int[] nums) {
        Integer element=null;
        int count = 0;
        Integer element2=null;
        int count2 = 0;

        for (int i = 0; i < nums.length; i++) {
            int currentEle = nums[i];
            if (element!=null&&element==currentEle){
                count++;
            }else if (element2!=null&&element2==currentEle){
                count2++;
            }else if (count==0){
                element = currentEle;
                count++;
            }else if (count2==0){
                element2 = currentEle;
                count2++;
            }else {
                count--;
                count2--;
            }
        }

        int eleCount=0;
        int eleCount1=0;
        for (int i = 0; i < nums.length; i++) {
            if (count>0&&nums[i]==element){
                eleCount++;
            }
            if (count2>0&&nums[i]==element2){
                eleCount1++;
            }
        }

        List<Integer> answer = new ArrayList<>();
        if (eleCount> nums.length/3) {
            answer.add(element);
        }
        if (eleCount1> nums.length/3) {
            answer.add(element2);
        }

        return answer;
    }

    public static void main(String[] args) {

        _26_MejorityElement2 solution = new _26_MejorityElement2();

        int[] input = {3,2,3};
        System.out.println("Answer : "+ solution.majorityElement(input));
        System.out.println("Answer : "+ solution.majorityElement1(input));
        System.out.println("Answer : "+ solution.majorityElement2(input));

        int[] input1 = {2,2,1,1,1,2,2};
        System.out.println("Answer : "+ solution.majorityElement(input1));
        System.out.println("Answer : "+ solution.majorityElement1(input1));
        System.out.println("Answer : "+ solution.majorityElement2(input1));
    }
}
