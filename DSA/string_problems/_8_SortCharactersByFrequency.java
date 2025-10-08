package string_problems;

import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Problem Link : https://leetcode.com/problems/sort-characters-by-frequency/description/
 * Video Explanation : https://www.youtube.com/watch?v=pnxShVARLLw
 *
 * 451. Sort Characters By Frequency
 * Medium
 * Topics
 *
 * Given a string s, sort it in decreasing order based on the frequency of the characters.
 * The frequency of a character is the number of times it appears in the string.
 *
 * Return the sorted string. If there are multiple answers, return any of them.
 *
 * Example 1:
 * Input: s = "tree"
 * Output: "eert"
 * Explanation: 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 *
 * Example 2:
 * Input: s = "cccaaa"
 * Output: "aaaccc"
 * Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 *
 * Example 3:
 * Input: s = "Aabb"
 * Output: "bbAa"
 * Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 *
 * Constraints:
 *     1 <= s.length <= 5 * 105
 *     s consists of uppercase and lowercase English letters and digits.
 *
 */
public class _8_SortCharactersByFrequency {

    class Pair{
        char value;
        int frequency;
        Pair(char value,int frequency){
            this.value = value;
            this.frequency = frequency;
        }
    }
    /**
     * Solution 1 : .
     * Time Complexity: O(n + k log k)
     * - Building the frequency map: O(n) - iterate through all n characters
     * - Creating the Pair list: O(k) - create k Pair objects for k distinct characters
     * - Sorting the list: O(k log k) - sorting k elements using comparison-based sort
     * - Building the result: O(n) - append each of the n characters once
     *
     * Space Complexity: O(n + k)
     * - HashMap: O(k) - stores k distinct characters
     * - ArrayList (Pair list): O(k) - stores k Pair objects
     * - StringBuilder (result): O(n) - stores all n characters
    * */
    public String frequencySort(String s) {

        // store all the char and their frequency in a map.
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c,map.getOrDefault(c,0)+1);
        }

        // let's create an array to sort the char by frequency
        List<Pair> arrays = new ArrayList<>();

        for (char key:map.keySet()){
            arrays.add(new Pair(key,map.get(key)));
        }

        // sort the array by value.
        arrays.sort((o1, o2) -> Integer.compare(o2.frequency, o1.frequency));

        // Poll elements in sorted order
        StringBuilder result = new StringBuilder();
        for (Pair entry:arrays) {
            int count = entry.frequency;
            while (count>0){
                result.append(entry.value);
                count--;
            }
        }

        return result.toString();
    }

    /**
     * Solution 2 : Optimal.
     * Time Complexity: O(n + 123 log 123) = O(n)
     * - Building frequency array: O(n) - iterate through all n characters
     * - Sorting the array: O(123 log 123) - sorting a fixed-size array of 123 elements
     * - Building the result: O(n) - append each of the n characters once
     * - Since 123 is a constant, the sorting becomes O(1) in terms of input size!
     * Total: O(n)
     *
     * Space Complexity: O(1) (excluding output)
     * - Pair array: O(123) = O(1) - fixed size, independent of input
     * - StringBuilder (result): O(n) - this is the output, often excluded from space complexity analysis
     * - Total: O(1) auxiliary space, or O(n) if counting output.
    * */
    public String frequencySort1(String s) {
        Pair[] arr = new Pair[123];
        Arrays.fill(arr,new Pair('@',0));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int freq=arr[c].frequency+1;
            arr[c] = new Pair(c,freq);
        }
        Arrays.sort(arr,(o1, o2) -> Integer.compare(o2.frequency, o1.frequency));
        StringBuilder result = new StringBuilder();
        for (Pair entry:arr) {
            if (entry.value!='@'&&entry.frequency>0){
                int count = entry.frequency;
                while (count>0){
                    result.append(entry.value);
                    count--;
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        _8_SortCharactersByFrequency obj = new _8_SortCharactersByFrequency();

        String s = "ccdcababa";
        System.out.println("Out : "+obj.frequencySort(s));
        System.out.println("Out : "+obj.frequencySort1(s));

    }
}