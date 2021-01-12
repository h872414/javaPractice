package main.java.algorithms;

import lombok.NonNull;

public class DivideAndConquer {
    /**
     * Use divide & conquer to find a local peak, divide the inputArray n/2 part and search for a peak in a smaller part
     *
     * @param inputArray need to find a peak in this array
     *
     * @return index of a local peak
     */
    public Integer findAPeak(final @NonNull int[] inputArray) {
        checkIsArrayEmpty(inputArray);
        checkIsPeakAvailable(inputArray);
        return lookForPeak(inputArray);
    }

    private void checkIsArrayEmpty(int[] inputArray) {
        if (inputArray.length == 0) {
            throw new IllegalArgumentException("InputArray can not be empty");
        }
    }

    private void checkIsPeakAvailable(int[] inputArray) {
        if (inputArray.length < 3) {
            throw new IllegalArgumentException("Can not find peak in array, which is smaller than 3");
        }
    }

    /**
     * Look for a "peak" in an array, where a given indexed element is greater than its neighbours
     * <p>Algorithm:</p>
     * <ul>
     *     <li>If {@code middle> is greater than its neighbours return middle</li>
     *     <li>If {code middle-1} is greater than {@code middle} update the {@code higherIndex}</li>
     *     <li>Else update {@code lowerIndex}</li>
     * </ul>
     *
     * @param inputArray look for peak in this array
     *
     * @return return the index of the peak, or return -1 if no peak exists
     */
    private int lookForPeak(int[] inputArray) {
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
     * @param higherIndex right bo1und to the search interval
     *
     * @return index of a local peak
     */
    public Integer findAPeakRecursive(final @NonNull int[] inputArray, final int lowerIndex, final int higherIndex) {
        checkIsInputValid(inputArray, lowerIndex, higherIndex);
        checkIsPeakAvailable(inputArray);
        final int peak = checkIsEndState(inputArray, lowerIndex, higherIndex);

        if (peak != -1) {
            return peak;
        }
        return lookForPeakRecursive(inputArray, lowerIndex, higherIndex);
    }

    private void checkIsInputValid(final int[] inputArray, final int lowerIndex, final int higherIndex) {
        checkIsArrayEmpty(inputArray);
        checkIsInputBoundaryValid(inputArray, lowerIndex, higherIndex);
    }

    private void checkIsInputBoundaryValid(int[] inputArray, int lowerIndex, int higherIndex) {
        if (lowerIndex < 0 || lowerIndex > higherIndex || higherIndex > inputArray.length - 1) {
            throw new IllegalArgumentException("The indexes has to be valid");
        }
    }

    /**
     * Check if end of the recursion
     *
     * @param inputArray  need to find a peak in this array
     * @param lowerIndex  left bound to the search interval
     * @param higherIndex right bo1und to the search interval
     *
     * @return index of a local peak, or -1 if no peak found
     */
    private int checkIsEndState(int[] inputArray, int lowerIndex, int higherIndex) {
        if (higherIndex - lowerIndex <= 1) {
            if (checkIsLeftBound(inputArray, lowerIndex)) {
                return lowerIndex;
            } else if (checkIsRightBound(inputArray, higherIndex)) {
                return higherIndex;
            }
        }
        return -1;
    }

    private boolean checkIsRightBound(int[] inputArray, int higherIndex) {
        return inputArray[higherIndex] >= inputArray[higherIndex - 1] && higherIndex + 1 < inputArray.length - 1;
    }

    private boolean checkIsLeftBound(int[] inputArray, int lowerIndex) {
        return inputArray[lowerIndex] >= inputArray[lowerIndex + 1] && lowerIndex > 1;
    }

    /**
     * * Look for a "peak" in an array, where a given indexed element is greater than its neighbours using recursion
     * * <p>Algorithm:</p>
     * * <ul>
     * *     <li>If {@code inputArray[middle]> is less than {@code inputArray[middle - 1]} call recursion
     * with updated indexes => {@code middle - 1}</li>
     * *     <li>If {@code inputArray[middle]> is less than {@code inputArray[middle + 1]} call recursion
     * with updated indexes => {@code middle + 1}</li>
     * *     <li>Else return {@code middle}</li>
     * * </ul>
     *
     * @param inputArray  need to find a peak in this array
     * @param lowerIndex  left bound to the search interval
     * @param higherIndex right bo1und to the search interval
     *
     * @return return middle if it find a peak, else call recursion
     */
    private int lookForPeakRecursive(int[] inputArray, int lowerIndex, int higherIndex) {
        final int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
        if (inputArray[middle] < inputArray[middle - 1]) {
            return middle - 1 > 0 ? findAPeakRecursive(inputArray, lowerIndex, middle - 1) : -1;
        } else if (inputArray[middle] < inputArray[middle + 1]) {
            return middle + 1 < inputArray.length - 1 ? findAPeakRecursive(inputArray, middle + 1, higherIndex) : -1;
        }
        return middle;
    }

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
