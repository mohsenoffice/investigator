package org.servicenow.model;

import java.util.HashMap;

public class Sentences {
    HashMap<Integer, Sentence> sentences;

    public Sentences() {
        sentences = new HashMap<Integer, Sentence>();
    }

    public void add(Sentence sentence) {
        sentences.put(sentence.getId(), sentence);
    }

    public Sentence getSentenceByID(int id){
        return sentences.get(id);
    }
}
