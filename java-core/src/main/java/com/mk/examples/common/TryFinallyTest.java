package com.mk.examples.common;

public class TryFinallyTest {

    public static void main(String[] args) {
        hello();
    }

    public static void hello() {
        try {
            System.out.print("hello ");
            return;
        } finally {
            System.out.print("world");
        }
    }

}
