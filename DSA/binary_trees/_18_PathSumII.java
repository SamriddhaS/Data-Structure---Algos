package binary_trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Problem Link : https://leetcode.com/problems/path-sum-ii/description/
 * Video Explanation :
 *
 * 113. Path Sum II
 * Solved
 *
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths
 * where the sum of the node values in the path equals targetSum. Each path should be
 * returned as a list of the node values, not node references.
 *
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 *
 * Example 1:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 *
 * Example 2:
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 *
 *  Example 3:
 * Input: root = [1,2], targetSum = 0
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 */
public class _18_PathSumII {

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

    public void dfs(TreeNode node,int targetSum,List<List<Integer>> answers,List<Integer> currentPath,int currentSum){
        // base case
        if(node==null) return;

        currentSum+=node.val;
        currentPath.add(node.val);

        if(currentSum==targetSum&&node.left==null&&node.right==null){
            answers.add(new ArrayList<>(currentPath));
        }

        dfs(node.left,targetSum,answers,currentPath,currentSum);
        dfs(node.right,targetSum,answers,currentPath,currentSum);

        currentPath.remove(currentPath.size()-1);
        currentSum-=node.val;
    }

    /**
     * Time Complexity: O(N)
     * Visit each node exactly once
     * N = number of nodes in the tree
     *
     * Space Complexity: O(H)
     * Recursion stack depth: O(H) where H = height of tree
     * currentPath list: O(H) in the worst case
     * H ranges from log(N) (balanced) to N (skewed)
    * */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> answer = new ArrayList<>();
        dfs(root,targetSum,answer,new ArrayList<>(),0);
        return answer;
    }


    public static void main(String[] args) {

        _18_PathSumII obj = new _18_PathSumII();

        // Test Case 1: Identical trees
        TreeNode root1 = obj.new TreeNode(1);
        root1.left = obj.new TreeNode(2);
        root1.right = obj.new TreeNode(3);
        root1.right.left = obj.new TreeNode(5);
        root1.right.right = obj.new TreeNode(6);
        root1.right.left.left = obj.new TreeNode(7);
        root1.right.left.right = obj.new TreeNode(8);
        System.out.println("Result: " + obj.pathSum(root1,10));
        System.out.println();
    }
}