package cz.robodreams.javadeveloper.homeworks.hw06fibo;

import java.math.BigDecimal;
import java.text.*;

public class Fibonacci {

    static private long citac;

    private long procRecursion(int n) {

        this.citac++;
        // System.out.println("číslo průchodu : " + this.citac);

        if (n <= 0) return 0;
        if (n == 1) return 1;

        return procRecursion(n - 1) + procRecursion(n - 2);
    }


    /**
     * Implement by https://cs.wikipedia.org/wiki/Fibonacciho_posloupnost
     */
    public long implementFibonacciByRecursion(int n) {

        this.citac = 0;

        long result = procRecursion(n);

        System.out.println("Fibonacciho číslo " + n + " v pořadí je " + result);
        System.out.println("Pro výpočet použito " + this.citac + " rekurzivních průchodů.\r\n");

        return result;
    }


    /**
     * Implement by https://cs.wikipedia.org/wiki/Fibonacciho_posloupnost
     */
    public long implementFibonacciByFor(int n) {

        if (n <= 0) return 0;

        if (n == 1) {
            System.out.println("Fibonacciho číslo   1 v pořadí je '1'.");
            return 1;
        }

        BigDecimal a1 = BigDecimal.valueOf(0);
        BigDecimal a2 = BigDecimal.valueOf(1);
        BigDecimal sum = BigDecimal.ZERO;

        for (int i = 1; i < n; i++) {
            sum = a1.add(a2);
            a1 = a2;
            a2 = sum;
        }

        DecimalFormat myFormatter = new DecimalFormat("###,###,###,###,##0");
        System.out.printf("Fibonacciho číslo %3d v pořadí je '%s'. \r\n",n,myFormatter.format(sum) );

        return sum.longValue();
    }

}
