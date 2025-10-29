package binary_trees;

import java.util.*;

/**
 * Problem Link : https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 * Video Explanation : https://www.youtube.com/watch?v=q_a6lpbKJdw
 *
 * 987. Vertical Order Traversal of a Binary Tree
 * Hard
 * Topics
 *
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index
 * starting from the leftmost column and ending on the rightmost column. There may be
 * multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
 *
 * Return the vertical order traversal of the binary tree.
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Column -1: Only node 9 is in this column.
 * Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
 * Column 1: Only node 20 is in this column.
 * Column 2: Only node 7 is in this column.
 *
 * Example 2:
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * Column -2: Only node 4 is in this column.
 * Column -1: Only node 2 is in this column.
 * Column 0: Nodes 1, 5, and 6 are in this column.
 *           1 is at the top, so it comes first.
 *           5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
 * Column 1: Only node 3 is in this column.
 * Column 2: Only node 7 is in this column.
 *
 * Example 3:
 * Input: root = [1,2,3,4,6,5,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * This case is the exact same as example 2, but with nodes 5 and 6 swapped.
 * Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [1, 1000].
 *     0 <= Node.val <= 1000
 *
 */
public class _11_VerticalOrderTravarsalOfBinaryTree {

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

    class NodeInfo {
        int value;
        int row;
        NodeInfo(int value,int row){
            this.value = value;
            this.row = row;
        }
    }

    public void dfs(TreeNode node,int row,int column,HashMap<Integer,ArrayList<NodeInfo>> columnMap){
        if(node==null) return;

        // insert the node to column map.
        ArrayList<NodeInfo> currentCol = columnMap.getOrDefault(column,new ArrayList<>());
        currentCol.add(new NodeInfo(node.val,row));
        columnMap.put(column,currentCol);

        // traverse the child nodes.
        dfs(node.left,row+1,column-1,columnMap);
        dfs(node.right,row+1,column+1,columnMap);
    }

    /**
     * Solution 1 :
     *
     * Time Complexity: O(N log N)
     * DFS traversal: O(N) - visit each node once
     * Sorting column keys: O(K log K) where K = number of unique columns ≤ N
     * Sorting nodes within each column: O(N log N) worst case
     *
     * In the worst case (skewed tree), all N nodes could be in one column
     * Across all columns, we sort N nodes total, so O(N log N)
     *
     * Building result: O(N) - iterate through all nodes once
     *
     * Overall: O(N) + O(N log N) + O(N) = O(N log N)
     *
     *
     * Space Complexity: O(N)
     *
     * HashMap storage: O(N) - stores all N nodes with their info
     * Recursion stack: O(H) where H = height of tree
     *
     * Best case (balanced): O(log N)
     * Worst case (skewed): O(N)
     *
     *
     * Result list: O(N) - stores all node values
     *
     * Overall: O(N) + O(H) = O(N)
    * */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        HashMap<Integer,ArrayList<NodeInfo>> colMap = new HashMap<>();
        dfs(root,0,0,colMap);

        // sort the keys in ascending order
        List<Integer> sortedKeys = new ArrayList<>(colMap.keySet());
        Collections.sort(sortedKeys);

        for (Integer key : sortedKeys) {

            List<NodeInfo> nodeInfoList = colMap.get(key);
            Collections.sort(nodeInfoList, (o1, o2) -> {
                if (o1.row != o2.row) {
                    return Integer.compare(o1.row, o2.row);
                }
                return Integer.compare(o1.value, o2.value);
            });
            List<Integer> columnValues = new ArrayList<>();
            for (NodeInfo node : nodeInfoList) {
                columnValues.add(node.value);
            }
            answer.add(columnValues);
        }
        return answer;
    }

    class NodeInfoMore {
        TreeNode node;
        int row;
        int col;
        NodeInfoMore(TreeNode node,int row,int col){
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Solution 2 : Using BFS, no changes in time and space complexity.
     *
     * Time Complexity: O(N log N)
     *
     * BFS traversal: O(N)
     * For each node insertion:
     * TreeMap (column) lookup/insert: O(log K)
     * TreeMap (row) lookup/insert: O(log R) where R = rows per column
     * PriorityQueue insert: O(log M) where M = nodes per (row, col) cell
     *
     * Total insertions: O(N × (log K + log R + log M))
     * Worst case: K ≈ N, R ≈ N, M ≈ N → O(N log N)
     * Building result: O(N)
     *
     * Overall: O(N log N)
     *
     *
     * Space Complexity: O(N)
     * Outer TreeMap: O(K)
     * Inner TreeMaps: O(K × R) total
     * PriorityQueues: O(N) total
     * Queue: O(W) where W = max width
     *
     * Overall: O(N)
    * */
    public List<List<Integer>> verticalTraversal1(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        TreeMap<Integer,TreeMap<Integer,PriorityQueue<Integer>>> colMap = new TreeMap<>();
        Queue<NodeInfoMore> queue = new LinkedList<>();
        queue.offer(new NodeInfoMore(root,0,0));
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size>0){
                NodeInfoMore nodeInfo = queue.poll();
                if (nodeInfo!=null&&nodeInfo.node!=null){
                    TreeMap<Integer,PriorityQueue<Integer>> list = colMap.getOrDefault(nodeInfo.col,new TreeMap<>());
                    PriorityQueue<Integer> pq = list.getOrDefault(nodeInfo.row,new PriorityQueue<>());
                    pq.offer(nodeInfo.node.val);
                    list.put(nodeInfo.row, pq);
                    colMap.put(nodeInfo.col, list);
                    if (nodeInfo.node.left!=null) queue.offer(new NodeInfoMore(nodeInfo.node.left,nodeInfo.row+1,nodeInfo.col-1));
                    if (nodeInfo.node.right!=null) queue.offer(new NodeInfoMore(nodeInfo.node.right,nodeInfo.row+1,nodeInfo.col+1));
                }
                size--;
            }
        }

        for (TreeMap<Integer,PriorityQueue<Integer>> cols : colMap.values()){
            ArrayList<Integer> elements = new ArrayList<>();
            for (PriorityQueue<Integer> elementQueue:cols.values()){
                while (!elementQueue.isEmpty()){
                    elements.add(elementQueue.poll());
                }
            }
            answer.add(elements);
        }

        return answer;
    }


    public static void main(String[] args) {

        _11_VerticalOrderTravarsalOfBinaryTree obj = new _11_VerticalOrderTravarsalOfBinaryTree();

        // Test Case 1: Identical trees
        TreeNode root1 = obj.new TreeNode(3);
        root1.left = obj.new TreeNode(9);
        root1.right = obj.new TreeNode(20);
        root1.right.left = obj.new TreeNode(15);
        root1.right.right = obj.new TreeNode(7);
        System.out.println("Result: " + obj.verticalTraversal(root1));
        System.out.println();
    }
}