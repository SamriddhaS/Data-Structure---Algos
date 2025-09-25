package stack_queue;

import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 * Video Explanation : https://www.youtube.com/watch?v=zx5Sw9130L0
 * 84. Largest Rectangle in Histogram
 * Hard
 * <p>
 * Given an array of integers heights representing the histogram's bar height where the width
 * of each bar is 1, return the area of the largest rectangle in the histogram.
 * <p>
 * Example 1:
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * <p>
 * Example 2:
 * Input: heights = [2,4]
 * Output: 4
 * <p>
 * Constraints:
 * 1 <= heights.length <= 105
 * 0 <= heights[i] <= 104
 *
 */
public class _14_LargestRectangleInHistogram {

    /**
     * Solution 1 : Brute Force
     * <p>
     * Time Complexity: O(n²)
     * - Outer loop runs n times
     * - Inner loop runs up to n times for each outer iteration
     * - Worst case: O(n²) when all elements are processed
     * <p>
     * Space Complexity: O(1)
     * - Only using constant extra variables (largestRet, minArea, currentArea)
     * - No additional data structures that scale with input size
     *
     */
    public int largestRectangleArea(int[] heights) {
        int largestRet = heights[0];
        for (int i = 0; i < heights.length; i++) {
            int minArea = heights[i];
            for (int j = i; j >= 0; j--) {
                minArea = Math.min(minArea, heights[j]);
                int currentArea = ((i - j) + 1) * minArea;
                largestRet = Math.max(largestRet, currentArea);
            }
        }
        return largestRet;
    }

    /**
     * Time Complexity: O(n)
     *
     * - Each element is pushed to the stack exactly once
     * - Each element is popped from the stack at most once
     * - Total operations across all iterations: O(n)
     *
     * Space Complexity: O(n)
     *
     * - Stack can store up to n Pair objects in worst case (when array is strictly increasing)
     * - Each Pair object stores constant space (height + index)
     *
    * */
    public int largestRectangleArea1(int[] heights) {
        Stack<Pair> stack = new Stack<>();
        int largestRec = 0;
        for (int i = 0; i < heights.length; i++) {
            int ele = heights[i];
            if (stack.isEmpty() || ele >= stack.peek().data) {
                // tring to create a ascending monotonic stack
                stack.push(new Pair(ele, i));
            } else {
                int lastIndex = i;
                // if a lesser element is there that means some elements in our stack cant
                // be extended any further so we check their rectangle area and pop them.
                while (!stack.isEmpty() && ele < stack.peek().data) {
                    Pair pair = stack.pop();
                    int currentArea = (i - pair.index) * pair.data;
                    largestRec = Math.max(largestRec, currentArea);
                    lastIndex = pair.index;
                }
                stack.push(new Pair(ele, lastIndex));
            }
        }
        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            int currentArea = (heights.length - pair.index) * pair.data;
            largestRec = Math.max(largestRec, currentArea);
        }
        return largestRec;
    }

    public static void main(String[] args) {
        _14_LargestRectangleInHistogram obj = new _14_LargestRectangleInHistogram();

        int[] input = {2, 1, 5, 6, 2, 3};
        System.out.println("The Answer : " + obj.largestRectangleArea(input));
        System.out.println("The Answer : " + obj.largestRectangleArea1(input));

        int[] input1 = {2, 4};
        System.out.println("The Answer : " + obj.largestRectangleArea(input1));
        System.out.println("The Answer : " + obj.largestRectangleArea1(input1));

        int[] input2 = {6, 7, 5, 2, 4, 5, 9, 3};
        System.out.println("The Answer : " + obj.largestRectangleArea(input2));
        System.out.println("The Answer : " + obj.largestRectangleArea1(input2));

        int[] input3 = {1, 1, 1, 1, 1};
        System.out.println("The Answer : " + obj.largestRectangleArea(input3));
        System.out.println("The Answer : " + obj.largestRectangleArea1(input3));
    }
}