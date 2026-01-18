package linked_list
import binary_trees._9_SameTree
import linked_list.SinglyLinkedList.ListNode
import linked_list.SinglyLinkedList.traverseList
import linked_list._1_ReverseLinkedList_I.Companion.createLinkedList

/**
 * Problem Link : https://leetcode.com/problems/reverse-linked-list-ii/description/
 * Intuition : https://www.youtube.com/watch?v=wk8-_M-2fzI
 *
 * 92. Reverse Linked List II
 * Solved
 * Medium
 * Topics
 * premium lock iconCompanies
 *
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 *
 * Example 2:
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 * Constraints:
 *
 *     The number of nodes in the list is n.
 *     1 <= n <= 500
 *     -500 <= Node.val <= 500
 *     1 <= left <= right <= n
 *
 *
 * Follow up: Could you do it in one pass?
 *
 * ======================== Solution One ====================================================================
 * Time Complexity: O(n)
 * - The first while loop runs left-1 times to find the position before the reversal starts
 * - The for loop runs right-left times to perform the actual reversal
 * - In the worst case, if left=1 and right=n (reversing the entire list), the total operations would be 0 + (n-1) = n-1
 * Therefore, the time complexity is O(n) where n is the length of the linked list
 *
 * Space Complexity: O(1)
 *
 * public Node reverseBetween(Node head, int left, int right) {
 *
 *         if(head==null||head.next==null||left==right) return head;
 *
 *         Node dummy = new Node(Integer.MIN_VALUE);
 *         dummy.next = head;
 *
 *         Node PRE=dummy,CURRENT=null,NEXT=null;
 *         int i=0;
 *         while(i<left-1){
 *             PRE = PRE.next;
 *             i++;
 *         }
 *
 *         CURRENT = PRE.next;
 *         for (int j=left;j<right;j++){
 *             NEXT = CURRENT.next;
 *             CURRENT.next = NEXT.next;
 *             NEXT.next = PRE.next;
 *             PRE.next = NEXT;
 *         }
 *
 *         return dummy.next;
 *     }
 *
 * ======================== Same As Solution One But With Single loop ====================================================================
 *
 *
 * public Node reverseBetweenSingleLoop(Node head, int left, int right) {
 *
 *         if(head==null||head.next==null||left==right) return head;
 *
 *         Node dummy = new Node(Integer.MIN_VALUE);
 *         dummy.next = head;
 *
 *         Node PRE=dummy,CURRENT=null,NEXT=null;
 *         for (int i=0;i<right;i++){
 *             if(i<left-1){
 *                 PRE = PRE.next;
 *             }else if (i == left - 1) {
 *                 CURRENT = PRE.next;
 *             }else {
 *                 NEXT = CURRENT.next;
 *                 CURRENT.next = NEXT.next;
 *                 NEXT.next = PRE.next;
 *                 PRE.next = NEXT;
 *             }
 *         }
 *
 *         return dummy.next;
 *     }
 *
 * */
class _2_ReverseLinkedList_II {

    /**
    * Solution 1 :
     *
    * */
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        if (head==null) return head

        val dummy = ListNode(Int.MAX_VALUE)
        dummy.next = head

        var preNode: ListNode? = null
        var nextNode: ListNode? = null
        var temp = head
        var pos=1

        // Find the previous/next node before the starting node.
        while (temp!=null){
            if (pos<left){
                preNode = temp
            }
            if (pos<=right){
                nextNode = temp.next
            }
            temp = temp.next
            pos++
        }
//
//        CURRENT = PRE.next;
//        *         for (int j=left;j<right;j++){
//            *             NEXT = CURRENT.next;
//            *             CURRENT.next = NEXT.next;
//            *             NEXT.next = PRE.next;
//            *             PRE.next = NEXT;
//            *         }
        val current = preNode?.next // 2
        pos = left
        while (pos<right){
            val next = current?.next // 3
            current?.next = next?.next // 2->4
            next?.next = preNode?.next // 3->2
            preNode?.next = next // 1->3
            pos++
            //current = current?.next
        }

        return dummy.next
    }

    companion object{
        fun createLinkedList(values: IntArray): ListNode? {
            if (values.isEmpty()) return null

            val head = ListNode(values[0])
            var current = head

            for (i in 1 until values.size) {
                current.next = ListNode(values[i])
                current = current.next!!
            }

            return head
        }
    }

}

fun main() {
    val solution = _2_ReverseLinkedList_II()

    println("=== Reverse Linked List Test Cases ===\n")

    // Test Case 1: [1,2,3,4,5]
    println("Test Case 1:")
    val head1 = createLinkedList(intArrayOf(1, 2, 3, 4, 5))
    val reversed1 = solution.reverseBetween(head1,2,4)
    println("Input: [1, 2, 3, 4, 5]")
    traverseList(reversed1)
    println()
    println("Expected: [5, 4, 3, 2, 1]")
    println()

    // Test Case 2: [1,2]
//    println("Test Case 2:")
//    val head2 = createLinkedList(intArrayOf(1, 2))
//    val reversed2 = solution.reverseList(head2)
//    println("Input: [1, 2]")
//    traverseList(reversed2)
//    println()
//    println("Expected: [2, 1]")
//    println()
//
//    // Test Case 4: Single node [1]
//    println("Test Case 4:")
//    val head4 = createLinkedList(intArrayOf(1))
//    val reversed4 = solution.reverseList(head4)
//    println("Input: [1]")
//    traverseList(reversed4)
//    println()
//    println("Expected: [1]")
//    println()
//
//    // Test Case 5: [1,2,3]
//    println("Test Case 5:")
//    val head5 = createLinkedList(intArrayOf(1, 2, 3))
//    val reversed5 = solution.reverseList(head5)
//    println("Input: [1, 2, 3]")
//    traverseList(reversed5)
//    println()
//    println("Expected: [3, 2, 1]")
//    println()
}