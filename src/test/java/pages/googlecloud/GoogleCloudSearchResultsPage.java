package pages.googlecloud;

import org.openqa.selenium.By;
import pages.BasicPage;

import static utils.StringFormatterUtil.composeXpath;

public class GoogleCloudSearchResultsPage extends BasicPage {
    private final String LINK_XPATH = "//b[text()='%s']/parent::a";

    public void openLink(String input) {
        LOGGER.info("Opening link with xpath =" + composeXpath(LINK_XPATH, input));
        driver.findElement(By.xpath(composeXpath(LINK_XPATH, input))).click();
    }
}
