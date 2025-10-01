package array_problems;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Problem Link : https://leetcode.com/problems/find-missing-and-repeated-values/description/
 * Video : https://www.youtube.com/watch?v=LQGmWiDuTw8
 * Topic : TArrays
 *
 * 2965. Find Missing and Repeated Values
 * Solved
 * Easy
 * Topics
 *
 * You are given a 0-indexed 2D integer matrix grid of size n * n with values
 * in the range [1, n2]. Each integer appears exactly once except a which appears
 * twice and b which is missing. The task is to find the repeating and missing numbers a and b.
 *
 * Return a 0-indexed integer array and of size 2 where ans[0] equals to a and ans[1] equals to b.
 *
 * Example 1:
 * Input: grid = [[1,3],[2,2]]
 * Output: [2,4]
 * Explanation: Number 2 is repeated and number 4 is missing so the answer is [2,4].
 *
 * Example 2:
 * Input: grid = [[9,1,7],[8,9,2],[3,4,6]]
 * Output: [9,5]
 * Explanation: Number 9 is repeated and number 5 is missing so the answer is [9,5].
 *
 * Constraints:
 *     2 <= n == grid.length == grid[i].length <= 50
 *     1 <= grid[i][j] <= n * n
 *
 *     For all x that 1 <= x <= n * n there is exactly one x that is not
 *     equal to any of the grid members.
 *
 *     For all x that 1 <= x <= n * n there is exactly one x that is
 *     equal to exactly two of the grid members.
 *
 *     For all x that 1 <= x <= n * n except two of them there is
 *     exactly one pair of i, j that 0 <= i, j <= n - 1 and grid[i][j] == x.
 *
* */
class _19_FindMissingAndRepeatedValues {

    /**
     * Solution 1 : First Solution.
     *
     * Time Complexity: O(n²)
     * Two nested loops iterate through the n×n grid: O(n²)
     * HashMap operations (containsKey, put) are O(1) average case
     * Final while loop runs at most n² times: O(n²)
     * Overall: O(n²)
     *
     * Space Complexity: O(n²)
     * HashMap stores at most n² elements (all unique values from grid)
     * Answer array is O(1) - fixed size of 2
     * Overall: O(n²)
     *
     * We can actually optimise the space complexity further, but I am ignoring it for now.
     *
    * */
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int[] answer = new int[2];
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (map.containsKey(grid[i][j])) answer[0] = grid[i][j]; // duplicate
                map.put(grid[i][j],grid[i][j]);
            }
        }

        // missing
        int i=1;
        while(i<=grid.length * grid.length){
            if (!map.containsKey(i)){
                answer[1] = i;
                return answer;
            }
            i++;
        }
        return answer;
    }

    /**
     * Solution 2 : Not sure how this one works. Will need to dig into this one later.
     * Without math solution| O(1) space
     *
     * Much better method : (Make sure with interviewer that its okay to modify the input grid)
     *
     * Approach : Simply , think in terms of 1D array, where all the elements are in the
     * range of [1,n*n] and the size of the array will also be [0,(n*n)-1] (0 based indexing).
     *
     * So the approach is to simply treat the element as position and mark it
     * as seen (make it to negative), and if a number is already seen, its a
     * repeated number, finally scan the array to get the missing number(The pos which is positive).
     *
     * Make sure to read the elements as abs(val)-1
     * Now, Doing this with 2D is little tedious, but if you think you can definitely crack it.
     *
    * */
    public int[] findMissingAndRepeatedValues1(int[][] grid) {
        int[] answer = new int[2];
        int n = grid.length;

        // Step 1: Mark each number as "seen" by making the value at its corresponding position negative
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Get the actual value (use abs because it might already be negative from previous marking)
                int value = Math.abs(grid[i][j]);

                // Convert this value to a 1D index (value range is [1, n²], so subtract 1)
                int index = value - 1;

                // Convert 1D index to 2D coordinates
                int row = index / n;
                int col = index % n;

                // Check if this position is already marked (negative)
                if (grid[row][col] < 0) {
                    // Already marked! This means 'value' appears twice
                    answer[0] = value;  // Duplicate found
                } else {
                    // Mark this position as seen by making it negative
                    grid[row][col] = -grid[row][col];
                }
            }
        }

        // Step 2: Find the missing number by scanning for the positive value
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // If this position is still positive, it was never marked
                if (grid[i][j] > 0) {
                    // Convert 2D position back to the number that should be here
                    int index = i * n + j;
                    answer[1] = index + 1;  // Missing number found (add 1 because we subtracted earlier)

                    // Optional: Restore the grid to original state
                    // Uncomment the next section if you need to preserve the original grid

                    return answer;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        _19_FindMissingAndRepeatedValues solution = new _19_FindMissingAndRepeatedValues();

        int[][] input = {{1,3},{2,2}};
        System.out.println("Answer : "+ Arrays.toString(solution.findMissingAndRepeatedValues1(input)));

    }
}
