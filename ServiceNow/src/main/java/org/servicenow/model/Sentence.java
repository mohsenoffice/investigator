package org.servicenow.model;

import org.servicenow.utilities.LineUtils;
import org.servicenow.utilities.SimpleIdGenerator;

import java.util.Arrays;

public class Sentence {
    public static final String SPACE = " ";


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
        return LineUtils.isValid(sentenceArr);

    }

    public String getWordAtIndex(int index) {
        return sentenceArr[index];
    }

    public String[] getSentenceArr() {
        return sentenceArr;
    }
}
