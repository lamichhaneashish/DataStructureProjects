package axl173530;
import java.util.Map;
import java.util.TreeMap;

public class IA31 {

    public static void main(String... args) {
        int[] a = {3,3,4,5,3,5};
        System.out.printf("Total is : %d \n", howMany(a,8));
    }

    /**
     * TreeMap stores the data and its occurrences.
     * If the X-key is in the tree map and if key doesn't match with X-key
     * then the program multiplies values of two entries to find the total
     * pair of elements that sum to X.
     * @param A : Integer array that contains the data to be checked.
     * @param X : Sum that is being checked with.
     * @return : total number of pairs of elements of A that sum to X.
     */

    static int howMany(int[] A, int X) {
        int total = 0;
        TreeMap<Integer, Integer> map = copies(A);
        for(Map.Entry <Integer,Integer> entry : map.entrySet()){
            Integer key = entry.getKey();
            if (map.containsKey(X-key) && key != X- key){
                total = entry.getValue() * map.get(X-key);
            }
        }
        return total;
    }

    /**
     * Calculates the number of copies of any specific data and puts it into TreeMap where 
     * key : number and value : # of copies.
     * @param A : integer array that contains the data.
     * @return TreeMap containing data as key and # of copies as value.
     */
    private static TreeMap<Integer, Integer> copies(int[] A){
        Map<Integer,Integer> map = new TreeMap<>();
        for (int a : A){
            Integer data = map.get(a);
            map.put(a, (data == null) ? 1: data+1);
        }
        return (TreeMap<Integer, Integer>) map;
    }
}

