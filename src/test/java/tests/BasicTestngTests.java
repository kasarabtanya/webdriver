package tests;

import driver.Config;
import driver.Driver;
import org.testng.annotations.AfterTest;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import utils.L4JLogging;


public class BasicTestngTests {
    public Logger LOGGER = Logger.getLogger(L4JLogging.class.getName());

    @BeforeTest
    public void initDriver() {
        LOGGER.info("test suite execution started");
        Driver.setConfig(Config.CHROME);
    }

    @AfterTest
    public void closeDriver() {
        LOGGER.info("test suite execution finished");
        Driver.destroy();
    }

}
