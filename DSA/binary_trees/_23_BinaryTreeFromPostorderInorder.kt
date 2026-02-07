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
        val noOfElementsLeft = index - inorderStartIndex
        val noOfElementsRight = inorderEndIndex - index

        val postStartIndexRight = postEndIndex-noOfElementsRight
        val postEndIndexRight = postEndIndex-1
        val postStartIndexLeft = postEndIndex - noOfElementsRight - 1 - noOfElementsLeft
        val postEndIndexLeft = postEndIndex-noOfElementsRight-1


        node.right = solve1(
            inorder,
            postorder,
            inorderMap,
            index+1,
            inorderEndIndex = index+1+noOfElementsRight,
            postStartIndexRight,
            postEndIndexRight
        )
        node.left = solve1(
            inorder,
            postorder,
            inorderMap,
            inorderStartIndex = index-noOfElementsLeft,
            inorderEndIndex = index-1,
            postStartIndexLeft,
            postEndIndexLeft
        )
        return node
    }

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
    val preorder1 = intArrayOf(3, 9, 20, 15, 7)
    val inorder1 = intArrayOf(9, 3, 15, 20, 7)
    println("Preorder: ${preorder1.contentToString()}")
    println("Inorder:  ${inorder1.contentToString()}")
    println("Expected: [3, 9, 20, null, null, 15, 7] (level-order)")
    println("buildTree:  ${levelOrder(bt.buildTree(preorder1, inorder1))}")
    println("buildTree1: ${levelOrder(bt.buildTree1(preorder1, inorder1))}")

    println("\n=== Test Case 2 ===")
    val preorder2 = intArrayOf(1, 2)
    val inorder2 = intArrayOf(2, 1)
    println("Preorder: ${preorder2.contentToString()}")
    println("Inorder:  ${inorder2.contentToString()}")
    println("Expected: [1, 2] (level-order)")
    println("buildTree:  ${levelOrder(bt.buildTree(preorder2, inorder2))}")
    println("buildTree1: ${levelOrder(bt.buildTree1(preorder2, inorder2))}")

    println("\n=== Test Case 3 ===")
    val preorder3 = intArrayOf(1, 2, 3)
    val inorder3 = intArrayOf(3, 2, 1)
    println("Preorder: ${preorder3.contentToString()}")
    println("Inorder:  ${inorder3.contentToString()}")
    println("Expected: [1, 2, null, 3] (level-order, left-skewed tree)")
    println("buildTree:  ${levelOrder(bt.buildTree(preorder3, inorder3))}")
    println("buildTree1: ${levelOrder(bt.buildTree1(preorder3, inorder3))}")

    println("\n=== Test Case 4 ===")
    val preorder4 = intArrayOf(1, 2, 3)
    val inorder4 = intArrayOf(1, 2, 3)
    println("Preorder: ${preorder4.contentToString()}")
    println("Inorder:  ${inorder4.contentToString()}")
    println("Expected: [1, null, 2, null, 3] (level-order, right-skewed tree)")
    println("buildTree:  ${levelOrder(bt.buildTree(preorder4, inorder4))}")
    println("buildTree1: ${levelOrder(bt.buildTree1(preorder4, inorder4))}")

    println("\n=== Test Case 5 ===")
    val preorder5 = intArrayOf(1)
    val inorder5 = intArrayOf(1)
    println("Preorder: ${preorder5.contentToString()}")
    println("Inorder:  ${inorder5.contentToString()}")
    println("Expected: [1] (single node)")
    println("buildTree:  ${levelOrder(bt.buildTree(preorder5, inorder5))}")
    println("buildTree1: ${levelOrder(bt.buildTree1(preorder5, inorder5))}")

    println("\n=== Test Case 6 ===")
    val preorder6 = intArrayOf(1, 2, 4, 5, 3, 6, 7)
    val inorder6 = intArrayOf(4, 2, 5, 1, 6, 3, 7)
    println("Preorder: ${preorder6.contentToString()}")
    println("Inorder:  ${inorder6.contentToString()}")
    println("Expected: [1, 2, 3, 4, 5, 6, 7] (perfect binary tree)")
    println("buildTree:  ${levelOrder(bt.buildTree(preorder6, inorder6))}")
    println("buildTree1: ${levelOrder(bt.buildTree1(preorder6, inorder6))}")
}
