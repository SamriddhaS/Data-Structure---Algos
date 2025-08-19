package DSA.greedy_algo;

import java.util.Arrays;

/**
 * Problem Link : https://leetcode.com/problems/assign-cookies/description/
 * Explanation : https://www.youtube.com/watch?si=6G7JzMZ2TSP_LWSG&v=DIX2p7vb9co&feature=youtu.be
 *
 * 455. Assign Cookies
 * Solved
 * Easy
 * Topics
 * Companies
 *
 * Assume you are an awesome parent and want to give your children some cookies.
 * But, you should give each child at most one cookie.
 *
 * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will
 * be content with; and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the
 * cookie j to the child i, and the child i will be content. Your goal is to maximize the
 * number of your content children and output the maximum number.
 *
 *
 * Example 1:
 *
 * Input: g = [1,2,3], s = [1,1]
 * Output: 1
 * Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
 * And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
 * You need to output 1.
 *
 *  Example 2:
 *
 * Input: g = [1,2], s = [1,2,3]
 * Output: 2
 * Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
 * You have 3 cookies and their sizes are big enough to gratify all of the children,
 * You need to output 2.
 *
 *
 * Constraints:
 *
 * 1 <= g.length <= 3 * 104
 * 0 <= s.length <= 3 * 104
 * 1 <= g[i], s[j] <= 231 - 1
 *
 *
 * Note: This question is the same as 2410: https://leetcode.com/problems/assign-cookies/description/
 */
public class _5_AssignCookies {

