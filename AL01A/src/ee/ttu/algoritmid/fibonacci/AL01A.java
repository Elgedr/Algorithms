package ee.ttu.algoritmid.fibonacci;

import java.math.BigInteger;

public class AL01A {

    /**
     * Compute the Fibonaci sequence number.
     *
     * @param n The number of the sequence to compute.
     * @return The n-th number in Fibonacci series.
     */
    public String iterativeF(int n) {
        if (n == 0 || n == 1) {
            return String.valueOf(n);
        }
        BigInteger secLast = BigInteger.valueOf(0); // n-2
        BigInteger last = BigInteger.valueOf(1); // n-1
        BigInteger current = BigInteger.valueOf(0);
        for (int i = 0; i <= n - 2; i++) {
            current = secLast.add(last);
            secLast = last;
            last = current;
        }
        return String.valueOf(last);
    }
}
