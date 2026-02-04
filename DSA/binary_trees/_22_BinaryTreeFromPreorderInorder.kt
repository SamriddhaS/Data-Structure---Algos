package binary_trees
import binary_search_tree.BST.TreeNode
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

    fun solve1(preorder: IntArray,inorder: IntArray,inorderMap: HashMap<Int,Int>):TreeNode?{
        if (preorder.size==0) return null
        val newNode = TreeNode(preorder[0])
        val indexInInorder = inorderMap[preorder[0]] ?: 0
        val inorderLeft = inorder.copyOfRange(0,indexInInorder)
        val inorderRight = inorder.copyOfRange(indexInInorder+1,inorder.size)
        val preorderLeft = preorder.copyOfRange(1,inorderLeft.size+1)
        val preorderRight = preorder.copyOfRange(inorderLeft.size+1,preorder.size)
        newNode.left = solve1(preorderLeft,inorderLeft,inorderMap)
        newNode.right = solve1(preorderRight,inorderRight,inorderMap)
        return newNode
    }

    fun buildTree1(preorder: IntArray, inorder: IntArray): TreeNode? {
        val inorderMap = HashMap<Int,Int>()
        inorder.forEachIndexed { index, i ->
            inorderMap[i] = index
        }
        return solve1(preorder,inorder,inorderMap)
    }

}

fun main() {
    val bt = _22_BinaryTreeFromPreorderInorder()

}
