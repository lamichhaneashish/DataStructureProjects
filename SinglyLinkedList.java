package axl173530;
import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Iterable<T> {

    /**
     * Class Entry holds a single node of the list
     */
    static class Entry<E> {
        E element;
        Entry<E> next;

        Entry(E x, Entry<E> nxt) {
            element = x;
            next = nxt;
        }
    }

    // Dummy header is used.  tail stores reference of tail element of list
    Entry<T> head, tail;
    int size;

    public SinglyLinkedList() {
        head = new Entry<>(null, null);
        tail = head;
        size = 0;
    }

    public Iterator<T> iterator() {
        return new SLLIterator();
    }

    protected class SLLIterator implements Iterator<T> {
        Entry<T> cursor, prev;
        boolean ready;  // is item ready to be removed?

        SLLIterator() {
            cursor = head;
            prev = null;
            ready = false;
        }

        public boolean hasNext() {
            return cursor.next != null;
        }

        public T next() {
            prev = cursor;
            cursor = cursor.next;
            ready = true;
            return cursor.element;
        }

        // Removes the current element (retrieved by the most recent next())
        // Remove can be called only if next has been called and the element has not been removed
        public void remove() {
            if (!ready) {
                throw new NoSuchElementException();
            }
            prev.next = cursor.next;
            // Handle case when tail of a list is deleted
            if (cursor == tail) {
                tail = prev;
            }
            cursor = prev;
            ready = false;  // Calling remove again without calling next will result in exception thrown
            size--;
        }
    }  // end of class SLLIterator

    // Add new elements to the end of the list
    public void add(T x) {
        add(new Entry<>(x, null));
    }

    // helper function for adding element at the end of the list.
    public void add(Entry<T> ent) {
        tail.next = ent;
        tail = tail.next;
        size++;
    }

    // Add new element after the given index.
    public void add(int index, T x) {
        Entry<T> ent = new Entry<>(x, find(index));
        Entry<T> prev = find(index-1);
        if (index == 0) {
            head.next = ent;
        } else if (index == size ) {
            tail.next = ent;
            tail = ent;
        } else {
            ent.next = prev.next;
            prev.next = ent;
        }
        size++;
    }

    // remove item at given index.
    public T remove(int index) {
        checkList();
        return remove(find(index - 1), find(index));
    }

    // Remove helper function.
    private T remove(Entry<T> prev, Entry<T> rmEntry) {
        prev.next = rmEntry.next;
        size--;
        return rmEntry.element;
    }

    //Get element from a particular index./
    public T get(int index) {
        checkList();
        if (index < 0 || index > size) {
            return null;
        }
        return find(index).element;
    }

    // Set element in a particular index.
    public void set(int index, T x) {
        checkList();
        find(index).element = x;
    }


    // Finds if the index is in the list and makes it a cursor.
    private Entry<T> find(int index) {
        Entry<T> cursor = head;
        int i = 0;
        while(i <= index){
            cursor = cursor.next;
            i++;
        }
        return cursor;
    }
    // checks if the list is too short and throws exception.
    private void checkList() {
        if (this.size < 3) {
            throw new NoSuchElementException();
        }
    }

    public void printList() {
        System.out.print(this.size + ": ");
        for (T item : this) {
            System.out.print(item + " ");
        }

        System.out.println();
    }

    // Rearrange the elements of the list by linking the elements at even index
    // followed by the elements at odd index. Implemented by rearranging pointers
    // of existing elements without allocating any new elements.
    public void unzip() {
        if (size < 3) {  // Too few elements.  No change.
            return;
        }

        Entry<T> tail0 = head.next;
        Entry<T> head1 = tail0.next;
        Entry<T> tail1 = head1;
        Entry<T> c = tail1.next;
        int state = 0;

        // Invariant: tail0 is the tail of the chain of elements with even index.
        // tail1 is the tail of odd index chain.
        // c is current element to be processed.
        // state indicates the state of the finite state machine
        // state = i indicates that the current element is added after taili (i=0,1).
        while (c != null) {
            if (state == 0) {
                tail0.next = c;
                tail0 = c;
                c = c.next;
            } else {
                tail1.next = c;
                tail1 = c;
                c = c.next;
            }
            state = 1 - state;
        }
        tail0.next = head1;
        tail1.next = null;
        // Update the tail of the list
        tail = tail1;
    }

    public static void main(String[] args) throws NoSuchElementException {
//            int n = 10;
//            if(args.length > 0) {
//                n = Integer.parseInt(args[0]);
//            }
//
//            SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
//            for(int i=1; i<=n; i++) {
//                lst.add(Integer.valueOf(i));
//            }
//            lst.printList();
//
//            Iterator<Integer> it = lst.iterator();
//            Scanner in = new Scanner(System.in);
//            whileloop:
//            while(in.hasNext()) {
//                int com = in.nextInt();
//                switch(com) {
//                    case 1:  // Move to next element and print it
//                        if (it.hasNext()) {
//                            System.out.println(it.next());
//                        } else {
//                            break whileloop;
//                        }
//                        break;
//                    case 2:  // Remove element
//                        it.remove();
//                        lst.printList();
//                        break;
//                    default:  // Exit loop
//                        break whileloop;
//                }
//            }
//            lst.printList();
//            lst.unzip();
//            lst.printList();


        System.out.println("Info's ");
        int a = 10;
        if (args.length >  0) {
            a = Integer.parseInt(args[0]);
        }

        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        for (int i = 0; i <= a; i++) {
            list1.add(Integer.valueOf(i));
        }
        list1.printList();
    }
}
