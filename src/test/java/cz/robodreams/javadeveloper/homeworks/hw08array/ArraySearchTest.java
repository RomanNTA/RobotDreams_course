package cz.robodreams.javadeveloper.homeworks.hw08array;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ArraySearchTest {

    ArraySearch arraySearch = new ArraySearch();

    private static Stream<Arguments> findElementInArray(){
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 5, 6, 8, 9, 12, 15, 18, 20, 23, 25}, 20, 10),
                Arguments.of(new int[]{4, 5, 8, 9, 10, 15, 18, 20, 28, 30}, 5, 1),
                Arguments.of(new int[]{4, 5, 8, 9, 10, 15, 18, 20, 28, 30}, 4, 0),
                Arguments.of(new int[]{4, 5, 8, 9, 10, 15, 18, 20, 28, 30}, 30, 9),
                Arguments.of(new int[]{4, 5, 8, 9, 10, 15, 18, 20, 28, 30, 31}, 31, 10),
                Arguments.of(new int[]{1, 2, 5, 7, 8}, 5, 2),
                Arguments.of(new int[]{}, 5, -1),
                Arguments.of(new int[]{5}, 5, 0),
                Arguments.of(null, 5, -1),
                Arguments.of(new int[]{2, 7, 8, 10}, 5, -1)
        );
    }


    @ParameterizedTest
    @MethodSource("findElementInArray")
    void linearSearch(int[] arr,int target, int expected) {
        assertThat(arraySearch.linearSearch(arr, target))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("findElementInArray")
    void binarySearch(int[] arr,int target, int expected) {
        assertThat(arraySearch.binarySearch(arr, target))
                .isEqualTo(expected);
    }
}
