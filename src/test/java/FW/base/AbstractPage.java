package FW.base;

import FW.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.*;

public abstract class AbstractPage {

    protected static Logger logger;

    private static final int SELENIUM_TIMEOUT_SECONDS = 5;

    public WebDriver getDriver() {
        return null;
    }

    public WebElement waitForClickableOfElement(By selector, String elementName) {
        WebDriverWait wait = new WebDriverWait(getDriver(), SELENIUM_TIMEOUT_SECONDS);
        return wait.until(ExpectedConditions.elementToBeClickable(selector));
    }

    public WebElement waitForClickableOfElement(By selector, String elementName, int waitTimeOut) {
        WebDriverWait wait = new WebDriverWait(getDriver(), waitTimeOut);
        return wait.until(ExpectedConditions.elementToBeClickable(selector));
    }

    public WebElement waitForClickableOfElement(WebElement element, String elementName) {
        WebDriverWait wait = new WebDriverWait(getDriver(), SELENIUM_TIMEOUT_SECONDS);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForClickableOfElement(WebElement element, String elementName, int waitTimeOut) {
        WebDriverWait wait = new WebDriverWait(getDriver(), waitTimeOut);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForVisibleElement(WebElement element, String elementName) {
        WebDriverWait wait = new WebDriverWait(getDriver(), SELENIUM_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementIsDisplayed(By selector, String elementName) {
        WebDriverWait wait = new WebDriverWait(getDriver(), SELENIUM_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public WebElement getElementByXpath(String xpath, String... arg) {
        WebElement webElement = null;
        if (arg.length > 0) {
            xpath = String.format ( xpath , arg );
        }
        try {
            webElement = getDriver ( ).findElement ( By.xpath ( xpath ) );
        } catch ( Exception ex ) {
        }
        return webElement;
    }

    public void getUrl(String url) {
        getDriver().get(url);
    }

    public void clickElement(WebElement element, String elementName) {
        waitForClickableOfElement(element, elementName).click();
    }

}
