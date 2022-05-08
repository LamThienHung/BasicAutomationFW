package FW.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class DriverFactory {
    //private static RemoteWebDriver driver;
    private static WebDriver driver;
    //private static AppiumDriver mobileDriver = null;
    private static List<WebDriver> driverList = new ArrayList<WebDriver>();

    public static void setCurrentDriver(WebDriver webDriver) {
        System.out.println("Dat Driver");
        driverList.add(webDriver);
        driver = webDriver;
    }

    public static WebDriver getCurrentDriver() {
        System.out.println("Lay Driver");
        return driver;
    }

    public static WebDriver switchBrowser(int index) {
        driver = (RemoteWebDriver) driverList.get(index);
        return driver;
    }

    public static int getDriversSize() {

        return driverList.size();
    }

//    public static AppiumDriver getMobileDriver() {
//        return mobileDriver;
//    }

//    public static void setMobileDriver(AppiumDriver mobileWebDriver) {
//        mobileDriver = mobileWebDriver;
//    }

    public static void closeAllOpeningBrowsersExceptTheFirst() throws Throwable {
        //AbstractLog.getLogger(DriverFactory.class.getSimpleName()).info("====== I close all opening browser windows except first browser ========= ");
        while (driverList.size() > 1) {
            driverList.get(1).quit();
            driverList.remove(1);
        }
        switchBrowser(0);
        Assert.assertEquals(driverList.size(), 1, "Other browser windows are not closed. Number of windows: " + driverList.size());
    }

    public static void releaseAllDrivers() {
        for (WebDriver dv : driverList) {
            driver = (WebDriver) dv;
            driver.close();
            driver.quit();
        }
        driverList.clear();
    }
}
