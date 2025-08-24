package DSA.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Problem Link : https://leetcode.com/problems/subsets/description/
 * Video : https://www.youtube.com/watch?v=3tpjp5h3M6Y
 * Topic : TRecursion, TBacktracking
 * (Revisit) - Need Revision
 *
 * Subsets
 *
 * Given an integer array nums of unique elements, return all possible
 * (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 * Constraints:
 *     1 <= nums.length <= 10
 *     -10 <= nums[i] <= 10
 *     All the numbers of nums are unique.
 */
public class _6_SubsetsPowerSets {

    public void backtrack(ArrayList<ArrayList<Integer>> answer,ArrayList<Integer> current,int[] nums,int index){
        answer.add(new ArrayList<>(current));
        for (int i = index;i<nums.length;i++){
            current.add(nums[i]); // step 1
            backtrack(answer, current, nums, i+1); // step 2
            current.remove(current.size()-1); // step 3
        }
    }

    /**
     * Time Complexity : O(n⋅2^n)
     *
     * Number of subsets:
     * For an input array of size n,
     * each element can either be included or not included in a subset.→ Total subsets:2^n.
     *
     * Work per subset:
     * To copy the current subset into resultSets, we do new ArrayList<>(tempSet) which takes
     * O(k), where k is the size of the subset.
     * Across all subsets, the average subset length is roughly
     * n/2, but the upper bound is O(n)
     *
     * Space Complexity :
     *
     * Recursion stack:
     * At most, you recurse down n levels (because start increments each time) → Stack usage: O(n)
     *
     * Temporary list: tempSet holds at most n elements → O(n)
     *
     * Output storage: The final result holds all subsets. There are 2n subsets and each can take up to
     * n space → Output space: O(n⋅2n)
     *
     * Auxiliary Space (without output):  O(n)
     * Total Space (including output): O(n⋅2n)
     *
    * */
    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
        backtrack(answer,new ArrayList<>(),nums,0);
        return (List) answer;
    }

    public static void main(String[] args) {

        _6_SubsetsPowerSets solution = new _6_SubsetsPowerSets();
        int[] nums = {1,2,3};
        solution.subsets(nums).forEach(integers -> {
            System.out.println("Answer : "+integers);
        });

    }

}
