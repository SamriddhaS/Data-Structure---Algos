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

    /**
     * Solution 1 : Initial solution using BFS
     *
     * Time Complexity: O(n)
     *
     * Visits each node exactly once
     * Each node is enqueued and dequeued once
     * n = total number of nodes in the tree
     *
     * Space Complexity: O(w)
     *
     * w = maximum width of the tree (max nodes at any level)
     * Worst case: O(n) for a complete binary tree where the last level has ~n/2 nodes
     * Best case: O(1) for a skewed tree
     *
    * */
    public boolean isSymmetric(TreeNode root) {
        if (root==null) return true;
        if (root.left==null&&root.right==null) return true;
        if (root.left==null||root.right==null) return false;
        Queue<TreeNode> leftQueue = new LinkedList<>();
        leftQueue.offer(root.left);
        Queue<TreeNode> rightQueue = new LinkedList<>();
        rightQueue.offer(root.right);

        while (!leftQueue.isEmpty()&&!rightQueue.isEmpty()){
            int n = leftQueue.size();
            while (n>0){
                TreeNode node1 = leftQueue.poll();
                TreeNode node2 = rightQueue.poll();

                if (node1==null&&node2!=null) return false;
                if (node2==null&&node1!=null) return false;
                if (node1!=null && node2!=null && node1.val!=node2.val) return false;

                if (node1!=null){
                    leftQueue.offer(node1.left);
                    leftQueue.offer(node1.right);
                }
                if (node2!=null){
                    rightQueue.offer(node2.right);
                    rightQueue.offer(node2.left);
                }
                n--;
            }
        }

        return leftQueue.isEmpty() && rightQueue.isEmpty();
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