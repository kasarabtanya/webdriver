package pages.googlecloud;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.BasicPage;

public class GoogleCloudStartPage extends BasicPage {

    private static final String URL_GOOGLE_CLOUD = "https://cloud.google.com/";
    private static final String SEARCH_BUTTON_XPATH = "//input[@name='q']";

    public void getPage() {
        driver.manage().window().maximize();
        driver.get(URL_GOOGLE_CLOUD);
        LOGGER.trace("open URL: " + URL_GOOGLE_CLOUD);
    }

    public void fillInSearchField(String value)  {
        WebElement searchField = driver.findElement(By.xpath(SEARCH_BUTTON_XPATH));
        searchField.click();
        searchField.sendKeys(value);
        searchField.sendKeys(Keys.ENTER);
    }

}

