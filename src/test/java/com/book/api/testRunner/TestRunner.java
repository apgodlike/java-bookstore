package com.book.api.testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/main/resources/features",
    glue = {"com.book.api.steps", "com.book.hooks"},
    plugin = { "pretty",
//            "html:target/extent-report.html",
    		 "com.book.hooks.StepLogger",
            "json:target/cucumber.json"},
    monochrome = true,
    tags = "@regression"
)
public class TestRunner {

}
