package stack_queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Problem Link : https://takeuforward.org/plus/dsa/problems/next-smaller-element
 *
 *
 */
public class _7_NextSmallerElement {

    public int[] nextSmallerElements(int[] arr) {
        int[] answer = new int[arr.length];
        Arrays.fill(answer,-1);
        Stack<Pair> stack = new Stack<>();
        for (int i=0;i<arr.length;i++){
            int ele = arr[i];
            if (stack.isEmpty()||ele>stack.peek().data){
                stack.push(new Pair(ele,i));
            }else {
                while (!stack.isEmpty()&&ele<stack.peek().data){
                    Pair popped = stack.pop();
                    answer[popped.index] = ele;
                }
                stack.push(new Pair(ele,i));
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        _7_NextSmallerElement obj = new _7_NextSmallerElement();
        //int[] nums2 = {1,2,3,4,3};
        //int[] nums2 = {5,4,3,2,1};
        int[] nums2 = {5,2,6,1};
        System.out.println("The Answer : " + Arrays.toString(obj.nextSmallerElements(nums2)));
        int[] nums3 = {4, 8, 5, 2, 25};
        System.out.println("The Answer : " + Arrays.toString(obj.nextSmallerElements(nums3)));
        int[] nums4 = {10, 9, 8, 7};
        System.out.println("The Answer : " + Arrays.toString(obj.nextSmallerElements(nums4)));
        int[] nums5 = {1, 2, 3, 4, 5};
        System.out.println("The Answer : " + Arrays.toString(obj.nextSmallerElements(nums5)));

    }
}