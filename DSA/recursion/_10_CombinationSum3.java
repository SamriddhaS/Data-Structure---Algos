package DSA.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem Link : https://leetcode.com/problems/combination-sum-iii/description/
 *
 *
 * 216. Combination Sum III
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 *
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.
 *
 *
 *
 * Example 1:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 *
 * Example 2:
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 *
 * Example 3:
 * Input: k = 4, n = 1
 * Output: []
 * Explanation: There are no valid combinations.
 * Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
 *
 *
 * Constraints:
 *  2 <= k <= 9
 *  1 <= n <= 60
 *
 */
public class _10_CombinationSum3 {

    /**
    * Time Complexity: O((9^k))
     * - We're choosing k numbers from 9 candidates {1,2,3,4,5,6,7,8,9}
     * - Total possible combinations = C(9,k) = 9!/(k!(9-k)!)
     * - Each valid combination requires O(k) time to copy into result
     * - Early termination when sum ≥ target significantly reduces actual explored paths
     *
     * Space Complexity: O(k)
     * - Recursion stack depth: O(k) - maximum k levels deep
     * - Current combination storage: O(k) - stores at most k elements
     * - Output space: O(C(9,k) × k) - not counted in auxiliary space analysis
    * */
    public void backtrackCombinationSum3(
            List<List<Integer>> answer,
            List<Integer> current,
            int[] candidates,
            int target,
            int steps,
            int sum,
            int index
    ){

        // Base case
        if (sum==target && current.size()==steps){
            answer.add(new ArrayList<>(current));
            return;
        }

        // Bounding Function
        if(index>=candidates.length || sum>=target){
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            int element = candidates[i];
            current.add(element);
            backtrackCombinationSum3(answer, current, candidates, target, steps,sum+element, i+1);
            current.remove(current.size()-1);
        }

    }

    public List<List<Integer>> combinationSum2(int k, int target) {
        List<List<Integer>> answerList = new ArrayList<>();
        int[] nums = {1,2,3,4,5,6,7,8,9};
        backtrackCombinationSum3(answerList,new ArrayList<>(),nums,target, k,0,0);
        return new ArrayList<>(answerList);
    }

    public static void main(String[] args) {

        _10_CombinationSum3 solution = new _10_CombinationSum3();;
        int k = 9, n = 45;
        solution.combinationSum2(k, n).forEach(integers -> {
            System.out.println("  " + integers);
        });
        System.out.println();

    }

}
