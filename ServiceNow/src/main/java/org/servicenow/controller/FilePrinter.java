package org.servicenow.controller;

import org.servicenow.utilities.PropertiesReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePrinter implements Printer {
    @Override
    public void print(String str) {
        Path path = Paths.get( PropertiesReader.getInstance().getProperty("investigator.private.output"));
        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            try {
                writer.append(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
