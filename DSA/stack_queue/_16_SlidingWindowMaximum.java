package stack_queue;

import stack_queue.basics.PriorityQueueUsingArray;

import java.util.*;

/**
 * Problem Link : https://leetcode.com/problems/sliding-window-maximum/
 * Video Explanation :
 *
 * 239. Sliding Window Maximum
 * Hard
 *
 * You are given an array of integers nums, there is a sliding
 * window of size k which is moving from the very left of the
 * array to the very right. You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 *
 * Return the max sliding window.
 *
 * Example 1:
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Constraints:
 *     1 <= nums.length <= 105
 *     -104 <= nums[i] <= 104
 *     1 <= k <= nums.length
 */
public class _16_SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] answer = new int[nums.length-k];
        PriorityQueueUsingArray queue = new PriorityQueueUsingArray(k);
        for (int i = 0; i < nums.length; i++) {
            if (queue.size()<k){
                queue.enqueue(nums[i],nums[i]);
            }else{
                queue.dequeue();
                queue.enqueue(nums[i],nums[i]);
            }

        }
        return answer;
    }

    public static void main(String[] args) {
        _16_SlidingWindowMaximum obj = new _16_SlidingWindowMaximum();

        int[] input = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println("The Answer : " + Arrays.toString(obj.maxSlidingWindow(input, k)));

    }
}