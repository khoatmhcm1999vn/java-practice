package com.mk.examples.common;

public class StringTrimExample {

    public static void main(String[] args) {
        String a = "   a   ";
        String b = "\t\tb\t\t";
        String c = "\n\nc\n\n";

        System.out.println("c = " + c.trim() + ", b = " + b.trim() + " a: " + a.trim());
    }

}
