package binary_trees
import binary_search_tree.BST.TreeNode
import binary_trees._22_BinaryTreeFromPreorderInorder.Companion.levelOrder
import java.util.Stack

/**
 * Problem Link : https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * Video Explanation :
 *
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Medium
 *
 * Given two integer arrays preorder and inorder where preorder is the preorder
 * traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 *
 *
 *
 * Example 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * Example 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 * Constraints:
 *
 *     1 <= preorder.length <= 3000
 *     inorder.length == preorder.length
 *     -3000 <= preorder[i], inorder[i] <= 3000
 *     preorder and inorder consist of unique values.
 *     Each value of inorder also appears in preorder.
 *     preorder is guaranteed to be the preorder traversal of the tree.
 *     inorder is guaranteed to be the inorder traversal of the tree.
 */
class _22_BinaryTreeFromPreorderInorder {

    fun findInorderIndex(inorder: IntArray,element:Int):Int{
        inorder.forEachIndexed{ index,ele ->
            if(ele==element) return index
        }
        return -1
    }

    fun solve(preorder: IntArray, inorder: IntArray):TreeNode?{

        // Base case : no elements left to process so return null.
        if (preorder.size==0){
            return null
        }

        // we always pick the 0th node from our preorder slice and create new node considering it as root
        val newNode = TreeNode(preorder[0])

        // find the index if the current node inside inorder array,
        // so we can check how many nodes are there to the left and right subtree of this node.
        val elementIndex = findInorderIndex(inorder,preorder[0])

        // these are the numbers of nodes present to the left subtree of our current node
        val inorderSubArrayLeft = inorder.copyOfRange(0, elementIndex)
        // these are the numbers nodes present to the right subtree of our current node
        val inorderSubArrayRight = inorder.copyOfRange(elementIndex+1, inorder.size)

        // these are the elements we need to process for the left subtree of the current node.
        val preorderSubArrayLeft = preorder.copyOfRange(1,inorderSubArrayLeft.size+1) // from == to → empty array so no exception will occur
        // these are the elements we need to process for the right subtree of the current node.
        val preorderSubArrayRight = preorder.copyOfRange(inorderSubArrayLeft.size+1, preorder.size) // from == to → empty array so no exception will occur

        // recursively create the left subtree
        newNode.left = solve(preorder = preorderSubArrayLeft, inorder = inorderSubArrayLeft)
        // recursively create the right subtree
        newNode.right = solve(preorder = preorderSubArrayRight, inorder = inorderSubArrayRight)

        return newNode
    }

    /**
     * Solution 1 : Brute Force
     * Time Complexity: O(n²)
     * Each node is processed once: O(n)
     * For each node, findInorderIndex scans the inorder array: O(n)
     * Total: O(n) × O(n) = O(n²)
     *
     * Space Complexity: O(n²)
     * Recursion stack: O(n) depth in worst case (skewed tree)
     * Array copies: At each level, copyOfRange creates new arrays. Across all recursive calls, this creates O(n²) total space due to overlapping subarrays
     * Total: O(n²) auxiliary space
     *
    * */
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        return solve(preorder,inorder)
    }



    fun solve1(
        preorder: IntArray,
        inorder: IntArray,
        inorderMap: HashMap<Int,Int>,
        preorderStartIndex:Int,
        preorderEndIndex:Int,
        inStart:Int
    ):TreeNode?{
        if (preorderStartIndex>preorderEndIndex) return null

        val newNode = TreeNode(preorder[preorderStartIndex])

        val indexInInorder = inorderMap[preorder[preorderStartIndex]] ?: 0
        val noOfNodesInLeftSubtree = indexInInorder - inStart

        newNode.left = solve1(
            preorder,
            inorder,
            inorderMap,
            preorderStartIndex+1,
            preorderStartIndex+noOfNodesInLeftSubtree,
            inStart
        )
        newNode.right =  solve1(
            preorder,
            inorder,
            inorderMap,
            preorderStartIndex+noOfNodesInLeftSubtree+1,
            preorderEndIndex,
            indexInInorder+1
        )
        return newNode
    }

    /**
     * Solution 2 : We don't create sub arrays instead we pass index's and pick values from them.
     *
     * Time Complexity: O(n)
     * HashMap creation: O(n)
     * Each node visited once: O(n)
     * HashMap lookup per node: O(1)
     * No array copying
     * Total: O(n)
     *
     * Space Complexity: O(n)
     * HashMap: O(n)
     * Recursion stack: O(h) where h = height, worst case O(n) for skewed tree
     * No extra array copies
     * Total: O(n)
     *
    * */
    fun buildTree1(preorder: IntArray, inorder: IntArray): TreeNode? {
        val inorderMap = HashMap<Int,Int>()
        inorder.forEachIndexed { index, i ->
            inorderMap[i] = index
        }
        return solve1(preorder,inorder,inorderMap,0,preorder.size-1,0)
    }

    companion object{
        // Helper function to print tree in level-order (for easy comparison)
        fun levelOrder(root: TreeNode?): String {
            if (root == null) return "[]"

            val result = mutableListOf<String>()
            val queue = ArrayDeque<TreeNode?>()
            queue.add(root)

            while (queue.isNotEmpty()) {
                val node = queue.removeFirst()
                if (node == null) {
                    result.add("null")
                } else {
                    result.add(node.`val`.toString())
                    queue.add(node.left)
                    queue.add(node.right)
                }
            }

            // Remove trailing nulls for cleaner output
            while (result.isNotEmpty() && result.last() == "null") {
                result.removeLast()
            }

            return result.toString()
        }
    }


}

fun main() {
    val bt = _22_BinaryTreeFromPreorderInorder()

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
