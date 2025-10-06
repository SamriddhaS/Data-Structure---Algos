package string_problems;

import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/largest-odd-number-in-string/submissions/1793051989/
 * Video Explanation :
 *
 * 1903. Largest Odd Number in String
 * Solved
 * Easy
 * Topics
 *
 * You are given a string num, representing a large integer. Return the largest-valued
 * odd integer (as a string) that is a non-empty substring of num, or an empty string "" if no
 * odd integer exists.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * Example 1:
 * Input: num = "52"
 * Output: "5"
 * Explanation: The only non-empty substrings are "5", "2", and "52". "5" is the only odd number.
 *
 * Example 2:
 * Input: num = "4206"
 * Output: ""
 * Explanation: There are no odd numbers in "4206".
 *
 * Example 3:
 * Input: num = "35427"
 * Output: "35427"
 * Explanation: "35427" is already an odd number.
 *
 * Constraints:
 *
 *     1 <= num.length <= 105
 *     num only consists of digits and does not contain any leading zeros.
 */
public class _2_LargestOddNumberInString {

    /**
    * Solution 1 : Brute force.
     * Generate each and every possible sub strings and keep checking for the
     * largest odd number.
     * However, this algo will not work for very large string : "239537672423884969653287101"
     * As the string exceeds the limit of a long.
    * */
    public String largestOddNumber(String num) {
        long largestOdd = 0;
        for (int i = 0; i <= num.length(); i++) {
            for (int j = i+1; j <= num.length(); j++) {
                String sub = num.substring(i,j);
                if(Long.parseLong(sub)%2==1){
                    largestOdd = Math.max(largestOdd,Long.parseLong(sub));
                }
            }
        }
        if (largestOdd==0) return "";
        else return String.valueOf(largestOdd);
    }

    /**
     *
     *  Time: O(n) - single pass through string in worst case
     * Space: O(1) - only stores index variable (substring return doesn't count as auxiliary space)
     *
     * Intuition :
     * A number is odd if and only if its last digit is odd. To get the largest odd substring,
     * we want the longest possible substring that ends with an odd digit. So scan from right to left,
     * find the first odd digit, and return everything from start to that position.
    * */
    public String largestOddNumber1(String num) {
        for (int i = num.length()-1; i >= 0; i--) {
            char c = num.charAt(i);
            if (Integer.valueOf(c)%2==1) return num.substring(0,i+1);
        }
        return "";
    }

    public static void main(String[] args) {

        _2_LargestOddNumberInString obj = new _2_LargestOddNumberInString();

        String input = "52";
        System.out.println("Out : "+obj.largestOddNumber(input));
        System.out.println("Out : "+obj.largestOddNumber1(input));

        String input1 = "4206";
        System.out.println("Out : "+obj.largestOddNumber(input1));
        System.out.println("Out : "+obj.largestOddNumber1(input1));

        String input2 = "35427";
        System.out.println("Out : "+obj.largestOddNumber(input2));
        System.out.println("Out : "+obj.largestOddNumber1(input2));

    }
}