package binary_search_tree
import binary_search_tree.BST.TreeNode
import binary_trees._2_BinaryTreeTraversalInorder
import java.util.*

/**
 * Problem Link : https://leetcode.com/problems/binary-search-tree-iterator/description/
 * Video Explanation : https://www.youtube.com/watch?v=D2jMcmxU4bs
 *
 * 173. Binary Search Tree Iterator
 * Medium
 *
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 *     BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor.
 *     The pointer should be initialized to a non-existent number smaller than any element in the BST.
 *     boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 *     int next() Moves the pointer to the right, then returns the number at the pointer.
 *
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.
 *
 * Example 1:
 *
 * Input
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * Output
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 *
 * Explanation :
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // return 3
 * bSTIterator.next();    // return 7
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 9
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 15
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 20
 * bSTIterator.hasNext(); // return False
 *
 *
 * Constraints:
 *
 *     The number of nodes in the tree is in the range [1, 105].
 *     0 <= Node.val <= 106
 *     At most 105 calls will be made to hasNext, and next.
 *
 * Follow up:
 *     Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?
 *
 *
 */
class _11_BSTIterator(root: TreeNode?)  {

    val stack: Stack<TreeNode?> = Stack<TreeNode?>()
    init {
        var currentNode = root
        stack.push(currentNode)
        while (currentNode?.left!=null){
            stack.push(currentNode.left)
            currentNode = currentNode.left
        }
    }

    /**
     * Complexity Analysis :
     *
     * Time Complexity
     *
     * next(): Amortised O(1)
     * Occasionally does extra work pushing left children, but each node is pushed and
     * popped exactly once across all next() calls. So total work over n calls = O(n),
     * meaning O(1) per call on average.
     *
     * Space Complexity
     * O(h) where h is the height of the tree.
     *
     * The stack holds at most one root-to-leaf path at any time — which is the left spine being tracked.
     * Best case (balanced tree): O(log n)
     * Worst case (skewed tree): O(n)
     * */
    fun next(): Int {
        if (stack.isEmpty()) return 0
        var currentNode = stack.pop()
        var nextValue = currentNode?.`val` ?: 0
        if (currentNode?.right!=null) {
            currentNode = currentNode.right
            stack.push(currentNode)
            while (currentNode?.left!=null){
                currentNode = currentNode.left
                stack.push(currentNode)
            }
        }
        return nextValue
    }

    /**
     * Complexity Analysis :
     *
     * Time Complexity
     * hasNext(): O(1) — just a stack size check.
     *
     * */
    fun hasNext(): Boolean {
        return stack.isNotEmpty()
    }

}

fun main() {
    // Test 1: Example from problem
    //       7
    //      / \
    //     3   15
    //        /  \
    //       9   20
    val root1 = TreeNode(7).apply {
        left = TreeNode(3)
        right = TreeNode(15).apply {
            left = TreeNode(9)
            right = TreeNode(20)
        }
    }
    val iter1 = _11_BSTIterator(root1)
    println("=== Test 1: [7, 3, 15, null, null, 9, 20] ===")
    println("next()    = ${iter1.next()}");    println("expected  = 3")
    println("next()    = ${iter1.next()}");    println("expected  = 7")
    println("hasNext() = ${iter1.hasNext()}"); println("expected  = true")
    println("next()    = ${iter1.next()}");    println("expected  = 9")
    println("hasNext() = ${iter1.hasNext()}"); println("expected  = true")
    println("next()    = ${iter1.next()}");    println("expected  = 15")
    println("hasNext() = ${iter1.hasNext()}"); println("expected  = true")
    println("next()    = ${iter1.next()}");    println("expected  = 20")
    println("hasNext() = ${iter1.hasNext()}"); println("expected  = false")

    // Test 2: Single node
    //    42
    val root2 = TreeNode(42)
    val iter2 = _11_BSTIterator(root2)
    println("\n=== Test 2: Single node [42] ===")
    println("hasNext() = ${iter2.hasNext()}"); println("expected  = true")
    println("next()    = ${iter2.next()}");    println("expected  = 42")
    println("hasNext() = ${iter2.hasNext()}"); println("expected  = false")

    // Test 3: Left-skewed tree (worst case for space)
    //       5
    //      /
    //     3
    //    /
    //   1
    val root3 = TreeNode(5).apply {
        left = TreeNode(3).apply {
            left = TreeNode(1)
        }
    }
    val iter3 = _11_BSTIterator(root3)
    println("\n=== Test 3: Left-skewed [5, 3, 1] ===")
    println("next()    = ${iter3.next()}"); println("expected  = 1")
    println("next()    = ${iter3.next()}"); println("expected  = 3")
    println("next()    = ${iter3.next()}"); println("expected  = 5")
    println("hasNext() = ${iter3.hasNext()}"); println("expected  = false")

    // Test 4: Right-skewed tree
    //   1
    //    \
    //     3
    //      \
    //       5
    val root4 = TreeNode(1).apply {
        right = TreeNode(3).apply {
            right = TreeNode(5)
        }
    }
    val iter4 = _11_BSTIterator(root4)
    println("\n=== Test 4: Right-skewed [1, 3, 5] ===")
    println("next()    = ${iter4.next()}"); println("expected  = 1")
    println("next()    = ${iter4.next()}"); println("expected  = 3")
    println("next()    = ${iter4.next()}"); println("expected  = 5")
    println("hasNext() = ${iter4.hasNext()}"); println("expected  = false")
}