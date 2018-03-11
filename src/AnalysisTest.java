import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AnalysisTest {
    public static void main(String[] args)  {

                // TODO Add code for checking command line arguments
                
                PerformanceAnalysisHash pah = new PerformanceAnalysisHash(args[0]);
                pah.compareDataStructures();
                pah.printReport();
     }
    
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
}
