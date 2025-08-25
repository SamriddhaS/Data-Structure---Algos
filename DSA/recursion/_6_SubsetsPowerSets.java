package DSA.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
 *
 *
 * ## SAME QUESTION ##
 *
 * Problem Link : https://takeuforward.org/data-structure/subset-sum-sum-of-all-subsets/
 *
 * Subset Sum : Sum of all Subsets
 * Problem Statement: Given an array print all the sum of the subset generated from it, in the increasing order.
 *
 * Examples:
 *
 * Example 1:
 * Input: N = 3, arr[] = {5,2,1}
 * Output: 0,1,2,3,5,6,7,8
 * Explanation: We have to find all the subset’s sum and print them.in this case the
 * generated subsets are [ [], [1], [2], [2,1], [5], [5,1], [5,2]. [5,2,1],so the sums we get will be  0,1,2,3,5,6,7,8
 *
 *
 * Input: N=3,arr[]= {3,1,2}
 * Output: 0,1,2,3,3,4,5,6
 * Explanation: We have to find all the subset’s sum and print them.in this case the
 * generated subsets are [ [], [1], [2], [2,1], [3], [3,1], [3,2]. [3,2,1],so the sums we get will be  0,1,2,3,3,4,5,6
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

    public void backtrackSubsetSum(
            List<Integer> answer,
            List<Integer> currentSet,
            int[] numbers,
            int sum,
            int index
    ){

        // base case : none

        // Bounding Function : none

        answer.add(sum);

        for (int i=index;i<numbers.length;i++){
            int currentElement = numbers[i];
            currentSet.add(currentElement);
            backtrackSubsetSum(answer, currentSet, numbers, sum+currentElement, i+1);
            currentSet.remove(currentSet.size()-1);
        }

    }

    public List<Integer> subsetsSum(int[] nums){
        List<Integer> answer = new ArrayList<>();
        backtrackSubsetSum(answer,new ArrayList<>(),nums,0,0);
        Collections.sort(answer);
        return answer;

    }

    public static void main(String[] args) {

        _6_SubsetsPowerSets solution = new _6_SubsetsPowerSets();
        int[] nums = {1,2,2};

        System.out.println("Subsets Answer: ");
        solution.subsets(nums).forEach(integers -> {
            System.out.println(" - "+integers);
        });

        System.out.println("Subsets Sum Answer: ");
        solution.subsetsSum(nums).forEach(integers -> {
            System.out.println(" - "+integers);
        });

    }

}
