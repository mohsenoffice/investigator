package org.servicenow.model;

public class Sentence {
    private int id;
    private String sentenceText;



    public Sentence(String line) {
        this.sentenceText = line;
        this.id = sentenceText.substring(20).hashCode();//SimpleIdGenerator.getInstance().getNewId();
    }

    public String getSentenceText() {
        return sentenceText;
    }

    public int getId() {
        return id;
    }

    public boolean isValid() {
        return sentenceText.split(" ").length>2;
    }

    public String getWordAtIndex(int index) {
        return getSentenceText().split(" ")[index];
    }
}
