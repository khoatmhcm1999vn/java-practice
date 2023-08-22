package com.minhkhoa.collection.queue;
import com.minhkhoa.collection.list.LinkedList;

import java.util.Objects;

public class SimpleQueueImpl<T> implements Queue<T> {

    LinkedList<T> items = new LinkedList<T>();

    public SimpleQueueImpl() {

    }

    public boolean isEmpty() {
        if (items.isEmpty())
            return true;
        else
            return false;
    }

    @Override
    public void enQueue(T element) {
        items.insert(element);
    }

    @Override
    public T deQueue() {
        T deletedValue = items.removeTail();
        if (Objects.isNull(deletedValue)) {
            System.out.println("Queue is empty");
            return null;
        }
        return deletedValue;
    }

    @Override
    public T peek(){
        return this.items.getTail().getValue();
    }

    @Override
    public String toString() {
        return this.items.toString();
    }
}