package com.minhkhoa.algorithm.recursion;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    private Map<Integer, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        int number = 6;
        int result = fibonacci.calculate(number);
        System.out.println(result);
    }

    public int calculate(int number) {

        if (number < 0) {
            throw new IllegalArgumentException(
                    "Index was negative. No such thing as a negative index in a series.");
        }

        else if (number == 0 || number == 1) {
            return number;
        }

        if (memo.containsKey(number)) {
            System.out.printf("grabbing memo[%d]\n", number);
            return memo.get(number);
        }

        System.out.printf("computing fib(%d)\n", number);
        int result = calculate(number - 1) + calculate(number - 2);

        // memoize
        memo.put(number, result);

        return result;

        //return calculate(number - 1) + calculate(number - 2);
    }
}
