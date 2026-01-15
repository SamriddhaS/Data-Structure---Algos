package binary_trees;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Link : https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/
 * Video Explanation :
 *
 * 1161. Maximum Level Sum of a Binary Tree
 * Solved
 * Medium
 *
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 *
 * Example 1:
 * Input: root = [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 *
 * Example 2:
 * Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * Output: 2
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [1, 104].
 *     -105 <= Node.val <= 105
 *
 */
public class _21_MaximumLevelSumBinaryTree {

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

    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int currentLevel=1;
        int maxSum=root.val;
        int result=1;
        while(!queue.isEmpty()){
            int n = queue.size();
            int currentLevelSum=0;
            while(n>0){
                TreeNode node = queue.poll();
                if (node!=null){
                    currentLevelSum+=node.val;
                    if (node.left!=null) queue.offer(node.left);
                    if (node.right!=null) queue.offer(node.right);
                }
                n--;
            }
            if (currentLevelSum>maxSum){
                maxSum = currentLevelSum;
                result = currentLevel;
            }
            currentLevel++;
        }
        return result;
    }


    public static void main(String[] args) {

        _21_MaximumLevelSumBinaryTree obj = new _21_MaximumLevelSumBinaryTree();

        // Test Case 1: Identical trees
        TreeNode root1 = obj.new TreeNode(3);
        root1.left = obj.new TreeNode(5);
        root1.right = obj.new TreeNode(1);
        root1.right.left = obj.new TreeNode(0);
        root1.right.right = obj.new TreeNode(8);
        root1.left.left = obj.new TreeNode(6);
        root1.left.right = obj.new TreeNode(2);
        root1.left.right.left = obj.new TreeNode(7);
        root1.left.right.right = obj.new TreeNode(4);
        System.out.println("Result: " + obj.maxLevelSum(root1));
        System.out.println();
    }
}