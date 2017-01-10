package com.shandrakov.sandbox.model.functional;

import java.util.ArrayList;
import java.util.List;

public final class ListUtil {

    /**
     *   |head|
     *   | 01 | 02 | 03 | 04 | 05 |
     *
     */
    public static <T> T head(List<T> list) {
        return list.get(0);
    }

    /**
     *        |-------tail--------|
     *   | 01 | 02 | 03 | 04 | 05 |
     *
     *   for list with size less than two elements return empty list
     */
    public static <T> List<T> tail(List<T> list) {
        return list.size() > 1
                ? list.subList(1, list.size())
                : new ArrayList();
    }

    /**
     *
     *   |-------init--------|
     *   | 01 | 02 | 03 | 04 | 05 |
     *
     *   for list with size less than two elements return empty list
     */
    public static <T> List<T> init(List<T> list) {
        return list.size() > 1
                ? list.subList(0, list.size() - 1)
                : new ArrayList();
    }

    /**
     *
     *                       |last|
     *   | 01 | 02 | 03 | 04 | 05 |
     *
     */
    public static <T> T last(List<T> list) {
        return list.get(list.size()-1);
    }

    /**
     *
     *   |----take 3----|
     *   | 01 | 02 | 03 | 04 | 05 |
     *
     */
    public static <T> List<T> take(List<T> list, int n) {
        if (list.isEmpty()) return new ArrayList();
        final int takeElements = Math.min(n, list.size());
        return list.subList(0, takeElements);
    }

    /**
     *
     *             |----drop 2----|
     *   | 01 | 02 | 03 | 04 | 05 |
     *
     */
    public static <T> List<T> drop(List<T> list, int n) {
        if (list.isEmpty()) return new ArrayList();
        final int dropElements = Math.min(n, list.size());
        return list.subList(dropElements, list.size());
    }
}
