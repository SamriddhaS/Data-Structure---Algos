package binary_trees;

import java.util.*;

/**
 * Problem Link : https://leetcode.com/problems/path-sum-iii/
 * Video Explanation :
 *
 * 437. Path Sum III
 * Solved
 *
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of
 * the values along the path equals targetSum.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 *
 * Example 1:
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 *
 *  Example 2:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 1000].
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 *
 */
public class _17_PathSumIII {

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

    int noOfPaths=0;

    public void dfs(TreeNode node, int targetSum, List<Integer> currentPath, long currentSum){

        if(node==null) return;

        //add new node
        currentPath.add(node.val);
        currentSum += node.val;

        // basecase
        if(targetSum==currentSum){
            noOfPaths++;
        }

        // edgecase subtrees
        if(currentPath.size()>1){
            long sum=0;
            for(int i=0;i<currentPath.size()-1;i++){
                sum+=currentPath.get(i);
                if((currentSum-sum)==targetSum){
                    noOfPaths++;
                }
            }
        }

        //explore left
        dfs(node.left,targetSum,currentPath,currentSum);

        //explore right
        dfs(node.right,targetSum,currentPath,currentSum);

        // backtrack
        currentPath.remove(currentPath.size()-1);
        currentSum -= node.val;

    }

    /**
    * Solution 1 :
     *
     * Time Complexity: O(n²)
     * Visit each node once: O(n)
     * At each node, iterate through the current path to check all possible subpaths: O(n) in worst case (skewed tree)
     * Total: O(n) × O(n) = O(n²)
     *
     * Space Complexity: O(n)
     * Recursion stack: O(h) where h is height (O(n) worst case for skewed tree)
     * currentPath list: O(h) = O(n) worst case
     * Total: O(n)
    * */
    public int pathSum(TreeNode root, int targetSum) {
        dfs(root,targetSum,new ArrayList<>(),0);
        return noOfPaths;
    }

    int noOfPaths1=0;

    public void dfs1(TreeNode node, int targetSum, HashMap<Long,Integer> currentPath, long currentSum){

        if(node==null) return;

        //add new node
        currentSum += node.val;

        long required = currentSum - targetSum;
        if(currentPath.containsKey(required)){
            noOfPaths1+=currentPath.get(required);
        }

        int count = currentPath.getOrDefault(currentSum,0);
        currentPath.put(currentSum,count+1);

        //explore left
        dfs1(node.left,targetSum,currentPath,currentSum);

        //explore right
        dfs1(node.right,targetSum,currentPath,currentSum);

        // backtrack
        currentPath.put(currentSum,count);
        currentSum -= node.val;
    }

    /**
     * Solution 2 :
     *
     * Time Complexity: O(n)
     * Visits each node exactly once in the DFS traversal
     * HashMap operations (get, put, containsKey) are O(1) average case
     *
     * Space Complexity: O(h)
     * Recursion stack: O(h) where h is tree height
     * HashMap: O(h) - stores at most one entry per level in the current path
     * Best case: O(log n) for balanced tree
     * Worst case: O(n) for skewed tree
     * */
    public int pathSum1(TreeNode root, int targetSum) {
        HashMap<Long,Integer> map = new HashMap<>();
        map.put((long)0,1);
        dfs1(root,targetSum,map,0);
        return noOfPaths1;
    }


    public static void main(String[] args) {

        _17_PathSumIII obj = new _17_PathSumIII();

        // Test Case 1: Identical trees
        TreeNode root1 = obj.new TreeNode(1);
        root1.left = obj.new TreeNode(2);
        root1.right = obj.new TreeNode(3);
        root1.right.left = obj.new TreeNode(5);
        root1.right.right = obj.new TreeNode(6);
        root1.right.left.left = obj.new TreeNode(7);
        root1.right.left.right = obj.new TreeNode(8);
        System.out.println("Result: " + obj.pathSum(root1,5));
        System.out.println("Result 1 : " + obj.pathSum1(root1,5));
        System.out.println();
    }
}