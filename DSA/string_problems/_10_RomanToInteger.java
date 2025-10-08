package string_problems;

import java.util.HashMap;

/**
 * Problem Link : https://leetcode.com/problems/roman-to-integer/description/
 * Video Explanation : https://www.youtube.com/watch?v=3jdxYj3DD98
 *
 * 13. Roman to Integer
 * Solved
 * Easy
 * Topics
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII,
 * which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX.
 *
 * There are six instances where subtraction is used:
 *     I can be placed before V (5) and X (10) to make 4 and 9.
 *     X can be placed before L (50) and C (100) to make 40 and 90.
 *     C can be placed before D (500) and M (1000) to make 400 and 900.
 *
 * Given a roman numeral, convert it to an integer.
 *
 * Example 1:
 * Input: s = "III"
 * Output: 3
 * Explanation: III = 3.
 *
 * Example 2:
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 *
 * Example 3:
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 * Constraints:
 *     1 <= s.length <= 15
 *     s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
 *     It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 */
public class _10_RomanToInteger {

    /**
     * Time Complexity: O(n)
     * - Single pass through the string (n = length of string)
     * - HashMap lookups are O(1)
     * - The continue statement skips already processed characters
     *
     * Space Complexity: O(1)
     * - HashMap stores fixed 13 key-value pairs (constant space)
     * - Few variables for tracking (res, i, c, nc)
    * */
    public int romanToInt(String s) {

        //I can be placed before V (5) and X (10) to make 4 and 9.
        //X can be placed before L (50) and C (100) to make 40 and 90.
        //C can be placed before D (500) and M (1000) to make 400 and 900.
        HashMap<String,Integer> map = new HashMap<>();
        map.put("I",1);
        map.put("IV",4);
        map.put("V",5);
        map.put("IX",9);
        map.put("X",10);
        map.put("XL",40);
        map.put("L",50);
        map.put("XC",90);
        map.put("C",100);
        map.put("CD",400);
        map.put("D",500);
        map.put("CM",900);
        map.put("M",1000);
        int res=0;
        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if (i<=s.length()-2){
                String nc = String.valueOf(s.charAt(i+1));
                if ((c.equals("I") && (nc.equals("V") || nc.equals("X")))
                    || (c.equals("X") && (nc.equals("L") || nc.equals("C")))
                        || (c.equals("C") && (nc.equals("D") || nc.equals("M")))
                ){
                    String key = c+nc;
                    res+=map.get(key);
                    i++;
                    continue;
                }
            }
            res+=map.get(c);
        }
        return res;
    }


    /**
     * We can actually if the next char is larger than the current char, if yes
     * then subtract the current character. This way we can avoid to add the exception 6 more
     * digits entries to our map.
     *
     * Solution 2 :
     * Same time & space complexity
    * */
    public int romanToInt2(String s) {
        int res=0;
        HashMap<String,Integer> map = new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            if (i+1<s.length()&&map.get(c)<map.get(String.valueOf(s.charAt(i+1)))){
                res-=map.get(c);
            }else{
                res+=map.get(c);
            }
        }
        return res;
    }

    public static void main(String[] args) {

        _10_RomanToInteger obj = new _10_RomanToInteger();

        String s = "XXVII";
        System.out.println("Out : "+obj.romanToInt(s));
        System.out.println("Out : "+obj.romanToInt2(s));

        String s1 = "LVIII";
        System.out.println("Out : "+obj.romanToInt(s1));
        System.out.println("Out : "+obj.romanToInt2(s1));

        String s2 = "MCMXCIV";
        System.out.println("Out : "+obj.romanToInt(s2));
        System.out.println("Out : "+obj.romanToInt2(s2));
    }
}