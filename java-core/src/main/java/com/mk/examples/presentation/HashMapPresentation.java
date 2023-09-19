package com.mk.examples.presentation;

import com.mk.examples.collection.map.HashMap;
import com.mk.examples.collection.map.Map;

public class HashMapPresentation {

    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 1);
        map.put('b', 2);
        map.put('c', 3);
        map.put('d', 4);
        map.put('e', 5);
        map.put('f', 6);
        map.put('g', 7);
        map.put('h', 8);
        map.put('i', 9);
        map.put('j', 10);
        map.put('k', 11);
        map.put('l', 12);
        map.put('m', 13);
        map.put('n', 14);
        map.put('n', 15);

        Map<Character, Integer> map1 = new HashMap<>();
        map1.put('o', 16);
        map1.put('p', 17);
        map1.put('q', 18);
        map.putAll(map1);
        map1.containsKey('o');
        map1.containsValue(16);
        System.out.println(map);
        map.remove('b');
        System.out.println(map);
    }
}
