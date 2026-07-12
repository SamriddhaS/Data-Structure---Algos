package binary_search_tree

import binary_trees._9_SameTree
import com.sun.org.apache.xpath.internal.operations.Bool
import kotlin.math.max
import kotlin.math.min

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


    /**
     *
     * Solution 2 : Optimal
     *
     * Time Complexity: O(N)
     *     Why: The algorithm visits every node in the binary tree exactly once using a post-order
     *     traversal (visiting the left child, then the right child, then processing the current node).
     *
     *     At each node, it performs a fixed number of basic comparisons
     *     and arithmetic additions, which take O(1) constant time.
     *
     *     N represents the total number of nodes in the binary tree.
     *
     * Space Complexity: O(H)
     *
     *     Why: The memory space used is entirely determined by the recursive call stack.
     *     The maximum number of frames on the stack at any given time is equal to the depth of the tree.
     *
     *     Worst Case: O(N) if the tree is highly skewed (essentially a straight line like a linked list).
     *
     *     Best/Average Case: O(log N) if the tree is balanced.
     *
     *     H represents the height of the tree.
     *
     *
     *
     *  INTUITION : The "Bottom-Up" Strategy
     *
     * Instead of checking validation rules from the top down (which forces a parent to judge children based on global limits),
     * we flip the script and ask the children to pass their own properties up to the parent.
     *
     * A parent node can only form a valid BST if three conditions are met simultaneously:
     *     - Left child is a valid BST.
     *     - Right child is a valid BST.
     *     - The boundaries fit: The parent's value must be strictly greater than the largest value in the left subtree,
     *     and strictly less than the smallest value in the right subtree.
     *
     * By using a custom return type (ReturnType), each subtree calculates its own validity,
     * min value, max value, and total sum in a single pass. If a node discovers it is a valid BST,
     * it updates the global maximum sum and hands its boundaries up to its own parent.
    * */
    fun maxSumBST2(root: BST.TreeNode?): Int {
        maxSum = 0
        validateBST(root)
        return maxSum.toInt()
    }

    class ReturnType(val isValidSubtree: Boolean,val minValue:Int,val maxValue:Int,val subtreeSum: Long)

    fun validateBST(
        node: BST.TreeNode?,
    ): ReturnType {
        if (node==null) return ReturnType(true, Int.MAX_VALUE, Int.MIN_VALUE,0)

        val leftSubtree = validateBST(node.left)
        val rightSubtree = validateBST(node.right)

        // BST = if (value is > max of left subtree) && (value if < min of rightSubtree)
        val isBST = (node.`val` > leftSubtree.maxValue && node.`val` < rightSubtree.minValue) && leftSubtree.isValidSubtree && rightSubtree.isValidSubtree
        var minValue = Math.min(leftSubtree.minValue,node.`val`)
        minValue = Math.min(minValue,rightSubtree.minValue)

        var maxValue = Math.max(rightSubtree.maxValue,node.`val`)
        maxValue = Math.max(maxValue,leftSubtree.maxValue)

        if (isBST){
            val sum = node.`val`+leftSubtree.subtreeSum+rightSubtree.subtreeSum
            maxSum = Math.max(maxSum,sum)
            return ReturnType(true,minValue,maxValue,sum)
        }

        return ReturnType(false,minValue,maxValue,0)
    }

}


fun main() {
    val obj = _14_MaximumSumBSTinBinaryTree()

    // Helper to create nodes quickly
    fun node(v: Int, left: BST.TreeNode? = null, right: BST.TreeNode? = null): BST.TreeNode {
        val n = BST.TreeNode(v)
        n.left = left
        n.right = right
        return n
    }

    // --- TEST CASE 1: The standard mix (LeetCode Example 1) ---
    // The right subtree (root 4) is a valid BST: 4 + 3 + 2 + 5 + 6 = 20
    val tree1 = node(1,
        left = node(4,
            left = node(2),
            right = node(4)
        ),
        right = node(3,
            left = node(2),
            right = node(5,
                left = node(4),
                right = node(6)
            )
        )
    )

    // --- TEST CASE 2: All Negative Values ---
    // An empty BST has a sum of 0, which beats any negative tree sum.
    val tree2 = node(-4,
        left = node(-2),
        right = node(-5)
    )

    // --- TEST CASE 3: Only One Right Child Boundary (Locally valid, globally broken) ---
    // The node 3 is a valid BST on its own (sum = 3).
    // But if your min/max logic gets confused by the null left child, it could incorrectly mark the root (2) as valid.
    val tree3 = node(2,
        left = null,
        right = node(3,
            left = null,
            right = node(1) // This 1 breaks the BST property for node 3!
        )
    )

    // --- TEST CASE 4: Complete Valid BST ---
    // The entire tree is a valid BST, so the answer is the sum of every node.
    val tree4 = node(10,
        left = node(5, node(3), node(7)),
        right = node(15, null, node(18))
    )

    // Run and Print results
    fun runTest(caseNum: Int, root: BST.TreeNode?, expected: Int) {
        val actual = obj.maxSumBST2(root)
        val status = if (actual == expected) "PASS" else "FAIL"
        println("Test $caseNum: $status | Expected: $expected | Actual: $actual")
    }

    println("--- Running Max Sum BST Tests ---")
    runTest(1, tree1, 20)
    runTest(2, tree2, 0)
    runTest(3, tree3, 1)
    runTest(4, tree4, 58) // 10 + 5 + 3 + 7 + 15 + 18 = 58
}