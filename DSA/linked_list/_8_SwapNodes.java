package linked_list;

/**
 * Problem Link : https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
 * Explanation :
 *
 *  1721. Swapping Nodes in a Linked List
 * You are given the head of a linked list, and an integer k.
 *
 * Return the head of the linked list after swapping the values of the kth node from the beginning and
 * the kth node from the end (the list is 1-indexed).
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 *
 * Constraints:
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 105
 * 0 <= Node.val <= 100
* */

public class _8_SwapNodes extends SinglyLinkedList {

    /**
     * Time Complexity: O(n)
     *
     * First loop: O(n) to find list size
     * Second loop: O(n) to find kth node from end
     * Value swap: O(1)
     * Total: O(n)
     *
     * Space Complexity: O(1)
     *
     * Uses only constant extra variables (temp, preNode1, preNode2, size, pos, data)
     * No additional data structures that scale with input size
     *
    * */
    public Node swapNodes(Node head, int k) {
        Node temp = head;
        Node preNode1 = head;
        Node preNode2 = head;
        int size=0;
        int pos=1;
        while (temp!=null){
            if(pos<k){
                preNode1 = preNode1.next;
            }
            temp = temp.next;
            pos++;
            size++;
        }

        pos=1;
        while (pos<=size-k){
            preNode2 = preNode2.next;
            pos++;
        }

        int data = preNode1.data;
        preNode1.data = preNode2.data;
        preNode2.data = data;

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

        _8_SwapNodes object = new _8_SwapNodes();

        traverseList(head);
        object.swapNodes(head,2);
        traverseList(head);

    }

}
