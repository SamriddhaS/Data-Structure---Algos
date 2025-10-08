package string_problems;

import java.util.*;

/**
 * Problem Link : https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/description/
 * Video Explanation :
 *
 * 1614. Maximum Nesting Depth of the Parentheses
 * Solved
 * Easy
 *
 * Given a valid parentheses string s, return the nesting depth of s. The nesting depth is
 * the maximum number of nested parentheses.
 *
 * Example 1:
 * Input: s = "(1+(2*3)+((8)/4))+1"
 * Output: 3
 *
 * Explanation:
 * Digit 8 is inside of 3 nested parentheses in the string.
 *
 * Example 2:
 * Input: s = "(1)+((2))+(((3)))"
 * Output: 3
 *
 * Explanation:
 * Digit 3 is inside of 3 nested parentheses in the string.
 *
 * Example 3:
 * Input: s = "()(())((()()))"
 * Output: 3
 *
 * Constraints:
 *     1 <= s.length <= 100
 *     s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
 *     It is guaranteed that parentheses expression s is a VPS.
 */
public class _9_MaximumNestingDepthOfTheParentheses {

    /**
     * Time Complexity: O(n)
     * - Single pass through the string
     * - Stack push/pop operations are O(1)
     *
     * Space Complexity: O(n)
     * - Stack can grow up to n/2 in worst case (all opening parentheses)
     * - Simplifies to O(n)
    * */
    public int maxDepth(String s) {
        Stack<Character> stack = new Stack<>();
        int depth = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c=='('){
                stack.push(c);
                depth = Math.max(depth,stack.size());
            } else if (c == ')' && !stack.isEmpty()) {
                stack.pop();
            }
        }
        return depth;
    }

    /**
     *
     * Time Complexity: O(n)
     * - Single pass through the string
     * - All operations are O(1)
     *
     * Space Complexity: O(1)
     * - Only uses two integer variables
     * - No additional data structures
     *
    * */
    public int maxDepth1(String s) {
        int maxDepth = 0;
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c=='('){
                counter++;
                maxDepth = Math.max(maxDepth,counter);
            } else if (c == ')' && counter>0) {
                counter--;
            }
        }
        return maxDepth;
    }

    public static void main(String[] args) {

        _9_MaximumNestingDepthOfTheParentheses obj = new _9_MaximumNestingDepthOfTheParentheses();

        String s = "(1+(2*3)+((8)/4))+1";
        System.out.println("Out : "+obj.maxDepth(s));
        System.out.println("Out : "+obj.maxDepth1(s));

        String s1 = "(1)+((2))+(((3)))";
        System.out.println("Out : "+obj.maxDepth(s1));
        System.out.println("Out : "+obj.maxDepth1(s1));

        String s2 = "()(())((()()))";
        System.out.println("Out : "+obj.maxDepth(s2));
        System.out.println("Out : "+obj.maxDepth1(s2));
    }
}