/*  Jason Luttrell
    CSD 42o Advanced Java Programming
    Module 2 View Randoms Application
    December 14, 2025
    
This program generates two arrays of random integers and doubles,
writes them to a file named "LuttrellDataFile.dat", and appends new
data each time it is run.

     */ 

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class LuttrellWriteData {

    public static void writeRandomData(String filename) {
        Random rand = new Random();

        int[] intArray = new int[5];
        double[] doubleArray = new double[5];

        for (int i = 0; i < 5; i++) {
            intArray[i] = rand.nextInt(100) + 1;
            doubleArray[i] = Math.round(rand.nextDouble() * 100 * 100.0) / 100.0;
        }

        // Ensure parent folder exists if you ever use one
        File file = new File(filename);
        File parent = file.getParentFile();
        if (parent != null) parent.mkdirs();

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write("Integers: ");
            for (int v : intArray) writer.write(v + " ");
            writer.write(System.lineSeparator());

            writer.write("Doubles: ");
            for (double d : doubleArray) writer.write(d + " ");
            writer.write(System.lineSeparator());

            writer.write("------------------------");
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        writeRandomData("data/LuttrellDataFile.dat");
        System.out.println("Data written successfully.");
    }
}
