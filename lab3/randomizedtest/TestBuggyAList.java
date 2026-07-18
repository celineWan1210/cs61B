package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correctList = new AListNoResizing<>();
        AListNoResizing<Integer> buggyList = new AListNoResizing<>();

        // add 4,5,6 to correct list
        correctList.addLast(4);
        correctList.addLast(5);
        correctList.addLast(6);
        // add 4,5,6 to buggy list
        buggyList.addLast(4);
        buggyList.addLast(5);
        buggyList.addLast(6);

        // compare list
        assertEquals(correctList.removeLast(), buggyList.removeLast());
        assertEquals(correctList.removeLast(), buggyList.removeLast());
        assertEquals(correctList.removeLast(), buggyList.removeLast());
    }
}
