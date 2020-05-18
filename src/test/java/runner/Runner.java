package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
		 tags = {},
		 glue = {"hooks", "steps"},
		 plugin = {"pretty","json:target/json-cucumber-reports/cucumber.json",
				 	"junit:target/xml-junit/junit.xml"},
		features = {"src/test/resources/features"})
public class Runner {}
