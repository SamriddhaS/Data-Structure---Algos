package string_problems;

import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/remove-outermost-parentheses/description/
 * Video Explanation :
 *
 * 1021. Remove Outermost Parentheses
 * Solved
 * Easy
 * Topics
 *
 * A valid parentheses string is either empty "", "(" + A + ")", or A + B, where A and B are valid
 * parentheses strings, and + represents string concatenation.
 *
 *     For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
 *
 * A valid parentheses string s is primitive if it is nonempty, and there does not exist a
 * way to split it into s = A + B, with A and B nonempty valid parentheses strings.
 *
 * Given a valid parentheses string s, consider its primitive decomposition
 * : s = P1 + P2 + ... + Pk, where Pi are primitive valid parentheses strings.
 *
 * Return s after removing the outermost parentheses of every primitive string in the primitive
 * decomposition of s.
 *
 * Example 1:
 * Input: s = "(()())(())"
 * Output: "()()()"
 * Explanation:
 * The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
 * After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
 *
 * Example 2:
 * Input: s = "(()())(())(()(()))"
 * Output: "()()()()(())"
 * Explanation:
 * The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
 * After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
 *
 * Example 3:
 * Input: s = "()()"
 * Output: ""
 * Explanation:
 * The input string is "()()", with primitive decomposition "()" + "()".
 * After removing outer parentheses of each part, this is "" + "" = "".
 *
 * Constraints:
 *     1 <= s.length <= 105
 *     s[i] is either '(' or ')'.
 *     s is a valid parentheses string.
 *
 */
public class _1_RemoveOutermostParentheses {

    /**
     * Solution 1 (Stack-based)
     *
     * Time Complexity: O(n)
     * Single pass through string: O(n)
     * Stack push/pop operations: O(1) each
     * StringBuilder append: O(1) amortized
     *
     * Space Complexity: O(n)
     * Stack: O(n) worst case (all opening parentheses)
     * StringBuilder output: O(n) for result
     * Total: O(n)
    * */
    public String removeOuterParentheses(String s) {
        Stack<Character> stack=new Stack<>();
        StringBuilder output=new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c=='('){
                if (!stack.isEmpty()) output.append('(');
                stack.push(c);
            }else {
                stack.pop();
                if (!stack.isEmpty()) output.append(')');
            }
        }
        return output.toString();
    }

    /**
     * Solution 2 (Counter-based) - optimal
     *
     * Time Complexity: O(n)
     * toCharArray(): O(n)
     * Single pass through array: O(n)
     * StringBuilder append: O(1) amortized
     * Total: O(n)
     *
     * Space Complexity: O(1)
     * depth variable: O(1)
     * toCharArray(): O(n) creates char array
     * StringBuilder output: O(n) for result
     * Excluding output (which is required): O(n) due to char array
     *
    * */
    public String removeOuterParentheses1(String s) {
        int depth=0;
        StringBuilder output=new StringBuilder();
        for (char c : s.toCharArray())  {
            if (c=='('){
                if (depth!=0) output.append('(');
                depth++;
            }else {
                depth--;
                if (depth!=0) output.append(')');
            }
        }
        return output.toString();
    }

    public static void main(String[] args) {

        _1_RemoveOutermostParentheses obj = new _1_RemoveOutermostParentheses();

        String input = "(()())(())";
        System.out.println("Out : "+obj.removeOuterParentheses(input));
        System.out.println("Out : "+obj.removeOuterParentheses1(input));

        String input1 = "(()())(())(()(()))";
        System.out.println("Out : "+obj.removeOuterParentheses(input1));
        System.out.println("Out : "+obj.removeOuterParentheses1(input1));

        String input2 = "()()";
        System.out.println("Out : "+obj.removeOuterParentheses(input2));
        System.out.println("Out : "+obj.removeOuterParentheses1(input2));
    }
}