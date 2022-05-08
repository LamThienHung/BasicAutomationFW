package FW.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import static FW.utilities.ConfigData.getRunMode;

public class Generic {

    private static Logger logger = Logger.getLogger(Generic.class.getSimpleName());
    public static String sBrowser = "chrome";
    public static String sVersion = null;
    public static String sOS = null;
    public static String sDirPath = System.getProperty("user.dir");
    public static final String FOLDER_DOWNLOAD = sDirPath + "\\src\\test\\resources\\testData\\download\\";
    public static String deviceTypeRun = "";
    public static String deviceType;
    private static String OS = System.getProperty("os.name");
    public final static String PROPERTIES_FILE = sDirPath + "/TestBDD.properties";

    public static void createNewSessionBrowser() throws MalformedURLException {
        WebDriver driver = null;
        logger.info("*** We will run with Browser Driver ***");
        if (getRunMode().equals("local")) {
            if (Generic.sBrowser.equalsIgnoreCase("chrome")) {
                startChromeDriver();
            } else if (Generic.sBrowser.equalsIgnoreCase("firefox")) {
                startFireFoxDriver();
            } else if (Generic.sBrowser.equalsIgnoreCase("internetexplorer")) {
                startInternetExplorerDriver();
            } else if (Generic.sBrowser.equalsIgnoreCase("edge")) {
                startEdgeDriver();
            } else if (Generic.sBrowser.equalsIgnoreCase("safari")) {
                DriverFactory.setCurrentDriver(new SafariDriver());
            } else if (Generic.sBrowser.equalsIgnoreCase("chromium")) {
                startChromiumDriver();
            }
            // Configure for Selenium Grid mode, to run on Jenkins
        } else if (getRunMode().equalsIgnoreCase("SeleniumGrid")) {
//            logger.info("*** Browser: " + Generic.sBrowser);
//            logger.info("*** OS: " + Generic.sOS);
//            logger.info("*** Version: " + Generic.sVersion);
//            setSeleniumGridHub();
//            if (Generic.sBrowser.equalsIgnoreCase("chrome")) {
//                ChromeOptions options = Generic.setOptionsForChrome();
//                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//                capabilities.setBrowserName("chrome");
//                // Enable VNC for Selenoid
//                capabilities.setCapability("enableVNC", true);
//                capabilities.setCapability("enableVideo", false);
//                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB), capabilities);
//            } else if (Generic.sBrowser.equalsIgnoreCase("firefox")) {
//                FirefoxOptions options = Generic.setOptionsForFirefox();
//                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB), options);
//            } else if (Generic.sBrowser.equalsIgnoreCase("internet explorer")) {
//                DesiredCapabilities capabilitiesIE;
//                capabilitiesIE = DesiredCapabilities.internetExplorer();
//                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB), capabilitiesIE);
//            } else if (Generic.sBrowser.equalsIgnoreCase("edge")) {
//                DesiredCapabilities capabilitiesEdge;
//                capabilitiesEdge = DesiredCapabilities.edge();
//                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB), capabilitiesEdge);
//            } else if (Generic.sBrowser.equalsIgnoreCase("safari")) {
//                DesiredCapabilities capabilitiesSafari;
//                capabilitiesSafari = DesiredCapabilities.safari();
//                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB), capabilitiesSafari);
//            }
//            driver.setFileDetector(new LocalFileDetector());
//            DriverFactory.setCurrentDriver(driver);
        }
        driver = DriverFactory.getCurrentDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //logger.info("*** Opened browser ***");
    }

    private static void startChromeDriver() {
        WebDriverManager.chromedriver().clearPreferences();
        WebDriverManager.chromedriver().version("91").setup();
        ChromeOptions options = setOptionsForChrome();
        if (isHeadless()) {
            options.addArguments("--no-sandbox");
            options.addArguments("--headless");
            options.addArguments("disable-gpu");
            options.addArguments("window-size=1920,1080");
        }
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        DriverFactory.setCurrentDriver(new ChromeDriver(cap));
    }

    private static ChromeOptions setOptionsForChrome() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", FOLDER_DOWNLOAD);
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--ignore-certificate-errors");
        return options;
    }

    public static boolean isHeadless() {
        return System.getProperty("headless") != null;
    }

    private static void startFireFoxDriver() {
    }

    private static void startInternetExplorerDriver() {
    }

    private static void startEdgeDriver() {
    }

    private static void startChromiumDriver() {
    }

    public static void createNewDeviceDriver() throws MalformedURLException {
        logger.info("*** We will run with Mobile Driver ***");
        switch (deviceType) {
            case "iOS":
                startIOSDevice();
                break;
            case "Android":
                startAndroidDevice();
                break;
            default:
                System.out.printf("Start iOS Mobile as Default");
                startIOSDevice();
                break;
        }
        //DriverFactory.getMobileDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //DriverFactory.setCurrentDriver(DriverFactory.getMobileDriver());
    }

    private static void startIOSDevice() {
    }
    private static void startAndroidDevice() {
    }

    public static String convertDirectory(String directory) {
        if (OS.contains("Windows")) {
            logger.info("*** We are working on Windows environment: " + OS);
            directory = directory.replace("/", "\\");
        } else {
            logger.info("*** We are working on Linux / Mac environment: " + OS);
        }
        return directory;
    }

    public static String getConfigValue(String sFile, String sKey) {
        logger.info("*** Read Configuration file ***");
        Properties prop = new Properties();
        String sValue = null;
        try {
            InputStream input = new FileInputStream(sFile);
            prop.load(input);
            sValue = prop.getProperty(sKey);
            logger.info("*** Value from Properties file of Parameter: " + sKey + ": " + sValue);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.info("*** Can not find the properties file ***" + sValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sValue;
    }


}
