package DSA.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Problem Link : https://leetcode.com/problems/word-search/
 * Video : https://www.youtube.com/watch?v=pfiQ_PS1g8E
 * (You can watch this video to understand the problem but the solution (solution 3) is a combination of sol provided
 * in the video and combining our solution 1 & 2 )
 *
 *
 * 79. Word Search

 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are
 * horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example 1:
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 *
 * Example 2:
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 *
 * Example 3:
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 *
 * Constraints:
 *     m == board.length
 *     n = board[i].length
 *     1 <= m, n <= 6
 *     1 <= word.length <= 15
 *
 *     board and word consists of only lowercase and uppercase English letters.
 *
 * Follow up: Could you use search pruning to make your solution faster with a larger board?
 */
public class _13_WordSearch {

    /**
     * Solution 1 :
     * Initial solution :
     * <p>
     * Time Complexity: O(N × M × 4^L)
     * <p>
     * N × M: Board dimensions (we potentially start from every cell)
     * 4^L: For each starting position, we explore up to 4 directions at each of L characters, creating a branching factor of 4
     * L: Length of the word
     * <p>
     * Space Complexity: O(L)
     * <p>
     * Recursion stack depth: Maximum L levels deep (one for each character)
     * No additional data structures used beyond the call stack
     */
    public boolean backtrackWordSearch(
            char[][] board,
            String word,
            int wordIndex,
            int preRow,
            int preColumn
    ) {

        // base case
        if (wordIndex == word.length()) {
            return true;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (word.charAt(wordIndex) == board[i][j]) {
                    // letter is found lets check if there is a possibility its part of the word sequence.
                    boolean res = false;
                    char copy = board[i][j];
                    if (wordIndex == 0) {
                        // first letter there for no need to check pre row and column.
                        board[i][j] = '1';
                        res = backtrackWordSearch(board, word, wordIndex + 1, i, j);
                    } else if (i == preRow && preColumn + 1 == j) {
                        // same row next column
                        board[i][j] = '1';
                        res = backtrackWordSearch(board, word, wordIndex + 1, i, j);
                    } else if (i == preRow && preColumn - 1 == j) {
                        // same row previous column
                        board[i][j] = '1';
                        res = backtrackWordSearch(board, word, wordIndex + 1, i, j);
                    } else if (i == preRow + 1 && preColumn == j) {
                        // next row same column
                        board[i][j] = '1';
                        res = backtrackWordSearch(board, word, wordIndex + 1, i, j);
                    } else if (i == preRow - 1 && preColumn == j) {
                        // previous row same column
                        board[i][j] = '1';
                        res = backtrackWordSearch(board, word, wordIndex + 1, i, j);
                    }

                    // when backtracking is complete restore the previous value so other paths are not effected.
                    board[i][j] = copy;

                    // If we got true from one of the path we return true; else we continue
                    // searching for the char in later part of the board.
                    if (res) return true;
                }
            }
        }

