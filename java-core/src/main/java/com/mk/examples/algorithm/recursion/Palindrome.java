package com.mk.examples.algorithm.recursion;

public class Palindrome {

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        String originalText = "akaka";
        boolean isPalindrome = palindrome.isPalindrome(originalText);
        System.out.println(isPalindrome);
    }

    public boolean isPalindrome(String text) {
        int length = text.length();
        if (length == 0 || length == 1) {
            return true;
        }
        if (text.charAt(0) == text.charAt(length - 1)) {
            return isPalindrome(text.substring(1, length - 1));
        }
        return false;
    }
}
