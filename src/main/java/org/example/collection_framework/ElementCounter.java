package org.example.collection_framework;

import java.util.HashMap;
import java.util.Map;

public class ElementCounter {

    public static <T> Map<T, Integer> counterElement(T[] array) {
        Map<T, Integer> countMap = new HashMap<>();

        if (array == null) {
            return countMap;
        }
        for (T element : array) {
            countMap.put(element, countMap.getOrDefault(element, 0) + 1);
        }
        return countMap;
    }
}
