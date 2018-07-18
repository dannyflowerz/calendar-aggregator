package io.github.dannyflowerz.calendaraggregator.ft;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/test-reports/html"},
        features = "src/test/resources/scenarios",
        tags = "not @performance",
        glue = "io.github.dannyflowerz.calendaraggregator.ft"
)
public class CucumberTest { }
