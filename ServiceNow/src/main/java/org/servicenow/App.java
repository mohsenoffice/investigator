package org.servicenow;

import org.servicenow.controller.Investigator;
import org.servicenow.controller.PrivateInvestigator;
import org.servicenow.utilities.PropertiesReader;

/**
 * Investigator
 *
 */
public class App {
    public static void main( String[] args ) {
        System.out.println( "Hello investigator!" );
        System.out.println("Input file: " + PropertiesReader.getInstance().getProperty("investigator.private.input"));
        Investigator investigator = new PrivateInvestigator();

        long startTime = System.currentTimeMillis();
        investigator.investigate();
        long endTime = System.currentTimeMillis();
        investigator.printResults();
        long endTimeWithPrint = System.currentTimeMillis();

        System.out.println("Total investigation time (without printing the result): "+ (endTime - startTime) + "ms");
        System.out.println("Total investigation time and printing the results : "+ (endTimeWithPrint-startTime) + "ms");
        System.out.println("Output file: " + PropertiesReader.getInstance().getProperty("investigator.private.output"));
        System.out.println("Done.");
    }
}
