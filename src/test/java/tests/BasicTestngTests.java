package tests;

import driver.Config;
import driver.Driver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import org.apache.log4j.Logger;
import utils.L4JLogging;


public class BasicTestngTests {
    public Logger LOGGER = Logger.getLogger(L4JLogging.class.getName());

    @BeforeTest
    public void initDriver() {
        LOGGER.info("test execution started");
        Driver.setConfig(Config.CHROME);
    }

    @AfterTest
    public void closeDriver() {
        LOGGER.info("test execution finished");
        Driver.destroy();
    }
}
