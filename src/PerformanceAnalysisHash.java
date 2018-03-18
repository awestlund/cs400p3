import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Object;
import java.util.Formatter;

//hash map example below!
//https://beginnersbook.com/2013/12/hashmap-in-java-with-example/

public class PerformanceAnalysisHash implements PerformanceAnalysis {

	// The input data from each file is stored in this/ per file
	private ArrayList<String> inputData;

	public PerformanceAnalysisHash() {
		// Constructor
		HashTable<Integer, String> hashTable = new HashTable<Integer, String>();
		HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
		// get data from file given
		// put in inputData arraylist
	}

	public PerformanceAnalysisHash(String details_filename) {
		// TODO: Save the details of the test data files
		HashTable<Integer, String> hashTable = new HashTable<Integer, String>();
		HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
		try {
			loadData(details_filename);
		} catch (IOException e) {
			System.out.println("incorrect filename entered");
			System.exit(0);
		}
		//parse the inputData array (no spaces, two elements on each line)
		
	}

	/**
	 * The important function that compares the implemented HashTable with TreeMap
	 * of Java and generates the table with all the comparison details This can
	 * internally call - compareInsertion, compareDeletion, CompareSearch for all
	 * the test data provided.
	 */
	@Override
	public void compareDataStructures() {
		// TODO: Complete this function which compares the ds and generates the details
		compareInsertion();
		compareDeletion();
		compareSearch();
		// Memory Consumption of the Java program
		System.out.println("The report name : Performance Analysis Report");
		System.out.println(
				"------------------------------------------------------------------------------------------------");
		System.out.println(
				"|            FileName|      Operation| Data Structure|   Time Taken (micro sec)|     Bytes Used|");
		System.out.println(
				"------------------------------------------------------------------------------------------------");
		//data entered below with correct spaceing
		//1. "|" filename 2. "|" get/PUT/remove 3. "|" HASHTABLE/TREEMAP,  "|" TIME(LONG),  "|" BYTES USED (INT) "|"
		
		//String padded = String.format("%21", ); //21, 17, 16, 27, 17
		System.out.println(
				"------------------------------------------------------------------------------------------------");
		// Total runtime of a program

	}

	/**
	 * Function used to print out the final report
	 *
	 */
	@Override
	public void printReport() {
		// TODO: Complete this method
		// /* Display content using Iterator*/
		// Set set = hmap.entrySet();
		// Iterator iterator = set.iterator();
		// while(iterator.hasNext()) {
		// Map.Entry mentry = (Map.Entry)iterator.next();
		// System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
		// System.out.println(mentry.getValue());
		// }
	}

	/**
	 * Standalone method for comparing insertion operation across HashTable and
	 * TreeMap
	 */
	@Override
	public void compareInsertion() {
		// TODO: Complete this method
		/* Adding elements to HashMap */
		// hmap.put(12, "Chaitanya");
		// value put(Key k, Value v)
	}

	/**
	 * Standalone method for comparing deletion operation across HashTable and
	 * TreeMap
	 */
	@Override
	public void compareDeletion() {
		// TODO: Complete this method
		/* Remove values based on key */
		// hmap.remove(3);
		// Value remove(Object key)
	}

	/**
	 * Standalone method for comparing search operation across HashTable and TreeMap
	 */
	@Override
	public void compareSearch() {
		// TODO: Complete this method
		/* Get values based on key */
		// String var= hmap.get(2);
		// Value get(Object key)
	}

	/*
	 * An implementation of loading files into local data structure is provided to
	 * you Please feel free to make any changes if required as per your
	 * implementation. However, this function can be used as is.
	 * 
	 * @param filename: Loads the data from the test file to local data structure
	 * This is already implemented for you.
	 * 
	 * @throws IOException
	 */
	@Override
	public void loadData(String filename) throws IOException {

		// Opens the given test file and stores the objects each line as a string
		File file = new File(filename);
		BufferedReader br = new BufferedReader(new FileReader(file));
		inputData = new ArrayList<>();
		String line = br.readLine();
		while (line != null) {
			inputData.add(line);
			line = br.readLine();
		}
		br.close();
	}
}
