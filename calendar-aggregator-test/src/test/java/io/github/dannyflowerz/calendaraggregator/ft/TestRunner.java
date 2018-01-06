package io.github.dannyflowerz.calendaraggregator.ft;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/test-reports/html"},
        features = "src/test/resources/scenarios"
)
public class TestRunner { }
