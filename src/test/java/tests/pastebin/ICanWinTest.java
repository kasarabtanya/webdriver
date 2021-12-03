/*При выполнении задания необходимо использовать возможности Selenium WebDriver,
юнит-тест фреймворка и концепцию Page Object. Автоматизировать следующий сценарий:
Открыть https://pastebin.com или аналогичный сервис в любом браузере
Создать New Paste со следующими деталями:
* Код: "Hello from WebDriver"
* Paste Expiration: "10 Minutes"
* Paste Name / Title: "helloweb"
*/

package tests.pastebin;

import org.testng.annotations.Test;
import pages.pastebin.PastebinStartPage;
import tests.BasicTestngTests;

public class ICanWinTest extends BasicTestngTests {
    PastebinStartPage startPage = new PastebinStartPage();
    String code = "Hello from WebDriver";
    String title = "helloweb";

    @Test (description = "just create a new paste")
    public void createPasteTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
        startPage.getPage();
        startPage.setValueToTextFieldFoundById(code, startPage.getElementId("text"));
        startPage.set10MinutesExpiration();
        startPage.setValueToTextFieldFoundById(title, startPage.getElementId("name"));
        startPage.createPaste();
    }
}
