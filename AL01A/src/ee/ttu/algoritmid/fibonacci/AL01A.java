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
        for (int i = 2; i <= n; i++) {
            current = secLast.add(last);
            secLast = last;
            last = current;
        }
        return String.valueOf(last);
    }

    public static void main(String[] args) {
        AL01A al01A = new AL01A();
        System.out.println(al01A.iterativeF(7));

    }
}
