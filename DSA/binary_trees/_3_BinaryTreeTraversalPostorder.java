package binary_trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/binary-tree-postorder-traversal/
 * Video Explanation : https://www.youtube.com/watch?v=QhszUQhGGlA
 *
 * 145. Binary Tree Postorder Traversal
 * Easy
 * Topics
 *
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 *
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 *
 * Example 2:
 * Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
 * Output: [4,6,7,5,2,9,8,3,1]
 *
 * Explanation:
 * Example 3:
 * Input: root = []
 * Output: []
 *
 * Example 4:
 * Input: root = [1]
 * Output: [1]
 *
 * Constraints:
 *     The number of the nodes in the tree is in the range [0, 100].
 *     -100 <= Node.val <= 100
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 */
public class _3_BinaryTreeTraversalPostorder {

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

    private void traversePostorderRecursive(TreeNode node, int depth, List<Integer> anwerArray) {

        if (node != null && node.left != null) {
            traversePostorderRecursive(node.left, depth + 1, anwerArray);
        }

        if (node != null && node.right != null) {
            traversePostorderRecursive(node.right, depth + 1, anwerArray);
        }

        if (node != null) anwerArray.add(node.val);
    }

    /**
     * Left - Right - Root
     *
     * Solution 1 : Recursive Way
     *
     * Time Complexity: O(n)
     * Each node is visited exactly once
     *
     * Space Complexity: O(h)
     * Recursive call stack depth equals tree height
     * Worst case (skewed tree): O(n)
     * Best case (balanced tree): O(log n)
    * */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        traversePostorderRecursive(root, 0, answer);
        return answer;
    }

    /**
     * Solution 2 : Iterative way with stack and hashmap.
     *
     * Time Complexity: O(n)
     * Each node is visited and processed once
     * HashMap operations are O(1) average
     *
     * Space Complexity: O(n)
     * Stack: O(h) in the worst case
     * HashMap: O(n) - stores visited status for all nodes
     * Overall: O(n) due to HashMap
     *
     * */

    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode,Boolean> map = new HashMap<>();
        TreeNode currentNode = root;
        while(!stack.isEmpty()||currentNode!=null){
            if (currentNode.left!=null){
                // If left node is available,move left.
                stack.push(currentNode);
                map.put(currentNode,true); // mark this node as visited
                if (currentNode.right!=null) {
                    // If this node has right node available push it to stack
                    // so we can traverse later.
                    stack.push(currentNode.right);
                    map.put(currentNode.right,false); // mark the right node as not visited
                }
                currentNode = currentNode.left;
            } else {
                // If left node not present check if right node is present.
                if (currentNode.right!=null) {
                    // right node is present so push the current node to stack and mark visited
                    stack.push(currentNode);
                    map.put(currentNode,true);
                    currentNode = currentNode.right; // move right
                } else {

                    result.add(currentNode.val);// this is a leaf node so add it to result.

                    while (!stack.isEmpty()&&map.getOrDefault(stack.peek(),false)){
                        // pop the stack to backtrack and add the visited nodes to result.
                        TreeNode node = stack.pop();
                        result.add(node.val);
                    }

                    // pop the nodes that needs to be traversed - those nodes which we had marked as not visited previously.
                    if (!stack.isEmpty()) currentNode = stack.pop();
                    else currentNode = null; // if stack is empty no more nodes are left to be traversed.
                }
            }

        }
        return result;
    }

    /**
     * Solution 3 : Iterative way with two stacks
     *
     * Time Complexity: O(n)
     * Each node is pushed and popped exactly twice (once as unvisited, once as visited)
     *
     * Space Complexity: O(h)
     * Two stacks grow proportionally with tree height
     * Worst case (skewed tree): O(n)
     * Best case (balanced tree): O(log n)
     *
     * */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        Stack<Boolean> visitedStack = new Stack<>();
        visitedStack.push(false);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            boolean isVisited = visitedStack.pop();
            if (node!=null){
                if (isVisited) result.add(node.val);
                else {
                    stack.push(node);
                    visitedStack.push(true);
                    stack.push(node.right);
                    visitedStack.push(false);
                    stack.push(node.left);
                    visitedStack.push(false);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

        _3_BinaryTreeTraversalPostorder obj = new _3_BinaryTreeTraversalPostorder();

        // Test Case 1: Empty tree
        System.out.println("Test 1 - Empty tree:");
        TreeNode root1 = null;
        System.out.println("Result: " + obj.inorderTraversal(root1));
        System.out.println("Result: " + obj.postorderTraversal1(root1));
        System.out.println("Result: " + obj.postorderTraversal2(root1));
        System.out.println("Expected: []");
        System.out.println();

        // Test Case 2: Single node
        System.out.println("Test 2 - Single node:");
        TreeNode root2 = obj.new TreeNode(1);
        System.out.println("Result: " + obj.inorderTraversal(root2));
        System.out.println("Result: " + obj.postorderTraversal1(root2));
        System.out.println("Result: " + obj.postorderTraversal2(root2));
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
        System.out.println("Result: " + obj.postorderTraversal1(root3));
        System.out.println("Result: " + obj.postorderTraversal2(root3));
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
        System.out.println("Result: " + obj.postorderTraversal1(root4));
        System.out.println("Result: " + obj.postorderTraversal2(root4));
        System.out.println("Expected: [3, 2, 1]");
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
        System.out.println("Result: " + obj.postorderTraversal1(root5));
        System.out.println("Result: " + obj.postorderTraversal2(root5));
        System.out.println("Expected: [4, 5, 2, 3, 1]");
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
        System.out.println("Result: " + obj.postorderTraversal1(root6));
        System.out.println("Result: " + obj.postorderTraversal2(root6));
        System.out.println("Expected: [4, 5, 2, 6, 7, 3, 1]");
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
        System.out.println("Result: " + obj.postorderTraversal1(root7));
        System.out.println("Result: " + obj.postorderTraversal2(root7));
        System.out.println("Expected: [4, 2, 3, 1]");
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
        System.out.println("Result: " + obj.postorderTraversal1(root8));
        System.out.println("Result: " + obj.postorderTraversal2(root8));
        System.out.println("Expected: [-4, -2, 5, 3, -1]");

    }
}