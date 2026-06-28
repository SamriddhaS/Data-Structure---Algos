package binary_search_tree
import java.util.*

/**
 * Problem Link : https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
 * Video Explanation :
 *
 * 116. Populating Next Right Pointers in Each Node
 * Medium
 * Topics
 *
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 *
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 *
 * Constraints:
 *
 *     The number of nodes in the tree is in the range [0, 212 - 1].
 *     -1000 <= Node.val <= 1000
 *
 *
 *
 * Follow-up:
 *
 *     You may only use constant extra space.
 *     The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 *
 *
 *
 */
class _13_PopulatingNextRightPointersInEachNodeBST {

    /**
     * Solution 1 : Using extra space
     * Time Complexity: O(N)
     * Each node is visited exactly once. The two nested while loops together
     * just partition the queue into levels — every node is polled once, and its children are offered once.
     *
     * Space Complexity: O(N)
     * The queue holds at most one full level at a time. The widest level
     * in a perfect binary tree is the last level, which has N/2 nodes → O(N).
    * */
    fun connect(root: Node?): Node? {
        if (root == null) return root

        val queue: Queue<Node?> = LinkedList()
        queue.offer(root)

        while (queue.isNotEmpty()) {
            var size = queue.size
            while (queue.isNotEmpty()&&size>0) {
                val current = queue.poll()
                if (current!=null){
                    current.next = if (size==1) {
                        null
                    } else {
                        queue.peek()
                    }
                    queue.offer(current.left)
                    queue.offer(current.right)
                }
                size--
            }
        }

        return root
    }

    /**
    * Solution 2:
     *
     * Time: O(N) — every node is visited exactly once in the inner while loop across all levels.
     * Space: O(1) — just two pointers (levelStart, cur). No queue, no recursion.
     *
     *
     * Intuition :
     * Each level as a linked list that we have already built.
     * Use that linked list to walk across and wire up the next level's linked list — then drop down and repeat.
     * Each level creates the next level's link.
     *
     *          [cur]  ----next---->  [cur.next]
     *         /    \                /        \
     *     left    right  -->  next.left    next.right
     *               |_____________↑
     *           cur.right.next = cur.next.left
     *
    * */
    fun connect1(root: Node?): Node? {
        if (root == null) return root

        var levelStart = root

        while (levelStart!=null){
            var cur = levelStart
            // explore all the nodes in same level.
            while (cur!=null){
                cur.left?.next = cur.right
                cur.right?.next = cur.next?.left
                cur = cur.next
            }
            levelStart = levelStart.left // next level --> left most node
        }

        return root
    }

}

class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null
}

fun main() {
    val convert = _13_PopulatingNextRightPointersInEachNodeBST()

    // Helper to print next pointers level by level
    fun printNextPointers(root: Node?) {
        var levelStart = root
        while (levelStart != null) {
            var curr = levelStart
            val sb = StringBuilder()
            while (curr != null) {
                sb.append(curr.`val`)
                if (curr.next != null) sb.append(" -> ") else sb.append(" -> #")
                curr = curr.next
            }
            println(sb)
            levelStart = levelStart.left
        }
    }

    // Test 1: Perfect binary tree
    // Expected:
    //   1 -> #
    //   2 -> 3 -> #
    //   4 -> 5 -> 6 -> 7 -> #
    println("=== Test 1: Perfect binary tree ===")
    val root1 = Node(1).apply {
        left = Node(2).apply { left = Node(4); right = Node(5) }
        right = Node(3).apply { left = Node(6); right = Node(7) }
    }
    convert.connect(root1)
    printNextPointers(root1)

    // Test 2: Single node
    // Expected:
    //   1 -> #
    println("\n=== Test 2: Single node ===")
    val root2 = Node(1)
    convert.connect(root2)
    printNextPointers(root2)

    // Test 3: Two levels, only left children
    // Expected:
    //   1 -> #
    //   2 -> #
    println("\n=== Test 3: Only left children ===")
    val root3 = Node(1).apply { left = Node(2) }
    convert.connect(root3)
    printNextPointers(root3)

    // Test 4: Two nodes, left and right
    // Expected:
    //   1 -> #
    //   2 -> 3 -> #
    println("\n=== Test 4: Root with two children ===")
    val root4 = Node(1).apply { left = Node(2); right = Node(3) }
    convert.connect(root4)
    printNextPointers(root4)

    // Test 5: Your original tree
    // Expected:
    //   1 -> #
    //   2 -> 3 -> #
    //   4 -> 5 -> #
    println("\n=== Test 5: Original tree (missing right subtree of node 3) ===")
    val root5 = Node(1).apply {
        left = Node(2).apply { left = Node(4); right = Node(5) }
        right = Node(3)
    }
    convert.connect(root5)
    printNextPointers(root5)
}
