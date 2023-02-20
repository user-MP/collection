package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

/**
 * {@link HashTable} is a simple Hashtable-based implementation of {@link Map} interface with some additional methods.
 * It is based on the array of {@link Node} objects. Both {@link HashTable} and {@link Node} have two type parameters:
 * K and V, which represent key and value.
 * <p>
 * Elements are stored int the table by their key. A table is basically an array, and fast access is possible due to
 * array capabilities. (You can access an array element by its index in O(1) time). In order to find an index for any
 * given key, it uses calculateIndex method which is based on the element's hash code.
 * <p>
 * If two elements (keys) have the same array index, they form a linked list. That's why class {@link Node} requires
 * a reference to the next field.
 * <p>
 * Since you don't always know the number of elements in advance, the table can be resized. You can do that manually by
 * calling method resizeTable, or it will be done automatically once the table reach resize threshold.
 * <p>
 * The initial array size (initial capacity) is 8.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <K> key type
 * @param <V> value type
 * @author Taras Boychuk
 */
public class HashTable<K, V> implements Map<K, V> {
    static class Node<E,V>{


        E key;
        V value;

        Node<E,V> next;


        public Node(E key, V value){
            this.key=key;
            this.value=value;
           // this.next=null;
        }

        public E getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

//        public Node<E, V> getNext() {
//            return next;
//        }
//
//        public void setNext(Node<E, V> next) {
//            this.next = next;
//        }
    }

    Node<K, V>[] table;
     private static final int INITIAL_CAPACITY=8;

    private int size=0;


    public HashTable() {

        this.table = new Node[HashTable.INITIAL_CAPACITY];
    }
    public HashTable(int initialCapacity) {
        if(initialCapacity<=0){
            throw new IllegalArgumentException();
        }
        this.table = new Node[initialCapacity];
    }




    /**
     * This method is a critical part of the hast table. The main idea is that having a key, you can calculate its index
     * in the array using the hash code. Since the computation is done in constant time (O(1)), it's faster than
     * any other kind search.
     * <p>
     * It's a function that accepts a key and calculates its index using a hash code. Please note that index cannot be
     * equal or greater than array size (table capacity).
     * <p>
     * This method is used for all other operations (put, get, remove).
     *
     * @param key
     * @param tableCapacity underlying array size
     * @return array index of the given key
     */
    public static int calculateIndex(Object key, int tableCapacity) {

        int hash = key.hashCode();
        int calculatedIndex = hash % tableCapacity;
        if (calculatedIndex < 0) {
            calculatedIndex += tableCapacity;
        }
        return calculatedIndex;

        // throw new ExerciseNotCompletedException(); // todo:
    }
    private int getIndex(K key) {
        int hashValue = key.hashCode();
        int index = hashValue % table.length;
        if(index>=0){
            return index;
        }
        return  index + table.length ;
    }

    /**
     * Creates a mapping between provided key and value, and returns the old value. If there was no such key, it returns
     * null. {@link HashTable} does not support duplicate keys, so if you put the same key it just overrides the value.
     * <p>
     * It uses calculateIndex method to find the corresponding array index. Please note, that even different keys can
     * produce the same array index.
     *
     * @param key
     * @param value
     * @return old value or null
     */
    @Override
    public V put(K key, V value) {

        int index = getIndex(key);
        Node<K, V> node = table[index];

        while (node != null) {
            if (node.key.equals(key)) {
                V previousValue = node.value;
                node.value = value;
                return previousValue;
            }
            node = node.next;
        }

        Node<K, V> newItem = new Node<>(key, value);
        newItem.next = table[index];
        table[index] = newItem;
        size++;



        return null;

        //throw new ExerciseNotCompletedException(); // todo:
    }

