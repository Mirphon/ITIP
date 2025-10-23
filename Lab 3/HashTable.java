import java.util.LinkedList;

public class HashTable<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private LinkedList<Entry<K, V>>[] table;
    private int size;
    private int capacity;
    private final double loadFactor = 0.75;

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.capacity = 16;
        this.table = new LinkedList[capacity];
        this.size = 0;
        initializeTable();
    }
    
    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.table = new LinkedList[capacity];
        this.size = 0;
        initializeTable();
    }
    
    private void initializeTable() {
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }
    
    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }
    

    public void put(K key, V value) {
        if ((double) size / capacity >= loadFactor) {
            rehash();
        }
        
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        
        bucket.add(new Entry<>(key, value));
        size++;
    }
    
    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        
        return null;
    }
    
    public V remove(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                V value = entry.value;
                bucket.remove(entry);
                size--;
                return value;
            }
        }
        
        return null;
    }

    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        int newCapacity = capacity * 2;
        LinkedList<Entry<K, V>>[] oldTable = table;
        
        table = new LinkedList[newCapacity];
        capacity = newCapacity;
        size = 0;
        initializeTable();
        
        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            for (Entry<K, V> entry : bucket) {
                put(entry.key, entry.value);
            }
        }
    }
    
    public void display() {
        for (int i = 0; i < capacity; i++) {
            System.out.print("Bucket " + i + ": ");
            LinkedList<Entry<K, V>> bucket = table[i];
            for (Entry<K, V> entry : bucket) {
                System.out.print("[" + entry.key + "=" + entry.value + "] ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();
        
        hashTable.put("apple", 1);
        hashTable.put("banana", 2);
        hashTable.put("orange", 3);
        hashTable.put("apple", 4);
        
        System.out.println("apple: " + hashTable.get("apple")); 
        System.out.println("banana: " + hashTable.get("banana")); 
        System.out.println("grape: " + hashTable.get("grape")); 
        
        System.out.println("Removed: " + hashTable.remove("banana"));
        System.out.println("After removal - banana: " + hashTable.get("banana"));
        
        System.out.println("Size: " + hashTable.size()); 
        
        System.out.println("\nTable contents:");
        hashTable.display();
    }
}