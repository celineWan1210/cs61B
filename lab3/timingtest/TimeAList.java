package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        // create list for size of data structure
        AList<Integer> Ns = new AList<>();
        // create list for time taken to complete the operations
        AList<Double> times = new AList<>();

        // N start with 1000
        int N = 1000;

        for (int i = 0; i < 8; i++) {
            Stopwatch sw = new Stopwatch();
            // add the size
            Ns.addLast(N);

            // create an array
            AList<Integer> list = new AList<>();
            for (int j = 0; j < N; j++) {
                list.addLast(N);
            }
            N += N;


            double timeInSeconds = sw.elapsedTime();
            // add the time taken
            times.addLast(timeInSeconds);
        }

        printTimingTable(Ns, times, Ns);
    }
}
