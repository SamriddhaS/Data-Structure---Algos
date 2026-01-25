package binary_search_tree
import binary_search_tree.BST.TreeNode
import binary_search_tree.BST.inorderTraversal
import binary_search_tree.BST.insertRecursively

/**
 * Problem Link : https://www.naukri.com/code360/problems/floor-from-bst_920457?leftPanelTabValue=PROBLEM
 * Video Explanation : https://www.youtube.com/watch?v=xm_W1ub-K-w&feature=youtu.be
 *
 * Floor in BST
 * Moderate
 *
 * You are given a BST (Binary search tree) with N number of nodes and a value ‘X’.
 * Your task is to find the greatest value node of the BST which is smaller than or equal to ‘X’.
 *
 * Note :‘X’ is not smaller than the smallest node of BST .
 * For example:
 * In the above example, For the given BST  and X = 7, the greatest value node of the BST
 * which is smaller than or equal to  7 is 6.
 *
 */
class _4_FloorInBST {

    /**
     * Solution 1
     * Time Complexity: O(h) where h is the height of the tree
     *   In the worst case, you traverse from root to a leaf node
     *   For a balanced BST: O(log n)
     *   For a skewed BST: O(n)
     *
     * Space Complexity: O(1)
     *   Only using a constant amount of extra space (current and answer variables)
     *   No recursion stack or additional data structures
     * */
    fun floorInBST(root: TreeNode?, x: Int): Int {
        if (root==null) return -1
        var current: TreeNode? = root
        var answer=-1
        while (current!=null){
            if (x==current.`val`){
                answer = current.`val`
                break
            }else if (current.`val`<x){
                answer = current.`val`
                current = current.right
            }else{
                current = current.left
            }
        }
        return answer
    }

}

fun main() {
    val bst = _4_FloorInBST()

    /* ---------- Build BST ---------- */
    var root: TreeNode? = null
    val values = listOf(50, 30, 70, 20, 40, 60, 80, 35)

    for (v in values) {
        root = insertRecursively(root, v)
    }

    print("BST In-order Traversal: ")
    inorderTraversal(root)
    println()
    println("Expected              : 20 30 35 40 50 60 70 80")
    println()

    /* ---------- Test Case 1: Find floor of existing value ---------- */
    println("Test Case 1: Find floor of existing value (40)")
    val result1 = bst.floorInBST(root, 40)
    println("Result  : $result1")
    println("Expected: 40")
    println()

    /* ---------- Test Case 2: Find floor between two values ---------- */
    println("Test Case 2: Find floor between two values (45)")
    val result2 = bst.floorInBST(root, 45)
    println("Result  : $result2")
    println("Expected: 40")
    println()

    /* ---------- Test Case 3: Find floor smaller than smallest value ---------- */
    println("Test Case 3: Find floor smaller than smallest value (10)")
    val result3 = bst.floorInBST(root, 10)
    println("Result  : $result3")
    println("Expected: -1")
    println()

    /* ---------- Test Case 4: Find floor larger than largest value ---------- */
    println("Test Case 4: Find floor larger than largest value (100)")
    val result4 = bst.floorInBST(root, 100)
    println("Result  : $result4")
    println("Expected: 80")
    println()

    /* ---------- Test Case 5: Find floor between leaf nodes ---------- */
    println("Test Case 5: Find floor between leaf nodes (36)")
    val result5 = bst.floorInBST(root, 36)
    println("Result  : $result5")
    println("Expected: 35")
    println()

    /* ---------- Test Case 6: Find floor at root ---------- */
    println("Test Case 6: Find floor at root (50)")
    val result6 = bst.floorInBST(root, 50)
    println("Result  : $result6")
    println("Expected: 50")
    println()

    /* ---------- Test Case 7: Find floor with null root ---------- */
    println("Test Case 7: Find floor with null root")
    val result7 = bst.floorInBST(null, 25)
    println("Result  : $result7")
    println("Expected: -1")
    println()
}
