package com.mk.examples.algorithm.recursion;

public class SumOfNaturalNumber {

    public static void main(String[] args) {
        SumOfNaturalNumber sumOfNaturalNumber = new SumOfNaturalNumber();
        int number = 5;
        int finalResult = sumOfNaturalNumber.sumOfNaturalNumber(number);
        System.out.println(finalResult);
    }

    public int sumOfNaturalNumber(int number) {
        System.out.println(number);
        if (number == 1) {
            return 1;
        }
        return number + sumOfNaturalNumber(number - 1);
    }
}
