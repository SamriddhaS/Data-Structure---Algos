package linked_list;

public class LinkedList {

    public static void main(String[] args) {

        //SinglyLinkedList.singlyLinkedListOperations();

        //DoublyLinkedList.doublyLinkedListOperations();

        CircularSinglyList.circularSinglyLinkedListOperations();
    }
}

class DoublyLinkedList {

    static class Node{
        Node previous,next;
        int data;
        Node(int data){
            this.data = data;
            previous = null;
            next = null;
        }
    }

    static void traversalForward(Node head){
        if(head==null){
            System.out.println("Not a valid list.");
            return;
        }

        while(head!=null){
            System.out.print("->"+head.data);
            head = head.next;
        }
    }

    static void traversalForwardRecursive(Node head){

        if(head==null){
            return;
        }

        System.out.print("->"+head.data);
        traversalForwardRecursive(head.next);
    }

    static void traversalBackward(Node tail){
        if(tail==null){
            System.out.println("Not a valid list.");
            return;
        }

        while(tail!=null){
            System.out.print("->"+tail.data);
            tail = tail.previous;
        }
    }

    static void traversalBackwardRecursive(Node tail){

        if(tail==null){
            return;
        }

        System.out.print("->"+tail.data);
        traversalBackwardRecursive(tail.previous);
    }

    public static Node insertAtPosition(Node head, int pos, int new_data){

        if(head==null){
            System.out.println("Not a valid list.");
            return null;
        }

        if(pos==1){
            Node newNode = new Node(new_data);
            newNode.next = head;
            return newNode;
        }

        Node current = head;
        for(int i=1;i<pos-1&&current!=null;i++){
            current = current.next;
        }

        if (current==null){
            System.out.println("Not a valid position");
            return head;
        }

        Node newNode = new Node(new_data);
        newNode.next = current.next;
        newNode.previous = current;
        current.next = newNode;

        return  head;
    }

    public static Node deleteAtPosition(Node head, int pos){

        if(head==null){
            System.out.println("Not a valid list.");
            return null;
        }

        Node current = head;
        for(int i=1;i<pos&&current!=null;i++){
            current = current.next;
        }

        if (current==null){
            System.out.println("Not a valid position");
            return head;
        }

        if(current.previous!=null) current.previous.next = current.next;
        if(current.next!=null) current.next.previous = current.previous;
        if(head==current){
            head = head.next;
        }
        current = null;

        return  head;
    }

    static void doublyLinkedListOperations(){
        Node head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);

        head.next = second;
        second.previous = head;
        second.next = third;
        third.previous = second;

        System.out.print("============= Doubly Linked List : Traversal ========================\n");
        traversalForward(head);
        System.out.print("\n============= Doubly Linked List : Traversal Recursive ========================\n");
        traversalForwardRecursive(head);
        System.out.print("\n============= Doubly Linked List : Backward Traversal  ========================\n");
        traversalBackward(third);
        System.out.print("\n============= Doubly Linked List : Backward Traversal Recursive ========================\n");
        traversalBackwardRecursive(third);
        System.out.print("\n============= Doubly Linked List : Insert New Node ========================\n");
        traversalForward(insertAtPosition(head,4,89));
        System.out.print("\n============= Doubly Linked List : Delete Node ========================\n");
        traversalForward(deleteAtPosition(head,2));
    }


}

class CircularSinglyList extends SinglyLinkedList {

    static void traverseList(ListNode last){
        if(last==null){
            System.out.println("Not a valid list");
        }

        ListNode temp = last.next;
        while (true){
            System.out.print("->"+temp.data);
            temp = temp.next;
            if(temp==last.next){
                break;
            }
        }
    }

    static ListNode insertAtPosition(ListNode last, int data, int pos){
        if(last==null){
            System.out.println("Not a valid list");
            return null;
        }

        if (pos==1){
            ListNode newListNode = new ListNode(data);
            newListNode.next = last.next;
            last.next = newListNode;
            return last;
        }

        ListNode current = last.next;
        for(int i=1;i<pos-1;i++){
            current = current.next;
            if (current == last.next){ // This is the break condition of the loop.
                System.out.println("Not a valid position");
                return last;
            }
        }

        ListNode newListNode = new ListNode(data);
        newListNode.next = current.next;
        current.next = newListNode;


        if (current==last){
            last = newListNode;
        }

        return  last;
    }

    static ListNode deleteAtPosition(ListNode last, int deleteAt){

        if(last==null||deleteAt<1){
            System.out.println("Not a valid list or position...");
            return last;
        }

        ListNode current = last.next;
        if (deleteAt==1){
            last.next = current.next;
            current = null;
            return last;
        }

        for(int i=1;i<deleteAt-1;i++){
            current = current.next;
            if (current == last){ // This is the break condition of the loop.
                System.out.println("Not a valid position");
                return last;
            }
        }

        ListNode tobeDeleted = current.next;
        current.next = tobeDeleted.next;
        if (tobeDeleted==last) last = current;
        tobeDeleted = null;
        return  last;
    }

    static void circularSinglyLinkedListOperations(){
        ListNode first = new ListNode(1);
        first.next = new ListNode(2);
        first.next.next = new ListNode(3);
        first.next.next.next = new ListNode(4);
        first.next.next.next.next = new ListNode(5);

        ListNode last = first.next.next.next.next;
        last.next = first;

        System.out.print("\n============= Circular Singly Linked List ========================\n");

        traverseList(last);

        // Insert elements at specific positions
        int data = 6, pos = 6;
        System.out.print("\n============= Circular Singly Linked List : Insert At N Position ========================\n");
        last = insertAtPosition(last, data, pos);
        traverseList(last);

        System.out.print("\n============= Circular Singly Linked List : Delete At N Position ========================\n");
        traverseList(deleteAtPosition(last,6));
    }
}