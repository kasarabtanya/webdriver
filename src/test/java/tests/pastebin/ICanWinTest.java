/*При выполнении задания необходимо использовать возможности Selenium WebDriver,
юнит-тест фреймворка и концепцию Page Object. Автоматизировать следующий сценарий:
Открыть https://pastebin.com или аналогичный сервис в любом браузере
Создать New Paste со следующими деталями:
* Код: "Hello from WebDriver"
* Paste Expiration: "10 Minutes"
* Paste Name / Title: "helloweb"
*/

package tests.pastebin;

import objects.pastebin.Paste;
import org.testng.annotations.Test;
import pages.pastebin.PastebinStartPage;
import tests.BasicTestngTests;
import utils.parsers.JacksonParser;

import java.io.File;
import java.io.IOException;

public class ICanWinTest extends BasicTestngTests {
    PastebinStartPage startPage = new PastebinStartPage();
    String root = "src/test/resources/pastes/paste1.json";
    File file = new File(root);
    Paste paste;

    {
        try {
            paste = JacksonParser.getPaste(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "just create a new paste")
    public void createPasteTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
        startPage.getPage();
        startPage.setValueToTextFieldFoundById(paste.getCode(), startPage.getElementId("text"));
        startPage.set10MinutesExpiration();
        startPage.setValueToTextFieldFoundById(paste.getTitle(), startPage.getElementId("name"));
        startPage.createPaste();
    }
}
