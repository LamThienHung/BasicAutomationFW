package FW.base;

import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.robotframework.javalib.annotation.*;
import org.testng.Assert;
import org.apache.logging.log4j.*;

import java.util.concurrent.TimeUnit;

public class AbstractPage {

    protected Logger logger;

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    private final int SELENIUM_TIMEOUT_SECONDS = 15;

    // Wait Element

    public WebElement waitForClickableOfElement(By selector, String elementName) {
        logger.info("+++ Wait For Clickable Of Element: " + elementName);
        WebDriverWait wait = new WebDriverWait(driver, SELENIUM_TIMEOUT_SECONDS);
        return wait.until(ExpectedConditions.elementToBeClickable(selector));
    }

    public WebElement waitForVisibilityOfElement(By selector, String elementName) {
        logger.info("+++ Wait For Clickable Of Element: " + elementName);
        WebDriverWait wait = new WebDriverWait(driver, SELENIUM_TIMEOUT_SECONDS);
        return wait.until(ExpectedConditions.elementToBeClickable(selector));
    }

    public boolean waitUntilElementIsDisplayed(By selector, String elementName) {
        logger.info("Wait for element displayed: " + elementName);
        logger.info("+++ Wait For Clickable Of Element: " + elementName);
        WebDriverWait wait = new WebDriverWait(driver, SELENIUM_TIMEOUT_SECONDS);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(selector));
    }

    //Action on element


    public void clickElement(By slector, String elementName) {
        logger.info("+++ Click on Element: " + elementName);
        waitForVisibilityOfElement(slector, elementName);
        waitForClickableOfElement(slector, elementName).click();
    }

    private static By entityNameXpath = By.xpath("//h3[@class='company-item-name']");




    public void verifyInviteClientButtonNotDisplayed() {

    }


}
