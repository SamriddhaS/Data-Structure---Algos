package stack_queue;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem Link : https://takeuforward.org/plus/dsa/problems/next-smaller-element
 *
 *
 */
public class _8_PreviousSmallerElement {

    public int[] previousSmallerElements(int[] arr) {
        int[] answer = new int[arr.length];
        Arrays.fill(answer,-1);
        Stack<Pair> stack = new Stack<>();
        for (int i=arr.length-1;i>=0;i--){
            int element = arr[i];
            if (stack.isEmpty()||element>stack.peek().data){
                stack.push(new Pair(element,i));
            }else {
                while(!stack.isEmpty()&&element<stack.peek().data){
                    Pair popped = stack.pop();
                    answer[popped.index] = element;
                }
                stack.push(new Pair(element,i));
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        _8_PreviousSmallerElement obj = new _8_PreviousSmallerElement();
        int[] nums2 = {5,2,6,1};
        System.out.println("The Answer : " + Arrays.toString(obj.previousSmallerElements(nums2)));
        int[] nums3 = {4, 8, 5, 2, 25};
        System.out.println("The Answer : " + Arrays.toString(obj.previousSmallerElements(nums3)));
        int[] nums4 = {10, 9, 8, 7};
        System.out.println("The Answer : " + Arrays.toString(obj.previousSmallerElements(nums4)));
        int[] nums5 = {1, 2, 3, 4, 5};
        System.out.println("The Answer : " + Arrays.toString(obj.previousSmallerElements(nums5)));

    }
}