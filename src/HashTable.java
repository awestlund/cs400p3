import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<K, V> implements HashTableADT<K, V> {
	// protected static class Bucket<K, V> implements HashTableADT<K, V> {
	// V value;
	// Bucket<K, V> prev;
	// Bucket<K, V> next;
	// public Bucket (V item) {
	// this.value = item;
	// this.next = null;
	// this.prev = null;
	// }
	// public Bucket (V item, int index) {
	// this.value = item;
	// this.next = null;
	// //the last one entered in this bucket
	// this.prev = table[index];
	// }
	// }

	/*
	 * Instance variables and constructors
	 */
	protected int table_size;
	protected double loadFactor;
	protected LinkedList<V>[] table;
	// how to handle collisions? -> linked lists
	public HashTable() {
		//constructor
		this.table_size = 100;
		table  = (LinkedList<V>[]) new LinkedList[table_size]; // our hash table
	}
	public HashTable(int initialCapacity, double loadFactor) {
		this.table_size = initialCapacity;
		this.loadFactor = loadFactor;
		table  = (LinkedList<V>[]) new LinkedList[table_size]; // our hash table
	}
	@Override
	public V put(K key, V value) {
		// TODO: Implement put method - using efficient algorithm
		// update array size if needed
		// rehash if needed

		// find index
		// see if value is a duplicate
		int index = hash(key) % table.length;
		return null;
	}

	private int hash(K key) {
		// get the hash key
		return key.hashCode();
	}

	private void rehash() {
		// new array size
	}

	private void expand() {
		// double the array size
	}

	@Override
	public void clear() {
		// TODO: Implement this method
		// go through array and set all values to null/0
	}

	@Override
	public V get(K key) {
		// TODO: Implement the get method
		int index = hash(key) % table.length;
		// key equal to search key: search hit
		if (table[index] != null) {
			Iterator Iterator = table[index].iterator();
			while(Iterator.hasNext()) {
				if (Iterator.next() == key); //wrong need value to know what node it would be?
			}
		}
		// empty position (null key at indexed position): search miss
		// key not equal to search key: try next entry
		return null;
	}

	@Override
	public boolean isEmpty() { // me
		// TODO: Implement the method
		// go through the whole table to see if any value is entered
		int i = 0;
		while (i < table.length) {
			//increment empty if we hit a value
			if (table[i] == null) {
				i++;
			}
			else {
				return false;
			}
		}
		return true;
	}

	@Override
	public V remove(K key) {
		// TODO: Implement the remove method
		int index = hash(key) % table.length;
		// see if it is in our table
		return null;
	}

	@Override
	public int size() { // me
		// TODO: Implement this method
		int count = 0;
		// go through the whole table to see if any value is entered
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				//go though the linked nodes at this index
				count++;
				Iterator Iterator = table[i].iterator();
				while(Iterator.hasNext()) {
					count++;
				}
				// if value points to another value at the same index?
				// go through bucket at each index
			}
		}
		// if enter increment the counter
		return 0;
	}
}
