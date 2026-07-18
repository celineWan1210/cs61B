package randomizedtest;

import edu.princeton.cs.algs4.StdOut;
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

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        // create a buggy list
        BuggyAList<Integer> buggyL = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                // add last for L
                L.addLast(randVal);
                // add last for buggy L
                buggyL.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size for L
                int sizeL = L.size();
                // size for buggy L
                int sizeBuggyL = L.size();
                System.out.println("size for L: " + sizeL);
                System.out.println("size for buggy L: " + sizeBuggyL);

                // assert
                assertEquals(sizeL, sizeBuggyL);
            } else if (operationNumber == 2 && L.size() > 0) {
                // print for L list
                System.out.println("getLast(" + L.getLast() + ")");
                // print for buggy list
                System.out.println("getLast(" + buggyL.getLast() + ")");

                // assert
                assertEquals(L.getLast(), buggyL.getLast());
            } else if (operationNumber == 3 && L.size() > 0) {
                // last item get remove from L
                int lastLItem = L.removeLast();
                // last item get remove from buggy L
                int lastBuggyLItem = buggyL.removeLast();

                // print for L list
                System.out.println("removeLast(" + lastLItem + ")");
                // print for buggy list
                System.out.println("removeLast(" + lastBuggyLItem + ")");

                // assert
                assertEquals(lastLItem, lastBuggyLItem);
            }
        }
    }
}
