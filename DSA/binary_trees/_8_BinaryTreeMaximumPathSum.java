package binary_trees;

/**
 * Problem Link : https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 * Video Explanation : https://www.youtube.com/watch?v=Op6YFcs8R9M
 *
 * 124. Binary Tree Maximum Path Sum
 * Solved
 * Hard
 *
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence
 * has an edge connecting them. A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 * The path sum of a path is the sum of the node's values in the path.
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 * Example 1:
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 *
 * Example 2:
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 * Constraints:
 *
 *     The number of nodes in the tree is in the range [1, 3 * 104].
 *     -1000 <= Node.val <= 1000
 */
public class _8_BinaryTreeMaximumPathSum {

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

    int maxSum = Integer.MIN_VALUE;

    public int dfs(TreeNode node,int depth){
        if (node==null) return 0;
        int leftSum = dfs(node.left,depth+1);
        int rightSum = dfs(node.right,depth+1);

        // Let's check which subtree has the larger sum from this current node.
        // So we can decide if we can return that subtree sum to upper level.
        int subTreeWithLargerSum=Math.max(leftSum,rightSum)+ node.val;

        // 1. sum = node + left subtree + right subtree, maybe including both
        // the left and right side gives me the largest sum
        maxSum = Math.max(maxSum,leftSum+rightSum+node.val);

        // 2. maybe excluding one of the subtree(left/right whichever is lesser) gives me
        // the largest sum.
        maxSum = Math.max(maxSum,subTreeWithLargerSum);

        // 3. maybe the current node is creating the largest sum so also check
        // if excluding both the left and right side gives us the larges sum.
        maxSum = Math.max(maxSum,node.val);

        // Maybe excluding the subtree can give us largest sum, so we will also check and return whoever is
        // larger among them.
        return Math.max(subTreeWithLargerSum,node.val);
    }

    public int maxPathSum(TreeNode root) {
        if (root==null) return 0;
        dfs(root,0);
        return maxSum;
    }

    public static void main(String[] args) {

        _8_BinaryTreeMaximumPathSum obj = new _8_BinaryTreeMaximumPathSum();

        // Test Case 1: Empty tree
        System.out.println("Test 1 - Empty tree:");
        TreeNode root1 = null;
        System.out.println("Result: " + obj.maxPathSum(root1));
        System.out.println();

        // Test Case 2: Single node
        System.out.println("Test 2 - Single node:");
        TreeNode root2 = obj.new TreeNode(1);
        root2.left = obj.new TreeNode(5);
        root2.right = obj.new TreeNode(3);
        System.out.println("Result: " + obj.maxPathSum(root2));
        System.out.println();

    }
}