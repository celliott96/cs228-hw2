package edu.iastate.cs228.hw2;

/**
 *  
 * @author Clinton Elliott
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class CompareSorters {
	/**
	 * Repeatedly take integer sequences either randomly generated or read from
	 * files. Perform the four sorting algorithms over each sequence of
	 * integers, comparing points by x-coordinate or by polar angle with respect
	 * to the lowest point.
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException {
		//
		// Conducts multiple sorting rounds. In each round, performs the
		// following:
		//
		// a) If asked to sort random points, calls generateRandomPoints() to
		// initialize an array
		// of random points.
		// b) Reassigns to elements in the array sorters[] (declared below) the
		// references to the
		// four newly created objects of SelectionSort, InsertionSort, MergeSort
		// and QuickSort.
		// c) Based on the input point order, carries out the four sorting
		// algorithms in a for
		// loop that iterates over the array sorters[], to sort the randomly
		// generated points
		// or points from an input file.
		// d) Meanwhile, prints out the table of runtime statistics.
		//
		// A sample scenario is given in Section 2 of the project description.
		//
		AbstractSorter[] sorters = new AbstractSorter[4];

		// Within a sorting round, every sorter object write its output to the
		// file
		// "select.txt", "insert.txt", "merge.txt", or "quick.txt" if it is an
		// object of
		// SelectionSort, InsertionSort, MergeSort, or QuickSort, respectively.
		boolean run = true;
		Scanner scan = new Scanner(System.in);
		int numTries = 1;
		int sortOrder = 0;
		Random generator = new Random();

		while (run == true) {
			System.out.println("Comparison of Four Sorting Algorithms \n\n");
			System.out
					.println("Keys: 1 (random integers) 2 (file input) 3 (exit) \n");
			System.out
					.println("Order: 1 (by x-coordinate) 2 (by polar angle) \n\n");
			System.out.println("Trial " + numTries + ": ");
			int choice = scan.nextInt(); // maybe
			switch (choice) {
			case 1:
				System.out.println("Enter number of random points: ");
				int numRP = scan.nextInt();
				Point[] randomPoints = new Point[numRP];
				randomPoints = generateRandomPoints(numRP, generator);
				System.out.println("\n" + "Order used in sorting: ");
				sortOrder = scan.nextInt();
				switch (sortOrder) {
				case 1:
					System.out.println("algorithm    size    time (ns)"
							+ " -----------------------------");
					int numSort = 4;
					while (numSort > 0) {
						switch (numSort) {
						case 4:
							sorters[0] = new SelectionSorter(randomPoints);
							break;
						case 3:
							sorters[1] = new InsertionSorter(randomPoints);
							break;
						case 2:
							sorters[2] = new MergeSorter(randomPoints);
							break;
						case 1:
							sorters[3] = new QuickSorter(randomPoints);
							break;
						}
						numSort++;
					}
					numSort = 0;
					while (numSort <= 4) {
						System.out.println(sorters[numSort].stats());
						numSort++;
					}
					System.out.println(" -----------------------------");
					break;
				case 2:
					// TODO
					break;
				}
				break;

			case 2:
				System.out.println("Points from a file \n");
				System.out.println("File name: ");
				String fileName = scan.nextLine();
				System.out.println("\n" + "Order used in sorting: ");
				sortOrder = scan.nextInt();
				switch (sortOrder) {
				case 1:
					System.out.println("algorithm    size    time (ns)"
							+ " -----------------------------");
					int numSort = 4;
					while (numSort > 0) {
						switch (numSort) {
						case 4:
							sorters[0] = new SelectionSorter(fileName);
							break;
						case 3:
							sorters[1] = new InsertionSorter(fileName);
							break;
						case 2:
							sorters[2] = new MergeSorter(fileName);
							break;
						case 1:
							sorters[3] = new QuickSorter(fileName);
							break;
						}
						numSort++;
					}
					numSort = 0;
					while (numSort <= 4) {
						System.out.println(sorters[numSort].stats());
						numSort++;
					}
					break;
				case 2:
					// TODO
					break;
				}
				break;

			case 3:
				run = false;
				break;
			}
			numTries++;
		}
		scan.close();
	}

	/**
	 * This method generates a given number of random points to initialize
	 * randomPoints[]. The coordinates of these points are pseudo-random numbers
	 * within the range [-50,50] × [-50,50]. Please refer to Section 3 on how
	 * such points can be generated.
	 * 
	 * Ought to be private. Made public for testing.
	 * 
	 * @param numPts
	 *            number of points
	 * @param rand
	 *            Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException
	 *             if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand)
			throws IllegalArgumentException {
		Point[] randomPoints = new Point[numPts];
		int numPoints = numPts;
		int randomNum1 = 0;
		int randomNum2 = 0;
		int i = 0;

		if (numPoints <= 0) // numPoints % 2 != 0
		{
			throw new IllegalArgumentException();
		}

		while (i <= numPoints) { // try < if not working
			randomNum1 = rand.nextInt(101) - 50;
			randomNum2 = rand.nextInt(101) - 50;
			Point currentPoint = new Point(randomNum1, randomNum2);
			randomPoints[i] = currentPoint;
			i++;
		}
		return randomPoints;
	}
}
