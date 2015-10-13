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
 * This class implements selection sort.
 *
 */

public class SelectionSorter extends AbstractSorter {
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
	public SelectionSorter(Point[] pts) {
		super(pts);
		algorithm = "selection sort";
		outputFileName = "select.txt";
	}

	/**
	 * Constructor reads points from a file.
	 * 
	 * @param inputFileName
	 *            name of the input file
	 */
	public SelectionSorter(String inputFileName) throws InputMismatchException,
			FileNotFoundException {
		super(inputFileName);
		algorithm = "selection sort";
		outputFileName = "select.txt";
	}

	/**
	 * Apply insertion sort on the array points[] of the parent class
	 * AbstractSorter.
	 *
	 * @param order
	 *            1 by x-coordinate 2 by polar angle
	 *
	 */
	@Override
	public void sort(int order) {
		long sortingTimeStart = System.nanoTime();
		int i = 0;
		int j = 0;
		int index = 0;

		if (order == 1) {
			for (i = 0; i < points.length - 1; i++) {
				index = i;
				for (j = i + 1; j < points.length; j++) {
					if (points[j].compareTo(points[index]) == -1)
						index = j;
				}
				swap(index, i);
			}
		}

		if (order == 2) {
			// sort by polar angle
		}
		long sortingTimeEnd = System.nanoTime();
		super.sortingTime = (sortingTimeEnd - sortingTimeStart);
	}
}
