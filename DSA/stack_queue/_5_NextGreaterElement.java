package stack_queue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/next-greater-element-i/description/
 * Video Explanation : https://www.youtube.com/watch?v=e7XQLtOQM3I
 *
 * 496. Next Greater Element I
 *
 * The next greater element of some element x in an array is the first greater element that
 * is to the right of x in the same array.
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater
 * element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 *
 * Example 1:
 *
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 *
 * Example 2:
 *
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums1.length <= nums2.length <= 1000
 *     0 <= nums1[i], nums2[i] <= 104
 *     All integers in nums1 and nums2 are unique.
 *     All the integers of nums1 also appear in nums2.
 *
 *
 * Follow up: Could you find an O(nums1.length + nums2.length) solution?
 *
* */
public class _5_NextGreaterElement {

    /**
     * Brute Force Approach : Pick every Element of nums1 and search for the next greater item
     * nums2 array.
     *
     * Time Complexity: O(nums1.length × nums2.length)
     * For each element in nums1, scan through entire nums2 array
     * Nested loops: outer O(nums1.length), inner O(nums2.length)
     *
     * Space Complexity: O(1)
     * Only using a few variables, no extra data structures
     * Output array doesn't count toward space complexity
     *
    * */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] answer = new int[nums1.length];
        for(int i=0 ;i<nums1.length;i++){
            int number = nums1[i];
            int nextGreater = -1;
            boolean elementFound=false;
            for (int j:nums2){
                if (!elementFound) elementFound = number==j;
                if (elementFound){
                    if (j>number) {
                        nextGreater=j;
                        break;
                    }
                }
            }
            answer[i] = nextGreater;
        }
        return answer;
    }

    /**
     * Follow-Up Solution :
     * Solution 2 (Monotonic Stack + HashMap) :
     *
     * Time Complexity: O(nums1.length + nums2.length)
     *
     * First loop: O(nums2.length) - each element pushed/popped at most once
     * Second loop: O(nums1.length) - HashMap lookup is O(1)
     * Total: O(nums1.length + nums2.length)
     *
     * Space Complexity: O(nums2.length)
     *
     * HashMap: stores at most nums2.length entries
     * Stack: stores at most nums2.length elements
     * Both scale with nums2 size
    * */
    public int[] nextGreaterElementUsingStack(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        Stack<Integer> decreasingMonoStack = new Stack<>();
        for(int i= nums2.length-1; i>=0;i--){
            int current = nums2[i];
            if (decreasingMonoStack.isEmpty()){
                decreasingMonoStack.push(current);
            }else if (current>=decreasingMonoStack.peek()){
                while (!decreasingMonoStack.isEmpty()&&current>=decreasingMonoStack.peek()) decreasingMonoStack.pop();
                if (!decreasingMonoStack.isEmpty()){
                    hashMap.put(current,decreasingMonoStack.peek());
                }
                decreasingMonoStack.push(current);
            }
            else {
                hashMap.put(current,decreasingMonoStack.peek());
                decreasingMonoStack.push(current);
            }
        }
        int[] answers = new int[nums1.length];
        for (int i=0;i< nums1.length;i++){
            int num = nums1[i];
            if(hashMap.containsKey(num)) answers[i] = hashMap.get(num);
            else answers[i] = -1;
        }

        return answers;
    }

    public static void main(String[] args) {
        _5_NextGreaterElement obj = new _5_NextGreaterElement();
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        System.out.println("The Answer : "+ Arrays.toString(obj.nextGreaterElementUsingStack(nums1, nums2)));
    }
}