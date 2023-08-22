package com.minhkhoa.algorithm.recursion;

import com.minhkhoa.collection.list.LinkedList;
import com.minhkhoa.collection.list.List;

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
