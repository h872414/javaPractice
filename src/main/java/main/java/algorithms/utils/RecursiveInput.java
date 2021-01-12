package main.java.algorithms.utils;

import lombok.NonNull;

public class RecursiveInput {
    @NonNull
    private final int[] inputArray;
    private final int lowerIndex;
    private final int higherIndex;

    /**
     * @param inputArray  need to find a peak in this array
     * @param lowerIndex  left bound to the search interval
     * @param higherIndex right bo1und to the search interval
     */
    public RecursiveInput(
        final int[] inputArray, final int lowerIndex, final int higherIndex
    ) {
        this.inputArray = inputArray;
        this.lowerIndex = lowerIndex;
        this.higherIndex = higherIndex;
        checkIsInputValid();
    }

    public int[] getInputArray() {
        return inputArray;
    }

    public int getLowerIndex() {
        return lowerIndex;
    }

    public int getHigherIndex() {
        return higherIndex;
    }

    public int getMiddle() {
        return lowerIndex + (higherIndex - lowerIndex) / 2;
    }

    public void checkIsInputValid() {
        checkIsArrayEmpty();
        checkIsInputBoundaryValid();
    }

    private void checkIsArrayEmpty() {
        if (inputArray.length == 0) {
            throw new IllegalArgumentException("InputArray can not be empty");
        }
    }

    private void checkIsInputBoundaryValid() {
        if (lowerIndex < 0 || lowerIndex > higherIndex || higherIndex > inputArray.length - 1) {
            throw new IllegalArgumentException("The indexes has to be valid");
        }
    }

    /**
     * Check if end of the recursion
     *
     * @return index of a local peak, or -1 if no peak found
     */
    public int checkIsEndState() {
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
}
