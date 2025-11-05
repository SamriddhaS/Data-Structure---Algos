package binary_trees;

import java.util.*;

/**
 * Problem Link : https://www.naukri.com/code360/problems/bottom-view-of-binary-tree_893110
 * Video Explanation :
 *
 * Bottom View Of Binary Tree
 *
 * Problem statement
 *
 * You are given a 'Binary Tree'.
 *
 * Return the bottom view of the binary tree.
 * Note :
 *
 * 1. A node will be in the bottom-view if it is the bottom-most node at its horizontal distance from the root.
 * 2. The horizontal distance of the root from itself is 0. The horizontal distance of the right child of the root node is 1 and the horizontal distance of the left child of the root node is -1.
 * 3. The horizontal distance of node 'n' from root = horizontal distance of its parent from root + 1, if node 'n' is the right child of its parent.
 * 4. The horizontal distance of node 'n' from root = horizontal distance of its parent from the root - 1, if node 'n' is the left child of its parent.
 * 5. If more than one node is at the same horizontal distance and is the bottom-most node for that horizontal distance, including the one which is more towards the right.
 *
 * Example:
 * Input: Consider the given Binary Tree:
 * Output: 4 2 6 3 7
 *
 * Explanation:
 * Below is the bottom view of the binary tree.
 *
 * 1 is the root node, so its horizontal distance = 0.
 * Since 2 lies to the left of 0, its horizontal distance = 0-1= -1
 * 3 lies to the right of 0, its horizontal distance = 0+1 = 1
 * Similarly, horizontal distance of 4 = Horizontal distance of 2 - 1= -1-1=-2
 * Horizontal distance of 5 = Horizontal distance of 2 + 1=  -1+1 = 0
 * Horizontal distance of 6 = 1-1 =0
 * Horizontal distance of 7 = 1+1 = 2
 *
 * The bottom-most node at a horizontal distance of -2 is 4.
 * The bottom-most node at a horizontal distance of -1 is 2.
 * The bottom-most node at a horizontal distance of 0 is 5 and 6. However, 6 is more towards the right, so 6 is included.
 * The bottom-most node at a horizontal distance of 1 is 3.
 * The bottom-most node at a horizontal distance of 2 is 7.
 *
 * Hence, the bottom view would be 4 2 6 3 7
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

    /**
     * Solution 2 : Using BFS
     *
     * Time Complexity: O(N log N)
     *
     * O(N) for BFS traversal (visiting each node once)
     * O(N log N) for sorting the column keys
     * O(W) for building final result (W = width of tree)
     * Overall: O(N log N) (sorting dominates)
     *
     * Space Complexity: O(N)
     *
     * O(W) for queue (max width of tree, worst case N for skewed tree)
     * O(W) for hashmap (stores one node per unique column)
     * O(W) for result lists
     * Overall: O(N) in worst case when tree is completely skewed
     *
     * */
    public static List<Integer> bottomView1(TreeNode root) {

        HashMap<Integer,NodeInfoMore> colMap = new HashMap<>();
        Queue<NodeInfoMore> queue = new LinkedList<>();
        queue.offer(new NodeInfoMore(root,0,0));

        while (!queue.isEmpty()){
            NodeInfoMore node = queue.poll();
            if (node!=null){
                int row = node.row;
                int col = node.col;

                NodeInfoMore existingNode = colMap.get(col);
                if (existingNode==null || row >= existingNode.row){
                    colMap.put(col,node);
                }

                if (node.node.left!=null) queue.offer(new NodeInfoMore(node.node.left,row+1,col-1));
                if (node.node.right!=null) queue.offer(new NodeInfoMore(node.node.right,row+1,col+1));
            }
        }

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
        System.out.println("Result: " + obj.bottomView1(root1));
        System.out.println();
    }
}