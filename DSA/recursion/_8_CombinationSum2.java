package recursion;

import java.util.*;

/**
 * Problem Link : https://leetcode.com/problems/combination-sum-ii/description/
 *
 * Explanation (backtrackCombinationSumSkipDuplicateApproachOne): https://www.youtube.com/watch?v=FOyRpNUSFeA
 * Explanation (backtrackCombinationSumSkipDuplicateApproachTwo): https://www.youtube.com/watch?v=rSA3t6BDDwg
 *
 * Topic : TRecursion, TBacktracking
 * (Revisit) - Need Revision
 *
 * 40. Combination Sum II
 *
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 *
 *
 * Constraints:
 *     1 <= candidates.length <= 100
 *     1 <= candidates[i] <= 50
 *     1 <= target <= 30
 */
public class _8_CombinationSum2 {

    /**
     * This is a valid solution but will result in TLE as we are using hashmap to avoid the duplicates,
     * the duplicate answers still gets generated but only one of them are kept as we have used set.
     *
     * Time Complexity: O(2^n × k)
     *  - 2^n: Each element has 2 choices (include/exclude), creating 2^n possible combinations
     *  - k: Average length of each combination for HashSet operations
     *
     *  Space Complexity: O(2^n × k)
     *  Breakdown:
     *  Recursion stack: O(target/min_element) ≈ O(k)
     *  Current path: O(k)
     *  HashSet storage: O(2^n × k) - stores ALL combinations (including duplicates initially)
     *  Duplicate combinations: Extra space for duplicates before they're filtered
     *
    * */
    public void backtrackCombinationSum2(
            HashSet<List<Integer>> answer,
            List<Integer> current,
            int[] candidates,
            int target,
            int index,
            int sum
    ){

        // Base case
        if(sum==target){
            answer.add(new ArrayList<>(current));
            return;
        }

        // Bounding function
        if (index>=candidates.length||sum>target){
            return;
        }

        current.add(candidates[index]);
        // Check all the possible combination by including the current element.
        backtrackCombinationSum2(answer, current, candidates, target, index+1, sum+candidates[index]);
        current.remove(current.size()-1);
        // Checking all possible combinations by excluding the current element.
        backtrackCombinationSum2(answer, current, candidates, target, index+1, sum);
    }

    /**
     * Time Complexity: O(2^n)
     * Reasoning:
     * At each element, we make two choices: include it or exclude it
     * Even with the duplicate skipping optimization, in the worst case (no duplicates), we still explore 2^n possibilities
     * The duplicate skipping reduces the constant factor but doesn't change the exponential nature
     * Each valid combination takes O(k) time to copy to the answer list, where k is the average length of combinations
     *
     * Space Complexity: O(n)
     *
     * Recursion Stack Depth: The index variable in your function controls the recursion depth.
     * It increments by 1 in each step, meaning the deepest
     * the recursion can go is n (the number of elements in candidates).
     *
     * current list: The current list stores the combination being built.
     * Its size is bounded by the recursion depth,
     * so its maximum size is also n.
     *
     * Therefore, the space used to store the recursive call stack and the current list is proportional
     * to n, giving a space complexity of O(n).
     *
     * Explanation : https://www.youtube.com/watch?v=FOyRpNUSFeA
     * */
    public void backtrackCombinationSumSkipDuplicateApproachOne(
            List<List<Integer>> answer,
            List<Integer> current,
            int[] candidates,
            int target,
            int index,
            int sum
    ){

        // Base case
        if(sum==target){
            answer.add(new ArrayList<>(current));
            return;
        }

        // Bounding function
        if (index>=candidates.length||sum>target){
            return;
        }

        current.add(candidates[index]);
        // Check all the possible combination by including the current element.
        backtrackCombinationSumSkipDuplicateApproachOne(answer, current, candidates, target, index+1, sum+candidates[index]);
        current.remove(current.size()-1);

        // Check if the next element is same then we skip the element as we have already checked all the
        // possibilities with this element.
        while(index<candidates.length-1 && candidates[index]==candidates[index+1]){
            index++;
        }

        // Checking all possible combinations by excluding the current element.
        backtrackCombinationSumSkipDuplicateApproachOne(answer, current, candidates, target, index+1, sum);
    }


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> answerList = new ArrayList<>();
        Arrays.sort(candidates);
        backtrackCombinationSumSkipDuplicateApproachTwo(answerList,new ArrayList<>(),candidates,target,0,0);
        return new ArrayList<>(answerList);
    }

    /**
    * Same time & space complexity but process is a bit different.
     * We iterate through all the element using a for loop and then use backtracking
     * to check for possible solutions using for that element.
     * While going to the next element we check if the current element is same as last,
     * then we skip that iteration as we have already checked for that element.
     *
     * Explanation : https://www.youtube.com/watch?v=rSA3t6BDDwg
    * */
    public void backtrackCombinationSumSkipDuplicateApproachTwo(
            List<List<Integer>> answer,
            List<Integer> current,
            int[] candidates,
            int target,
            int index,
            int sum
    ){

        // Base case
        if(sum==target){
            answer.add(new ArrayList<>(current));
            return;
        }

        // Bounding function
        if (index>=candidates.length||sum>target){
            return;
        }

        for (int i=index;i<candidates.length;i++){
            // If previous element was same as current we skip that element as it will generate duplicate combinations.
            if (i!=index && candidates[i]==candidates[i-1]) continue;
            int currentElement = candidates[i];
            current.add(currentElement);
            backtrackCombinationSumSkipDuplicateApproachTwo(answer, current, candidates, target, i+1, sum+currentElement);
            current.remove(current.size()-1);
        }

    }


    /**
    * Revisited : 17th Nov,2025
     * public void solve(
     *         ArrayList<ArrayList<Integer>> answer,
     *         ArrayList<Integer> current,
     *         int currentSum,
     *         int[] nums,
     *         int target,
     *         int index
     *         )
     *     {
     *         // base case
     *         if(currentSum==target){
     *             answer.add(new ArrayList<>(current));
     *             return;
     *         }
     *         // bounding function
     *         if(index>=nums.length||currentSum>target) return;
     *
     *         for(int i=index;i<nums.length;i++){
     *             if(i!=index && nums[i]==nums[i-1]) continue;
     *             current.add(nums[i]);
     *             solve(answer,current,currentSum+nums[i],nums,target,i+1);
     *             current.remove(current.size()-1);
     *         }
     *     }
     *
     *     public List<List<Integer>> combinationSum2(int[] candidates, int target) {
     *         ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
     *         ArrayList<Integer> current = new ArrayList<>();
     *         Arrays.sort(candidates);
     *         solve(answer,current,0,candidates,target,0);
     *         return (List)answer;
     *     }
    * */

    public static void main(String[] args) {

        _8_CombinationSum2 solution = new _8_CombinationSum2();

        System.out.println("=== Test Case 1 ===");
        int[] candidates1 = {1,2,5,2,1,2};

        int target1 = 5;
        solution.combinationSum2(candidates1, target1).forEach(integers -> {
            System.out.println("  " + integers);
        });
        System.out.println();

    }

}
