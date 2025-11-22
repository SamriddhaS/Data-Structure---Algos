package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Problem Link : https://leetcode.com/problems/subsets-ii/description/
 * Video (backtrackSubsetsWithoutDupApproachTwo) : https://www.youtube.com/watch?v=RIn3gOkbhQE&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=54&ab_channel=takeUforward
 * Video 2 (backtrackSubsetsWithoutDupApproachOne) : https://www.youtube.com/watch?v=qGyyzpNlMDU&ab_channel=NikhilLohia
 *
 * Topic : TRecursion, TBacktracking
 * (Revisit) - Need Revision
 *
 * 90. Subsets II
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 * Constraints:
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 */
public class _9_SubsetsTwo {


    public void backtrackSubsetsWithoutDupApproachOne(
            List<List<Integer>> answer,
            List<Integer> current,
            int[] nums,
            int index
    ){

        // this contains checking will take extra time so this solution will be slightly slower.
        if (answer.contains(current)) return;
        answer.add(new ArrayList<>(current));

        for (int i = index;i<nums.length;i++){
            int element = nums[i];
            current.add(element);
            backtrackSubsetsWithoutDupApproachOne(answer, current, nums, i+1);
            current.remove(current.size()-1);
        }

    }

    public void backtrackSubsetsWithoutDupApproachTwo(
            List<List<Integer>> answer,
            List<Integer> current,
            int[] nums,
            int index
    ){

        answer.add(new ArrayList<>(current));

        for (int i = index;i<nums.length;i++){

            if (i!=index && nums[i]==nums[i-1]) continue;// if previous item was same we skip the item to avoid duplicates

            int element = nums[i];
            current.add(element);
            backtrackSubsetsWithoutDupApproachTwo(answer, current, nums, i+1);
            current.remove(current.size()-1);
        }

    }

    /**
     * Time Complexity : O(k * 2^n)
     * O(2^n) for generating every subset and O(k)  to
     * insert every subset in another data structure if the average
     * length of every subset is k. Overall O(k * 2^n).
     *
     * Space Complexity: O(2^n * k) to store every subset of average length k. Auxiliary space is O(n)  if n is the depth of the recursion tree.
    * */
    public List<List<Integer>> subsetsWithoutDup(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
        backtrackSubsetsWithoutDupApproachTwo(answer,new ArrayList<>(),nums,0);
        return answer;
    }


    /**
    * Revisited on : 22nd Nov 2025
    * */
    /*public void solve(
            List<List<Integer>> answer,
            List<Integer> current,
            int[] nums,
            int index
    )
    {
        if(index>=nums.length) {
            answer.add(new ArrayList<>(current));
            return;
        }

        current.add(nums[index]);
        solve(answer,current,nums,index+1);
        current.remove(current.size()-1);
        while(index<nums.length-1 && nums[index+1]==nums[index]) index++;
        solve(answer,current,nums,index+1);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> answers = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        solve(answers,current,nums,0);
        return answers;
    }*/

    public static void main(String[] args) {

        _9_SubsetsTwo solution = new _9_SubsetsTwo();
        int[] nums = {1,2,2};

        System.out.println("Subsets Without Dup Answer: ");
        solution.subsetsWithoutDup(nums).forEach(integers -> {
            System.out.println(" - "+integers);
        });

    }

}
