package DSA.linked_list;

import javax.swing.*;

public class _4_RemoveNthFromEnd extends SinglyLinkedList {

    public Node removeNthFromEnd(Node head, int n) {

        // Empty / not a vaild list
        if(head==null){
            return head;
        }

        // Find out the size of the list
        Node temp = head;
        int size=0;
        while(temp!=null){
            temp = temp.next;
            size++;
        }

        // Check if the position we are trying to delete is valid one.
        if(n>size||n<=0){
            System.out.println("Not a valid position to delete");
            return head;
        }

        // Determine the position to be deleted
        int pointTobeDeleted = (size - n);

        // If deleting 1st node : Head needs to be readjusted.
        if(pointTobeDeleted==0){
            temp = head;
            head = head.next;
            temp = null;
            return head;
        }

        // Determine the node previous to one we are trying to delete.
        size=1;
        Node pre = head;
        while(size<pointTobeDeleted){
            pre = pre.next;
            size++;
        }

        // Change the pointer of the previous node. Then delete the node.
        Node toBeDeletedNode = pre.next;
        pre.next = toBeDeletedNode.next;
        toBeDeletedNode = null;

        return head;
    }

    public Node removeNthFromEndFastAndSlowPointers(Node head, int n) {

        // Empty / not a vaild list
        if(head==null){
            return head;
        }

        if(n<=0){
            return head;
        }

        // Find out the size of the list
        Node dummy = new Node(Integer.MIN_VALUE);
        dummy.next = head;
        Node slow = dummy;
        Node fast = head;

        int i=1;
        while (i<=n&&fast!=null){
            fast = fast.next;
            i++;
        }

        // If fast is null and we haven't moved n steps, n is larger than list size
        if (fast == null && i <= n) {
            System.out.println("\nInvalid Position");
            return head; // Invalid n, return original list
        }

        while(fast!=null){
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node one = new Node(2);
        Node two = new Node(3);
        Node three = new Node(4);
        Node four = new Node(5);
        Node five = new Node(6);
        Node six = new Node(7);
        head.next = one;
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        Node head1 = new Node(1);
        Node one1 = new Node(2);
        Node two1 = new Node(3);
        head1.next = one1;
        one1.next = two1;
        _4_RemoveNthFromEnd object = new _4_RemoveNthFromEnd();
//        traverseList(head);
//        traverseList(object.removeNthFromEnd(head,7));
//        traverseList(head1);
//        traverseList(object.removeNthFromEnd(head1,1));

        traverseList(head);
        traverseList(object.removeNthFromEndFastAndSlowPointers(head,7));
        //traverseList(head1);
        //traverseList(object.removeNthFromEndFastAndSlowPointers(head1,1));

    }

}
