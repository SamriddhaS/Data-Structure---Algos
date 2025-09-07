package DSA.stack_queue.basics;


/**
 * Theory : https://www.geeksforgeeks.org/dsa/implementation-deque-using-circular-array/
 *
* */
public class DeQueueUsingArray {

    private int[] arr;
    private int front,rear, size;
    private int maxSize;

    DeQueueUsingArray(int size){
        this.maxSize = size;
        arr = new int[maxSize];
        front=0;
        rear=-1;
        this.size=0;
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
    public void enqueueRear(int x) {
        if (size==maxSize){
            System.out.println("Queue is full");
            return;
        }
        rear = ((rear+1)%maxSize); // calculate rear and increment by 1 to add the new item.
        arr[rear] = x;
        size++;
    }

    public void enqueueFront(int x) {
        if (size==maxSize){
            System.out.println("Queue is full");
            return;
        }
        front = (front - 1 + maxSize) % maxSize;
        arr[front] = x;
        size++;
    }

    // Remove an element from front
    public int dequeueFront() {
        if (size==0){
            System.out.println("Queue is empty");
            return -1;
        }
        int res = arr[front];
        front = (front+1)%maxSize;
        size--;
        return res;
    }

    //Remove element from rear
    public int dequeueRear() {
        if (size==0){
            System.out.println("Queue is empty");
            return -1;
        }
        int res = arr[rear];
        rear = (rear - 1 + maxSize) % maxSize; // calculate rear and increment by 1 to add the new item.
        size--;
        return res;
    }


    public static void main(String[] args) {
        // Create deque with capacity 4
        DeQueueUsingArray dq = new DeQueueUsingArray(4);

        // Insert at rear
        dq.enqueueRear(10);
        System.out.println(dq.getFront() + " "
                + dq.getRear());

        // Insert at front
        dq.enqueueFront(20);
        System.out.println(dq.getFront() + " "
                + dq.getRear());
        dq.enqueueFront(30);
        System.out.println(dq.getFront() + " "
                + dq.getRear());

        // Delete from rear
        dq.dequeueRear();
        System.out.println(dq.getFront() + " "
                + dq.getRear());
        dq.enqueueRear(40);
        System.out.println(dq.getFront() + " "
                + dq.getRear());
        dq.dequeueRear();
        System.out.println(dq.getFront() + " "
                + dq.getRear());
        dq.dequeueFront();
        System.out.println(dq.getFront() + " "
                + dq.getRear());
        dq.dequeueRear();
        System.out.println(dq.getFront() + " "
                + dq.getRear());
    }
}
