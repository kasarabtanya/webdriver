package pages.googlecloud;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasicPage;


import java.time.Duration;

import static utils.StringFormatterUtil.composeXpath;

public class GoogleCloudPricingCalculatorPage extends BasicPage {

    private static final String INSTANCES_NUMBER_ID = "input_75";
    private static final String ADD_GPU_CHECKBOX_XPATH = "//md-checkbox[@aria-label='Add GPUs']";
    private final String TAB_ID = "//md-tab-item//div[text()='%s']";
    private final String DROPDOWN_XPATH = "//md-select[contains(@aria-label, '%s')]//span[@class='md-select-icon']";
    private final String VALUE_XPATH = "//div[contains(text(), '%s')]/parent::md-option";
    private final String BUTTON_XPATH = "//button[@aria-label='%s']";
    private final String GPU_XPATH = "//md-select[contains(@aria-label, '%s')]";
    private final String GPU_TYPE_XPATH = "//div[contains(text(), '%s')]/parent::md-option";
    private final String GPU_NUMBER_XPATH = "//*[@id='select_container_454']/descendant::div[contains(text(), '%s')]/parent::md-option";
    private final String MACHINE_TYPE_XPATH = "//*[@id='select_container_93']//md-option[@value='%s']";
    private final String REGION_XPATH = "//*[@id='select_container_109']/descendant::div[contains(text(), '%s')]/parent::md-option";
    private final String COMMITTED_USAGE_XPATH = "//div[@id='select_container_143']/descendant::div[contains(text(), '%s')]/parent::md-option";


    public void switchToMyFrame() {
        driver.switchTo().frame(0);
        driver.switchTo().frame("myFrame");
    }

    public void setTab(String tabValue) {
        if (getURLValue().contains(tabValue)) {
            return;
        } else {
            driver.findElement(By.xpath(composeXpath(TAB_ID, tabValue))).click();
        }
    }

    public void setInstancesNumber(String value) {
        LOGGER.info("Setting value to field with ID =" + INSTANCES_NUMBER_ID);
        driver.findElement(By.id(INSTANCES_NUMBER_ID)).sendKeys(value);
    }

    public void setDropdownValue(String field, String value) {
        openDropdown(DROPDOWN_XPATH, field);
        LOGGER.info("Clicking element with xpath =" + composeXpath(VALUE_XPATH, value));
        waitForElementAndClick(composeXpath(VALUE_XPATH, value));
    }

    public void setMachineClassValue(String field, String value) {
        openDropdown(DROPDOWN_XPATH, field);
        LOGGER.info("Clicking element with xpath =" + composeXpath(MACHINE_TYPE_XPATH, value));
        waitForElementAndClick(composeXpath(MACHINE_TYPE_XPATH, value));
    }

    public void setRegionValue(String field, String value) {
        openDropdown(DROPDOWN_XPATH, field);
        LOGGER.info("Clicking element with xpath =" + composeXpath(REGION_XPATH, value));
        waitForElementAndClick(composeXpath(REGION_XPATH, value));
    }

    public void setCommittedUsageValue(String field, String value) {
        LOGGER.info("Opening dropdown xpath =" + field);
        openDropdown(DROPDOWN_XPATH, field);
        String xpath = composeXpath(COMMITTED_USAGE_XPATH, "None");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        try {
            LOGGER.info("Clicking element with xpath =" + xpath);
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions
                            .presenceOfElementLocated(
                                    By.xpath(xpath))).click();
        } catch (Exception e) {
            LOGGER.info(String.format("Element with xpath %s was not selected", xpath));
        }
        Driver.setTimeouts();
    }

    public void setGpuTypeValue(String field, String value) {
        openDropdown(GPU_XPATH, field);
        LOGGER.info("Clicking element with xpath =" + composeXpath(GPU_TYPE_XPATH, value));
        waitForElementAndClick(composeXpath(GPU_TYPE_XPATH, value));
    }

    public void setGpuNumberValue(String field, int value) {
        openDropdown(GPU_XPATH, field);
        String xpath = String.format(GPU_NUMBER_XPATH, value);
        LOGGER.info("Clicking element with xpath =" + xpath);
        waitForElementAndClick(xpath);
    }

    public void checkGPUCheckbox() {
        LOGGER.info("Checking checkbox with xpath =" + ADD_GPU_CHECKBOX_XPATH);
        driver.findElement(By.xpath(ADD_GPU_CHECKBOX_XPATH)).click();
    }

    public void pressButton(String value) {
        LOGGER.info("Clicking element with xpath =" + composeXpath(BUTTON_XPATH, value));
        waitForElementAndClick(composeXpath(BUTTON_XPATH, value));
    }

}