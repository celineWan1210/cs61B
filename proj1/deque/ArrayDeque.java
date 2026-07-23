package deque;

public class ArrayDeque<T> {
    // size of the deque
    private int size;
    // array of T items
    private T[] items;
    // arraySize
    private int arraySize;
    // nextFirst
    private int nextFirst;
    // nextLast
    private int nextLast;

    /**
     * Instantiate an array of 8 null at the start and size of 0
     */
    public ArrayDeque() {
        // nextFirst set to 0
        nextFirst = 4;
        // nextLast set to 1
        nextLast = 5;

        // instantiate of 8 null at the start
        arraySize = 8;
        items = (T[]) new Object[arraySize];
        size = 0;
    }

    public void addFirst(T item) {
        if (size == arraySize) {
            System.out.println("Array Full");
        } else {
            items[nextFirst] = item;

            if (nextFirst == 0) {
                nextFirst = arraySize - 1;
            } else {
                nextFirst -= 1;
            }

            size += 1;
        }
    }

    public void addLast(T item) {
        if (size == arraySize) {
            System.out.println("Array Full");
        } else {
            items[nextLast] = item;

            if (nextLast == arraySize - 1) {
                nextLast = 0;
            } else {
                nextLast += 1;
            }

            size += 1;
        }
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