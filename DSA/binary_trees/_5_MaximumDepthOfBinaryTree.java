package binary_trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Link : https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 * Video Explanation :
 *
 *
 * 104. Maximum Depth of Binary Tree
 * Solved
 * Easy
 * Topics
 *
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path from
 * the root node down to the farthest leaf node.
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 *
 * Example 2:
 * Input: root = [1,null,2]
 * Output: 2
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [0, 104].
 *     -100 <= Node.val <= 100
 */
public class _5_MaximumDepthOfBinaryTree {

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

    int maxdepth=0;
    public void recursiveInOrder(TreeNode node,int depth){
        if(node==null) return;
        maxdepth = Math.max(maxdepth,depth);
        if(node.left!=null) recursiveInOrder(node.left,depth+1);
        if(node.right!=null) recursiveInOrder(node.right,depth+1);
    }

    /**
    *
     * Time Complexity: O(n)
     * Visits each node exactly once, where n is the number of nodes in the tree
     *
     * Space Complexity: O(h)
     * Recursion stack depth equals tree height h
     * Best case: O(log n) for balanced tree
     * Worst case: O(n) for skewed tree
    * */
    public int maxDepth(TreeNode root) {
        recursiveInOrder(root,1);
        return maxdepth;
    }

    /**
    *
     * Time Complexity: O(n)
     *
     * Visits each node exactly once, where n is the number of nodes in the tree
     *
     * Space Complexity: O(w)
     * - Queue stores nodes level by level, where w is maximum width
     * - Best case: O(1) for skewed tree
     * - Worst case: O(n/2) ≈ O(n) for complete binary tree (last level has ~n/2 nodes)
    * */
    public int maxDepth1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(root!=null) queue.offer(root);
        int depth=0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size>0){
                TreeNode node = queue.poll();
                if (node.left!=null) queue.offer(node.left);
                if (node.right!=null) queue.offer(node.right);
                size--;
            }
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {

        _5_MaximumDepthOfBinaryTree obj = new _5_MaximumDepthOfBinaryTree();

        // Test Case 1: Empty tree
        System.out.println("Test 1 - Empty tree:");
        TreeNode root1 = null;
        System.out.println("Result: " + obj.maxDepth(root1));
        System.out.println("Result: " + obj.maxDepth1(root1));
        System.out.println("Expected: 0");
        System.out.println();

        // Test Case 2: Single node
        System.out.println("Test 2 - Single node:");
        TreeNode root2 = obj.new TreeNode(1);
        System.out.println("Result: " + obj.maxDepth(root2));
        System.out.println("Result: " + obj.maxDepth1(root2));
        System.out.println("Expected: 1");
        System.out.println();

        // Test Case 3: Left skewed tree
        //     1
        //    /
        //   2
        //  /
        // 3
        System.out.println("Test 3 - Left skewed tree:");
        TreeNode root3 = obj.new TreeNode(1);
        root3.left = obj.new TreeNode(2);
        root3.left.left = obj.new TreeNode(3);
        System.out.println("Result: " + obj.maxDepth(root3));
        System.out.println("Result: " + obj.maxDepth1(root3));
        System.out.println("Expected: 3");
        System.out.println();

        // Test Case 4: Right skewed tree
        // 1
        //  \
        //   2
        //    \
        //     3
        System.out.println("Test 4 - Right skewed tree:");
        TreeNode root4 = obj.new TreeNode(1);
        root4.right = obj.new TreeNode(2);
        root4.right.right = obj.new TreeNode(3);
        System.out.println("Result: " + obj.maxDepth(root4));
        System.out.println("Result: " + obj.maxDepth1(root4));
        System.out.println("Expected: 3");
        System.out.println();

        // Test Case 5: Balanced tree
        //     1
        //    / \
        //   2   3
        //  / \
        // 4   5
        System.out.println("Test 5 - Balanced tree:");
        TreeNode root5 = obj.new TreeNode(1);
        root5.left = obj.new TreeNode(2);
        root5.right = obj.new TreeNode(3);
        root5.left.left = obj.new TreeNode(4);
        root5.left.right = obj.new TreeNode(5);
        System.out.println("Result: " + obj.maxDepth(root5));
        System.out.println("Result: " + obj.maxDepth1(root5));
        System.out.println("Expected: 3");
        System.out.println();

        // Test Case 6: Complete binary tree
        //       1
        //      / \
        //     2   3
        //    / \ / \
        //   4  5 6  7
        System.out.println("Test 6 - Complete binary tree:");
        TreeNode root6 = obj.new TreeNode(1);
        root6.left = obj.new TreeNode(2);
        root6.right = obj.new TreeNode(3);
        root6.left.left = obj.new TreeNode(4);
        root6.left.right = obj.new TreeNode(5);
        root6.right.left = obj.new TreeNode(6);
        root6.right.right = obj.new TreeNode(7);
        System.out.println("Result: " + obj.maxDepth(root6));
        System.out.println("Result: " + obj.maxDepth1(root6));
        System.out.println("Expected: 3");
        System.out.println();

        // Test Case 7: Mixed tree
        //     1
        //    / \
        //   2   3
        //  /
        // 4
        System.out.println("Test 7 - Mixed tree:");
        TreeNode root7 = obj.new TreeNode(1);
        root7.left = obj.new TreeNode(2);
        root7.right = obj.new TreeNode(3);
        root7.left.left = obj.new TreeNode(4);
        System.out.println("Result: " + obj.maxDepth(root7));
        System.out.println("Result: " + obj.maxDepth1(root7));
        System.out.println("Expected: 3");
        System.out.println();

        // Test Case 8: Tree with negative values
        //      -1
        //      / \
        //    -2   3
        //    /     \
        //  -4       5
        System.out.println("Test 8 - Tree with negative values:");
        TreeNode root8 = obj.new TreeNode(-1);
        root8.left = obj.new TreeNode(-2);
        root8.right = obj.new TreeNode(3);
        root8.left.left = obj.new TreeNode(-4);
        root8.right.right = obj.new TreeNode(5);
        System.out.println("Result: " + obj.maxDepth(root8));
        System.out.println("Result: " + obj.maxDepth1(root8));
        System.out.println("Expected: 3");
        System.out.println();

        // Test Case 9: Deeper left subtree
        //       1
        //      / \
        //     2   3
        //    /
        //   4
        //  /
        // 5
        System.out.println("Test 9 - Deeper left subtree:");
        TreeNode root9 = obj.new TreeNode(1);
        root9.left = obj.new TreeNode(2);
        root9.right = obj.new TreeNode(3);
        root9.left.left = obj.new TreeNode(4);
        root9.left.left.left = obj.new TreeNode(5);
        System.out.println("Result: " + obj.maxDepth(root9));
        System.out.println("Result: " + obj.maxDepth1(root9));
        System.out.println("Expected: 4");
        System.out.println();

        // Test Case 10: Deeper right subtree
        //   1
        //  / \
        // 2   3
        //      \
        //       4
        //        \
        //         5
        System.out.println("Test 10 - Deeper right subtree:");
        TreeNode root10 = obj.new TreeNode(1);
        root10.left = obj.new TreeNode(2);
        root10.right = obj.new TreeNode(3);
        root10.right.right = obj.new TreeNode(4);
        root10.right.right.right = obj.new TreeNode(5);
        System.out.println("Result: " + obj.maxDepth(root10));
        System.out.println("Result: " + obj.maxDepth1(root10));
        System.out.println("Expected: 4");
        System.out.println();

    }
}