package org.servicenow.controller;

import org.servicenow.model.Sentences;
import org.servicenow.model.Sentence;
import org.servicenow.model.OutputData;
import org.servicenow.model.SimpleIdGenerator;
import org.servicenow.utilities.PropertiesReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PrivateInvestigator implements Investigator {

    Sentences sentences;
    OutputData output;

    public PrivateInvestigator() {
        sentences = new Sentences();
        output = new OutputData();
    }

    @Override
    public void investigate() {
        String fileName = PropertiesReader.getInstance().getProperty("investigator.private.input");
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line-> {
                if(line.trim().length()>Integer.parseInt(PropertiesReader.getInstance().getProperty("investigator.private.min.raw.Length"))) {
                    Sentence sentence = new Sentence(line.replaceAll("\\s{2,}", " ").trim());
                    if (sentence.isValid() && sentences.getSentenceByID(sentence.getId()) == null) {
                        output.add(sentence);
                        sentences.add(sentence);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printResults() {
        output.printResults(sentences);
    }
}
