package com.qe.bdd.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features/"},
		tags = "@api or @ui",
		plugin = {"html:target/cucumber-html-report.html"},
		glue = {"com.qe.bdd"},
		monochrome=true,
		dryRun=false		
)
public class RunnerTest {
}
