package DSA.stack_queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/implement-queue-using-stacks/description/
 * Video Explanation : https://www.youtube.com/watch?v=eanwa3ht3YQ
 *
 * 232. Implement Queue using Stacks
 *
 * Implement a first in first out (FIFO) queue using only two stacks. The implemented queue
 * should support all the functions of a normal queue (push, peek, pop, and empty).
 *
 * Implement the MyQueue class:
 *
 *     void push(int x) Pushes element x to the back of the queue.
 *     int pop() Removes the element from the front of the queue and returns it.
 *     int peek() Returns the element at the front of the queue.
 *     boolean empty() Returns true if the queue is empty, false otherwise.
 *
 * Notes:
 *
 *     You must use only standard operations of a stack, which means only push to top, peek/pop from top,
 *     size, and is empty operations are valid.
 *     Depending on your language, the stack may not be supported natively.
 *     You may simulate a stack using a list or deque (double-ended queue) as long as you use
 *     only a stack's standard operations.
 *
 * Example 1:
 *
 * Input
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output
 * [null, null, null, 1, 1, false]
 *
 * Explanation
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 *
 *
 *
 * Constraints:
 *     1 <= x <= 9
 *     At most 100 calls will be made to push, pop, peek, and empty.
 *     All the calls to pop and peek are valid.
 *
 * Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity?
 * In other words, performing n operations will take overall O(n) time even if one
 * of those operations may take longer.
 *
* */
public class _2_ImplementQueueUsingStack {

    public _2_ImplementQueueUsingStack() {

    }

    /**
    * Solution 1 : Using two stacks.
    * */
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    /**
     * push(int x): O(n) - transfers all elements from stack1 to stack2,
     * adds new element, then transfers all back to stack1
    * */
    public void push(int x) {
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        stack2.push(x);
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
    }

    /**
    * pop(): O(1) - single pop operation from stack1
    * */
    public int pop() {
        if (stack1.isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return stack1.pop();
    }

    /**
    * peek(): O(1) - single peek operation on stack1
    * */
    public int peek() {
        if (stack1.isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return stack1.peek();
    }

    /**
    * empty(): O(1) - single isEmpty check
    * */
    public boolean empty() {
        return stack1.isEmpty();
    }

    /**
     * Solution 2 : Better solution with average complexity of O(1)
     * */
    Stack<Integer> stack3 = new Stack<>();
    Stack<Integer> stack4 = new Stack<>();


    /**
    * _push(int x): O(1) - single push to stack3
    * */
    public void _push(int x) {
        stack3.push(x);
    }

    /**
    * _pop(): O(1) amortized - worst case O(n) when stack4 is empty, but amortized O(1)
    * */
    public int _pop() {
        if (_empty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        if (stack4.isEmpty()){
            while (!stack3.isEmpty()) stack4.push(stack3.pop());
        }
        return stack4.pop();
    }

    /**
    * _peek(): O(1) amortized - worst case O(n) when stack4 is empty, but amortized O(1)
    * */
    public int _peek() {
        if (_empty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        if (stack4.isEmpty()){
            while (!stack3.isEmpty()) stack4.push(stack3.pop());
        }
        return stack4.peek();
    }

    /**
    * _empty(): O(1) - checks both stacks
    * */
    public boolean _empty() {
        return stack3.isEmpty()&&stack4.isEmpty();
    }


    public static void main(String[] args) {
        _2_ImplementQueueUsingStack myStack = new _2_ImplementQueueUsingStack();
        myStack._push(12);
        myStack._push(14);
        System.out.println(myStack._peek());
        myStack._push(18);
        myStack._push(21);
        System.out.println(myStack._peek());
        System.out.println(myStack._pop());
        System.out.println(myStack._pop());
        System.out.println(myStack._pop());
        System.out.println(myStack._pop());

    }
}
