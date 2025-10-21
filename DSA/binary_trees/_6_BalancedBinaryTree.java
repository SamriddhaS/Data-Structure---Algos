package binary_trees;

/**
 * Problem Link : https://leetcode.com/problems/balanced-binary-tree/description/
 * Video Explanation : https://www.youtube.com/watch?v=QfJsau0ItOY
 *
 * 110. Balanced Binary Tree
 * Attempted
 * Easy
 *
 * Given a binary tree, determine if it is.
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 *
 * Example 2:
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 *
 * Example 3:
 * Input: root = []
 * Output: true
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [0, 5000].
 *     -104 <= Node.val <= 104
 */
public class _6_BalancedBinaryTree {

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


    public int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right)); // try to go left most/right most subtree to get max height
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int leftHeight = height(root.left); // 1. Get the left subtree height
        int rightHeight = height(root.right); // 2. Get the right subtree height

        if (Math.abs(leftHeight - rightHeight) > 1) return false; //3. check if is balanced, return false if not.

        return isBalanced(root.left) && isBalanced(root.right); // Repeat 1,2,3 for all the nodes/subtrees.
    }


    boolean isBalanced=true;

    public int dfs(TreeNode root,int depth){
        if(root==null) return depth;
        int leftHeight = dfs(root.left,depth+1);
        int rightHeight = dfs(root.right,depth+1);
        if (isBalanced) isBalanced =  Math.max(leftHeight,rightHeight)-Math.min(leftHeight,rightHeight)<=1;
        return Math.max(leftHeight,rightHeight);
    }

    /**
     * Solution :
     * Time Complexity: O(n)
     * Visits each node exactly once in the tree
     * Performs constant-time operations at each node
     *
     * Space Complexity: O(h)
     * Recursion stack depth equals tree height
     * Best case (balanced): O(log n)
     * Worst case (skewed): O(n)
    * */
    public boolean isBalanced1(TreeNode root) {
        if(root==null) return true;
        dfs(root,0);
        return isBalanced;
    }


    public static void main(String[] args) {

        _6_BalancedBinaryTree obj = new _6_BalancedBinaryTree();

        // Test Case 10: Deeper right subtree
        //       1
        //      / \
        //     2   5
        //    /     \
        //   3       6
        //  /         \
        // 4           7
        TreeNode root10 = obj.new TreeNode(1);
        root10.left = obj.new TreeNode(2);
        root10.left.left = obj.new TreeNode(3);
        root10.left.left.left = obj.new TreeNode(4);
        root10.right = obj.new TreeNode(5);
        root10.right.right = obj.new TreeNode(6);
        root10.right.right.right = obj.new TreeNode(7);
        System.out.println("Result: " + obj.isBalanced1(root10));
        System.out.println("Result: " + obj.isBalanced(root10));
        System.out.println();

    }
}