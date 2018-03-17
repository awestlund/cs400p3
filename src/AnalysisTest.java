import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AnalysisTest {
//    public static void main(String[] args)  {
//
//                // TODO Add code for checking command line arguments
//                
//                PerformanceAnalysisHash pah = new PerformanceAnalysisHash(args[0]);
//                pah.compareDataStructures();
//                pah.printReport();
//     }
    
	HashTable<Integer, String> hashTable = null;
	// can make more trees of different types
	String expected = null;
	String actual = null;
	Random r = new Random();
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// set up huge data structure
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Constructor that runs before every test
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		hashTable = new HashTable<Integer, String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	/**
	 * Tests if isEmpty is true in an empty tree, fails if it is false for the newly
	 * constructed search tree
	 */
	@Test
	public void test01_isEmpty_on_empty_hashTable() {
		expected = "true";
		actual = "" + hashTable.isEmpty();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
	}
	@Test
	public void test02_size_of_empty_hashTable() {
		expected = "0";
		actual = "" + hashTable.size();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
		
	}
	@Test
	public void test03_clear_empty_hashTable() {
		expected = "0";
		hashTable.clear();
		actual = "" + hashTable.size();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
		
	}
	@Test
	public void test04_get_empty_hashTable() {
		expected = "null";
		actual = "" + hashTable.get(4);
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
		
	}
	@Test
	public void test05_remove_empty_hashTable() {
		expected = "null";
		actual = "" + hashTable.remove(4);
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
		
	}
	@Test
	public void test06_insert_1() {
		expected = "cow";
		hashTable.put(34, "cow");
		actual = "" + hashTable.get(34);
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
		
	}
	@Test
	public void test07_remove_1() {
		expected = "cow";
		hashTable.put(34, "cow");
		actual = "" + hashTable.remove(34);
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
		
	}
	@Test
	public void test08_remove_1_size() {
		expected = "0";
		hashTable.put(34, "cow");
		hashTable.remove(34);
		actual = "" + hashTable.size();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
		
	}
	@Test
	public void test09_insert_1_size() {
		expected = "1";
		hashTable.put(34, "cow");
		actual = "" + hashTable.size();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
		
	}
	@Test
	public void test10_insert5_remove1_getsize() {
		expected = "4";
		hashTable.put(34, "cow");
		hashTable.put(33, "wow");
		hashTable.put(3, "zow");
		hashTable.put(40, "meow");
		hashTable.put(12, "pow");
		hashTable.remove(3);
		actual = "" + hashTable.size();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
		
	}
	@Test
	public void test11_insert5_remove1_get() {
		expected = "null";
		hashTable.put(34, "cow");
		hashTable.put(33, "wow");
		hashTable.put(3, "zow");
		hashTable.put(40, "meow");
		hashTable.put(12, "pow");
		hashTable.remove(3);
		hashTable.get(3);
		actual = "" + hashTable.get(3);
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
		
	}
	@Test
	public void test12_insert5_remove5_getsize() {
		expected = "0";
		hashTable.put(34, "cow");
		hashTable.put(33, "wow");
		hashTable.put(3, "zow");
		hashTable.put(40, "meow");
		hashTable.put(12, "pow");
		hashTable.remove(34);
		hashTable.remove(33);
		hashTable.remove(3);
		hashTable.remove(40);
		hashTable.remove(12);
		actual = "" + hashTable.size();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
		
	}
	@Test
	public void test13_insert5_remove5_ofthesame_get1_size() {
		expected = "0";
		hashTable.put(34, "cow");
		hashTable.put(34, "wow");
		hashTable.put(34, "zow");
		hashTable.put(34, "meow");
		hashTable.put(34, "pow");
		hashTable.remove(34);
		hashTable.remove(33);
		hashTable.remove(3);
		hashTable.remove(40);
		hashTable.remove(12);
		hashTable.get(3);
		actual = "" + hashTable.size();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
		
	}
	@Test
	public void test14_insert5_clear() {
		expected = "0";
		hashTable.put(34, "cow");
		hashTable.put(33, "wow");
		hashTable.put(3, "zow");
		hashTable.put(40, "meow");
		hashTable.put(12, "pow");
		hashTable.clear();
		actual = "" + hashTable.size();
		if (!expected.equals(actual))
			fail("expected: " + expected + " actual: " + actual);
		
	}
}
