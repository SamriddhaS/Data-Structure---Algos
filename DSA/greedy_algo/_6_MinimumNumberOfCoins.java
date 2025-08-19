package DSA.greedy_algo;

import java.util.Arrays;
import java.util.Collections;

/**
 * Problem Link : https://takeuforward.org/data-structure/find-minimum-number-of-coins/
 * Explanation : https://www.youtube.com/watch?v=mVg9CfJvayM&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=49&ab_channel=takeUforward
 *
 * Find minimum number of coins
 *
 * Problem Statement: Given a value V, if we want to make a change for V Rs, and we have an infinite supply
 * of each of the denominations in Indian currency, i.e., we have an infinite supply of { 1, 2, 5, 10, 20, 50, 100, 500, 1000}
 * valued coins/notes, what is the minimum number of coins and/or notes needed to make the change.
 *
 * Examples:
 *
 * Example 1:
 * Input: V = 70
 * Output: 2
 *
 * Explaination: We need a 50 Rs note and a 20 Rs note.
 *
 * Example 2:
 * Input: V = 121
 * Output: 3
 *
 * Explaination: We need a 100 Rs note, a 20 Rs note and a 1 Rs coin.
 *
 */
public class _6_MinimumNumberOfCoins {


    /**
     *
     * When Coin's array is not sorted and can be in random order :
     *
     * Time Complexity: O(n log n)
     * The time complexity is dominated by the sorting operation:
     *
     * Arrays.sort(g, Collections.reverseOrder()) takes O(n log n) time
     * The while loop runs at most n iterations (once per coin denomination), so it's O(n)
     * Inside the loop, all operations (comparison, division, modulo) are O(1)
     *
     * Overall: O(n log n) + O(n) = O(n log n)
     *
     * Space Complexity: O(1)
     *
     * When Coin's array is sorted :
     *
     * If the array is already sorted, then the complexity changes:
     *
     * Time Complexity: O(n)
     *
     * Overall: O(n)
     * Space Complexity: O(1)
    * */
    public int minNoOfCoins(Integer[] g, int requiredValue) {

        Arrays.sort(g, Collections.reverseOrder());

        int index=0;
        int count = 0;
        while (requiredValue!=0&&index<g.length){
            if (g[index]<=requiredValue){
                Integer value =g[index];
                int howMany = (int) Math.floor((double) requiredValue /value);
                count+=howMany;
                requiredValue = requiredValue%value;
            }
            index++;
        }
        return count;
    }

    public static void main(String[] args) {
        _6_MinimumNumberOfCoins object = new _6_MinimumNumberOfCoins();
        Integer coins[] = {1, 2, 5, 10, 20, 50, 100, 500, 1000};

        // Original test cases
        System.out.println("Test Case 1:");
        System.out.println("Input: V = 70");
        System.out.println("No of coin/note needed : " + object.minNoOfCoins(coins, 70));
        System.out.println("Expected: 2 (50 + 20)");
        System.out.println();

        System.out.println("Test Case 2:");
        System.out.println("Input: V = 121");
        System.out.println("No of coin/note needed : " + object.minNoOfCoins(coins, 121));
        System.out.println("Expected: 3 (100 + 20 + 1)");
        System.out.println();

        // Additional test cases
        System.out.println("Test Case 3:");
        System.out.println("Input: V = 1");
        System.out.println("No of coin/note needed : " + object.minNoOfCoins(coins, 1));
        System.out.println("Expected: 1 (1)");
        System.out.println();

        System.out.println("Test Case 4:");
        System.out.println("Input: V = 3");
        System.out.println("No of coin/note needed : " + object.minNoOfCoins(coins, 3));
        System.out.println("Expected: 2 (2 + 1)");
        System.out.println();

        System.out.println("Test Case 5:");
        System.out.println("Input: V = 13");
        System.out.println("No of coin/note needed : " + object.minNoOfCoins(coins, 13));
        System.out.println("Expected: 3 (10 + 2 + 1)");
        System.out.println();

        System.out.println("Test Case 6:");
        System.out.println("Input: V = 25");
        System.out.println("No of coin/note needed : " + object.minNoOfCoins(coins, 25));
        System.out.println("Expected: 2 (20 + 5)");
        System.out.println();

        System.out.println("Test Case 7:");
        System.out.println("Input: V = 99");
        System.out.println("No of coin/note needed : " + object.minNoOfCoins(coins, 99));
        System.out.println("Expected: 6 (50 + 20 + 20 + 5 + 2 + 2)");
        System.out.println();

        System.out.println("Test Case 8:");
        System.out.println("Input: V = 150");
        System.out.println("No of coin/note needed : " + object.minNoOfCoins(coins, 150));
        System.out.println("Expected: 2 (100 + 50)");
        System.out.println();

        System.out.println("Test Case 9:");
        System.out.println("Input: V = 573");
        System.out.println("No of coin/note needed : " + object.minNoOfCoins(coins, 573));
        System.out.println("Expected: 5 (500 + 50 + 20 + 2 + 1)");
        System.out.println();

        System.out.println("Test Case 10:");
        System.out.println("Input: V = 1599");
        System.out.println("No of coin/note needed : " + object.minNoOfCoins(coins, 1599));
        System.out.println("Expected: 8 (1000 + 500 + 50 + 20 + 20 + 5 + 2 + 2 )");
        System.out.println();

        System.out.println("Test Case 11:");
        System.out.println("Input: V = 2023");
        System.out.println("No of coin/note needed : " + object.minNoOfCoins(coins, 2023));
        System.out.println("Expected: 5 (1000 + 1000 + 20 + 2 + 1)");
        System.out.println();

        // Edge cases
        System.out.println("Test Case 12 (Edge - Largest denomination):");
        System.out.println("Input: V = 1000");
        System.out.println("No of coin/note needed : " + object.minNoOfCoins(coins, 1000));
        System.out.println("Expected: 1 (1000)");
        System.out.println();

        System.out.println("Test Case 13 (Edge - Zero):");
        System.out.println("Input: V = 0");
        System.out.println("No of coin/note needed : " + object.minNoOfCoins(coins, 0));
        System.out.println("Expected: 0");
        System.out.println();

        System.out.println("Test Case 14 (Large value):");
        System.out.println("Input: V = 5678");
        System.out.println("No of coin/note needed : " + object.minNoOfCoins(coins, 5678));
        System.out.println("Expected: 12 (1000*5 + 500 + 100 + 50 + 20 + 5 + 2 + 1)");
        System.out.println();

    }

}
