import java.util.Iterator;
// import java.util.Optional;

public class HashSet<E> implements Iterable<E> {
    public static final int DEFAULT_CAPACITY = 100;
    private Object[] table;
    private int size;

    public HashSet(int capacity) {
        table = new Object[capacity];
        size = 0;
    }

    public HashSet() {
        this(DEFAULT_CAPACITY);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public E get(E e) {
        return (E) table[indexOf(e)];
    }

    /**
     * Check if the value is in the set
     * 
     * @param value The value to check
     * @return Whether or not value is in the set. If there is a hashCode collision
     *         (something is already there but it isn't the same as value), then
     *         contains will return false
     */
    public boolean contains(Object data) {
        int index = indexOf(data);
        if (table[index] == null) {
            return false;
        }
        return table[index].equals(data);
    }

    public boolean add(E data) {
        if (contains(data)) {
            return false;
        }
        table[indexOf(data)] = data;
        size++;
        return true;
    }

    public boolean remove(Object o) {
        if (contains(o)) {
            table[indexOf(o)] = null;
            size--;
            return true;
        }
        return false;
    }

    public void clear() {
        table = new Object[table.length];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public DLList<E> toDLList() {
        DLList<E> list = new DLList<>();
        for (Object o : table) {
            if (o != null) {
                list.add((E) o);
            }
        }
        return list;
    }

    public String toString() {
        String res = "[";
        for (E item : this) {
            res += item + ", ";
        }
        return res.substring(0, res.length() - 2) + "]";
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int initial = -1;
            private int times = 0;

            public boolean hasNext() {
                return times != size;
            }

            @SuppressWarnings("unchecked")
            public E next() {
                for (int i = initial + 1; i < table.length; i++) {
                    if (table[i] != null) {
                        initial = i;
                        times++;
                        return (E) table[i];
                    }
                }
                return null;
            }
        };
    }

    private int indexOf(Object item) {
        return item.hashCode() % table.length;
    }
}