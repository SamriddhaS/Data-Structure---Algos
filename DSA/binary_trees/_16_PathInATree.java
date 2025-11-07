package binary_trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Link : https://www.naukri.com/code360/problems/path-in-a-tree_3843990?leftPanelTabValue=PROBLEM
 * Video Explanation :
 *
 * Print Root to Node Path in a Binary Tree | Path In A Tree
 *
 * Problem statement
 * You are given a binary tree with ‘N’ number of nodes and a node ‘X’. Your task is to print the path from
 * the root node to the given node ‘X’.
 * A binary tree is a hierarchical data structure in which each node has at most two children.
 *
 * Example:
 * Here, for ‘X ’= 7, the output will be 1 3 7.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 10
 * 1 <= N <= 10000
 * 1 <= X <= N
 * All the node values will be in a range from 1 to N.
 *
 * Time limit: 1 sec.
 *
 *  Sample Input 1 :
 * 2
 * 1 2 3 4 5 6 7 -1 -1 -1 -1 -1 -1 -1 -1
 * 7
 * 3 2 1 -1 -1 -1 -1
 * 1
 * Sample output 1 :
 * 1 3 7
 * 3 1
 * Explanation For Sample Output 1:
 * For the first test case, the tree will be:
 *
 * Here, for ‘X ’= 7, the output will be 1 3 7.
 *
 *
 * For the second test case, the tree will be:
 *
 * Here, for ‘X ’= 1, the output will be 3 1.
 * Sample Input 2 :
 * 2
 * 1 3 -1 -1 4 2 -1 -1 -1
 * 1
 * 4 -1 1 2 -1 -1 3 -1 -1
 * 1
 * Sample output 2 :
 * 1
 * 4 1
 *
 */
public class _16_PathInATree {

    class TreeNode
    {
        int data;
        TreeNode left, right;

        public TreeNode(int item)
        {
            data = item;
            left = right = null;
        }
    }

    public static boolean dfs(TreeNode node, int target, ArrayList<Integer> pathArray){
        if(node==null) return false;
        pathArray.add(node.data);
        if(node.data==target) return true;
        if(dfs(node.left,target,pathArray)) return true;
        if(dfs(node.right,target,pathArray)) return true;
        pathArray.remove(pathArray.size()-1);
        return false;
    }

    /**
     * Solution 1 : using dfs.
     *
     * Time Complexity: O(n)
     * Visits each node at most once in worst case (when target doesn't exist or is at the last position)
     * Each node is processed with constant work (add/remove operations are O(1))
     *
     * Space Complexity: O(h)
     * Recursion stack: O(h) where h = height of tree
     * pathArray: O(h) stores at most h nodes (root to target path)
     * Best case: O(log n) for balanced tree
     * Worst case: O(n) for skewed tree
    * */
    public static ArrayList<Integer> pathInATree(TreeNode root, int x) {
        ArrayList<Integer> answer = new ArrayList<>();
        dfs(root,x,answer);
        return answer;
    }

    public static void main(String[] args) {

        _16_PathInATree obj = new _16_PathInATree();

        // Test Case 1: Identical trees
        TreeNode root1 = obj.new TreeNode(1);
        root1.left = obj.new TreeNode(2);
        root1.right = obj.new TreeNode(3);
        root1.right.left = obj.new TreeNode(5);
        root1.right.right = obj.new TreeNode(6);
        root1.right.left.left = obj.new TreeNode(7);
        root1.right.left.right = obj.new TreeNode(8);
        System.out.println("Result: " + pathInATree(root1,8));
        System.out.println();

        TreeNode root2 = obj.new TreeNode(1);
        root2.left = obj.new TreeNode(2);
        root2.right = obj.new TreeNode(2);
        System.out.println("Result: " + pathInATree(root2,2));
        System.out.println();
    }
}