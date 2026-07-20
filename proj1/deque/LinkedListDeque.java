package deque;

public class LinkedListDeque<T> {
    // size of array
    private int size;
    // array of items
    private T[] items;


    /**
     * A size of 100 0s are instantiate by default
     */
    public LinkedListDeque() {
        items = (T[]) new Object[100];
        size = 0;
    }

    public void addFirst(T item) {
        //
    }

    public void addLast(T item) {
        //
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {

    }
}