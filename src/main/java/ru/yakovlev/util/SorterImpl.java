package ru.yakovlev.util;

import java.util.Comparator;
import java.util.List;

public class SorterImpl<T> implements Sorter<T> {

    @Override
    public void quickSort(List<? super T> list, Comparator<? super T> c) {
        quickSort(list, c, 0, list.size() - 1);
    }

    @Override
    public void quickSort(List<? super T> list, Comparator<? super T> c, int begin, int end){
        if(begin < end){
            int partitionIndex = partition(list, c, begin, end);
            quickSort(list, c, begin, partitionIndex - 1);
            quickSort(list, c, partitionIndex + 1, end);
        }
    }

    private int partition(List<? super T> list, Comparator<? super T> c,  int begin, int end){
        T pivot = (T) list.get(end);
        int i = begin - 1;
        for(int j = begin; j < end; j++){
            if(c.compare((T) list.get(j), pivot) <= 0){
                i ++;
                swapInList(list, i, j);
            }
        }
        swapInList(list, i + 1, end);
        return i + 1;
    }

    private void swapInList(List<? super T> list, int i, int j){
        T swapTemp = (T) list.get(i);
        list.set(i, (T) list.get(j));
        list.set(j, swapTemp);
    }
    
}
