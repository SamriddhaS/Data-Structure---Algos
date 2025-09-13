package stack_queue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/next-greater-element-ii/submissions/1769743005/
 * Video Explanation : https://www.youtube.com/watch?v=e7XQLtOQM3I
 * (core logic is same so attaching same link of the NextGreaterElement, only the two pass thing we need to add)
 * <p>
 * 503. Next Greater Element II
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
 * return the next greater number for every element in nums.
 * <p>
 * The next greater number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,1]
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number.
 * The second 1's next greater number needs to search circularly, which is also 2.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4,3]
 * Output: [2,3,4,-1,4]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 *
 *
 */
public class _6_NextGreaterElement2 {


    /**
     * Brute Force Approach :
     *
     *
     */
    public int[] nextGreaterElement2(int[] nums1) {
        int len = nums1.length;
        int[] answer = new int[len];
        Arrays.fill(answer, -1);
        for (int i = 0; i < len; i++) {
            int number = nums1[i];
            for (int j = i + 1; j < len + i; j++) {
                int pos = j % len;
                int number2 = nums1[pos];
                if (number2 > number) {
                    answer[i] = number2;
                    break;
                }
            }
        }
        return answer;
    }


    /**
     * Optimal Solution :
     * Use a decreasing monotonic stack to track elements waiting for their next greater element.
     * Process the array twice to handle the circular nature.
     *
     * Algorithm Logic
     * Two-Pass Approach:
     *
     * First Pass (i = 0 to n-1): Build stack, resolve elements that find their next greater element normally
     * Second Pass (i = n to 2n-1): Use circular indexing to resolve remaining elements in stack
     *
     * Stack Operations:
     * Pop: When current > stack.top(), pop smaller elements and set their answer to current
     * Push: Only in first pass - add current element to stack for future processing
     *
     * Time Complexity: O(n)
     * - Loop runs 2n times, but each element is pushed once and popped once
     * - Total operations: n pushes + n pops = 2n = O(n)
     *
     * Space Complexity: O(n)
     * - Stack can hold at most n elements (worst case: decreasing array)
     * - Answer array: O(n)
     * - Total: O(n)
    * */
    public int[] nextGreaterElementUsingStack2(int[] nums) {

        Stack<Pair> decreasingMonoStack = new Stack<>();
        int[] answers = new int[nums.length];
        Arrays.fill(answers,-1);

        for (int i=0;i<2 * nums.length;i++) {
            int index = i % nums.length;
            Pair current = new Pair(nums[index],index);
            if (i<nums.length){
                // 1st pass
                if (decreasingMonoStack.isEmpty()) {
                    decreasingMonoStack.push(current);
                } else if (current.data > decreasingMonoStack.peek().data) {
                    while (!decreasingMonoStack.isEmpty() && current.data > decreasingMonoStack.peek().data) {
                        Pair ele = decreasingMonoStack.pop();
                        answers[ele.index] = current.data;
                    }
                    decreasingMonoStack.push(current);
                } else {
                    decreasingMonoStack.push(current);
                }
            }else {
                // 2st pass
                while (!decreasingMonoStack.isEmpty() && current.data > decreasingMonoStack.peek().data) {
                    Pair ele = decreasingMonoStack.pop();
                    answers[ele.index] = current.data;
                }
            }
        }

        return answers;
    }

    public static void main(String[] args) {
        _6_NextGreaterElement2 obj = new _6_NextGreaterElement2();
        //int[] nums2 = {1,2,3,4,3};
        //int[] nums2 = {5,4,3,2,1};
        int[] nums2 = {1,1,1,1,1};
        //int[] nums2 = {1,2,3,4,5,6,5,4,5,1,2,3};

        System.out.println("The Answer : " + Arrays.toString(obj.nextGreaterElementUsingStack2(nums2)));
    }
}

class Pair {
    int data;
    int index;

    Pair(int data, int index) {
        this.data = data;
        this.index = index;
    }
}