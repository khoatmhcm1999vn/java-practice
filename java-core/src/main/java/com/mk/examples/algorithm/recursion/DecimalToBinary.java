package com.mk.examples.algorithm.recursion;

public class DecimalToBinary {

    public static void main(String[] args) {
        DecimalToBinary decimalToBinary = new DecimalToBinary();
        int number = 13;
        String binary = decimalToBinary.toBinary(number, "");
        System.out.println(binary);
    }

    public String toBinary(int number) {
        if (number == 1) {
            return String.valueOf(1);
        }
        String remainder = String.valueOf(number % 2);
        return toBinary(number / 2) + remainder;
    }

    public String toBinary(int number, String result) {
        if (number == 0) {
            return result;
        }
        result = number % 2 + result;
        return toBinary(number / 2, result);
    }
}
