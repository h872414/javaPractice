package main.java.algorithms;

import com.sun.istack.internal.NotNull;

public class DivideAndConquer {
    /**
     * Use divide & conquer to find a local peak, divide the inputArray n/2 part and search for a peak in a smaller part
     *
     * @param inputArray need to find a peak in this array
     * @return index of a local peak
     */
    public static Integer findAPeak(int[] inputArray) throws NullPointerException, IllegalArgumentException{
        if(inputArray == null) throw new NullPointerException();
        if(inputArray.length == 0) throw  new IllegalArgumentException();

        int lowerIndex = 1;
        int higherIndex = inputArray.length - 2;

        while (lowerIndex <= higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            if (inputArray[middle] < inputArray[middle - 1]) {
                higherIndex = middle - 1;
            } else if (inputArray[middle] < inputArray[middle + 1]) {
                lowerIndex = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * Use divide & conquer to find a local peak, using recursion
     *
     * @param inputArray  need to find a peak in this array
     * @param lowerIndex  left bound to the search interval
     * @param higherIndex right bound to the search interval
     * @return index of a local peak
     */
    public static Integer findAPeakRecursive(int[] inputArray, int lowerIndex, int higherIndex) throws NullPointerException, IllegalArgumentException{
        if(inputArray == null) throw new NullPointerException();
        if(inputArray.length == 0 || ( lowerIndex < 0 || lowerIndex > higherIndex) || (higherIndex < lowerIndex || higherIndex > inputArray.length - 1)) throw  new IllegalArgumentException();

        if (higherIndex - lowerIndex <= 1) {
            if (inputArray[lowerIndex] >= inputArray[lowerIndex + 1]) {
                return lowerIndex;
            } else if (inputArray[higherIndex] >= inputArray[higherIndex - 1]) {
                return higherIndex;
            }
        }

        int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
        if (inputArray[middle] < inputArray[middle - 1]) {
            return findAPeakRecursive(inputArray, lowerIndex, middle - 1);
        } else if (inputArray[middle] < inputArray[middle + 1]) {
            return findAPeakRecursive(inputArray, middle + 1, higherIndex);
        }
        return middle;
    }

    /**
     * Search in an ordered input for specified key
     * @param inputArray
     * @param leftBound
     * @param rightBound
     * @param key
     * @return
     * - the index if the key,
     * - -1 the array does not contains the key
     */
    public static int binarySearch(int[] inputArray, int leftBound, int rightBound, int key) {

        if (leftBound < rightBound) {
            return -1;
        }
        int middle = leftBound + (rightBound - leftBound) / 2;
        if (key == inputArray[middle]) {
            return middle;
        }
        if (key < inputArray[middle]) {
            return binarySearch(inputArray, leftBound, middle - 1, key);
        } else {
            return binarySearch(inputArray, middle + 1, rightBound, key);
        }
    }

}
