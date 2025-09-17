package stack_queue;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/trapping-rain-water/
 *
 *
 */
public class _9_TrappingRainWater {

    /**
     * Solution 1 : Intuition Behind the Stack-Based Rain Water Trapping Algorithm
     * Intuition :
     * Core Idea: Use a stack to find "valleys" between taller bars where water can be trapped.
     *
     * Key Insight: Water can only be trapped when we have a pattern like this:
     * Taller → Shorter → Taller
     *  Left     Valley    Right
     *
     * How it works:
     *
     * Push decreasing/equal heights onto stack - these could be potential valley bottoms
     * When we find a taller bar - this could be a right boundary that traps water
     * Pop from stack - each popped element is a valley bottom
     * Calculate trapped water - between the left boundary (new stack top) and right boundary (current bar)
     *
     * Why the stack?:
     *
     * Keeps track of potential left boundaries in decreasing order
     * When we find a taller bar, we know it can form a "container" with previous taller bars
     * We process valleys from innermost to outermost (due to stack's LIFO nature)
     *
     * The calculation:
     *
     * Width: Distance between left and right boundaries (excluding boundaries)
     * Height: Water level = min(left_height, right_height) - valley_height
     * Water: width × height
     *
     * The algorithm essentially "fills up" valleys layer by layer, always ensuring water is contained between proper boundaries.
     *
     * Time Complexity: O(n)
     * - Each element is pushed onto the stack exactly once
     * - Each element is popped from the stack at most once
     * - Total stack operations: 2n (n pushes + at most n pops)
     * Loop runs n times
     * Overall: O(n)
     *
     * Space Complexity: O(n)
     *
     * - In worst case, stack stores all elements
     * - This happens when array is in strictly decreasing order (e.g., [5,4,3,2,1])
     * - No water gets trapped, but all elements remain in stack
     * Overall: O(n)
     *
    * */
    public int trap(int[] height) {
        int answer = 0;
        Stack<Pair> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            int element = height[i];
            if (stack.isEmpty()||element<=stack.peek().data){
                stack.push(new Pair(element, i));
            } else if (element > stack.peek().data) {
                while (!stack.isEmpty()&&element>stack.peek().data) {
                    Pair pair = stack.pop();
                    int currentHeight = 0;
                    if(!stack.isEmpty()) currentHeight = Math.min(stack.peek().data,element);
                    int currWidth = 0;
                    if(!stack.isEmpty()) currWidth = i - stack.peek().index - 1;
                    int result = (currentHeight - pair.data)*(currWidth);
                    if (result > 0) answer += result;
                }
                stack.push(new Pair(element, i));
            }
        }
        return answer;
    }

    /**
     * Solution 2 : Intuition: Pre-compute Left and Right Maximum Heights
     *
     * Instead of using a stack, pre-calculate the maximum height to the left and right of each position,
     * then determine water level at each index.
     *
     * Key Insight:
     * Water trapped at any position = min(max_left, max_right) - current_height
     *
     * - Water can only be trapped if there are taller bars on both sides
     * - The water level is limited by the shorter of the two boundaries
     *
     * Algorithm Steps:
     *
     * 1. Left Pass: For each position, store the maximum height seen to its left
     * leftPeeks[i] = maximum height among height[0...i-1]
     *
     * 2. Right Pass: For each position, store the maximum height seen to its right
     * rightPeeks[i] = maximum height among height[i+1...n-1]
     *
     * 3. Calculate Water: For each position, water trapped =
     * min(leftPeeks[i], rightPeeks[i]) - height[i]
     *
     * Complexity:
     * Time: O(3n) = O(n) - three separate loops
     * Space: O(2n) = O(n) - two arrays for left/right maximums
    * */
    public int trap2(int[] height) {
        int answer = 0;
        int[] leftPeeks = new int[height.length];
        int[] rightPeeks = new int[height.length];
        int maxLeft = 0;
        int maxRight = 0;

        for (int i = 1; i < height.length; i++) {
            leftPeeks[i] = Math.max(maxLeft,height[i-1]);
            maxLeft = Math.max(maxLeft,height[i-1]);
        }

        for (int i = height.length-2; i >= 0; i--) {
            rightPeeks[i] = Math.max(maxRight,height[i+1]);
            maxRight = Math.max(maxRight,height[i+1]);
        }

        for (int i = 0; i < height.length; i++) {
            int base = Math.min(leftPeeks[i],rightPeeks[i]);
            int res = base - height[i];
            if (res>0) answer+=res;
        }

        return answer;
    }

    public static void main(String[] args) {
        _9_TrappingRainWater obj = new _9_TrappingRainWater();
        int[] nums2 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("The Answer : " + obj.trap2(nums2));
    }
}