package binary_trees;

import java.util.Queue;

import java.util.*;

/**
 * Problem Link : https://leetcode.com/problems/binary-tree-level-order-traversal/
 * Video Explanation : https://www.youtube.com/watch?v=6ZnyEApgFYg
 *
 * 102. Binary Tree Level Order Traversal
 * Medium
 * Topics
 *
 * Given the root of a binary tree, return the level order traversal of its
 * nodes' values. (i.e., from left to right, level by level).
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 *
 * Example 2:
 * Input: root = [1]
 * Output: [[1]]
 *
 * Example 3:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [0, 2000].
 *     -1000 <= Node.val <= 1000
 *
 */
public class _4_BinaryTreeLevelOrderTraversal {

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

    private void traverseLevelOrderRecursive(TreeNode node, int depth, List<List<Integer>> answerArray) {

        if (node == null) return;

        if (depth==answerArray.size()) {
            answerArray.add(new ArrayList<>());
        }

        answerArray.get(depth).add(node.val);

        if (node.left != null) {
            traverseLevelOrderRecursive(node.left, depth + 1, answerArray);
        }

        if (node.right != null) {
            traverseLevelOrderRecursive(node.right, depth + 1, answerArray);
        }

    }

    /**
     * Solution 1 : Using DFS
     * Current Solution (DFS):
     * - Uses recursion call stack
     * - Goes deep first (root → left child → left's left → ... then backtracks)
     * - Groups results by depth, but traversal order is different
     *
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - O(1) operations per node (list add, size check)
     * - Total: O(n) where n = number of nodes
     *
     * Space Complexity: O(h)
     * - Recursion call stack: O(h) where h = height of tree
     * - Output list doesn't count toward space complexity
     * - Best case: O(log n) for balanced tree
     * - Worst case: O(n) for skewed tree
     *
    * */
    public List<List<Integer>> levelOrderTraversal(TreeNode root) {
        List<List<Integer>> answerMap = new ArrayList<>();
        traverseLevelOrderRecursive(root, 0, answerMap);
        return answerMap;
    }

    /**
     * Solution 2 : BFS Algo - Level order traversal
     *
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - O(1) operations per node (poll, offer, add)
     * - Total: O(n) where n = number of nodes
     *
     * Space Complexity: O(w)
     * - Queue stores nodes level by level
     * - Maximum nodes in queue = maximum width of tree
     * - O(w) where w = max width
     * - Worst case: O(n) for complete binary tree (bottom level has ~n/2 nodes)
     * - Best case: O(1) for skewed tree
     * Note: Output list is O(n) but typically not counted in space complexity analysis.
     *
    * */
    public List<List<Integer>> levelOrderTraversal1(TreeNode root) {
        List<List<Integer>> answers = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            ArrayList<Integer> arrayList = new ArrayList<>();
            int size = queue.size();
            while(!queue.isEmpty()&&size>0){ // only traverse till same level.
                TreeNode current = queue.poll();
                if (current!=null){
                    arrayList.add(current.val);
                    if (current.left!=null) queue.offer(current.left);
                    if (current.right!=null) queue.offer(current.right);
                }
                size--;
            }
            if (!arrayList.isEmpty()) answers.add(arrayList);
        }

        return answers;
    }

    public static void main(String[] args) {

        _4_BinaryTreeLevelOrderTraversal obj = new _4_BinaryTreeLevelOrderTraversal();

        // Test Case 1: Empty tree
        System.out.println("Test 1 - Empty tree:");
        TreeNode root1 = null;
        System.out.println("Result: " + obj.levelOrderTraversal(root1));
        System.out.println("Result: " + obj.levelOrderTraversal1(root1));
        System.out.println("Expected: []");
        System.out.println();

        // Test Case 2: Single node
        System.out.println("Test 2 - Single node:");
        TreeNode root2 = obj.new TreeNode(1);
        System.out.println("Result: " + obj.levelOrderTraversal(root2));
        System.out.println("Result: " + obj.levelOrderTraversal1(root2));
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
        System.out.println("Result: " + obj.levelOrderTraversal(root3));
        System.out.println("Result: " + obj.levelOrderTraversal1(root3));
        System.out.println("Expected: [[1], [2], [3]]");
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
        System.out.println("Result: " + obj.levelOrderTraversal(root4));
        System.out.println("Result: " + obj.levelOrderTraversal1(root4));
        System.out.println("Expected: [[1], [2], [3]]");
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
        System.out.println("Result: " + obj.levelOrderTraversal(root5));
        System.out.println("Result: " + obj.levelOrderTraversal1(root5));
        System.out.println("Expected: [[1], [2, 3], [4, 5]]");
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
        System.out.println("Result: " + obj.levelOrderTraversal(root6));
        System.out.println("Result: " + obj.levelOrderTraversal1(root6));
        System.out.println("Expected: [[1], [2, 3], [4, 5, 6, 7]]");
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
        System.out.println("Result: " + obj.levelOrderTraversal(root7));
        System.out.println("Result: " + obj.levelOrderTraversal1(root7));
        System.out.println("Expected: [[1], [2, 3], [4]]");
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
        System.out.println("Result: " + obj.levelOrderTraversal(root8));
        System.out.println("Result: " + obj.levelOrderTraversal1(root8));
        System.out.println("Expected: [[-1], [-2, 3], [-4, 5]]");

    }
}