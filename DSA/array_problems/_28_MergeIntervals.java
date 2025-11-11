package array_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Problem Link : https://leetcode.com/problems/merge-intervals/description/
 * Video : https://www.youtube.com/watch?v=44H3cEC2fFM
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

    /**
     * Time Complexity: O(n log n)
     *
     * Sorting the intervals takes O(n log n)
     * Single pass through intervals takes O(n)
     * Overall: O(n log n) dominates
     *
     * Space Complexity: O(n)
     *
     * answer list can store up to n intervals in worst case (no overlaps)
     * Sorting may use O(log n) space depending on implementation
     * Overall: O(n)
    * */
    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> answer = new ArrayList<>();
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0], b[0]));
        answer.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            int previousEnd = answer.get(answer.size()-1)[1];
            if (previousEnd>=start){
                answer.get(answer.size()-1)[1] = Math.max(end,previousEnd);
            }else {
                answer.add(new int[]{start,end});
            }
        }

        return answer.toArray(new int[answer.size()][]);
    }

    public static void main(String[] args) {

        _28_MergeIntervals solution = new _28_MergeIntervals();

        int[][] input = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println("Answer : "+ Arrays.deepToString(solution.merge(input)));

    }
}
