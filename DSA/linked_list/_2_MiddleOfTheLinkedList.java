package DSA.linked_list;

public class _2_MiddleOfTheLinkedList extends SinglyLinkedList {

    public Node middleNode(Node head) {
        int size=0;
        Node temp=head;
        while(temp!=null){
            temp = temp.next;
            size++;
        }
        int halfPoint = 1;
        while(halfPoint!=(size/2)+1){
            head = head.next;
            halfPoint++;
        }
        return head;
    }

    public Node middleNodeFastAndSlowPointer(Node head) {
        Node slow=head,fast=head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node one = new Node(2);
        Node two = new Node(3);
        Node three = new Node(4);
        Node four = new Node(5);
        Node five = new Node(6);
        head.next = one;
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        traverseList(head);
        _2_MiddleOfTheLinkedList object = new _2_MiddleOfTheLinkedList();
        traverseList(object.middleNode(head));
        traverseList(object.middleNodeFastAndSlowPointer(head));
    }

}
