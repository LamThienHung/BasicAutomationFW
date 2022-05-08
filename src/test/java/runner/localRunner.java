package runner;

import FW.utilities.Generic;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;


//ru.yandex.qatools.allure.cucumberjvm.AllureReporter
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/testScenario"}
        , format = {"json:target/cucumber-report.json"}
        , glue = "stepDefinitions"
)

public class localRunner {
    @BeforeClass
    public static void beforeSuite() {
        Generic.sVersion = "60";
        Generic.sOS = "WIN10";
    }
}
