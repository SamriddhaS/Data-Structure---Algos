package stack_queue;

import java.util.Arrays;
import java.util.Stack;

public class TrappingRainWater {

//    public int nextGreaterElement2(int[] nums1) {
//        int answer = 0;
//        Stack<Pair> stack = new Stack<>();
//        for (int i = 0; i < nums1.length; i++) {
//            int element = nums1[i];
//            if (stack.isEmpty()||element<=stack.peek().data) stack.push(new Pair(element, i));
//            else if (element > stack.peek().data) {
//                int base = Math.min(stack.peek().data, element);
//                int subArrayStartIndex = stack.peek().index;
//                int j = i;
//                while (j >= subArrayStartIndex) {
//                    int result = base - nums1[j];
//                    if (result > 0) answer += result;
//                    j--;
//                }
//                stack.pop();
//                stack.push(new Pair(element, i));
//            }
//        }
//        return answer;
//    }

    public int nextGreaterElement2(int[] nums1) {
        int answer = 0;
        Stack<Pair> stack = new Stack<>();
        int base = 0;
        for (int i = 0; i < nums1.length; i++) {
            int element = nums1[i];
            if (stack.isEmpty()||element<=stack.peek().data){
                stack.push(new Pair(element, i));

            } else if (element > stack.peek().data) {
                while (!stack.isEmpty()&&element>stack.peek().data) {
                    Pair pair = stack.pop();
                    int result = base - pair.data;
                    if (result > 0) answer += result;
                }
                stack.push(new Pair(element, i));
                base = element;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        TrappingRainWater obj = new TrappingRainWater();
        int[] nums2 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("The Answer : " + obj.nextGreaterElement2(nums2));
    }
}
