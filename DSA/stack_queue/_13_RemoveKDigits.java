package stack_queue;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/remove-k-digits/description/
 * Video Explanation : https://www.youtube.com/watch?v=cFabMOnJaq0
 *
 * 402. Remove K Digits
 * Given string num representing a non-negative integer num, and an integer k,
 * return the smallest possible integer after removing k digits from num.
 *
 * Example 1:
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 *
 * Example 2:
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 *
 * Example 3:
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 *
 * Constraints:
 *     1 <= k <= num.length <= 105
 *     num consists of only digits.
 *     num does not have any leading zeros except for the zero itself.
 *
 */
public class _13_RemoveKDigits {

    /**
     * Time Complexity: O(n)
     *
     * Main loop: O(n) - iterate through each character once
     * Inner while loop: O(n) amortized - each character is pushed once and popped at most once across all iterations
     * Result building: O(n) - pop from stack and append to StringBuilder
     * Overall: O(n) where n = length of input string
     *
     *
     * Space Complexity: O(n)
     *
     * Stack: O(n) worst case - when no digits are removed (e.g., already ascending sequence)
     * StringBuilder: O(n) for building the final result
     * Overall: O(n)
    * */
    public String removeKdigits(String num, int k) {

        if(num.length()==k) return "0"; // edge case

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            // try to maintain ascending monotonic order stack to make sure
            // we are crating lowest possible value.
            if (stack.isEmpty() || (int) stack.peek() <= (int) c ){
                // don't push 0 if we don't have a char before it.
                if (!(stack.isEmpty() && c=='0')) stack.push(c);
            }else{

                // If we encounter a value that is lower than our current top then
                // we can remove the current top as we are certain removing the current
                // top will result to create a lower value.
                while (k>0 && !stack.isEmpty() && (int) stack.peek() > (int) c) {
                    stack.pop();
                    k--;
                }

                // don't push 0 if we don't have a char before it.
                if (!(stack.isEmpty() && c=='0')) stack.push(c);
            }
        }

        StringBuilder builder = new StringBuilder();
        while(!stack.isEmpty()){
            char c = stack.pop();
            if (k>0){
                // k is > 0  that means the char are already in ascending order, so
                // we can simply remove the last k elements to get the result.
                k--;
                continue;
            }
            builder.append(c);  // Insert at position 0 (beginning)
        }
        String result = builder.reverse().toString();
        if(result.isEmpty()) return "0";
        else return result;
    }

    public static void main(String[] args) {
        _13_RemoveKDigits obj = new _13_RemoveKDigits();

        String input = "1432219";
        int k = 3;
        System.out.println("The Answer : " + obj.removeKdigits(input,k));

        String input1 = "10200";
        int k1 = 1;
        System.out.println("The Answer : " + obj.removeKdigits(input1,k1));

        String input2 = "9";
        int k2 = 1;
        System.out.println("The Answer : " + obj.removeKdigits(input2,k2));

        String input3 = "112";
        int k3 = 1;
        System.out.println("The Answer : " + obj.removeKdigits(input3,k3));

        String input4 = "10001";
        int k4 = 1;
        System.out.println("The Answer : " + obj.removeKdigits(input4,k4));

        String input5 = "33526221184202197273";
        int k5 = 19;
        System.out.println("The Answer : " + obj.removeKdigits(input5,k5));

        String input6 = "9999";
        int k6 = 2;
        System.out.println("The Answer : " + obj.removeKdigits(input6,k6));

        String input7 = "12345";
        int k7 = 2;
        System.out.println("The Answer : " + obj.removeKdigits(input7,k7));
    }
}