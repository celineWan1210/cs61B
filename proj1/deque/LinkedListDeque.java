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