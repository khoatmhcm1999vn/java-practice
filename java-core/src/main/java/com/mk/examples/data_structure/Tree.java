package com.mk.examples.data_structure;

public interface Tree<T extends Comparable> {
    T insert(T value);

    boolean contain(T value);

    void remove(T value);
}
