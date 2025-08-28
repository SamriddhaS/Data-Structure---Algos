package DSA.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Link : https://leetcode.com/problems/word-break/submissions/1750577332/
 * Video(intuition) : https://www.youtube.com/watch?v=Sx9NNgInc3A&ab_channel=NeetCode
 * (Watch till 9:00 min of the video to understand the decision tree, after that read the code documentation, our
 * solution will not match after this point as we have used different approach to solve this.)
 * Topic : TRecursion, TBacktracking, TDynamicProgramming
 * (Revisit) - Need Revision
 *
 * 139. Word Break
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 * Constraints:
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 *
 */
public class _15_WordBreak {

    public boolean backtrack(
            String s,
            List<String> wordDict,
            int position
    ){
        if (position==s.length()) return true;

        for (String word : wordDict) {
            if (s.startsWith(word,position)){
                if(backtrack(s,wordDict,position+word.length())) return true;
            }
        }
        return false;
    }

    /**
    * Solution 1 : this will work but will give TLE in leetcode.
     *
     * Why TLE occurs:
     * Overlapping subproblems - the same position gets computed multiple times through different paths.
     * Example: For string "aaab" with dict ["a","aa"]:
     *
     * Path 1: use "a" at pos 0 → solve pos 1
     * Path 2: use "aa" at pos 0 → solve pos 2
     * Later: use "a" at pos 1 → solve pos 2 again ← Redundant!
     *
     * With your test case having many "a"s and multiple word lengths, the same positions get recomputed exponentially many times.
     *
     * Time Complexity:
     * O(2^n) - exponential
     * At each position, you branch into multiple recursive calls
     * In worst case, you explore all possible ways to segment
     *
     * Space Complexity:
     * O(n) - for recursion stack
     * Maximum recursion depth is n (length of string)
     *
    * */
    public boolean wordBreak(String s, List<String> wordDict) {
        return backtrack(s, wordDict, 0);
    }

    public boolean backtrackWithMemorization(
            String s,
            List<String> wordDict,
            int position,
            Boolean[] memo
    ){
        if (position==s.length()) return true;

        if (memo[position]!=null) return memo[position];

        for (String word : wordDict) {
            if (s.startsWith(word,position)){
                boolean result = backtrackWithMemorization(s,wordDict,position+word.length(),memo);
                memo[position] = result;
                if(result) return true;
            }
        }
        return false;
    }

    /**
     * Solution 2 :
     * Approach: Top-down dynamic programming (memoized recursion) to solve the word segmentation problem.
     *
     * Core Logic:
     * At each position in the string, try all dictionary words that match starting from that position
     * For each match, recursively solve the remaining substring
     * Use memoization to cache results for each position to avoid redundant computations
     *
     * Flow:
     * Base case: If position equals string length, segmentation is complete → return true
     * Memoization check: If result for current position already computed → return cached result
     * Word matching: For each dictionary word that starts at current position:
     *
     * Recursively solve from position + word.length()
     * Cache the result at current position
     * If any path succeeds → return true
     *
     * Failure: If no word leads to valid segmentation → return false
     *
     * Key Optimization: Each position's result is computed only once and reused, eliminating exponential redundancy.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(n × m × L)
     * n = string length
     * m = dictionary size
     * L = average word length
     * Each of n positions computed once, trying m words, each requiring O(L) string matching
     *
     * Space Complexity: O(n)
     * O(n) for memoization array
     * O(n) for recursion stack (worst case depth = n)
     * Total: O(n)
     *
     * */
    public boolean wordBreakWithMemorization(String s, List<String> wordDict) {
        Boolean[] memo = new Boolean[s.length()];
        return backtrackWithMemorization(s, wordDict, 0,memo);
    }

    public static void main(String[] args) {
        _15_WordBreak solution = new _15_WordBreak();
        List<String> word = new ArrayList<>();
        word.add("cats");
        word.add("dog");
        word.add("sand");
        word.add("and");
        word.add("cat");

        String str = "catsandog";

        // Measure time for wordBreak
        long start1 = System.nanoTime();
        boolean result1 = solution.wordBreak(str, word);
        long end1 = System.nanoTime();
        System.out.println(str + " Word Break : " + result1
                + " | Time taken: " + (end1 - start1) / 1_000_000.0 + " ms");

        // Measure time for wordBreakWithMemorization
        long start2 = System.nanoTime();
        boolean result2 = solution.wordBreakWithMemorization(str, word);
        long end2 = System.nanoTime();
        System.out.println(str + " Word Break With Memorization : " + result2
                + " | Time taken: " + (end2 - start2) / 1_000_000.0 + " ms");

    }

}
