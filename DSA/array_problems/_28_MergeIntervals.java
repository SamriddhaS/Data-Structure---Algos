package array_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Problem Link : https://leetcode.com/problems/merge-intervals/description/
 * Video :
 * Topic : TArrays
 *
 * 56. Merge Intervals
 * Solved
 * Medium
 * Topics
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * Example 3:
 * Input: intervals = [[4,7],[1,4]]
 * Output: [[1,7]]
 * Explanation: Intervals [1,4] and [4,7] are considered overlapping.
 *
 * Constraints:
 *     1 <= intervals.length <= 104
 *     intervals[i].length == 2
 *     0 <= starti <= endi <= 104
 *
* */
class _28_MergeIntervals {

    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> answer = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int[] currentInterval = new int[2];
        Arrays.fill(currentInterval,Integer.MAX_VALUE);

        for (int i = 1; i < intervals.length; i++) {

            int start = intervals[i][0];
            int end = intervals[i][1];

            int previousStart = intervals[i -1][0];
            int previousEnd = intervals[i - 1][1];

            if (previousEnd>=start){
                if(previousStart<currentInterval[0]) currentInterval[0] = previousStart;
            }else {
                currentInterval[1] = previousEnd;
                answer.add(currentInterval.clone());
                currentInterval[0] = start;
                if (i==intervals.length-1) {
                    currentInterval[1] = end;
                    answer.add(currentInterval.clone());
                }
            }

        }

        return answer.toArray(new int[0][]);
    }

    public static void main(String[] args) {

        _28_MergeIntervals solution = new _28_MergeIntervals();

        int[][] input = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println("Answer : "+ Arrays.deepToString(solution.merge(input)));

    }
}
