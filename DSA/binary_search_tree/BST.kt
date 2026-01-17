package binary_search_tree

import binary_search_tree.BST.inorderTraversal
import binary_search_tree.BST.insertRecursively
import com.sun.jndi.url.corbaname.corbanameURLContextFactory

object BST {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }


    fun insertRecursively(root: TreeNode?,value:Int): TreeNode{

        //If root is null then return this as root node
        if (root==null) return TreeNode(value)

        if (value < root.`val`){
            root.left = insertRecursively(root.left,value)
        }else {
            root.right = insertRecursively(root.right,value)
        }
        return root
    }

    fun insertIteratively(root: TreeNode?,value:Int): TreeNode{

        val temp = TreeNode(value)
        if (root==null) return temp

        var curr = root
        while (curr!=null){
            if (value < curr.`val`!!&&curr.left!=null) {
                curr = curr.left
            }else if(value > curr.`val`!!&&curr.right!=null){
                curr = curr.right
            } else break
        }

        if (curr?.`val`!! > value) {
            curr.left = temp;
        } else {
            curr.right = temp;
        }

        return root
    }

    // In-order traversal (Left -> Root -> Right)
    fun inorderTraversal(root: TreeNode?) {
        if (root == null) return
        inorderTraversal(root.left)
        print("${root.`val`} ")
        inorderTraversal(root.right)
    }
}

fun main(){

    /* ---------- Test Case 1 ---------- */
    var root: BST.TreeNode? = null
    val values1 = listOf(50, 30, 70, 20, 40, 60, 80, 35)

    for (v in values1) {
        root = insertRecursively(root, v)
    }

    print("Test Case 1 - Actual Output   : ")
    inorderTraversal(root)
    println()
    println("Test Case 1 - Expected Output : 20 30 35 40 50 60 70 80")
    println()

    /* ---------- Test Case 2 ---------- */
    root = null
    val values2 = listOf(10, 5, 15, 3, 7, 12, 18)

    for (v in values2) {
        root = insertRecursively(root, v)
    }

    print("Test Case 2 - Actual Output   : ")
    inorderTraversal(root)
    println()
    println("Test Case 2 - Expected Output : 3 5 7 10 12 15 18")
    println()

    /* ---------- Test Case 3 (Skewed Tree) ---------- */
    root = null
    val values3 = listOf(1, 2, 3, 4, 5)

    for (v in values3) {
        root = insertRecursively(root, v)
    }

    print("Test Case 3 - Actual Output   : ")
    inorderTraversal(root)
    println()
    println("Test Case 3 - Expected Output : 1 2 3 4 5")
}
