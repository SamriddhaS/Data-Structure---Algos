package linked_list;

/**
 * Problem Link : https://leetcode.com/problems/delete-node-in-a-linked-list/description/
 * Explanation : https://www.youtube.com/watch?v=icnp4FJdZ_c&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=32
 *
 * There is a singly-linked list head and we want to delete a node node in it.
 * You are given the node to be deleted node. You will not be given access to the first node of head.
 * All the values of the linked list are unique, and it is guaranteed that the given node node is not the last node in the linked list.
 *
 * Delete the given node. Note that by deleting the node, we do not mean removing it from memory.
 * We mean:
 *     The value of the given node should not exist in the linked list.
 *     The number of nodes in the linked list should decrease by one.
 *     All the values before node should be in the same order.
 *     All the values after node should be in the same order.
 *
 * Custom testing:
 *     For the input, you should provide the entire linked list head and the node to be given node. node should not be the last node of the list and should be an actual node in the list.
 *     We will build the linked list and pass the node to your function.
 *     The output will be the entire list after calling your function.
 *
 *
 *
 * Example 1:
 * Input: head = [4,5,1,9], node = 5
 * Output: [4,1,9]
 * Explanation: You are given the second node with value 5, the linked list
 * should become 4 -> 1 -> 9 after calling your function.
 *
 * Example 2:
 * Input: head = [4,5,1,9], node = 1
 * Output: [4,5,9]
 * Explanation: You are given the third node with value 1, the linked list should
 * become 4 -> 5 -> 9 after calling your function.
 *
 * Constraints:
 *
 *     The number of the nodes in the given list is in the range [2, 1000].
 *     -1000 <= Node.val <= 1000
 *     The value of each node in the list is unique.
 *     The node to be deleted is in the list and is not a tail node.
* */

public class _7_DeleteNodeWithoutHead extends SinglyLinkedList {

    /**
     * Time And Space Complexity : O(1)
    * */
    public void deleteNodeWithoutHead(Node node) {
        if(node==null){
            return;
        }

        if(node.next==null){ // This is the last node.
            node = null;
        }else { // This is not the last node.
            node.data = node.next.data;
            node.next = node.next.next;
        }
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
        _7_DeleteNodeWithoutHead object = new _7_DeleteNodeWithoutHead();

        traverseList(head);
        object.deleteNodeWithoutHead(one);
        traverseList(head);

    }

}
