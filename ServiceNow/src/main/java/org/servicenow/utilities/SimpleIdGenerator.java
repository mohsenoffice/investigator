package org.servicenow.utilities;

/**
 * Simple ID generator - behave like a counter
 *
 */
public class SimpleIdGenerator implements IdGenerator {

    private static SimpleIdGenerator simpleIdGenerator;

    private SimpleIdGenerator() {
    }

    public static SimpleIdGenerator getInstance(){
        if(simpleIdGenerator == null){
            simpleIdGenerator = new SimpleIdGenerator();
        }
        return simpleIdGenerator;
    }

    @Override
    public int getNewId(String sid) {
        int minRawLength = Integer.parseInt(PropertiesReader.getInstance().getProperty("investigator.private.min.raw.Length"));
        return sid.length() >=  minRawLength  ? UniqueValueGenerator.getUniqueCode(sid.substring(minRawLength)) : UniqueValueGenerator.getUniqueCode(sid);
    }
}
