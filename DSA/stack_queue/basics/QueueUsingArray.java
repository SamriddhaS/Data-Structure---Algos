package DSA.stack_queue.basics;


/**
 * Operation : https://www.geeksforgeeks.org/dsa/introduction-to-circular-queue/
 * Problem Link: https://takeuforward.org/data-structure/implement-queue-using-array/
* */
public class QueueUsingArray {

    private int[] arr;
    private int front,rear, size;
    private int maxSize;

    QueueUsingArray(int size){
        this.maxSize = size;
        arr = new int[maxSize];
        front=0;
        rear=0;
        size=0;
    }

    // Get the front element
    public int getFront() {
        if (size==0){
            System.out.println("Empty Queue");
            return -1;
        }
        return arr[front];
    }

    // Get the rear element
    public int getRear() {
        if (size==0){
            System.out.println("Empty Queue");
            return -1;
        }
        return arr[rear];
    }

    // Insert an element at the rear
    public void enqueue(int x) {
        if (size==maxSize){
            System.out.println("Queue is full");
            return;
        }
        rear = ((front+size)%maxSize); // calculate rear and increment by 1 to add the new item.
        arr[rear] = x;
        size++;
    }

    // Remove an element from the front
    public int dequeue() {
        if (size==0){
            System.out.println("Queue is empty");
            return -1;
        }
        int res = arr[front];
        front = (front+1)%maxSize;
        size--;
        return res;
    }

    public static void main(String[] args) {
        QueueUsingArray q = new QueueUsingArray(4);
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
