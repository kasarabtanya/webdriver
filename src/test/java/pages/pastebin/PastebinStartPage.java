package pages.pastebin;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.BasicPage;

public class PastebinStartPage extends BasicPage {
    private static final String URL_PASTEBIN = "https://pastebin.com";
    private static final String EXPIRATION_ARROW_XPATH = "//*[@id='postform-expiration']/following-sibling::span//span[contains(@class, 'arrow')]";
    private static final String HIGHLIGHTING_ARROW_XPATH = "//*[@id='postform-format']/following-sibling::span//span[contains(@class, 'arrow')]";
    private static final String CREATE_BUTTON_XPATH = "//button[@type='submit']";

    public void getPage() {
        driver.get(URL_PASTEBIN);
        LOGGER.trace("open URL: " + URL_PASTEBIN);
    }

    public String getElementId(String field) {
        return String.format("postform-%s", field);
    }

    public void set10MinutesExpiration() {
        LOGGER.info("Selecting value to dropdown Paste Expiration");
        Actions perform = new Actions(driver);  //  Select throws error in this field on Chrome; to be refactored
        WebElement element = driver.findElement(By.xpath(EXPIRATION_ARROW_XPATH));
        element.click();
        perform.moveToElement(element)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
    }

    public void setSyntaxHighlightBash() {
        LOGGER.info("Selecting value to dropdown Syntax Highlight");
        Actions perform = new Actions(driver);              // Select throws error in this field on Chrome; to be refactored
        WebElement element = driver.findElement(By.xpath(HIGHLIGHTING_ARROW_XPATH));
        element.click();
        perform.moveToElement(element)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
    }

    public void createPaste() {
        LOGGER.trace("clicked element with xpath = " + CREATE_BUTTON_XPATH);
        driver.findElement(By.xpath(CREATE_BUTTON_XPATH)).click();
    }


}
