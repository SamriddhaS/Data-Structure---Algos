package binary_trees;

import java.util.*;

/**
 * Problem Link : https://leetcode.com/problems/symmetric-tree/description/
 * Video Explanation :
 *
 * 101. Symmetric Tree
 * Easy
 * Topics
 *
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 * Example 1:
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 *
 * Example 2:
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 *
 * Follow up: Could you solve it both recursively and iteratively?
 *
 */
public class _15_SymmetricBinaryTree {

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

    public boolean dfs(TreeNode node){
        if (node==null) return true;
        if (!dfs(node.left)) return false;
        if (!dfs(node.right)) return false;
        if (node.left==null&&node.right==null) return true;
        if (node.left==null||node.right==null) return false;
        else return node.left.val==node.right.val;
    }

    public boolean isSymmetric(TreeNode root) {
        return dfs(root);
    }


    public static void main(String[] args) {

        _15_SymmetricBinaryTree obj = new _15_SymmetricBinaryTree();

        // Test Case 1: Identical trees
        TreeNode root1 = obj.new TreeNode(1);
        root1.left = obj.new TreeNode(2);
        root1.right = obj.new TreeNode(3);
        root1.right.left = obj.new TreeNode(5);
        root1.right.right = obj.new TreeNode(6);
        root1.right.left.left = obj.new TreeNode(7);
        root1.right.left.right = obj.new TreeNode(8);
        System.out.println("Result: " + obj.isSymmetric(root1));
        System.out.println();

        TreeNode root2 = obj.new TreeNode(1);
        root2.left = obj.new TreeNode(2);
        root2.right = obj.new TreeNode(2);
        System.out.println("Result: " + obj.isSymmetric(root2));
        System.out.println();
    }
}