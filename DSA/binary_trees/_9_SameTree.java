package binary_trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Link : https://leetcode.com/problems/same-tree/description/
 * Video Explanation : https://www.youtube.com/watch?v=YtoibyDlzk0
 *
 * 100. Same Tree
 * Solved
 * Easy
 *
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical,
 * and the nodes have the same value.
 *
 * Example 1:
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 *
 * Example 2:
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 *
 * Example 3:
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 *
 * Constraints:
 *     The number of nodes in both trees is in the range [0, 100].
 *     -104 <= Node.val <= 104
 *
 */
public class _9_SameTree {

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

    public void dfs(TreeNode node, ArrayList<Integer> answer){
        if(node==null) {
            answer.add(null);
            return;
        }

        answer.add(node.val);
        dfs(node.left,answer);
        dfs(node.right,answer);
    }

    /**
     * Solution 1 :
     *
     * Time Complexity: O(n + m)
     *
     *     Where n and m are the number of nodes in trees p and q respectively
     *     We traverse each tree once completely using DFS
     *     The equals() comparison takes O(min(n, m)) in best case or O(max(n, m)) in worst case
     *
     * Space Complexity: O(n + m)
     *
     *     Two ArrayLists store all nodes: O(n) + O(m)
     *     Recursion call stack: O(h₁) + O(h₂) where h₁ and h₂ are heights of the trees
     *     In worst case (skewed tree): O(n) + O(m)
     *     Overall: O(n + m)
     *
     * Note: If both trees have same structure (n = m), complexity simplifies to O(n) for both time and space.
    * */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        ArrayList<Integer> preorder1 = new ArrayList<>();
        ArrayList<Integer> preorder2 = new ArrayList<>();
        dfs(p,preorder1);
        dfs(q,preorder2);
        return preorder1.equals(preorder2);
    }

    public boolean dfs(TreeNode node1, TreeNode node2){
        if(node1==null&&node2==null) {
            return true;
        }

        if(node1==null) {
            return false;
        }

        if(node2==null) {
            return false;
        }

        if (node1.val!=node2.val) return false;

        return dfs(node1.left,node2.left) && dfs(node1.right,node2.right);
    }

    /**
     * Solution 2 : Without extra space for ArrayLists.
     *
     * Time Complexity: O(min(n, m))
     * Where n and m are the number of nodes in trees p and q
     * In best case: O(1) if root values differ
     * In worst case: O(min(n, m)) when trees are identical or differ at deepest level
     * Stops early upon finding first mismatch
     *
     * Space Complexity: O(min(h₁, h₂))
     * Where h₁ and h₂ are the heights of the two trees
     * Only recursion call stack is used
     * In worst case (skewed tree): O(min(n, m))
     * In best case (balanced tree): O(min(log n, log m))
     *
     * This is the optimal solution - no extra data structures, early termination on mismatch.
    * */
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        return dfs(p,q);
    }


    /**
     * Solution 3 : Iterative approach using BFS
     *
     * Time Complexity: O(min(n, m))
     * Visits each node once, stops early on mismatch
     * Best case: O(1), Worst case: O(min(n, m))
     *
     * Space Complexity: O(max(w₁, w₂))
     * Where w₁, w₂ are maximum widths of the trees
     * Two queues store nodes level-by-level
     * Best case: O(1) for skewed trees
     * Worst case: O(n) for complete/balanced trees (width ≈ n/2)
     *
    * */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p==null&&q==null) return true;
        if (p==null||q==null) return false;
        Queue<TreeNode> queue1 = new LinkedList<>();
        queue1.offer(p);
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue2.offer(q);
        while (!queue1.isEmpty()||!queue2.isEmpty()){
            int size = queue1.size();
            if (size!=queue2.size()) return false;
            while (size>0){
                TreeNode node = queue1.poll();
                TreeNode node1 = queue2.poll();

                if (node.val!=node1.val) return false;

                if (node.left!=null && node1.left!=null){
                    queue1.offer(node.left);
                    queue2.offer(node1.left);
                } else if(node.left!=null || node1.left!=null) {
                    return false;
                }

                if (node.right!=null && node1.right!=null){
                    queue1.offer(node.right);
                    queue2.offer(node1.right);
                } else if(node.right!=null || node1.right!=null){
                    return false;
                }

                size--;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        _9_SameTree obj = new _9_SameTree();

        // Test Case 1: Identical trees
        TreeNode root1 = obj.new TreeNode(1);
        root1.left = obj.new TreeNode(5);
        root1.right = obj.new TreeNode(3);

        TreeNode root2 = obj.new TreeNode(1);
        root2.left = obj.new TreeNode(5);
        root2.right = obj.new TreeNode(3);

        System.out.println("Test Case 1: Identical trees");
        System.out.println("Expected: true");
        System.out.println("Result: " + obj.isSameTree(root1, root2));
        System.out.println("Result: " + obj.isSameTree1(root1, root2));
        System.out.println("Result: " + obj.isSameTree2(root1, root2));
        System.out.println();

        // Test Case 2: Different structure (missing left child)
        TreeNode root3 = obj.new TreeNode(1);
        root3.left = obj.new TreeNode(2);

        TreeNode root4 = obj.new TreeNode(1);
        root4.right = obj.new TreeNode(2);

        System.out.println("Test Case 2: Different structure (left vs right child)");
        System.out.println("Expected: false");
        System.out.println("Result: " + obj.isSameTree(root3, root4));
        System.out.println("Result: " + obj.isSameTree1(root3, root4));
        System.out.println("Result: " + obj.isSameTree2(root3, root4));
        System.out.println();

        // Test Case 3: Different values
        TreeNode root5 = obj.new TreeNode(1);
        root5.left = obj.new TreeNode(2);
        root5.right = obj.new TreeNode(3);

        TreeNode root6 = obj.new TreeNode(1);
        root6.left = obj.new TreeNode(2);
        root6.right = obj.new TreeNode(4);

        System.out.println("Test Case 3: Different values in right child");
        System.out.println("Expected: false");
        System.out.println("Result: " + obj.isSameTree(root5, root6));
        System.out.println("Result: " + obj.isSameTree1(root5, root6));
        System.out.println("Result: " + obj.isSameTree2(root5, root6));
        System.out.println();

        // Test Case 4: Both trees are null
        System.out.println("Test Case 4: Both trees are null");
        System.out.println("Expected: true");
        System.out.println("Result: " + obj.isSameTree(null, null));
        System.out.println("Result: " + obj.isSameTree1(null, null));
        System.out.println("Result: " + obj.isSameTree2(null, null));
        System.out.println();

        // Test Case 5: One tree is null
        TreeNode root7 = obj.new TreeNode(1);

        System.out.println("Test Case 5: One tree is null");
        System.out.println("Expected: false");
        System.out.println("Result: " + obj.isSameTree(root7, null));
        System.out.println("Result: " + obj.isSameTree2(root7, null));
        System.out.println();

        // Test Case 6: Single node trees with same value
        TreeNode root8 = obj.new TreeNode(5);
        TreeNode root9 = obj.new TreeNode(5);

        System.out.println("Test Case 6: Single node trees with same value");
        System.out.println("Expected: true");
        System.out.println("Result: " + obj.isSameTree(root8, root9));
        System.out.println("Result: " + obj.isSameTree1(root8, root9));
        System.out.println("Result: " + obj.isSameTree2(root8, root9));
        System.out.println();

        // Test Case 7: Single node trees with different values
        TreeNode root10 = obj.new TreeNode(5);
        TreeNode root11 = obj.new TreeNode(10);

        System.out.println("Test Case 7: Single node trees with different values");
        System.out.println("Expected: false");
        System.out.println("Result: " + obj.isSameTree(root10, root11));
        System.out.println("Result: " + obj.isSameTree1(root10, root11));
        System.out.println("Result: " + obj.isSameTree2(root10, root11));
        System.out.println();

        // Test Case 8: Larger identical trees
        TreeNode root12 = obj.new TreeNode(1);
        root12.left = obj.new TreeNode(2);
        root12.right = obj.new TreeNode(3);
        root12.left.left = obj.new TreeNode(4);
        root12.left.right = obj.new TreeNode(5);
        root12.right.left = obj.new TreeNode(6);

        TreeNode root13 = obj.new TreeNode(1);
        root13.left = obj.new TreeNode(2);
        root13.right = obj.new TreeNode(3);
        root13.left.left = obj.new TreeNode(4);
        root13.left.right = obj.new TreeNode(5);
        root13.right.left = obj.new TreeNode(6);

        System.out.println("Test Case 8: Larger identical trees");
        System.out.println("Expected: true");
        System.out.println("Result: " + obj.isSameTree(root12, root13));
        System.out.println("Result: " + obj.isSameTree1(root12, root13));
        System.out.println("Result: " + obj.isSameTree2(root12, root13));
        System.out.println();

        // Test Case 9: Different subtree depth
        TreeNode root14 = obj.new TreeNode(1);
        root14.left = obj.new TreeNode(2);
        root14.left.left = obj.new TreeNode(3);

        TreeNode root15 = obj.new TreeNode(1);
        root15.left = obj.new TreeNode(2);

        System.out.println("Test Case 9: Different subtree depth");
        System.out.println("Expected: false");
        System.out.println("Result: " + obj.isSameTree(root14, root15));
        System.out.println("Result: " + obj.isSameTree1(root14, root15));
        System.out.println("Result: " + obj.isSameTree2(root14, root15));
        System.out.println();

    }
}