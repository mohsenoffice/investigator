package org.servicenow.model;

import org.servicenow.controller.FilePrinter;
import org.servicenow.controller.Printer;
import org.servicenow.utilities.PropertiesReader;
import org.servicenow.utilities.UniqueValueGenerator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class OutputData {
    public static final String SPACE = " ";
    public static final String INVESTIGATOR_PRIVATE_FIRST_WORD_INDEX = "investigator.private.first.word.index";
    public static final String THE_CHANGING_WORD_WAS = "The changing word was: ";
    public static final String Comma = ", ";
    public static final String NEW_LINE = "\n";
    HashMap<String, Set<Integer>> similarityMap;
    Set<String> similarityOutput;

    public OutputData() {
        similarityMap = new HashMap<>();
        similarityOutput = new HashSet<>();
    }

    public void printSimilarLineIntoFile(){
        //similarLines.forEach(System.out::println);
    }

    public void add(Sentence sentence) {
        int firstIndex = Integer.parseInt(PropertiesReader.getInstance().getProperty(INVESTIGATOR_PRIVATE_FIRST_WORD_INDEX));

        for(int i=firstIndex ; i<sentence.getSentenceArr().length ; i++){
            int skipperKey = generateKey(sentence.getSentenceArr(), i);
            addToSimilarityMap(skipperKey, i, sentence.getId());
        }
    }

    private void addToSimilarityMap(int skipperKey, int diffLocation, int sentenceID) {
        String similarityMapKey = skipperKey + "$" + diffLocation;
        if(similarityMap.containsKey(similarityMapKey)){
            similarityMap.get(similarityMapKey).add(sentenceID);
            similarityOutput.add(similarityMapKey);
        }else{
            Set<Integer> newSet = new HashSet<>();
            newSet.add(sentenceID);
            similarityMap.put(similarityMapKey,newSet);
        }
    }

    private int generateKey(String[] sentenceArray, int wordToSkip) {
        StringBuilder subString = new StringBuilder();
        int firstIndex = Integer.parseInt(PropertiesReader.getInstance().getProperty(INVESTIGATOR_PRIVATE_FIRST_WORD_INDEX));
        for(int i=firstIndex ; i<sentenceArray.length ; i++){
            if(i == wordToSkip){
                continue;
            }
            subString.append(sentenceArray[i]).append(SPACE);
        }
        return UniqueValueGenerator.getUniqueCode(subString.toString());
    }

    public void printResults(Sentences sentences) {
        Printer printer = new FilePrinter();
        printer.print(fetchResults(sentences));

    }

    private String fetchResults(Sentences sentences) {
        StringBuilder results = new StringBuilder();
        similarityOutput.stream().forEach(key -> results.append(fetchSimilarity(key.split("\\$")[1], similarityMap.get(key),sentences)));
        return results.toString();
    }

    private String fetchSimilarity(String changingWordIndex, Set<Integer> sentencesToGet, Sentences allSentencecs) {
        StringBuilder result = new StringBuilder();
        StringBuilder changingWords = new StringBuilder();

        sentencesToGet.forEach(id-> {
            Sentence sentence = allSentencecs.getSentenceByID(id);
            result.append(sentence.getSentenceText()).append(NEW_LINE);
            changingWords.append(sentence.getWordAtIndex(Integer.parseInt(changingWordIndex))).append(Comma);
        });
        result.append(THE_CHANGING_WORD_WAS).
                append(changingWords.toString(), 0, changingWords.length()-2).
                append("\n\n");

        return  result.toString();
    }



}
