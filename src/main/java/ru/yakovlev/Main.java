package ru.yakovlev;

import java.util.List;

import ru.yakovlev.util.ArrayListCustImpl;
import ru.yakovlev.util.Sorter;
import ru.yakovlev.util.SorterImpl;

public class Main {
    public static void main(String[] args) {
        List<String> myList = new ArrayListCustImpl<>();
        myList.add("0");
        myList.add("Test");
        myList.add("Aston");
        myList.add("Homework");
        myList.add("Training");
        System.out.println(myList.size());
        Sorter<String> sorter = new SorterImpl<String>();
        sorter.quickSort(myList, String.CASE_INSENSITIVE_ORDER);
        for(int i = 0; i < myList.size(); i ++){
            System.out.println(myList.get(i));
        }
    }
}