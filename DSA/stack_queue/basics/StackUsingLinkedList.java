package stack_queue.basics;


import linked_list.SinglyLinkedList;

/**
 * Theory : https://www.geeksforgeeks.org/dsa/implement-a-stack-using-singly-linked-list/
 * Problem Link : https://takeuforward.org/data-structure/implement-stack-using-linked-list/
* */
class LLStack {

    SinglyLinkedList.ListNode head;

    LLStack(){
        head = null;
    }

    public boolean push(int value){
        SinglyLinkedList.ListNode newNode = new SinglyLinkedList.ListNode(value);
        newNode.next = head;
        head = newNode;
        return true;
    }

    public boolean pop(){
        if (isEmpty()){
            System.out.println("Stack UnderFlow");
            return false;
        }
        head = head.next;
        return true;
    }

    public int peek() {
        return head.data;
    }

    public boolean isEmpty() {
        return head==null;
    }


    public static void main(String[] args) {
        LLStack s = new LLStack();
        s.push(11);
        s.push(22);
        s.push(33);
        s.push(44);

        System.out.println(s.peek());

        s.pop();
        s.pop();

        System.out.println(s.peek());
    }
}
