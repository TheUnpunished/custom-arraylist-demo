package ru.yakovlev.util;

import java.util.Comparator;
import java.util.List;

public interface Sorter<T> {

    /**
     * Sorts the entire list using quicksort algorithm
     * @param list, list to sort
     * @param c, comparator to use
     */
    public void quickSort(List<? super T> list, Comparator<? super T> c);
    /**
     * Sorts the only the part of the list using quicksort algorithm
     * @param list, list to sort
     * @param c, comparator to use
     * @param begin, start index
     * @param end, end index INCLUDING
     */
    public void quickSort(List<? super T> list, Comparator<? super T> c, int begin, int end);

}
