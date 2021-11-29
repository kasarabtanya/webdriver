package pages.pastebin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasicPage;

import java.util.ArrayList;
import java.util.List;

public class PastebinResultPage extends BasicPage {

    public static final String BASH_ELEMENTS_XPATH = "//ol[@class='bash']//span";

    public String getElementColor(String xpath) {
        LOGGER.debug("got color from element with xpath= " + xpath);
    //    driver.navigate().refresh();                                        // do we need it?
        WebElement codeLine = driver.findElement(By.xpath(xpath));
        return codeLine.getCssValue("color");
    }

    public List<String> getCodeElements() {
        List<WebElement> elementsList = new ArrayList<>();
        LOGGER.trace("found  web elements with xpath= " + BASH_ELEMENTS_XPATH);
        elementsList = driver.findElements(By.xpath(BASH_ELEMENTS_XPATH));
        LOGGER.debug("List size= " + elementsList.size());
        return elementsList.stream()
                .map(s -> s.getText())
                .toList();
    }
}
