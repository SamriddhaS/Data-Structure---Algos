package binary_trees
import binary_search_tree.BST.TreeNode
import binary_trees._22_BinaryTreeFromPreorderInorder.Companion.levelOrder
import java.util.Stack

/**
 * Problem Link : https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
 * Video Explanation :
 *
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Solved
 * Medium
 *
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder
 * is the postorder traversal of the same tree, construct and return the binary tree.
 *
 * Example 1:
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 *
 * Example 2:
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 *
 * Constraints:
 *     1 <= inorder.length <= 3000
 *     postorder.length == inorder.length
 *     -3000 <= inorder[i], postorder[i] <= 3000
 *     inorder and postorder consist of unique values.
 *     Each value of postorder also appears in inorder.
 *     inorder is guaranteed to be the inorder traversal of the tree.
 *     postorder is guaranteed to be the postorder traversal of the tree.
 *
 */
class _23_BinaryTreeFromPostorderInorder {

    fun getIndexFromInorder(inorder: IntArray,element:Int):Int{
        inorder.forEachIndexed { index,num ->
            if(num==element) return index
        }
        return -1
    }

    fun solve(inorder: IntArray, postorder: IntArray):TreeNode?{
        if(postorder.size==0) return null
        val node = TreeNode(postorder[postorder.size-1])
        val index = getIndexFromInorder(inorder,postorder[postorder.size-1])
        val leftInorder = inorder.copyOfRange(0,index)
        val rightInorder = inorder.copyOfRange(index+1,inorder.size)
        val rightPostorder = postorder.copyOfRange(postorder.size-1-rightInorder.size,postorder.size-1)
        val leftPostorder = postorder.copyOfRange(0,postorder.size-1-rightInorder.size)
        node.left = solve(leftInorder,leftPostorder)
        node.right = solve(rightInorder,rightPostorder)
        return node
    }


    /**
    *
     * Time Complexity: O(n²)
     * The algorithm makes O(n) recursive calls (one per node)
     * Each call performs:
     * Linear search in getIndexFromInorder: O(n)
     * Array copying operations: O(n)
     *
     *
     * Total: O(n) × O(n) = O(n²)
     * Space Complexity: O(n²)
     * Recursion stack: O(n) depth in worst case (skewed tree)
     * Array copies: At each level, we create new subarrays. Across all recursive calls,
     * this creates O(n²) total space due to overlapping copies
     * Total: O(n²)
     *
    * */
    fun buildTree(inorder: IntArray, postorder: IntArray): TreeNode? {
        return solve(inorder,postorder)
    }

    fun solve1(
        inorder: IntArray,
        postorder: IntArray,
        inorderMap:Map<Int,Int>,
        inorderStartIndex:Int,
        inorderEndIndex:Int,
        postStartIndex:Int,
        postEndIndex:Int
    ): TreeNode?{

        if (postStartIndex>postEndIndex) return null

        val node = TreeNode(postorder[postEndIndex])

        val index = inorderMap[postorder[postEndIndex]]!!

        val noOfElementsRight = inorderEndIndex - index //correct
        val noOfElementsLeft = index - inorderStartIndex // correct

        val postEndIndexRight = postEndIndex-1 // correct
        val postStartIndexRight = postEndIndex-noOfElementsRight // correct

        val postEndIndexLeft = postStartIndexRight-1
        val postStartIndexLeft = postStartIndexRight - noOfElementsLeft

        node.right = solve1(
            inorder,
            postorder,
            inorderMap,
            inorderStartIndex = index+1,
            inorderEndIndex = index+noOfElementsRight,
            postStartIndex = postStartIndexRight,
            postEndIndex = postEndIndexRight
        )

        node.left = solve1(
            inorder,
            postorder,
            inorderMap,
            inorderStartIndex = index - noOfElementsLeft,
            inorderEndIndex = index-1,
            postStartIndex = postStartIndexLeft,
            postEndIndex = postEndIndexLeft
        )
        return node
    }

