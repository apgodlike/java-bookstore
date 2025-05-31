package com.book.api.common;

import org.junit.Assert;

public class AssertUtil {
    public static void assertEquals(String message, Object expected, Object actual) {
        try {
            Assert.assertEquals(message, expected, actual);
            ExtentReportCommon.getTestStep().pass("✅ " + message + " | Expected: " + expected + ", Actual: " + actual);
        } catch (AssertionError e) {
            ExtentReportCommon.getTestStep().fail("❌ " + message + " | Expected: " + expected + ", Actual: " + actual);
            throw e;
        }
    }
}
