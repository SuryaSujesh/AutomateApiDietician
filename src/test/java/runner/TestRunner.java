package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",
	 glue = {"stepDefinition"},
	 plugin= {
			 "json:target/TestReports/json/report.json",
			 "html:target/TestReports/HTML/index.html",
			 "junit:target/TestReports/junit/report.xml",				 
	         })

public class TestRunner {

}
