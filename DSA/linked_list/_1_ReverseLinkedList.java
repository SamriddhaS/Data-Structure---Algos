package linked_list;

public class _1_ReverseLinkedList extends SinglyLinkedList{
    
    public ListNode reverseBetween(ListNode head, int left, int right) {

        if(head==null||head.next==null||left==right) return head;

        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        ListNode PRE=dummy,CURRENT=null,NEXT=null;
        int i=0;
        while(i<left-1){
            PRE = PRE.next;
            i++;
        }

        CURRENT = PRE.next;
        for (int j=left;j<right;j++){
            NEXT = CURRENT.next;
            CURRENT.next = NEXT.next;
            NEXT.next = PRE.next;
            PRE.next = NEXT;
        }
        
        return dummy.next;
    }

    public ListNode reverseBetweenSingleLoop(ListNode head, int left, int right) {

        if(head==null||head.next==null||left==right) return head;

        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;

        ListNode PRE=dummy,CURRENT=null,NEXT=null;
        for (int i=0;i<right;i++){
            if(i<left-1){
                PRE = PRE.next;
            }else if (i == left - 1) {
                CURRENT = PRE.next;
            }else {
                NEXT = CURRENT.next;
                CURRENT.next = NEXT.next;
                NEXT.next = PRE.next;
                PRE.next = NEXT;
            }
        }

        return dummy.next;
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
        _1_ReverseLinkedList object = new _1_ReverseLinkedList();
        traverseList(object.reverseBetween(head,3,5));
        traverseList(object.reverseBetweenSingleLoop(head,3,5));
    }
}
