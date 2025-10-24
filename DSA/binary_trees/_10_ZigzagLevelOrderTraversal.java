package binary_trees;

import java.util.*;

/**
 * Problem Link : https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
 * Video Explanation : https://www.youtube.com/watch?v=igbboQbiwqw
 *
 * 103. Binary Tree Zigzag Level Order Traversal
 * Solved
 * Medium
 *
 * Given the root of a binary tree, return the zigzag level order traversal of
 * its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
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
 *     -100 <= Node.val <= 100
 *
 */
public class _10_ZigzagLevelOrderTraversal {

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

    /**
     *
     * Time Complexity: O(n)
     * - Each node in the tree is visited exactly once and added to the queue once
     * - The Collections.reverse() operation is O(k) where k is the number of nodes at that level
     * - Across all levels, we reverse at most n/2 nodes (alternate levels), which is still O(n)
     * Overall: O(n) for traversal + O(n) for all reversals = O(n)
     *
     * Space Complexity: O(n)
     * - Queue storage: In the worst case (complete binary tree), the maximum width occurs at the last level,
     * which can contain up to ⌈n/2⌉ nodes = O(n)
     * - Answer list: Stores all n nodes = O(n)
     * - Temporary lists: The tempAns list stores one level at a time, which at most is O(n) for a very wide tree
     * Overall: O(n)
    * */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root==null) return new ArrayList<>();
        List<List<Integer>> answer = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isZigZag = false;
        while(!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> tempAns = new ArrayList<>();
            while(size>0){
                TreeNode node = queue.poll();
                if (node!=null){
                    tempAns.add(node.val);
                    if (node.left!=null) queue.offer(node.left);
                    if (node.right!=null) queue.offer(node.right);
                }
                size--;
            }
            if (!tempAns.isEmpty()){
                if (isZigZag) Collections.reverse(tempAns);
                answer.add(tempAns);
            }
            isZigZag = !isZigZag;
        }
        return answer;
    }


    public static void main(String[] args) {

        _10_ZigzagLevelOrderTraversal obj = new _10_ZigzagLevelOrderTraversal();

        // Test Case 1: Identical trees
        TreeNode root1 = obj.new TreeNode(3);
        root1.left = obj.new TreeNode(9);
        root1.right = obj.new TreeNode(20);
        root1.right.left = obj.new TreeNode(15);
        root1.right.right = obj.new TreeNode(7);
        System.out.println("Result: " + obj.zigzagLevelOrder(root1));
        System.out.println();
    }
}