    /**
     * Retrieves a value by the given key. It uses calculateIndex method to find the corresponding array index.
     * Then it iterates though all elements that are stored by that index, and uses equals to compare its keys.
     *
     * @param key
     * @return value stored in the table by the given key or null if there is no such key
     */
    @Override
    public V get(K key) {


        int index = calculateIndex(key, table.length);
        for (Node<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;

        // throw new ExerciseNotCompletedException(); // todo:
    }

    /**
     * Checks if the table contains a given key.
     *
     * @param key
     * @return true is there is such key in the table or false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        int index = calculateIndex(key, table.length) ;
        for (Node<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;

        //  throw new ExerciseNotCompletedException(); // todo:
    }

    /**
     * Checks if the table contains a given value.
     *
     * @param value
     * @return true is there is such value in the table or false otherwise
     */
    @Override
    public boolean containsValue(V value) {

        for (int i = 0; i < table.length; i++) {
            for (Node<K, V> entry = table[i]; entry != null; entry = entry.next) {
                if (entry.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
        //throw new ExerciseNotCompletedException(); // todo:
    }

    /**
     * Return a number of elements in the table.
     *
     * @return size
     */
    @Override
    public int size() {
       return size;
        // throw new ExerciseNotCompletedException(); // todo:
    }

    /**
     * Checks is the table is empty.
     *
     * @return true is table size is zero or false otherwise
     */
    @Override
    public boolean isEmpty() {

        if(size==0){
            return true;
        }
        return false;

        //throw new ExerciseNotCompletedException(); // todo:
    }

    /**
     * Removes an element by its key and returns a removed value. If there is no such key in the table, it returns null.
     *
     * @param key
     * @return removed value or null
     */
    @Override
    public V remove(K key) {

        int index =calculateIndex(key, table.length);
        Node<K, V> prev = null;
        for (Node<K, V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return entry.value;
            }
            prev = entry;
        }
        return null;

        //throw new ExerciseNotCompletedException(); // todo:
    }

    /**
     * It's a special toString method dedicated to help you visualize a hash table. It creates a string that represents
     * an underlying array as a table. It has multiples rows. Every row starts with an array index followed by ": ".
     * Then it adds every key and value (key=value) that have a corresponding index. Every "next" reference is
     * represented as an arrow like this " -> ".
     * <p>
     * E.g. imagine a table, where the key is a string username, and the value is the number of points of that user.
     * Is this case method toString can return something like this:
     * <pre>
     * 0: johnny=439
     * 1:
     * 2: madmax=833 -> leon=886
     * 3:
     * 4: altea=553
     * 5:
     * 6:
     * 7:
     * </pre>
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();


        for (int i = 0; i < table.length; i++) {
            Node<K, V> entry = table[i];

            if (entry == null) {
                strBuilder.append(i).append(": \n");
                continue;
            }

            strBuilder.append(i).append(": ");

            while (entry != null) {
                strBuilder.append(entry.key).append("=").append(entry.value+"\n");

                if (entry.next != null) {
                    strBuilder.append("->");
                }

                entry = entry.next;
            }

        }




        return strBuilder.toString();

        //throw new ExerciseNotCompletedException(); // todo:
    }

    /**
     * Creates a new underlying table with a given size and adds all elements to the new table.
     * <p>
     * In order to allow a fast access, this hash table needs to have a sufficient capacity.
     * (You can imagine a hash table, with a default capacity of 8 that stores hundreds of thousands of elements.
     * In that case it's just 8 huge linked lists. That's why we need this method.)
     * <p>
     * PLEASE NOTE that such method <strong>should not be a part of the public API</strong>, but it was made public
     * for learning purposes. You can create a table, print it using toString, then resizeTable and print it again.
     * It will help you to understand how it works.
     *
     * @param newCapacity a size of the new underlying array
     */
    public void resizeTable(int newCapacity) {

        Node<K, V>[] obsoleteTable = table;
        int obsoleteCapacity = obsoleteTable.length;

        Node<K, V>[] newTable = (Node<K, V>[]) new Node[newCapacity];
        size = 0;

        for (int i = 0; i < obsoleteCapacity; i++) {
            Node<K, V> entry = obsoleteTable[i];
            while (entry != null) {
                Node<K, V> next = entry.next;

                int index =  Math.abs(entry.key.hashCode()) % newCapacity;

                entry.next = newTable[index];
                newTable[index] = entry;
                size++;
                entry = next;
            }
        }

        table = newTable;

   }



 }


