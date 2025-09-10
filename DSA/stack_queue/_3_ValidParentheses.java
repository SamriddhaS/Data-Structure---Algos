package stack_queue;

import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/valid-parentheses/description/
 * Video Explanation :
 *
 *  20. Valid Parentheses
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 *
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "()[]{}"
 *
 * Output: true
 *
 * Example 3:
 *
 * Input: s = "(]"
 *
 * Output: false
 *
 * Example 4:
 *
 * Input: s = "([])"
 *
 * Output: true
 *
 * Example 5:
 *
 * Input: s = "([)]"
 *
 * Output: false
 *
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 *
* */
public class _3_ValidParentheses {


    /**
     * Initial solution.
     *
     * Time Complexity: O(n)
     * - Single pass through the string of length n
     * - Each character is processed once with O(1) stack operations
     *
     * Space Complexity: O(n)
     * - In worst case (all opening brackets), stack stores all n characters
     * - Best case O(1) when brackets are properly matched and popped immediately
     *
    * */
    public boolean isValid(String s) {
        Stack<Character> stack1 = new Stack<>();
        for(int i=0;i<s.length();i++){
            Character currentChar = s.charAt(i);
            if (!stack1.isEmpty()&&(currentChar==')'||currentChar=='}'||currentChar==']')){
                Character top = stack1.peek();
                if (top=='('&&currentChar == ')') stack1.pop();
                else if (top=='{'&&currentChar == '}') stack1.pop();
                else if (top=='['&&currentChar == ']') stack1.pop();
                else stack1.push(currentChar);
            }
            else stack1.push(currentChar);
        }
        return stack1.isEmpty();
    }

    /**
    * Same logic but with cleaner code.
    * */
    public boolean isValid2(String s) {
        Stack<Character> stack1 = new Stack<>();
        for(char c:s.toCharArray()){
            if (c=='(') stack1.push(')');
            else if (c=='{') stack1.push('}');
            else if (c=='[') stack1.push(']');
            else {
                if (stack1.isEmpty()) return false;
                char top = stack1.peek();
                if (top==c) stack1.pop();
                else return false;
            }
        }
        return stack1.isEmpty();
    }


    public static void main(String[] args) {
        _3_ValidParentheses object = new _3_ValidParentheses();
        String s = "()[]{}";
        String s1 = "({[)";
        String s2 = "(])";
        System.out.println("Valid Parenthesis 0 : "+object.isValid(s));
        System.out.println("Valid Parenthesis 0 : "+object.isValid(s1));
        System.out.println("Valid Parenthesis 0 : "+object.isValid(s2));

        System.out.println("Valid Parenthesis 1 : "+object.isValid2(s));
        System.out.println("Valid Parenthesis 1 : "+object.isValid2(s1));
        System.out.println("Valid Parenthesis 1 : "+object.isValid2(s2));

    }
}
