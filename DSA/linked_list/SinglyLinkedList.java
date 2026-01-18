package linked_list;

public class SinglyLinkedList {

    public static class ListNode {

        public ListNode next;
        public int data;

        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static void traverseList(ListNode head) {
        while (head != null) {
            System.out.print("->" + head.data);
            head = head.next;
        }
    }

    static void traverseListRecursive(ListNode head) {
        System.out.print("->" + head.data);
        if (head.next == null) return;
        traverseListRecursive(head.next);
    }

    static Boolean searchList(ListNode head, int searchingFor) {
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

    static Boolean searchListRecursive(ListNode head, int searchingFor) {

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

    static ListNode insertPos(ListNode head, int pos, int data) {

        System.out.println("Trying to insert new node at position -> " + pos);

        if (pos < 1)
            return head;

        // head will change if pos=1
        if (pos == 1) {
            ListNode newListNode = new ListNode(data);
            newListNode.next = head;
            return newListNode;
        }

        ListNode posListNode = head;
        for (int i = 1; i < pos - 1 && posListNode != null; i++) {
            posListNode = posListNode.next;
        }
        if (posListNode == null) {
            System.out.println("Position Out of bound");
            return head;
        }
        ListNode newListNode = new ListNode(data);
        newListNode.next = posListNode.next;
        posListNode.next = newListNode;
        return head;
    }

    public static ListNode deleteNode(ListNode head, int position) {

        System.out.println("Trying to delete node at position -> " + position);
        ListNode temp = head;
        if (position == 1) {
            head = head.next;
            temp = null;
            return head;
        }

        ListNode prev = null;
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

    static ListNode reverseList(ListNode head) {
        ListNode current = head, prev = null, next = null;
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
    static ListNode reverseListRecursive(ListNode head) {
        if (head.next == null) return head;
        ListNode next = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return next;
    }

    static void singlyLinkedListOperations(){
        // Create a hard-coded linked list:
        // 10 -> 20 -> 30 -> 40
        ListNode head = new ListNode(10);
        head.next = new ListNode(20);
        head.next.next = new ListNode(30);
        head.next.next.next = new ListNode(40);
        head.next.next.next.next = new ListNode(50);

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
