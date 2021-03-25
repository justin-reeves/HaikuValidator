package io.haikuvalidator.client;

import io.haikuvalidator.HaikuValidator;

import java.io.*;
import java.util.*;

/**
 * Client side code for reading a file containing haiku and validating each haiku based on line-syllable count.
 *
 * SEE README.txt for information on running this project, valid input file format, and other project info.
 *
 * <p>
 * Created By: Justin Reeves
 * Created On: 3/12/2021
 * Credit to:
 *  Academic and Research Technologies
 *    Northwestern University - MorphAdorner project http://morphadorner.northwestern.edu/morphadorner/
 *    for providing the syllable counter used in this project.
 */
class HaikuValidatorClient {
    private static String OUTPUT_FILE = "validated_haiku.txt";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Invalid usage. Expected: java io.haikuvalidator.HaikuValidator \"path/to/file.txt\"");
            System.out.println("See README.txt for more information about expected usage and file format.");
        } else {
            String fileName = args[0];

            Map<String, List<String>> authorHaikuMap = getAuthorHaikuMap(fileName);
            printResults(authorHaikuMap);
        }

    }

    private static Map<String, List<String>> getAuthorHaikuMap(String fileName) {
        Map<String, List<String>> map = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (true) {
                String author = reader.readLine();

                // Check if we've reached the end or if invalid file format
                // I.e., could have reached EOF or an unexpected '\n' character
                if (author == null) {
                    break;
                } else if (author.equals('\n')) {
                    throw new IOException("Invalid File Format");
                }

                List<String> haikuLines = new ArrayList<>();
                String curLine = reader.readLine();

                // Ensure reader hasn't reached EOF
                // AND
                // Not reading a new author's haiku - file should have a '\n' between haiku
                while (curLine != null && !curLine.equals("")) {
                    haikuLines.add(curLine);
                    curLine = reader.readLine();
                }

                map.put(author, haikuLines);

                if (curLine == null) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

        return map;
    }

    private static void printResults(Map<String, List<String>> authorHaikuMap) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(OUTPUT_FILE))){

            for (Map.Entry<String, List<String>> entry :
                    authorHaikuMap.entrySet()) {
                boolean isValidHaiku = HaikuValidator.isValidHaiku(entry.getValue());
                printEntry(writer, entry);
                writer.printf(" - %s\n\n",
                        (isValidHaiku ? "Valid" : "Invalid"));
            }
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

    }

    private static void printEntry(PrintWriter writer, Map.Entry<String, List<String>> entry) {
        for (String s :
                entry.getValue()) {
            writer.printf("[%d]\t%s\n",
                    HaikuValidator.getLineSyllableCount(s.split(" ")),
                    s);
        }

        writer.printf("\t\t- %s", entry.getKey());
    }
}