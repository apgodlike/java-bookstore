package com.book.hooks;

import com.book.api.common.ExtentReportCommon;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks {
	
	@BeforeAll
	public static void beforeAll() {
		ExtentReportCommon.initExtentReport();
	}

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        ExtentReportCommon.startTest(scenario.getName());
        ExtentReportCommon.getTestStep().pass("Starting scenario: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("Finished scenario: " + scenario.getStatus());
    }
    
    @AfterAll
    public static void afterAll() {
    	ExtentReportCommon.flushReport();
    }
}
