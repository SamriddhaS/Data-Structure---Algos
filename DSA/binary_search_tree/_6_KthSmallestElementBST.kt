package binary_search_tree
import binary_search_tree.BST.TreeNode
import binary_search_tree.BST.inorderTraversal
import binary_search_tree.BST.insertRecursively

/**
 * Problem Link : https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
 * Video Explanation :
 *
 * 230. Kth Smallest Element in a BST
 * Medium
 *
 * Given the root of a binary search tree, and an integer k, return the kth smallest
 * value (1-indexed) of all the values of the nodes in the tree.
 *
 * Example 1:
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 *
 * Constraints:
 *     The number of nodes in the tree is n.
 *     1 <= k <= n <= 104
 *     0 <= Node.val <= 104
 *
 * Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and
 * you need to find the kth smallest frequently, how would you optimize?
 *
 */
class _6_KthSmallestElementBST {

    fun backtrack(root: TreeNode?,arraylist:ArrayList<Int>){
        if(root==null) return
        arraylist.add(root.`val`)
        backtrack(root.left,arraylist)
        backtrack(root.right,arraylist)
    }

    /**
     * Solution 1
     *
     * Time Complexity: O(n log n)
     * The backtrack function visits every node in the tree once: O(n)
     * Sorting the array: O(n log n)
     * Overall: O(n log n)
     *
     * Space Complexity: O(n)
     * ArrayList stores all n node values: O(n)
     * Recursion call stack depth: O(h) where h is tree height
     * Overall: O(n)
     * */
    fun kthSmallest(root: TreeNode?, k: Int): Int {
        val arraylist:ArrayList<Int> = arrayListOf()
        backtrack(root,arraylist)
        arraylist.sort()
        return arraylist[k-1]
    }

    var count=0

    fun inorder(root: TreeNode?, k: Int): Int? {
        if(root==null) return null
        val left = inorder(root.left,k)
        if(left!=null) return left
        count++
        if(count==k) return root.`val`
        return inorder(root.right,k)
    }

    /**
     * Solution 2
     *
     * Time Complexity
     * Worst case: O(n)
     * (you may visit all nodes if k is large)
     * Best case: O(k)
     * (you stop once kth element is found)
     *
     * Space Complexity
     * Average case - O(h) due to recursion stack, where h is tree height
     * Balanced BST → O(log n)
     * Skewed BST → O(n)
    * */
    fun kthSmallestOptimal(root: TreeNode?, k: Int): Int {
        count=0
        return inorder(root,k)!!
    }

}

fun main() {
    val bst = _6_KthSmallestElementBST()

    /* ---------- Test Case 1: Small BST ---------- */
    println("Test Case 1: Small BST")
    var root: TreeNode? = TreeNode(3)
    root?.left = TreeNode(1)
    root?.right = TreeNode(4)
    root?.left?.right = TreeNode(2)

    val k1 = 1
    val result1 = bst.kthSmallest(root, k1)
    val result11 = bst.kthSmallestOptimal(root, k1)
    println("kthSmallest(root, $k1) = $result1")
    println("kthSmallestOptimal(root, $k1) = $result11")
    println("Expected: 1")
    println()

    /* ---------- Test Case 2: Larger BST ---------- */
    println("Test Case 2: Larger BST")
    root = TreeNode(5)
    root.left = TreeNode(3)
    root.right = TreeNode(6)
    root.left?.left = TreeNode(2)
    root.left?.right = TreeNode(4)
    root.left?.left?.left = TreeNode(1)

    val k2 = 3
    val result2 = bst.kthSmallest(root, k2)
    val result12 = bst.kthSmallestOptimal(root, k2)
    println("kthSmallest(root, $k2) = $result2")
    println("kthSmallestOptimal(root, $k1) = $result12")
    println("Expected: 3")
    println()

    /* ---------- Test Case 3: Find largest element ---------- */
    println("Test Case 3: Find largest element")
    root = TreeNode(50)
    root.left = TreeNode(30)
    root.right = TreeNode(70)
    root.left?.left = TreeNode(20)
    root.left?.right = TreeNode(40)
    root.right?.left = TreeNode(60)
    root.right?.right = TreeNode(80)

    val k3 = 7
    val result3 = bst.kthSmallest(root, k3)
    val result33 = bst.kthSmallestOptimal(root, k3)
    println("kthSmallest(root, $k3) = $result3")
    println("kthSmallestOptimal(root, $k1) = $result33")
    println("Expected: 80")
    println()

    /* ---------- Test Case 4: Find middle element ---------- */
    println("Test Case 4: Find middle element")
    val k4 = 4
    val result4 = bst.kthSmallest(root, k4)
    println("kthSmallest(root, $k4) = $result4")
    println("Expected: 50")
    println()

    /* ---------- Test Case 5: Single node tree ---------- */
    println("Test Case 5: Single node tree")
    root = TreeNode(1)

    val k5 = 1
    val result5 = bst.kthSmallest(root, k5)
    println("kthSmallest(root, $k5) = $result5")
    println("Expected: 1")
    println()
}

