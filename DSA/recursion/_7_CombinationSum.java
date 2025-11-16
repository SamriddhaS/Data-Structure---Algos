package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Link : https://leetcode.com/problems/combination-sum/description/
 * Video : https://www.youtube.com/watch?v=GBKI9VSKdGg
 * Topic : TRecursion, TBacktracking
 * (Revisit) - Need Revision
 *
 *
 * 39. Combination Sum
 *
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers
 * sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
 * of at least one of the chosen numbers is different.
 * The test cases are generated such that the number of unique combinations that sum up to
 * target is less than 150 combinations for the given input.
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 *
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * Example 3:
 *
 * Input: candidates = [2], target = 1
 * Output: []
 *
 *
 * Constraints:
 *     1 <= candidates.length <= 30
 *     2 <= candidates[i] <= 40
 *     All elements of candidates are distinct.
 *     1 <= target <= 40
 */
public class _7_CombinationSum {


    public void backtrackCombinationSum(List<List<Integer>> answerList,
                                        List<Integer> current,
                                        int[] candidates,
                                        int target,
                                        int index,
                                        int sum
    ){
        // base case
        if (sum == target){
            answerList.add(new ArrayList<>(current));
            return;
        }

        // bounding function
        if(index>=candidates.length || sum>target){
            return;
        }

        current.add(candidates[index]);
        backtrackCombinationSum(answerList, current, candidates, target, index, sum+candidates[index]);
        current.remove(current.size()-1);
        backtrackCombinationSum(answerList, current, candidates, target, index+1, sum);
    }

