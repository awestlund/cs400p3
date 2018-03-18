import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.lang.Object;
import java.util.Formatter;

//hash map example below!
//https://beginnersbook.com/2013/12/hashmap-in-java-with-example/

public class PerformanceAnalysisHash implements PerformanceAnalysis {

	// The input data from each file is stored in this/ per file
	// private ArrayList<String> inputData;
	private ArrayList<String> inputData;
	private ArrayList<String> reportData; // everything that needs to be printed out
	// .add after each comparison
	private LinkedList<String> pathnames;
	private ArrayList<String> dataFiles;
	private HashTable<Integer, String> hashTable;
	private HashMap<Integer, String> hashMap;

	public PerformanceAnalysisHash() {
		// Constructor
		hashTable = new HashTable<Integer, String>();
		hashMap = new HashMap<Integer, String>();
		// get data from file given
		// put in inputData arraylist
	}

	public PerformanceAnalysisHash(String details_filename) {
		// TODO: Save the details of the test data files
		hashTable = new HashTable<Integer, String>();
		hashMap = new HashMap<Integer, String>();
		try {
			loadData(details_filename);
		} catch (IOException e) {
			System.out.println("incorrect filename entered");
			System.exit(0);
		}

		// parse the inputData array (no spaces, two elements on each line)
		String[] temp = new String[2];
		temp = inputData.get(0).split(",");
		String path = temp[0].trim();
		for (int i = 1; i < inputData.size(); i++) {
			// get all of the file names
			// inputData.set(i, inputData.get(i).replace(" ", ""));
			temp = inputData.get(i).split(",");
			dataFiles.add(i, path + "/" + temp[0].trim()); // absolute path?
		}
		// for (int j = 0; j < inputData.size(); j++) {
		// try {
		// loadData(inputData.get(j));
		// } catch (IOException e) {
		// System.out.println("incorrect filename entered");
		// System.exit(0);
		// }
		// }
	}

	/**
	 * The important function that compares the implemented HashTable with TreeMap
	 * of Java and generates the table with all the comparison details This can
	 * internally call - compareInsertion, compareDeletion, CompareSearch for all
	 * the test data provided.
	 */
	@Override
	public void compareDataStructures() {
		// for filepath in dataFiles do every thing below
		//loadData for the file into inputData
		//compareInsertion();
		//compareDeletion();
		//compareSearch();
		//repeat
		// TODO: Complete this function which compares the ds and generates the details

		// Total runtime of a program

	}

	/**
	 * Function used to print out the final report
	 *
	 */
	@Override
	public void printReport() {
		System.out.println("The report name : Performance Analysis Report");
		System.out.println(
				"------------------------------------------------------------------------------------------------");
		System.out.println(
				"|            FileName|      Operation| Data Structure|   Time Taken (micro sec)|     Bytes Used|");
		System.out.println(
				"------------------------------------------------------------------------------------------------");

		// loop through report and print out every line
		// report.add(String.format("|%21s|%17s|%16s|%27s|%17s|", )); //21, 17, 16, 27,
		// 17
		System.out.println(
				"------------------------------------------------------------------------------------------------");
		// Total runtime of a program
		//print reportData in a loop
		
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
		long startTimeImp = System.nanoTime();
		for (int i= 0; i < inputData.size(); i++) {
			hashTable.put(Integer.parseInt(inputData.get(i)), inputData.get(i));
		}
		long endTimeImp = System.nanoTime();
		long elapsedTimeImp = (endTimeImp - startTimeImp)/1000;
		
		Runtime runtimeImp = Runtime.getRuntime();
        runtimeImp.gc();
        long memoryImp = runtimeImp.totalMemory() - runtimeImp.freeMemory();
		
		
		long startTimeJava = System.nanoTime();
		for (int i= 0; i < inputData.size(); i++) {
			hashMap.put(Integer.parseInt(inputData.get(i)), inputData.get(i));
		}
		long endTimeJava = System.nanoTime();
		long elapsedTimeJava = (endTimeJava - startTimeJava)/1000;
		Runtime runtimeJava = Runtime.getRuntime();
        runtimeJava.gc();
        long memoryJava = runtimeImp.totalMemory() - runtimeImp.freeMemory();
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
		long startTimeImp = System.nanoTime();
		for (int i= 0; i < inputData.size(); i++) {
			hashTable.remove(Integer.parseInt(inputData.get(i)));
		}
		long endTimeImp = System.nanoTime();
		long elapsedTimeImp = (endTimeImp - startTimeImp)/1000;
		
		Runtime runtimeImp = Runtime.getRuntime();
        runtimeImp.gc();
        long memoryImp = runtimeImp.totalMemory() - runtimeImp.freeMemory();
		
		
		long startTimeJava = System.nanoTime();
		for (int i= 0; i < inputData.size(); i++) {
			hashMap.remove(Integer.parseInt(inputData.get(i)));
		}
		long endTimeJava = System.nanoTime();
		long elapsedTimeJava = (endTimeJava - startTimeJava)/1000;
		Runtime runtimeJava = Runtime.getRuntime();
        runtimeJava.gc();
        long memoryJava = runtimeImp.totalMemory() - runtimeImp.freeMemory();
	}

	/**
	 * Standalone method for comparing search operation across HashTable and TreeMap
	 */
	@Override
	public void compareSearch() {
		long startTimeImp = System.nanoTime();
		for (int i= 0; i < inputData.size(); i++) {
			hashTable.get(Integer.parseInt(inputData.get(i)));
		}
		long endTimeImp = System.nanoTime();
		long elapsedTimeImp = (endTimeImp - startTimeImp)/1000;
		
		Runtime runtimeImp = Runtime.getRuntime();
        runtimeImp.gc();
        long memoryImp = runtimeImp.totalMemory() - runtimeImp.freeMemory();
		
		
		long startTimeJava = System.nanoTime();
		for (int i= 0; i < inputData.size(); i++) {
			hashMap.get(Integer.parseInt(inputData.get(i)));
		}
		long endTimeJava = System.nanoTime();
		long elapsedTimeJava = (endTimeJava - startTimeJava)/1000;
		Runtime runtimeJava = Runtime.getRuntime();
        runtimeJava.gc();
        long memoryJava = runtimeImp.totalMemory() - runtimeImp.freeMemory();
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
