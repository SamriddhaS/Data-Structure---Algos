package DSA.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Problem Link : https://leetcode.com/problems/word-search/
 * Video :
 *
 *
 */
public class _13_WordSearch {

    public boolean exist(char[][] board, String word) {

        int r = -1;
        int c = -1;
        for (int v = 0; v < word.length(); v++) {

            // First letter is not found return;
            if (v!=0 && r==-1 && c==-1) return false;

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (word.charAt(v)==board[i][j]){
                        // letter is found lets check if there is a possibility its part of the word sequence.

                        if (i==r && c+1==j) {
                            // same row next column

                        }
                        if (i==r && c-1==j) {
                            // same row previous column

                        }
                        if (i==r+1 && c==j) {
                            // next row same column

                        }
                        if (i==r-1 && c==j) {
                            // previous row same column

                        }
                        r = i;
                        c = j;
                    }
                }
            }

        }


        return false;
    }

    public static void main(String[] args) {

        _13_WordSearch solution = new _13_WordSearch();
        char[][] board = {
                {'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'h'},
                {'i', 'j', 'k', 'l'},
                {'m', 'n', 'o', 'p'}
        };

        String word = "abc";
        System.out.println(" Word "+word+" Exists : "+solution.exist(board,word));

    }

}
