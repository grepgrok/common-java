/**
 * MyHashTable<K,V> Class
 * Instance Variables
 * - table : DLList<V>[ ]
 * - keySet:MyHashSet<K>
 * Constructor
 * @SuppressWarnings("unchecked") - Add this code above the constructor
 * + MyHashTable() - Sets up the table array.
 * Methods
 * + put(K,V) : void - Maps the HashTable given the key and value. If there is a
 * duplicate key, add the value to the linked list within the table. Add to the
 * keys HashSet, but make sure to check for duplicates.
 * + get(K) : DLList - Return the linked list containing the values.
 * + keySet() : MyHashSet<K> - Return a set of keys.
 * + toString() : String - Return the string of the HashTable. Only display
 * buckets that are not empty. Use the following format. bucket # - Student name
 * and id - <each item in the list>. (Hint: You should go through your DLList of
 * keys object instead of the table.)
 * + remove(K,V) : void - Remove the given value within the given key. If a
 * bucket no longer has any values, then you should remove the bucket too.
 * + remove(K) : void - Remove all values of the given key.
 * Add more methods and instance variables as needed!
 * 
 */

public class HashTable<K,V> {
    public static final int DEFAULT_CAPACITY = 100;
    private DLList<V>[] table;
    private HashSet<K> keySet;
    
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        table = new DLList[capacity];
        keySet = new HashSet<>();
    }

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    public boolean put(K key, V value) {
        int index = indexOf(key);

        if (table[index] == null) {
            table[index] = new DLList<>();
        }
        table[index].add(value);

        return keySet.add(key);
    }

    public void remove(K key, V value) {
        if (!keySet.contains(key)) { return; }
        int index = indexOf(key);
        System.out.println(value);
        System.out.println(table[index].remove(value));

        if (table[index].size() == 0) {
            table[index] = null;
            keySet.remove(key);
        }
    }

    public void remove(K key) {
        if (!keySet.contains(key)) { return; }
        int index = indexOf(key);
        table[index] = null;
        keySet.remove(key);
    }
    
    public HashSet<K> keySet() {
        return keySet;
    }

    public String toString() {
        String res = "";
        for (K key : keySet) {
            int bucketNum = indexOf(key);
            res += bucketNum + " - " + key.toString() + " - ";
            for (V value : table[bucketNum]) {
                res += value.toString() + ", ";
            }
            res = res.substring(0, res.length() - 2) + "\n";
        }
        return res;
    }

    private int indexOf(K key) {
        return key.hashCode() % table.length;
    }
}