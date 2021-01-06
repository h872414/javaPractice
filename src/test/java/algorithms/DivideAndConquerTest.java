package test.java.algorithms;

import main.java.algorithms.DivideAndConquer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.omg.CORBA.INVALID_ACTIVITY;

import javax.naming.directory.InvalidAttributeIdentifierException;
import java.security.PrivilegedActionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


class DivideAndConquerTest {

    public static Collection<Object[]> dataForPeakSearch() {
        return Arrays.asList(new Object[][]{
                {
                        new int[]{1, 3, 4, 3, 5, 1, 3},
                        2
                },
                {
                        new int[]{1, 4, 1, 2, 1, 3, 5, 1, 3},
                        1
                },
                {
                        new int[]{1, 2, 5, 4},
                        2
                },
                {
                    new int[]{},
                        -1,
                },
                {
                    null,
                        -1
                }
        });
    }


    @DisplayName("Find a peak with normal input")
    @ParameterizedTest(name = "{index} test case")
    @MethodSource("dataForPeakSearch")
    void findAPeak(int[] inputArray, int expectedIndex) throws NullPointerException, IllegalArgumentException {
        if(inputArray == null){
            assertThrows(NullPointerException.class, () -> DivideAndConquer.findAPeak(inputArray));
        }else if(inputArray.length == 0){
            assertThrows(IllegalArgumentException.class, () -> DivideAndConquer.findAPeak(inputArray));
        }else{
            assertEquals(expectedIndex, DivideAndConquer.findAPeak(inputArray));
        }
    }


    @DisplayName("Find a peak recursive")
    @ParameterizedTest(name = "{index} test case")
    @MethodSource("dataForPeakSearch")
    void findAPeakRecursive(int[] inputArray, int expectedIndex)throws NullPointerException, IllegalArgumentException {
        if(inputArray == null){
            assertThrows(NullPointerException.class, () -> DivideAndConquer.findAPeakRecursive(inputArray, 1, inputArray.length-1));
        }else if(inputArray.length == 0){
            assertThrows(IllegalArgumentException.class, () -> DivideAndConquer.findAPeakRecursive(inputArray, 1, inputArray.length - 1));
        }else{
            assertEquals(expectedIndex, DivideAndConquer.findAPeakRecursive(inputArray, 1, inputArray.length-1));
        }

    }



    @Test
    void binarySearch() {
    }
}