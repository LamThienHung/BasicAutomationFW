package stepDefinitions;

import FW.utilities.DriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.*;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import static FW.utilities.ConfigData.keepBrowser;
import static FW.utilities.Generic.*;
import static FW.utilities.PageManager.getCommonPage;

public class AbstractStep {

    protected static Logger logger;

    @Before
    public void initializeDriver(Scenario scenario) throws Exception {
        if ((DriverFactory.getDriversSize() == 0)) {
            if (deviceTypeRun.equalsIgnoreCase("Mobile")) {
                //createNewDeviceDriver();
            } else {
                createNewSessionBrowser();
                getCommonPage().openDefaultSite();
            }
        } else {

        }
    }

    @After
    public void tearDownTest(Scenario scenario) {
        try {
            //logger.info("*** Scenario Status: " + scenario.getStatus());
            if (scenario.isFailed() || scenario.getStatus().equals("undefined")) {
                scenario.embed(((TakesScreenshot) DriverFactory.getCurrentDriver()).getScreenshotAs(OutputType.BYTES), "image/png");
//                if (DriverFactory.getMobileDriver() != null)
//                    scenario.embed(((TakesScreenshot) DriverFactory.getMobileDriver()).getScreenshotAs(OutputType.BYTES), "image/png");
                scenario.write("Scenario failed.");
                //logger.error(">>>>>>>>>>>>>> Scenario Status: FAILED <<<<<<<<<<<");
                //getExportFailTestCase(getFeatureName(scenario), scenario.getName());
            } else {
                scenario.write("Scenario Passed");
                //logger.info(">>>>>>>>>>>>>> Scenario Status: PASSED <<<<<<<<<<<");
            }
            if (!keepBrowser.equals("Enable")) {
                DriverFactory.releaseAllDrivers();
            }
            keepBrowser = "Disable";
            //logger.info("|||||||||| End scenario: " + scenario.getName() + ": " + scenario.getStatus() + " ||||||||||");
            //logger.info("|||||||||| Engagement name: " + getEngagementInfo().getName() + " ||||||||||");
        } catch (Exception e) {
            //logger.error("*** There are some errors when closing browser: \n" + e.getMessage() + " ***");
        } finally {
            //resetData();
        }
    }
}
