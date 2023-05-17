import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
* This program uses a selection sort.
*
* @author  Alexander Matheson
* @version 1.0
* @since   2023-05-15
*/

public final class SelectionSort {
    /**
    * For style checker.
    *
    *
    * @exception IllegalStateException Utility class.
    * @see IllegalStateException
    */
    private SelectionSort() {
        throw new IllegalStateException("Utility class");
    }

    /**
    * Print messages.
    *
    * @param args Unused
    */
    public static void main(String[] args) {
        // Declare variables.
        String sortStr = "";

        // String variables because the style check is being pedantic.
        final String sp = " ";
        final String error = "Error: ";
        final String newLine = "\n";

        // Get input from file.
        final String[] inputArr = getInput();

        // Loop to send each array to function.
        for (int counter = 0; counter < inputArr.length; counter++) {
            try {
                // Convert inputArr to int.
                final int size = inputArr[counter].split(sp).length;
                final int[] arrInt = new int[size];
                for (int location = 0; location < size; location++) {
                    arrInt[location] = Integer.parseInt(
                        inputArr[counter].split(sp)[location]);
                }

                // Call function.
                final int[] sorted = sort(arrInt);
                sortStr = sortStr + Arrays.toString(sorted) + newLine;
            } catch (NumberFormatException err) {
                sortStr = sortStr + error + err.getMessage() + newLine;
            }
        }

        try {
            // Choose (or create) a file to print output to.
            final FileWriter output = new FileWriter("output.txt");
            output.write(sortStr);
            System.out.println(sortStr);

            // Close writer.
            output.close();
        } catch (IOException err) {
            System.err.println(error + err.getMessage());
        }
    }

    /**
    * This function uses an iterative selection sort.
    *
    * @param listOfNum from file
    * @return sorted array
    */
    public static int[] sort(int[] listOfNum) {
        // Loop to iterate through array.
        for (int index = 0; index < listOfNum.length - 1; index++) {
            int minIndex = index;
            for (int element = index + 1;
                    element < listOfNum.length - 1; element++) {
                // Compare each element to find lowest unsorted element.
                if (listOfNum[element] < listOfNum[minIndex]) {
                    minIndex = element;
                }
            }

            // Move lowest unsorted element to the front of the unsorted
            // section of the array.
            if (minIndex != index) {
                final int temp = listOfNum[index];
                listOfNum[index] = listOfNum[minIndex];
                listOfNum[minIndex] = temp;
            }
        }
        return listOfNum;
    }

    /**
    * This function gets input from a file.
    *
    * @return file contents as an array separated by newlines.
    */
    public static String[] getInput() {
        // Declare list and array.
        final ArrayList<String> inputList = new ArrayList<String>();
        String[] inputArr = new String[0];

        try {
            // Choose a file to get input from.
            final File input = new File("input.txt");
            final Scanner scanInput = new Scanner(input);

            // Loop to read each line of input file.
            while (scanInput.hasNextLine()) {
                // Add the next line.
                inputList.add(scanInput.nextLine());
            }

            // Create an array with all elements in the input list.
            inputArr = new String[inputList.size()];
            for (int location = 0; location < inputArr.length; location++) {
                inputArr[location] = inputList.get(location);
            }
        } catch (IOException err) {
            // For when no input file is found.
            System.err.println("An error occurred: " + err.getMessage());
            System.exit(0);
        } finally {
            return inputArr;
        }
    }
}
