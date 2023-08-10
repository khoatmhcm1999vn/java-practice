package com.minhkhoa.presentation;

import com.minhkhoa.collection.Iterator;
import com.minhkhoa.collection.list.ArrayList;
import com.minhkhoa.collection.list.List;

public class ArrayListPresentation {
    public static void main(String[] args) {
        List<Integer> arrList = new ArrayList<>(3);
        arrList.insert(3);
        arrList.insert(4);
        arrList.insert(9);
        arrList.insertAt(20, 1);
        System.out.println(arrList.toString());

        arrList.reverse();
        Iterator<Integer> iterator = arrList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
