package ru.yakovlev.util;

import java.util.Comparator;
import java.util.List;

public interface ArrayListCust<T> extends List<T> {
 
    /**
     * Sorts the array using the bubble sort
     * @param c: comparator for the type the ArrayListCust is in
     */
    public void sort(Comparator <? super T> c);

}
