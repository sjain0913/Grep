/* Collaboration Statement: In order to help learn course concepts,
I worked on the homework with Joshua Donegal, discussed homework topics
and issues with Joshua Donegal, and/or consulted related material that
can be found at N/A. */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * This class represents the class Grep.
 * @author Saumya Jain
 * @version 1.0
 */

public class Grep {
    private static String[] errors = {"Incorrect amount of arguments",
        "This argument is not valid", "This file/directory is not valid",
        "Invalid String"};

/**
 * This is the main method
 * @param args These are the arguments entered in the console.
 */
    public static void main(String[] args) {
        if ((args.length != 2) && (args.length != 3)) {
            throw new IllegalArgumentException(errors[0]);
        }
        boolean caseSensitive;
        String toSearch;
        File searchedFile;
        if (args.length == 3) {
            if (args[0].compareTo("-i") != 0) {
                throw new IllegalArgumentException(errors[1]);
            } else {
                searchedFile = new File(args[2]);
                toSearch = args[1];
                caseSensitive = false;
            }
        } else {
            searchedFile = new File(args[1]);
            toSearch = args[0];
            caseSensitive = true;
        }
        try {
            System.out.print(grep(searchedFile, toSearch, caseSensitive));
        } catch (InvalidSearchStringException e) {
            System.out.println(errors[3]);
        } catch (FileNotFoundException e) {
            System.out.println(errors[2]);
        }
    }

/**
 * This is the grep method
 * @param searchedFile This is the File/Directory being searched.
 * @param toSearch This is the String to be searched.
 * @param ifSensitive This indicated if case matters.
 * @return A String with all the occurences of of the String
    to be found along with the File location and Line number
 */
    public static String grep(File searchedFile, String toSearch,
        boolean ifSensitive) throws FileNotFoundException {
        String output = "";
        if (toSearch.contains("\\n") || toSearch.contains("\n")) {
            throw new InvalidSearchStringException(errors[3]);
        }
        if (!(searchedFile.isDirectory() || searchedFile.isFile())) {
            throw new FileNotFoundException(errors[2]);
        }
        if (searchedFile.isFile()) {
            Scanner scanner = new Scanner(searchedFile);
            int lineNum = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (ifSensitive) {
                    if (line.contains(toSearch)) {
                        output += searchedFile + ":" + lineNum + ":"
                            + line + "\n";
                    }
                    lineNum++;
                } else {
                    String lineLow = line.toLowerCase();
                    String toSearchLow = toSearch.toLowerCase();
                    if (lineLow.contains(toSearchLow)) {
                        output += searchedFile + ":" + lineNum + ":"
                            + line + "\n";
                    }
                    lineNum++;
                }
            }
            scanner.close();
        } else if (searchedFile.isDirectory()) {
            for (File file : searchedFile.listFiles()) {
                output += grep(file, toSearch, ifSensitive);
            }
        }
        return output;
    }
}