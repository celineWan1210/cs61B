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
        lld1.addFirst("front");
        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());
    }
}