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
        inorder.forEachIndexed{ ele,index ->
            if(ele==element) return index
        }
        return 0
    }

    fun solve(preorder: IntArray, inorder: IntArray,preorderIndex:Int):TreeNode?{

        val newNode = TreeNode(preorder[preorderIndex])
        val elementIndex = findInorderIndex(inorder,preorder[preorderIndex])
        newNode.left = solve(preorder.subArray(preorderIndex+1,elementIndex), inorder.subArray(0,elementIndex),preorderIndex)

        newNode.right = solve(preorder.subArray(elementIndex,preorder.length-1), inorder.subArray(elementIndex,inorder.length-1),preorderIndex)
        return newNode
    }

    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {

    }

}

fun main() {
    val bt = _22_BinaryTreeFromPreorderInorder()

}
