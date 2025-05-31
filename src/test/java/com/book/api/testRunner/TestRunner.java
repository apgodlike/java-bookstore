package com.book.api.testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/main/resources/features",
    glue = {"com.book.api.steps", "com.book.hooks"},
    plugin = { "pretty",
    		 "com.book.hooks.StepLogger",
            "json:target/cucumber.json"},
    tags = "@regression"
)
public class TestRunner {

}
