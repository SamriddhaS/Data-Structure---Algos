package array_problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Problem Link : https://leetcode.com/problems/longest-consecutive-sequence/description/
 * Video : https://www.youtube.com/watch?v=P6RZZMu_maU
 * Topic : TArrays
 *
 * 128. Longest Consecutive Sequence
 * Solved
 * Medium
 *
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore, its length is 4.
 *
 * Example 2:
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 * Example 3:
 * Input: nums = [1,0,1,2]
 * Output: 3
 *
 * Constraints:
 *     0 <= nums.length <= 105
 *     -109 <= nums[i] <= 109
 *
* */
class _21_LongestConsecutiveSequence {

    /**
     * Solution 1 : Brute Force Solution.
     * Sorting the array and then counting the longest sequence will work.
     *
     * Time Complexity: O(n log n)
     * Dominated by Arrays.sort(nums) which uses a comparison-based sorting algorithm
     * The subsequent loop is O(n)
     *
     * Space Complexity: O(1) or O(log n)
     * O(1) auxiliary space used by the algorithm itself
     * O(log n) if counting the stack space used by the sorting algorithm (implementation-dependent)
     * Sorting is done in-place, so no extra array is created
     *
     * But clearly we are using (n Log n) to solve the problem and question has asked us to do in
     * O(n).
    * */
    public int longestConsecutive(int[] nums) {
        if (nums.length==0) return 0;
        Arrays.sort(nums);
        int longest=1;
        int current=1;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i]==nums[i+1]) continue;
            if (nums[i]+1==nums[i+1]){
                current++;
                longest = Math.max(current,longest);
            } else {
                current=1;
            }
        }
        return longest;
    }

    /**
     * Solution 2 :
     *
     * The trick to this problem is to not start checking for a sequence for those
     * values who has a previous smaller value.
     * This way we only check for a possible sequence from a starting point eliminating lots of iteration.
     *
     * Time Complexity: O(n)
     * - Building the HashSet: O(n) - inserting all elements
     * - The outer loop iterates through each unique element in the set: O(n) in worst case
     *
     * The if (mySet.contains(i-1)) continue; ensures we only start counting from the beginning of consecutive sequences
     * Since each element can only be part of one sequence, total work is bounded by O(n)
     * HashSet operations (contains, add) are O(1) average case
     *
     * Space Complexity: O(n)
     *
     * The HashSet stores all unique elements from the input array
     * In worst case (all elements unique): O(n) space
     * Additional variables (result, seq, number) use O(1) space
     * Overall: O(n)
     *
     * Why it's O(n) and not O(n²):
     * The crucial optimization is if (mySet.contains(i-1)) continue; - this prevents starting a count from the
     * middle of a sequence. Without this check, you'd potentially count the same sequence multiple times,
     * leading to O(n²) complexity. This is the optimal solution for this problem - you can't do better than O(n)
     * time since you need to examine each element at least once.
    * */
    public int longestConsecutive1(int[] nums) {
        if (nums.length==0) return 0;
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int longest=1;
        for (int i:set) {
            if (set.contains(i-1)) continue;
            else {
                int current=1;
                int num = i;
                while (set.contains(num+1)){
                    num = num+1;
                    current++;
                }
                longest = Math.max(current,longest);
            }
        }
        return longest;
    }

    public static void main(String[] args) {

        _21_LongestConsecutiveSequence solution = new _21_LongestConsecutiveSequence();

        int[] input = {100,4,200,1,3,2};
        System.out.println("Answer : "+ solution.longestConsecutive(input));
        System.out.println("Answer : "+ solution.longestConsecutive1(input));
    }
}
