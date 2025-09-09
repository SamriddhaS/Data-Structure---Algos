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

public class _9_ReverseInPairs extends SinglyLinkedList {

    /**
     *
    * */
    public Node reverseInPairs(Node head) {
        Node temp = head;
        while(temp!=null&&temp.next!=null){
            Node tempo = temp.next.next;
            Node newHead = temp.next;
            temp.next.next = temp;
            temp.next = tempo;
            head = newHead;
        }
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node one = new Node(2);
        Node two = new Node(3);
        Node three = new Node(4);
        Node four = new Node(5);
        Node five = new Node(6);
        head.next = one;
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        _9_ReverseInPairs object = new _9_ReverseInPairs();

        traverseList(head);
        object.reverseInPairs(head);
        traverseList(head);

    }

}
