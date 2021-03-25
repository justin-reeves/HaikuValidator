package io.haikuvalidator;

import edu.northwestern.at.morphadorner.corpuslinguistics.syllablecounter.EnglishSyllableCounter;

import java.util.Collection;

/**
 * Singleton Class for validating if a poem matches the haiku syllable 5-7-5 pattern.
 *
 * <p>
 * Created By: Justin
 * Created On: 3/12/2021
 */
public class HaikuValidator {

    // FIELDS

    // CONSTRUCTORS
    private HaikuValidator() {
    }


    // BUSINESS METHODS
    public static boolean isValidHaiku(Collection<String> haiku) {
        return isValidLength(haiku) && hasValidLineSyllableCount(haiku);
    }

    public static int getLineSyllableCount(String[] line) {
        EnglishSyllableCounter syllableCounter = new EnglishSyllableCounter();
        int lineSyllableCount = 0;

        for (String word :
                line) {
            String noPunctuationWord = word.replaceAll("\\p{Punct}", "");
            lineSyllableCount += syllableCounter.countSyllables(noPunctuationWord);
        }

        return lineSyllableCount;
    }


    // ACCESSOR METHODS


    // HELPER METHODS
    // A haiku should only have 3 lines
    private static boolean isValidLength(Collection<String> haiku) {
        return haiku.size() == 3;
    }

    // A haiku should match the 5-7-5 syllable/line count
    private static boolean hasValidLineSyllableCount(Collection<String> haiku) {
        boolean isValid = true;


        // For keeping track of what count to check against
        LineSyllableCount[] expectedSyllables = LineSyllableCount.values();
        int lineNumber = 1;

        for (String line :
                haiku) {
            String[] lineWords = line.split(" ");
            int lineSyllableCount = getLineSyllableCount(lineWords);

            if (lineSyllableCount != expectedSyllables[lineNumber - 1].syllableCount()) {
                isValid = false;
                break;
            } else {
                lineNumber++;
            }
        }

        return isValid;
    }


    // OVERRIDES


}