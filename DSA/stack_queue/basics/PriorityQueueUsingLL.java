package stack_queue.basics;


/**
 * Operation : https://www.geeksforgeeks.org/dsa/priority-queue-using-linked-list/
 *
 * Time Complexity :
 *
 * enqueue() :  O(n)
 * dequeue() :  O(1)
 * peek() : O(1)
 *
* */
public class PriorityQueueUsingLL {

    PriorityQueueNode head;

    public PriorityQueueUsingLL(){

    }

    int peek(){
        if (head==null){
            System.out.println("Empty Queue");
            return -1;
        }
        return  head.value;
    }

    PriorityQueueNode dequeue(){
        if (head==null){
            System.out.println("Empty Queue");
            return null;
        }
        head = head.next;
        return head;
    }

    PriorityQueueNode enqueue(int value,int priority){

        PriorityQueueNode newNode = new PriorityQueueNode(value,priority);

        if (head==null||head.priority<priority){
            //This is the first element to be inserted in the queue
            newNode.next = head;
            head = newNode;
            return head;
        }

        PriorityQueueNode temp = head;

        while(temp.next!=null && temp.next.priority>=priority){
            temp = temp.next;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        return head;
    }

    int isEmpty() {
        return (head == null)?1:0;
    }

    public static void main(String[] args) {
        PriorityQueueUsingLL pq = new PriorityQueueUsingLL();
        pq.enqueue(4, 1);
        System.out.printf("%d ", pq.peek());
        pq.enqueue(5, 2);
        System.out.printf("%d ", pq.peek());
        pq.enqueue( 6, 3);
        System.out.printf("%d ", pq.peek());
        pq.enqueue( 7, 0);
        System.out.printf("%d ", pq.peek());
        pq.enqueue( 9, 5);
        System.out.printf("%d ", pq.peek());
        pq.dequeue();
        System.out.printf("%d ", pq.peek());
        pq.dequeue();
        System.out.printf("%d ", pq.peek());
        pq.dequeue();
        System.out.printf("%d ", pq.peek());
        pq.dequeue();
        System.out.printf("%d ", pq.peek());
        pq.enqueue(6, 2);
        pq.enqueue(11, 7);
        pq.enqueue(5, 5);
        System.out.printf("%d ", pq.peek());

    }

}

class PriorityQueueNode{
    int value,priority;
    PriorityQueueNode next;
    PriorityQueueNode(int val,int priority){
        this.value = val;
        this.priority = priority;
        this.next = null;
    }
}
