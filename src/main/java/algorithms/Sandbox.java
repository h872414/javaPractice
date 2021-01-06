package main.java.algorithms;

public class Sandbox {
    public static void main(String[] args){
        int[] array = {1, 4, 1, 2, 1, 3, 5, 1, 3};
        int[] arrayBinarySearch = {5, 6, 20, 30, 30, 66, 69};
//        int peak = DivideAndConquer.findAPeakRecursive(array, 1, array.length - 2);
//        int peak = DivideAndConquer.findAPeak(array);
//        System.out.print(array[peak]);
        int searchResult = DivideAndConquer.binarySearch(arrayBinarySearch, 0, arrayBinarySearch.length-1, 100);
        System.out.print(searchResult);
    }

    private static void printArray(int[] inputArray){
        for(int element : inputArray ){
            System.out.print(element + " ;");
        }
    }
}
