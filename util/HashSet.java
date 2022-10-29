import java.util.Iterator;

/**
 * MyHashSet
 * Write your own MyHashSet. See the instructions in the slides. In your
 * MyHashSet, have the following method.
 * add(E) : boolean
 * clear() : void
 * contains(Object) : boolean
 * remove(Object) : boolean
 * size() : int
 * 
 * +toDLList : DLList<E> - This will return a DLList<E> of all the items in your
 * MyHashSet. (Hint: This is similar to the toString method.) (Alternatively, if
 * you want to return an Iterator<E>, you can too.) (Also, you can also
 * add @SuppressWarnings("unchecked") due to the generic type warning ).
 */

public class HashSet<E> implements Iterable<E> {
    public static final int ALLOCATED_LENGTH = 100;
    private Object[] hashTable;
    private int size;

    public HashSet() {
        hashTable = new Object[ALLOCATED_LENGTH];
        size = 0;
    }

    public boolean add(E add) {
        if (!contains(add)) {
            hashTable[indexOf(add)] = add;
            size++;
            return true;
        }
        return false;
    }

    public void clear() {
        hashTable = new Object[ALLOCATED_LENGTH];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public boolean contains(E check) {
        E against = (E) hashTable[indexOf(check)];
        if (against == null) {
            return false;
        }
        return against.equals(check);
    }

    public boolean remove(E remove) {
        if (contains(remove)) {
            hashTable[indexOf(remove)] = null;
            size--;
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int current = 0;
            private int lastIndex = 0;

            public boolean hasNext() {
                return current != size;
            }

            @SuppressWarnings("unchecked")
            public E next() {
                current++;
                E res = null;
                for (int i = lastIndex; i < hashTable.length; i++) {
                    if (hashTable[i] != null) {
                        res = (E) hashTable[i];
                        i++;
                        lastIndex = i;
                        break;
                    }
                }
                return res;
            }
        };
    }

    private int indexOf(E item) {
        return item.hashCode() % ALLOCATED_LENGTH;
    }
}