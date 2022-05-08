package stepDefinitions;

import cucumber.api.java.en.*;

import static FW.utilities.PageManager.*;


public class CommonPageStepDefinition {

    @And("^I click on Search button on (.*) page$")
    public void iClickOnSearchButton(String pageName) throws Throwable {
        //logger.info("I click on Search button on "+ pageName + " page$");
        getCommonPage().clickOnSearchButton();
    }
    @Then("^I wait for ten seconds$")
    public void waitTenSeconds() throws InterruptedException {
        getCommonPage().waitTenSecons();
    }

}
