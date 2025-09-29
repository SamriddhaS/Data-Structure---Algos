package stack_queue.basics;


/**
 * Operation : https://www.geeksforgeeks.org/dsa/priority-queue-set-1-introduction/
 *
* */
public class PriorityQueueUsingArray {

    QueueItem[] items;
    int size;

    public PriorityQueueUsingArray(int size){
        items = new QueueItem[size];
        this.size = 0;
    }

    public boolean enqueue(int value,int priority){
        if (size==items.length){
            System.out.println("Queue is full");
            return false;
        }
        QueueItem item = new QueueItem(value,priority);
        size++;
        items[size-1] = item;
        return true;
    }

    public int findPeek(){
        int index=0;
        int maxValue=items[index].priority;
        for (int i=1;i<size;i++){
            if (items[i].priority>maxValue ||
                    (items[i].priority==maxValue && items[i].value>items[index].value) // if same priority them choose the one with bigger value
            ){
                index = i;
                maxValue = items[i].priority;
            }
        }
        return index;
    }

    public boolean dequeue(){
        if (size==0){
            System.out.println("Its empty queue");
            return false;
        }
        int peek = findPeek();
        items[peek] = items[size-1];
        size--;
        return true;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        PriorityQueueUsingArray pq = new PriorityQueueUsingArray(10);
        pq.enqueue(10, 2);
        pq.enqueue(14, 4);
        pq.enqueue(16, 4);
        pq.enqueue(12, 3);

        System.out.println(pq.items[pq.findPeek()].value);
        pq.dequeue();

        System.out.println(pq.items[pq.findPeek()].value);
        pq.dequeue();

        System.out.println(pq.items[pq.findPeek()].value);
    }

}

class QueueItem{
    int value,priority;
    QueueItem(int val,int priority){
        this.value = val;
        this.priority = priority;
    }
}