    /**
    * Solution 2 : Using index to build the subtree instead of creating sub arrays.
     *
     * Time Complexity: O(n)
     *
     * Makes exactly n recursive calls (one per node)
     * Each call does O(1) work: HashMap lookup, arithmetic operations, and creating a TreeNode
     * Total: O(n)
     *
     * Space Complexity: O(n)
     *
     * HashMap storage: O(n) for storing all node indices
     * Recursion stack: O(h) where h is tree height
     *
     * Best case (balanced): O(log n)
     * Worst case (skewed): O(n)
     *
     *
     * Total: O(n)
    * */
    fun buildTree1(inorder: IntArray, postorder: IntArray): TreeNode? {
        val inorderMap = HashMap<Int,Int>()
        inorder.forEachIndexed { index, i ->
            inorderMap[i] = index
        }
        return solve1(inorder,postorder,inorderMap,0,inorder.size-1,0,postorder.size-1)
    }

}

fun main() {
    val bt = _23_BinaryTreeFromPostorderInorder()

    println("=== Test Case 1 ===")
    val inorder1 = intArrayOf(9, 3, 15, 20, 7)
    val postorder1 = intArrayOf(9, 15, 7, 20, 3)
    println("Inorder:   ${inorder1.contentToString()}")
    println("Postorder: ${postorder1.contentToString()}")
    println("Expected: [3, 9, 20, null, null, 15, 7] (level-order)")
    println("buildTree:  ${levelOrder(bt.buildTree(inorder1, postorder1))}")
    println("buildTree1: ${levelOrder(bt.buildTree1(inorder1, postorder1))}")

    println("\n=== Test Case 2 ===")
    val inorder2 = intArrayOf(2, 1)
    val postorder2 = intArrayOf(2, 1)
    println("Inorder:   ${inorder2.contentToString()}")
    println("Postorder: ${postorder2.contentToString()}")
    println("Expected: [1, 2] (level-order)")
    println("buildTree:  ${levelOrder(bt.buildTree(inorder2, postorder2))}")
    println("buildTree1: ${levelOrder(bt.buildTree1(inorder2, postorder2))}")

    println("\n=== Test Case 3 ===")
    val inorder3 = intArrayOf(3, 2, 1)
    val postorder3 = intArrayOf(3, 2, 1)
    println("Inorder:   ${inorder3.contentToString()}")
    println("Postorder: ${postorder3.contentToString()}")
    println("Expected: [1, 2, null, 3] (level-order, left-skewed tree)")
    println("buildTree:  ${levelOrder(bt.buildTree(inorder3, postorder3))}")
    println("buildTree1: ${levelOrder(bt.buildTree1(inorder3, postorder3))}")

    println("\n=== Test Case 4 ===")
    val inorder4 = intArrayOf(1, 2, 3)
    val postorder4 = intArrayOf(1, 2, 3)
    println("Inorder:   ${inorder4.contentToString()}")
    println("Postorder: ${postorder4.contentToString()}")
    println("Expected: [3, 2, null, 1] (level-order, right-skewed tree)")
    println("buildTree:  ${levelOrder(bt.buildTree(inorder4, postorder4))}")
    println("buildTree1: ${levelOrder(bt.buildTree1(inorder4, postorder4))}")

    println("\n=== Test Case 5 ===")
    val inorder5 = intArrayOf(1)
    val postorder5 = intArrayOf(1)
    println("Inorder:   ${inorder5.contentToString()}")
    println("Postorder: ${postorder5.contentToString()}")
    println("Expected: [1] (single node)")
    println("buildTree:  ${levelOrder(bt.buildTree(inorder5, postorder5))}")
    println("buildTree1: ${levelOrder(bt.buildTree1(inorder5, postorder5))}")

    println("\n=== Test Case 6 ===")
    val inorder6 = intArrayOf(4, 2, 5, 1, 6, 3, 7)
    val postorder6 = intArrayOf(4, 5, 2, 6, 7, 3, 1)
    println("Inorder:   ${inorder6.contentToString()}")
    println("Postorder: ${postorder6.contentToString()}")
    println("Expected: [1, 2, 3, 4, 5, 6, 7] (perfect binary tree)")
    println("buildTree:  ${levelOrder(bt.buildTree(inorder6, postorder6))}")
    println("buildTree1: ${levelOrder(bt.buildTree1(inorder6, postorder6))}")
}
