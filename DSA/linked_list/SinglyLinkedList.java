package DSA.linked_list;

public class SinglyLinkedList {

    public static class Node {

        public Node next;
        public int data;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static void traverseList(Node head) {
        System.out.println("\n========== SinglyLinkedList TraverseList ================\n");
        while (head != null) {
            System.out.print("->" + head.data);
            head = head.next;
        }
    }

    static void traverseListRecursive(Node head) {
        System.out.print("->" + head.data);
        if (head.next == null) return;
        traverseListRecursive(head.next);
    }

    static Boolean searchList(Node head, int searchingFor) {
        System.out.println("========== SinglyLinkedList SearchList ================");
        while (head != null) {
            System.out.println("Checking ->" + head.data);
            if (head.data == searchingFor) {
                System.out.println("Item Found" + head.data);
                return true;
            }
            head = head.next;
        }
        System.out.println("Item not found");
        return false;
    }

    static Boolean searchListRecursive(Node head, int searchingFor) {

        if (head.next == null) {
            System.out.println("Item not found");
            return false;
        }

        if (head.data == searchingFor) {
            System.out.println("Item Found : " + head.data);
            return true;
        }

        return searchListRecursive(head.next, searchingFor);
    }

    static Node insertPos(Node head, int pos, int data) {

        System.out.println("Trying to insert new node at position -> " + pos);

        if (pos < 1)
            return head;

        // head will change if pos=1
        if (pos == 1) {
            Node newNode = new Node(data);
            newNode.next = head;
            return newNode;
        }

        Node posNode = head;
        for (int i = 1; i < pos - 1 && posNode != null; i++) {
            posNode = posNode.next;
        }
        if (posNode == null) {
            System.out.println("Position Out of bound");
            return head;
        }
        Node newNode = new Node(data);
        newNode.next = posNode.next;
        posNode.next = newNode;
        return head;
    }

    public static Node deleteNode(Node head, int position) {

        System.out.println("Trying to delete node at position -> " + position);
        Node temp = head;
        if (position == 1) {
            head = head.next;
            temp = null;
            return head;
        }

        Node prev = null;
        for (int i = 1; i < position && temp != null; i++) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null || prev == null) {
            System.out.println("Position Out of bound");
            return head;
        }

        prev.next = temp.next;
        temp = null;
        return head;
    }

    static Node reverseList(Node head) {
        Node current = head, prev = null, next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    /**
     * How this works not able to visulise
     */
    static Node reverseListRecursive(Node head) {
        if (head.next == null) return head;
        Node next = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return next;
    }

    static void singlyLinkedListOperations(){
        // Create a hard-coded linked list:
        // 10 -> 20 -> 30 -> 40
        SinglyLinkedList.Node head = new SinglyLinkedList.Node(10);
        head.next = new SinglyLinkedList.Node(20);
        head.next.next = new SinglyLinkedList.Node(30);
        head.next.next.next = new SinglyLinkedList.Node(40);
        head.next.next.next.next = new SinglyLinkedList.Node(50);

        // Example of traversing the node and printing
        SinglyLinkedList.traverseList(head);
        System.out.println("\n========== SinglyLinkedList TraverseList Recursive ================");
        SinglyLinkedList.traverseListRecursive(head);
        SinglyLinkedList.searchList(head, 30);
        System.out.println("\n========== SinglyLinkedList Search Recursive ================");
        SinglyLinkedList.searchListRecursive(head, 40);
        System.out.println("\n========== SinglyLinkedList Insert at position ================");
        head = SinglyLinkedList.insertPos(head, 5, 45);
        SinglyLinkedList.traverseList(head);
        System.out.println("\n========== SinglyLinkedList Delete position ================");
        SinglyLinkedList.traverseList(SinglyLinkedList.deleteNode(head, 3));
        System.out.println("\n========== SinglyLinkedList Reverse ================");
        //SinglyLinkedList.traverseList(SinglyLinkedList.reverseList(head));
        SinglyLinkedList.traverseList(SinglyLinkedList.reverseListRecursive(head));
    }
}
