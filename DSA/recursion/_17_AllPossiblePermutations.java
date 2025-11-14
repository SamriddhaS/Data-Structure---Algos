package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Problem Link : https://leetcode.com/problems/permutations/description/
 * Video(intuition) :
 *
 * Topic : TRecursion, TBacktracking, TDynamicProgramming
 * (Revisit) - Need Revision
 *
 * 46. Permutations
 * Medium
 * Topics
 *
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Example 2:
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 *
 * Example 3:
 * Input: nums = [1]
 * Output: [[1]]
 *
 *
 * Constraints:
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 *
 */
public class _17_AllPossiblePermutations {

    public void permute(List<List<Integer>> answer,
                        ArrayList<Integer> currentPath,
                        HashSet<Integer> used,
                        int[] nums) {

        if (currentPath.size() == nums.length){
            answer.add(new ArrayList<>(currentPath));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int ele = nums[i];
            if (used.contains(ele)) continue;
            currentPath.add(ele);
            used.add(ele);
            permute(answer,currentPath,used,nums);
            currentPath.remove(currentPath.size()-1);
            used.remove(ele);
        }
    }

    /**
     *
     * Time Complexity: O(n! × n)
     * Breakdown:
     * n! permutations to generate (for n=3: 3! = 6 permutations)
     * For each permutation, O(n) work to:
     *
     * Copy to result list: new ArrayList<>(currentPath) → O(n)
     * Loop through all n elements at each recursion level
     *
     * Total recursion depth: n levels
     * Why n! × n? Each of n! permutations requires O(n) operations to construct and copy.
     *
     * Space Complexity: O(n)
     *
     * Breakdown:
     * Recursion stack: O(n) depth (one call per element in permutation)
     * currentPath: O(n) size at most
     * used HashSet: O(n) size at most
     * Output space (n! × n) is typically not counted in space complexity
     *
     * Note: If we count output space, it becomes O(n! × n) since we store n! permutations, each of size n.
    * */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        permute(answer,new ArrayList<>(),new HashSet<>(),nums);
        return answer;
    }

    public static void main(String[] args) {

        _17_AllPossiblePermutations solution = new _17_AllPossiblePermutations();
        int[] paths = {1, 2, 3};
        System.out.println("Answer : " + solution.permute(paths));

    }

}
