/**
 * Jason Luttrell CSD 420 Module 6 Programming Assignment
 * BubbleSort
 *
 * Demonstrates two generic bubble sort methods:
 * 1) Sorting using Comparable
 * 2) Sorting using Comparator
 * Date: January 28, 2026
 */
import java.util.Arrays;
import java.util.Comparator;

/**

 */
public class BubbleSort {

    /**
     * Bubble sort using Comparable
     * Elements must define their natural ordering
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] array) {
        boolean swapped;

        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;

            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                    swapped = true;
                }
            }

            // Stop early if no swaps occurred
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Bubble sort using Comparator
     * Allows custom ordering logic
     */
    public static <T> void bubbleSort(T[] array, Comparator<T> comparator) {
        boolean swapped;

        for (int i = 0; i < array.length - 1; i++) {
            swapped = false;

            for (int j = 0; j < array.length - 1 - i; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    /** 
     * Helper method to swap array elements
     */
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Test code to verify both sorting methods
     */
    public static void main(String[] args) {

        // Test Comparable version with Integers
        Integer[] numbers = {5, 3, 4, 9, 0, 1, 2, 7, 6, 8};
        System.out.println("Before Comparable sort: " + Arrays.toString(numbers));
        bubbleSort(numbers);
        System.out.println("After Comparable sort:  " + Arrays.toString(numbers));

        // Test Comparator version with Strings (reverse order)
        String[] names = {"Charlie", "Alice", "Eve", "Bob"};
        System.out.println("\nBefore Comparator sort: " + Arrays.toString(names));

        bubbleSort(names, (a, b) -> b.compareTo(a));

        System.out.println("After Comparator sort:  " + Arrays.toString(names));
    }
}

/**
 * Results of Test Code
 * Before Comparable sort: [5, 3, 4, 9, 0, 1, 2, 7, 6, 8]
 *After Comparable sort:  [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

 * Before Comparator sort: [Charlie, Alice, Eve, Bob]
 * After Comparator sort:  [Eve, Charlie, Bob, Alice]
 */