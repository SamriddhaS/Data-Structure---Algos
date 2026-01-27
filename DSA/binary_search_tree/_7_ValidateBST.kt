package binary_search_tree
import binary_search_tree.BST.TreeNode
import binary_search_tree.BST.inorderTraversal
import binary_search_tree.BST.insertRecursively

/**
 * Problem Link : https://leetcode.com/problems/validate-binary-search-tree/
 * Video Explanation : https://www.youtube.com/watch?v=s6ATEkipzow
 *
 * 98. Validate Binary Search Tree
 * Solved
 * Medium
 *
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *     The left
 *     of a node contains only nodes with keys strictly less than the node's key.
 *     The right subtree of a node contains only nodes with keys strictly greater than the node's key.
 *     Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 * Input: root = [2,1,3]
 * Output: true
 *
 * Example 2:
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [1, 104].
 *     -231 <= Node.val <= 231 - 1
 *
 */
class _7_ValidateBST {

    fun travarse(node:TreeNode?,leftLimit:Long,rightLimit:Long):Boolean{
        if(node==null) return true

        val currentValid = if(node.left==null&&node.right==null){
            true
        }else if(node.left==null){
            node.right?.`val`!! >node.`val`
        } else if(node.right==null){
            node.left!!.`val`<node.`val`
        } else {
            node.right!!.`val`>node.`val` && node.left!!.`val`<node.`val`
        }

        val leftValid = travarse(node.left,leftLimit,node.`val`.toLong())
        val rightValid = travarse(node.right,node.`val`.toLong(),rightLimit)

        return currentValid && leftValid && rightValid && (leftLimit<node.`val`.toLong()) && (node.`val`.toLong()<rightLimit)

    }

    /**
     *
     * Time Complexity: O(n)
     *
     * The function visits each node exactly once in the recursive traversal
     * At each node, it performs constant-time operations (comparisons)
     * Where n is the number of nodes in the tree
     *
     * Space Complexity: O(h)
     *
     * The recursion stack depth is proportional to the height of the tree
     * Best case (balanced tree): O(log n)
     * Worst case (skewed tree): O(n)
     * Where h is the height of the tree
    * */
    fun isValidBST(root: TreeNode?): Boolean {
        return travarse(root,Long.MIN_VALUE,Long.MAX_VALUE)
    }

}

fun main() {
    val bst = _7_ValidateBST()

    /* ---------- Test Case 1: Valid BST ---------- */
    println("Test Case 1: Valid BST")
    var root: TreeNode? = TreeNode(2)
    root?.left = TreeNode(1)
    root?.right = TreeNode(3)

    val result1 = bst.isValidBST(root)
    println("isValidBST(root) = $result1")
    println("Expected: true")
    println()

    /* ---------- Test Case 2: Invalid BST (right child less than root) ---------- */
    println("Test Case 2: Invalid BST (right child less than root)")
    root = TreeNode(5)
    root.left = TreeNode(1)
    root.right = TreeNode(4)
    root.right?.left = TreeNode(3)
    root.right?.right = TreeNode(6)

    val result2 = bst.isValidBST(root)
    println("isValidBST(root) = $result2")
    println("Expected: false")
    println()

    /* ---------- Test Case 3: Valid larger BST ---------- */
    println("Test Case 3: Valid larger BST")
    root = TreeNode(50)
    root.left = TreeNode(30)
    root.right = TreeNode(70)
    root.left?.left = TreeNode(20)
    root.left?.right = TreeNode(40)
    root.right?.left = TreeNode(60)
    root.right?.right = TreeNode(80)

    val result3 = bst.isValidBST(root)
    println("isValidBST(root) = $result3")
    println("Expected: true")
    println()

    /* ---------- Test Case 4: Invalid BST (violates ancestor constraint) ---------- */
    println("Test Case 4: Invalid BST (violates ancestor constraint)")
    root = TreeNode(10)
    root.left = TreeNode(5)
    root.right = TreeNode(15)
    root.right?.left = TreeNode(6)
    root.right?.right = TreeNode(20)

    val result4 = bst.isValidBST(root)
    println("isValidBST(root) = $result4")
    println("Expected: false (6 < 10, violates BST property)")
    println()

    /* ---------- Test Case 5: Single node tree ---------- */
    println("Test Case 5: Single node tree")
    root = TreeNode(1)

    val result5 = bst.isValidBST(root)
    println("isValidBST(root) = $result5")
    println("Expected: true")
    println()

    /* ---------- Test Case 6: Two nodes - valid ---------- */
    println("Test Case 6: Two nodes - valid")
    root = TreeNode(2)
    root.left = TreeNode(1)

    val result6 = bst.isValidBST(root)
    println("isValidBST(root) = $result6")
    println("Expected: true")
    println()

    /* ---------- Test Case 7: Two nodes - invalid ---------- */
    println("Test Case 7: Two nodes - invalid")
    root = TreeNode(1)
    root.left = TreeNode(2)

    val result7 = bst.isValidBST(root)
    println("isValidBST(root) = $result7")
    println("Expected: false")
    println()
}
