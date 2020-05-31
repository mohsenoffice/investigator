package org.servicenow.utilities;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class LineUtilsTest {

    @Test
    public void refine() {
        assertEquals("a b", LineUtils.refine("a      b"));
        assertEquals("b", LineUtils.refine("       b"));
        assertEquals("a", LineUtils.refine("a       "));
        assertEquals("", LineUtils.refine("        "));
        assertEquals("a b", LineUtils.refine("  a     b   "));
    }

    @Test
    public void isValid() {
        //This is a valid test only if investigator.private.first.word.index=2
        String[] arr0 = {};
        String[] arr1 = {"1"};
        String[] arr2 = {"1", "2"};
        String[] arr3 = {"1", "2", "3"};

        assertFalse(LineUtils.isValid(arr0));
        assertFalse(LineUtils.isValid(arr1));
        assertFalse(LineUtils.isValid(arr2));
        assertTrue(LineUtils.isValid(arr3));
    }
}