package string_problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

/**
 * Problem Link : https://leetcode.com/problems/rotate-string/description/
 * Video Explanation : https://www.youtube.com/watch?v=6fFWixNh4rg
 *
 * 796. Rotate String
 * Easy
 * Topics
 *
 * Given two strings s and goal, return true if and only if s can become goal after
 * some number of shifts on s.
 *
 * A shift on s consists of moving the leftmost character of s to the rightmost position.
 *
 *     For example, if s = "abcde", then it will be "bcdea" after one shift.
 *
 * Example 1:
 * Input: s = "abcde", goal = "cdeab"
 * Output: true
 *
 * Example 2:
 * Input: s = "abcde", goal = "abced"
 * Output: false
 *
 * Constraints:
 *     1 <= s.length, goal.length <= 100
 *     s and goal consist of lowercase English letters.
 *
 */
public class _7_RotateString {

    /**
     * Solution 1 : Brute Force
     *
     * Time Complexity: O(n²)
     * Outer loop: O(n) iterations
     * Inner loop: O(n) iterations for each outer loop
     * Total: O(n) × O(n) = O(n²)
     *
     * Space Complexity: O(1)
     *
     * Only using a constant amount of extra variables (i, j, g, possible, index)
     * No additional data structures that scale with input size
    * */
    public boolean rotateString(String s, String goal) {
        if(s.length()!=goal.length()) return false;
        for (int i = 0; i <= s.length(); i++) {
            int g = 0;
            boolean possible = true;
            for (int j = i; j < i+s.length(); j++) {
                int index = j%s.length();
                if (s.charAt(index)!=goal.charAt(g)) {
                    possible = false;
                }
                g++;
            }
            if (possible) return true;
        }
        return false;
    }

    /**
    * Solution 2 : Better solution in average case. But uses extra space and also same
     * worst time complexity.
     *
     * Time Complexity:
     * Worst case: Still O(n²) - if all characters in s match goal.charAt(0), you check all n positions
     * Best case: Better than original - O(n) if first match succeeds
     * Average case: Potentially better - only checks promising starting positions
     *
     * Space Complexity:
     * O(n) vs O(1) - uses HashSet that could store up to n indices in worst case
     *
     * Trade-off:
     * Sacrifices space for potential time improvement in average cases
     * No improvement in worst-case time complexity
     * Not worth it - the space overhead doesn't justify the limited optimization
     *
    * */
    public boolean rotateString1(String s, String goal) {
        if(s.length()!=goal.length()) return false;
        HashSet<Integer> startingLetterMap = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)==goal.charAt(0)){
                startingLetterMap.add(i);
            }
        }

        for(int indices:startingLetterMap){
            int g = 0;
            boolean possible = true;
            for (int j = indices; j < indices+s.length(); j++) {
                int index = j%s.length();
                if (s.charAt(index)!=goal.charAt(g)) {
                    possible = false;
                }
                g++;
            }
            if (possible) return possible;
        }

        return false;
    }


    /**
     * If allowed to use library function.
     * Trick To Know : If we concat a string with itself again it will have all the possible substrings
     * within that concatinated string.
     *
     * Time Complexity: O(n)
     * - Creating s + s: O(n) - concatenates two strings of length n
     * - contains(goal): O(n) average case with optimized algorithm (e.g., KMP)
     * Total: O(n)
     *
     * Space Complexity: O(n)
     * - s + s creates a new string of length 2n = O(n)
     * - No other significant space used
     *
     * This is the most efficient solution among all three you've shown - linear time and space.
    * */
    public boolean rotateString2(String s, String goal) {
        if(s.length()!=goal.length()) return false;
        return (s+s).contains(goal);
    }



    public static void main(String[] args) {

        _7_RotateString obj = new _7_RotateString();

        String s = "abcde", goal = "cdeab";
        System.out.println("Out : "+obj.rotateString(s,goal));
        System.out.println("Out : "+obj.rotateString1(s,goal));
        System.out.println("Out : "+obj.rotateString2(s,goal));

        String s1 = "abcde", goal1 = "abced";
        System.out.println("Out : "+obj.rotateString(s1,goal1));
        System.out.println("Out : "+obj.rotateString1(s1,goal1));
        System.out.println("Out : "+obj.rotateString2(s1,goal1));

        String s2 = "aa", goal2 = "a";
        System.out.println("Out : "+obj.rotateString(s2,goal2));
        System.out.println("Out : "+obj.rotateString1(s2,goal2));
        System.out.println("Out : "+obj.rotateString2(s2,goal2));

        String s3 = "defdefdefabcabc", goal3 = "defdefabcabcdef";
        System.out.println("Out : "+obj.rotateString(s3,goal3));
        System.out.println("Out : "+obj.rotateString1(s3,goal3));
        System.out.println("Out : "+obj.rotateString2(s3,goal3));

    }
}