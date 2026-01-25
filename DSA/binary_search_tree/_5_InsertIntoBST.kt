package binary_search_tree
import binary_search_tree.BST.TreeNode
import binary_search_tree.BST.inorderTraversal
import binary_search_tree.BST.insertRecursively

/**
 * Problem Link : https://leetcode.com/problems/insert-into-a-binary-search-tree/
 * Video Explanation :
 *
 * 701. Insert into a Binary Search Tree
 *
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree.
 * Return the root node of the BST after the insertion. It is guaranteed that the new value
 * does not exist in the original BST.
 *
 * Notice that there may exist multiple valid ways for the insertion,
 * as long as the tree remains a BST after insertion. You can return any of them.
 *
 * Example 1:
 * Input: root = [4,2,7,1,3], val = 5
 * Output: [4,2,7,1,3,5]
 * Explanation: Another accepted tree is:
 *
 * Example 2:
 * Input: root = [40,20,60,10,30,50,70], val = 25
 * Output: [40,20,60,10,30,50,70,null,null,25]
 *
 * Example 3:
 * Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * Output: [4,2,7,1,3,5]
 *
 * Constraints:
 *
 *     The number of nodes in the tree will be in the range [0, 104].
 *     -108 <= Node.val <= 108
 *     All the values Node.val are unique.
 *     -108 <= val <= 108
 *     It's guaranteed that val does not exist in the original BST.
 */
class _5_InsertIntoBST {

    /**
     * Solution 1
     *
     * Time Complexity
     * O(h), where h is the height of the tree.
     *
     * Best case: O(1)
     * → Root is null or insertion happens at the first comparison.
     * Average case: O(log n)
     * → BST is balanced, height ≈ log n.
     * Worst case: O(n)
     * → BST is skewed (like a linked list), height = n.
     *
     * Space Complexity
     * O(1)
     * → Iterative approach, no recursion stack, only constant extra variables.
     * */
    fun insertIntoBST(root: TreeNode?, `val`: Int): TreeNode? {
        if(root==null) {
            return TreeNode(`val`)
        }
        var current = root!!
        while(true){
            if(current.`val`>`val`){
                if(current.left==null){
                    val node = TreeNode(`val`)
                    current.left = node
                    break
                }
                current = current.left!!
            }else{
                if(current.right==null){
                    val node = TreeNode(`val`)
                    current.right = node
                    break
                }
                current = current.right!!
            }
        }
        return root
    }

}

fun main() {
    val bst = _5_InsertIntoBST()

    /* ---------- Test Case 1: Insert into empty BST ---------- */
    println("Test Case 1: Insert into empty BST")
    var root: TreeNode? = null
    root = bst.insertIntoBST(root, 50)

    print("In-order Traversal: ")
    inorderTraversal(root)
    println()
    println("Expected           : 50")
    println()

    /* ---------- Test Case 2: Insert multiple values ---------- */
    println("Test Case 2: Insert multiple values")
    val values = listOf(30, 70, 20, 40, 60, 80, 35)

    for (v in values) {
        root = bst.insertIntoBST(root, v)
    }

    print("In-order Traversal: ")
    inorderTraversal(root)
    println()
    println("Expected           : 20 30 35 40 50 60 70 80")
    println()

    /* ---------- Test Case 3: Insert value smaller than all nodes ---------- */
    println("Test Case 3: Insert value smaller than all nodes (10)")
    root = bst.insertIntoBST(root, 10)

    print("In-order Traversal: ")
    inorderTraversal(root)
    println()
    println("Expected           : 10 20 30 35 40 50 60 70 80")
    println()

    /* ---------- Test Case 4: Insert value larger than all nodes ---------- */
    println("Test Case 4: Insert value larger than all nodes (100)")
    root = bst.insertIntoBST(root, 100)

    print("In-order Traversal: ")
    inorderTraversal(root)
    println()
    println("Expected           : 10 20 30 35 40 50 60 70 80 100")
    println()

    /* ---------- Test Case 5: Insert duplicate value ---------- */
    println("Test Case 5: Insert duplicate value (40)")
    root = bst.insertIntoBST(root, 40)

    print("In-order Traversal: ")
    inorderTraversal(root)
    println()
    println("Expected           : 10 20 30 35 40 40 50 60 70 80 100")
    println()
}

