package pages.googlecloud;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasicPage;

import java.time.Duration;


public class GoogleCloudCalculatorResultsPage extends BasicPage {
    private static final String ACTUAL_RESULT_XPATH = "//md-content[@id='compute']//div[contains(text(),'%s')]";
    private static final String PRICE_ACTUAL_RESULT_XPATH = "//b[contains(text(), 'Total Estimated Cost')]";
    private static final String EMAIL_INPUT_XPATH = "//md-dialog//input[@type='email']";
    private static final String SEND_EMAIL_BUTTON_XPATH = "//button[@aria-label='Send Email']";


    public String getActualResult(String field) {
        String xpath = String.format(ACTUAL_RESULT_XPATH, field);
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public String getPriceResult() {
        return driver.findElement(By.xpath(PRICE_ACTUAL_RESULT_XPATH)).getText();
    }

    public void sendToEmail(String emailValue) {
        LOGGER.info("Setting value to field with xpath =" + EMAIL_INPUT_XPATH);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions
                        .presenceOfElementLocated(
                                By.xpath(EMAIL_INPUT_XPATH))).sendKeys(emailValue);
        Driver.setTimeouts();
        driver.findElement(By.xpath(SEND_EMAIL_BUTTON_XPATH)).click();
    }

}
