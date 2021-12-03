package pages.yopmail;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasicPage;

import java.time.Duration;
import java.util.List;

public class YopmailPage extends BasicPage {
    private static final String YOPMAIL_URL = "https://yopmail.com/";
    private static final String RANDOM_EMAIL_LINK_XPATH = "//a[contains(@title, 'random')]";
    private static final String EMAIL_VALUE_ID = "egen";
    private static final String CHECK_INBOX_BUTTON_XPATH = "//span[contains(text(), 'Check Inbox')]/parent::button";
    private static final String MAIL_LINK_XPATH = "//button[@class='lm']";
    private static final String ESTIMATED_COST_FROM_EMAIL_XPATH = "//h3";
    private static final String REFRESH_ID = "refresh";


    public void getPage() {
        driver.get(YOPMAIL_URL);
        LOGGER.trace("open URL: " + YOPMAIL_URL);
    }

    public void getRandomEmail() {
        LOGGER.info("Clicking element with xpath =" + RANDOM_EMAIL_LINK_XPATH);
        driver.findElement(By.xpath(RANDOM_EMAIL_LINK_XPATH)).click();
    }

    public String copyEmail() {
        LOGGER.info("Copying text of element with id =" + EMAIL_VALUE_ID);
        return driver.findElement(By.id(EMAIL_VALUE_ID)).getText();
    }

    public void openMailbox() {
        LOGGER.info("Clicking element with xpath =" + CHECK_INBOX_BUTTON_XPATH);
        driver.findElement(By.xpath(CHECK_INBOX_BUTTON_XPATH)).click();
    }

    public void openEmail() {
        LOGGER.info("Clicking element with xpath =" + REFRESH_ID);
        driver.findElement(By.id(REFRESH_ID)).click();
        LOGGER.info("Clicking element with xpath =" + MAIL_LINK_XPATH);
    }

    public String getCost() {
        LOGGER.info("Switching to iframe ifmail");
        driver.switchTo().frame("ifmail");
        LOGGER.info("Getting cost from email from element with xpath =" + ESTIMATED_COST_FROM_EMAIL_XPATH);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        List<WebElement> elementsList = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(
                                By.xpath(ESTIMATED_COST_FROM_EMAIL_XPATH)));
        Driver.setTimeouts();
        return elementsList.get(1).getText();
    }

}
