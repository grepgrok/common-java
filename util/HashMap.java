public class HashMap<K, V> {
    public static final int DEFAULT_CAPACITY = 100;

    private Object[] values;
    private HashSet<K> keySet;

    public HashMap(int capacity) {
        values = new Object[capacity];
        keySet = new HashSet<>(capacity);
    }

    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public int size() {
        return keySet.size();
    }

    public boolean isEmpty() {
        return keySet.isEmpty();
    }

    public HashSet<K> keySet() {
        return keySet;
    }

    @SuppressWarnings("unchecked")
    public V[] values() {
        return (V[]) values;
    }

    public boolean containsKey(Object key) {
        return keySet.contains(key);
    }

    public boolean containsValue(Object value) {
        for (Object o : values) {
            if (o.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public V get(Object key) {
        int hash = key.hashCode();
        return (V) values[hash];
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
        keySet.add(key);
        return prev;
    }

    public void putAll(HashMap<? extends K, ? extends V> map) {
        for (K key : map.keySet()) {
            put(key, map.get(key));
        }
    }

    @SuppressWarnings("unchecked")
    public V remove(Object o) {
        int hash = ((K) o).hashCode();
        V res = (V) values[hash];
        values[hash] = null;
        keySet.remove(o);
        return res;
    }

    public void clear() {
        values = new Object[values.length];
        keySet.clear();
    }

    public HashMap<K, V> clone() {
        HashMap<K, V> res = new HashMap<>();
        for (K key : keySet) {
            res.put(key, get(key));
        }
        return res;
    }
}