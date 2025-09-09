package linked_list;

/**
 * Problem Link : https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/
 *
 * 2095. Delete the Middle Node of a Linked List
 * Solved
 * Medium
 * Topics
 * premium lock iconCompanies
 * Hint
 *
 * You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
 *
 * The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.
 *
 *     For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
 *
 * Example 1:
 *
 * Input: head = [1,3,4,7,1,2,6]
 * Output: [1,3,4,1,2,6]
 * Explanation:
 * The above figure represents the given linked list. The indices of the nodes are written below.
 * Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
 * We return the new list after removing this node.
 *
 * Example 2:
 *
 * Input: head = [1,2,3,4]
 * Output: [1,2,4]
 * Explanation:
 * The above figure represents the given linked list.
 * For n = 4, node 2 with value 3 is the middle node, which is marked in red.
 *
 * Example 3:
 *
 * Input: head = [2,1]
 * Output: [2]
 * Explanation:
 * The above figure represents the given linked list.
 * For n = 2, node 1 with value 1 is the middle node, which is marked in red.
 * Node 0 with value 2 is the only node remaining after removing node 1.
 *
 * Constraints :
 *     The number of nodes in the list is in the range [1, 105].
 *     1 <= Node.val <= 105
 *
* */

public class _5_DeleteMiddle extends SinglyLinkedList {


    /**
    * Time Complexity: O(n)
     *
     * The algorithm uses the two-pointer technique (fast and slow pointers)
     * The fast pointer moves 2 steps at a time, while the slow pointer moves 1 step
     * In the worst case, the fast pointer traverses the entire list once
     * Since the fast pointer moves twice as fast, it will traverse roughly n/2 nodes, but this is still O(n) time complexity
     *
     * Space Complexity: O(1)
     *
     * Algorithm Analysis:
     * The solution cleverly uses a dummy node approach where:
     *
     * slow starts one position before head (pointing to a dummy node)
     * When fast reaches the end, slow will be pointing to the node just before the middle node
     * This allows direct deletion of the middle node with slow.next = slow.next.next
    * */
    public Node deleteMiddle(Node head) {
        if(head==null) return head;
        Node fast = head;
        Node slow = new Node(Integer.MIN_VALUE);
        slow.next = head;

        // Edge case only 1 element present.
        if(fast.next==null){
            return null;
        }

        while (fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        slow.next = slow.next.next;

        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node one = new Node(2);
        Node two = new Node(3);
        Node three = new Node(4);
        Node four = new Node(5);
        Node five = new Node(6);
        Node six = new Node(7);
        head.next = one;
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        Node head1 = new Node(1);
        Node one1 = new Node(2);
        Node two1 = new Node(3);
        head1.next = one1;
        one1.next = two1;
        _5_DeleteMiddle object = new _5_DeleteMiddle();

        traverseList(head);
        traverseList(object.deleteMiddle(head));
        traverseList(head1);
        traverseList(object.deleteMiddle(head1));

    }

}
