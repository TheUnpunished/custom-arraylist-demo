package ru.yakovlev.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayListCustImpl<T> implements ArrayListCust<T> {

    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;

    /**
     * Check the size (number of elemets)
     * @return the size of the array list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Initializes the ArrayList with internal array that is of the initial size
     * @param initialSize initial size of the internal array
     * @throws IllegalArgumentException if the initial size parameter is lower than 0
     */
    public ArrayListCustImpl (int initialSize){
        if (initialSize >= 0) {
            this.elements = new Object[initialSize];
        }
        else {
            throw new IllegalArgumentException("Initial size is incompatible : " + initialSize);
        }
    }

    /**
     * Initializes an empty ArrayListCust
     */
    public ArrayListCustImpl() {
        elements = new Object[INITIAL_CAPACITY];
    }


    /**
     * Checks if the array list has non-null elements
     * @return size of the arraylist
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Searches the ArrayListCust for the passed object
     * @return true if found, false otherwise
     * @param Object to find
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    /**
     * Get the Array representation of the ArrayList
     * @return Array of Object[]
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    /**
     * Adds an element to the ArrayListCust
     * If the internal array is too small, extend it
     * @param e: element to add
     * @return true if element was added
     */
    @Override
    public boolean add(T e) {
        int lastIndex = size;
        if(lastIndex == elements.length)
            elements = increaseCapacity();
        elements[lastIndex] = e;
        size++;
        return true;
    }

    private Object[] increaseCapacity(int increaseTo){
        int oldSize = elements.length;
        if(increaseTo < 0){
            throw new IllegalArgumentException("Illegal size : " + increaseTo);
        }
        if(oldSize > 0) {
            int prefSize = oldSize * 2;
            return elements = Arrays.copyOf(elements, prefSize > increaseTo ? prefSize : increaseTo);
        }
        else {
            return elements = new Object[increaseTo > INITIAL_CAPACITY ? increaseTo : INITIAL_CAPACITY];
        }
    }

    private Object[] increaseCapacity(){
        return increaseCapacity(size + 1);
    }


    /**
     * Searches an for the first occurence of the object in the ArrayListCust and deletes it
     * @param Object o, object to delete
     * @return true, if object is found and deleted, otherwise false
     */
    @Override
    public boolean remove(Object o) {
        int idx = indexOf(o);
        if(idx >= 0){
            remove(idx);
            return true;
        }
        return false;
    }

    /**
     * Checks, whether the ArrayListCust contains all of the elements from a collection
     * @param Collection c of elements to check for
     * @return true, if ArrayListCust has all elements of c, false otherwise
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
        boolean containsAll = true;
        while(containsAll && iterator.hasNext()){
            Object obj = iterator.next();
            containsAll = contains(obj);
        }
        return containsAll;
    }

    /**
     * Adds all elements from another collection
     * @param Collection c, collection of elements to add
     * @return true, if all elements have been added
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] arrayOfC = c.toArray();
        int slotsNeeded = arrayOfC.length;
        int slotsLeft = elements.length - size;
        if(slotsLeft > slotsNeeded)
            elements = increaseCapacity(size + slotsNeeded);
        System.arraycopy(arrayOfC, 0, elements, size, slotsNeeded);
        size += slotsNeeded;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    /**
     * Remove all the elements from the ArrayListCust if they are in collection
     * @param Collection c, which has all elements to remove
     * @return true, when all elements are removed
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            int idx;
            while((idx = indexOf(obj)) >= 0)
                remove(idx);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    /**
     * Empty the entire ArrayListCust and set the size to 0
     */
    @Override
    public void clear() {
        for(int i = 0; i < size; i++)
            elements[i] = null;
        size = 0;
    }

    private void checkIndex(int index){
        if(index >= size || index < 0){
            throw new ArrayIndexOutOfBoundsException("Out of bounds : " + index);
        }
    }

    /**
     * Get the element that is from the corresponding index
     * @param int index, the index to get the element from
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
     * @return the element from that index
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    /**
     * Replace the element that is in the position of the passed index with a passed element
     * @param int index, index of which element to replace
     * @param T element, the element to replace with
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
     * @return T element that got replaced
     */
    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T elementBefore = (T) elements[index];
        elements[index] = element;
        return elementBefore;
    }

    @Override
    public void add(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    /**
     * Deletes the element from the desired index
     * @param int index, index of which element to remove
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
     * @return T element that got removed
     */
    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldElement = (T) elements[index];
        for(int i = size - 1; i > index; i --)
            elements[i - 1] = elements[i];
        elements[size - 1] = null;
        size --;
        return oldElement;
    }

    private int indexOfRange(Object obj, int start, int end){
        for(int i = start; i < end; i ++){
            if (obj == null) 
                if(elements[i] == null) 
                    return i;
            else
                if(elements[i].equals(obj))
                    return i;
        }
        return -1;
    }

    /**
     * Returns the index of the desired object
     * @return the index of the object, if one was found, -1 otherwise
     */
    @Override
    public int indexOf(Object o) {
        return indexOfRange(o, 0, size);
    }

    private int lastIndexOfRange(Object obj, int start, int end){
        for(int i = end - 1; i >= start; i --){
            if (obj == null) 
                if(elements[i] == null) 
                    return i;
            else
                if(elements[i].equals(obj))
                    return i;
        }
        return -1;
    }

    /**
     * Returns the index of last occurence the desired object
     * @return the index of the object, if one was found, -1 otherwise
     */
    @Override
    public int lastIndexOf(Object o) {
        return lastIndexOfRange(o, 0, size);
    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subList'");
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Object temp;
        boolean swapped;
        for(int i = 0; i < size - 1; i++){
            swapped = false;
            for(int j = 0; j < size - i - 1; j ++){
                if(c.compare((T) elements[j], (T) elements[j + 1]) == 1) {
                    temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped)
                break;
        }
    }
    
}
