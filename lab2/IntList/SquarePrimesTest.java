package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    /**
     * Test that should return everything as the same and return false
     * as there is no primes to square
     */
    @Test
    public void testSquarePrimeAllRemain() {
        IntList lst = IntList.of(14, 15, 16, 18, 20);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 18 -> 20", lst.toString());
        assertFalse(changed);
    }

    /**
     * Test that should return everything by square
     */
    @Test
    public void testSquarePrimteAllSquare() {
        IntList lst = IntList.of(2, 3, 5, 7);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 9 -> 25 -> 49", lst.toString());
        assertTrue(changed);
    }
}
