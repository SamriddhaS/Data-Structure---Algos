package binary_trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 * Video Explanation : https://www.youtube.com/watch?v=g_S5WuasWUE
 *
 * 94. Binary Tree Inorder Traversal
 * Easy
 * Topics
 *
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 *
 * Example 2:
 * Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
 * Output: [4,2,6,5,7,1,3,9,8]
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
public class _2_BinaryTreeTraversalInorder {

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


    private void traverseInorderRecursive(TreeNode node, int depth, List<Integer> anwerArray) {

        if (node != null && node.left != null) {
            traverseInorderRecursive(node.left, depth + 1, anwerArray);
        }

        if (node != null) anwerArray.add(node.val);

        if (node != null && node.right != null) {
            traverseInorderRecursive(node.right, depth + 1, anwerArray);
        }
    }

    /**
     * Solution 1 : Recursive Way
     *
     * Time Complexity: O(n) - Each node visited exactly once
     * Space Complexity: O(h) - Recursive call stack depth equals tree height
     * Worst case (skewed): O(n)
     * Best case (balanced): O(log n)
    * */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        traverseInorderRecursive(root, 0, answer);
        return answer;
    }

    /**
     * Solution 2 : Iterative way
     *
     * Time Complexity: O(n) - Each node visited exactly once
     * Space Complexity: O(h) - Stack stores nodes along path from root to current
     *
     * Worst case (skewed): O(n)
     * Best case (balanced): O(log n)
     *
    * */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;
        while(!stack.isEmpty()||currentNode!=null){
            if (currentNode.left!=null){
                stack.push(currentNode); // If there are more left node add the current to stack and go left.
                currentNode = currentNode.left;
            } else {
                result.add(currentNode.val); // left
                while (!stack.isEmpty()&&currentNode.right==null) {
                    currentNode = stack.pop();
                    result.add(currentNode.val); // root
                }
                if (currentNode.right!=null) currentNode = currentNode.right; // try to go to right node
                else currentNode = null;
            }

        }
        return result;
    }

    public static void main(String[] args) {

        _2_BinaryTreeTraversalInorder obj = new _2_BinaryTreeTraversalInorder();

        // Test Case 1: Empty tree
        System.out.println("Test 1 - Empty tree:");
        TreeNode root1 = null;
        System.out.println("Result: " + obj.inorderTraversal(root1));
        System.out.println("Result: " + obj.inorderTraversal1(root1));
        System.out.println("Expected: []");
        System.out.println();

        // Test Case 2: Single node
        System.out.println("Test 2 - Single node:");
        TreeNode root2 = obj.new TreeNode(1);
        System.out.println("Result: " + obj.inorderTraversal(root2));
        System.out.println("Result: " + obj.inorderTraversal1(root2));
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
        System.out.println("Result: " + obj.inorderTraversal(root3));
        System.out.println("Result: " + obj.inorderTraversal1(root3));
        System.out.println("Expected: [3, 2, 1]");
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
        System.out.println("Result: " + obj.inorderTraversal(root4));
        System.out.println("Result: " + obj.inorderTraversal1(root4));
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
        System.out.println("Result: " + obj.inorderTraversal(root5));
        System.out.println("Result: " + obj.inorderTraversal1(root5));
        System.out.println("Expected: [4, 2, 5, 1, 3]");
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
        System.out.println("Result: " + obj.inorderTraversal(root6));
        System.out.println("Result: " + obj.inorderTraversal1(root6));
        System.out.println("Expected: [4, 2, 5, 1, 6, 3, 7]");
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
        System.out.println("Result: " + obj.inorderTraversal(root7));
        System.out.println("Result: " + obj.inorderTraversal1(root7));
        System.out.println("Expected: [4, 2, 1, 3]");
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
        System.out.println("Result: " + obj.inorderTraversal(root8));
        System.out.println("Result: " + obj.inorderTraversal1(root8));
        System.out.println("Expected: [-4, -2, -1, 3, 5]");

    }
}