package binary_search_tree
import binary_search_tree.BST.TreeNode
import binary_search_tree.BST.inorderTraversal
import binary_search_tree.BST.insertRecursively

/**
 * Problem Link : https://leetcode.com/problems/delete-node-in-a-bst/
 * Video Explanation : https://www.geeksforgeeks.org/dsa/deletion-in-binary-search-tree/
 *
 * 450. Delete Node in a BST
 * Solved
 * Medium
 *
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *     Search for a node to remove.
 *     If the node is found, delete the node.
 *
 * Example 1:
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 * Explanation: The tree does not contain a node with value = 0.
 *
 * Example 3:
 * Input: root = [], key = 0
 * Output: []
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [0, 104].
 *     -105 <= Node.val <= 105
 *     Each node has a unique value.
 *     root is a valid binary search tree.
 *     -105 <= key <= 105
 *
 * Follow up: Could you solve it with time complexity O(height of tree)?
 *
 */
class _2_DeleteNodeInBST {

    fun findInorderSuccessor(node: TreeNode): TreeNode {
        var node = node.right
        while (node!=null && node.left!=null){
            node = node.left
        }
        return node!!
    }

    /**
    * Solution 1
     * Time Complexity: O(h)
     *
     * Average case: O(log n) for balanced BST
     * Worst case: O(n) for skewed BST
     *
     * Where h is the height of the tree. You traverse down once to find the node, then potentially traverse down again to find the inorder successor and delete it.
     * Space Complexity: O(h)
     *
     * Average case: O(log n) for balanced BST
     * Worst case: O(n) for skewed BST
     *
     * Revisited - 26th Jan 2026
    * */
    fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
        if (root==null) return null
        if (key>root.`val`){
            root.right = deleteNode(root.right,key)
        }else if (key<root.`val`){
            root.left = deleteNode(root.left,key)
        }else{
            if (root.left==null) return root.right
            if (root.right==null) return root.left
            val inorderSuccessor = findInorderSuccessor(root)
            root.`val` = inorderSuccessor.`val`
            root.right = deleteNode(root.right,inorderSuccessor.`val`)
        }

        return root
    }

}

fun main(){
    val bst = _2_DeleteNodeInBST()

    /* ---------- Build BST ---------- */
    var root: TreeNode? = null
    val values = listOf(50, 30, 70, 20, 40, 60, 80, 35)

    for (v in values) {
        root = insertRecursively(root, v)
    }

    print("Initial BST In-order Traversal: ")
    inorderTraversal(root)
    println()
    println("Expected                      : 20 30 35 40 50 60 70 80")
    println()

    /* ---------- Test Case 1: Delete leaf node ---------- */
    println("Test Case 1: Delete leaf node (20)")
    root = bst.deleteNode(root, 20)
    print("After deleting 20: ")
    inorderTraversal(root)
    println()
    println("Expected         : 30 35 40 50 60 70 80")
    println()

    /* ---------- Test Case 2: Delete node with one child ---------- */
    println("Test Case 2: Delete node with one child (40)")
    root = bst.deleteNode(root, 40)
    print("After deleting 40: ")
    inorderTraversal(root)
    println()
    println("Expected         : 30 35 50 60 70 80")
    println()

    /* ---------- Test Case 3: Delete node with two children ---------- */
    println("Test Case 3: Delete node with two children (30)")
    root = bst.deleteNode(root, 30)
    print("After deleting 30: ")
    inorderTraversal(root)
    println()
    println("Expected         : 35 50 60 70 80")
    println()

    /* ---------- Test Case 4: Delete root node ---------- */
    println("Test Case 4: Delete root node (50)")
    root = bst.deleteNode(root, 50)
    print("After deleting 50: ")
    inorderTraversal(root)
    println()
    println("Expected         : 35 60 70 80")
    println()

    /* ---------- Test Case 5: Delete non-existent node ---------- */
    println("Test Case 5: Delete non-existent node (100)")
    root = bst.deleteNode(root, 100)
    print("After deleting 100: ")
    inorderTraversal(root)
    println()
    println("Expected          : 35 60 70 80 (no change)")
    println()
}