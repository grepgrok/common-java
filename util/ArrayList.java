// package util;

import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * MyArrayList Class: This class will be similar to the ArrayList data
 * structure. It will house an array. It will utilize generics and have the
 * methods…
 * 
 * + add(E) : boolean - Add the element E to the end of list. Return true if
 * successful and false otherwise.
 * + get(int) : E - Return the element at a specific location.
 * + remove(int) : E - Remove an element at a specific location. Return the
 * element removed.
 * + set(i,E) : void - Replace an element at specific location.
 * + toString() : String - Return a string of all the elements within the list
 * + size() : int - Return the size.
 * 
 * Add more methods as needed.
 * 
 */

public class ArrayList<E> implements Iterable<E> {
    public static final int INITIAL_CAPACITY = 10;
    public static final int CAPACITY_INCREASE = 10;

    private Object[] array;
    private int size;

    public ArrayList(int capacity) {
        array = new Object[capacity];
        size = 0;
    }
    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return array.length;
    }

    public boolean add(E newItem) {
        return add(size, newItem);
    }

    public void correctCapacity(int num) {
        if (size + num >= array.length) {
            Object[] res = new Object[size + num + CAPACITY_INCREASE];
            for (int i = 0; i < size(); i++) {
                res[i] = array[i];
            }
            array = res;
        }
    }

    public boolean add(int index, E newItem) {
        try {
            Object[] res;
            if (size == array.length) {
                res = new Object[array.length + CAPACITY_INCREASE];

                for (int i = 0; i < array.length; i++) {
                    res[i] = array[i];
                }

            } else {
                res = array;
            }

            for (int i = size - 1; i >= index; i--) {
                res[i + 1] = res[i];
            }

            res[index] = newItem;
            size++;

            array = res;
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public E get(int i) {
        return (E) array[i];
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        E res = (E) array[index];
        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[array.length - 1] = null;
        size--;
        return res;
    }

    public E remove(E remove) {
        return remove(indexOf(remove));
    }

    public int indexOf(E search) {
        for (int i = 0; i < size; i++) {
            if (get(i).equals(search)) {
                return i;
            }
        }
        return -1;
    }

    public boolean set(int index, E newItem) {
        if (index < size + 1) {
            if (index == size) {
                size++;
            }
            array[index] = newItem;
            return true;
        } else {
            return false;
        }
    }

    public void swap(int a, int b) {
        E temp = get(a);
        set(a, get(b));
        set(b, temp);
    }

    @SuppressWarnings("unchecked")
    public String toString() {
        String res = "[";

        if (size != 0) {
            for (int i = 0; i < size; i++) {
                res += (E) array[i].toString() + " \n";
            }
    
            return res.substring(0, res.length() - 2) + "]";
        } else {
            return "[]";
        }
    }

    @SuppressWarnings("unchecked")
    public String toStringln() {
        String res = "";

        for (int i = 0; i < size; i++) {
            res += (E) array[i].toString() + "\n";
        }

        return res.substring(0, res.length() - 1);
    }

    public void scramble() {
        for (int i = 0; i < size; i++) {
            E temp = get(i);
            int j = (int) (Math.random() * size);
            set(i, get(j));
            set(j, temp);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        // Adjust array size
        if (a.length < size) {
            a = (T[]) Array.newInstance(a.getClass().getComponentType(), size); // make a the correct size if it is too
                                                                                // small
        } else if (a.length > size) {
            a[size] = null; // null is used as a cutoff for the array
        }

        System.arraycopy(array, 0, a, 0, size); // copy array -> a
        return a;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int currentIndex = 0;

            public boolean hasNext() {
                return currentIndex < size && array[currentIndex] != null;
            }

            @SuppressWarnings("unchecked")
            public E next() {
                return (E) array[currentIndex++];
            }

        };
    }
}