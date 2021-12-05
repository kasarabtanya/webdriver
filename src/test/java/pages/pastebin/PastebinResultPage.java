package pages.pastebin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasicPage;

import java.util.List;

import static utils.StringFormatterUtil.composeXpath;

public class PastebinResultPage extends BasicPage {

    private static final String BASH_ELEMENTS_XPATH = "//ol[@class='bash']//span";
    private final String ELEMENT_XPATH = "//div[@class='highlighted-code']//span[contains(text(),'%s')]";

    public String getElementColor(String value) {
        String xpath = composeXpath(ELEMENT_XPATH, value);
        LOGGER.debug("got color from element with xpath= " + xpath);
        WebElement codeLine = driver.findElement(By.xpath(xpath));
        return codeLine.getCssValue("color");
    }

    public List<String> getCodeElements() {
        List<WebElement> elementsList;
        LOGGER.trace("found  web elements with xpath= " + BASH_ELEMENTS_XPATH);
        elementsList = driver.findElements(By.xpath(BASH_ELEMENTS_XPATH));
        LOGGER.debug("List size= " + elementsList.size());
        return elementsList.stream()
                .map(s -> s.getText())
                .toList();
    }
}
