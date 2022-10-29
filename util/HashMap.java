/**
 * MyHashMap<K,V>
 * Instance Variables
 * - hashArray : Object[]
 * - size : int
 * - keySet:MyHashSet<K>
 * Constructor
 * +MyHashMap() - Sets up the instance variables.
 *
 * Methods
 * +put(K,V) : boolean - Takes in a key and value. Insert in the hashArray
 * accordingly. Add key to keyList. Increase size. Return true if successful and
 * false if the array slot already exists.
 * +get(Object) : V - Returns the value given a key object. Because you will be
 * casting an array to generic, you may want to add
 * the @SuppressWarnings("unchecked").
 * +remove(Object) : V - Takes in a key object. Remove the key-value pair from
 * the hashArray. Return the value that was removed. You may want to add
 * the @SuppressWarnings("unchecked").
 * +size() : int - Return the size.
 * +keySet() : MyHashSet<K> - Return a set of keys.
 * 
 */

public class HashMap<K, V> {
    public static final int DEFAULT_CAPACITY = 100;

    private Object[] hashArray;
    private int size;
    private HashSet<K> keySet;

    public HashMap() {
        hashArray = new Object[DEFAULT_CAPACITY];
        size = 0;
        keySet = new HashSet<>();
    }

    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        int hash = key.hashCode();
        V prev = (V) hashArray[hash];
        hashArray[hash] = value;
        keySet.add(key);
        size++;
        return prev;
    }

    @SuppressWarnings("unchecked")
    public V get(Object e) {
        int hash = ((K) e).hashCode();
        return (V) hashArray[hash];
    }

    @SuppressWarnings("unchecked")
    public V remove(Object o) {
        int hash = ((K) o).hashCode();
        V res = (V) hashArray[hash];
        hashArray[hash] = null;
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