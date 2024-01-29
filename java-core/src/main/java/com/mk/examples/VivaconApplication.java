package com.mk.examples;

import java.util.List;
import java.util.stream.Collectors;

public class VivaconApplication {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        List<String> ls1 = List.of("a", "b", "c", "d");
        List<Integer> ls2 = List.of(1, 2, 3);
        List<String> lstFinal = ls1
                .stream()
                .map(x -> VivaconApplication.convertListToString(String.valueOf(x), ls2))
                .collect(Collectors.toList());
        System.out.println(lstFinal);
    }

    public static String convertListToString(String e, List<Integer> ls2) {
        return ls2
                .stream()
                .map(i -> e + i)
                .collect(Collectors.joining(","));
    }

}
