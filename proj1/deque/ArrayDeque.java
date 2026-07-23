package deque;

public class ArrayDeque<T> {
    // size of the deque
    private int size;
    // array of T items
    private T[] items;

    public ArrayDeque() {
        // instantiate of 8 0s at the start
        items = (T[]) new Object[8];
        size = 0;
    }

    public void addFirst(T item) {

    }

    public void addLast(T item) {

    }

    public boolean isEmpty() {
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