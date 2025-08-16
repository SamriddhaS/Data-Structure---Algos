package DSA.linked_list;

public class _3_MergeTwoSortedList extends SinglyLinkedList {

    /**
     * 1st Solution
    * */
    public Node mergeTwoListExtraSpace(Node list1, Node list2) {
        if(list1==null&&list2==null) return null;
        if(list1 == null) return list2;
        if(list2 == null) return list1;

        Node dummy = new Node(Integer.MIN_VALUE);
        Node mergedList = dummy;

        while(list1!=null || list2!=null){
            if (list1==null){
                mergedList.next = new Node(list2.data);
                list2 = list2.next;
            } else if (list2 == null){
                mergedList.next = new Node(list1.data);
                list1 = list1.next;
            }else if (list1.data<list2.data){
                mergedList.next = new Node(list1.data);
                list1 = list1.next;
            }else if (list2.data<list1.data) {
                mergedList.next = new Node(list2.data);
                list2 = list2.next;
            }else {
                mergedList.next = new Node(list1.data);
                mergedList = mergedList.next;
                mergedList.next = new Node(list2.data);
                list1 = list1.next;
                list2 = list2.next;
            }
            mergedList = mergedList.next;
        }

        return dummy.next;
    }

    /**
     * 2st Solution
     * */
    public Node mergeTwoListsInPlace(Node list1, Node list2) {
        if(list1==null&&list2==null) return null;
        if(list1 == null) return list2;
        if(list2 == null) return list1;

        Node dummy = new Node(Integer.MIN_VALUE);
        Node mergedList = dummy;

        while(list1!=null || list2!=null){
            if (list1==null){
                mergedList.next = list2;
                list2 = list2.next;
            } else if (list2 == null){
                mergedList.next = list1;
                list1 = list1.next;
            }else if (list1.data<list2.data){
                mergedList.next = list1;
                list1 = list1.next;
            }else if (list2.data<list1.data) {
                mergedList.next = list2;
                list2 = list2.next;
            }else {
                mergedList.next = list1;
                mergedList = mergedList.next;
                list1 = list1.next;

                mergedList.next = list2;
                mergedList = mergedList.next;
                list2 = list2.next;
                continue;
            }
            mergedList = mergedList.next;
        }

        return dummy.next;
    }

    /**
     * 3st Solution : More concise & better time complexity
     * */
    public Node mergeTwoListsInPlaceLessConditions(Node list1, Node list2) {

        if(list1==null&&list2==null) return null;
        if(list1 == null) return list2;
        if(list2 == null) return list1;

        Node dummy = new Node(Integer.MIN_VALUE);
        Node mergedList = dummy;

        while(list1!=null&&list2!=null){

            // compare the l1 vs l2 nodes value and rearrange the currentNode
            if (list1.data<list2.data){
                mergedList.next = list1;
                list1 = list1.next;
            }else {
                mergedList.next = list2;
                list2 = list2.next;
            }
            mergedList = mergedList.next;
        }

        // If remaining values there just append the rest
        if(list1==null){
            mergedList.next = list2;
        }

        if(list2==null){
            mergedList.next = list1;
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
        Node head1 = new Node(1);
        Node one1 = new Node(2);
        Node two1 = new Node(3);
        head1.next = one1;
        one1.next = two1;
        traverseList(head);
        traverseList(head1);
        _3_MergeTwoSortedList object = new _3_MergeTwoSortedList();
        traverseList(object.mergeTwoListExtraSpace(head,head1));
        traverseList(object.mergeTwoListsInPlace(head,head1));
        traverseList(object.mergeTwoListsInPlaceLessConditions(head,head1));
    }

}
