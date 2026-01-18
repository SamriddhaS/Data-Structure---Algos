package linked_list;

public class _3_MergeTwoSortedList extends SinglyLinkedList {

    /**
     * 1st Solution
    * */
    public ListNode mergeTwoListExtraSpace(ListNode list1, ListNode list2) {
        if(list1==null&&list2==null) return null;
        if(list1 == null) return list2;
        if(list2 == null) return list1;

        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode mergedList = dummy;

        while(list1!=null || list2!=null){
            if (list1==null){
                mergedList.next = new ListNode(list2.data);
                list2 = list2.next;
            } else if (list2 == null){
                mergedList.next = new ListNode(list1.data);
                list1 = list1.next;
            }else if (list1.data<list2.data){
                mergedList.next = new ListNode(list1.data);
                list1 = list1.next;
            }else if (list2.data<list1.data) {
                mergedList.next = new ListNode(list2.data);
                list2 = list2.next;
            }else {
                mergedList.next = new ListNode(list1.data);
                mergedList = mergedList.next;
                mergedList.next = new ListNode(list2.data);
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
    public ListNode mergeTwoListsInPlace(ListNode list1, ListNode list2) {
        if(list1==null&&list2==null) return null;
        if(list1 == null) return list2;
        if(list2 == null) return list1;

        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode mergedList = dummy;

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
    public ListNode mergeTwoListsInPlaceLessConditions(ListNode list1, ListNode list2) {

        if(list1==null&&list2==null) return null;
        if(list1 == null) return list2;
        if(list2 == null) return list1;

        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode tail = dummy;

        while(list1!=null&&list2!=null){

            // compare the l1 vs l2 nodes value and rearrange the currentNode
            if (list1.data<list2.data){
                tail.next = list1;
                list1 = list1.next;
            }else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        // If remaining values there just append the rest
        if(list1==null){
            tail.next = list2;
        }

        if(list2==null){
            tail.next = list1;
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
        ListNode head1 = new ListNode(1);
        ListNode one1 = new ListNode(2);
        ListNode two1 = new ListNode(3);
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
