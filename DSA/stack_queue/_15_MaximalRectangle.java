package stack_queue;

import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/maximal-rectangle/description/
 * Video Explanation : https://www.youtube.com/watch?v=ttVu6G7Ayik
 *
 * 85. Maximal Rectangle
 * Hard
 * Topics
 *
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing
 * only 1's and return its area.
 *
 * Example 1:
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above picture.
 *
 * Example 2:
 * Input: matrix = [["0"]]
 * Output: 0
 *
 * Example 3:
 * Input: matrix = [["1"]]
 * Output: 1
 *
 * Constraints:
 *     rows == matrix.length
 *     cols == matrix[i].length
 *     1 <= row, cols <= 200
 *     matrix[i][j] is '0' or '1'.
 *
 */
public class _15_MaximalRectangle {

    public int[][] createPrefixSumMatrix(char[][] matrix){
        int ROW = matrix.length;
        int COL = matrix[0].length;
        int[][] prefixSumMatrix = new int[ROW][COL];

        for (int column = 0; column < COL ; column++) {
            int sum = 0;
            for (int row = 0; row < ROW ; row++) {

                int element = 1;
                if ((int)matrix[row][column]==48) element=0; // convert char to int values.

                sum+=element;
                if (matrix[row][column]=='0') sum=0;
                prefixSumMatrix[row][column] = sum;
            }
        }

        return prefixSumMatrix;
    }

    public int findRectangleHistogramForEachRow(int[] row){
        int maxRec = 0;
        Stack<Pair> stack = new Stack<>();
        for (int i = 0; i < row.length; i++) {
            int ele = row[i];
            int startIndex = i;
            while (!stack.isEmpty()&&ele<stack.peek().data){
                Pair popped = stack.pop();
                int width = i-popped.index;
                int height = popped.data;
                int area = width*height;
                maxRec = Math.max(maxRec,area);
                startIndex = popped.index;
            }
            stack.push(new Pair(ele,startIndex));
        }
        while (!stack.isEmpty()){
            Pair popped = stack.pop();
            int width = row.length-popped.index;
            int height = popped.data;
            int area = width*height;
            maxRec = Math.max(maxRec,area);
        }

        return maxRec;
    }

    /**
     * Algorithm Description:
     * This algorithm solves the "Maximal Rectangle" problem by
     * converting it into multiple "Largest Rectangle in Histogram" problems:
     * @see _14_LargestRectangleInHistogram;
     *
     * - Prefix Sum Matrix: For each column, calculate consecutive '1's ending
     * at each row (resets to 0 when encountering '0')
     * - Histogram Processing: Treat each row of the prefix matrix as a histogram and
     * find the largest rectangle using a stack-based approach
     * - Maximum Tracking: Return the maximum rectangle found across all rows
     *
     * Time Complexity: O(m × n)
     * - Creating prefix sum matrix: O(m × n) - visit each cell once
     * - Processing each row as histogram: O(n) per row using stack (each element pushed/popped once)
     * - Total histogram processing: O(m × n)
     * - Overall: O(m × n)
     *
     * Space Complexity: O(m × n)
     * - Prefix sum matrix: O(m × n)
     * - Stack for histogram processing: O(n) in worst case
     * - Overall: O(m × n)
     *
    * */
    public int maximalRectangle(char[][] matrix) {
        int maximalRectangle = 0;
        int row = matrix.length;
        int[][] prefixSumMatrix = createPrefixSumMatrix(matrix);
        for (int i=0;i<row;i++){
            int maxRecForRow = findRectangleHistogramForEachRow(prefixSumMatrix[i]);
            maximalRectangle = Math.max(maxRecForRow,maximalRectangle);
        }
        return maximalRectangle;
    }

    public static void main(String[] args) {
        _15_MaximalRectangle obj = new _15_MaximalRectangle();

        char[][] input = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println("The Answer : " + obj.maximalRectangle(input));

        char[][] input1 = {
                {'1','0','1','0','0'},
                {'1','1','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','1','1','1'}
        };
        System.out.println("The Answer : " + obj.maximalRectangle(input1));

        char[][] input2 = {
                {'1','1','1','0','0'},
        };
        System.out.println("The Answer : " + obj.maximalRectangle(input2));

        char[][] input3 = {
                {'1'},
        };
        System.out.println("The Answer : " + obj.maximalRectangle(input3));


    }
}