package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

/**
 *  
 * @author Clinton Elliott
 *
 */

/**
 * 
 * This class implements insertion sort.
 *
 */

public class InsertionSorter extends AbstractSorter {
	// Other private instance variables if you need ...

	/**
	 * The two constructors below invoke their corresponding superclass
	 * constructors. They also set the instance variables algorithm and
	 * outputFileName in the superclass.
	 */

	/**
	 * Constructor takes an array of points.
	 * 
	 * @param pts
	 */
	public InsertionSorter(Point[] pts) {
		super(pts);
		algorithm = "insertion sort";
		outputFileName = "insert.txt";
	}

	/**
	 * Constructor reads points from a file.
	 * 
	 * @param inputFileName
	 *            name of the input file
	 */
	public InsertionSorter(String inputFileName) throws InputMismatchException,
			FileNotFoundException {
		super(inputFileName);
		algorithm = "insertion sort";
		outputFileName = "insert.txt";
	}

	/**
	 * Perform insertion sort on the array points[] of the parent class
	 * AbstractSorter.
	 * 
	 * @param order
	 *            1 by x-coordinate 2 by polar angle
	 */
	@Override
	public void sort(int order) {
		long sortingTimeStart = System.nanoTime();
		Point[] points = super.points;
		int n = points.length;
		for (int j = 1; j < n; j++) {
			Point test = points[j];
			int i = j - 1;
			while ((i > -1) && (points[i].compareTo(test) == 1)) {
				points[i + 1] = points[i];
				i--;
			}
			points[i + 1] = test;
		}
		long sortingTimeEnd = System.nanoTime();
		super.sortingTime = (sortingTimeEnd - sortingTimeStart);
	}
}