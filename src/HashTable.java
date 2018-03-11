
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

		this.table_size = 100;
		this.LOAD_FACTOR = 0.75;
		value_table = (LinkedList<V>[]) new LinkedList[table_size]; // our hash table
		key_table = (LinkedList<K>[]) new LinkedList[table_size]; // our hash table
		for( int j = 0; j < table_size; j++ ) {
			value_table[ j ] = new LinkedList<V>( );
			key_table[ j ] = new LinkedList<K>( );
		}
		

	}

	public HashTable(int initialCapacity, double loadFactor) {
		this.table_size = initialCapacity;
		this.LOAD_FACTOR = loadFactor;
		value_table = (LinkedList<V>[]) new LinkedList[table_size]; // our hash table
		key_table = (LinkedList<K>[]) new LinkedList[table_size]; // our hash table
		for( int j = 0; j < table_size; j++ ) {
			value_table[ j ] = new LinkedList<V>( );
			key_table[ j ] = new LinkedList<K>( );
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
				if (findKey(key)) {
					return null; //maybe?
				}
				
				
				if ((size()/table_size) > LOAD_FACTOR) {
					//needs rehashing
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
	
	
//	// Makes sure the key is not already in the hashtable.
//		HashtableEntry tab[] = table;
//		int hash = key.hashCode();
//		int index = (hash & 0x7FFFFFFF) % tab.length;
//		for (HashtableEntry e = tab[index] ; e != null ; e = e.next) {
//		    if ((e.hash == hash) && e.key.equals(key)) {
//			Object old = e.value;
//			e.value = value;
//			return old;
//		    }
//		}
//
//		// Creates the new entry.
//		HashtableEntry e = new HashtableEntry();
//		e.hash = hash;
//		e.key = key;
//		e.value = value;
//		e.next = tab[index];
//		tab[index] = e;
//		count++;
//		return null;
//	    }

	private int hash(K key) {
		// get the hash key
		return key.hashCode();
	}

	private void rehash() {
		// int oldCap = table.length;
		LinkedList<V> oldTable[] = value_table;
		table_size = table_size*2;
		expand();
		//int newCap = value_table.length * 2;
		//value_table = new LinkedList[bestPrime(2 * value_table.length)];
		for (int j = 0; j < table_size; j++)
			value_table[j] = new LinkedList<V>();

		// Copy table over
		table_size = 0;
		for (int i = 0; i < oldTable.length; i++) {
//			for ( : oldTable[i])  //for.......i dont know
				//insert value from old to new
		
		}
		//RENAME
	}
	// tried to do it, let me know what you think
	private boolean findKey(K key) {
		int index = hash(key) % table_size;
		if (key_table[index] != null) {
			Iterator it = value_table[index].iterator();
			while (it.hasNext()) {
				if (key ==(K) it.next() ) {
					return true;
				}
				//compare key values and return that same index in array and linked list in the value_table
				//(V) it.next(); // wrong need "value" to know what node it would be?
				// or should we just return the first value in the linked list
				// not sure here either.
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
		expanded = (LinkedList<V>[]) new LinkedList[bestPrime(table_size)];
		expanded_key = (LinkedList<K>[]) new LinkedList[bestPrime(table_size)];
		for( int j = 0; j < table_size; j++ ) {
			expanded[ j ] = new LinkedList<V>( );
			expanded_key[ j ] = new LinkedList<K>( );
		}
		// rehash
		// replace table array w/ expanded array (named table array)

	}

	@Override
	public void clear() {
		// TODO: Implement this method
		// go through array and set all values to null
		for (int i = 0; i < table_size; i++) {
			value_table[i].clear();
		}

	}
	// go through array and set all values to null/0

	@Override
	public V get(K key) {
		// TODO: Implement the get method
		int index = hash(key) % table_size;
		// key equal to search key: search hit
		if (key_table[index] != null) {
			Iterator it = value_table[index].iterator();
			while (it.hasNext()) {
				//compare key values and return that same index in array and linked list in the value_table
				//(V) it.next(); // wrong need "value" to know what node it would be?
				// or should we just return the first value in the linked list
				// not sure here either.
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

	@Override
	public V remove(K key) {
		// TODO: Implement the remove method
		int index = hash(key) % table_size;
		// see if it is in our table
		return null;
	}

//	 public synchronized Object remove(Object key) {
//			HashtableEntry tab[] = table;
//			int hash = key.hashCode();
//			int index = (hash & 0x7FFFFFFF) % tab.length;
//			for (HashtableEntry e = tab[index], prev = null ; e != null ; prev = e, e = e.next) {
//			    if ((e.hash == hash) && e.key.equals(key)) {
//				if (prev != null) {
//				    prev.next = e.next;
//				} else {
//				    tab[index] = e.next;
//				}
//				count--;
//				return e.value;
//			    }
//			}
//			return null;
//		    }
	//
	// public synchronized Object clone() {
	// try {
	// Hashtable t = (Hashtable)super.clone();
	// t.table = new HashtableEntry[table.length];
	// for (int i = table.length ; i-- > 0 ; ) {
	// t.table[i] = (table[i] != null)
	// ? (HashtableEntry)table[i].clone() : null;
	// }
	// return t;
	// } catch (CloneNotSupportedException e) {
	// // this shouldn't happen, since we are Cloneable
	// throw new InternalError();
	// }
	// }
	@Override
	public int size() { // me
		// TODO: Implement this method
		int count = 0;
		// go through the whole table to see if any value is entered
		for (int i = 0; i < table_size; i++) {
			if (value_table[i] != null) {
				// go though the linked nodes at this index
				count = count + value_table[i].size();
//				Iterator Iterator = value_table[i].iterator();
//				while (Iterator.hasNext()) {
//					count++;
				}
				// if value points to another value at the same index?
				// go through bucket at each index
			}
//		}
		// if enter increment the counter
		return count;
	}
}
