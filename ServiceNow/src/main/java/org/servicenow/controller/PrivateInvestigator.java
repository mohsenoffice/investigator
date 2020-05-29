package org.servicenow.controller;

import org.servicenow.model.Sentences;
import org.servicenow.model.Sentence;
import org.servicenow.model.OutputData;
import org.servicenow.utilities.PropertiesReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PrivateInvestigator implements Investigator {

    public static final String INPUT_FILE = "investigator.private.input";
    public static final String SPACE = " ";
    public static final String MIN_RAW_LENGTH = "investigator.private.min.raw.Length";
    Sentences sentencesRepository;
    OutputData investigationOutput;

    public PrivateInvestigator() {
        sentencesRepository = new Sentences();
        investigationOutput = new OutputData();
    }

    @Override
    public void investigate() {
        String fileName = PropertiesReader.getInstance().getProperty(INPUT_FILE);
        int minRawLength = Integer.parseInt(PropertiesReader.getInstance().getProperty(MIN_RAW_LENGTH));
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line-> {
                if(line.trim().length()> minRawLength) {
                    Sentence sentence = new Sentence(refine(line));
                    //Ignore non valid and existing lines
                    if (sentence.isValid() && isNew(sentence)) {
                        investigationOutput.add(sentence);
                        sentencesRepository.add(sentence);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method used to refine a line
     * 1. Remove extra whitespaces (e.g "hello     word" --> "hello word"
     * 2. Trim
     *
     * @param line
     * @return refined line
     */
    private String refine(String line) {
        return line.replaceAll("\\s{2,}", SPACE).trim();
    }

    private boolean isNew(Sentence sentence) {
        return sentencesRepository.getSentenceByID(sentence.getId()) == null;
    }

    @Override
    public void printResults() {
        investigationOutput.printResults(sentencesRepository);
    }
}
