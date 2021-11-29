package pages.googlecloud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pages.BasicPage;

public class GoogleCloudPricingCalculatorPage extends BasicPage {

    private static final String COMPUTE_ENGINE_ID = "//*[@id=\"mainForm\"]/md-tabs/md-tabs-wrapper/md-tabs-canvas/md-pagination-wrapper/md-tab-item[1]/div[1]/div/div[2]";
    private static final String INSTANCES_NUMBER_ID = "input_75";
    private static final String DROPDOWN_XPATH = "//md-select[contains(@aria-label, '%s')]//span[@class='md-select-icon']";
    private static final String VALUE_XPATH = "//div[contains(text(), '%s')]/parent::md-option";
    private static final String ESTIMATE_BTN_XPATH = "//button[@aria-label='Add to Estimate']";
    private static final String URL_CALCULATOR = "https://cloud.google.com/products/calculator?hl=en#tab=compute-engine";
    private static final String BUTTON_XPATH = "//button[@aria-label='%s']";
    private static final String ACTUAL_RESULT_XPATH = "//*[@id='compute']//b[contains(text(), 'Total Estimated Cost')]";

    public void openDropdown(String field) {
        String xpath = String.format(DROPDOWN_XPATH, field);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void getPage() {
        driver.manage().window().maximize();
        driver.get(URL_CALCULATOR);
        LOGGER.trace("open URL: " + URL_CALCULATOR);
    }

    public void switchToMyFrame() {
        driver.switchTo().frame(0);
        driver.switchTo().frame("myFrame");
    }

    public void setInstancesNumber(String value) {
        LOGGER.info("Setting value to field with ID =" + INSTANCES_NUMBER_ID);
        WebElement element = driver.findElement(By.id(INSTANCES_NUMBER_ID));
        element.sendKeys(value);
    }

    public void setDropdownValue(String value) {
        String xpath = String.format(VALUE_XPATH, value);
        LOGGER.info("Setting value to field with ID =" + xpath);

        driver.findElement(By.xpath(xpath)).click();

//        Actions perform = new Actions(driver);
//        WebElement element = driver.findElement(By.xpath(xpath));
//       element.click();
//        perform.moveToElement(driver.findElement(By.xpath(xpath)))
//                .pause(500)
//                .click()
//                .pause(500)
//                .build()
//                .perform();
    }

    public void pressButton(String text) {
        String xpath = String.format(BUTTON_XPATH, text);
        driver.findElement(By.xpath(xpath)).click();
    }

    public String getResult() {
        return driver.findElement(By.xpath(ACTUAL_RESULT_XPATH)).getText();
    }


}