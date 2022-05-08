package FW.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import static FW.utilities.Generic.convertDirectory;

public class ConfigData {

    private static Logger logger = Logger.getLogger(Generic.class.getSimpleName());
    public static String keepBrowser = "Disable";
    public static String userName;
    public static String userPassword;


    private static String sRunMode = "local";

    private static void setRunMode(String runMode) {
        sRunMode = runMode;
        logger.info("*** Run Mode: " + sRunMode);
    }

    public static String getRunMode() {
        if (System.getProperty("runMode") != null) {
            setRunMode(System.getProperty("runMode"));
        }
        return sRunMode;
    }

    public static String getTestSuites() {
        logger.info("Get test suites and ignore test suite from TestBDD.properties");
        String suites = Generic.getConfigValue(convertDirectory(Generic.PROPERTIES_FILE), "TEST_CASES_ID_OR_SUITES");
        logger.info("Get test suites: " + suites + " from TestBDD.properties");
        return suites;
    }

    public static String getPriorities() {
        logger.info("Get test priorities and ignore test priorities from TestBDD.properties");
        String priorities = Generic.getConfigValue(convertDirectory(Generic.PROPERTIES_FILE), "TEST_PRIORITIES");
        logger.info("Get test priorities: " + priorities + " from TestBDD.properties");
        return priorities;
    }

//    public static void setDefaultBrowser() {
//        sBrowser = System.getProperty("sBrowser");
//        if (sBrowser == null) {
//            sBrowser = Generic.getConfigValue(convertDirectory(Generic.PROPERTIES_FILE), "TEST_BROWSER");
//            if (sBrowser == null) {
//                sBrowser = "chrome";
//            }
//        }
//        logger.info("*** We will start test with browser: " + sBrowser);
//    }

}
