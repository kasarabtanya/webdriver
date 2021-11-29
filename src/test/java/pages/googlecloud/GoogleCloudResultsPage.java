package pages.googlecloud;

import org.openqa.selenium.By;
import pages.BasicPage;

public class GoogleCloudResultsPage extends BasicPage {

    public void openLink(String input) throws InterruptedException {
        String xpath = String.format("//b[text()='%s']/parent::a", input); // 2 results by xpath
        LOGGER.info("Opening link with xpath =" + xpath);
        driver.findElement(By.xpath(xpath)).click();
        Thread.sleep(1000);

    }
}
