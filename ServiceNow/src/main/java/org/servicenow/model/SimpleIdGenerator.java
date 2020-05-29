package org.servicenow.model;

import com.sun.org.apache.bcel.internal.generic.IFGE;

/**
 * Simple ID generator - behave like a counter
 *
 */
public class SimpleIdGenerator implements IdGenerator {
    private static SimpleIdGenerator simpleIdGenerator=null;

    private static int counter;

    private SimpleIdGenerator() {
        counter = 0;
    }

    public static SimpleIdGenerator getInstance(){
        if(simpleIdGenerator == null){
            simpleIdGenerator = new SimpleIdGenerator();
        }
        return simpleIdGenerator;
    }

    /**
     * Should be synchronized if it's called from multiple threads
     * @return new ID
     */
    @Override
    public int getNewId() {
        return ++counter;
    }
}
