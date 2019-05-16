/**
 * @author: Ashish Lamichhane
 * @version: 1.0
 * @Date: 10/19/2018
 * @projectname: SkipList Project 2
 */
package axl173530;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class SkipList<T extends Comparable<? super T>> {
    static final int PossibleLevels = 33;   // total number of possible levels.

    /**
     * Entry class stores the element, its previous entry as well as the array of entries called next
     * that points to other entries at different levels.
     * @param <E>
     */
    class Entry<E> {
        E element;
        Entry <T>[] next;
        Entry prev;

        /**
         * Constructor to create entry with element x and number of levels = lev.
         * @param x : element
         * @param lev   : number of levels.
         */
        public Entry(E x, int lev) {
            element = x;
            next = new Entry[lev];
        }

        /**
         * Getter method for element.
         * @return the element of the entry.
         */
        public E getElement() {
            return element;
        }
    }

    Entry head, tail;       /**dummy head and tail of the SkipList.*/
    int size, maxLevel;     /** size of the skiplist and number of maximum level upto this point on skiplist.*/
    Entry[] last;           /** used by find to store the entry where search ended.*/
    Random random;          /** random number generator for generating levels randomly.*/


    /**
     * Default constructor of the skiplist.
     */
    public SkipList() {
        head = new Entry(null, PossibleLevels); // dummy head creation.
        tail = new Entry(null, PossibleLevels); // dummy tail creation.
        size = 0;
        maxLevel = 1;
        last = new Entry[PossibleLevels];           // array creation to store the entry where search ended.
        random = new Random();
        /**
         * Connecting all levels in the head to tail to avoid NPE.
         * Initializing all entries in the last array to head entry to avoid NPE.
         */
        for (int i = 0; i < PossibleLevels; i++) {
            head.next[i] = tail;
            last[i] = head;
        }
    }


    /**
     * Helper method find to set the last array as the entry where search ended.
     * Starting from the head entry at current maximum level to level 0 iterate to next entry if
     * the current entry is not tail or the element that is being searched for is greater than element
     * at the current entry. Set the last array to the entry where both condition fails.
     * Level down once reached the tail and again repeat the procedure. In the end,
     * last array contains the entry where the search ended and last[0].next[0] is the element being searched
     * for if it exists on the list.
     * @param x
     * @return  void
     */

    private void find(T x) {
        Entry entry = head;
        for (int i = maxLevel; i >= 0; i--) {   // search from top to bottom.
            while (entry.next[i] != tail && x.compareTo((T) entry.next[i].getElement()) > 0) {
                entry = entry.next[i];          // search from left to right.
            }
            last[i] = entry;
        }
    }

    /**
     * Contains method checks if the element is in the list or not. Calls the helper method find to set the last array.
     * Checks if the element is not null and is equal to the element being compared against.
     * @param x : element being searched and compared against.
     * @return true if both the condition is true else false.
     */
    public boolean contains(T x) {
        find(x);
        T element = (T) last[0].next[0].getElement();
        return element != null && element.equals(x);
    }

    /**
     * Helper method.
     * Randomly choose a level for the new entry.
     * Probability of choosing level i = 1/2 * Probability of choosing level (i-1).
     * @return the number of level generated.
     */
    private int chooseLevel() {
        int level = 1 + Integer.numberOfTrailingZeros(random.nextInt());
        level = Math.min(level, maxLevel + 1);     // to allow maxLevel to grow gradually.
        if (level > maxLevel) {
            maxLevel = level;
        }
        return level;
    }

    /**
     * Adds element x to the list. Checks if the list contains the element x. If true, it rejects the value. Else, a random level is chosen for the new entry
     * and a entry is created. For every level from the level 0 to the current level connect all the levels in the last to current entry and all the levels
     * in the current entry to the last.next entries. The skiplist has prev field so similarly connect backward at the bottom level (only) to maintain the prev field.
     * Increase the size per addition.
     * @param x : element to be added to the list.
     * @return false if the element is rejected else true.
     */
    public boolean add(T x) {
        if (contains(x)) {
            return false;           // duplicate element rejection.
        }
        int level = chooseLevel();
        Entry entry = new Entry(x, level);
        for (int i = 0; i < level; i++) {       // connecting entries at level i.
            entry.next[i] = last[i].next[i];
            last[i].next[i] = entry;
        }
        entry.next[0].prev = entry;         // setting prev field.
        entry.prev = last[0];
        size++;
        return true;
    }

    /**
     * Returns the smallest element that is bigger than or equal the element x. Calls the helper method find. If the find ends at the last entry then there is no
     * ceiling. Else, the element next to the entry where search ended is the ceiling of x. It could be x itself or the element greater than x. No need to check
     * if the element is greater or equal to x as the find is designed to end where the current element is greater than x.
     * @param x
     * @return : null if no ceiling and element if the ceiling exists.
     */
    public T ceiling(T x) {
        find(x);
        if (last[0].equals(tail)){
            return null;
        }
        return (T) last[0].next[0].getElement();
    }


    /**
     * @return the first element in the list i.e next to the head entry.
     */
    public T first() {
        return (T) head.next[0].getElement();
    }

    /**
     * Returns the largest element that is less than or equal to x. Calls the helper method find. If the element next to where the search ended is equal to x then
     * return that element else return where the search ended.
     * @param x
     * @return : element next to where the search ended if the next element is equal to x else return element form where the search ended.
     */
    public T floor(T x) {
        find(x);
        if (x.compareTo((T) last[0].next[0].getElement()) == 0){
            return (T)last[0].next[0].getElement();
        }else{
            return (T) last[0].getElement();
        }
    }

    /**
     * Get the element at index n.
     * If the n is smaller than zero or greater than the size of the list then throws NSE exception.
     * Element next to the head is considered to be at index 0.
     * Starting from there search for the element until the required index.
     * The element of the entry where the iteration stops is the element at index n.
     * @param n  index
     * @return  element x at index n. if invalid index throw exception.
     */
    public T get(int n) {
        if (n < 0 || n > size - 1) {    // size out of scope.
            throw new NoSuchElementException();
        }
        Entry entry = head.next[0];
        for (int i = 0; i < n; i++) {
            entry = entry.next[0];
        }
        return (T) entry.getElement();
    }

    /**
     * @return if the size of the list is zero. size is increased during addition and decreased during remove.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Iterators through the list in the sorted order. See the skipListIterator class below for details.
     * @return
     */
    public Iterator <T> iterator() {
        return new skipListIterator();
    }

    /**
     * @return the element before the tail of the list. tail is the dummy entry.
     */
    public T last() {
        return (T) tail.prev.getElement();
    }

    /**
     * Remove element from the list if it exists in the list.
     * If the list doesn't contain that element x then return null.
     * If the element is present in the list then bypass that element and connect all levels of previous entry to the next entry.
     * @param x : element to be removed.
     * @return x on success and on failure return null.
     */
    public T remove(T x) {
        if (!contains(x)) {
            return null;
        }
        Entry ent = last[0].next[0];
        for (int i = 0; i < ent.next.length; i++) {
            last[i].next[i] = ent.next[i]; // bypass ent at level i
        }
        last[0] = ent.next[0].prev;         // bypassing entry with element to be deleted in the prev field.
        size--;
        return (T) ent.element;
    }


    /**
     * @return size of the list.
     */
    public int size() {
        return this.size;
    }

    /**
     * skipListIterator class implements iterator and moves through list in the sorted order.
     */

    public class skipListIterator implements Iterator <T> {
        Entry <T> current, prev;    // entries to store current and its previous entry.

        /**
         * Default constructor sets current entry to be head and prev to be null.
         */
        public skipListIterator() {
            current = head;
            prev = null;
        }

        /**
         * @return if the element has any elements left by checking if the entry next to the current is tail or not. Tail is the dummy entry and has no element.
         */
        @Override
        public boolean hasNext() {
            return current.next[0] != tail;
        }

        /**
         * The skiplist is created in the sorted order so this methods just iterators through the list by setting current to current.next on every iteration.
         * @return element x in the next entry.
         */
        @Override
        public T next() {
            prev = current;
            current = current.next[0];
            return current.getElement();
        }
    }

}
