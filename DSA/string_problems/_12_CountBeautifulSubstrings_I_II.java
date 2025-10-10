package string_problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Problem Link :
 * 1.(medium) https://leetcode.com/problems/count-beautiful-substrings-i/
 * 2.(hard) https://leetcode.com/problems/count-beautiful-substrings-ii/
 * Video Explanation : https://www.youtube.com/watch?v=Th4jtNqzyEc&t=93s
 * Topic : TString,TPrefixSum
 *
 * 2947. Count Beautiful Substrings I
 * Medium
 * Topics
 *
 * You are given a string s and a positive integer k.
 * Let vowels and consonants be the number of vowels and consonants in a string.
 * A string is beautiful if:
 *     vowels == consonants.
 *     (vowels * consonants) % k == 0, in other terms the multiplication of vowels and consonants is divisible by k.
 *
 * Return the number of non-empty beautiful substrings in the given string s.
 * A substring is a contiguous sequence of characters in a string.
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 * Consonant letters in English are every letter except vowels.
 *
 * Example 1:
 * Input: s = "baeyh", k = 2
 * Output: 2
 * Explanation: There are 2 beautiful substrings in the given string.
 * - Substring "baeyh", vowels = 2 (["a",e"]), consonants = 2 (["y","h"]).
 * You can see that string "aeyh" is beautiful as vowels == consonants and vowels * consonants % k == 0.
 * - Substring "baeyh", vowels = 2 (["a",e"]), consonants = 2 (["b","y"]).
 * You can see that string "baey" is beautiful as vowels == consonants and vowels * consonants % k == 0.
 * It can be shown that there are only 2 beautiful substrings in the given string.
 *
 * Example 2:
 * Input: s = "abba", k = 1
 * Output: 3
 * Explanation: There are 3 beautiful substrings in the given string.
 * - Substring "abba", vowels = 1 (["a"]), consonants = 1 (["b"]).
 * - Substring "abba", vowels = 1 (["a"]), consonants = 1 (["b"]).
 * - Substring "abba", vowels = 2 (["a","a"]), consonants = 2 (["b","b"]).
 * It can be shown that there are only 3 beautiful substrings in the given string.
 *
 * Example 3:
 * Input: s = "bcdf", k = 1
 * Output: 0
 * Explanation: There are no beautiful substrings in the given string.
 *
 * Constraints:
 *     1 <= s.length <= 1000
 *     1 <= k <= 1000
 *     s consists of only English lowercase letters.
 *
 *
 *
 * 2949. Count Beautiful Substrings II
 * Solved
 * Hard
 * Topics
 *
 * You are given a string s and a positive integer k.
 * Let vowels and consonants be the number of vowels and consonants in a string.
 * A string is beautiful if:
 *     vowels == consonants.
 *     (vowels * consonants) % k == 0, in other terms the multiplication of vowels and consonants is divisible by k.
 *
 * Return the number of non-empty beautiful substrings in the given string s.
 * A substring is a contiguous sequence of characters in a string.
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 * Consonant letters in English are every letter except vowels.
 *
 * Example 1:
 * Input: s = "baeyh", k = 2
 * Output: 2
 * Explanation: There are 2 beautiful substrings in the given string.
 * - Substring "baeyh", vowels = 2 (["a",e"]), consonants = 2 (["y","h"]).
 * You can see that string "aeyh" is beautiful as vowels == consonants and vowels * consonants % k == 0.
 * - Substring "baeyh", vowels = 2 (["a",e"]), consonants = 2 (["b","y"]).
 * You can see that string "baey" is beautiful as vowels == consonants and vowels * consonants % k == 0.
 * It can be shown that there are only 2 beautiful substrings in the given string.
 *
 * Example 2:
 * Input: s = "abba", k = 1
 * Output: 3
 * Explanation: There are 3 beautiful substrings in the given string.
 * - Substring "abba", vowels = 1 (["a"]), consonants = 1 (["b"]).
 * - Substring "abba", vowels = 1 (["a"]), consonants = 1 (["b"]).
 * - Substring "abba", vowels = 2 (["a","a"]), consonants = 2 (["b","b"]).
 * It can be shown that there are only 3 beautiful substrings in the given string.
 *
 * Example 3:
 * Input: s = "bcdf", k = 1
 * Output: 0
 * Explanation: There are no beautiful substrings in the given string.
 *
 * Constraints:
 *
 *     1 <= s.length <= 5 * 104
 *     1 <= k <= 1000
 *     s consists of only English lowercase letters.
 *
 */
public class _12_CountBeautifulSubstrings_I_II {

    boolean isVowel(char c){
        return c=='a'||c=='e'||c=='i'||c=='o'||c=='u';
    }

    private boolean isBeautiful(int vowel,int consonant,int k){
        return (vowel==consonant) && ((vowel * consonant) % k == 0);
    }

    /**
     * Solution 1 : Brute Force
     *
     * Time Complexity: O(n²)
     * - Nested loops iterate through all possible substrings
     * - For each starting position i, checks all ending positions j
     * - HashSet lookup is O(1)
     *
     * Space Complexity: O(1)
     * - Only uses a fixed-size HashSet (5 vowels) and a few variables
     * - No additional space that grows with input
    * */
    public int beautifulSubstrings(String s, int k) {
        int numOfSubstrings=0;
        for (int i = 0; i < s.length(); i++) {
            int numOfVowel=0;
            int numOfCons=0;
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                if (isVowel(c)) numOfVowel++;
                else numOfCons++;
                if (isBeautiful(numOfVowel,numOfCons,k)) numOfSubstrings++;
            }
        }
        return numOfSubstrings;
    }

    /**
    * Solution 2 : Optimized with Prefix Sum
     *
     * Time Complexity: O(n × m) where m is the number of distinct vowel counts (mod k)
     * First loop: O(n) to build countMap
     * Second loop: O(n) iterations, but for each iteration:
     * Iterates through all previous vowel counts in the map
     * In worst case, m can be up to k (distinct remainders mod k)
     * Overall: O(n × k) in worst case
     *
     * Space Complexity: O(n × k)
     * countMap: O(n) - stores 3 values per character
     * prefixSumMap: O(n × k) worst case
     * Can have up to n distinct sums
     * Each sum can map to up to k distinct vowel count remainders
     *
     * Dominates to O(n × k)
    * */
    public long beautifulSubstrings1(String s, int k) {
        int numOfSubstrings=0;

        // Step 1 : Lets create a count array to store the num of count of vowels & consonants
        // till each individual character.
        int[][] countMap = new int[s.length()][3];
        int numOfVowel=0;
        int numOfCons=0;
        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) numOfVowel++;
            else numOfCons++;
            countMap[i][0] = numOfVowel; // No of vowels
            countMap[i][1] = numOfCons; // No of consonants
            countMap[i][2] = numOfVowel-numOfCons; // Sum = Vowels - consonants
        }

        // Now lets crete the prefix sum map and start looking for
        // the sums where sum = 0 ( vowels == consonants)
        HashMap<Integer, HashMap<Integer,Integer>> prefixSumMap=new HashMap<>(); // prefixSum -> (vowelCount -> sumCount)
        prefixSumMap.computeIfAbsent(0, i -> new HashMap<>()).put(0, 1);

        HashMap<Integer,Integer> vowelAndCountMap; // No Of Vowel -> Count of that sum
        for (int i = 0; i < countMap.length; i++) {
            int[] countArr = countMap[i];
            int noOfVowels = countArr[0];
            int sum = countArr[2];
            if (prefixSumMap.containsKey(sum)){ // check if this sum came before
                // check for beautiful substring.
                vowelAndCountMap = prefixSumMap.get(sum);
                for (Integer pastVowelCount:vowelAndCountMap.keySet()){
                    // we know for these paris vowel == consonant. So sending vowel count
                    // as consonant count as they are same.
                    int currentVowelCount=noOfVowels%k-pastVowelCount;
                    if (isBeautiful(currentVowelCount,currentVowelCount,k)){
                        numOfSubstrings+=vowelAndCountMap.get(pastVowelCount);
                    }
                }
            }else {
                vowelAndCountMap = new HashMap<>();
            }
            // (a-b) * (a-b) % k = (a%k - b%k) * (a%k - b%k) % k
            // So we do the modulo operation to the noOfVowels count. Because
            // in case our test case is too large there will be too many vowels count.
            // so we reduce that using % operator.
            vowelAndCountMap.put(noOfVowels%k,vowelAndCountMap.getOrDefault(noOfVowels%k,0)+1);
            prefixSumMap.put(sum,vowelAndCountMap);
        }

        return numOfSubstrings;
    }

    public static void main(String[] args) {

        _12_CountBeautifulSubstrings_I_II obj = new _12_CountBeautifulSubstrings_I_II();

        String s1 = "baeyh";
        int k=2;
        System.out.println("Out : "+obj.beautifulSubstrings(s1,k));
        System.out.println("Out : "+obj.beautifulSubstrings1(s1,k));

        String s2 = "abba";
        int k1=1;
        System.out.println("Out : "+obj.beautifulSubstrings(s2,k1));
        System.out.println("Out : "+obj.beautifulSubstrings1(s2,k1));

    }
}