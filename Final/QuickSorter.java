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
 * This class implements the version of the quicksort algorithm presented in the
 * lecture.
 *
 */

public class QuickSorter extends AbstractSorter {

	private Point[] points;
	private int length;

	/**
	 * The two constructors below invoke their corresponding superclass
	 * constructors. They also set the instance variables algorithm and
	 * outputFileName in the superclass.
	 */

	/**
	 * Constructor accepts an input array of points.
	 * 
	 * @param pts
	 *            input array of integers
	 */
	public QuickSorter(Point[] pts) {
		super(pts);
		algorithm = "quick sort";
		outputFileName = "quick.txt";
	}

	/**
	 * Constructor reads points from a file.
	 * 
	 * @param inputFileName
	 *            name of the input file
	 */
	public QuickSorter(String inputFileName) throws InputMismatchException,
			FileNotFoundException {
		super(inputFileName);
		algorithm = "quick sort";
		outputFileName = "quick.txt";
	}

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.
	 * 
	 * @param order
	 *            1 by x-coordinate 2 by polar angle
	 *
	 */
	@Override
	public void sort(int order) {
		long sortingTimeStart = System.nanoTime();
		if (super.points == null || super.points.length == 0) {
			return;
		}
		this.points = super.points;
		length = super.points.length;
		quickSortRec(0, length - 1);
		long sortingTimeEnd = System.nanoTime();
		super.sortingTime = (sortingTimeEnd - sortingTimeStart);
	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 *            starting index of the subarray
	 * @param last
	 *            ending index of the subarray
	 */
	private void quickSortRec(int first, int last) {
		int i = first;
		int j = last;
		partition(0, 0);
		Point pivot = points[first + (last - first) / 2];
		while (i <= j) {
			while (points[i].compareTo(pivot) < 1) { // should be correct
				i++;
			}
			while (points[j].compareTo(pivot) == 1) {
				j--;
			}
			if (i <= j) {
				exchangeNumbers(i, j);
				i++;
				j--;
			}
		}
		if (first < j)
			quickSortRec(first, j);
		if (i < last)
			quickSortRec(i, last);
	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last) {
		return 0;
	}

	private void exchangeNumbers(int i, int j) {
		Point temp = points[i];
		points[i] = points[j];
		points[j] = temp;
	}
}
