package binary_trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/binary-tree-preorder-traversal/description/
 * Video Explanation : https://www.youtube.com/watch?v=afTpieEZXck
 *
 * 144. Binary Tree Preorder Traversal
 * Solved
 * Easy
 *
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 *
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [1,2,3]
 *
 * Example 2:
 * Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
 * Output: [1,2,4,5,6,7,3,8,9]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 *
 * Example 4:
 * Input: root = [1]
 * Output: [1]
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [0, 100].
 *     -100 <= Node.val <= 100
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 */
public class _1_BinaryTreeTraversalPreorder {

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


    private void traversePreorderRecursive(TreeNode node, int depth, List<Integer> anwerArray) {

        if (node != null) anwerArray.add(node.val);

        if (node != null && node.left != null) {
            traversePreorderRecursive(node.left, depth + 1, anwerArray);
        }

        if (node != null && node.right != null) {
            traversePreorderRecursive(node.right, depth + 1, anwerArray);
        }
    }

    /**
     * Solution 1 : Recursive Way
     * Time Complexity : O(n)
     * Space Complexity : O(n)
     * n = height of the tree.
    * */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        traversePreorderRecursive(root, 0, answer);
        return answer;
    }

    /**
     * Solution 2 : Iterative way
     *
     * Time Complexity: O(n)
     * - Each node is visited exactly once
     * - Each node is pushed to the stack at most once and popped at most once
     * - Total operations: O(n) where n = number of nodes
     *
     * Space Complexity: O(h)
     * - Stack stores only right children while traversing left
     * - In worst case (skewed tree): O(n)
     * - In best case (balanced tree): O(log n)
     * Where h = height of the tree
     *
     * Summary: Linear time, space depends on tree shape (height-based).
     *
    * */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;
        while(!stack.isEmpty()||currentNode!=null){
            result.add(currentNode.val);
            if (currentNode.right!=null) stack.push(currentNode.right);
            if (currentNode.left!=null) currentNode = currentNode.left; // if there are left nodes then move left
            else if (!stack.isEmpty()) currentNode = stack.pop(); // if no left node is there check the stack for right node and move right.
            else {
                currentNode = null;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        _1_BinaryTreeTraversalPreorder obj = new _1_BinaryTreeTraversalPreorder();

        // Test Case 1: Empty tree
        System.out.println("Test 1 - Empty tree:");
        TreeNode root1 = null;
        System.out.println("Result: " + obj.preorderTraversal(root1));
        System.out.println("Result: " + obj.preorderTraversal1(root1));
        System.out.println("Expected: []");
        System.out.println();

        // Test Case 2: Single node
        System.out.println("Test 2 - Single node:");
        TreeNode root2 = obj.new TreeNode(1);
        System.out.println("Result: " + obj.preorderTraversal(root2));
        System.out.println("Result: " + obj.preorderTraversal1(root2));
        System.out.println("Expected: [1]");
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
        System.out.println("Result: " + obj.preorderTraversal(root3));
        System.out.println("Result: " + obj.preorderTraversal1(root3));
        System.out.println("Expected: [1, 2, 3]");
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
        System.out.println("Result: " + obj.preorderTraversal(root4));
        System.out.println("Result: " + obj.preorderTraversal1(root4));
        System.out.println("Expected: [1, 2, 3]");
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
        System.out.println("Result: " + obj.preorderTraversal(root5));
        System.out.println("Result: " + obj.preorderTraversal1(root5));
        System.out.println("Expected: [1, 2, 4, 5, 3]");
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
        System.out.println("Result: " + obj.preorderTraversal(root6));
        System.out.println("Result: " + obj.preorderTraversal1(root6));
        System.out.println("Expected: [1, 2, 4, 5, 3, 6, 7]");
        System.out.println();

        // Test Case 7: Tree with only left children at root
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
        System.out.println("Result: " + obj.preorderTraversal(root7));
        System.out.println("Result: " + obj.preorderTraversal1(root7));
        System.out.println("Expected: [1, 2, 4, 3]");
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
        System.out.println("Result: " + obj.preorderTraversal(root8));
        System.out.println("Result: " + obj.preorderTraversal1(root8));
        System.out.println("Expected: [-1, -2, -4, 3, 5]");

    }
}