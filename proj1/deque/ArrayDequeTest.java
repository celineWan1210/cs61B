package deque;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testEmptySize() {
        ArrayDeque<String> L = new ArrayDeque<>();
        Assert.assertEquals(0, L.size());
    }

    @Test
    public void testIsEmpty() {
        ArrayDeque<String> L = new ArrayDeque<>();
        Assert.assertTrue(L.isEmpty());
    }

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        ArrayDeque<String> lld1 = new ArrayDeque<>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addLast("a");
        lld1.addLast("b");
        lld1.addFirst("c");
        lld1.addLast("d");
        lld1.addLast("e");
        lld1.addFirst("f");
        lld1.addLast("g");
        lld1.addLast("h");

        assertEquals(8, lld1.size());
        lld1.printDeque();
    }
}