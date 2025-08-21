package DSA.recursion;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Problem Link : https://leetcode.com/problems/string-to-integer-atoi/description/
 * Explanation : https://www.youtube.com/watch?v=Tz7z0JOLCHs
 * https://www.youtube.com/watch?v=ne_Ki_sdRSk
 *
 * String to Integer (atoi)
 *
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
 *
 * The algorithm for myAtoi(string s) is as follows:
 *
 *     Whitespace: Ignore any leading whitespace (" ").
 *     Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity
 *     if neither present.
 *     Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered
 *     or the end of the string is reached. If no digits were read, then the result is 0.
 *     Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the
 *     integer to remain in the range. Specifically, integers less than -231 should be rounded to -231,
 *     and integers greater than 231 - 1 should be rounded to 231 - 1.
 *
 * Return the integer as the final result.
 *
 *
 * Example 1:
 *
 * Input: s = "42"
 *
 * Output: 42
 *
 * Explanation:
 *
 * The underlined characters are what is read in and the caret is the current reader position.
 * Step 1: "42" (no characters read because there is no leading whitespace)
 *          ^
 * Step 2: "42" (no characters read because there is neither a '-' nor '+')
 *          ^
 * Step 3: "42" ("42" is read in)
 *            ^
 *
 * Example 2:
 *
 * Input: s = " -042"
 *
 * Output: -42
 *
 * Explanation:
 *
 * Step 1: "   -042" (leading whitespace is read and ignored)
 *             ^
 * Step 2: "   -042" ('-' is read, so the result should be negative)
 *              ^
 * Step 3: "   -042" ("042" is read in, leading zeros ignored in the result)
 *                ^
 *
 * Example 3:
 *
 * Input: s = "1337c0d3"
 *
 * Output: 1337
 *
 * Explanation:
 *
 * Step 1: "1337c0d3" (no characters read because there is no leading whitespace)
 *          ^
 * Step 2: "1337c0d3" (no characters read because there is neither a '-' nor '+')
 *          ^
 * Step 3: "1337c0d3" ("1337" is read in; reading stops because the next character is a non-digit)
 *              ^
 *
 * Example 4:
 *
 * Input: s = "0-1"
 *
 * Output: 0
 *
 * Explanation:
 *
 * Step 1: "0-1" (no characters read because there is no leading whitespace)
 *          ^
 * Step 2: "0-1" (no characters read because there is neither a '-' nor '+')
 *          ^
 * Step 3: "0-1" ("0" is read in; reading stops because the next character is a non-digit)
 *           ^
 *
 * Example 5:
 *
 * Input: s = "words and 987"
 *
 * Output: 0
 *
 * Explanation:
 *
 * Reading stops at the first non-digit character 'w'.
 *
 *
 *
 * Constraints:
 *
 *     0 <= s.length <= 200
 *     s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 *
 *
* */
public class _1_StringToInt {

    public int myAtoi(String s) {
        if (s==null||s=="") return 0;

        boolean isNegative = false;
        long answer = Long.MIN_VALUE;

        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);

            // check for empty space
            if(answer==Long.MIN_VALUE && c==' '){
                continue;
            }

            // check for negative sign
            if (answer==Long.MIN_VALUE && c=='-'){
                isNegative = true;
                answer = 0;
                continue;
            }

            if (answer==Long.MIN_VALUE && c=='+'){
                isNegative = false;
                answer = 0;
                continue;
            }

