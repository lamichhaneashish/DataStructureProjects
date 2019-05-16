package axl173530;

import java.util.*;

/**
 * @author: Ashish Lamichhane
 * @version: <Enter the version>
 * @Date: 10/6/18
 * @projectname: Project
 */
public class IA6_3 {
    public static void main(String... args) {
        int[] A = {1,7,9,4,1,7,4,8,7,1,10,9,7};
        longestStreak(A);


    }

    /**
     * calculates the length of longest streak of consecutive integers that occur in A
     * Adds the elements in the array to set.
     * For element in the set checks if the next element is an increment of 1
     * compared to the previous element, if it is then increment the counter of consecutive
     * element found (current).
     * Keep track of the maximum consecutive elements found in the separate variable called
     * max. if the current length is larger than max then replace
     * max with current.
     * Max is the length of longest streak of consecutive integers.
     * @param A array of an integer
     */
    static void longestStreak(int[] A) {
        int prev;
        int max ;
        int current;
        HashSet<Integer> s = new HashSet <>();
        for (int x : A){
            s.add(x);
        }
        prev = s.iterator().next();
        max = 1;
        current = 0;
        for (int i : s){
          if (prev+1 == i){
              current ++;      // checking if the elements are consecutive as in the natural order.
          }else{
              current = 1;
          }
          prev = i;
        if (current > max){
            max = current;  // change the value of max if new max found.
        }
        }

        System.out.println(max);
    }
}

