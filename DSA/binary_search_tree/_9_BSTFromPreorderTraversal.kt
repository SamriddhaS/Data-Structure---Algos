package binary_search_tree
import binary_search_tree.BST.TreeNode
import java.util.Stack

/**
 * Problem Link : https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * Video Explanation :
 *
 * 1008. Construct Binary Search Tree from Preorder Traversal
 * Solved
 * Medium
 *
 * Given an array of integers preorder, which represents the preorder traversal of a
 * BST (i.e., binary search tree), construct the tree and return its root.
 *
 * It is guaranteed that there is always possible to find a binary search tree with
 * the given requirements for the given test cases.
 *
 * A binary search tree is a binary tree where for every node, any descendant of Node.left has
 * a value strictly less than Node.val, and any descendant of Node.right has a value strictly
 * greater than Node.val.
 *
 * A preorder traversal of a binary tree displays the value of the node first,
 * then traverses Node.left, then traverses Node.right.
 *
 * Example 1:
 * Input: preorder = [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 *
 * Example 2:
 * Input: preorder = [1,3]
 * Output: [1,null,3]
 *
 * Constraints:
 *     1 <= preorder.length <= 100
 *     1 <= preorder[i] <= 1000
 *     All the values of preorder are unique.
 */
class _9_BSTFromPreorderTraversal {

    /**
     * Time Complexity: O(n)
     * Each node is pushed and popped from the stack at most once, so the total work across
     * all iterations is linear.
     *
     * Space Complexity: O(n)
     * In the worst case (fully skewed tree), the stack holds all n nodes. In the average/balanced case
     * it holds O(h) where h is the height of the tree.
     *
     * Intuition :
     *
     * The key insight is that preorder traversal visits: Root → Left → Right.
     * So as you scan the array left to right:
     *
     * If the next value is smaller than the current node, it must be its left child — just attach it directly.
     * If the next value is larger, it can't be a left child of anything currently on the stack.
     * So you pop nodes off the stack until you find the last node whose value is still smaller
     * than the new value — the new node becomes that node's right child.
     *
     * The stack essentially keeps track of the path from the root to the current node.
     * Popping simulates backtracking up the tree to find the correct parent for a right child.
    * */
    fun bstFromPreorder(preorder: IntArray): TreeNode? {
        val root = TreeNode(preorder[0])
        val stack = Stack<TreeNode>()
        stack.push(root)
        var current = root
        for ( i in 1 until preorder.size ){
            val newNode = TreeNode(preorder[i])
            if (newNode.`val`<stack.peek().`val`){
                // If new node is smaller than our stack top then it belongs to
                // the left side of stack.top() node.
                current = stack.peek()
                current.left = newNode
            }else{
                // If new node is greater than our stack top then it doesn't belong to
                // left of current stack.top(). We keep pop-ing the stack values until the stack
                // is empty or we found a value from stack top that is greater than new node.
                // At this point whatever value stack held before this peek ( last pop-ed value )
                // our new node will go to right of that.
                while (stack.isNotEmpty() && newNode.`val`>stack.peek().`val`){
                    current = stack.pop()
                }
                current.right = newNode
            }
            stack.push(newNode)
        }
        return root
    }
}

