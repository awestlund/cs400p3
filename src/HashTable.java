
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           HashTable
// Files:           AnalysisTest.java, HashTable.java, HashTableADT.java, 
//			PerformanceAnalysis.java, PerformanceAnalysisHash.java
// Course:          CS400
//
// Author:          Susie Chongthaweephol
// Email:           chongthaweep@wisc.edu
// Lecturer's Name: Debra Deppeler
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Amber Westlund
// Partner Email:   awestlund@wisc.edu
// Lecturer's Name: Debra Deppeler
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         none
// Online Sources:  none
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class implements a HashTable
 *
 * Bugs: none that we are aware of.
 *
 * @author Amber
 * @author Susie
 */
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

	public HashTable() {
		this.table_size = bestPrime(4);
		this.LOAD_FACTOR = 0.75;
		value_table = (LinkedList<V>[]) new LinkedList[table_size]; // our hash table
		expanded = (LinkedList<V>[]) new LinkedList[table_size];
		key_table = (LinkedList<K>[]) new LinkedList[table_size]; // our hash table
		expanded_key = (LinkedList<K>[]) new LinkedList[table_size];
		for (int j = 0; j < table_size; j++) {
			value_table[j] = new LinkedList<V>();
			key_table[j] = new LinkedList<K>();
			expanded[j] = new LinkedList<V>();
			expanded_key[j] = new LinkedList<K>();
		}

	}

	public HashTable(int initialCapacity, double loadFactor) {
		this.table_size = bestPrime(initialCapacity);
		this.LOAD_FACTOR = loadFactor;
		value_table = (LinkedList<V>[]) new LinkedList[table_size]; // our hash table
		expanded = (LinkedList<V>[]) new LinkedList[table_size];
		key_table = (LinkedList<K>[]) new LinkedList[table_size]; // our hash table
		expanded_key = (LinkedList<K>[]) new LinkedList[table_size];
		for (int j = 0; j < table_size; j++) {
			value_table[j] = new LinkedList<V>();
			key_table[j] = new LinkedList<K>();
			expanded[j] = new LinkedList<V>();
			expanded_key[j] = new LinkedList<K>();
		}
	}

	@Override
	/**
	 * Inserts a key and a value into the HashTable
	 *
	 * @param key
	 *            : The key that goes into the hashtable
	 * @param value:
	 *            The Value associated with the key
	 * @return value of the key added to the hashtable, throws NullPointerException
	 *         if key is null
	 */
	public V put(K key, V value) {
		// find index
		// see if value is a duplicate
		if (value == null || key == null) {
			throw new NullPointerException();
		} else {
			try {
				if (findKey(key) == true) {
					return null;
				}

				if ((size() / table_size) > LOAD_FACTOR) {
					// needs rehashing
					rehash();
					return put(key, value);

				}
				int index = hash(key) % value_table.length;
				// put in array at the index, if index already has values, add to the end of the
				// linked list
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
	/**
	 * Clear the hashtable of all its contents
	 */
	public void clear() {
		// go through array and set all values to null
		value_table = (LinkedList<V>[]) new LinkedList[table_size]; // our hash table
		expanded = (LinkedList<V>[]) new LinkedList[table_size];
		key_table = (LinkedList<K>[]) new LinkedList[table_size]; // our hash table
		expanded_key = (LinkedList<K>[]) new LinkedList[table_size];
	}

	@Override
	/**
	 * Searches for a key in the HashTable
	 * 
	 * @param key:
	 *            The key for which the value is returned
	 * @return The value associated with the key, else throws NoSuch Element
	 *         Exception
	 */
	public V get(K key) {
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
			}
			int count2 = 0;
			Iterator<V> it2 = value_table[index].iterator();
			while (it2.hasNext()) {
				V ret_this = it2.next();
				if (count2 == count) {
					return ret_this;
				}
				count2++;
			}
		}
		// empty position (null key at indexed position): search miss
		// key not equal to search key: try next entry
		return null;
	}

	@Override
	/**
	 * Checks if the hashtable is empty
	 * 
	 * @return true : if Empty, else False
	 */
	public boolean isEmpty() {
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
	 * Removes a key from the HashTable
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
		int index = hash(key) % table_size;
		if (key_table[index] != null) {
			int count = 0;
			Iterator<K> it = key_table[index].iterator();
			while (it.hasNext()) {
				// compare key values and return that same index in array
				// and linked list in the value_table
				if (it.next() != key) {
					count++;
				}
			}

			int count2 = 0;
			Iterator<V> it2 = value_table[index].iterator();
			while (it2.hasNext()) {
				V ret_this = it2.next();
				if (count2 == count) {
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
	/**
	 *
	 * @return: The total number of entries in the hashtable
	 */
	public int size() {
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

	/**
	 * Private helper method for hash function for keys
	 * 
	 * @param key:
	 *            the key of interest
	 * @return: the hashcode of the key
	 */
	private int hash(K key) {
		return key.hashCode();
	}

	/**
	 * Private helper method that rehashes the table into a larger one
	 */
	private void rehash() {
		LinkedList<V> oldTable[] = value_table;
		LinkedList<K> oldTableKey[] = key_table;
		table_size = bestPrime(table_size * 2);
		expand();
		value_table = expanded;
		key_table = expanded_key;

		for (int i = 0; i < oldTable.length; i++) {
			for (K key : oldTableKey[i]) {
				put(key, get(key));
			}
		}
	}

	/**
	 * Private helper method to find the hashkey
	 * 
	 * @param key:
	 *            the key of interest
	 * @return: returns true if key is present
	 */
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

	/**
	 * Private helper method to find the next prime number, used in the rehash
	 * method.
	 * 
	 * @param key:
	 *            the number n that the array is doubled to
	 * @return: the next prime number over the number n
	 */
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

	/**
	 * Private helper method that expands the array size when the load factor is hit
	 */
	private void expand() {
		// double the array size
		// += old expanded array = new bigger array
		expanded = (LinkedList<V>[]) new LinkedList[bestPrime(table_size)];
		expanded_key = (LinkedList<K>[]) new LinkedList[bestPrime(table_size)];
		for (int j = 0; j < table_size; j++) {
			expanded[j] = new LinkedList<V>();
			expanded_key[j] = new LinkedList<K>();
		}

	}

}
