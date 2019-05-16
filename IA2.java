package axl173530;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collections;

/**
 * @author: Ashish Lamichhane
 * @version: 1.0
 * @Date: 9/14/18
 * @projectname: IA2
 */
public class IA2  {

    public static<T extends Comparable<? super T>>
    void intersect(List<T> l1, List<T> l2, List<T> outList) {
        Iterator iterator1 = l1.listIterator();
        Iterator iterator2 = l2.listIterator();
        T x1 = (T)iterator1.next();
        T x2 = (T) iterator2.next();

        while (x1 != null && x2 !=null){
            if (((Integer)x1).compareTo(((Integer)x2)) < 0){
                x1 = (T)next(iterator1);
            } else if (((Integer)x2).compareTo(((Integer)x1)) < 0){
                x2 = (T) next(iterator2);
            } else {
                outList.add((T)x2);
                x1 = (T) next(iterator1);
                x2 = (T) next(iterator2);
            }
        }

    }

    private static Object next(Iterator iterator){
        return iterator.hasNext() ? iterator.next() : null;
    }

    public static<T extends Comparable<? super T>>
    void union(List<T> l1, List<T> l2, List<T> outList) {
        Iterator iterator1 = l1.iterator();
        Iterator iterator2 = l2.iterator();
        Object x1 = iterator1.next();
        Object x2 = iterator2.next();

        while (x1 != null && x2 != null){
            if (((Integer)x1).compareTo(((Integer)x2)) < 0 ){
                outList.add((T)x1);
                x1 = next(iterator1);
            } else if (((Integer)x2).compareTo(((Integer)x1)) < 0){
                outList.add((T)x2);
                x2 = next(iterator2);
            } else {
                outList.add((T) x2);
                x1 = next(iterator1);
                x2 = next(iterator2);
            }
        }

        while (x1 == null && x2 != null){
            outList.add((T)x2);
            x2 = next(iterator2);
        }

        while (x1 != null && x2 == null){
            outList.add((T)x1);
            x1 = next(iterator1);
        }
    }

    public static<T extends Comparable<? super T>>
    void difference(List<T> l1, List<T> l2, List<T> outList) {
        Iterator iterator1 = l1.iterator();
        Iterator iterator2 = l2.iterator();
        Object x1 = iterator1.next();
        Object x2 = iterator2.next();

        while (x1 != null && x2 != null){

            if (((Integer)x1).compareTo((Integer)x2) == 0){
                x1 = next(iterator1);
                x2 = next(iterator2);
            } else if (((Integer)x1).compareTo((Integer)x2) < 0){
                outList.add((T)x1);
                x1 = next(iterator1);
            }else{
                x2 = next(iterator2);
            }
        }
    }

    public static void  main (String args[]){
        List<Integer> l1 = new LinkedList<>();
        List<Integer> l2 = new LinkedList<>();
        List<Integer> output = new LinkedList<>();
        List<Integer> newoutput = new LinkedList<>();
        List<Integer> diffoutput = new LinkedList<>();
        Collections.sort(l1);
        Collections.sort(l2);

        int n = 10;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        for(int i=1; i<=n; i+=2) {
            l1.add(Integer.valueOf(i));
        }
        for(int i = 1; i <=n ; i +=3){
            l2.add(Integer.valueOf(i));
        }

        intersect(l1,l2,output);
        union(l1,l2,newoutput);
        difference(l1, l2, diffoutput);

        System.out.printf("Intersection is : %s  \n",output.toString());
        System.out.printf("Union is : %s    \n", newoutput.toString());
        System.out.printf("Difference is : %s   \n", diffoutput.toString());



    }
}
