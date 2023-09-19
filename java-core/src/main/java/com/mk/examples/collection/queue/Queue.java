package com.mk.examples.collection.queue;

public interface Queue<T> {

    void enQueue(T element);

    T deQueue();

    T peek();
}
