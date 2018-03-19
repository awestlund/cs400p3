
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           HashTable
// Files:           AnalysisTest.java, HashTable.java, HashTableADT.java, 
//					PerformanceAnalysis.java, PerformanceAnalysisHash.java
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
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class uses the HashTable that we created, reads in data from files and
 * prints out the output in a txt file.
 *
 * Bugs: compareDataStructures() throws an IOException.
 *
 * @author Amber
 * @author Susie
 */
public class PerformanceAnalysisHash implements PerformanceAnalysis {
	// The input data from each file is stored in this/ per file
	private ArrayList<String> inputData;
	// everything that needs to be printed out
	private ArrayList<String> reportData = new ArrayList<String>();
	// .add after each comparison
	private LinkedList<String> pathnames = new LinkedList<String>();
	private ArrayList<String> dataFiles = new ArrayList<String>();
	private HashTable<String, String> hashTable;
	private HashMap<String, String> TreeMap;

	public PerformanceAnalysisHash() {
	}

	public PerformanceAnalysisHash(String details_filename) {
		try {
			loadData(details_filename);
			// parse the inputData array (no spaces, two elements on each line)
			String[] temp = new String[2];
			String path2 = inputData.get(0);
			temp = path2.split(",");
			String path = temp[1].trim().replace("../", "");
			inputData.remove(0);
			for (int i = 0; i < inputData.size(); i++) {
				// get all of the file names
				String file = inputData.get(i);
				temp = file.split(",");
				String filename = (path + "/" + temp[0].trim()).replaceAll("/", File.separator);
				dataFiles.add(i, filename); // absolute path
				pathnames.add(temp[0].trim()); // save the file names to a linked list
			}
		} catch (IOException e) {
			System.out.println("incorrect filename entered");
			System.exit(0);
		}

	}

	/**
	 * The important function that compares the implemented HashTable with TreeMap
	 * of Java and generates the table with all the comparison details This can
	 * internally call - compareInsertion, compareDeletion, CompareSearch for all
	 * the test data provided.
	 */
	@Override
	public void compareDataStructures() {
		int count = 0;
		Iterator<String> it = pathnames.iterator();
		for (int i = 0; i < dataFiles.size(); i++) {
			try {
				loadData(dataFiles.get(i));
				// trim all of the data
				for (int j = 0; j < inputData.size(); j++) {
					// replace all data with trimmed data
					inputData.add(j, inputData.get(j).trim());
				}
				// clears old hashtables in insertion by making new tables
				compareInsertion();
				compareSearch();
				compareDeletion();

				// make the current file name the first on in pathnames
				// so that we know where we
				// are at in the compare methods
				// so delete name when we are done using it in order
				while (it.hasNext()) {
					if (count == i) {
						pathnames.remove(i);
					}
					count++;
				}
			} catch (IOException e) {
				System.out.println("incorrect filename entered");
				System.exit(0);
			}
			// now inputData is updated to the new .txt
		}

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
		for (int i = 0; i < reportData.size(); i++) {
			System.out.println(reportData.get(i));
		}
		System.out.println(
				"------------------------------------------------------------------------------------------------");
	}

	/**
	 * Standalone method for comparing insertion operation across HashTable and
	 * TreeMap
	 */
	@Override
	public void compareInsertion() {
		// make new tree
		hashTable = new HashTable<String, String>();
		// put data in the tree
		long startTimeImp = System.nanoTime();
		// all data is in string form
		for (int i = 0; i < inputData.size(); i++) {
			hashTable.put((inputData.get(i)), inputData.get(i));
			// hashTable.put(Integer.parseInt(inputData.get(i)), inputData.get(i));
		}
		long endTimeImp = System.nanoTime();
		long elapsedTimeImp = (endTimeImp - startTimeImp) / 1000;

		Runtime runtimeImp = Runtime.getRuntime();
		runtimeImp.gc();
		long memoryImp = runtimeImp.totalMemory() - runtimeImp.freeMemory();
		String filename = pathnames.getFirst();

		// add values to the report to be printed
		reportData.add(String.format("|%21s|%17s|%16s|%27%.1ld|%17%.0ld|", filename, "PUT", "HASHTABLE", elapsedTimeImp,
				memoryImp));

		// TreeMap part
		TreeMap = new HashMap<String, String>();

		long startTimeJava = System.nanoTime();
		for (int i = 0; i < inputData.size(); i++) {
			TreeMap.put((inputData.get(i)), inputData.get(i));
		}
		long endTimeJava = System.nanoTime();
		long elapsedTimeJava = (endTimeJava - startTimeJava) / 1000;
		Runtime runtimeJava = Runtime.getRuntime();
		runtimeJava.gc();
		long memoryJava = runtimeImp.totalMemory() - runtimeImp.freeMemory();

		reportData.add(String.format("|%21s|%17s|%16s|%27%.1ld|%17%.0ld|", filename, "PUT", "TREEMAP", elapsedTimeJava,
				memoryJava));

	}

