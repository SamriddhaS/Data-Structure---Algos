package string_problems;

import java.util.HashMap;

/**
 * Problem Link : https://leetcode.com/problems/sum-of-beauty-of-all-substrings/description/
 * Video Explanation :
 *
 * 1781. Sum of Beauty of All Substrings
 * Medium
 * Topics
 *
 * The beauty of a string is the difference in frequencies between the most frequent and least
 * frequent characters.
 *
 *     For example, the beauty of "abaacc" is 3 - 1 = 2.
 *
 * Given a string s, return the sum of beauty of all of its substrings.
 *
 * Example 1:
 * Input: s = "aabcb"
 * Output: 5
 * Explanation: The substrings with non-zero beauty are ["aab","aabc","aabcb","abcb","bcb"],
 * each with beauty equal to 1.
 *
 * Example 2:
 * Input: s = "aabcbaa"
 * Output: 17
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 500
 *     s consists of only lowercase English letters.
 *
 */
public class _11_SumOfBeautyOfAllSubstrings {

    /**
     * Time Complexity: O(n² × k)
     * Where:
     * n = length of string
     * k = number of distinct characters (at most 26 for lowercase letters)
     * Breakdown:
     * Outer loop: O(n)
     * Inner loop: O(n)
     * Finding minimum frequency: O(k) for each substring
     * Total: O(n) × O(n) × O(k) = O(n² × 26) = O(n²) (since k is constant)
     *
     * Space Complexity: O(k) = O(1)
     *
     * HashMap stores at most 26 characters
     * A few integer variables
     * O(26) = O(1) constant space
    * */
    public int beautySum(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int sum=0;
        for (int i = 0; i < s.length(); i++) {
            map.clear();
            map.put(s.charAt(i),1);
            int currentMax=1;
            for (int j = i+1; j < s.length(); j++) {
                char c = s.charAt(j);
                int freq = map.getOrDefault(c,0)+1;
                map.put(c,freq);
                currentMax = Math.max(currentMax,freq);
                int currentMin=freq;
                if(map.size()>1){
                    // we should calculate the sub then.
                    for(char key: map.keySet()){
                        currentMin = Math.min(currentMin,map.get(key));
                    }
                    sum+=currentMax-currentMin;
                }
            }
        }
        return sum;
    }

    /**
     * Solution 2 : Using array instead of HashMap makes the programme faster
     * But no changes in time & space complexity.
     *
     * Time Complexity: O(n² × 26) = O(n²)
     * Still the same structure: nested loops + finding min frequency
     *
     * Space Complexity: O(26) = O(1)
    * */
    public int beautySum1(String s) {
        int sum=0;
        for (int i = 0; i < s.length(); i++) {
            int[] frequencyArr = new int[26];
            int maxFreq=1;
            for (int j = i; j < s.length(); j++) {
                int c = s.charAt(j)-'a'; // convert the char to integer
                int freq = frequencyArr[c]+1;
                frequencyArr[c] = freq;
                maxFreq = Math.max(maxFreq,freq);
                int minFreq=freq;
                // calculate the min frequency
                for(int val: frequencyArr){
                    if (val>0) minFreq = Math.min(minFreq,val);
                }
                int beauty = maxFreq-minFreq;
                sum+=beauty;
            }
        }
        return sum;
    }

    public static void main(String[] args) {

        _11_SumOfBeautyOfAllSubstrings obj = new _11_SumOfBeautyOfAllSubstrings();

        String s1 = "aabcbaa";
        System.out.println("Out : "+obj.beautySum(s1));
        System.out.println("Out : "+obj.beautySum1(s1));

        String s2 = "xzvfsppsjfbxdwkqe";
        System.out.println("Out : "+obj.beautySum(s2));
        System.out.println("Out : "+obj.beautySum1(s2));

    }
}