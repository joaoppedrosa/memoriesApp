package com.jpp.memories.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author João Pedro Pedrosa, memories on 14-03-2017.
 */
public class StringsUtilsTest {

    @Test
    public void isNullOrEmpty() throws Exception {
        assertEquals(StringsUtils.isNullOrEmpty(null), true);
        assertEquals(StringsUtils.isNullOrEmpty(""), true);
    }

    @Test
    public void isNotNullOrEmpty() throws Exception {
        assertEquals(StringsUtils.isNullOrEmpty("123"), false);
    }
}