package binary_trees;

public class Tree {

    private static class Node {
        String data;
        Node leftChild;
        Node rightChild;

        public Node(String data) {
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }

    }

    private Node root;

    public Tree(String rootData) {
        this.root = new Node(rootData);
    }

    public void printTree() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        printTreePreOrder(root, 0);
        System.out.println("---------------------------");
        printTreePostOrder(root, 0);
        System.out.println("---------------------------");
        printTreeInOrder(root, 0);
    }

    /**
     * Pre-order traversal => (Root, Left, Right).
     * @param node The current node to print.
     * @param depth The current depth of the node, used for indentation.
     */
    private void printTreePreOrder(Node node, int depth) {
        
        // Print the current node's data
        System.out.println("- " + node.data);

        // Recursively call this method for the left child
        if (node.leftChild != null) {
            printTreePreOrder(node.leftChild, depth + 1);
        }
        
        // Recursively call this method for the right child
        if (node.rightChild != null) {
            printTreePreOrder(node.rightChild, depth + 1);
        }
    }

    /**
     * Post-order traversal => (Left, Right, Root).
     * @param node The current node to print.
     * @param depth The current depth of the node, used for indentation.
     */
    private void printTreePostOrder(Node node, int depth) {
        
        // Recursively call this method for the left child
        if (node.leftChild != null) {
            printTreePostOrder(node.leftChild, depth + 1);
        }
        
        // Recursively call this method for the right child
        if (node.rightChild != null) {
            printTreePostOrder(node.rightChild, depth + 1);
        }

        // Print the current node's data
        System.out.println("- " + node.data);
    }
    
    /**
     * In-order traversal => (Left, Root, Right, ).
     * @param node The current node to print.
     * @param depth The current depth of the node, used for indentation.
     */
    private void printTreeInOrder(Node node, int depth) {
        
        // Recursively call this method for the left child
        if (node.leftChild != null) {
            printTreeInOrder(node.leftChild, depth + 1);
        }

        // Print the current node's data
        System.out.println("- " + node.data);
        
        // Recursively call this method for the right child
        if (node.rightChild != null) {
            printTreeInOrder(node.rightChild, depth + 1);
        }
    }
    
    public static void main(String args[]){
        // --- Sample Tree 1: A simple family tree ---
        System.out.println("--- Sample Tree 1: Family Tree ---");
        Tree familyTree = new Tree("Family");
        
        Node father = new Node("Father");
        Node uncle = new Node("Uncle");
        
        familyTree.root.leftChild=father;
        familyTree.root.rightChild= uncle;
        
        Node son = new Node("Son");
        Node daughter = new Node("Daughter");
        
        father.leftChild = son ;
        father.rightChild = daughter;
        
        Node cousin1 = new Node("Cousin 1");
        Node cousin2 = new Node("Cousin 2");
        uncle.leftChild = cousin1;
        uncle.rightChild = cousin2;

        familyTree.printTree();
        
    }
}
