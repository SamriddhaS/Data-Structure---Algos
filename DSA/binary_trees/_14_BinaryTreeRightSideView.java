package binary_trees;

import java.util.*;

/**
 * Problem Link : https://leetcode.com/problems/binary-tree-right-side-view/description/
 * Video Explanation :
 *
 * 199. Binary Tree Right Side View
 * Medium
 *
 * Given the root of a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *
 * Example 1:
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 *
 * Explanation:
 * Example 2:
 * Input: root = [1,2,3,4,null,null,null,5]
 * Output: [1,3,4,5]
 *
 * Explanation:
 * Example 3:
 * Input: root = [1,null,3]
 * Output: [1,3]
 *
 * Example 4:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [0, 100].
 *     -100 <= Node.val <= 100
 *
 *
 */
public class _14_BinaryTreeRightSideView {

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
        NodeInfoMore(TreeNode node, int row){
            this.node = node;
            this.row = row;
        }
    }

    /**
     * Solution 1 : Using BFS
     *
     * Time Complexity: O(n)
     * Visits each node exactly once in the BFS traversal
     * HashMap operations (put/get) are O(1) average case
     * Final iteration through rowMap is O(h) where h = height
     *
     * Space Complexity: O(n)
     * Queue: O(w) where w is max width of tree, worst case O(n) for complete tree
     * HashMap: O(h) where h is height, stores one node per level
     * Result list: O(h)
     * Overall: O(n) dominated by the queue in worst case
     *
     * */
    public static List<Integer> rightSideView(TreeNode root) {

        if(root==null) return new ArrayList<>();

        HashMap<Integer,NodeInfoMore> rowMap = new HashMap<>();
        Queue<NodeInfoMore> queue = new LinkedList<>();
        queue.offer(new NodeInfoMore(root,0));

        while (!queue.isEmpty()){
            NodeInfoMore node = queue.poll();
            if (node!=null){
                int row = node.row;
                rowMap.put(row,node);
                if (node.node.left!=null) queue.offer(new NodeInfoMore(node.node.left,row+1));
                if (node.node.right!=null) queue.offer(new NodeInfoMore(node.node.right,row+1));
            }
        }

        List<Integer> answer = new ArrayList<>();
        for (NodeInfoMore nodeInfo : rowMap.values()){
            answer.add(nodeInfo.node.val);
        }

        return answer;
    }

    /**
     * Solution 2 : Optimal solution
     *
     * Time Complexity: O(n)
     * Visits each node exactly once in BFS traversal
     * Queue operations are O(1)
     *
     * Space Complexity: O(w)
     * Queue: O(w) where w is maximum width of tree
     * Result list: O(h) where h is height
     * Overall: O(w), which is O(n) worst case for complete binary tree
     *
     * */
    public static List<Integer> rightSideView1(TreeNode root) {

        List<Integer> answer = new ArrayList<>();
        if(root==null) return answer;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int n = queue.size();
            while (n>0){
                TreeNode node = queue.poll();
                if (node!=null){
                    n--;
                    if (n==0) answer.add(node.val);
                    if (node.left!=null) queue.offer(node.left);
                    if (node.right!=null) queue.offer(node.right);
                }
            }
        }

        return answer;
    }


    public static void main(String[] args) {

        _14_BinaryTreeRightSideView obj = new _14_BinaryTreeRightSideView();

        // Test Case 1: Identical trees
        TreeNode root1 = obj.new TreeNode(1);
        root1.left = obj.new TreeNode(2);
        root1.right = obj.new TreeNode(3);
        root1.right.left = obj.new TreeNode(5);
        root1.right.right = obj.new TreeNode(6);
        root1.right.left.left = obj.new TreeNode(7);
        root1.right.left.right = obj.new TreeNode(8);
        System.out.println("Result: " + obj.rightSideView(root1));
        System.out.println();
    }
}