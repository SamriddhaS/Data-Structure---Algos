package stack_queue;

import java.util.*;

/**
 * Problem Link : https://leetcode.com/problems/implement-stack-using-queues/description/
 * Video Explanation : https://www.youtube.com/watch?v=rW4vm0-DLYc
 *
 * 225. Implement Stack using Queues
 * Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should
 * support all the functions of a normal stack (push, top, pop, and empty).
 *
 * Implement the MyStack class:
 *
 *     void push(int x) Pushes element x to the top of the stack.
 *     int pop() Removes the element on the top of the stack and returns it.
 *     int top() Returns the element on the top of the stack.
 *     boolean empty() Returns true if the stack is empty, false otherwise.
 *
 * Notes:
 *
 *     You must use only standard operations of a queue, which means that only push to back,
 *     peek/pop from front, size and is empty operations are valid.
 *     Depending on your language, the queue may not be supported natively.
 *     You may simulate a queue using a list or deque (double-ended queue) as long
 *     as you use only a queue's standard operations.
 *
 * Example 1:
 *
 * Input
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output
 * [null, null, null, 2, 2, false]
 *
 * Explanation
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // return 2
 * myStack.pop(); // return 2
 * myStack.empty(); // return False
 *
 *
 * Constraints:
 *
 *     1 <= x <= 9
 *     At most 100 calls will be made to push, pop, top, and empty.
 *     All the calls to pop and top are valid.
 *
 *
 * Follow-up: Can you implement the stack using only one queue?
* */
public class _1_ImplementStackUsingQueue {

    public _1_ImplementStackUsingQueue() {

    }

    /**
    * Solution 1 : Using two queues.
    * */
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    /**
    * push(int x): O(n) - transfers all elements from queue1 to queue2, then back to queue1
    * */
    public void push(int x) {
        queue2.add(x);
        while(!queue1.isEmpty()){
            queue2.add(queue1.remove());
        }
        while (!queue2.isEmpty()){
            queue1.add(queue2.remove());
        }
    }

    /**
    * pop(): O(1) - single remove operation from front of queue1
    * */
    public int pop() {
        if (queue1.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return queue1.remove();
    }

    /**
    * top(): O(1) - single peek operation on queue1
    * */
    public int top() {
        if (queue1.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return queue1.peek();
    }

    /**
    * empty(): O(1) - single isEmpty check
    * */
    public boolean empty() {
        return queue1.isEmpty();
    }



    /**
     * Solution 2 : Using single queue.
     * */
    Queue<Integer> queue3 = new LinkedList<>();

    /**
     * _push(int x): O(n) - adds new element, then rotates all previous elements to maintain stack order
     * */
    public void _push(int x) {
        queue3.add(x);
        int index=0;
        while(index<queue3.size()-1){
            queue3.add(queue3.remove());
            index++;
        }
    }

    /**
     * pop(): O(1) - single remove operation from front
     * */
    public int _pop() {
        if (queue3.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return queue3.remove();
    }

    /**
     * top(): O(1) - single peek operation
     * */
    public int _top() {
        if (queue3.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return queue3.peek();
    }

    /**
     * empty(): O(1) - single isEmpty check
     * */
    public boolean _empty() {
        return queue3.isEmpty();
    }

    public static void main(String[] args) {
        _1_ImplementStackUsingQueue myStack = new _1_ImplementStackUsingQueue();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top()); // return 2
        System.out.println(myStack.pop()); // return 2
        System.out.println(myStack.empty()); // return False

        myStack._push(1);
        myStack._push(2);
        System.out.println(myStack._top()); // return 2
        System.out.println(myStack._pop()); // return 2
        myStack._push(22);
        System.out.println(myStack._top());
        myStack._push(7);
        System.out.println(myStack._top());
        System.out.println(myStack._pop());
        System.out.println(myStack._pop());
        System.out.println(myStack._pop());
        System.out.println(myStack._pop()); // return

    }
}
