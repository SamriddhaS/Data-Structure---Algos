package linked_list;

/**
 * Problem Link : https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
 * Explanation :
 *
 * Swap Nodes in Pairs
 *
 * Problem Description
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the
 * problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 *
 * Example 3:
 * Input: head = [1]
 * Output: [1]
 *
 * Example 4
 * Input: head = [1,2,3,4,5]
 * Output: [2,1,4,3,5]
 *
 * Explanation:
 * Swap (1,2) → (2,1)
 * Swap (3,4) → (4,3)
 * Node 5 remains in place (no pair to swap with)
 * Result: 2→1→4→3→5
 *
* */

public class _9_ReverseInPairs extends SinglyLinkedList {

    /**
     * Time Complexity: O(n)
     * Single pass through the linked list
     * Each node is visited exactly once
     * Constant time operations (pointer manipulations) for each node
     *
     * Space Complexity: O(1)
     *
     * Uses only a fixed number of extra variables (temp, preTail, tempo)
     * No additional data structures or recursive calls
     * Space usage remains constant regardless of input size
     *
    * */
    public ListNode reverseInPairs(ListNode head) {
        ListNode temp = head;
        ListNode preTail = null;
        while(temp!=null&&temp.next!=null){
            ListNode tempo = temp.next.next; //3
            if (preTail!=null) preTail.next = temp.next;
            temp.next.next = temp; // 2->1
            if (temp==head) head = temp.next;
            temp.next = tempo; // 2->1->3->4

            preTail = temp;
            temp = temp.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode one = new ListNode(2);
        ListNode two = new ListNode(3);
        ListNode three = new ListNode(4);
        ListNode four = new ListNode(5);
        ListNode five = new ListNode(6);
        head.next = one;
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        _9_ReverseInPairs object = new _9_ReverseInPairs();

        traverseList(head);
        ListNode newhead = object.reverseInPairs(head);
        traverseList(newhead);

    }

}