        return false;
    }

    public boolean exist(char[][] board, String word) {
        boolean result = backtrackWordSearch(board, word, 0, -1, -1);
        return result;
    }

    /**
     * Solution 2 :
     * Better Solution : Reduced number of matrix iteration by setting search to only
     * pre-next row/column.
     * <p>
     * Time Complexity Improvement:
     * Previous: O(N × M × 4^L) - scanned entire board at each recursive level
     * Current: O(N × M × 4^L) - same worst-case but with better average performance
     * <p>
     * The boundary optimization reduces the constant factor by limiting search to 3×3 neighborhood
     * instead of the full N×M board, but the asymptotic complexity remains the same.
     * <p>
     * Space Complexity:
     * Both: O(L) - same recursion stack depth
     * <p>
     * Key Improvements:
     * - Boundary optimization: Only searches adjacent cells (3×3 area) instead of entire board
     * - Better cache locality: Accessing nearby memory locations
     * - Reduced unnecessary iterations: Fewer cells checked per recursive call
     */
    public boolean backtrackWordSearchWithBoundary(
            char[][] board,
            String word,
            int wordIndex,
            int preRow,
            int preColumn
    ) {

        // base case
        if (wordIndex == word.length()) {
            return true;
        }

        int rowBoundaryStart = Math.max(0, preRow - 1);
        int rowBoundaryEnd = Math.min(board.length - 1, preRow + 1);
        if (preRow == -1) {
            rowBoundaryEnd = board.length - 1;
        }
        int columnBoundaryStart = Math.max(0, preColumn - 1);
        int columnBoundaryEnd = Math.min(board[rowBoundaryEnd].length - 1, preColumn + 1);
        if (preColumn == -1) {
            columnBoundaryEnd = board[rowBoundaryEnd].length - 1;
        }

        for (int i = rowBoundaryStart; i <= rowBoundaryEnd; i++) {
            for (int j = columnBoundaryStart; j <= columnBoundaryEnd; j++) {

                if (word.charAt(wordIndex) == board[i][j]) {
                    // letter is found lets check if there is a possibility its part of the word sequence.
                    boolean res = false;
                    char copy = board[i][j];
                    if (wordIndex == 0) {
                        // first letter there for no need to check pre row and column.
                        board[i][j] = '1';
                        res = backtrackWordSearchWithBoundary(board, word, wordIndex + 1, i, j);
                    } else if (i == preRow && preColumn + 1 == j) {
                        // same row next column
                        board[i][j] = '1';
                        res = backtrackWordSearchWithBoundary(board, word, wordIndex + 1, i, j);
                    } else if (i == preRow && preColumn - 1 == j) {
                        // same row previous column
                        board[i][j] = '1';
                        res = backtrackWordSearchWithBoundary(board, word, wordIndex + 1, i, j);
                    } else if (i == preRow + 1 && preColumn == j) {
                        // next row same column
                        board[i][j] = '1';
                        res = backtrackWordSearchWithBoundary(board, word, wordIndex + 1, i, j);
                    } else if (i == preRow - 1 && preColumn == j) {
                        // previous row same column

                    }

                    // when backtracking is complete restore the previous value so other paths are not effected.
                    board[i][j] = copy;

                    // If we got true from one of the path we return true; else we continue
                    // searching for the char in later part of the board.
                    if (res) return true;
                }
            }
        }

        return false;
    }

    public boolean existWithBoundary(char[][] board, String word) {
        boolean result = backtrackWordSearchWithBoundary(board, word, 0, -1, -1);
        return result;
    }

    /**
    * Solution 3 :
     * Time Complexity: SAME Asymptotic, Different Constants
     * Both Solutions (Sol 2 & 3 ): O(N × M × 4^L)
     *
     * However:
     * Solution 2: Higher constant factor due to 3×3 boundary iteration + adjacency checks
     * Solution 3: Lower constant factor with direct 4-direction exploration
     *
     * Space Complexity: IDENTICAL to Sol 1 & 2.
     *
     * Why is solution 3 is better despite having same time & space complexity as previous solutions ?
     *
     * Algorithmic Efficiency ->
     * Solution 2: O(9) cells checked per recursion (3×3 boundary)
     * Solution 3: O(4) cells checked per recursion (only 4 directions)
     *
     * Performance Impact ->
     * Solution 2: ~9 iterations × adjacency checks = redundant work
     * Solution 3: Exactly 4 recursive calls = no wasted effort
     *
     * Bottom Line:
     * Solution 3 eliminates unnecessary work by directly exploring only valid directions instead of
     * calculating boundaries and then filtering. It's both faster and cleaner.
     *
    * */
    public boolean backtrackWordSearchApproachTwo(
            char[][] board,
            String word,
            int wordIndex,
            int preRow,
            int preColumn,
            int ROW,
            int COL

    ) {

        // base case
        if (wordIndex == word.length()) return true;

        if (preRow >= ROW ||
                preColumn >= COL ||
                preRow < 0 ||
                preColumn< 0 ||
                word.charAt(wordIndex) != board[preRow][preColumn]
        ) return false;

        char copy = board[preRow][preColumn];
        board[preRow][preColumn] = '#';

        boolean res = (backtrackWordSearchApproachTwo(board, word, wordIndex+1, preRow-1, preColumn, ROW, COL) ||
                backtrackWordSearchApproachTwo(board, word, wordIndex+1, preRow+1, preColumn, ROW, COL) ||
                backtrackWordSearchApproachTwo(board, word, wordIndex+1, preRow, preColumn-1, ROW, COL) ||
                backtrackWordSearchApproachTwo(board, word, wordIndex+1, preRow, preColumn+1, ROW, COL)
        );

        board[preRow][preColumn] = copy;
        return res;
    }

    public boolean existApproachTwo(char[][] board, String word) {

        int ROW = board.length;
        int COL = board[0].length;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (backtrackWordSearchApproachTwo(board,word,0,i,j,ROW,COL)) return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {

        _13_WordSearch solution = new _13_WordSearch();
//        char[][] board = {
//                {'a', 'b', 'c', 'd'},
//                {'e', 'f', 'g', 'h'},
//                {'i', 'j', 'k', 'l'},
//                {'m', 'n', 'o', 'p'}
//        };
//        char[][] board = {
//                {'A', 'B', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
//        };
        char[][] board = {
                {'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}
        };

        String word = "AAB";
        long startTime = System.currentTimeMillis();
        System.out.println(" Word " + word + " Exists : " + solution.exist(board, word));
        long endTime = System.currentTimeMillis();
        System.out.println(" Time Took : " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(" Word " + word + " Exists : " + solution.existWithBoundary(board, word));
        endTime = System.currentTimeMillis();
        System.out.println(" Time Took : " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(" Word " + word + " Exists : " + solution.existApproachTwo(board, word));
        endTime = System.currentTimeMillis();
        System.out.println(" Time Took : " + (endTime - startTime));


    }

}
