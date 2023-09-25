package com.mk.examples.refactor.collection;

public interface Queue<T> {

    void enQueue(T element);

    T deQueue();

    T peek();
}
