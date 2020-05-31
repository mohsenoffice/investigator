package org.servicenow;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.servicenow.utilities.PropertiesReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    public static final String OUTPUT_FILE = "investigator.private.output";
    public static final String EXPECTED_OUTPUT_FILE = "investigator.private.output.expected";


    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        App.main(null);
        assertTrue(compareFiles(PropertiesReader.getInstance().getProperty(OUTPUT_FILE), PropertiesReader.getInstance().getProperty(EXPECTED_OUTPUT_FILE)));
    }

    private boolean compareFiles(String file1Path, String file2Path){
        Path p1 = Paths.get(file1Path);
        Path p2 = Paths.get(file1Path);

        try{
            List<String> listF1 = Files.readAllLines(p1);
            List<String> listF2 = Files.readAllLines(p2);
            return listF1.containsAll(listF2);

        }catch(IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
