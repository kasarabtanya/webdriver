package driver;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Driver {
    private static WebDriver driver;
    private static Config config;

    private Driver() {
    }

    public static void setConfig(Config config) {
        Driver.config = config;
    }

    public static WebDriver getWebDriver() {
        if (driver == null) {
            driver = DriverManager.getDriver(config);
            setTimeouts();
        }
        return driver;
    }

    public static void destroy() {
        driver.quit();
        Driver.driver = null;
    }

    public static void setTimeouts() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

}
