package DSA.stack_queue;


import DSA.linked_list.SinglyLinkedList;

/**
 *
 * Theory : https://www.geeksforgeeks.org/dsa/queue-linked-list-implementation/
 *
* */
public class QueueUsingLL {

    SinglyLinkedList.Node front,rear;

    QueueUsingLL(int size){
        front=null;
        rear=null;
    }

    // Get the front element
    public int getFront() {
        if(front==null){
            System.out.println("Empty Queue");
            return -1;
        }
        return front.data;
    }

    // Get the rear element
    public int getRear() {
        if(rear==null){
            System.out.println("Empty Queue");
            return -1;
        }
        return rear.data;
    }

    // Insert an element at the rear
    public void enqueue(int x) {
        SinglyLinkedList.Node newNode = new SinglyLinkedList.Node(x);
        if (rear==null){
            front=rear=newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    // Remove an element from the front
    public int dequeue() {
        if (front==null){
            System.out.println("Its an empty queue.");
            return -1;
        }
        int deletedFront = front.data;
        front = front.next;
        return deletedFront;
    }

    public static void main(String[] args) {
        QueueUsingLL q = new QueueUsingLL(4);
        q.enqueue(10);
        System.out.println(q.getFront() + " " + q.getRear());
        q.enqueue(20);
        System.out.println(q.getFront() + " " + q.getRear());
        q.enqueue(30);
        System.out.println(q.getFront() + " " + q.getRear());
        q.enqueue(40);
        System.out.println(q.getFront() + " " + q.getRear());
        q.dequeue();
        System.out.println(q.getFront() + " " + q.getRear());
        q.dequeue();
        System.out.println(q.getFront() + " " + q.getRear());
        q.enqueue(50);
        System.out.println(q.getFront() + " " + q.getRear());
    }
}
