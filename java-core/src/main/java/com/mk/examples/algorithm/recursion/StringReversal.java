package com.mk.examples.algorithm.recursion;

public class StringReversal {

    public static void main(String[] args) {
        StringReversal stringReversal = new StringReversal();
        String originalText = "Hello you guys !";
        String reverseString = stringReversal.reverseString(originalText);
        System.out.println(reverseString);
    }

    public String reverseString(String text) {
        if (text.isEmpty()) {
            return "";
        }
        return reverseString(text.substring(1)) + text.charAt(0);
    }
}
