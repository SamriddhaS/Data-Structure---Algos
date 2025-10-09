package string_problems;

import java.util.HashMap;

/**
 * Problem Link : https://leetcode.com/problems/string-to-integer-atoi/description/
 * Video Explanation :
 *
 * 8. String to Integer (atoi)
 * Solved
 * Medium
 *
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
 * The algorithm for myAtoi(string s) is as follows:
 *     Whitespace: Ignore any leading whitespace (" ").
 *     Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if neither present.
 *     Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, then the result is 0.
 *     Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the integer to remain in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater than 231 - 1 should be rounded to 231 - 1.
 *
 * Return the integer as the final result.
 *
 * Example 1:
 * Input: s = "42"
 * Output: 42
 * Explanation:
 * The underlined characters are what is read in and the caret is the current reader position.
 * Step 1: "42" (no characters read because there is no leading whitespace)
 *          ^
 * Step 2: "42" (no characters read because there is neither a '-' nor '+')
 *          ^
 * Step 3: "42" ("42" is read in)
 *            ^
 *
 * Example 2:
 * Input: s = " -042"
 * Output: -42
 * Explanation:
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
 * Output: 1337
 * Explanation:
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
 * Output: 0
 * Explanation:
 * Step 1: "0-1" (no characters read because there is no leading whitespace)
 *          ^
 * Step 2: "0-1" (no characters read because there is neither a '-' nor '+')
 *          ^
 * Step 3: "0-1" ("0" is read in; reading stops because the next character is a non-digit)
 *           ^
 *
 * Example 5:
 * Input: s = "words and 987"
 * Output: 0
 * Explanation:
 * Reading stops at the first non-digit character 'w'.
 *
 * Constraints:
 *     0 <= s.length <= 200
 *     s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 *
 */
public class _10_Atoi {

    /**
     * Solution 1 :
     * Time Complexity: O(n)
     * - Single pass through the string
     * - Each character is processed once with constant-time operations (comparisons, arithmetic)
     * - Where n = length of input string
     *
     * Space Complexity: O(1)
     * - Uses only a fixed number of variables (booleans, long, int) regardless of input size
     * - No additional data structures allocated
     *
    * */
    public int myAtoi(String s) {
        boolean isFirstChar=true;
        boolean isNegative=false;
        boolean signCheckingDone=false;
        long result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isFirstChar && !signCheckingDone && c==' ') continue;
            else{
                if (!signCheckingDone && (c=='-'||c=='+')){
                    isNegative = c=='-';
                    signCheckingDone=true;
                    continue;
                }

                if (isFirstChar && c=='0'){
                    signCheckingDone=true;
                    continue;
                }

                if (c<='9' && c>='0'){
                    if (isNegative) {
                        int num = (c - '0')*(-1);
                        result = (result * 10 + num );
                    }else {
                        result = (result * 10 + (c - '0'));
                    }
                    if (result<Integer.MIN_VALUE) return Integer.MIN_VALUE;
                    if (result>Integer.MAX_VALUE) return Integer.MAX_VALUE;
                }else {
                    break;
                }
                signCheckingDone=true;
                isFirstChar=false;
            }
        }
        return (int) result;
    }

    /**
     * Solution 2 :
     * - Same intuition & solution but code is a bit more clear and readable.
     * - Same time & space complexity.
    * */
    public int myAtoi1(String s) {
        int sign=1;
        long result = 0;
        int index=0;

        //check for leading white spaces
        while(index<s.length()&&s.charAt(index)==' '){
            index++;
        }

        //check for + or -  signs
        if (index<s.length()&&(s.charAt(index)=='-' || s.charAt(index)=='+')){
            sign = s.charAt(index)=='-' ? -1 : 1;
            index++;
        }

        //check for leading 0's
        while(index<s.length()&&s.charAt(index)=='0'){
            index++;
        }

        // Now check for valid digits and build the result integer.
        while(index<s.length()&& s.charAt(index)>='0' && s.charAt(index)<='9'){
            int c = s.charAt(index)-'0';
            c = c * sign;
            result = (result*10) + c;
            index++;

            //edge cases
            if (result>Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (result<Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }

        return (int) result;
    }

    public static void main(String[] args) {

        _10_Atoi obj = new _10_Atoi();

        String s = "   -042";
        System.out.println("Out : "+obj.myAtoi(s));
        System.out.println("Out : "+obj.myAtoi1(s));

        String s1 = "4193 with words";
        System.out.println("Out : "+obj.myAtoi(s1));
        System.out.println("Out : "+obj.myAtoi1(s1));

        String s2 = "-91283472332";
        System.out.println("Out : "+obj.myAtoi(s2));
        System.out.println("Out : "+obj.myAtoi1(s2));

        String s3 = "00123";
        System.out.println("Out : "+obj.myAtoi(s3));
        System.out.println("Out : "+obj.myAtoi1(s3));
    }
}