    /**
    * Solution 1 : sort the greed factor and cookies weight then match them.
     *
     * Time Complexity :
     * Overall: O(n log n + m log m + O(m+n))
     *
     * Sorting: O(n log n + m log m)
     * Two-pointer traversal: O(n + m)
     *
     * Space Complexity :
     *
     * Overall: O(1) auxiliary (or O(log n + log m) if counting recursion stack).
     *
     * Sorting with Arrays.sort() (dual-pivot quicksort for primitives in Java) → O(log n + log m)
     * (due to recursion stack)
    * */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int child=0;
        int cookie=0;
        int count = 0;
        while(child<g.length&&cookie<s.length){
            if(s[cookie]>=g[child]) {
                count++;
                child++;
                cookie++;
            }else{
                cookie++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        _5_AssignCookies solution = new _5_AssignCookies();

        System.out.println("=== Cookie Assignment Test Cases ===\n");

        // Test Case 1: Example from problem
        int[] g1 = {1, 2, 3};
        int[] s1 = {1, 1};
        int result1 = solution.findContentChildren(g1, s1);
        System.out.println("Test Case 1:");
        System.out.println("Children greed: " + Arrays.toString(g1));
        System.out.println("Cookie sizes: " + Arrays.toString(s1));
        System.out.println("Result: " + result1);
        System.out.println("Expected: 1");
        System.out.println("Status: " + (result1 == 1 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 2: Example from problem
        int[] g2 = {1, 2};
        int[] s2 = {1, 2, 3};
        int result2 = solution.findContentChildren(g2, s2);
        System.out.println("Test Case 2:");
        System.out.println("Children greed: " + Arrays.toString(g2));
        System.out.println("Cookie sizes: " + Arrays.toString(s2));
        System.out.println("Result: " + result2);
        System.out.println("Expected: 2");
        System.out.println("Status: " + (result2 == 2 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 3: No children
        int[] g3 = {};
        int[] s3 = {1, 2, 3};
        int result3 = solution.findContentChildren(g3, s3);
        System.out.println("Test Case 3 (No children):");
        System.out.println("Children greed: " + Arrays.toString(g3));
        System.out.println("Cookie sizes: " + Arrays.toString(s3));
        System.out.println("Result: " + result3);
        System.out.println("Expected: 0");
        System.out.println("Status: " + (result3 == 0 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 4: No cookies
        int[] g4 = {1, 2, 3};
        int[] s4 = {};
        int result4 = solution.findContentChildren(g4, s4);
        System.out.println("Test Case 4 (No cookies):");
        System.out.println("Children greed: " + Arrays.toString(g4));
        System.out.println("Cookie sizes: " + Arrays.toString(s4));
        System.out.println("Result: " + result4);
        System.out.println("Expected: 0");
        System.out.println("Status: " + (result4 == 0 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 5: All cookies too small
        int[] g5 = {3, 4, 5};
        int[] s5 = {1, 1, 2};
        int result5 = solution.findContentChildren(g5, s5);
        System.out.println("Test Case 5 (All cookies too small):");
        System.out.println("Children greed: " + Arrays.toString(g5));
        System.out.println("Cookie sizes: " + Arrays.toString(s5));
        System.out.println("Result: " + result5);
        System.out.println("Expected: 0");
        System.out.println("Status: " + (result5 == 0 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 6: All children can be satisfied
        int[] g6 = {1, 2, 3};
        int[] s6 = {3, 4, 5};
        int result6 = solution.findContentChildren(g6, s6);
        System.out.println("Test Case 6 (All children satisfied):");
        System.out.println("Children greed: " + Arrays.toString(g6));
        System.out.println("Cookie sizes: " + Arrays.toString(s6));
        System.out.println("Result: " + result6);
        System.out.println("Expected: 3");
        System.out.println("Status: " + (result6 == 3 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 7: More cookies than children
        int[] g7 = {1, 3};
        int[] s7 = {1, 2, 3, 4, 5};
        int result7 = solution.findContentChildren(g7, s7);
        System.out.println("Test Case 7 (More cookies than children):");
        System.out.println("Children greed: " + Arrays.toString(g7));
        System.out.println("Cookie sizes: " + Arrays.toString(s7));
        System.out.println("Result: " + result7);
        System.out.println("Expected: 2");
        System.out.println("Status: " + (result7 == 2 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 8: Single child, single cookie (match)
        int[] g8 = {5};
        int[] s8 = {5};
        int result8 = solution.findContentChildren(g8, s8);
        System.out.println("Test Case 8 (Single child, cookie matches):");
        System.out.println("Children greed: " + Arrays.toString(g8));
        System.out.println("Cookie sizes: " + Arrays.toString(s8));
        System.out.println("Result: " + result8);
        System.out.println("Expected: 1");
        System.out.println("Status: " + (result8 == 1 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 9: Single child, single cookie (no match)
        int[] g9 = {5};
        int[] s9 = {3};
        int result9 = solution.findContentChildren(g9, s9);
        System.out.println("Test Case 9 (Single child, cookie too small):");
        System.out.println("Children greed: " + Arrays.toString(g9));
        System.out.println("Cookie sizes: " + Arrays.toString(s9));
        System.out.println("Result: " + result9);
        System.out.println("Expected: 0");
        System.out.println("Status: " + (result9 == 0 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 10: Complex case with mixed satisfaction
        int[] g10 = {1, 2, 7, 8, 9};
        int[] s10 = {1, 3, 5, 6};
        int result10 = solution.findContentChildren(g10, s10);
        System.out.println("Test Case 10 (Complex mixed case):");
        System.out.println("Children greed: " + Arrays.toString(g10));
        System.out.println("Cookie sizes: " + Arrays.toString(s10));
        System.out.println("Result: " + result10);
        System.out.println("Expected: 2 (children with greed 1,2 get cookies 1,3)");
        System.out.println("Status: " + (result10 == 2 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 11: All same greed, all same cookie size
        int[] g11 = {2, 2, 2};
        int[] s11 = {2, 2, 2};
        int result11 = solution.findContentChildren(g11, s11);
        System.out.println("Test Case 11 (All same values):");
        System.out.println("Children greed: " + Arrays.toString(g11));
        System.out.println("Cookie sizes: " + Arrays.toString(s11));
        System.out.println("Result: " + result11);
        System.out.println("Expected: 3");
        System.out.println("Status: " + (result11 == 3 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 12: Large numbers
        int[] g12 = {10, 20, 30};
        int[] s12 = {15, 25, 35};
        int result12 = solution.findContentChildren(g12, s12);
        System.out.println("Test Case 12 (Large numbers):");
        System.out.println("Children greed: " + Arrays.toString(g12));
        System.out.println("Cookie sizes: " + Arrays.toString(s12));
        System.out.println("Result: " + result12);
        System.out.println("Expected: 3");
        System.out.println("Status: " + (result12 == 3 ? "PASS" : "FAIL"));

        System.out.println("\n=== Test Summary ===");
        System.out.println("All test cases validate the greedy algorithm approach:");
        System.out.println("1. Sort children by greed factor (ascending)");
        System.out.println("2. Sort cookies by size (ascending)");
        System.out.println("3. Match smallest available cookie to least greedy child");
        System.out.println("4. This maximizes the number of satisfied children");
    }

}
