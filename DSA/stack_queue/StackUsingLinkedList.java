package DSA.stack_queue;


import DSA.linked_list.SinglyLinkedList;

class LLStack {

    SinglyLinkedList.Node head;

    LLStack(){
        head = null;
    }

    public boolean push(int value){
        SinglyLinkedList.Node newNode = new SinglyLinkedList.Node(value);
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
