package binary_search_tree
import binary_search_tree.BST.TreeNode
import binary_search_tree.BST.inorderTraversal
import binary_search_tree.BST.insertRecursively

/**
 * Problem Link : https://www.naukri.com/code360/problems/ceil-from-bst_920464
 * Video Explanation : https://www.youtube.com/watch?v=KSsk8AhdOZA
 *
 * Ceil from BST
 *
 * Problem statement
 * Ninja is given a binary search tree and an integer. Now he is given a particular key in the tree and returns its ceil value. Can you help Ninja solve the problem?
 * Note:
 *
 * Ceil of an integer is the closest integer greater than or equal to a given number.
 * For example:
 * arr[] = {1, 2, 5, 7, 8, 9}, key = 3.
 * The closest integer greater than 3 in the given array is 5. So, its ceil value in the given array is 5.
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 *
 * 1 <= T <= 10
 * 1 <= N <= 10^5
 * 0 <= node data <= 10^9
 * 1 <= X <= 10^9
 *
 *
 */
class _3_CeilInBST {

    /**
     * Solution 1
     *  Time Complexity: O(h) where h is the height of the tree
     *
     *     In the worst case, you traverse from root to a leaf node
     *     For a balanced BST: O(log n)
     *     For a skewed BST: O(n)
     *
     * Space Complexity: O(1)
     *
     *     Only using a constant amount of extra space (current and answer variables)
     *     No recursion stack or additional data structures
     *
     *
     * */
    fun findCeil(root: TreeNode?, key: Int): Int {
        if (root==null) return -1
        var current: TreeNode? = root
        var answer=-1
        while (current!=null){
            if (current.`val`==key){
                answer = current.`val`
                break
            }else if (current.`val`>key){
                answer = current.`val`
                current = current.left
            }else{
                current = current.right
            }
        }
        return answer
    }

}

fun main(){
    val bst = _3_CeilInBST()

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

    /* ---------- Test Case 1: Find ceil of existing value ---------- */
    println("Test Case 1: Find ceil of existing value (40)")
    val result1 = bst.findCeil(root, 40)
    println("Result  : $result1")
    println("Expected: 40")
    println()

    /* ---------- Test Case 2: Find ceil between two values ---------- */
    println("Test Case 2: Find ceil between two values (45)")
    val result2 = bst.findCeil(root, 45)
    println("Result  : $result2")
    println("Expected: 50")
    println()

    /* ---------- Test Case 3: Find ceil smaller than smallest value ---------- */
    println("Test Case 3: Find ceil smaller than smallest value (10)")
    val result3 = bst.findCeil(root, 10)
    println("Result  : $result3")
    println("Expected: 20")
    println()

    /* ---------- Test Case 4: Find ceil larger than largest value ---------- */
    println("Test Case 4: Find ceil larger than largest value (100)")
    val result4 = bst.findCeil(root, 100)
    println("Result  : $result4")
    println("Expected: -1")
    println()

    /* ---------- Test Case 5: Find ceil between leaf nodes ---------- */
    println("Test Case 5: Find ceil between leaf nodes (36)")
    val result5 = bst.findCeil(root, 36)
    println("Result  : $result5")
    println("Expected: 40")
    println()

    /* ---------- Test Case 6: Find ceil at root ---------- */
    println("Test Case 6: Find ceil at root (50)")
    val result6 = bst.findCeil(root, 50)
    println("Result  : $result6")
    println("Expected: 50")
    println()

    /* ---------- Test Case 7: Find ceil with null root ---------- */
    println("Test Case 7: Find ceil with null root")
    val result7 = bst.findCeil(null, 25)
    println("Result  : $result7")
    println("Expected: -1")
    println()
}