package cz.robodreams.javadeveloper.homeworks.hw06fibo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FibonacciTest {

    Fibonacci fibonacci = new Fibonacci();

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "1,1",
            "5,5",
            "6,8",
            "20,6765"
//            ,"50,12586269025"
    })
    void implementFibonacciByRecursion(int n, long expected) {
        assertThat(fibonacci.implementFibonacciByRecursion(n)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "1,1",
            "5,5",
            "6,8",
            "20,6765",
            "50,12586269025"
    })
    void implementFibonacciByFor(int n, long expected) {
        assertThat(fibonacci.implementFibonacciByFor(n)).isEqualTo(expected);
    }

}