    /**
     * Solution 1 :
     * Time Complexity: O(N^T)
     * Space Complexity: O(T)
     * Where:
     * N = length of candidates array
     * T = target value
     *
     * Approach 1 explicitly makes two choices at each step:
     * "Should I include this element again?" (same index)
     * "Should I move to the next element?" (index+1)
    * */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answerList = new ArrayList<>();
        backtrackCombinationSum(answerList,new ArrayList<>(),candidates,target,0,0);
        return answerList;
    }

    public void backtrackCombinationSumApproachTwo(List<List<Integer>> answerList,
                                        List<Integer> current,
                                        int[] candidates,
                                        int target,
                                        int index,
                                        int sum
    ){
        // base case
        if (sum == target){
            answerList.add(new ArrayList<>(current));
            return;
        }

        // bounding function
        if(index>=candidates.length || sum>target){
            return;
        }

        for (int i=index;i< candidates.length;i++){
            current.add(candidates[i]);
            backtrackCombinationSumApproachTwo(answerList, current, candidates, target, i, sum+candidates[i]);
            current.remove(current.size()-1);
        }

    }

    /**
     * Solution 2 :
     * Same time and space complexity and will generate the same decision tree.
     * Key Differences in Execution:
     *
     * Approach 2 implicitly handles both by:
     *
     * The loop naturally moves through elements (like the "exclude" path)
     * Passing i instead of i+1 allows reuse (like the "include" path)
     *
     * */
    public List<List<Integer>> combinationSumApproachTwo(int[] candidates, int target) {
        List<List<Integer>> answerList = new ArrayList<>();
        backtrackCombinationSumApproachTwo(answerList,new ArrayList<>(),candidates,target,0,0);
        return answerList;
    }


    /**
     * Revisited on - 17th Nov,2025
    * */
    /*public void solve(
            ArrayList<ArrayList<Integer>> answer,
            ArrayList<Integer> current,
            int currentSum,
            int[] nums,
            int target,
            int index
    )
    {
        // base case
        if(currentSum==target){
            answer.add(new ArrayList<>(current));
            return;
        }
        // bounding function
        if(index>=nums.length||currentSum>target) return;

        // 1st decision -> Keep the item and move forward
        current.add(nums[index]);
        currentSum+=nums[index];
        solve(answer,current,currentSum,nums,target,index);
        current.remove(current.size()-1);
        currentSum-=nums[index];

        // 2nd desicion -> Dont add the same element again move with the next elements
        solve(answer,current,currentSum,nums,target,index+1);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
        ArrayList<Integer> current = new ArrayList<>();
        solve(answer,current,0,candidates,target,0);
        return (List)answer;
    }*/

    public static void main(String[] args) {

        _7_CombinationSum solution = new _7_CombinationSum();

        // Test Case 1: Example from problem
        System.out.println("=== Test Case 1 ===");
        int[] candidates1 = {2,3,6,7};
        int target1 = 7;
        System.out.println("Input: candidates = [2,3,6,7], target = 7");
        System.out.println("Expected: [[2,2,3],[7]]");
        System.out.println("Actual:");
        solution.combinationSum(candidates1, target1).forEach(integers -> {
            System.out.println("  " + integers);
        });
        System.out.println();

        // Test Case 2: Example 2 from problem
        System.out.println("=== Test Case 2 ===");
        int[] candidates2 = {2,3,5};
        int target2 = 8;
        System.out.println("Input: candidates = [2,3,5], target = 8");
        System.out.println("Expected: [[2,2,2,2],[2,3,3],[3,5]]");
        System.out.println("Actual:");
        solution.combinationSum(candidates2, target2).forEach(integers -> {
            System.out.println("  " + integers);
        });
        System.out.println();

        // Test Case 3: No solution possible
        System.out.println("=== Test Case 3 ===");
        int[] candidates3 = {2};
        int target3 = 1;
        System.out.println("Input: candidates = [2], target = 1");
        System.out.println("Expected: []");
        System.out.println("Actual:");
        solution.combinationSum(candidates3, target3).forEach(integers -> {
            System.out.println("  " + integers);
        });
        if (solution.combinationSum(candidates3, target3).isEmpty()) {
            System.out.println("  []");
        }
        System.out.println();

        // Test Case 4: Single element that equals target
        System.out.println("=== Test Case 4 ===");
        int[] candidates4 = {5};
        int target4 = 5;
        System.out.println("Input: candidates = [5], target = 5");
        System.out.println("Expected: [[5]]");
        System.out.println("Actual:");
        solution.combinationSum(candidates4, target4).forEach(integers -> {
            System.out.println("  " + integers);
        });
        System.out.println();

        // Test Case 5: Multiple uses of same number
        System.out.println("=== Test Case 5 ===");
        int[] candidates5 = {3};
        int target5 = 9;
        System.out.println("Input: candidates = [3], target = 9");
        System.out.println("Expected: [[3,3,3]]");
        System.out.println("Actual:");
        solution.combinationSum(candidates5, target5).forEach(integers -> {
            System.out.println("  " + integers);
        });
        System.out.println();

        // Test Case 6: Larger array with multiple solutions
        System.out.println("=== Test Case 6 ===");
        int[] candidates6 = {2,3,4,5};
        int target6 = 6;
        System.out.println("Input: candidates = [2,3,4,5], target = 6");
        System.out.println("Expected: [[2,2,2],[2,4],[3,3]]");
        System.out.println("Actual:");
        solution.combinationSum(candidates6, target6).forEach(integers -> {
            System.out.println("  " + integers);
        });
        System.out.println();

        // Test Case 7: All candidates larger than target
        System.out.println("=== Test Case 7 ===");
        int[] candidates7 = {10,15,20};
        int target7 = 5;
        System.out.println("Input: candidates = [10,15,20], target = 5");
        System.out.println("Expected: []");
        System.out.println("Actual:");
        solution.combinationSum(candidates7, target7).forEach(integers -> {
            System.out.println("  " + integers);
        });
        if (solution.combinationSum(candidates7, target7).isEmpty()) {
            System.out.println("  []");
        }
        System.out.println();

        // Test Case 8: Complex case with many combinations
        System.out.println("=== Test Case 8 ===");
        int[] candidates8 = {2,3,7};
        int target8 = 7;
        System.out.println("Input: candidates = [2,3,7], target = 7");
        System.out.println("Expected: [[2,2,3],[7]]");
        System.out.println("Actual:");
        solution.combinationSum(candidates8, target8).forEach(integers -> {
            System.out.println("  " + integers);
        });
        System.out.println();


    }

}
