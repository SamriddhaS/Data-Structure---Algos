package stack_queue;


import java.util.*;

/**
 * Problem Link : https://leetcode.com/problems/sliding-window-maximum/
 * Video Explanation : https://www.youtube.com/watch?v=DfljaUwZsOk
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
 *
 */
public class _16_SlidingWindowMaximum {

    /**
     * Solution 1 : Brute Force - Will give TLE.
     * Time Complexity: O(n*k)
     * - Outer loop: O(n) iterations through the array
     * - Inner operations: Finding max in list of size k takes O(k)
     * - Total: O(n*k)
     *
     * Space Complexity: O(k)
     * - ArrayList maintains at most k elements
     * - Answer array is O(n-k+1) but that's output, so auxillary space is O(k)
    * */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] answer = new int[nums.length-k+1]; // 8 - 3 = 5
        int index=0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (list.size()<k-1){
                list.add(nums[i]);
            }else{
                // Find and add the current max before adding the new element.
                if (list.size()==k) list.remove(0);
                list.add(nums[i]);
                int max = list.get(0);
                for (int j = 1; j < list.size(); j++) {
                    max = Math.max(max, list.get(j));
                }
                answer[index]=max;
                index++;
            }
        }
        return answer;
    }

    /**
     * Solution 2 : Optimal using monotonic queue.
     *
     * Time Complexity: O(n)
     *
     * - Each element is added to deque once: O(n)
     * - Each element is removed from deque at most once: O(n)
     * - Total: O(n)
     *
     * Space Complexity: O(k)
     * - Deque stores at most k indices (window size)
     * - Answer array is output,we don't count that one, so auxiliary space is O(k)
     *
    * */
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int[] answer = new int[nums.length-k+1]; // 8 - 3 = 5
        int ind=0;
        Deque<Integer> deque = new LinkedList<>();
        int left=0,right=0;
        while (right<nums.length) {
            int ele = nums[right];
            if (deque.isEmpty()||ele<nums[deque.peekLast()]){
                deque.addLast(right);
            }else{
                while(!deque.isEmpty()&&ele>nums[deque.peekLast()]){
                    deque.removeLast();
                }
                deque.addLast(right);
            }

            // move both the pointers to shift the window.
            if (right>=k-1){

                // save the current max to create the answer array.
                answer[ind] = nums[deque.peekFirst()];
                ind++;

                if (nums[deque.peekFirst()]==nums[left]){
                    deque.removeFirst();
                }

                //move the window one step.
                left++;
            }
            right++;
        }
        return answer;
    }

    public static void main(String[] args) {
        _16_SlidingWindowMaximum obj = new _16_SlidingWindowMaximum();

        int[] input = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println("The Answer : " + Arrays.toString(obj.maxSlidingWindow(input, k)));
        System.out.println("The Answer : " + Arrays.toString(obj.maxSlidingWindow1(input, k)));

        int[] input1 = {1};
        int k1 = 1;
        System.out.println("The Answer : " + Arrays.toString(obj.maxSlidingWindow(input1, k1)));
        System.out.println("The Answer : " + Arrays.toString(obj.maxSlidingWindow1(input1, k1)));

    }
}