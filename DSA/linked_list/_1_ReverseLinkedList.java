package DSA.linked_list;

public class _1_ReverseLinkedList extends SinglyLinkedList{
    
    public Node reverseBetween(Node head, int left, int right) {

        if(head==null||head.next==null||left==right) return head;

        Node dummy = new Node(Integer.MIN_VALUE);
        dummy.next = head;

        Node PRE=dummy,CURRENT=null,NEXT=null;
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

    public Node reverseBetweenSingleLoop(Node head, int left, int right) {

        if(head==null||head.next==null||left==right) return head;

        Node dummy = new Node(Integer.MIN_VALUE);
        dummy.next = head;

        Node PRE=dummy,CURRENT=null,NEXT=null;
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
        _1_ReverseLinkedList object = new _1_ReverseLinkedList();
        traverseList(object.reverseBetween(head,3,5));
        traverseList(object.reverseBetweenSingleLoop(head,3,5));
    }
}
