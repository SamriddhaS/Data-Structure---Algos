package binary_search_tree
import binary_search_tree.BST.TreeNode

/**
 * Problem Link : https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
 * Video Explanation :
 *
 * 653. Two Sum IV - Input is a BST
 *
 * Given the root of a binary search tree and an integer k, return true if there exist
 * two elements in the BST such that their sum is
 * equal to k, or false otherwise.
 *
 * Example 1:
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [1, 104].
 *     -104 <= Node.val <= 104
 *     root is guaranteed to be a valid binary search tree.
 *     -105 <= k <= 105
 */
class _10_BSTTwoSum {
    val map = HashMap<Int,Int>()
    fun travarsal(
        node:TreeNode?,
        k:Int
    ):Boolean{
        if(node==null) return false
        val needed = k - node.`val`
        if(map.containsKey(needed)) return true
        map[node.`val`] = node.`val`
        if(travarsal(node.left,k)) return true
        if(travarsal(node.right,k)) return true
        return false
    }

    /**
     * Solution 1 : Using extra space
     *
     * Time: O(n)
     * Every node is visited exactly once.
     * Space: O(n)
     *
     * Two sources of space usage, both O(n):
     * HashMap — stores at most n values
     * Call stack — recursion depth is O(h) where h is tree height
     * Balanced tree → O(log n)
     * Skewed tree → O(n) (worst case)
     *
     * */
    fun findTarget(root: TreeNode?, k: Int): Boolean {
        return travarsal(root,k)
    }

    var arrayList = arrayListOf<Int>()
    fun inorderTravarsal(
        node:TreeNode?,
        k:Int,
    ){
        if(node==null) return
        inorderTravarsal(node.left,k)
        arrayList.add(node.`val`)
        inorderTravarsal(node.right,k)
    }

    /**
     * Solution 2 :
     * Same Time and space complexity as sol 1.
     * */
    fun findTarget1(root: TreeNode?, k: Int): Boolean {
        arrayList = arrayListOf()
        inorderTravarsal(root,k)
        var start =0
        var end = arrayList.size-1
        while (start<end){
            if (arrayList[start]+arrayList[end]<k){
                start++
            }else if (arrayList[start]+arrayList[end]>k){
                end--
            }else{
                return true
            }
        }
        return false
    }
}

fun main() {
    val twoSum = _10_BSTTwoSum()

    println("=== findTarget Tests ===")
    println()

    //        5
    //       / \
    //      3   6
    //     / \    \
    //    2   4    7
    val ft1 = TreeNode(5).apply {
        left = TreeNode(3).apply {
            left = TreeNode(2)
            right = TreeNode(4)
        }
        right = TreeNode(6).apply {
            right = TreeNode(7)
        }
    }
    println("Test 1 - k=9:  Actual: ${twoSum.findTarget(ft1, 9)},   Expected: true  (2+7 or 3+6)")
    println("Test 2 - k=28: Actual: ${twoSum.findTarget(ft1, 28)},  Expected: false")
    println("Test 3 - k=6:  Actual: ${twoSum.findTarget(ft1, 6)},   Expected: true  (2+4)")
    println("Test 4 - k=4:  Actual: ${twoSum.findTarget(ft1, 4)},   Expected: false (no valid pair)")
    println()

    // Single node
    val ft2 = TreeNode(1)
    println("Test 5 - k=2:  Actual: ${twoSum.findTarget(ft2, 2)},   Expected: false (single node)")
    println()

    // Two nodes: 5 -> 3
    val ft3 = TreeNode(5).apply {
        left = TreeNode(3)
    }
    println("Test 6 - k=8:  Actual: ${twoSum.findTarget(ft3, 8)},   Expected: true  (5+3)")
    println("Test 7 - k=6:  Actual: ${twoSum.findTarget(ft3, 6)},   Expected: false (no valid pair)")
    println()

    //          0
    //         / \
    //       -3   2
    //       /   / \
    //     -5   1   4
    val ft4 = TreeNode(0).apply {
        left = TreeNode(-3).apply {
            left = TreeNode(-5)
        }
        right = TreeNode(2).apply {
            left = TreeNode(1)
            right = TreeNode(4)
        }
    }
    println("Test 8  - k=-8: Actual: ${twoSum.findTarget(ft4, -8)},  Expected: true  (-5+-3)")
    println("Test 9  - k=-3: Actual: ${twoSum.findTarget(ft4, -3)},  Expected: true  (-5+2)")
    println("Test 10 - k=0:  Actual: ${twoSum.findTarget(ft4, 0)},   Expected: false (no valid pair)")
    println("Test 11 - k=-4: Actual: ${twoSum.findTarget(ft4, -4)},  Expected: true  (-5+1)")
    println("Test 12 - k=1:  Actual: ${twoSum.findTarget(ft4, 1)},   Expected: true  (-3+4)")
    println()

    //           -1
    //           / \
    //         -6   3
    //         /   / \
    //       -9  -2   5
    val ft5 = TreeNode(-1).apply {
        left = TreeNode(-6).apply {
            left = TreeNode(-9)
        }
        right = TreeNode(3).apply {
            left = TreeNode(-2)
            right = TreeNode(5)
        }
    }
    println("Test 13 - k=-3: Actual: ${twoSum.findTarget(ft5, -3)},  Expected: true  (-2+-1)")
    println("Test 14 - k=-7: Actual: ${twoSum.findTarget(ft5, -7)},  Expected: true  (-6+-1)")
    println("Test 15 - k=4:  Actual: ${twoSum.findTarget(ft5, 4)},   Expected: true  (-1+5)")
    println("Test 16 - k=2:  Actual: ${twoSum.findTarget(ft5, 2)},   Expected: true  (-1+3)")
    println("Test 17 - k=-2: Actual: ${twoSum.findTarget(ft5, -2)},  Expected: false (no valid pair)")
    println()

    //      -5
    //      / \
    //    -8  -2
    val ft6 = TreeNode(-5).apply {
        left = TreeNode(-8)
        right = TreeNode(-2)
    }
    println("Test 18 - k=-10: Actual: ${twoSum.findTarget(ft6, -10)},  Expected: true  (-8+-2)")
    println("Test 19 - k=-7:  Actual: ${twoSum.findTarget(ft6, -7)},   Expected: true  (-5+-2)")
    println("Test 20 - k=-13: Actual: ${twoSum.findTarget(ft6, -13)},  Expected: true  (-5+-8)")
    println("Test 21 - k=-4:  Actual: ${twoSum.findTarget(ft6, -4)},   Expected: false (no valid pair)")
    println()

    //      0
    //     / \
    //   -7   7
    //   /     \
    // -10      10
    val ft7 = TreeNode(0).apply {
        left = TreeNode(-7).apply {
            left = TreeNode(-10)
        }
        right = TreeNode(7).apply {
            right = TreeNode(10)
        }
    }
    println("Test 22 - k=0:   Actual: ${twoSum.findTarget(ft7, 0)},   Expected: true  (-7+7)")
    println("Test 23 - k=3:   Actual: ${twoSum.findTarget(ft7, 3)},   Expected: true  (-7+10)")
    println("Test 24 - k=14:  Actual: ${twoSum.findTarget(ft7, 14)},  Expected: false (only one 7)")
    println("Test 25 - k=17:  Actual: ${twoSum.findTarget(ft7, 17)},  Expected: true  (7+10)")
    println("Test 26 - k=100: Actual: ${twoSum.findTarget(ft7, 100)}, Expected: false")
}
