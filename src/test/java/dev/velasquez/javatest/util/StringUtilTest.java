package dev.velasquez.javatest.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringUtilTest {

    @Test
    public void repeatStringOnceTest() {
        Assert.assertEquals("hola", StringUtil.repeat("hola", 1));
    }

    @Test
    public void repeatStringMulyipleTimesTest() {
        Assert.assertEquals("holaholahola", StringUtil.repeat("hola", 3));
    }

    @Test
    public void repeatStringZeroTimes() {
        Assert.assertEquals("", StringUtil.repeat("hola", 0));
    }

    /*
     * Cuando esperamos una Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void repeatStringNegativeTimes() {
        StringUtil.repeat("hola", -1);
    }

    @Test
    public void isEmptyNoEmpty() {
        String strToTest = "Hola mundo Inmundo";
        assertFalse(StringUtil.isEmpty(strToTest));
    }

    @Test
    public void isEmptyIsEmpty() {
        String strToTest = "";
        assertTrue(StringUtil.isEmpty(strToTest));
    }

    @Test
    public void isEmptyIsNull() {
        String strToTest = null;
        assertTrue(StringUtil.isEmpty(strToTest));
    }

    @Test
    public void isEmptyIsSpace() {
        String strToTest = "   ";
        assertTrue(StringUtil.isEmpty(strToTest));
    }
}