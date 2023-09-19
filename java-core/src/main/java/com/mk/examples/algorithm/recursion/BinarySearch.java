package com.mk.examples.algorithm.recursion;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 5, 6, 9, 11};
        int target = 12;
        BinarySearch binarySearch = new BinarySearch();
        int index = binarySearch.binarySearch(arr, 0, arr.length - 1, target);
        System.out.println(index);
    }

    public int binarySearch(int[] arr, int startIndex, int endIndex, int target) {
        System.out.println(startIndex + ":" + endIndex);
        if (startIndex > endIndex) {
            return -1;
        }
        int midIndex = (startIndex + endIndex) / 2;
        int currentValue = arr[midIndex];

        if (currentValue == target) {
            return midIndex;
        }
        if (currentValue > target) {
            return binarySearch(arr, startIndex, midIndex - 1, target);
        }
        return binarySearch(arr, midIndex + 1, endIndex, target);
    }
}
