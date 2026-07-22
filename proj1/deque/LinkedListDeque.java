package deque;

import jh61b.junit.In;

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

    /**
     * IF item is first in the whole list
     * - make that the sentinel node
     * ELSE
     * - let sentinel point to new node
     * - update previous sentinel node to point to new sentinel
     * - update last node to point to new sentinel
     * @param item to add into front of the list
     */
    public void addFirst(T item) {
        if (size == 0) {
            setSentinel(item);
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
            previousSentinelNode.previous = sentinel;
            // set the last node to point to the new sentinel
            lastNode.next = sentinel;
        }

        size += 1;
    }

    /**
     * IF item is first in the whole list
     * - make that the sentinel node
     * ELSE
     * - get the previous last node
     * - set the current last node
     * - update sentinel's previous to the new last node
     * - update previous sentinel's last node to point at last node
     * @param item to add to the end of the list
     */
    public void addLast(T item) {
        // if size is 0 means it's the sentinel
        if (size == 0) {
            setSentinel(item);
        } else {
            // previous last node
            IntNode previousLastNode = sentinel.previous;
            // current last node
            IntNode lastNode = new IntNode(previousLastNode, item, sentinel);

            // set sentinel previous to point at the last node
            sentinel.previous = lastNode;
            // set previous last node to point to the new node
            previousLastNode.next = lastNode;
        }

        size += 1;
    }

    /**
     * IF list is empty
     * ELSE
     * - IF size is 1 set sentinel to null
     * - ELSE
     *      - set new sentinel as the next sentinel
     *      - update sentinel's previous to point at last node
     *      - update last node's next to point at sentinel
     *
     * - reduce size and return the old sentinel's item
     * @return null / or the sentinel's item
     */
    public T removeFirst() {
        // if list is empty, nothing to remove
        // return null
        if (isEmpty()) {
            return null;
        }

        IntNode nodeToRemove = sentinel;
        if (size == 1) {
            sentinel = null;
        } else {
            IntNode lastNode = sentinel.previous;

            // set second item as the new sentinel
            sentinel = sentinel.next;
            // update new sentinel previous to point at last node
            sentinel.previous = lastNode;
            // update last node to point to new sentinel
            lastNode.next = sentinel;
        }
        size -= 1;
        return nodeToRemove.item;
    }

    /**
     * IF list is empty
     * ELSE
     * - IF size is 1 set sentinel to null
     * - ELSE
     *      - set sentinel's previous to point at new last node
     *      - set new last node's next to point at sentinel
     * - reduce size by 1
     * @return null / or the last node's item
     */
    public T removeLast() {
        // if list is empty, nothing to remove
        // return null
        if (isEmpty()) {
            return null;
        }
        IntNode nodeToRemove = sentinel.previous;
        // new last node
        IntNode lastNode = nodeToRemove.previous;

        if (size == 1) {
           sentinel = null;
        } else {
            sentinel.previous = lastNode;
            lastNode.next = sentinel;
        }

        size -= 1;
        return nodeToRemove.item;
    }

    /**
     * Keep iterate until get to the desired item / return null if no item is found
     * @param index of the item
     * @return null or item
     */
    public T get(int index) {
        // start from first node
        IntNode node = sentinel;

        for (int i = 0; i < size; i ++) {
            if (i == index) {
                return node.item;
            }
            node = node.next;
        }

        return null;
    }

    /**
     * Recursion start with the sentinel and listIndex of zero
     * @param index of the item we want to find
     * @return item if found or null when it's out of bound
     *
     * @source <a href="https://softwareengineering.stackexchange.com/questions/279004/general-way-to-convert-a-loop-while-for-to-recursion-or-from-a-recursion-to-a">How to convert for loop to recursion</a>
     */
    public T getRecursive(int index) {
        // start from first node
        IntNode node = sentinel;
        // start from first index of the list
        int listIndex = 0;

        return getRecursion(listIndex, index, node);
    }

    /**
     * Base case to stop the recursion is when item is found or the index given was out of bound
     * @param listIndex index of the list to keep going forward and try to match with the given index
     * @param index that we want to match and find the item
     * @param node node that allow us to keep pointing to the next item
     * @return item if found or null when it's out of bound
     */
    private T getRecursion(int listIndex, int index, IntNode node) {
        if (listIndex == index) {
            return node.item;
        } else if (index >= size || index < 0) {
            return null;
        }

        return getRecursion(listIndex+1, index, node.next);
    }


    /**
     * When the item is the first in the list,
     * - sentinel is automatically set to that first item
     * @param item first item to add to the list
     */
    private void setSentinel(T item) {
        sentinel = new IntNode(null, item, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
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

    /**
     * Return false if
     * - objects are not the same instance
     * - objects are not same size
     * - object are not equal to each other
     * Else return True
     *
     * @param o the reference object with which to compare.
     * @return true if both objects are equal and false otherwise
     */
    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque)) {
            return false;
        } else if (((LinkedListDeque<?>) o).size() != size) {
            return false;
        }

        IntNode nodeO = (IntNode) ((LinkedListDeque<?>) o).sentinel;
        IntNode node = sentinel;

        for (int i = 0; i < size; i++) {
            if (nodeO.item != node.item) {
                return false;
            }
            nodeO = nodeO.next;
            node = node.next;
        }
        return true;
    }

    /**
     * print the items in the deque from first to last
     * - separated by space and a new line after all items are printed out
     */
    public void printDeque() {
        // start from first node
        IntNode node = sentinel;

        for (int i = 0; i < size; i++) {
            System.out.print(node.item + " ");
            node = node.next;
        }
        System.out.println();
    }
}