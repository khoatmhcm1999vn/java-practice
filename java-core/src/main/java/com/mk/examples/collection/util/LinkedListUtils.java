package com.mk.examples.collection.util;

import com.mk.examples.collection.list.LinkedList;
import com.mk.examples.collection.list.Node;

import java.util.Objects;

/**
 * This class holds utilities for performing Linked List operations
 */
public class LinkedListUtils {

    private LinkedListUtils() {

    }

    /**
     * This method is used for creating a simple abstraction layer for merging two Linked List operation
     *
     * @param firstList  which is the first Linked List
     * @param secondList which is the second Linked List
     * @param <T>        which implement Comparable to ensure that T can be compared to perform merge operation
     * @return LinkedList<T> return the new LinkedList which is merged these two input Linked Lists
     */
    public static <T extends Comparable<T>> LinkedList<T> merge(LinkedList<T> firstList, LinkedList<T> secondList) {
        LinkedList<T> linkedList = new LinkedList<>();
        Node<T> node = performMerge(firstList.getHead(), secondList.getHead());
        while (!Objects.isNull(node)) {
            T value = node.getValue();
            linkedList.insert(value);
            node = node.getNext();
        }
        return linkedList;
    }

    /**
     * This method is used to perform actually recursion merging operation
     *
     * @param firstNode  this node is must be the head node of the first linked list
     * @param secondNode this node is must be the head node of the second linked list
     * @param <T>        which implement Comparable to ensure that T can be compared to perform merge operation
     * @return Node<T>   which is the head node of the new merged linked list
     */
    private static <T extends Comparable<T>> Node<T> performMerge(Node<T> firstNode, Node<T> secondNode) {
        if (Objects.isNull(firstNode)) {
            return secondNode;
        }
        if (Objects.isNull(secondNode)) {
            return firstNode;
        }
        boolean compareResult = firstNode.getValue().compareTo(secondNode.getValue()) <= 0;
        if (compareResult) {
            Node<T> oldNextNode = firstNode.getNext();
            Node<T> newNextNode = performMerge(oldNextNode, secondNode);
            firstNode.setNext(newNextNode);
            return firstNode;
        }
        Node<T> oldNextNode = secondNode.getNext();
        Node<T> newNextNode = performMerge(firstNode, oldNextNode);
        secondNode.setNext(newNextNode);
        return secondNode;
    }
}
