package DSA.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Link : https://leetcode.com/problems/n-queens/description/
 * Video(intuition) : https://www.youtube.com/watch?v=xFv_Hl4B83A&t=10s
 *
 * 51. N-Queens
 *
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate
 * a queen and an empty space, respectively.
 *
 * Example 1:
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 *
 * Example 2:
 * Input: n = 1
 * Output: [["Q"]]
 *
 * Constraints:
 *     1 <= n <= 9
 *
 */
public class _14_NQueensProblem {

    public boolean isValidPositionFound(int[][] board, int row, int col) {
        int n = board.length;

        int r = row - 1, c = col - 1;
        // Check same columns
        while(r>=0){
            if (board[r][col]!=0) return false;
            r--;
        }


        r = row-1;
        c = col-1;
        // Check top-left diagonal
        while (r >= 0 && c >= 0) {
            if (board[r][c]!=0) return false;
            r--;
            c--;
        }

        // Check top-right diagonal
        r = row - 1;
        c = col + 1;
        while (r >= 0 && c < n) {
            if (board[r][c]!=0) return false;
            r--;
            c++;
        }

        return true; // No elements found on either diagonal
    }

    public void backtrackQueenPositions(
            List<List<String>> answer,
            List<String> currentPositions,
            int noOfQueens,
            int[][] board,
            int currentQueen
    ){

        if (noOfQueens==currentQueen){
            answer.add(new ArrayList<>(currentPositions));
            return;
        }

        for (int j=0;j<noOfQueens;j++){
            // Iterate through all the columns to check for a valid position.
            if (isValidPositionFound(board,currentQueen,j)){
                StringBuilder builder = new StringBuilder();
                int temp = 0;
                while(temp<noOfQueens){
                    //create the string eg: "..Q."
                    if (temp==j) builder.append("Q");
                    else builder.append(".");
                    temp++;
                }
                currentPositions.add(builder.toString());
                board[currentQueen][j] = 1;
                backtrackQueenPositions(answer, currentPositions, noOfQueens, board, currentQueen+1);
                board[currentQueen][j] = 0;
                currentPositions.remove(currentPositions.size()-1);
            }
        }

    }

    /**
    * Solution 1 : My initial solution. It works but can be improved.
     *
     * Time Complexity: O(N!)
     *
     * - In the first row, you have N possible positions for the queen
     * - In the second row, you have at most (N-1) valid positions (due to column conflicts)
     * - In the third row, you have at most (N-2) valid positions
     * - and so on.
     * - For each potential placement, isValidPositionFound runs in O(N) time (checking column and diagonals)
     * - String building for each solution takes O(N) time
     * Total = O(N + N + N!) = O(N!)
     *
     * Space Complexity: O(N²)
     * Breakdown:
     * - Board storage: O(N²) for the 2D array
     * - Recursion stack: O(N) depth (one recursive call per row)
     * - Current solution storage: O(N) for the current path being built
     * - Result storage: Each solution takes O(N²) space (N strings of length N), and there can be multiple solutions
     *
    * */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> answer = new ArrayList<>();
        int[][] board1 = new int[n][n];
        backtrackQueenPositions(answer,new ArrayList<>(),n,board1,0);
        return answer;
    }

    public void backtrackQueenOptimised(
            List<List<String>> answer,
            List<String> currentPositions,
            int noOfQueens,
            int currentQueen,
            boolean[] column,
            boolean[] diagonal1,
            boolean[] diagonal2
    ){

        if (noOfQueens==currentQueen){
            answer.add(new ArrayList<>(currentPositions));
            return;
        }

        for (int j=0;j<noOfQueens;j++){
            // Iterate through all the columns to check for a valid position.
            if (!column[j]&&!diagonal1[currentQueen-j+noOfQueens-1]&&!diagonal2[currentQueen+j]){
                StringBuilder builder = new StringBuilder();
                int temp = 0;
                while(temp<noOfQueens){
                    //create the string eg: "..Q."
                    if (temp==j) builder.append("Q");
                    else builder.append(".");
                    temp++;
                }
                currentPositions.add(builder.toString());
                column[j] = true;
                diagonal1[currentQueen-j+noOfQueens-1] = true;
                diagonal2[currentQueen+j] = true;
                backtrackQueenOptimised(answer, currentPositions, noOfQueens, currentQueen+1,column,diagonal1,diagonal2);
                column[j] = false;
                diagonal1[currentQueen-j+noOfQueens-1] = false;
                diagonal2[currentQueen+j] = false;
                currentPositions.remove(currentPositions.size()-1);
            }
        }

    }

    /**
     * Solution 2
     *
     * Time Complexity: O(N!)
     * - Same asymptotic complexity as before (still exploring N! possibilities)
     * - Significant constant factor improvement due to O(1) validation vs O(N)
     *
     * Space Complexity: O(N)
     * - O(N) for boolean arrays + O(N) recursion stack = O(N) total
     *
     *
     * Why This Solution is Better ?
     *
     * Performance Improvements:
     *
     * - Faster Validation: O(1) vs O(N)
     *
     * - Before: Scanned entire columns/diagonals for each placement check
     *   Now: Single boolean array lookup for each conflict type
     * - Memory Efficiency: O(N) vs O(N²)
     * - Before: N×N board array
     *   Now: Three 1D boolean arrays totaling ~4N space
     * */
    public List<List<String>> solveNQueensOptimised(int n) {
        List<List<String>> answer = new ArrayList<>();
        boolean[] column = new boolean[n];
        boolean[] diagonal1 = new boolean[n*2-1];
        boolean[] diagonal2 = new boolean[n*2-1];
        backtrackQueenOptimised(answer,new ArrayList<>(),n,0,column,diagonal1,diagonal2);
        return answer;
    }

    public static void main(String[] args) {
        _14_NQueensProblem solution = new _14_NQueensProblem();
        solution.solveNQueensOptimised(4).forEach(strings -> {
            System.out.println("Answers : "+strings);
        });
    }

}
