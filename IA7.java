package axl173530;

/**
 * @author: Ashish Lamichhane
 * @version: 1.0
 * @Date: 11/1/18
 * @projectname: IA7 Problem 1
 */
import java.util.Arrays;
import java.util.PriorityQueue;


/**
 * Observation : The select method with priority queue is faster and efficient than the sortandSelect method.
 */
public class IA7 {
    /**
     * Selects the kth largest element of the array arr.
     * Import all k elements into the priority queue. Iterate through the remaining elements in the array
     * and check if the element is bigger than the smallest element in the queue. If true, remove the element
     * from the queue and add the element from array to the queue.
     * Notes: Timer from SkipListDriver class is used to calculate the runtime.
     * @param arr : array from which the kth largest element is to be found.
     * @param k : the value of k.
     * @return queue.peek() which will be the kth largest element of the array.
     */
    public static int select(int[] arr, int k) {
        // Initialize the timer.
        Timer timer = new Timer();
        assert (arr != null && arr.length > 0 && k > 0 && k <= arr.length);
        PriorityQueue<Integer> queue = new PriorityQueue <>();
       //Fill queue with k largest elements seen so far.
        for(int i = 0 ; i < k ; i++){
            queue.add(arr[i]);
        }

        int x; // holds the element of the array.
        for (int i = k ; i < arr.length; i++){ //iterate through the remaining elements in the array.
            x=arr[i];
            // if an element greater than the smallest element in the priority queue is found
            // then swap it with the new found element from the array.
            if (queue.peek().compareTo(x) < 0){
                queue.remove();
                queue.add(x);
            }
        }
        // End Time
        timer.end();
        System.out.println(timer);
        return queue.peek();
    }

    /**
     * Sort the array before returning the kth largest element.
     * @param arr : array from where the kth largest element is to be found.
     * @param k : value of the k.
     * @return : kth largest element in the array.
     */

    public static int sortandSelect(int[] arr, int k ){
        Timer timer = new Timer();
        Arrays.sort(arr);   // sort the array.
        int r = arr[arr.length-k];  // extract the kth largest element.
        timer.end();
        System.out.println(timer);
        return r;

    }

    public static void main(String[] args)  {
        int n = (int) (Math.random()*1000000-100) + 100; // randomly generating the size of the array.
        int[] arr = new int[n];
        for(int i = 0; i <  arr.length; i++) {
            arr[i] = (int) (Math.random() * n); // random elements in the array.
        }
        int k = 10;     // finding the fixed kth largest element.
        System.out.println("Using the priority queue...");
        System.out.printf("The %dth largest element is : %d \n",k,IA7.select(arr,9));
        System.out.println("\nUsing select and sort... ");
        System.out.printf("The %dth largest element is : %d\n",k,IA7.sortandSelect(arr,9));

      }
    /** Timer class for roughly calculating running time of programs
     *  @author rbk
     *  Usage:  Timer timer = new Timer();
     *          timer.start();
     *          timer.end();
     *          System.out.println(timer);  // output statistics
     */

    public static class Timer {
        long startTime, endTime, elapsedTime, memAvailable, memUsed;

        public Timer() {
            startTime = System.currentTimeMillis();
        }

        public void start() {
            startTime = System.currentTimeMillis();
        }

        public Timer end() {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            memAvailable = Runtime.getRuntime().totalMemory();
            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
            return this;
        }

        public String toString() {
            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
        }
    }



    }



