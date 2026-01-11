package binary_trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Problem Link : https://leetcode.com/problems/maximum-width-of-binary-tree/description/
 * Video Explanation : https://www.youtube.com/watch?v=hwjd0U36MUE
 *
 * 662. Maximum Width of Binary Tree
 *
 * Given the root of a binary tree, return the maximum width of the given tree.
 * The maximum width of a tree is the maximum width among all levels.
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes),
 * where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that
 * level are also counted into the length calculation.
 * It is guaranteed that the answer will in the range of a 32-bit signed integer.
 *
 *
 * Example 1:
 * Input: root = [1,3,2,5,3,null,9]
 * Output: 4
 * Explanation: The maximum width exists in the third level with length 4 (5,3,null,9).
 *
 * Example 2:
 * Input: root = [1,3,2,5,null,null,9,6,null,7]
 * Output: 7
 * Explanation: The maximum width exists in the fourth level with length 7 (6,null,null,null,null,null,7).
 *
 * Example 3:
 * Input: root = [1,3,2,5]
 * Output: 2
 * Explanation: The maximum width exists in the second level with length 2 (3,2).
 *
 *
 * Constraints:
 *     The number of nodes in the tree is in the range [1, 3000].
 *     -100 <= Node.val <= 100
 */
public class _20_MaximumWidthOfBinaryTree {

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

    class NodeWithIndex{
        TreeNode node;
        int index;
        NodeWithIndex(TreeNode node,int index){
            this.node = node;
            this.index = index;
        }
    }

    /**
     *
     * Time Complexity: O(n)
     *
     *     Visit each node exactly once where n is the number of nodes in the tree
     *
     * Space Complexity: O(w)
     *     Where w is the maximum width of the tree (max nodes at any level)
     *     In the worst case (complete binary tree), w can be O(n/2) = O(n)
     *     In the best case (skewed tree), w = O(1)
    * */
    public int widthOfBinaryTree(TreeNode root) {
        Deque<NodeWithIndex> queue = new LinkedList<>();
        queue.offer(new NodeWithIndex(root,0));
        int answer=0;
        while(!queue.isEmpty()){
            int s = queue.size();
            int firstIdx = queue.peekFirst().index;
            int lastIdx = queue.peekLast().index;
            answer = Math.max(answer,lastIdx-firstIdx+1);
            while(!queue.isEmpty()&&s>0){
                NodeWithIndex nodeWithIdx = queue.pollFirst();
                int index = nodeWithIdx.index;
                TreeNode node = nodeWithIdx.node;
                if(node!=null){
                    if(node.left!=null) queue.addLast(new NodeWithIndex(node.left,index*2+1));
                    if(node.right!=null) queue.addLast(new NodeWithIndex(node.right,index*2+2));
                }
                s--;
            }
        }
        return answer;
    }

    public static void main(String[] args) {

        _20_MaximumWidthOfBinaryTree obj = new _20_MaximumWidthOfBinaryTree();

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
        System.out.println("Result: " + obj.widthOfBinaryTree(root1));
        System.out.println();
    }
}