package axl173530;

/**
 * @author: Ashish Lamichhane
 * @version: 1.0
 * @Date: 9/14/18
 * @projectname: IA2
 */

public class BoundedQueue<T> {

    private static int capacity;
    private static int front, back;
    private Object[] queue;
    private int count;

    // creates a bounded queue with maximum capacity.
    public BoundedQueue(int capacity) {
        BoundedQueue.capacity = capacity;
        queue = new Object[capacity];
        front = back = 0;
    }

    /**
     * add a new element x at the rear of the queue
     * returns false if the element was not added because the queue is full 
     **/
    public boolean offer(T x) {
        if (!isFull()) {
            if (back == capacity) {
                for (int i = 0; i < queue.length; i++) {
                    if (queue[i] == null) {
                        back = i;
                        break;
                    }
                }
                addToBack(x);
                return true;
            } else {
                addToBack(x);
                return true;
            }

        } else {
            System.out.println("Queue is full.. pop items before adding !!");
            return false;
        }

    }

    // helper function to check if the queue is full.
    private boolean isFull() {
        return size() == capacity;
    }

    // helper function to add at the back of the queue.
    private void addToBack(T x) {
        queue[back] = x;
        back++;
        count++;
    }

    //remove and return the element at the front of the queue
//return null if the queue is empty
    public T poll() {
        checkFront();
        if (!isEmpty()) {
            T x = (T) queue[front];
            queue[front] = null;
            front++;
            count--;
            return x;
        } else {
            return null;
        }

    }

    //return front element, without removing it (null if queue is empty)
    public T peek() {
        checkFront();
        if (isEmpty()) {
            return null;
        } else {
            return (T) queue[front];
        }

    }

    // checks if front reaches end of the queue and sets it zero if it did.
    private void checkFront() {
        if (front == capacity) {
            front = 0;
        }
    }

    //return the number of elements in the queue
    public int size() {
        return count;
    }

    //check if the queue is empty
    public boolean isEmpty() {
        return this.size() == 0;
    }

    //clear the queue (size=0)
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            queue[i] = null;
        }
        back = front = 0;
        count = 0;
    }

    //fill user supplied array with the elements of the queue, in queue order
    public void toArray(T[] a) {
        for (int i = 0; i < queue.length; i++) {
            a[i] = peek();
            front++;
        }

        System.out.print("New Array is : ");
        for (T x : a) {
            System.out.printf("%d ", x);
        }

    }

    public void printQueue() {
        for (Object x : queue) {
            System.out.printf("%d ", x);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        BoundedQueue<Integer> q = new BoundedQueue<>(8);
        for (int i = 1; i <= capacity; i++) {
            q.offer(i);
        }
        System.out.printf("Is queue empty ? %b \n", q.isEmpty());
        System.out.print("Queue is : ");
        q.printQueue();
        q.offer(10);
        q.poll();
        q.poll();
        q.offer(100);
        q.printQueue();
        System.out.println(q.peek());

        System.out.print("Queue is : ");
        q.printQueue();

        Integer[] a = new Integer[capacity];
        q.toArray(a);
        System.out.println();


    }
}
