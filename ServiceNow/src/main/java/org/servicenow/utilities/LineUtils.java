package org.servicenow.utilities;

public class LineUtils {
    public static final String SPACE = " ";
    public static final String INVESTIGATOR_PRIVATE_FIRST_WORD_INDEX = "investigator.private.first.word.index";
    /**
     * This method used to refine a line
     * 1. Remove extra whitespaces (e.g "hello     word" --> "hello word"
     * 2. Trim
     *
     * @param line
     * @return refined line
     */
    public static String refine(String line) {
        return line.replaceAll("\\s{2,}", SPACE).trim();
    }

    public static boolean isValid(String[] sentenceArr) {
        int firstIndex = Integer.parseInt(PropertiesReader.getInstance().getProperty(INVESTIGATOR_PRIVATE_FIRST_WORD_INDEX));
        return sentenceArr.length > firstIndex;
    }
}
