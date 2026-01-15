package binary_search_tree

import com.sun.jndi.url.corbaname.corbanameURLContextFactory

class BST {
    class BSTNode(){
        var value: Int?=null
        var left: BSTNode?=null
        var right: BSTNode?=null
        constructor(value: Int) : this() {
            this.value = value
        }
    }


    fun insertRecursively(root: BSTNode?,value:Int): BSTNode{

        //If root is null then return this as root node
        if (root==null) return BSTNode(value = value)

        if (value < root.value!!){
            root.left = insertRecursively(root.left,value)
        }else {
            root.right = insertRecursively(root.right,value)
        }
        return root
    }

    fun insertIteratively(root: BSTNode?,value:Int): BSTNode{

        val temp = BSTNode(value)
        if (root==null) return temp

        var curr = root
        while (curr!=null){
            if (value < curr.value!!&&curr.left!=null) {
                curr = curr.left
            }else if(value > curr.value!!&&curr.right!=null){
                curr = curr.right
            } else break
        }

        if (curr?.value!! > value) {
            curr.left = temp;
        } else {
            curr.right = temp;
        }

        return root
    }

    // In-order traversal (Left -> Root -> Right)
    fun inorderTraversal(root: BSTNode?) {
        if (root == null) return
        inorderTraversal(root.left)
        print("${root.value} ")
        inorderTraversal(root.right)
    }
}

fun main(){
    val bst = BST()

    /* ---------- Test Case 1 ---------- */
    var root: BST.BSTNode? = null
    val values1 = listOf(50, 30, 70, 20, 40, 60, 80, 35)

    for (v in values1) {
        root = bst.insertRecursively(root, v)
    }

    print("Test Case 1 - Actual Output   : ")
    bst.inorderTraversal(root)
    println()
    println("Test Case 1 - Expected Output : 20 30 35 40 50 60 70 80")
    println()

    /* ---------- Test Case 2 ---------- */
    root = null
    val values2 = listOf(10, 5, 15, 3, 7, 12, 18)

    for (v in values2) {
        root = bst.insertRecursively(root, v)
    }

    print("Test Case 2 - Actual Output   : ")
    bst.inorderTraversal(root)
    println()
    println("Test Case 2 - Expected Output : 3 5 7 10 12 15 18")
    println()

    /* ---------- Test Case 3 (Skewed Tree) ---------- */
    root = null
    val values3 = listOf(1, 2, 3, 4, 5)

    for (v in values3) {
        root = bst.insertRecursively(root, v)
    }

    print("Test Case 3 - Actual Output   : ")
    bst.inorderTraversal(root)
    println()
    println("Test Case 3 - Expected Output : 1 2 3 4 5")
}
