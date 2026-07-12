package binary_search_tree

import binary_trees._9_SameTree
import com.sun.org.apache.xpath.internal.operations.Bool
import kotlin.math.max

/**
 * Problem Link : https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/description/
 * Video Explanation :
 *
 * 1373. Maximum Sum BST in Binary Tree
 * Hard
 * Topics
 *
 * Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).
 * Assume a BST is defined as follows:
 *     The left subtree of a node contains only nodes with keys less than the node's key.
 *     The right subtree of a node contains only nodes with keys greater than the node's key.
 *     Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 * Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 * Output: 20
 * Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
 *
 * Example 2:
 * Input: root = [4,3,null,1,2]
 * Output: 2
 * Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
 *
 * Example 3:
 * Input: root = [-4,-2,-5]
 * Output: 0
 * Explanation: All values are negatives. Return an empty BST.
 *
 * Constraints:
 *
 *     The number of nodes in the tree is in the range [1, 4 * 104].
 *     -4 * 104 <= Node.val <= 4 * 104
 */
class _14_MaximumSumBSTinBinaryTree {

    var maxSum = 0L
    fun validateBST(
        node: BST.TreeNode?,
        leftLimit:Long,
        rightLimit:Long,
    ): Boolean{
        if (node==null) return true
        val currentValid = node.`val` > leftLimit && node.`val` < rightLimit
        val leftValidBST = validateBST(node.left,leftLimit,node.`val`.toLong())
        val rightValidBST = validateBST(node.right,node.`val`.toLong(),rightLimit)
        return currentValid && leftValidBST && rightValidBST
    }

    fun sumOfTree(node: BST.TreeNode?): Long {
        if (node == null) return 0L
        return node.`val` + sumOfTree(node.left) + sumOfTree(node.right)
    }

    fun solve(node: BST.TreeNode?){
        if (node==null) return
        if (validateBST(node, Long.MIN_VALUE, Long.MAX_VALUE)){
            maxSum = Math.max(maxSum,sumOfTree(node))
        }
        solve(node.left)
        solve(node.right)
    }

    /**
     * Solution 1 : Gives TLE
     *
     * Time complexity: O(n²) worst case
     * solve visits all n nodes.
     * At each node, you call validateBST(node,...) (walks the entire subtree under that node) and sumOfTree(node) (again walks the entire subtree under that node).
     * So the total work is the sum of subtree sizes over all nodes.
     *
     * Skewed tree (worst case): subtree sizes are n, n-1, n-2, ..., 1 → sum ≈ O(n²).
     *
     * Space complexity: O(h)
     * Recursion stack depth is bounded by the tree height h at any point in time
     * (the calls to validateBST/sumOfTree from within solve are sequential, not concurrent, so they don't stack on top of each other — each nested call still only goes as deep as h).
     * Worst case (skewed tree): O(n). Best case (balanced tree): O(log n).
    * */
    fun maxSumBST(root: BST.TreeNode?): Int {
        maxSum=0
        solve(root)
        return maxSum.toInt()
    }

}

fun main() {

    val obj = _14_MaximumSumBSTinBinaryTree()


}
