public class HashMap<K, V> {
    public static final int DEFAULT_CAPACITY = 100;

    private Object[] values;
    private int size;
    private HashSet<K> keySet;

    public HashMap(int capacity) {
        values = new Object[capacity];
        size = 0;
        keySet = new HashSet<>(capacity);
    }

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Adds a new key-value pair
     * @param key
     * @param value
     * @return The previous value associated with `key`.
     */
    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        int hash = key.hashCode();
        V prev = (V) values[hash];
        values[hash] = value;
        keySet.add(key, true);
        size++;
        return prev;
    }

    @SuppressWarnings("unchecked")
    public V get(Object e) {
        int hash = ((K) e).hashCode();
        return (V) values[hash];
    }

    @SuppressWarnings("unchecked")
    public V remove(Object o) {
        int hash = ((K) o).hashCode();
        V res = (V) values[hash];
        values[hash] = null;
        keySet.remove((K) o);

        if (res != null) {
            size--;
        }
        return res;
    }

    public int size() {
        return size;
    }

    public HashSet<K> keySet() {
        return keySet;
    }
}