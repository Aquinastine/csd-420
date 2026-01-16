import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * LinkedList Traversal Benchmark + Correctness Tests
 *
 * What this program does:
 * 1) Builds a LinkedList with N integers: 0..N-1
 * 2) Measures traversal time using:
 *      A) Iterator
 *      B) get(index) in a for-loop
 * 3) Runs for N = 50,000 and N = 500,000
 * 4) Includes correctness tests (size + checksum).
 *
 * Notes:
 * - Uses System.nanoTime() and multiple runs.
 * - The first run is treated as a warmup (JIT compilation effects).
 * - Prints best time of measured runs to reduce noise.
 */
public class LinkedListTraversalTest {

    public static void main(String[] args) {
        runScenario(50_000);
        System.out.println();
        runScenario(500_000);
    }

    private static void runScenario(int n) {
        System.out.println("=== Scenario: N = " + n + " ===");

        LinkedList<Integer> list = buildList(n);

        // --- Correctness tests (ensures code functions correctly) ---
        testListSize(list, n);

        long expectedSum = expectedSumOf0ToNMinus1(n);

        // Warmup + measure iterator traversal
        long iterSumWarmup = traverseWithIterator(list);
        testSum(iterSumWarmup, expectedSum, "Iterator warmup");

        long iteratorBestNs = bestOfRuns(() -> {
            long s = traverseWithIterator(list);
            testSum(s, expectedSum, "Iterator measured run");
        }, 3);

        // Warmup + measure get(index) traversal
        long getSumWarmup = traverseWithGetIndex(list);
        testSum(getSumWarmup, expectedSum, "get(index) warmup");

        long getBestNs = bestOfRuns(() -> {
            long s = traverseWithGetIndex(list);
            testSum(s, expectedSum, "get(index) measured run");
        }, 3);

        // Print results
        System.out.printf("Iterator traversal best:   %.3f ms%n",
        iteratorBestNs / 1_000_000.0);
        System.out.printf("get(index) traversal best: %.3f ms%n", 
        getBestNs / 1_000_000.0);

        double ratio = (getBestNs == 0) ? Double.POSITIVE_INFINITY : 
        (double) getBestNs / iteratorBestNs;
        System.out.printf("Slowdown (get / iterator): %.2fx%n", ratio);
    }

    private static LinkedList<Integer> buildList(int n) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        return list;
    }

    private static long traverseWithIterator(List<Integer> list) {
        long sum = 0;
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            sum += it.next();
        }
        return sum;
    }

    private static long traverseWithGetIndex(List<Integer> list) {
        long sum = 0;
        // IMPORTANT: On LinkedList, list.get(i) is O(i) due to 
        // traversal from an end.
        // Doing it in a loop makes total traversal O(n^2).
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum;
    }

    // -------- Timing helpers --------

    private static long bestOfRuns(Runnable r, int runs) {
        long best = Long.MAX_VALUE;
        for (int i = 0; i < runs; i++) {
            long t0 = System.nanoTime();
            r.run();
            long t1 = System.nanoTime();
            best = Math.min(best, (t1 - t0));
        }
        return best;
    }

    // -------- Correctness tests (simple assertions without JUnit) --------

    private static void testListSize(List<Integer> list, int expectedSize) {
        if (list.size() != expectedSize) {
            throw new AssertionError("Size test failed: expected " + 
            expectedSize + " but got " + list.size());
        }
    }

    private static void testSum(long actual, long expected, String label) {
        if (actual != expected) {
            throw new AssertionError(label + " sum test failed: expected "
            + expected + " but got " + actual);
        }
    }

    private static long expectedSumOf0ToNMinus1(int n) {
        // sum of 0..(n-1) = n*(n-1)/2
        return ((long) n) * (n - 1L) / 2L;
    }
}

/*
COMMENTS / DISCUSSION :

Output of the test:
=== Scenario: N = 50000 ===
Iterator traversal best:   0.261 ms
get(index) traversal best: 759.322 ms
Slowdown (get / iterator): 2909.28x

=== Scenario: N = 500000 ===
Iterator traversal best:   0.749 ms
get(index) traversal best: 77932.210 ms
Slowdown (get / iterator): 104117.85x
------------------------------------------
------------------------------------------

Explanation of the Results of the test

When the list contained 50,000 integers, traversing the LinkedList with 
an iterator completed in 0.261 ms, while using get(index) took 
759.322 ms. This means the index-based approach was approximately 
2,900 times slower than iterator traversal.

When the list size increased to 500,000 integers, the iterator traversal 
time rose modestly to 0.749 ms, which reflects near-linear growth. In 
contrast, the get(index) traversal time increased dramatically to 
77,932.210 ms (over 77 seconds). At this size, the get(index) approach 
was more than 104,000 times slower than using an iterator.

Explanation of the results

Iterator traversal is significantly faster on a LinkedList because it 
moves sequentially from one node to the next, visiting each element 
exactly once. Each call to next() is effectively constant time, 
resulting in overall O(n) performance.

Using get(index) on a LinkedList is much slower because the structure 
does not support true random access. Each call to get(i) requires 
traversing the list from the head or tail to reach the requested 
position. When this operation is placed inside a loop, the repeated 
traversals compound, producing O(n²) behavior.

Growth comparison

The results clearly show that increasing the list size by a factor of 
10 caused the iterator approach to slow down slightly, while the 
get(index) approach became orders of magnitude slower. This dramatic 
difference highlights how poorly index-based access scales with LinkedList.

Conclusion

For LinkedList, traversal should always be performed using an iterator 
or enhanced for loop. Using get(index) in a loop is extremely 
inefficient and should be avoided.
*/
