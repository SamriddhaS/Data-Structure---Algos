package linked_list
import linked_list.SinglyLinkedList.ListNode
import linked_list.SinglyLinkedList.traverseList
import linked_list._1_ReverseLinkedList_I.Companion.createLinkedList

/**
 * Problem Link : https://leetcode.com/problems/reverse-linked-list/description/
 * Explanation :
 *
 * 206. Reverse Linked List
 * Solved
 * Easy
 *
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 *
 * Example 3:
 * Input: head = []
 * Output: []
 *
 * Constraints:
 *     The number of nodes in the list is the range [0, 5000].
 *     -5000 <= Node.val <= 5000
 *
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 *
 * */
class _1_ReverseLinkedList_I {

    /**
    * Solution 1 :
     *
     * Time Complexity: O(n)
     *
     * We traverse the linked list exactly once, visiting each of the n nodes
     * At each node, we perform constant time operations (pointer assignments)
     *
     * Space Complexity: O(1)
     *
     * We only use a fixed number of pointers (current, previous, next)
     * No additional data structures are created
     * The space used doesn't grow with input size
    * */
    fun reverseList(head: ListNode?): ListNode? {
        if (head==null) return head
        var current = head

        var previous: ListNode? = null
        while (current!=null){
            val next =current.next
            current.next = previous
            previous=current
            current = next
        }
        return previous
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
    val solution = _1_ReverseLinkedList_I()

    println("=== Reverse Linked List Test Cases ===\n")

    // Test Case 1: [1,2,3,4,5]
    println("Test Case 1:")
    val head1 = createLinkedList(intArrayOf(1, 2, 3, 4, 5))
    val reversed1 = solution.reverseList(head1)
    println("Input: [1, 2, 3, 4, 5]")
    traverseList(reversed1)
    println()
    println("Expected: [5, 4, 3, 2, 1]")
    println()

    // Test Case 2: [1,2]
    println("Test Case 2:")
    val head2 = createLinkedList(intArrayOf(1, 2))
    val reversed2 = solution.reverseList(head2)
    println("Input: [1, 2]")
    traverseList(reversed2)
    println()
    println("Expected: [2, 1]")
    println()

    // Test Case 3: []
    println("Test Case 3:")
    val head3 = createLinkedList(intArrayOf())
    val reversed3 = solution.reverseList(head3)
    println("Input: []")
    traverseList(reversed3)
    println()
    println("Expected: []")
    println()

    // Test Case 4: Single node [1]
    println("Test Case 4:")
    val head4 = createLinkedList(intArrayOf(1))
    val reversed4 = solution.reverseList(head4)
    println("Input: [1]")
    traverseList(reversed4)
    println()
    println("Expected: [1]")
    println()

    // Test Case 5: [1,2,3]
    println("Test Case 5:")
    val head5 = createLinkedList(intArrayOf(1, 2, 3))
    val reversed5 = solution.reverseList(head5)
    println("Input: [1, 2, 3]")
    traverseList(reversed5)
    println()
    println("Expected: [3, 2, 1]")
    println()

    // Test Case 6: Negative numbers [-5, -3, -1, 0, 2]
    println("Test Case 6:")
    val head6 = createLinkedList(intArrayOf(-5, -3, -1, 0, 2))
    val reversed6 = solution.reverseList(head6)
    println("Input: [-5, -3, -1, 0, 2]")
    traverseList(reversed6)
    println()
    println("Expected: [2, 0, -1, -3, -5]")
}