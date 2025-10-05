package string_problems;

import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/reverse-words-in-a-string/
 * Video Explanation :
 *
 * 151. Reverse Words in a String
 * Medium
 * Topics
 *
 * Given an input string s, reverse the order of the words.
 * A word is defined as a sequence of non-space characters. The words in s will be separated
 * by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words.
 * The returned string should only have a single space separating the words.
 * Do not include any extra spaces.
 *
 * Example 1:
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 *
 * Example 2:
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 *
 * Example 3:
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single
 * space in the reversed string.
 *
 * Constraints:
 *     1 <= s.length <= 104
 *     s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 *     There is at least one word in s.
 *
 * Follow-up: If the string data type is mutable in your language, can you solve
 * it in-place with O(1) extra space?
 *
 */
public class _2_ReverseWordsInAString {

    /**
     * Solution 1 : Somewhat brute force.
     * - Scan the string and construct the words and push them on a stack.
     * - then pop the stack one by one to reverse the words.
     * - add a hard coded " " in between two words.
     *
     * Time Complexity: O(n)
     *
     * Single pass through the string: O(n)
     * Building words character by character: O(n) total across all words
     * Popping from stack and building result: O(n)
     * Overall: O(n) where n is the length of the string
     *
     * Space Complexity: O(n)
     *
     * Stack stores all words: O(n) in total
     * wordBuilder: O(k) where k is the length of current word (reused, so not significant)
     * builder for final result: O(n)
     * Overall: O(n)
    * */
    public String reverseWords(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder wordBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c!=' '){
                wordBuilder.append(c);
            }else{
                String word = wordBuilder.toString();
                if (!word.isEmpty()){
                    stack.push(word);
                }
                wordBuilder = new StringBuilder();
            }
        }

        String word = wordBuilder.toString();
        if (!word.isEmpty()){
            stack.push(word);
        }

        StringBuilder builder = new StringBuilder();
        int size = stack.size();
        while(!stack.isEmpty()){
            builder.append(stack.pop());
            if(size>1) builder.append(" ");
            size--;
        }
        return builder.toString();
    }

    void reverse(char[] arr, int start, int end) {
        while(start<end){
            char temp = arr[end];
            arr[end] = arr[start];
            arr[start] = temp;
            start++;
            end--;
        }
    }

    /**
     *
     *  Solution 2 : In java this sol still has O(n) space complexity but in other
     * lang with mutable string support this is optimal solution.
     *
     * Time Complexity: O(n)
     * - Convert to char array: O(n) - copy each character
     * - Reverse entire string: O(n) - visit each character once
     * - Reverse each word: O(n) - each character visited once across all words
     * - Compact array (remove spaces): O(n) - scan through entire array once
     * - Total: O(n) + O(n) + O(n) + O(n) = O(4n) = O(n)
     *
     * We do approximately 4 passes through the data, but since constants
     * are dropped in Big O notation, it simplifies to O(n).
     *
     * Space Complexity:
     *
     * In Java: O(n) :
     * - char[] inputString = s.toCharArray();  // O(n) space
     * - return new String(inputString, 0, write);  // O(n) space for output
     * - Input string is immutable, so we must create a char array copy: O(n)
     * - Output string creation: O(n)
     * - Auxiliary variables (left, write, read, isFirstWord): O(1)
     * - Total: O(n) due to Java's string immutability
     *
     * In languages with mutable strings (C++, Python with bytearray): O(1)
     * - Only auxiliary variables needed: O(1)
     * - No need to create a copy of the input
     * - Modifications done directly on the input
     *
    * */
    public String reverseWords1(String s) {
        char[] inputString = s.toCharArray();

        //reverse the entire string
        reverse(inputString,0,inputString.length-1);

        //reverse the words to form correct words and keeping reversed seq.
        int left=0;
        for (int i = 0; i < inputString.length; i++) {
            char c = inputString[i];
            if (c==' '){
                reverse(inputString,left,i-1);
                left = i + 1;  // Move left to start of next word
            }
        }

        //for the last word
        if (left< inputString.length-1){
            reverse(inputString,left,inputString.length-1);
        }

        // let's remove the leading, trailing, multiple spaces
        int write=0;
        int read=0;
        boolean isFirstWord=true;
        while (read < inputString.length) {
            char c = inputString[read];
            if(c!=' '){

                if (!isFirstWord){
                    inputString[write++]=' ';
                }

                // Copy the entire word
                while (read < inputString.length && inputString[read] != ' ') {
                    inputString[write++] = inputString[read++];
                }

                isFirstWord=false;
            }
            read++;
        }

        return new String(inputString,0,write);
    }

    public static void main(String[] args) {

        _2_ReverseWordsInAString obj = new _2_ReverseWordsInAString();

        String input = "the sky is blue";
        System.out.println("Out : "+obj.reverseWords(input));
        System.out.println("Out : "+obj.reverseWords1(input));

        String input1 = "  hello world  ";
        System.out.println("Out : "+obj.reverseWords(input1));
        System.out.println("Out : "+obj.reverseWords1(input1));

        String input2 = "a good   example";
        System.out.println("Out : "+obj.reverseWords(input2));
        System.out.println("Out : "+obj.reverseWords1(input2));

    }
}