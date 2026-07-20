package deque;

public class LinkedListDeque<T> {
    /**
     * Int node class with the
     *  - a node that points to the previous item
     *  - current item
     *  - a node that point to the next item
     */
    private class IntNode {
        public T item;
        public IntNode next;
        public IntNode previous;

        public IntNode(IntNode p, T i, IntNode n) {
            previous = p;
            item = i;
            next = n;
        }
    }

    // size of array
    private int size;
    // array of items
    private T[] items;
    // node that point to the first item
    private IntNode sentinal;

    /**
     * A size of 100 0s are instantiate by default
     */
    public LinkedListDeque() {
        items = (T[]) new Object[3];
        size = 0;
    }

    public void addFirst(T item) {
        //
    }

    public void addLast(T item) {
        //
    }

    /**
     * If size is not zero then its not empty
     * @return True if linked list is empty / false if its not empty
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * -- constant time
     * @return size of the linked list deque
     */
    public int size() {
        return size;
    }

    public void printDeque() {

    }
}