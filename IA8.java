package axl173530;

/**
 * @author: Ashish Lamichhane
 * @version: <Enter the version>
 * @Date: 11/10/18
 * @projectname: Project
 */
public class IA8{
    /**
     * calls helper function merge sort to sort the given array using divide
     * and conquer.
     * @param arr : array to be sorted.
     */
    void mergeSort(int[] arr){
          int[] temp = new int[arr.length];
          mergeSort(arr,temp,0,arr.length-1);

    }

    /**
     * uses recursion to divide the given array before merging them.
     * @param arr : array to be sorted
     * @param tmp : temporary array
     * @param left : left starting point.
     * @param right : right ending point.
     */
    void mergeSort(int[] arr, int[]tmp, int left, int right){
        int mid;
        if (right >left){
            mid = (left+right)/2;
            mergeSort(arr,tmp,left,mid);
            mergeSort(arr,tmp,mid+1,right);
            merge(arr,tmp,left,mid+1,right);
        }

    }


    /**
     *
     * @param arr : array to be sorted.
     * @param tmp : temporary array.
     * @param left_start : left pointer
     * @param right_start : right pointer.
     * @param right_end : end of the array.
     */
    void merge(int[]arr, int[]tmp, int left_start, int right_start, int right_end){
        int left_pointer = left_start ;
        int right_pointer = right_start;
        int temp_pointer = left_start;


        while (left_pointer < right_start && right_pointer <= right_end){
            if (arr[left_pointer] <= arr[right_pointer]){
                tmp[temp_pointer++] = arr[left_pointer++];
            }else{
                tmp[temp_pointer++] = arr[right_pointer++];
            }
        }

        /** One of the two subarrays  has run out of elements.
         * copying the remaining elements into temp.
         */
        while (left_pointer <right_start){
            tmp[temp_pointer++] = arr[left_pointer++];
        }
        while (right_pointer <= right_end){
            tmp[temp_pointer++] = arr[right_pointer++];
        }

        /** copying temp back to the original array **/
        System.arraycopy(tmp,left_start,arr,left_start,right_end-left_start+1);
    }

    /**
     * Insertion sort.
     * @param arr
     */
    void insertionSort(int[] arr){
        int temp,j;
        for (int i = 1 ; i < arr.length ; i++){ //LI : arr[p..i-1] is sorted.
            temp = arr[i];
            j = i-1;
            /** find place for arr[i] in sorted subarray[p..i-1] **/
            while (j>= 0 && temp <arr[j]){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
    }

    public static  void main(String[] args){
        int[] arr = new int[512000];
        for (int i = 0 ;i < arr.length ; i ++){
            arr[i] = (int) (Math.random()*1000000+100);
        }
        IA8 tester = new IA8();
        Timer timer = new Timer();
        tester.mergeSort(arr);
        timer.end();
        System.out.println(timer);
//        Timer timer1 = new Timer();
//        tester.insertionSort(arr);
//        timer1.end();
//        System.out.println(timer1);
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