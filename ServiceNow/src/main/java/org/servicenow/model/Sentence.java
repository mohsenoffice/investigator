package org.servicenow.model;

import org.servicenow.utilities.PropertiesReader;

import java.util.Arrays;

public class Sentence {
    public static final String SPACE = " ";
    public static final String INVESTIGATOR_PRIVATE_FIRST_WORD_INDEX = "investigator.private.first.word.index";

    private int id;
    private String[] sentenceArr;

    public Sentence(String line) {
        sentenceArr = line.split(SPACE);
        this.id = SimpleIdGenerator.getInstance().getNewId(line);
    }

    public String getSentenceText() {
        StringBuilder sentenceText = new StringBuilder();
        Arrays.stream(sentenceArr).forEach(sentence -> sentenceText.append(sentence).append(SPACE));
        return sentenceText.toString().trim();
    }

    public int getId() {
        return id;
    }

    public boolean isValid() {
        int firstIndex = Integer.parseInt(PropertiesReader.getInstance().getProperty(INVESTIGATOR_PRIVATE_FIRST_WORD_INDEX));
        return sentenceArr.length > firstIndex;
    }

    public String getWordAtIndex(int index) {
        return sentenceArr[index];
    }

    public String[] getSentenceArr() {
        return sentenceArr;
    }
}
