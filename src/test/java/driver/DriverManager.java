package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    public static WebDriver getDriver(Config config) {
        switch (config != null ? config : Config.CHROME) {
            case FIREFOX -> {
                return getFFDriver();
            }
            case REMOTE -> {
                return getRemoteDriver();
            }
            default -> {
                return getChromeDriver();
            }
        }
    }

    private static WebDriver getFFDriver() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.private.browsing.autostart", true);
        return null;   // do add driver
    }

    private static WebDriver getChromeDriver() {
        ChromeDriver chromeDriver = new ChromeDriver();
        ChromeOptions caps = new ChromeOptions();
 //       caps.addArguments("incognito");
        caps.addArguments("start-maximized");
        return chromeDriver;
    }

    private static WebDriver getRemoteDriver() {
        ChromeOptions caps = new ChromeOptions();
        caps.addArguments("incognito");
        caps.addArguments("start-maximized");
        try {
            return new RemoteWebDriver(new URL("http://localhost:9515"), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


}


