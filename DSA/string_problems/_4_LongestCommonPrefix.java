package string_problems;

import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/longest-common-prefix/description/
 * Video Explanation :
 *
 * 14. Longest Common Prefix
 * Solved
 * Easy
 * Topics
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 * Constraints:
 *     1 <= strs.length <= 200
 *     0 <= strs[i].length <= 200
 *     strs[i] consists of only lowercase English letters if it is non-empty.
 *
 */
public class _4_LongestCommonPrefix {

    /**
     * Solution 1 :
     *
     * Time Complexity: O(n × m²)
     *
     * n = number of strings
     * m = length of shortest string
     * You iterate through each character of the shortest string (m iterations)
     * For each character, you check all n strings with startsWith()
     * startsWith() itself is O(i) where i is the prefix length being checked
     * Total: O(n × (1 + 2 + 3 + ... + m)) = O(n × m²)
     *
     * So : O(n × m²) worst case, because startsWith() for a prefix of length i takes O(i) time, and you do this for all prefixes.
     *
     * Space Complexity: O(m)
     *
     * shortestStr reference: O(1)
     * currentAns and prefix strings: O(m) in worst case (when prefix equals shortest string)
     * No additional data structures
     *
    * */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==1) return strs[0];

        String shortestStr = strs[0];
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length()<shortestStr.length()){
                shortestStr = strs[i];
            }
        }

        if (shortestStr.isEmpty()) return "";

        String currentAns="";
        for (int i = 1; i <= shortestStr.length(); i++) {
            String prefix = shortestStr.substring(0,i);
            for (int i1 = 0; i1 < strs.length; i1++) {
                if (!strs[i1].startsWith(prefix)){
                    return currentAns;
                }
            }
            // At least one possible prefix will be there to reach this line.
            currentAns = prefix;
        }
        return currentAns;
    }

    /**
     * Solution 2 : Optimal
     * Time Complexity: O(n × m)
     * - n = number of strings
     * - m = length of shortest string
     * - Outer loop: m iterations
     * - Inner loop: n iterations
     * - charAt(i): O(1)
     * - Total: O(n × m) ✓
     *
     * Space Complexity: O(m) :
     * - currentAns is built using substring(), which creates new strings
     * - In worst case (full shortest string is the prefix): O(m)
    * */
    public String longestCommonPrefix1(String[] strs) {

        if(strs.length==1) return strs[0];

        String shortestStr = strs[0];
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length()<shortestStr.length()){
                shortestStr = strs[i];
            }
        }

        if (shortestStr.isEmpty()) return "";

        String currentAns="";
        for (int i = 0; i < shortestStr.length(); i++) {
            char prefixChar = shortestStr.charAt(i);
            for (int i1 = 0; i1 < strs.length; i1++) {
                if (strs[i1].charAt(i)!=prefixChar){
                    return currentAns;
                }
            }
            // At least one possible prefix will be there to reach this line.
            currentAns = shortestStr.substring(0,i+1);
        }
        return currentAns;
    }

    public static void main(String[] args) {

        _4_LongestCommonPrefix obj = new _4_LongestCommonPrefix();

        String[] input = {"flower","flow","flight"};
        System.out.println("Out : "+obj.longestCommonPrefix(input));

        String[] input1 = {"dog","racecar","car"};
        System.out.println("Out : "+obj.longestCommonPrefix(input1));

        String[] input2 = {"reflower","flow","flight"};
        System.out.println("Out : "+obj.longestCommonPrefix(input2));

        String[] input3 = {"",""};
        System.out.println("Out : "+obj.longestCommonPrefix(input3));

        String[] input4 = {"flower","flower","flower","flower"};
        System.out.println("Out : "+obj.longestCommonPrefix(input4));

    }
}