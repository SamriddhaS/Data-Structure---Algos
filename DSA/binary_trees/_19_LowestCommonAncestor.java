package binary_trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Problem Link : https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 * Video Explanation : https://www.youtube.com/watch?v=Oi3_06ultic&t=268s
 *
 * 236. Lowest Common Ancestor of a Binary Tree
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
 * two nodes p and q as the lowest node in T that has both p and q as descendants
 * (where we allow a node to be a descendant of itself).”
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 *
 * Example 3:
 *
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [2, 105].
 *     -109 <= Node.val <= 109
 *     All Node.val are unique.
 *     p != q
 *     p and q will exist in the tree.
 *
 */
public class _19_LowestCommonAncestor {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public boolean dfs(TreeNode node,TreeNode target,ArrayList<TreeNode> ancestor){
        if (node==null) return false;
        ancestor.add(node);
        if (node.val==target.val) return true;
        if (dfs(node.left,target,ancestor)) return true;
        if (dfs(node.right,target,ancestor)) return true;
        ancestor.remove(ancestor.size()-1);
        return false;
    }

    /**
     * Solution 1 :
     * Time Complexity: O(n²) :
     * Two DFS traversals: O(n) each
     * Nested loop comparison of ancestor paths: O(h²) where h is tree height
     * Worst case: When tree is skewed (h = n), the nested loops dominate: O(n²)
     * Best case (balanced tree): O(n)
     *
     * Space Complexity: O(n) :
     * Recursion stack: O(h)
     * Two ancestor lists: O(h) each
     * Worst case: O(n) when tree is skewed
    * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> ancestors1 = new ArrayList<>();
        dfs(root,p,ancestors1);
        ArrayList<TreeNode> ancestors2 = new ArrayList<>();
        dfs(root,q,ancestors2);
        for (int i = ancestors1.size()-1; i>=0 ;i--) {
            for (int j = ancestors2.size()-1; j>=0 ;j--){
                if (ancestors1.get(i).val == ancestors2.get(j).val) return ancestors1.get(i);
            }
        }
        return root;
    }


    HashSet<TreeNode> ancestor = new HashSet<>();
    TreeNode answer = null;

    public boolean dfs1(TreeNode node, TreeNode target){
        if (node==null) return false;
        ancestor.add(node);
        if (node.val==target.val) return true;
        if (dfs1(node.left,target)) return true;
        if (dfs1(node.right,target)) return true;
        ancestor.remove(node);
        return false;
    }

    public boolean dfsWithChecking(TreeNode node, TreeNode target, ArrayList<TreeNode> cAncestor){
        if (node==null) return false;
        cAncestor.add(node);
        if (node.val==target.val) {
            for (int j = cAncestor.size()-1; j>=0 ;j--){
                if (ancestor.contains(cAncestor.get(j))){
                    answer = cAncestor.get(j);
                    break;
                }
            }
            return true;
        }
        if (dfsWithChecking(node.left,target,cAncestor)) return true;
        if (dfsWithChecking(node.right,target,cAncestor)) return true;
        cAncestor.remove(node);
        return false;
    }

    /**
     * Time Complexity: O(n)
     *
     * dfs1: O(n) - traverses tree to find p
     * dfsWithChecking: O(n) - traverses tree to find q
     * HashSet lookup when q found: O(h) with O(1) per contains check
     * Total: O(n)
     *
     * Space Complexity: O(n)
     *
     * HashSet ancestor: O(h)
     * ArrayList cAncestor: O(h)
     * Recursion stack: O(h) for each DFS
     * Worst case (skewed tree): O(n)
    * */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> ancestors1 = new ArrayList<>();
        dfs1(root,p);
        dfsWithChecking(root,q,ancestors1);
        return answer;
    }

    public TreeNode solve(TreeNode node, TreeNode p, TreeNode q){
        if(node==null) return null;
        if(node==p||node==q) return node;
        TreeNode firstNode = solve(node.left,p,q);
        TreeNode secondNode = solve(node.right,p,q);
        if(firstNode!=null&&secondNode!=null) return node;
        if(firstNode==null) return secondNode;
        else return firstNode;
    }

    /**
     * Solution 3 : Optimal
     *
     * Time Complexity: O(n)
     * Single DFS traversal visiting each node at most once
     * Each node does O(1) work (comparisons and returns)
     * Total: O(n) where n is the number of nodes
     *
     * Space Complexity: O(h)
     * Only recursion call stack is used
     * Maximum depth of recursion is the tree height h
     * Balanced tree: O(log n)
     * Skewed tree (worst case): O(n)
    * */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        return solve(root,p,q);
    }

    public static void main(String[] args) {

        _19_LowestCommonAncestor obj = new _19_LowestCommonAncestor();

        // Test Case 1: Identical trees
        TreeNode root1 = obj.new TreeNode(3);
        root1.left = obj.new TreeNode(5);
        root1.right = obj.new TreeNode(1);
        root1.right.left = obj.new TreeNode(0);
        root1.right.right = obj.new TreeNode(8);
        root1.left.left = obj.new TreeNode(6);
        root1.left.right = obj.new TreeNode(2);
        root1.left.right.left = obj.new TreeNode(7);
        root1.left.right.right = obj.new TreeNode(4);
        System.out.println("Result: " + obj.lowestCommonAncestor1(root1,root1.left,root1.right).val);
        System.out.println();
    }
}