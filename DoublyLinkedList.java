package axl173530;


import java.util.Iterator;

public class DoublyLinkedList<T> extends SinglyLinkedList<T> {


    static class Entry<E> extends SinglyLinkedList.Entry {
        SinglyLinkedList.Entry<E> previous;

        Entry(E x, SinglyLinkedList.Entry<E> next, SinglyLinkedList.Entry<E> previous) {
            super(x, next);
            this.previous = previous;
        }
    }


    DoublyLinkedList() {
        super();
    }

    // method to return the iterator type.
    public DoublyLinkedListIterator<T> dllIterator() {
        return new DLLIterator();
    }

    protected class DLLIterator extends SinglyLinkedList.SLLIterator implements DoublyLinkedListIterator<T> {

        SinglyLinkedList.Entry<T> nxt;

        public DLLIterator() {
            super();
        }

        @Override
        public boolean hasPrevious() {
            return ((DoublyLinkedList.Entry) cursor).previous != null;
        }

        @Override
        public T previous() {
            nxt = cursor;
            cursor = ((DoublyLinkedList.Entry) cursor).previous;
            ready = true;
            return (T) cursor.element;
        }

        @Override
        public void add(T x) {
            nxt = cursor.next;
            Entry<T> ent = new Entry<>(x, nxt, cursor);
            cursor.next = ent;
            ((DoublyLinkedList.Entry) nxt).previous = ent;
            cursor = ent;
            size ++;
        }

        @Override
        public void remove() {
            super.remove();
            ((DoublyLinkedList.Entry)nxt).previous = prev;
        }


    }

    @Override
    public void add(T i) {
        super.add(new Entry<>(i, null, tail));
    }

    public static void main(String... args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
            list.printList();
        }
        DoublyLinkedListIterator<Integer> iter = list.dllIterator();
        while (iter.hasNext()) {
            int x = (Integer) iter.next();
            if (x == 3 || x == 8) {
                iter.add(x + 10);
            } else if (x == 5) {
                iter.remove();
            }
        }
        list.printList();
    }
}

interface DoublyLinkedListIterator<T> extends Iterator {
    boolean hasPrevious();
    T previous();
    void add(T ent);

}