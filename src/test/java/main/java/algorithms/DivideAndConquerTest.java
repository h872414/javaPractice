package main.java.algorithms;

import lombok.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DivideAndConquerTest {
    private final DivideAndConquer divideAndConquer = new DivideAndConquer();

    @Value
    private static class SimpleTestCaseForPeakSearch {
        int[] inputArray;
        int expectedIndex;
    }

    private static Collection<SimpleTestCaseForPeakSearch> dataForSimplePeakSearch() {
        return Arrays.asList(
            new SimpleTestCaseForPeakSearch(new int[]{1, 3, 4, 3, 5, 1, 3}, 2),
            new SimpleTestCaseForPeakSearch(new int[]{1, 4, 1, 2, 1, 3, 5, 1, 3}, 1),
            new SimpleTestCaseForPeakSearch(new int[]{1, 2, 5, 4}, 2),
            new SimpleTestCaseForPeakSearch(new int[]{1, 2, 2, 2, 2, 5, 4}, 3),
            new SimpleTestCaseForPeakSearch(new int[]{6, 21, 7, 8, 9, 10, 13, 10}, 6),
            new SimpleTestCaseForPeakSearch(new int[]{6, 21, 7, 8, 9, 10, 13, 14}, -1),
            new SimpleTestCaseForPeakSearch(new int[]{14, 13, 12, 11, 9, 10, 13, 14}, -1),
            new SimpleTestCaseForPeakSearch(new int[]{14, 13, 12}, -1),
            new SimpleTestCaseForPeakSearch(new int[]{12, 13, 14}, -1),
            new SimpleTestCaseForPeakSearch(new int[]{12, 14, 12}, 1)
        );
    }

    @DisplayName("Simple test for binary search")
    @ParameterizedTest(name = "{index} test case")
    @MethodSource("dataForSimplePeakSearch")
    void simpleTest(SimpleTestCaseForPeakSearch testCase) {
        assertThat(divideAndConquer.findAPeak(testCase.inputArray), is(testCase.expectedIndex));
    }

    @DisplayName("PeakSearch null test")
    @ParameterizedTest(name = "{index} test case")
    @NullSource
    void peakSearchNullTest(int[] inputArray) {
        assertThrows(NullPointerException.class, () -> divideAndConquer.findAPeak(inputArray));
    }

    @DisplayName("PeakSearch null test")
    @ParameterizedTest(name = "{index} test case")
    @EmptySource
    void peakSearchEmptyTest(int[] inputArray) {
        assertThrows(IllegalArgumentException.class, () -> divideAndConquer.findAPeak(inputArray));
    }

    @DisplayName("PeakSearchRecursive simple test")
    @ParameterizedTest(name = "{index} test case [args] ={arguments}")
    @MethodSource("dataForSimplePeakSearch")
    void peakSearchRecursiveSimpleTest(SimpleTestCaseForPeakSearch testCase) {
        assertThat(
            testCase.expectedIndex,
            is(divideAndConquer.findAPeakRecursive(testCase.inputArray, 1, (testCase.inputArray.length - 2)))
        );
    }

    @DisplayName("PeakSearchRecursive null test")
    @ParameterizedTest(name = "{index} test case")
    @NullSource
    void peakSearchRecursiveNullTest(int[] inputArray) {
        assertThrows(NullPointerException.class,
            () -> divideAndConquer.findAPeakRecursive(inputArray, 1, inputArray.length - 1));
    }

    @DisplayName("PeakSearchRecursive null test")
    @ParameterizedTest(name = "{index} test case")
    @EmptySource
    void peakSearchRecursiveEmptyTest(int[] inputArray) {
        assertThrows(IllegalArgumentException.class,
            () -> divideAndConquer.findAPeakRecursive(inputArray, 1, inputArray.length - 1));
    }
}
