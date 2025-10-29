package binary_trees;

import java.util.*;

/**
 * Problem Link : https://takeuforward.org/data-structure/top-view-of-a-binary-tree/
 * https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1
 * Video Explanation :
 *
 * @see _11_VerticalOrderTravarsalOfBinaryTree Almost same problem
 *
 * Top View of Binary Tree
 * Difficulty: MediumAccuracy: 38.43%Submissions: 416K+Points: 4Average Time: 45m
 *
 * You are given the root of a binary tree, and your task is to return its top view. The top view of a binary tree is the set of nodes visible when the tree is viewed from the top.
 *
 * Note:
 *     Return the nodes from the leftmost node to the rightmost node.
 *     If multiple nodes overlap at the same horizontal position, only the topmost (closest to the root) node is included in the view.
 *
 * Examples:
 * Input: root = [1, 2, 3]
 * Output: [2, 1, 3]
 * Explanation: The Green colored nodes represents the top view in the below Binary tree.
 *
 *
 * Input: root = [10, 20, 30, 40, 60, 90, 100]
 * Output: [40, 20, 10, 30, 100]
 * Explanation: The Green colored nodes represents the top view in the below Binary tree.
 *
 *
 * Constraints:
 * 1 ≤ number of nodes ≤ 105
 * 1 ≤ node->data ≤ 105
 *
 */
public class _12_TopViewOfABinaryTree {

    class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
            this.left = null;
            this.right = null;
        }
    }

    public static class NodeInfo {
        int value;
        int row;
        NodeInfo(int value,int row){
            this.value = value;
            this.row = row;
        }
    }

    public void dfs(Node node, int row, int column, HashMap<Integer,NodeInfo> columnMap){
        if(node==null) return;

        // insert the node to column map.
        NodeInfo existingNode = columnMap.get(column);
        if (existingNode==null||row<existingNode.row) columnMap.put(column, new NodeInfo(node.data, row));

        // traverse the child nodes.
        dfs(node.left,row+1,column-1,columnMap);
        dfs(node.right,row+1,column+1,columnMap);
    }

    /**
     * Solution 1 : Using DFS
     *
     * Time Complexity: O(N log N)
     *     DFS traversal: O(N) - visits each node once
     *     Sorting columns: O(N log N) - worst case when all nodes are in different columns
     *     Building result: O(N) - iterates through sorted columns
     *     Overall: O(N log N) dominated by sorting
     *
     * Space Complexity: O(N)
     *     HashMap: O(N) - stores one entry per column (max N columns)
     *     Recursion stack: O(H) - where H is tree height (O(N) worst case for skewed tree)
     *     SortedKeys list: O(N) - stores all column indices
     *     Overall: O(N)
    * */
    public ArrayList<Integer> topView(Node root) {

        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<Integer,NodeInfo> colMap = new HashMap<>();
        dfs(root,0,0,colMap);

        // sort the keys in ascending order
        List<Integer> sortedKeys = new ArrayList<>(colMap.keySet());
        Collections.sort(sortedKeys);

        for (Integer key : sortedKeys) {
            answer.add(colMap.get(key).value);
        }
        return answer;
    }

    class NodeInfoMore {
        Node node;
        int row;
        int col;
        NodeInfoMore(Node node, int row, int col){
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Solution 2 : Using BFS, no changes in time and space complexity.
     *
     * Time Complexity: O(N log N)
     * Visit each node once: O(N)
     * TreeMap operations (put): O(log N) per insertion
     * At most N insertions into TreeMap: O(N log N)
     * Final iteration over TreeMap: O(N)
     * Overall: O(N log N) due to TreeMap operations
     *
     * Space Complexity: O(N)
     * Queue: O(W) where W is max width, worst case O(N) for skewed tree
     * TreeMap: O(N) in worst case (all nodes in different columns)
     * Answer list: O(N)
     * Overall: O(N)
     * */
    public ArrayList<Integer> topView1(Node root) {
        ArrayList<Integer> answer = new ArrayList<>();
        TreeMap<Integer,Integer> colMap = new TreeMap<>();
        Queue<NodeInfoMore> queue = new LinkedList<>();
        queue.offer(new NodeInfoMore(root,0,0));
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size>0){
                NodeInfoMore nodeInfo = queue.poll();
                if (nodeInfo!=null&&nodeInfo.node!=null){
                    if (!colMap.containsKey(nodeInfo.col)) {
                        colMap.put(nodeInfo.col,nodeInfo.node.data);
                    }
                    if (nodeInfo.node.left!=null) queue.offer(new NodeInfoMore(nodeInfo.node.left,nodeInfo.row+1,nodeInfo.col-1));
                    if (nodeInfo.node.right!=null) queue.offer(new NodeInfoMore(nodeInfo.node.right,nodeInfo.row+1,nodeInfo.col+1));
                }
                size--;
            }
        }

        for (Integer value:colMap.values()){
            answer.add(value);
        }

        return answer;
    }


    public static void main(String[] args) {

        _12_TopViewOfABinaryTree obj = new _12_TopViewOfABinaryTree();

        // Test Case 1: Identical trees
        Node root1 = obj.new Node(3);
        root1.left = obj.new Node(9);
        root1.right = obj.new Node(20);
        root1.right.left = obj.new Node(15);
        root1.right.right = obj.new Node(7);
        System.out.println("Result: " + obj.topView(root1));
        System.out.println("Result: " + obj.topView1(root1));
        System.out.println();
    }
}