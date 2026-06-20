package binary_search_tree
import binary_search_tree.BST.TreeNode

/**
 * Problem Link : https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * Video Explanation :
 *
 * 108. Convert Sorted Array to Binary Search Tree
 * Easy
 *
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a binary search tree.
 *
 * Example 1:
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 *
 * Example 2:
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 *
 * Constraints:
 *     1 <= nums.length <= 104
 *     -104 <= nums[i] <= 104
 *     nums is sorted in a strictly increasing order.
 *
 */
class _12_SortedArrayToBst {

    fun solve(nums: IntArray, start:Int, end:Int): TreeNode?{
        //-10,-3,0,5,9
        val noOfItems = end - start + 1
        if (noOfItems<=0||end<start) return null
        if (noOfItems==1) return TreeNode(nums[start])
        val steps = (end-start)/2 // middle is these many steps away from start.
        val middle = start+steps // starting pointer + no of steps should give us our actual middle index
        val node = TreeNode(nums[middle])
        node.left = solve(nums,start,middle-1) // the left subtree from middle
        node.right = solve(nums,middle+1,end) // the right subtree from middle
        return node
    }

    /**
     * Time Complexity: O(n)
     *
     * Each element in the array is visited exactly once to create a TreeNode, so the work is linear in the number of elements.
     *
     * Space Complexity: O(log n)
     * The output tree itself isn't counted (it's the required result, not auxiliary space).
     * Auxiliary space comes from the recursion call stack. Since you always pick the middle element, the tree is height-balanced, giving a recursion depth of O(log n).
     *
     * Note: If you do count the output tree as space, it'd be O(n) since you're creating n TreeNodes — but the extra/auxiliary space due to recursion is O(log n).
     *
    * */
    fun sortedArrayToBST(nums: IntArray): TreeNode? {
        return solve(nums = nums, start = 0, end = nums.size-1)
    }

    fun printTree(node: TreeNode?){
        if (node==null) return
        printTree(node.left)
        println(node.`val`)
        printTree(node.right)
    }
}

fun main() {
    val convert = _12_SortedArrayToBst()

    println("=== sortedArrayToBST Tests ===")
    println()

    val nums1 = intArrayOf(-10, -3, 0, 5, 9)
    println("Test 1 - odd length:   Actual: ${convert.printTree(convert.sortedArrayToBST(nums1))}, Expected: [-10, -3, 0, 5, 9]")

    val nums2 = intArrayOf(0, 1, 2, 3, 4, 5)
    println("Test 2 - even length:  Actual: ${convert.printTree(convert.sortedArrayToBST(nums2))}, Expected: [0, 1, 2, 3, 4, 5]")

    val nums3 = intArrayOf(5)
    println("Test 3 - single node:  Actual: ${convert.printTree(convert.sortedArrayToBST(nums3))}, Expected: [5]")

    val nums4 = intArrayOf()
    println("Test 4 - empty array:  Actual: ${convert.printTree(convert.sortedArrayToBST(nums4))}, Expected: []")

    val nums5 = intArrayOf(1, 2)
    println("Test 5 - two elements: Actual: ${convert.printTree(convert.sortedArrayToBST(nums5))}, Expected: [1, 2]")

    val nums6 = intArrayOf(-20, -10, -5, 0, 5, 10, 20)
    println("Test 6 - negatives:    Actual: ${convert.printTree(convert.sortedArrayToBST(nums6))}, Expected: [-20, -10, -5, 0, 5, 10, 20]")

    val nums7 = intArrayOf(1, 1, 2, 2, 3)
    println("Test 7 - duplicates:   Actual: ${convert.printTree(convert.sortedArrayToBST(nums7))}, Expected: [1, 1, 2, 2, 3]")
}
