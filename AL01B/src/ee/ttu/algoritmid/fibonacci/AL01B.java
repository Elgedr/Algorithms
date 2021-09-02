package ee.ttu.algoritmid.fibonacci;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class AL01B {

    /**
     * Estimate or find the exact time required to compute the n-th Fibonacci number.
     *
     * @param n The n-th numbr to compute.
     * @return The time estimate or exact time in YEARS.
     */
    public String timeToComputeRecursiveFibonacci(int n) {
        long days = TimeUnit.DAYS
        if (n < 30) {
            long startTime = System.nanoTime();
            recursiveF(30);
            long finalTime = System.nanoTime() - startTime;
            long result = TimeUnit.DAYS.convert(finalTime, TimeUnit.NANOSECONDS) / 365;
            String res = String.valueOf(result);
            return res;
        }
        long startTime = System.nanoTime();
        recursiveF(30);
        long endTime = System.nanoTime() - startTime;
        String res = String.valueOf(endTime / 30 * n);
        return res;
    }

    /**
     * Compute the Fibonacci sequence number recursively.
     * (You need this in the timeToComputeRecursiveFibonacci(int n) function!)
     *
     * @param n The n-th number to compute.
     * @return The n-th Fibonacci number as a string.
     */
    public BigInteger recursiveF(int n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }
        return recursiveF(n - 1).add(recursiveF(n - 2));
    }
}
