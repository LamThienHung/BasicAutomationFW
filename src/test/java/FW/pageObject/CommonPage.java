package FW.pageObject;

import FW.base.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class CommonPage extends AbstractPage implements ICommonPage {

    private WebDriver driver;

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    public CommonPage(WebDriver webDriver) {
        super();
        driver = webDriver;
        initElement();
    }

    public void initElement() {
        //logger.info("Init elements of class: " + this.getClass().getName());
        PageFactory.initElements(getDriver(), this);
    }


    //private String customBPXpath = "//button[@data-view-id='main_search_form_button']";

    private String cartXpath = "//span[@class='cart-text']";

    public void clickOnSearchButton() {
        clickElement(getElementByXpath(cartXpath), "Search Button");
    }

    public void openDefaultSite() {
        //logger.info(" ===== Get the current URL ======== ");
        getUrl("https://tiki.vn/");
    }

    public void waitTenSecons() throws InterruptedException {
        Thread.sleep(1000000);
    }

}

