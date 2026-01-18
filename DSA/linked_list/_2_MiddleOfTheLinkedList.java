package linked_list;

public class _2_MiddleOfTheLinkedList extends SinglyLinkedList {

    public ListNode middleNode(ListNode head) {
        int size=0;
        ListNode temp=head;
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

    public ListNode middleNodeFastAndSlowPointer(ListNode head) {
        ListNode slow=head,fast=head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode one = new ListNode(2);
        ListNode two = new ListNode(3);
        ListNode three = new ListNode(4);
        ListNode four = new ListNode(5);
        ListNode five = new ListNode(6);
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
