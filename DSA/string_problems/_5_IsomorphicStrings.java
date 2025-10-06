package string_problems;

import java.util.HashMap;

/**
 * Problem Link : https://leetcode.com/problems/isomorphic-strings/description/
 * Video Explanation : https://www.youtube.com/watch?v=7yF-U1hLEqQ
 *
 * 205. Isomorphic Strings
 * Solved
 * Easy
 * Topics
 *
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving
 * the order of characters. No two characters may map to the same character, but a character
 * may map to itself.
 *
 * Example 1:
 * Input: s = "egg", t = "add"
 *
 * Output: true
 *
 * Explanation:
 * The strings s and t can be made identical by:
 *     Mapping 'e' to 'a'.
 *     Mapping 'g' to 'd'.
 *
 * Example 2:
 * Input: s = "foo", t = "bar"
 *
 * Output: false
 *
 * Explanation:
 * The strings s and t can not be made identical as 'o' needs to be mapped to both 'a' and 'r'.
 *
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 *
 * Constraints:
 *     1 <= s.length <= 5 * 104
 *     t.length == s.length
 *     s and t consist of any valid ascii character.
 *
 */
public class _5_IsomorphicStrings {

    /**
     * Solution 1 :
     * Time Complexity: O(n)
     * - First loop: O(n) - iterate through string once
     * - Second loop: O(n) - iterate through string once
     * - Total: O(n) where n = length of strings
     *
     * Space Complexity: O(n)
     * - map: stores up to n key-value pairs (worst case: all unique characters)
     * - map2: stores up to n key-value pairs (worst case: all unique characters)
     * - Total: O(n)
    * */
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character,Character> map = new HashMap<>();
        HashMap<Character,Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char first = s.charAt(i);
            char second = t.charAt(i);
            map.put(first,second);
            map2.put(second,first);
        }
        for (int i = 0; i < t.length(); i++) {
            if (map.get(s.charAt(i))!=t.charAt(i)) return false;
            if (map2.get(t.charAt(i))!=s.charAt(i)) return false;
        }
        return true;
    }

    /**
     * Solution 2 :
     * Create the mapping and validate at the same time.
     * Same solution 1 but in single pass.
     * Time Complexity: O(n)
     *  Space Complexity: O(n)
     *
    * */
    public boolean isIsomorphic1(String s, String t) {
        HashMap<Character,Character> map = new HashMap<>();
        HashMap<Character,Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char first = s.charAt(i);
            char second = t.charAt(i);
            if (map.containsKey(first)&&map.get(first)!=second) return false;
            map.put(first,second);
            if (map2.containsKey(second)&&map2.get(second)!=first) return false;
            map2.put(second,first);
        }
        return true;
    }

    public static void main(String[] args) {

        _5_IsomorphicStrings obj = new _5_IsomorphicStrings();

        String input = "paper";
        String input1 = "title";
        System.out.println("Out : "+obj.isIsomorphic(input,input1));

        String input2 = "foo";
        String input3 = "bar";
        System.out.println("Out : "+obj.isIsomorphic(input2,input3));

        String input4 = "badc";
        String input5 = "baba";
        System.out.println("Out : "+obj.isIsomorphic(input4,input5));

    }
}