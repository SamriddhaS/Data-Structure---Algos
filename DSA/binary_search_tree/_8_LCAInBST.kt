package binary_search_tree
import binary_search_tree.BST.TreeNode

/**
 * Problem Link : https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * Video Explanation : https://www.youtube.com/watch?v=gs2LMfuOR9k
 *
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * Solved
 * Medium
 *
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the
 * lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 *
 * Example 1:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 *
 * Example 2:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 *
 * Example 3:
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [2, 105].
 *     -109 <= Node.val <= 109
 *     All Node.val are unique.
 *     p != q
 *     p and q will exist in the BST.
 *
 */
class _8_LCAInBST {

    val ancestors = HashSet<TreeNode>()

    fun dfs(
        root: TreeNode?,
        target: TreeNode?,
    ): Boolean {
        if (root==null) return false
        ancestors.add(root)
        if (root.`val`==target?.`val`) return true
        if(dfs(root.left,target)) return true
        if(dfs(root.right,target)) return true
        ancestors.remove(root)
        return false
    }

    fun dfsWithAncestorChecking(root: TreeNode?,
                                target: TreeNode?,
                                currentancestors: ArrayList<TreeNode>
                                ): TreeNode? {
        if (root==null) return null
        currentancestors.add(root)
        if(currentancestors.contains(target)){
            for (i in currentancestors.size - 1 downTo 0){
                if (ancestors.contains(currentancestors[i])) {
                    return currentancestors[i]
                }
            }
        }
        val ret = dfsWithAncestorChecking(root.left,target,currentancestors)
        if (ret!=null) return ret
        val re = dfsWithAncestorChecking(root.right,target,currentancestors)
        if (re!=null) return re
        currentancestors.remove(root)
        return null
    }

    /**
     * Solution 1 :
     *
     * Time Complexity: O(n)
     * Both dfs and dfsWithAncestorChecking traverse the tree once → O(n) + O(n) = O(n).
     * The currentancestors.contains(target) check is O(h) per call,
     * but it only triggers once (when q is found), so it doesn't change the overall bound.
     * The ancestor walk-back loop is also O(h) and runs once.
     *
     * Space Complexity: O(n)
     * ancestors HashSet stores up to O(n) nodes in the worst case (all nodes are ancestors of p in a skewed tree, but realistically O(h)).
     * currentancestors ArrayList holds O(h) nodes (current path depth).
     * Recursion stack: O(h) for both DFS calls.
     *
     * Worst case (skewed tree): h = n → O(n)
     * Balanced tree: h = log n → O(log n)
     *
    * */
    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        dfs(root,p)
        println(ancestors)
        return dfsWithAncestorChecking(root,q,arrayListOf())
    }

    fun solve(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root==null) return null
        // Both the target value is greater than current node, so this cant be the intersection point.
        // But as both the target is greater and this is BST then the intersection point will be somewhere in
        // right subtree.
        if (p?.`val`!!>root.`val`&&q?.`val`!!>root.`val`) return solve(root.right,p,q)

        // Similarly both the target value is lesser than current node then we explore the left subtree.
        if (p?.`val`!!<root.`val`&&q?.`val`!!<root.`val`) return solve(root.left,p,q)

        // During exploring if the current node is equal to one of our target value then
        // this current node is the intersection point
        if (p.`val`==root.`val` || q?.`val`==root.`val`) return root

        //intersection node found
        return root
    }

    /**
     * Solution 2 : Using the BST property to make a decision if the intersection point can be found in left/right
     * subtree.
     *
     * Time: O(h) — it only traverses a single path down the tree, not the entire tree. O(log n) balanced, O(n) skewed.
     * Space: O(h) due to recursion stack. Can be reduced to O(1) by converting to an iterative loop.
    * */
    fun lowestCommonAncestor1(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        return solve(root,p,q)
    }

    /**
    * Solution 3 : Using iterative way to avoid recursion stack space overhead
     *
     * Time: O(h) — same as Solution 2
     * Space: O(1) — no recursion stack, just a single current pointer
    * */
    fun lowestCommonAncestor2(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        if (root==null) return null
        var current = root
        while (true){
            if (p?.`val`!!>current?.`val`!!&&q?.`val`!!>current?.`val`!!) current = current?.right
            else if (p?.`val`!!<current?.`val`!!&&q?.`val`!!<current?.`val`!!) current = current?.left
            else break
        }
        return current
    }

}

fun main() {
    val bst = _8_LCAInBST()

    // Test Case 1: Identical trees
    val root1 = TreeNode(3)
    root1.left = TreeNode(5)
    root1.right = TreeNode(1)
    root1.right?.left = TreeNode(0)
    root1.right?.right = TreeNode(8)
    root1.left?.left = TreeNode(6)
    root1.left?.right = TreeNode(2)
    root1.left?.right?.left = TreeNode(7)
    root1.left?.right?.right = TreeNode(4)
    println("Result: ${bst.lowestCommonAncestor(root1, root1.left, root1.right)?.`val`}")
    println("Result: ${bst.lowestCommonAncestor1(root1, root1.left, root1.right)?.`val`}")
    println("Result: ${bst.lowestCommonAncestor2(root1, root1.left, root1.right)?.`val`}")
    println()

}
