package DSA.array_problems;


import java.util.HashMap;
import java.util.HashSet;

class LongestSubstringWithoutRepeatingChar {

    /**
     * Brute Force Solution
    * */
    public int lengthOfLongestSubstringBrute(String s) {
        int longestSubString = 0;
        for(int i=0;i<s.length();i++){
            int currentSubStringLen=0;
            HashSet<Character> set = new HashSet<>();
            for(int j=i;j<s.length();j++){
                if(set.contains(s.charAt(j))){
                    break;
                }
                currentSubStringLen++;
                longestSubString = Math.max(currentSubStringLen,longestSubString);
                set.add(s.charAt(j));
            }
        }

        return longestSubString;
    }


    /**
     * Better Solution Using Two Pointers
     * */
    public int lengthOfLongestSubstring(String s) {
        int longestSubString = 0;
        HashSet<Character> set = new HashSet<>();
        int right = 0;
        int left = 0;
        while(right<s.length()){
            if(set.contains(s.charAt(right))){
                while(set.contains(s.charAt(right))){
                    set.remove(s.charAt(left));
                    left++;
                }
            }else{
                int currentLength = (right-left)+1;
                if(currentLength>longestSubString) longestSubString = currentLength;
            }
            set.add(s.charAt(right));
            right++;
        }
        return longestSubString;
    }

    /**
    * Optimal Approach : Instead of shifting the left pointer one by one we jump it based on
     * the intex of the char that is repeating.
    * */
    public int lengthOfLongestSubstringOptimal(String s) {
        int longestSubString = 0;
        HashMap<Character,Integer> set = new HashMap<>();
        int right = 0;
        int left = 0;
        while(right<s.length()){
            if(set.containsKey(s.charAt(right))){
                int index = set.get(s.charAt(right))+1;
                left = Math.max(left,index);
            }
            int currentLength = (right-left)+1;
            longestSubString = Math.max(currentLength,longestSubString);
            set.put(s.charAt(right),right);
            right++;
        }
        return longestSubString;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingChar solution = new LongestSubstringWithoutRepeatingChar();

        // Test cases
        String[] testInputs = {
                "abcabcbb",      // Expected: 3 ("abc")
                "bbbbb",         // Expected: 1 ("b")
                "pwwkew",        // Expected: 3 ("wke")
                "",              // Expected: 0 (empty string)
                "abcdef",        // Expected: 6 (entire string)
                "a",             // Expected: 1 (single character)
                "au",            // Expected: 2 ("au")
                "dvdf",          // Expected: 3 ("vdf")
                "abba",          // Expected: 2 ("ab" or "ba")
                "tmmzuxt"        // Expected: 5 ("mzuxt")
        };

        int[] expectedResults = {3, 1, 3, 0, 6, 1, 2, 3, 2, 5};

        System.out.println("Testing Longest Substring Without Repeating Characters:");
        System.out.println("=============================================================================");

        for (int i = 0; i < testInputs.length; i++) {
            String input = testInputs[i];
            int result = solution.lengthOfLongestSubstringOptimal(input);
            int expected = expectedResults[i];
            boolean passed = result == expected;

            System.out.printf("Test %2d: %-12s -> Result: %d, Expected: %d [%s]\n",
                    i + 1,
                    input.isEmpty() ? "\"\"" : "\"" + input + "\"",
                    result,
                    expected,
                    passed ? "PASS" : "FAIL");
        }
    }
}
