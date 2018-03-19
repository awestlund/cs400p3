
import java.util.Iterator;
import java.util.LinkedList;

//https://secweb.cs.odu.edu/~zeil/cs330/live/website/Slides/libraries/Hashtable.java.html
//http://homepage.divms.uiowa.edu/~kvaradar/sp2009/SeparateChainingHashTable.java

public class HashTable<K, V> implements HashTableADT<K, V> {

	/*
	 * Instance variables and constructors
	 */
	protected int table_size; // changes when load factor is hit, not static
	protected double LOAD_FACTOR;
	protected LinkedList<V>[] expanded;
	protected LinkedList<K>[] expanded_key;
	protected LinkedList<V>[] value_table; // array of type linked list
	protected LinkedList<K>[] key_table;
	// how to handle collisions? -> linked lists

	public HashTable() {
		// constructor

		this.table_size = 1000;
		this.LOAD_FACTOR = 0.75;
		value_table = (LinkedList<V>[]) new LinkedList[table_size]; // our hash table
		expanded = (LinkedList<V>[]) new LinkedList[table_size];
		key_table = (LinkedList<K>[]) new LinkedList[table_size]; // our hash table
		expanded_key =  (LinkedList<K>[]) new LinkedList[table_size];
		for (int j = 0; j < table_size; j++) {
			value_table[j] = new LinkedList<V>();
			key_table[j] = new LinkedList<K>();
			expanded[j] = new LinkedList<V>();
			expanded_key[j] = new LinkedList<K>();
		}

	}

	public HashTable(int initialCapacity, double loadFactor) {
		this.table_size = initialCapacity;
		this.LOAD_FACTOR = loadFactor;
		value_table = (LinkedList<V>[]) new LinkedList[table_size]; // our hash table
		expanded = (LinkedList<V>[]) new LinkedList[table_size];
		key_table = (LinkedList<K>[]) new LinkedList[table_size]; // our hash table
		expanded_key =  (LinkedList<K>[]) new LinkedList[table_size];
		for (int j = 0; j < table_size; j++) {
			value_table[j] = new LinkedList<V>();
			key_table[j] = new LinkedList<K>();
			expanded[j] = new LinkedList<V>();
			expanded_key[j] = new LinkedList<K>();
		}
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
		if (value == null || key == null) {
			throw new NullPointerException();
		} else {
			try {
				if (findKey(key) == true) {
					return null; // maybe?
				}

				if ((size() / table_size) > LOAD_FACTOR) {
					// needs rehashing
					rehash();
					return put(key, value);

				}
				int index = hash(key) % value_table.length;
				// put in array at the index, if index already has values, add to the end of the
				// linked list?
				value_table[index].add(value); // add to the array in the correct linked list
				expanded[index].add(value);
				key_table[index].add(key); // add to the array in the correct linked list
				expanded_key[index].add(key);
				return value;
			} catch (NullPointerException e) {
				return null;
			}
		}
	}

	@Override
	public void clear() {
		// TODO: Implement this method
		// go through array and set all values to null
		value_table = (LinkedList<V>[]) new LinkedList[table_size]; // our hash table
		expanded = (LinkedList<V>[]) new LinkedList[table_size];
		key_table = (LinkedList<K>[]) new LinkedList[table_size]; // our hash table
		expanded_key =  (LinkedList<K>[]) new LinkedList[table_size];
//		for (int i = 0; i < table_size; i++) {
//			value_table[i].clear();
//			//other arrays
//		}
		//
	}

