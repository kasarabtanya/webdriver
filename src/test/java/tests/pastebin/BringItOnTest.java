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

import objects.pastebin.Paste;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.pastebin.PastebinResultPage;
import pages.pastebin.PastebinStartPage;
import tests.BasicTestngTests;
import utils.parsers.JacksonParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BringItOnTest extends BasicTestngTests {
    PastebinStartPage startPage = new PastebinStartPage();
    PastebinResultPage resultPage = new PastebinResultPage();

    String root = "src/test/resources/pastes/paste2.json";
    File file = new File(root);
    Paste paste;

    {
        try {
            paste = JacksonParser.getPaste(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test(description = "verify URL of a page with created paste")
    public void verifyPastePageTitle() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
        startPage.getPage();
        startPage.setValueToTextFieldFoundById(paste.getCode(), startPage.getElementId("text"));
        startPage.set10MinutesExpiration();
        startPage.setSyntaxHighlightBash();
        startPage.setValueToTextFieldFoundById(paste.getTitle(), startPage.getElementId("name"));
        startPage.createPaste();
        Assert.assertTrue(resultPage.getHeaderValue().contains(paste.getTitle()),
                String.format("Actual URL is '%s' instead of '%s'", resultPage.getHeaderValue(), paste.getTitle()));
    }

    @Test(description = "verify that syntax of a paste is highlighted")
    public void verifySyntaxIsHighlighted() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
        startPage.getPage();
        startPage.setValueToTextFieldFoundById(paste.getCode(), startPage.getElementId("text"));
        startPage.set10MinutesExpiration();
        startPage.setSyntaxHighlightBash();
        startPage.setValueToTextFieldFoundById(paste.getTitle(), startPage.getElementId("name"));
        startPage.createPaste();
        String actualResult;
        actualResult = resultPage.getElementColor(paste.getHighlightedElement());
        LOGGER.debug(String.format("actual result is %s; expected result is %s", actualResult, paste.getExpectedResult()));
        Assert.assertEquals(actualResult, paste.getExpectedResult(), String.format("Actual result is %s; expected result is %s", actualResult, paste.getExpectedResult()));
    }

    @Test(description = "verify text of code in a paste")
    public void verifyCodeText() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
        startPage.getPage();
        startPage.setValueToTextFieldFoundById(paste.getCode(), startPage.getElementId("text"));
        startPage.set10MinutesExpiration();
        startPage.setSyntaxHighlightBash();
        startPage.setValueToTextFieldFoundById(paste.getTitle(), startPage.getElementId("name"));
        startPage.createPaste();
        List<String> actualResult = resultPage.getCodeElements();
        for (String ar : actualResult) {
            Assert.assertTrue(paste.getCode().contains(ar), String.format("Actual URL is '%s' instead of '%s'", actualResult, ar));
        }
    }

}
