package org.servicenow.utilities;

import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import org.servicenow.model.SimpleIdGenerator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class PropertiesReader {
    private static PropertiesReader propertiesReader;

    Properties prop = new Properties();
    String propFileName = "config.properties";
    InputStream inputStream;

    private PropertiesReader() {
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Property file '" + propFileName + "' not found in the classpath");
        }
    }
    public static PropertiesReader getInstance(){
        if(propertiesReader == null){
            propertiesReader = new PropertiesReader();
        }
        return propertiesReader;
    }

    public String getProperty(String propertyName){
        return prop.getProperty(propertyName);
    }




}
