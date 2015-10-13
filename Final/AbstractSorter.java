package edu.iastate.cs228.hw2;

/**
 * 
 * @author Clinton Elliott
 * 
 */

 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort,
 * and QuickSort. It stores the input (later on the sorted) sequence and records
 * the employed sorting algorithm, the comparison method, and the time spent on
 * sorting.
 *
 */

public abstract class AbstractSorter {

	protected Point[] points; // Array of points operated on by a sorting
								// algorithm.
								// The number of points is given by
								// points.length.

	protected String algorithm = null; // "selection sort", "insertion sort",
										// "mergesort", or "quicksort".
										// Initialized by a subclass
										// constructor.
	protected boolean sortByAngle; // true if last sort was done by polar angle
									// and false
									// if by x-coordinate

	protected String outputFileName; // "select.txt", "insert.txt", "merge.txt",
										// or "quick.txt"

	protected long sortingTime; // execution time in nanoseconds.

	protected Comparator<Point> pointComparator; // comparator which compares
													// polar angle if
													// sortByAngle == true and
													// x-coordinate if
													// sortByAngle == false

	private Point lowestPoint; // lowest point in the array, or in case of a
								// tie, the
								// leftmost of the lowest points. This point is
								// used
								// as the reference point for polar angle based
								// comparison.

	// Add other protected or private instance variables you may need.

	protected AbstractSorter() {
		// No implementation needed. Provides a default super constructor to
		// subclasses.
		// Removable after implementing SelectionSorter, InsertionSorter,
		// MergeSorter, and QuickSorter.
	}

	/**
	 * This constructor accepts an array of points as input. Copy the points
	 * into the array points[]. Sets the instance variable lowestPoint.
	 * 
	 * @param pts
	 *            input array of points
	 * @throws IllegalArgumentException
	 *             if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException {
		if (pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		} else {
			points = pts;
			lowestPoint = pts[0];
		}
	}

	/**
	 * This constructor reads points from a file. Sets the instance variables
	 * lowestPoint and outputFileName.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException
	 *             when the input file contains an odd number of integers
	 */
	protected AbstractSorter(String inputFileName)
			throws FileNotFoundException, InputMismatchException {
		int i = 0;
		int numPoints = 0;
		Scanner scan;
		File inputFile = new File(inputFileName);
		if (inputFile.exists()) {
			scan = new Scanner(inputFile);
		} else {
			throw new FileNotFoundException();
		}
		while (scan.hasNextInt()) {
			numPoints++;
		}
		scan.close();
		if (numPoints % 2 != 0) {
			throw new InputMismatchException();
		}
		scan = new Scanner(inputFile);
		Point[] sortedPoints = new Point[numPoints / 2];
		while (scan.hasNextInt()) {
			Point currentPoint = new Point(scan.nextInt(), scan.nextInt());
			sortedPoints[i] = currentPoint;
			i++;
		}
		points = sortedPoints;
		lowestPoint = sortedPoints[0];
		scan.close();
	}

	/**
	 * Sorts the elements in points[].
	 * 
	 * a) in the non-decreasing order of x-coordinate if order == 1 b) in the
	 * non-decreasing order of polar angle w.r.t. lowestPoint if order == 2
	 * (lowestPoint will be at index 0 after sorting)
	 * 
	 * Sets the instance variable sortByAngle based on the value of order. Calls
	 * the method setComparator() to set the variable pointComparator and use it
	 * in sorting. Records the sorting time (in nanoseconds) using the
	 * System.nanoTime() method. (Assign the time to the variable sortingTime.)
	 * 
	 * @param order
	 *            1 by x-coordinate 2 by polar angle w.r.t lowestPoint
	 *
	 * @throws IllegalArgumentException
	 *             if order is less than 1 or greater than 2
	 */
	public abstract void sort(int order) throws IllegalArgumentException;

	/**
	 * Outputs performance statistics in the format:
	 * 
	 * <sorting algorithm> <size> <time>
	 * 
	 * For instance,
	 * 
	 * selection sort 1000 9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project
	 * description.
	 */
	public String stats() {
		String stats = new String();
		stats = algorithm + "\t" + points.length + "\t" + sortingTime;
		return stats;
	}

	/**
	 * Write points[] to a string. When printed, the points will appear in order
	 * of increasing index with every point occupying a separate line. The x and
	 * y coordinates of the point are displayed on the same line with exactly
	 * one blank space in between.
	 */
	@Override
	public String toString() {
		String pointsString = new String();
		pointsString = points.toString();
		return pointsString;
	}

	/**
	 * 
	 * This method, called after sorting, writes point data into a file by
	 * outputFileName. It will be used for Mathematica plotting to verify the
	 * sorting result. The data format depends on sortByAngle. It is detailed in
	 * Section 4.1 of the projection description proj2.pdf.
	 * 
	 * @throws FileNotFoundException
	 */
	public void writePointsToFile() throws FileNotFoundException {
		BufferedWriter outputWriter = null;
		  outputWriter = new BufferedWriter(new FileWriter(outputFileName));
		  for (int i = 0; i < points.length; i++) {
		    outputWriter.write(points[i]+"\n");
		    outputWriter.newLine();
		  }
		  outputWriter.flush();  
		  outputWriter.close(); 
	}

	/**
	 * Generates a comparator on the fly that compares by polar angle if
	 * sortByAngle == true and by x-coordinate if sortByAngle == false. Set the
	 * protected variable pointComparator to it. Need to create an object of the
	 * PolarAngleComparator class and call the compareTo() method in the Point
	 * class, respectively for the two possible values of sortByAngle.
	 * 
	 * @param order
	 */
	protected void setComparator(int order) {
		// TODO
	}

	/**
	 * Swap the two elements indexed at i and j respectively in the array
	 * points[].
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j) {
		Point temp = points[i];
		points[i] = points[j];
		points[j] = temp;
	}
}
