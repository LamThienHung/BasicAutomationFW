package runner;

import FW.utilities.Generic;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.BeforeClass;


@CucumberOptions(
        features = {"src/test/java/testScenario"}
        , format = {"json:target/cucumber-report.json"}
        , glue = "stepDefinitions"
)
public class testNG extends AbstractTestNGCucumberTests {
    @BeforeClass
    public static void beforeSuite() {
        Generic.sVersion = "60";
        Generic.sOS = "WIN10";
    }
}
