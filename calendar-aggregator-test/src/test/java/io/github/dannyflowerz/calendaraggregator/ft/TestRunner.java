package io.github.dannyflowerz.calendaraggregator.ft;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/test-reports/html"},
        glue = "io/github/dannyflowerz/calendaraggregator/ft",
        features = "src/test/resources/scenarios",
        tags = {"@happy"}
)
public class TestRunner {
    //Run this
}