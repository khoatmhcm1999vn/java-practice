package com.mk.examples.algorithm.recursion;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] array = new int[]{21, 32, 13, 34, 25, 16, 17, 8};
        MergeSort mergeSort = new MergeSort();
        int[] orderedArr = mergeSort.performSort(array);
        System.out.println(Arrays.toString(orderedArr));
    }

    public int[] performSort(int[] array) {
        if (array.length == 1) {
            return array;
        }
        int midIndex = array.length / 2;
        int[] firstHalf = Arrays.copyOfRange(array, 0, midIndex);
        int[] secondHalf = Arrays.copyOfRange(array, midIndex, array.length);
        return merge(performSort(firstHalf), performSort(secondHalf));
    }

    private int[] merge(int[] firstArray, int[] secondArray) {
        int counter = 0;
        int firstCursor = 0;
        int secondCursor = 0;
        int firstLength = firstArray.length;
        int secondLength = secondArray.length;
        int totalLength = firstArray.length + secondArray.length;

        int[] combinedOrderedArray = new int[totalLength];
        while (firstCursor < firstLength && secondCursor < secondLength) {
            if (firstArray[firstCursor] <= secondArray[secondCursor]) {
                combinedOrderedArray[counter] = firstArray[firstCursor];
                firstCursor++;
            } else {
                combinedOrderedArray[counter] = secondArray[secondCursor];
                secondCursor++;
            }
            counter++;
        }

        firstArray = Arrays.copyOfRange(firstArray, firstCursor, firstLength);
        secondArray = Arrays.copyOfRange(secondArray, secondCursor, secondLength);
        combinedOrderedArray = Arrays.copyOfRange(combinedOrderedArray, 0, counter);
        combinedOrderedArray = this.concatenateMultipleArrays(combinedOrderedArray, firstArray, secondArray);
        return combinedOrderedArray;
    }

    private int[] concatenateMultipleArrays(int[]... arrays) {
        int length = 0;
        for (int[] array : arrays) {
            length += array.length;
        }
        int[] result = new int[length];
        int pos = 0;
        for (int[] array : arrays) {
            for (int element : array) {
                result[pos] = element;
                pos++;
            }
        }
        return result;
    }
}
