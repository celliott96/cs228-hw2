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
 * This class implements the mergesort algorithm.
 *
 */

public class MergeSorter extends AbstractSorter {

	private Point[] points;
	private Point[] tempPoints;
	private int length;

	/**
	 * The two constructors below invoke their corresponding superclass
	 * constructors. They also set the instance variables algorithm and
	 * outputFileName in the superclass.
	 */

	/**
	 * Constructor accepts an input array of points. in the array.
	 * 
	 * @param pts
	 *            input array of integers
	 */
	public MergeSorter(Point[] pts) {
		super(pts);
		algorithm = "merge sort";
		outputFileName = "merge.txt";
	}

	/**
	 * Constructor reads points from a file.
	 * 
	 * @param inputFileName
	 *            name of the input file
	 */
	public MergeSorter(String inputFileName) throws InputMismatchException,
			FileNotFoundException {
		super(inputFileName);
		algorithm = "merge sort";
		outputFileName = "merge.txt";
	}

	/**
	 * Perform mergesort on the array points[] of the parent class
	 * AbstractSorter.
	 * 
	 * @param order
	 *            1 by x-coordinate 2 by polar angle
	 *
	 */
	@Override
	public void sort(int order) {
		long sortingTimeStart = System.nanoTime();
		if (order == 1) {
			this.points = super.points;
			this.length = points.length;
			this.tempPoints = new Point[length];
			mergeSortRec(0, length - 1);
		}
		if (order == 2) {
		}
		long sortingTimeEnd = System.nanoTime();
		super.sortingTime = (sortingTimeEnd - sortingTimeStart);
	}

	/**
	 * This is a recursive method that carries out mergesort on an array pts[]
	 * of points. One way is to make copies of the two halves of pts[],
	 * recursively call mergeSort on them, and merge the two sorted subarrays
	 * into pts[].
	 * 
	 * @param lowerIndex
	 *            lower half of array
	 * 
	 * @param higherIndex
	 *            upper half of array
	 */
	private void mergeSortRec(int lowerIndex, int higherIndex) {
		if (lowerIndex < higherIndex) {
			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
			mergeSortRec(lowerIndex, middle);
			mergeSortRec(middle + 1, higherIndex);
			mergeParts(lowerIndex, middle, higherIndex);
		}
	}

	private void mergeParts(int lowerIndex, int middle, int higherIndex) {

		for (int i = lowerIndex; i <= higherIndex; i++) {
			tempPoints[i] = points[i];
		}
		int i = lowerIndex;
		int j = middle + 1;
		int k = lowerIndex;
		while (i <= middle && j <= higherIndex) {
			if (tempPoints[i].compareTo(tempPoints[j]) <= 0) {
				points[k] = tempPoints[i];
				i++;
			} else {
				points[k] = tempPoints[j];
				j++;
			}
			k++;
		}
		while (i <= middle) {
			points[k] = tempPoints[i];
			k++;
			i++;
		}

	}
}