	@Override
	public V get(K key) {
		// TODO: Implement the get method
		int index = hash(key) % table_size;
		// key equal to search key: search hit
		if (key_table[index] != null) {
			int count = 0;
			Iterator<K> it = key_table[index].iterator();
			while (it.hasNext()) {
				// compare key values and return that same index in array and linked list in the
				// value_table
				if (it.next() != key) {
					count++;
				}
				// (V) it.next(); // wrong need "value" to know what node it would be?
				// or should we just return the first value in the linked list
				// not sure here either.
			}
			int count2 = 0;
			Iterator<V> it2 = value_table[index].iterator();
			while (it2.hasNext()) {
				if (count2 == count) {
					return it2.next();
				}
				count2++;
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
		while (i < table_size) {
			// increment empty if we hit a value
			if (value_table[i].size() == 0) {
				i++;
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 *
	 * @param key:
	 *            Key of the entry to be removed
	 * @return value: Value of the key-value pair removed, null if key did not have
	 *         a mapping
	 * @throws NullPointerException
	 *             if key is null
	 */
	@Override
	public V remove(K key) {
		// TODO: Implement the remove method
		int index = hash(key) % table_size;
		if (key_table[index] != null) {
			int count = 0;
			Iterator<K> it = key_table[index].iterator();
			while (it.hasNext()) {
				// compare key values and return that same index in array and linked list in the
				// value_table
				if (it.next() != key) {
					count++;
				}
			}
	
			int count2 = 0;
			Iterator<V> it2 = value_table[index].iterator();
			while (it2.hasNext()) {
				if (count2 == count) {
					V ret_this = it2.next();
					// if the correct value is there, delete it
					if (value_table[index].contains(ret_this)) {
							key_table[index].remove(key);
							value_table[index].remove(ret_this);
							return ret_this;
					}
				}
				count2++;
			}
		}
		// see if it is in our table
		return null;
	}

	@Override
	public int size() { // me
		// TODO: Implement this method
		int count = 0;
		// go through the whole table to see if any value is entered
		for (int i = 0; i < table_size; i++) {
			if (value_table[i] != null) {
				// go though the linked nodes at this index
				count = count + value_table[i].size();
			}

		}

		return count;
	}

	private int hash(K key) {
		// get the hash key
		return key.hashCode();
	}

		private void rehash() {
		// int oldCap = table.length;
		LinkedList<V> oldTable[] = value_table;
		LinkedList<K> oldTableKey[] = key_table;
		table_size = table_size * 2;
//		V value = null;
//		K key;
		expand();
		
		value_table = expanded;
		key_table = expanded_key;
		
//        table_size = 0;
        for( int i = 0; i < oldTable.length; i++ ) {
//            for( V value : oldTable[i] ) {
//            	put(value);
//            }
            	for(K key : oldTableKey[i] ) {
//            		value = value_table[i];
            		put(key, get(key));
            	}
//            	for( V value : oldTable[i] ) {
//                	put(hash((K)getKey(value)), value);
//                }
        }
            }

	// tried to do it, let me know what you think
	private boolean findKey(K key) {
		int index = hash(key) % table_size;
		if (key_table[index] != null) {
			Iterator<K> it = key_table[index].iterator();
			while (it.hasNext()) {
				if (key == (K) it.next()) {
					return true;
				}

			}
		}
		return false;
	}

	// find next prime number, used for rehash
	private int bestPrime(int n) {
		int counter;
		n++;
		while (true) {
			counter = 0;
			for (int i = 2; i <= Math.sqrt(n); i++) {
				if (n % i == 0)
					counter++;
			}
			if (counter == 0) {
				return n;
			} else {
				n++;
				continue;
			}
		}
	}

	private void expand() {
		// expand the array size when the load factor is hit
		// double the array size
		// += old expanded array = new bigger array
		expanded = (LinkedList<V>[]) new LinkedList[bestPrime(table_size)];
		expanded_key = (LinkedList<K>[]) new LinkedList[bestPrime(table_size)];
		for (int j = 0; j < table_size; j++) {
			expanded[j] = new LinkedList<V>();
			expanded_key[j] = new LinkedList<K>();
		}
		// rehash
		// replace table array w/ expanded array (named table array)

	}

	private K getKey(int index, K key) {
		if (key_table[index] != null) {
			int count = 0;
			Iterator<K> it_key = key_table[index].iterator();
			while (it_key.hasNext()) {
				// compare key values and return that same index in array and linked list in the
				// value_table
				if (it_key.next() == key) {
					return it_key.next();
				}
				count++;
				// (V) it.next(); // wrong need "value" to know what node it would be?
				// or should we just return the first value in the linked list
				// not sure here either.
			}
		}
		return null;
	}
}
