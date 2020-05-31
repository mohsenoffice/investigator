package org.servicenow.utilities;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UniqueValueGenerator {
    private static MessageDigest md = null;
    public static final String SPACE = " ";
    public static final String INVESTIGATOR_PRIVATE_FIRST_WORD_INDEX = "investigator.private.first.word.index";

    static{
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static int getUniqueCode(String str){
        try {
            md.update(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new BigInteger(1, md.digest()).intValue();
    }

    public static  int generateKey(String[] sentenceArray, int wordToSkip) {
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
}
