package linked_list;

/**
 * Problem Link : https://leetcode.com/problems/add-two-numbers/
 *
 * 2. Add Two Numbers
 * Solved
 * Medium
 * Topics
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2:
 *
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 *
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 * Constraints:
 *     The number of nodes in each linked list is in the range [1, 100].
 *     0 <= Node.val <= 9
 *     It is guaranteed that the list represents a number that does not have leading zeros.
 *
* */

public class _6_AddTwoNumbers extends SinglyLinkedList {

    /**
     * ###### First solution : This problem has no optimal solution #########
     *
     *
     * Time Complexity: O(max(m, n))
     *
     * Where m and n are the lengths of the two linked lists
     * The while loop runs until both lists are exhausted, so it runs for max(m, n) iterations
     * Each iteration does constant time operations
     *
     * Space Complexity: O(max(m, n))
     *
     * Creating a new linked list to store the result.
     * The result list will have at most max(m, n) + 1 nodes (the +1 is for potential final carry)
     * Not counting the input space, you're using O(max(m, n)) extra space
    * */
    public Node addTwoNumbers(Node l1, Node l2) {
        Node dummy = new Node(Integer.MIN_VALUE);
        Node result = dummy;
        int reminder = 0;

        while (l1!=null||l2!=null){

            int value1 = 0;
            int value2 = 0;
            if(l1!=null) value1 = l1.data;
            if(l2!=null) value2 = l2.data;
            int sum = value1+value2+reminder;
            int newNum = sum%10;

            if (sum>=10) reminder = 1;
            else reminder = 0;

            result.next = new Node(newNum);

            result = result.next;
            if(l1!=null) l1 = l1.next;
            if(l2!=null) l2 = l2.next;

            if (l1==null&&l2==null&&reminder>0){
                result.next = new Node(reminder);
                break;
            }

        }

        return dummy.next;
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        Node one = new Node(4);
        Node two = new Node(3);
        head.next = one;
        one.next = two;
        Node head1 = new Node(5);
        Node one1 = new Node(6);
        Node two1 = new Node(4);
        head1.next = one1;
        one1.next = two1;
        _6_AddTwoNumbers object = new _6_AddTwoNumbers();

        traverseList(head);
        traverseList(head1);
        traverseList(object.addTwoNumbers(head,head1));

    }

}
