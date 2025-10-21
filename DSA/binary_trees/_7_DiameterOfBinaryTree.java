package binary_trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Link : https://leetcode.com/problems/diameter-of-binary-tree/
 * Video Explanation : https://www.youtube.com/watch?v=K81C31ytOZE
 *
 * 543. Diameter of Binary Tree
 * Easy
 * Topics
 *
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Example 2:
 * Input: root = [1,2]
 * Output: 1
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [1, 104].
 *     -100 <= Node.val <= 100
 *
 */
public class _7_DiameterOfBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    int maxDiameter=0;

    public int dfs(TreeNode node,int depth){
        if (node==null) return depth-1;
        int leftHeight = dfs(node.left,depth+1);
        int rightHeight = dfs(node.right,depth+1);
        maxDiameter = Math.max(maxDiameter,(leftHeight-depth)+(rightHeight-depth));
        return Math.max(leftHeight,rightHeight);
    }

    /**
     * Solution 1 : I am using the depth parameter to keep track of the depth of the node and then
     * using "leftHeight-depth"/"rightHeight-depth" to get the relative height of left/right subtree.
     *
     * Time Complexity: O(n)
     * Visits each node exactly once
     * Constant-time operations at each node
     *
     * Space Complexity: O(h)
     * Recursion stack depth equals tree height
     * Best case (balanced): O(log n)
     * Worst case (skewed): O(n)
    * */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root==null) return 0;
        dfs(root,0);
        return maxDiameter;
    }

    int max = 0;

    public int dfs1(TreeNode node){
        if (node==null) return 0;
        int leftHeight = dfs1(node.left);
        int rightHeight = dfs1(node.right);
        max = Math.max(max,leftHeight+rightHeight);
        return Math.max(leftHeight,rightHeight)+1;
    }

    /**
    * Solution 2 : Almost same solution compared to solution 1, only diff is we are
     * not using depth parameter and returning height of left/right subtree by keeping
     * base case if (node==null) return 0; and keep incrementing +1 for every iterative call : Math.max(leftHeight,rightHeight)+1
    * */
    public int diameterOfBinaryTree1(TreeNode root) {
        if (root==null) return 0;
        dfs1(root);
        return max;
    }

    public static void main(String[] args) {

        _7_DiameterOfBinaryTree obj = new _7_DiameterOfBinaryTree();

        // Test Case 1: Empty tree
        System.out.println("Test 1 - Empty tree:");
        TreeNode root1 = null;
        System.out.println("Result: " + obj.diameterOfBinaryTree(root1));
        System.out.println("Expected: 0");
        System.out.println();

        // Test Case 2: Single node
        System.out.println("Test 2 - Single node:");
        TreeNode root2 = obj.new TreeNode(1);
        System.out.println("Result: " + obj.diameterOfBinaryTree(root2));
        System.out.println("Expected: 1");
        System.out.println();

    }
}