package string_problems;

import java.util.HashMap;
import java.util.Objects;

/**
 * Problem Link : https://leetcode.com/problems/valid-anagram/description/
 * Video Explanation : https://www.youtube.com/watch?v=9UtInBqnCgA
 *
 * 242. Valid Anagram
 * Easy
 * Topics
 *
 * Given two strings s and t, return true if t is an
 * of s, and false otherwise.
 *
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 *
 * Constraints:
 *
 *     1 <= s.length, t.length <= 5 * 104
 *     s and t consist of lowercase English letters.
 *
 * Follow up: What if the inputs contain Unicode characters?
 * How would you adapt your solution to such a case?
 *
 */
public class _6_VaildAnagram {

    /**
     * Solution 1 :
     * This one will also work for the follow-up question that's there.
     * We can also handle the follow-up question by sorting the char in alphabetical order
     * and then compare them
     *
     * Time Complexity: O(n)
     *
     * First loop: O(n) - iterate through string s
     * Second loop: O(n) - iterate through string t
     * Third loop: O(k) where k = unique characters in s (at most 26 for lowercase English letters, or at most n)
     * Total: O(n) since all operations are linear
     *
     * Space Complexity: O(k)
     *
     * Two HashMaps storing character frequencies
     * k = number of unique characters (at most 26 for lowercase English, or at most n)
     * Total: O(k) or O(1) if we consider a fixed character set (e.g., 26 letters)
     *
    * */
    public boolean isAnagram(String s, String t) {
        if (s.length()!=t.length()) return false;
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }
        HashMap<Character,Integer> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = t.charAt(i);
            map2.put(c,map2.getOrDefault(c,0)+1);
        }

        for (char c : map.keySet()){
            if (!Objects.equals(map.get(c), map2.get(c))) return false;
        }
        return true;
    }

    /**
     * Solution 2
     * This solution is a bit faster in real time as we are using arrays instead of hashmap.
     * But it wouldn't work with other char other than small english alphabet.
     *
     * Time Complexity: O(n)
     * - First loop: O(n) - iterate through both strings simultaneously
     * - Second loop: O(26) = O(1) - fixed iteration over 26 array elements
     * Total: O(n)
     *
     * Space Complexity: O(1)
     * - Fixed-size array of 26 integers regardless of input size
     * - Total: O(1) - constant space
    * */
    public boolean isAnagram1(String s, String t) {
        if (s.length()!=t.length()) return false;

        int[] arr = new int[26];
        char a = 'a';
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            arr[c1-a] = arr[c1-a]+1;
            arr[c2-a] = arr[c2-a]-1;
        }

        for (int i = 0; i < 26; i++) {
            if (arr[i]!=0) return false;
        }

        return true;
    }

    public static void main(String[] args) {

        _6_VaildAnagram obj = new _6_VaildAnagram();

        String input = "anagram";
        String input1 = "nagaram";
        System.out.println("Out : "+obj.isAnagram(input,input1));
        System.out.println("Out : "+obj.isAnagram1(input,input1));

        String input2 = "ggii";
        String input3 = "eekk";
        System.out.println("Out : "+obj.isAnagram(input2,input3));
        System.out.println("Out : "+obj.isAnagram1(input2,input3));

    }
}