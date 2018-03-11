import java.util.Iterator;
import java.util.LinkedList;

//https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html#listIterator(int)
public class HashTable<K, V> implements HashTableADT<K, V> {

	/*
	 * Instance variables and constructors
	 */
	protected int table_size; // changes when load factor is hit, not static
	protected double loadFactor;
	protected LinkedList<V>[] expanded;
	protected LinkedList<V>[] table; // array of type linked list
	// how to handle collisions? -> linked lists

	public HashTable() {
		// constructor

		this.table_size = 100;
		this.loadFactor = 0.75;
		table = (LinkedList<V>[]) new LinkedList[table_size]; // our hash table

		this.table_size = 100;
		table = (LinkedList<V>[]) new LinkedList[table_size]; // our hash table

	}

	public HashTable(int initialCapacity, double loadFactor) {
		this.table_size = initialCapacity;
		this.loadFactor = loadFactor;
		table = (LinkedList<V>[]) new LinkedList[table_size]; // our hash table
	}

	@Override
	/**
	 *
	 * @param key
	 *            : The key that goes into the hashtable
	 * @param value:
	 *            The Value associated with the key
	 * @return value of the key added to the hashtable, throws NullPointerException
	 *         if key is null
	 */
	public V put(K key, V value) {
		// TODO: Implement put method - using efficient algorithm
		// update array size if needed
		// check load factor ^^
		// rehash if needed

		// find index
		// see if value is a duplicate
		if (value == null) {
			return null;
		} else {
			try {
				int index = hash(key) % table.length;
				// put in array at the index, if index already has values, add to the end of the
				// linked list?
				table[index].add(value); // add to the array in the correct linked list
				return value;
			} catch (NullPointerException e) {
				return null;
			}
		}
	}

	private int hash(K key) {
		// get the hash key
		return key.hashCode();
	}

	private void rehash() {
		// new array size
		// i don't know how to do this
	}

	private void expand() {
		// expand the array size when the load factor is hit
		// double the array size
		expanded = (LinkedList<V>[]) new LinkedList[table_size * 2];
		// rehash
		// replace table array w/ expanded array (named table array)

	}

	@Override
	public void clear() {
		// TODO: Implement this method
		// go through array and set all values to null
		for (int i = 0; i < table.length; i++) {
			table[i] = null; // will this work for a linked list? or do we need to remove every value?
		}

	}
	// go through array and set all values to null/0

	@Override
	public V get(K key) {
		// TODO: Implement the get method
		int index = hash(key) % table.length;
		// key equal to search key: search hit
		if (table[index] != null) {
			Iterator Iterator = table[index].iterator();
			while (Iterator.hasNext()) {
				return (V) Iterator.next(); // wrong need "value" to know what node it would be?
				// or should we just return the first value in the linked list
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
			// increment empty if we hit a value
			if (table[i] == null) {
				i++;
			} else {
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
				// go though the linked nodes at this index
				count++;
				Iterator Iterator = table[i].iterator();
				while (Iterator.hasNext()) {
					count++;
				}
				// if value points to another value at the same index?
				// go through bucket at each index
			}
		}
		// if enter increment the counter
		return count;
	}
}
