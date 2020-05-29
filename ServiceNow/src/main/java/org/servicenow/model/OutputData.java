package org.servicenow.model;

import org.servicenow.utilities.PropertiesReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OutputData {
    public static final String SPACE = " ";
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
        String[] sentenceArray = sentence.getSentenceText().split(SPACE);
        for(int i=2; i<sentenceArray.length ; i++){
            int skipperKey = generateKey(sentenceArray,i);
            addToSimilarityMap(skipperKey,i,sentence.getId());
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
        for(int i=2 ; i<sentenceArray.length ; i++){
            if(i == wordToSkip){
                continue;
            }
            subString.append(sentenceArray[i]).append(SPACE);
        }
        return subString.toString().hashCode();
    }

    public void printResults(Sentences sentences) {
        similarityOutput.stream().forEach(key -> fetchAndPrint(key.split("\\$")[1], similarityMap.get(key),sentences));
    }

    private void fetchAndPrint(String changingWordIndex, Set<Integer> sentencesToGet, Sentences allSentencecs) {
        StringBuilder changingWords = new StringBuilder();

        sentencesToGet.forEach(id-> {
            Sentence sentence = allSentencecs.getSentenceByID(id);
            System.out.println(sentence.getSentenceText());
            changingWords.append(sentence.getWordAtIndex(Integer.parseInt(changingWordIndex))).append(", ");
        });
        String changingWordsStr = changingWords.toString();
        Path path = Paths.get( PropertiesReader.getInstance().getProperty("investigator.private.output"));
        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            try {
                writer.append("The changing word was: " + changingWordsStr.substring(0, changingWordsStr.length()-2));
                writer.append("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The changing word was: " + changingWordsStr.substring(0, changingWordsStr.length()-2));
        System.out.println("");
    }

}
