package DSA.stack_queue;


import java.util.LinkedList;

/**
 *
 * Theory : https://www.geeksforgeeks.org/dsa/implementation-deque-using-doubly-linked-list/
* */
public class DeQueueUsingLL {

    private LinkedList<Integer> deque;

    DeQueueUsingLL(int size){
        deque = new LinkedList<>();
    }

    public boolean isEmpty() { return deque.isEmpty(); }
    public int getSize() { return deque.size(); }

    public void insertFront(int data) {
        deque.addFirst(data);
    }

    public void insertRear(int data) {
        deque.addLast(data);
    }

    public void deleteFront() {
        if (isEmpty()) System.out.println("UnderFlow");
        else deque.removeFirst();
    }

    public void deleteRear() {
        if (isEmpty()) System.out.println("UnderFlow");
        else deque.removeLast();
    }

    public int getFront() { return isEmpty() ? -1 : deque.getFirst(); }
    public int getRear() { return isEmpty() ? -1 : deque.getLast(); }

    public void clear() { deque.clear(); }

    public static void main(String[] args) {

        // Create deque with capacity 4
        DeQueueUsingLL dq = new DeQueueUsingLL(4);
        dq.insertRear(5);
        dq.insertRear(10);
        System.out.println("Rear: " + dq.getRear());
        dq.deleteRear();
        System.out.println("New Rear: " + dq.getRear());

        dq.insertFront(15);
        System.out.println("Front: " + dq.getFront());
        System.out.println("Size: " + dq.getSize());

        dq.deleteFront();
        System.out.println("New Front: " + dq.getFront());

    }
}