fun main() {
    val bst = _9_BSTFromPreorderTraversal()

    // Helper: collects inorder traversal into a list
    fun inorder(node: TreeNode?): List<Int> {
        if (node == null) return emptyList()
        return inorder(node.left) + node.`val` + inorder(node.right)
    }

    // Test Case 1: Normal BST preorder
    //        8
    //       / \
    //      5   12
    //     / \    \
    //    2   7   15
    val preorder1 = intArrayOf(8, 5, 2, 7, 12, 15)
    val root1 = bst.bstFromPreorder(preorder1)
    println("Test 1 - Inorder:       Actual: ${inorder(root1)},   Expected: [2, 5, 7, 8, 12, 15]")
    println("Test 1 - Root:          Actual: ${root1?.`val`},              Expected: 8")
    println("Test 1 - Left:          Actual: ${root1?.left?.`val`},        Expected: 5")
    println("Test 1 - Right:         Actual: ${root1?.right?.`val`},       Expected: 12")
    println()

    // Test Case 2: Single node
    val preorder2 = intArrayOf(42)
    val root2 = bst.bstFromPreorder(preorder2)
    println("Test 2 - Inorder:       Actual: ${inorder(root2)},          Expected: [42]")
    println("Test 2 - Root:          Actual: ${root2?.`val`},              Expected: 42")
    println("Test 2 - Left:          Actual: ${root2?.left?.`val`},        Expected: null")
    println("Test 2 - Right:         Actual: ${root2?.right?.`val`},       Expected: null")
    println()

    // Test Case 3: Skewed left (descending order)
    //     5
    //    /
    //   4
    //  /
    // 3
    val preorder3 = intArrayOf(5, 4, 3)
    val root3 = bst.bstFromPreorder(preorder3)
    println("Test 3 - Inorder:       Actual: ${inorder(root3)},          Expected: [3, 4, 5]")
    println("Test 3 - Root:          Actual: ${root3?.`val`},              Expected: 5")
    println("Test 3 - Left:          Actual: ${root3?.left?.`val`},        Expected: 4")
    println("Test 3 - Left.Left:     Actual: ${root3?.left?.left?.`val`},  Expected: 3")
    println()

    // Test Case 4: Skewed right (ascending order)
    // 1
    //  \
    //   2
    //    \
    //     3
    val preorder4 = intArrayOf(1, 2, 3)
    val root4 = bst.bstFromPreorder(preorder4)
    println("Test 4 - Inorder:       Actual: ${inorder(root4)},          Expected: [1, 2, 3]")
    println("Test 4 - Root:          Actual: ${root4?.`val`},              Expected: 1")
    println("Test 4 - Right:         Actual: ${root4?.right?.`val`},       Expected: 2")
    println("Test 4 - Right.Right:   Actual: ${root4?.right?.right?.`val`}, Expected: 3")
    println()

    // Test Case 5: Two nodes (root + left child)
    val preorder5 = intArrayOf(10, 5)
    val root5 = bst.bstFromPreorder(preorder5)
    println("Test 5 - Inorder:       Actual: ${inorder(root5)},          Expected: [5, 10]")
    println("Test 5 - Root:          Actual: ${root5?.`val`},              Expected: 10")
    println("Test 5 - Left:          Actual: ${root5?.left?.`val`},        Expected: 5")
    println("Test 5 - Right:         Actual: ${root5?.right?.`val`},       Expected: null")
    println()

    // Test Case 6: Two nodes (root + right child)
    val preorder6 = intArrayOf(10, 15)
    val root6 = bst.bstFromPreorder(preorder6)
    println("Test 6 - Inorder:       Actual: ${inorder(root6)},          Expected: [10, 15]")
    println("Test 6 - Root:          Actual: ${root6?.`val`},              Expected: 10")
    println("Test 6 - Left:          Actual: ${root6?.left?.`val`},        Expected: null")
    println("Test 6 - Right:         Actual: ${root6?.right?.`val`},       Expected: 15")
    println()

    // Test Case 7: Larger tree with mixed structure
    //           20
    //          /  \
    //        10    25
    //       /  \     \
    //      5   15     30
    //     /   /
    //    2   12
    val preorder7 = intArrayOf(20, 10, 5, 2, 15, 12, 25, 30)
    val root7 = bst.bstFromPreorder(preorder7)
    println("Test 7 - Inorder:       Actual: ${inorder(root7)},                      Expected: [2, 5, 10, 12, 15, 20, 25, 30]")
    println("Test 7 - Root:          Actual: ${root7?.`val`},                          Expected: 20")
    println("Test 7 - Left:          Actual: ${root7?.left?.`val`},                    Expected: 10")
    println("Test 7 - Right:         Actual: ${root7?.right?.`val`},                   Expected: 25")
    println("Test 7 - Left.Left:     Actual: ${root7?.left?.left?.`val`},              Expected: 5")
    println("Test 7 - Left.Right:    Actual: ${root7?.left?.right?.`val`},             Expected: 15")
    println("Test 7 - Right.Right:   Actual: ${root7?.right?.right?.`val`},            Expected: 30")
    println("Test 7 - Left.Left.Left:Actual: ${root7?.left?.left?.left?.`val`},        Expected: 2")
    println("Test 7 - Left.Right.Left:Actual: ${root7?.left?.right?.left?.`val`},      Expected: 12")
    println()
}