            // check if number
            if(c >= '0' && c<='9'){
                int number = c - '0'; // convert to number from string.
                answer = answer*10 + number;

                // check if integer is going beyond int limit.
                // TODO : Need to do this without help of Integer class.
                if(isNegative){
                    long val = -answer;
                    if (val<Integer.MIN_VALUE) return Integer.MIN_VALUE;
                }else {
                    if (answer>Integer.MAX_VALUE) return Integer.MAX_VALUE;
                }
            }else {
                break;
            }
        }

        if (answer==Long.MIN_VALUE) return 0;

        if (isNegative) return (int) -answer;
        return (int) answer;
    }

    public int myAtoiRecursiveFun(String s, int index, long result, int sign, boolean hasStarted){

        if (index==s.length()){
            return (int)result*sign;
        }

        char character = s.charAt(index);

        if (!hasStarted && character==' '){
            return myAtoiRecursiveFun(s,index+1,result,sign,false);
        }

        if (!hasStarted && (character=='-'||character=='+')){
            int newSign = (character == '-') ? -1 : 1;
            return myAtoiRecursiveFun(s,index+1,result,newSign,true);
        }

        if (character >= '0' && character <= '9') {
            int digit = character - '0';

            if (result > Integer.MAX_VALUE / 10 ||
                    (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            int newResult = (int) result * 10 + digit;
            return myAtoiRecursiveFun(s, index + 1, newResult, sign, true);
        }

        if (!hasStarted) {
            return 0;
        } else {
            return (int) result * sign;
        }
    }

    /**
     * Time Complexity: O(n)
     * Where n is the length of the input string.
     * Reasoning:
     *
     * The function processes each character in the string at most once
     * In each recursive call, the index increments by 1
     * The function terminates when index == s.length() or when a non-digit character is encountered after processing has started
     * Even in the worst case (processing the entire string), we make exactly n recursive calls
     * Each recursive call performs constant time operations (O(1))
     *
     * Space Complexity: O(n)
     * Where n is the length of the input string.
     * Reasoning:
     *
     * This is due to the call stack depth from recursion
     * In the worst case, the function will recurse through the entire string before returning
     * Each recursive call adds a new frame to the call stack
     * Maximum call stack depth = length of the string = n
     * Each stack frame stores constant space (parameters: s, index, result, sign, hasStarted)
    * */
    public int myAtoiRecursive(String str, int n)
    {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        return myAtoiRecursiveFun(str, 0, 0, 1, false);
    }

    public static void main(String[] args) {
        _1_StringToInt solution = new _1_StringToInt();

        // Test Case 1: Basic positive number (Example 1)
        testCase(solution, "42", 42, "Basic positive number");

        // Test Case 2: Negative number with leading whitespace and zeros (Example 2)
        testCase(solution, " -042", -42, "Negative with leading whitespace and zeros");

        // Test Case 3: Number followed by non-digits (Example 3)
        testCase(solution, "1337c0d3", 1337, "Number followed by non-digits");

        // Test Case 4: Number followed by special characters (Example 4)
        testCase(solution, "0-1", 0, "Number followed by special characters");

        // Test Case 5: String starting with letters (Example 5)
        testCase(solution, "words and 987", 0, "String starting with letters");

        // Test Case 6: Empty string
        testCase(solution, "", 0, "Empty string");

        // Test Case 7: Only whitespace
        testCase(solution, "   ", 0, "Only whitespace");

        // Test Case 8: Only positive sign
        testCase(solution, "+", 0, "Only positive sign");

        // Test Case 9: Only negative sign
        testCase(solution, "-", 0, "Only negative sign");

        // Test Case 10: Positive number with + sign
        testCase(solution, "+123", 123, "Positive number with + sign");

        // Test Case 11: Negative number
        testCase(solution, "-123", -123, "Negative number");

        // Test Case 12: Leading zeros
        testCase(solution, "000123", 123, "Leading zeros");

        // Test Case 13: Only zeros
        testCase(solution, "000", 0, "Only zeros");

        // Test Case 14: Integer overflow (positive)
        testCase(solution, "2147483648", Integer.MAX_VALUE, "Integer overflow positive");

        // Test Case 15: Integer overflow (negative)
        testCase(solution, "-2147483649", Integer.MIN_VALUE, "Integer overflow negative");

        // Test Case 16: Maximum valid positive integer
        testCase(solution, "2147483647", 2147483647, "Maximum valid positive integer");

        // Test Case 17: Minimum valid negative integer
        testCase(solution, "-2147483648", -2147483648, "Minimum valid negative integer");

        // Test Case 18: Very large overflow
        testCase(solution, "9999999999999999999", Integer.MAX_VALUE, "Very large overflow");

        // Test Case 19: Whitespace with sign and number
        testCase(solution, "   +123", 123, "Whitespace with positive sign");

        // Test Case 20: Whitespace with negative sign and number
        testCase(solution, "   -456", -456, "Whitespace with negative sign");

        // Test Case 21: Multiple signs (should stop at second sign)
        testCase(solution, "+-12", 0, "Multiple signs");

        // Test Case 22: Sign after number started
        testCase(solution, "12+34", 12, "Sign after number started");

        // Test Case 23: Decimal point
        testCase(solution, "123.45", 123, "Decimal point");

        // Test Case 24: Leading whitespace with special characters
        testCase(solution, "   abc", 0, "Leading whitespace with letters");

        // Test Case 25: Single digit
        testCase(solution, "5", 5, "Single digit");

        System.out.println("\nAll test cases completed!");
    }

    private static void testCase(_1_StringToInt solution, String input, int expected, String description) {
        int result = solution.myAtoiRecursive(input,input.length());
        String status = (result == expected) ? "PASS" : "FAIL";
        System.out.printf("%-40s | Input: %-20s | Expected: %-12d | Got: %-12d | %s%n",
                description, "\"" + input + "\"", expected, result, status);

        if (result != expected) {
            System.out.println("  ERROR: Expected " + expected + " but got " + result);
        }
    }

}
