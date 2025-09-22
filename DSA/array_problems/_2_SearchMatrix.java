package array_problems;


/**
 *
 * Question link : https://leetcode.com/problems/search-a-2d-matrix/description/
 * Video Explanation : https://www.youtube.com/watch?v=Ber2pi2C0j0
 *
 * 74. Search a 2D Matrix
 *
 * You are given an m x n integer matrix matrix with the following two properties:
 *
 *     Each row is sorted in non-decreasing order.
 *     The first integer of each row is greater than the last integer of the previous row.
 *
 * Given an integer target, return true if target is in matrix or false otherwise.
 *
 * You must write a solution in O(log(m * n)) time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 *
 * Example 2:
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 *
 *
 *
 * Constraints:
 *
 *     m == matrix.length
 *     n == matrix[i].length
 *     1 <= m, n <= 100
 *     -104 <= matrix[i][j], target <= 104
 *
* */
public class _2_SearchMatrix {

    /**
     * Solution 1 :
     * Brute Force Approach is to solve it by going through each and every element. If we found the target then return true else
     * return false.
     *
     * Time Complexity: O(N X M), where N = given row number, M = given column number.
     * Reason: In order to traverse the matrix, we are using nested loops running for n and m times respectively.
     *
     * Space Complexity: O(1) as we are not using any extra space.
    * */
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (target==matrix[i][j]) return true;
            }
        }
        return false;
    }

    /**
     * Solution 2 :
     * In this solution we instead of going through each and every element we try to
     * reduce the iteration by first check if there is a chance of existent of the target in a particular row.
     * We can do that as we already know the matrix is sorted in ascending order.
     * So this particular if statement is true
     * (target>=matrix[i][0] && target <= matrix[i][m-1])
     * We iterate through that particular row otherwise we go to the next row and dont iterate for each and every row.
     *
     * Time complexity : O(n+m)
    * */
    public boolean searchMatrix1(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            if (target>=matrix[i][0]&&target<=matrix[i][m-1]){
                for (int j = 0; j < m; j++) {
                    if (target==matrix[i][j]) return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * Time Complexity: O(log m + log n)
     *
     * - First binary search on rows: O(log m) where m is number of rows
     * - Second binary search on columns: O(log n) where n is number of columns
     * - Total: O(log m + log n)
     *
     * Space Complexity: O(1)
     *
     * - Uses only a constant amount of extra variables (top, bottom, middle, row, left, right, mid)
     * - No additional data structures or recursive calls
     *
     * This is optimal for searching in a sorted 2D matrix where each row and column are sorted.
    * */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int ROW_SIZE = matrix.length;
        int COLUMN_SIZE = matrix[0].length;

        // First lets use binary search to find the row where our target value might be there.
        int top = 0,bottom = ROW_SIZE-1;
        while(top<=bottom){
            int middle = (top+bottom)/2;
            if (target<matrix[middle][0]){
                bottom = middle-1;
            }else if (target>matrix[middle][COLUMN_SIZE-1]){
                top = middle+1;
            }else {
                break;
            }
        }

        // If the row is not found then can return false as we are certain the value is not there in the matrix.
        if (!(top<=bottom)) return false;

        // Now lets run another binary search in the specific row to check if the value is there or not.
        int row = (top+bottom)/2;
        int left = 0,right = COLUMN_SIZE-1;
        while (left<=right){
            int mid = (left+right)/2;
            if (target>matrix[row][mid]){
                left = mid+1;
            }else if (target<matrix[row][mid]){
                right = mid-1;
            }else{
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        _2_SearchMatrix solution = new _2_SearchMatrix();
        int[][] inputArr = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 13;
        System.out.println("Target "+target+" is present : "+solution.searchMatrix(inputArr,target));
        System.out.println("Target "+target+" is present : "+solution.searchMatrix1(inputArr,target));
        System.out.println("Target "+target+" is present : "+solution.searchMatrix2(inputArr,target));


    }
}
