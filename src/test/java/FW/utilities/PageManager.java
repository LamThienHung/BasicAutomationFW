package FW.utilities;

import FW.pageObject.CommonPage;
import FW.pageObject.ICommonPage;
import FW.pageObject.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PageManager {
    private static LoginPage loginPage;
    private static CommonPage commonPage;

    public static ICommonPage getInstance(ICommonPage iPage, String className) {
        try {
            if (iPage == null || !iPage.getDriver().equals(DriverFactory.getCurrentDriver())) {
                System.out.println("Da Vao");
                iPage = (ICommonPage) Class.forName(className).getConstructor(WebDriver.class).newInstance(DriverFactory.getCurrentDriver());
                //logger.info("Init class: " + iPage.getClass().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iPage;
    }

    public static LoginPage getLoginPage() {
        loginPage = (LoginPage) getInstance(loginPage, LoginPage.class.getName());
        return loginPage;
    }

    public static CommonPage getCommonPage() {
        commonPage = (CommonPage) getInstance(commonPage, CommonPage.class.getName());
        return commonPage;
    }
}
