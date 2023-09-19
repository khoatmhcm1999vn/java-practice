package com.mk.examples.algorithm.recursion;

import com.mk.examples.collection.list.LinkedList;
import com.mk.examples.collection.list.List;

public class LinkedListReversal {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.insert(4);
        list.insert(3);
        list.insert(12);
        list.insert(1);
        list.insert(5);
        System.out.println(list);
        list.reverse();
        System.out.println(list);
    }
}
