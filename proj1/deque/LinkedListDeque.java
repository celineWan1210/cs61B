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
    // node that point to the first item
    private IntNode sentinel;

    /**
     * A size of 100 0s are instantiate by default
     */
    public LinkedListDeque() {
        size = 0;
    }

    public void addFirst(T item) {
        if (size == 0) {
            sentinel = new IntNode(null, item, null);
            sentinel.next = sentinel;
            sentinel.previous = sentinel;
        } else {
            // last node
            IntNode lastNode = sentinel.previous;
            // second node to sentinel
            IntNode previousSentinelNode = sentinel;

            // new node set as sentinel
            // - prev set to point to last node
            // - next set to point to previous sentinel node
            sentinel = new IntNode(lastNode, item, previousSentinelNode);

            // set the previous sentinel node (now second front) to point to the sentinel
            previousSentinelNode.previous = previousSentinelNode;
            // set the last node to point to the new sentinel
            lastNode.next = sentinel;
        }

        size += 1;
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