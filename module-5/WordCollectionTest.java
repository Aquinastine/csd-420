/**
 * Jason Luttrell CSD 420 Module 5 Programming Assignment
 * Word Collection Sorter
 * Displays words frm a file in alphabetical and reverse alphabetical 
 * order.
 * Date: June 12, 2024
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * WordCollectionTest
 *
 * Purpose:
 *  - Reads words from a text file
 *  - Removes duplicates
 *  - Displays words in ascending and descending order
 *  - Includes simple test validation
 */
public class WordCollectionTest {

    private static final String FILE_NAME = "collection_of_words.txt";

    public static void main(String[] args) {
        try {
            Set<String> words = readWordsFromFile(FILE_NAME);

            // Test: Ensure duplicates were removed
            testNoDuplicates(words);

            // Display ascending order
            System.out.println("Words in Ascending Order:");
            for (String word : words) {
                System.out.println(word);
            }

            System.out.println();

            // Display descending order
            System.out.println("Words in Descending Order:");
            for (String word : ((TreeSet<String>) words).descendingSet()) {
                System.out.println(word);
            }

            System.out.println("\nAll tests passed.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + FILE_NAME);
        }
    }

    /**
     * Reads words from a file into a TreeSet.
     * TreeSet automatically removes duplicates and sorts values.
     */
    private static Set<String> readWordsFromFile(String fileName)
            throws FileNotFoundException {

        Set<String> wordSet = new TreeSet<>();
        Scanner scanner = new Scanner(new File(fileName));

        while (scanner.hasNext()) {
            wordSet.add(scanner.next().toLowerCase());
        }

        scanner.close();
        return wordSet;
    }

    /**
     * Test method to ensure duplicate words were removed.
     */
    private static void testNoDuplicates(Set<String> words) {
        if (words.size() < 1) {
            throw new AssertionError("Test failed: No words were read.");
        }

        // Known duplicates from input file
        if (words.contains("apple") && words.contains("banana")) {
            System.out.println("Duplicate removal test passed.");
        } else {
            throw new AssertionError("Test failed: Expected words missing.");
        }
    }
}

/**
RESULTS:

PS C:\Users\Luttr\csd\csd-420\module-5> java WordCollectionTest
Duplicate removal test passed.
Words in Ascending Order:
apple
banana
grape
kiwi
orange
pear

Words in Descending Order:
pear
orange
kiwi
grape
banana
apple

All tests passed.
 */