	/**
	 * Standalone method for comparing search operation across HashTable and TreeMap
	 */
	@Override
	public void compareSearch() {

		String filename = pathnames.getFirst(); // GETS FILE NAME
		// load data
		// trim data make into string
		// make new hash table and tree map
		// run tests
		// add results into result array to be printed
		long startTimeImp = System.nanoTime();
		for (int j = 0; j < inputData.size(); j++) {
			hashTable.get((inputData.get(j)));
		}
		long endTimeImp = System.nanoTime();
		long elapsedTimeImp = (endTimeImp - startTimeImp) / 1000;

		Runtime runtimeImp = Runtime.getRuntime();
		runtimeImp.gc();
		long memoryImp = runtimeImp.totalMemory() - runtimeImp.freeMemory();

		reportData.add(String.format("|%21s|%17s|%16s|%27%.1ld|%17%.0ld|", filename, "GET", "HASHTABLE", elapsedTimeImp,
				memoryImp));

		long startTimeJava = System.nanoTime();
		for (int k = 0; k < inputData.size(); k++) {
			TreeMap.get((inputData.get(k)));
		}
		long endTimeJava = System.nanoTime();
		long elapsedTimeJava = (endTimeJava - startTimeJava) / 1000;
		Runtime runtimeJava = Runtime.getRuntime();
		runtimeJava.gc();
		long memoryJava = runtimeImp.totalMemory() - runtimeImp.freeMemory();

		reportData.add(String.format("|%21s|%17s|%16s|%27%.1ld|%17%.0ld|", filename, "GET", "TREEMAP", elapsedTimeJava,
				memoryJava));
	}

	/**
	 * Standalone method for comparing deletion operation across HashTable and
	 * TreeMap
	 */
	@Override
	public void compareDeletion() {

		String filename = pathnames.getFirst(); // GETS FILE NAME

		long startTimeImp = System.nanoTime();
		for (int i = 0; i < inputData.size(); i++) {
			hashTable.remove((inputData.get(i)));
		}
		long endTimeImp = System.nanoTime();
		long elapsedTimeImp = (endTimeImp - startTimeImp) / 1000;

		Runtime runtimeImp = Runtime.getRuntime();
		runtimeImp.gc();
		long memoryImp = runtimeImp.totalMemory() - runtimeImp.freeMemory();

		reportData.add(String.format("|%21s|%17s|%16s|%27%.1ld|%17%.0ld|", filename, "REMOVE", "HASHTABLE",
				elapsedTimeImp, memoryImp));

		long startTimeJava = System.nanoTime();
		for (int i = 0; i < inputData.size(); i++) {
			TreeMap.remove((inputData.get(i)));
		}
		long endTimeJava = System.nanoTime();
		long elapsedTimeJava = (endTimeJava - startTimeJava) / 1000;
		Runtime runtimeJava = Runtime.getRuntime();
		runtimeJava.gc();
		long memoryJava = runtimeImp.totalMemory() - runtimeImp.freeMemory();

		reportData.add(String.format("|%21s|%17s|%16s|%27%.1ld|%17%.0ld|", filename, "REMOVE", "TREEMAP",
				elapsedTimeJava, memoryJava));
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
