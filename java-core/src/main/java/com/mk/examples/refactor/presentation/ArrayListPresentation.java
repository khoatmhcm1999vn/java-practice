package com.mk.examples.refactor.presentation;

import com.mk.examples.refactor.collection.ArrayList;
import com.mk.examples.refactor.collection.Iterator;
import com.mk.examples.refactor.collection.List;

public class ArrayListPresentation {
    public static void main(String[] args) {
        List<Integer> arrList = new ArrayList<>(3);
        arrList.insert(3);
        arrList.insert(4);
        arrList.insert(9);
        arrList.insertAt(20, 1);
        System.out.println(arrList);

        arrList.reverse();
        Iterator<Integer> iterator = arrList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
