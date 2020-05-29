package org.servicenow;

import org.servicenow.controller.Investigator;
import org.servicenow.controller.PrivateInvestigator;
import org.servicenow.utilities.PropertiesReader;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Investigator
 *
 */
public class App {

    public static final String OUTPUT_FILE = "investigator.private.output";
    public static final String INPUT_FILE = "investigator.private.input";

    public static void main(String[] args ) {
        System.out.println( "Hello investigator!" );
        System.out.println("Input file was: " + PropertiesReader.getInstance().getProperty(INPUT_FILE));
        Investigator investigator = new PrivateInvestigator();

        long startTime = System.currentTimeMillis();
        investigator.investigate();
        long endTime = System.currentTimeMillis();
        investigator.printResults();
        long endTimeWithPrint = System.currentTimeMillis();

        System.out.println("Total investigation time (without printing the result): "+ (endTime - startTime) + "ms");
        System.out.println("Total investigation time and printing the results : "+ (endTimeWithPrint-startTime) + "ms");
        System.out.println("Output file: " + PropertiesReader.getInstance().getProperty(OUTPUT_FILE));
        System.out.println("Done.");
    }

    static int getCode(String str){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            md.update(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new BigInteger(1, md.digest()).intValue();
    }
}
