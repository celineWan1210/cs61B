package deque;

public class ArrayDeque<T> {
    // size of the deque
    private int size;
    // array of T items
    private T[] items;

    /**
     * Instantiate an array of 8 null at the start and size of 0
     */
    public ArrayDeque() {
        // instantiate of 8 null at the start
        items = (T[]) new Object[8];
        size = 0;
    }

    public void addFirst(T item) {

    }

    public void addLast(T item) {

    }

    /**
     * @return true if size is 0 (no items) else false if deque contains item
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * @return number of items in deque
     */
    public int size() {
        return size;
    }

    public T removeFirst() {
        return null;
    }

    public T get(int index) {
        return null;
    }

    public void printDeque() {

    }
}