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
        val middle = (end-start)/2
        val node = TreeNode(nums[start+middle])
        node.left = solve(nums,start,middle-1)
        node.right = solve(nums,start+middle+1,end)
        return node
    }

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

    //working
    val nums = intArrayOf(-10,-3,0,5,9)
    val result = convert.sortedArrayToBST(nums)
    convert.printTree(result)

    //not working
    val nums1 = intArrayOf(0,1,2,3,4,5)
    val result1 = convert.sortedArrayToBST(nums1)
    convert.printTree(result1)
}
