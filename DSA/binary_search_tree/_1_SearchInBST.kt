package binary_search_tree
import binary_search_tree.BST.TreeNode
import binary_search_tree.BST.inorderTraversal
import binary_search_tree.BST.insertRecursively

/**
 * Problem Link : https://leetcode.com/problems/search-in-a-binary-search-tree/
 * Video Explanation : https://www.geeksforgeeks.org/dsa/binary-search-tree-set-1-search-and-insertion/
 *
 * 700. Search in a Binary Search Tree
 * Solved
 * Easy
 *
 * You are given the root of a binary search tree (BST) and an integer val.
 * Find the node in the BST that the node's value equals val and return the subtree
 * rooted with that node. If such a node does not exist, return null.
 *
 * Example 1:
 * Input: root = [4,2,7,1,3], val = 2
 * Output: [2,1,3]
 *
 * Example 2:
 * Input: root = [4,2,7,1,3], val = 5
 * Output: []
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [1, 5000].
 *     1 <= Node.val <= 107
 *     root is a binary search tree.
 *     1 <= val <= 107
 *
 */
class _1_SearchInBST {

    /**
     * Solution 1
     * Time Complexity: O(h) where h is the height of the tree
     *
     * Best case (balanced BST): O(log n)
     * Worst case (skewed tree): O(n)
     *
     * Each recursive call moves one level down the tree, eliminating roughly half
     * the nodes in a balanced tree or one node in a skewed tree.
     *
     * Space Complexity: O(h)
     * Due to the recursive call stack
     * Best case: O(log n) for balanced tree
     * Worst case: O(n) for completely skewed tree
     *
     * The recursion depth equals the height of the tree in the worst case (when searching for a leaf or non-existent value).
     *
    * */
    fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
        if(root==null) return null;
        if(root.`val`==`val`) return root;
        if(`val`>root.`val`) return searchBST(root.right,`val`);
        else return searchBST(root.left,`val`);
    }

}

fun main(){
    val bst = _1_SearchInBST()

    /* ---------- Build BST ---------- */
    var root: TreeNode? = null
    val values = listOf(50, 30, 70, 20, 40, 60, 80, 35)

    for (v in values) {
        root = insertRecursively(root, v)
    }

    print("BST In-order Traversal       : ")
    inorderTraversal(root)
    println()
    println("Expected In-order Traversal : 20 30 35 40 50 60 70 80")
    println()

    /* ---------- Test Case 1: Search existing value ---------- */
    val searchValue1 = 35
    val result1 = bst.searchBST(root, searchValue1)

    println("Search Value : $searchValue1")
    println("Actual Result: ${result1?.`val` ?: "Not Found"}")
    println("Expected     : 35")
    println()

    /* ---------- Test Case 2: Search root value ---------- */
    val searchValue2 = 50
    val result2 = bst.searchBST(root, searchValue2)

    println("Search Value : $searchValue2")
    println("Actual Result: ${result2?.`val` ?: "Not Found"}")
    println("Expected     : 50")
    println()

    /* ---------- Test Case 3: Search non-existing value ---------- */
    val searchValue3 = 90
    val result3 = bst.searchBST(root, searchValue3)

    println("Search Value : $searchValue3")
    println("Actual Result: ${result3?.`val` ?: "Not Found"}")
    println("Expected     : Not Found")
    println()

    /* ---------- Test Case 4: Search leaf node ---------- */
    val searchValue4 = 20
    val result4 = bst.searchBST(root, searchValue4)

    println("Search Value : $searchValue4")
    println("Actual Result: ${result4?.`val` ?: "Not Found"}")
    println("Expected     : 20")
}