/** Class that prints the Collatz sequence starting from a given number.
 *  @author Wan Ying Xuan
 */
public class Collatz {

    /** Buggy implementation of nextNumber! */
    public static int nextNumber(int n) {
        // start by checking if number is even
        if (n % 2 == 0) {
            // if even return n/2
            return n / 2;
            // else if odd return 3n+1
        } else {
            return 3 * n + 1;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

