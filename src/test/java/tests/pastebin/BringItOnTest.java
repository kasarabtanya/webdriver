/*При выполнении задания необходимо использовать возможности Selenium WebDriver, юнит-тест фреймворка и концепцию Page Object. Автоматизировать следующий сценарий:
Открыть https://pastebin.com  или аналогичный сервис в любом браузере
Создать New Paste со следующими деталями:
* Код:
git config --global user.name  "New Sheriff in Town"
git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
git push origin master --force
* Syntax Highlighting: "Bash"
* Paste Expiration: "10 Minutes"
* Paste Name / Title: "how to gain dominance among developers"
3. Сохранить paste и проверить следующее:
* Заголовок страницы браузера соответствует Paste Name / Title
* Синтаксис подвечен для bash
* Проверить что код соответствует введенному в пункте 2
*/

package tests.pastebin;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.pastebin.PastebinResultPage;
import pages.pastebin.PastebinStartPage;
import tests.BasicTestngTests;

import java.util.List;


public class BringItOnTest extends BasicTestngTests {
    PastebinStartPage startPage = new PastebinStartPage();
    PastebinResultPage resultPage = new PastebinResultPage();

    String code = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force\n";
    String title = "how to gain dominance among developers";
    String expectedResult = "rgba(194, 12, 185, 1)";
    String highlightedElement = "//div[@class='highlighted-code']//span[contains(text(),'git reset')]";

    @Test
    public void verifyPastePageTitle() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
        startPage.getPage();
        startPage.setValueToTextFieldFoundById(code, startPage.getElementId("text"));
        startPage.set10MinutesExpiration();
        startPage.setSyntaxHighlightBash();
        startPage.setValueToTextFieldFoundById(title, startPage.getElementId("name"));
        startPage.createPaste();
        Assert.assertTrue(resultPage.getHeaderValue().contains(title));
    }

    @Test
    public void verifySyntaxIsHighlighted() throws InterruptedException {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
        startPage.getPage();
        Thread.sleep(5000); // used to close ads popups
        startPage.setValueToTextFieldFoundById(code, startPage.getElementId("text"));
        startPage.set10MinutesExpiration();
        startPage.setSyntaxHighlightBash();
        startPage.setValueToTextFieldFoundById(title, startPage.getElementId("name"));
        startPage.createPaste();
        String actualResult = "";
        actualResult = resultPage.getElementColor(highlightedElement);
        LOGGER.debug(String.format("actual result is %s; expected result is %s", actualResult, expectedResult));
        Assert.assertEquals(actualResult, expectedResult, String.format("actual result is %s; expected result is %s", actualResult, expectedResult));
    }

    @Test
    public void verifyCodeText() throws InterruptedException {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
        startPage.getPage();
        Thread.sleep(5000); // used to close ads popups
        startPage.setValueToTextFieldFoundById(code, startPage.getElementId("text"));
        startPage.set10MinutesExpiration();
        startPage.setSyntaxHighlightBash();
        startPage.setValueToTextFieldFoundById(title, startPage.getElementId("name"));
        startPage.createPaste();
        List<String> actualResult = resultPage.getCodeElements();
        for (String ar : actualResult) {
            Assert.assertTrue(code.contains(ar));
        }
    }

}
