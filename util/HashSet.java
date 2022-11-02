import java.util.Iterator;
import java.util.Optional;

public class HashSet<E> implements Iterable<E> {
    public static final int DEFAULT_CAPACITY = 100;
    private Object[] hashTable;
    private int size;

    public HashSet(int capacity) {
        hashTable = new Object[capacity];
        size = 0;
    }

    public HashSet() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Adds value to the set
     * 
     * @param value     The value to add
     * @param replace   Whether or not to replace existing values
     * @return If replace is true, then original value (may be null); else, the given value if it was added and null otherwise
     */
    @SuppressWarnings("unchecked")
    public Optional<E> add(E value, boolean replace) {
        Optional<E> old = Optional.ofNullable(null);
        int index = indexOf(value);

        if (replace) {
            if (!contains(value)) {
                size++;
            }
        
            old = Optional.ofNullable((E) hashTable[index]);
            hashTable[index] = value;

        } else {
            if (!contains(value)) {
                hashTable[index] = value;
                size++;
                // technically current but otherwise there is no way to tell if added
                // successfully
                old = Optional.of((E) hashTable[index]);
            }
        }
        return old;
    }

    public boolean add(E add) {
        return add(add, false).isPresent();
    }

    public void clear() {
        hashTable = new Object[hashTable.length];
        size = 0;
    }

    /**
     * Check if the value is in the set
     * 
     * @param value The value to check
     * @return Whether or not value is in the set. If there is a hashCode collision
     *         (something is already there but it isn't the same as value), then
     *         contains will return false
     */
    @SuppressWarnings("unchecked")
    public boolean contains(E value) {
        E against = (E) hashTable[indexOf(value)];
        if (against == null) {
            return false;
        }
        // There is a chance that we have a hashCode collision, in which case, value is not in the set
        return against.equals(value);
    }

    @SuppressWarnings("unchecked")
    public E get(E e) {
        return (E) hashTable[indexOf(e)];
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
            // We can't just go step by step, we want to loop past null's so we keep track of the lastIndex
            // Count lets us know when there is nothing left
            private int count = 0;
            private int lastIndex = 0;

            public boolean hasNext()  {
                return count != size;
            }

            @SuppressWarnings("unchecked")
            public E next() {
                count++;
                E res = null;
                // Loop from last value
                for (int i = lastIndex + 1; i < hashTable.length; i++) {
                    if (hashTable[i] != null) {
                        res = (E) hashTable[i];
                        lastIndex = i;
                        break;
                    }
                }
                return res;
            }
        };
    }

    private int indexOf(E item) {
        return item.hashCode() % hashTable.length;
    }
}