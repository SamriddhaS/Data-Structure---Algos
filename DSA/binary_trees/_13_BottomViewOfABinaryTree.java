package binary_trees;

import java.util.*;

/**
 * Problem Link : https://www.naukri.com/code360/problems/bottom-view-of-binary-tree_893110
 *
 * Video Explanation :
 *
 *
 */
public class _13_BottomViewOfABinaryTree {

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

    static class NodeInfoMore {
        TreeNode node;
        int row;
        int col;
        NodeInfoMore(TreeNode node, int row, int col){
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }

    public static void dfs(TreeNode node,int row,int col,HashMap<Integer,NodeInfoMore> colMap){

        // base case
        if (node==null) return;

        NodeInfoMore existingNode = colMap.get(col);
        if (existingNode==null||row>=existingNode.row) colMap.put(col,new NodeInfoMore(node,row,col));

        dfs(node.left,row+1,col-1,colMap);
        dfs(node.right,row+1,col+1,colMap);

    }

    /**
     * Solution 1 : This is a valid solution using DFS but will give stack overflow error for
     * large test case.
     *
     * Time Complexity: O(N log N)
     *
     * Breaking it down:
     *
     * DFS Traversal: O(N)
     *
     * You visit each node exactly once
     * At each node, you perform O(1) operations (HashMap get/put)
     *
     * Sorting columns: O(K log K) where K = number of unique columns
     *
     * In the worst case, K can be O(N) (for a skewed tree)
     * So this becomes O(N log N) in worst case
     *
     * Building final result: O(K) = O(N) worst case
     *
     * Overall: O(N) + O(N log N) + O(N) = O(N log N)
     *
     *
     * Space Complexity: O(N)
     *
     * Breaking it down:
     * Recursion Call Stack: O(H) where H = height of tree
     *
     * Best case (balanced tree): O(log N)
     * Worst case (skewed tree): O(N) ← This causes stack overflow!
     *
     * HashMap (colMap): O(K) = O(N) worst case
     *
     * Stores one entry per unique horizontal distance
     * Maximum K columns = N (in worst case)
     *
     * Auxiliary lists (sortedCol, answer): O(K) = O(N)
     *
     * Overall: O(H) + O(N) + O(N)
     *
     * */
    public static List<Integer> bottomView(TreeNode root) {
        HashMap<Integer,NodeInfoMore> colMap = new HashMap<>();
        dfs(root,0,0,colMap);

        List<Integer> sortedCol = new ArrayList<>(colMap.keySet());
        Collections.sort(sortedCol);

        List<Integer> answer = new ArrayList<>();
        for (Integer i:sortedCol){
            answer.add(colMap.get(i).node.val);
        }
        return answer;
    }


    public static void main(String[] args) {

        _13_BottomViewOfABinaryTree obj = new _13_BottomViewOfABinaryTree();

        // Test Case 1: Identical trees
        TreeNode root1 = obj.new TreeNode(1);
        root1.left = obj.new TreeNode(2);
        root1.right = obj.new TreeNode(3);
        root1.right.left = obj.new TreeNode(5);
        root1.right.right = obj.new TreeNode(6);
        root1.right.left.left = obj.new TreeNode(7);
        root1.right.left.right = obj.new TreeNode(8);
        System.out.println("Result: " + obj.bottomView(root1));
        System.out.println();
    }
}