package array_problems;


import java.util.HashMap;
import java.util.HashSet;

/**
 * Problem Link : https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Video :
 * Topic : TArrays
 *
 *
 * 3. Longest Substring Without Repeating Characters
 * Solved
 * Medium
 * Topics
 *
 * Given a string s, find the length of the longest
 * without duplicate characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Note that "bca" and "cab" are also correct answers.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Constraints:
 *     0 <= s.length <= 5 * 104
 *     s consists of English letters, digits, symbols and spaces.
 *
 * */
class _7_LongestSubstringWithoutRepeatingChar {

    /**
     * Brute Force Solution :
     * Time -> O(n^2) for 2 loops
     * Space -> O(n) for the hash set.
    * */
    public int lengthOfLongestSubstringBrute(String s) {
        int longestSubString = 0;
        for(int i=0;i<s.length();i++){
            int currentSubStringLen=0;
            HashSet<Character> set = new HashSet<>();
            for(int j=i;j<s.length();j++){
                if(set.contains(s.charAt(j))){
                    break;
                }
                currentSubStringLen++;
                longestSubString = Math.max(currentSubStringLen,longestSubString);
                set.add(s.charAt(j));
            }
        }

        return longestSubString;
    }


    /**
     * Better Solution Using Two Pointers
     *
     * Time Complexity: O(n)
     *
     * The right pointer traverses the string once: O(n)
     * The left pointer also moves at most n times across the entire execution (it never moves backwards)
     * Each character is added to and removed from the HashSet at most once
     * HashSet operations (add, remove, contains) are O(1) on average
     *
     * Even though there's a nested while loop, the inner loop's total iterations across the entire algorithm execution is bounded by n,
     * since left can only increment up to right, and right goes from 0 to n-1.
     *
     * Space Complexity: O(min(m, n))
     *
     * Where m is the size of the character set and n is the length of the string
     * The HashSet stores at most all unique characters in the current window
     * In the worst case, this could be:
     *
     * O(1) for ASCII (128 characters max)
     * O(1) for extended ASCII (256 characters max)
     * O(n) if we consider Unicode, but practically it's still limited by the character set size
     *
     * For most practical purposes with standard character sets,
     * the space complexity is effectively O(1) since the HashSet size is
     * bounded by a constant (the alphabet size).
     * */
    public int lengthOfLongestSubstring(String s) {
        int longestSubString = 0;
        HashSet<Character> set = new HashSet<>();
        int right = 0;
        int left = 0;
        while(right<s.length()){
            if(set.contains(s.charAt(right))){
                while(set.contains(s.charAt(right))){
                    set.remove(s.charAt(left));
                    left++;
                }
            }else{
                int currentLength = (right-left)+1;
                if(currentLength>longestSubString) longestSubString = currentLength;
            }
            set.add(s.charAt(right));
            right++;
        }
        return longestSubString;
    }

    /**
    * Optimal Approach : Instead of shifting the left pointer one by one we jump it based on
     * the intex of the char that is repeating.
     *
     * Time - O(n) - each character visited exactly once
     * Space - O(n)
     *
     * Less iterations for the left pointer by storing & jumping it through index.
     *
     * Intuition:
     *
     * - Uses a HashMap to store both characters AND their positions
     * - When a duplicate is found, it directly jumps the left pointer to the optimal position
     * - No need to remove characters one by one
     * - Calculates length at every step
    * */
    public int lengthOfLongestSubstringOptimal(String s) {
        int longestSubString = 0;
        HashMap<Character,Integer> set = new HashMap<>();
        int right = 0;
        int left = 0;
        while(right<s.length()){
            if(set.containsKey(s.charAt(right))){
                int index = set.get(s.charAt(right))+1;
                left = Math.max(left,index);
            }
            int currentLength = (right-left)+1;
            longestSubString = Math.max(currentLength,longestSubString);
            set.put(s.charAt(right),right);
            right++;
        }
        return longestSubString;
    }

    public static void main(String[] args) {
        _7_LongestSubstringWithoutRepeatingChar solution = new _7_LongestSubstringWithoutRepeatingChar();

        // Test cases
        String[] testInputs = {
                "abcabcbb",      // Expected: 3 ("abc")
                "bbbbb",         // Expected: 1 ("b")
                "pwwkew",        // Expected: 3 ("wke")
                "",              // Expected: 0 (empty string)
                "abcdef",        // Expected: 6 (entire string)
                "a",             // Expected: 1 (single character)
                "au",            // Expected: 2 ("au")
                "dvdf",          // Expected: 3 ("vdf")
                "abba",          // Expected: 2 ("ab" or "ba")
                "tmmzuxt"        // Expected: 5 ("mzuxt")
        };

        int[] expectedResults = {3, 1, 3, 0, 6, 1, 2, 3, 2, 5};

        System.out.println("Testing Longest Substring Without Repeating Characters:");
        System.out.println("=============================================================================");

        for (int i = 0; i < testInputs.length; i++) {
            String input = testInputs[i];
            int result = solution.lengthOfLongestSubstringOptimal(input);
            int expected = expectedResults[i];
            boolean passed = result == expected;

            System.out.printf("Test %2d: %-12s -> Result: %d, Expected: %d [%s]\n",
                    i + 1,
                    input.isEmpty() ? "\"\"" : "\"" + input + "\"",
                    result,
                    expected,
                    passed ? "PASS" : "FAIL");
        }
    }
}
