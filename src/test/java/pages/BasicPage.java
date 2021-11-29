package pages;

import driver.Driver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.L4JLogging;

import java.util.ArrayList;

public class BasicPage {
    public WebDriver driver = Driver.getWebDriver();
    public Logger LOGGER = Logger.getLogger(L4JLogging.class.getName());

    public void switchToNewTab(int tabNumber) {
        LOGGER.trace("switched to tab " + tabNumber);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        try {
            driver.switchTo().window(tabs.get(tabNumber - 1));
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public String getHeaderValue() {
        LOGGER.trace("got Header value: " + driver.getTitle());
        try {
            return driver.getTitle();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return null;
    }

    public String getURLValue() {
        LOGGER.trace("got URL value: " + driver.getCurrentUrl());
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return null;
    }

    public void setValueToTextFieldFoundById( String value, String id){
        LOGGER.trace("typed "+ value +" into element with id="+ id);
        try {
            WebElement inputField = driver.findElement(By.id(id));
            inputField.sendKeys(value);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
