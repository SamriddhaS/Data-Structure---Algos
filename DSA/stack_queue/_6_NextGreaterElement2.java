package stack_queue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * Problem Link :
 * Video Explanation :
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


    public int[] nextGreaterElementUsingStack2(int[] nums1) {
        int len = nums1.length;
        Stack<Pair> stack = new Stack<>();
        int[] answer = new int[len];
        Arrays.fill(answer, -1);
//        for (int i = len - 1; i >= 0; i--) {
//            int num = nums1[i];
//            if (stack.isEmpty()) {
//                stack.push(new Pair(num, i));
//            } else if (num >= stack.peek().data) {
//                while (!stack.isEmpty() && num >= stack.peek().data) {
//                    Pair poppedEle = stack.pop();
//                    if (answer[poppedEle.index]==-1) answer[poppedEle.index] = num;
//                }
//                if(!stack.isEmpty()) {
//                    Pair topEle = stack.peek();
//                    answer[i] = topEle.data;
//                }
//                stack.push(new Pair(num, i));
//            } else {
//                Pair topEle = stack.peek();
//                answer[i] = topEle.data;
//                stack.push(new Pair(num, i));
//            }
//        }
        for (int i = 0; i < len; i++) {
            int num = nums1[i];
            if (stack.isEmpty()) {
                stack.push(new Pair(num, i));
            } else if (num >= stack.peek().data) {
                while (!stack.isEmpty() && num >= stack.peek().data) {
                    Pair poppedEle = stack.pop();
                    if (answer[poppedEle.index]==-1) answer[poppedEle.index] = num;
                }
                if(!stack.isEmpty()) {
                    Pair topEle = stack.peek();
                    answer[i] = topEle.data;
                }
                stack.push(new Pair(num, i));
            } else {
                Pair topEle = stack.peek();
                answer[i] = topEle.data;
                stack.push(new Pair(num, i));
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        _6_NextGreaterElement2 obj = new _6_NextGreaterElement2();
        int[] nums2 = {1,2,3,4,3}; // Expected - [-1,5,5,5,5] output - [-1,5,4,3,2]

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