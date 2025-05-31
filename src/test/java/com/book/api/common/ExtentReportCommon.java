package com.book.api.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportCommon {
	
	private static ExtentReports extentReports;
	private static ExtentTest extentTest;
	
	public static void initExtentReport() {
		extentReports = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter("target/extent-report-cucumber.html");
		extentReports.attachReporter(reporter);
		
	}
	
	public static ExtentTest getTestStep() {
		return extentTest;
	}
	
	public static void startTest(String value) {
        extentTest = extentReports.createTest(value);
    }
	
	public static void flushReport() {
        if (extentReports != null) {
        	extentReports.flush();
        }
    }
    

}
