package ee.ttu.algoritmid.fibonacci;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class AL01B {

    /**
     * Estimate or find the exact time reqired to compute the n-th Fibonacci number.
     *
     * @param n The n-th numbr to compute.
     * @return The time estimate or exact time in YEARS.
     */
    public String timeToComputeRecursiveFibonacci(int n) {
        long firstTime = System.currentTimeMillis();
        recursiveF(40);
        long lastTime = System.currentTimeMillis();
        long deltaTime = lastTime - firstTime;
        long k = (long) (deltaTime / Math.pow(1.6, 40));
        long answer = (long) (k * Math.pow(1.6, k));
        return String.valueOf(answer / 31557600/100);

//        if (n < 40) {
//            double startTime = System.currentTimeMillis();
//            recursiveF(30);
//            double end = System.currentTimeMillis() - startTime;
//            return String.valueOf(end / 86400 / 365250);
//        }
//        double startTime = System.currentTimeMillis();
//        recursiveF(40);
//        double end = System.currentTimeMillis() - startTime;
//        double finall = end / 40 * n;
//        return String.valueOf(finall / 86400 / 365250);

//        if (n < 30) {
//            long startTime = System.nanoTime();
//            recursiveF(30);
//            long end = System.nanoTime() - startTime;
//            long result = TimeUnit.DAYS.convert(end, TimeUnit.NANOSECONDS) / 365;
//            String res = String.valueOf(result);
//            return res;
//        }
//        long startTime = System.nanoTime();
//        recursiveF(30);
//        long endTime = System.nanoTime() - startTime;
//        String res = String.valueOf(endTime / 30 * n);
//        return res;
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
