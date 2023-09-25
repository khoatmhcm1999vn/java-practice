package com.mk.examples.refactor.presentation;

import com.mk.examples.refactor.collection.Iterator;
import com.mk.examples.refactor.collection.LinkedList;
import com.mk.examples.refactor.collection.List;
import com.mk.examples.refactor.util.LinkedListUtils;

public class LinkedListPresentation {

    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<>();
        linkedList.insertAt(20, 1);

        System.out.println(linkedList);
        System.out.println(linkedList.size());
        System.out.println(linkedList.indexOf(9));
        System.out.println(linkedList.getAt(1));

        linkedList.reverse();
        System.out.println(linkedList);

        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println();

        LinkedList<Integer> originalList = new LinkedList<>();
        originalList.insert(3);
        originalList.insert(4);
        originalList.insert(9);
        System.out.println(originalList);

        LinkedList<Integer> otherList = new LinkedList<>();
        otherList.insert(-13);
        otherList.insert(6);
        otherList.insert(8);
        System.out.println(otherList);

        List<Integer> mergeList = LinkedListUtils.merge(originalList, otherList);
        System.out.println(mergeList);
    }
}
