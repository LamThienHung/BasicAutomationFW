package FW.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface ICommonPage {
    void initElement();
    WebDriver getDriver();
}
