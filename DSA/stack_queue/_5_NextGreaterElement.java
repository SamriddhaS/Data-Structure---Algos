package stack_queue;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/min-stack/
 * Video Explanation :
 *
* */
public class _5_NextGreaterElement {

    /**
     * Brute Force Approach : Pick every Element of nums1 and search for the next greater item
     * nums2 array.
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

    public int[] nextGreaterElementUsingStack(int[] nums1, int[] nums2) {
        int[] nextGreaterElementArr = new int[nums2.length];
        Arrays.fill(nextGreaterElementArr, -1);
        Stack<Integer> decreasingMonoStack = new Stack<>();
        for(int i= nums2.length-1; i>=0;i--){
            int current = nums2[i];
            if (decreasingMonoStack.isEmpty()){
                decreasingMonoStack.push(current);
            }else if (current>=decreasingMonoStack.peek()){
                while (!decreasingMonoStack.isEmpty()&&current>=decreasingMonoStack.peek()) decreasingMonoStack.pop();
                if (!decreasingMonoStack.isEmpty()){
                    nextGreaterElementArr[i] = decreasingMonoStack.peek();
                }
                decreasingMonoStack.push(current);
            }
            else {
                nextGreaterElementArr[i] = decreasingMonoStack.peek();
                decreasingMonoStack.push(current);
            }
        }
        return nextGreaterElementArr;
    }

    public static void main(String[] args) {
        _5_NextGreaterElement obj = new _5_NextGreaterElement();
        int[] nums1 = {4,1,2};
        int[] nums2 = {2,1,2,4,3};
        System.out.println("The Answer : "+ Arrays.toString(obj.nextGreaterElementUsingStack(nums1, nums2)));
    }
}