/*При выполнении задания необходимо использовать возможности Selenium WebDriver, юнит-тест фреймворка и концепцию Page Object. Автоматизировать следующий сценарий:
1. Открыть https://cloud.google.com/
2. Нажав кнопку поиска по порталу вверху страницы, ввести в поле поиска"Google Cloud Platform Pricing Calculator"
3. Запустить поиск, нажав кнопку поиска.
4. В результатах поиска кликнуть "Google Cloud Platform Pricing Calculator" и перейти на страницу калькулятора.
5. Активировать раздел COMPUTE ENGINE вверху страницы
6. Заполнить форму следующими данными:
    * Number of instances: 4
    * What are these instances for?: оставить пустым
    * Operating System / Software: Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS
    * VM Class: Regular
    * Instance type: n1-standard-8    (vCPUs: 8, RAM: 30 GB)
    * Выбрать Add GPUs
        * Number of GPUs: 1
        * GPU type: NVIDIA Tesla V100
    * Local SSD: 2x375 Gb
    * Datacenter location: Frankfurt (europe-west3)
    * Commited usage: 1 Year
7. Нажать Add to Estimate
8. Выбрать пункт EMAIL ESTIMATE
9. В новой вкладке открыть https://yopmail.com/ или аналогичный сервис для генерации временных email'ов
10. Скопировать почтовый адрес сгенерированный в yopmail.com
11. Вернуться в калькулятор, в поле Email ввести адрес из предыдущего пункта
12. Нажать SEND EMAIL
13. Дождаться письма с рассчетом стоимости и проверить что Total Estimated Monthly Cost в письме совпадает с тем, что отображается в калькуляторе
*/

package tests.googlecloud;

import objects.googlecloud.Calculator;
import objects.googlecloud.Search;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.googlecloud.GoogleCloudCalculatorResultsPage;
import pages.googlecloud.GoogleCloudPricingCalculatorPage;
import pages.googlecloud.GoogleCloudSearchResultsPage;
import pages.googlecloud.GoogleCloudStartPage;
import pages.yopmail.YopmailPage;
import tests.BasicTestngTests;
import utils.parsers.JacksonParser;

import java.io.File;
import java.io.IOException;

public class HardCoreTest extends BasicTestngTests {

    GoogleCloudStartPage startPage = new GoogleCloudStartPage();
    GoogleCloudSearchResultsPage searchResultsPage = new GoogleCloudSearchResultsPage();
    GoogleCloudPricingCalculatorPage calculatorPage = new GoogleCloudPricingCalculatorPage();
    GoogleCloudCalculatorResultsPage calculatorResultsPage = new GoogleCloudCalculatorResultsPage();
    YopmailPage yopmailPage = new YopmailPage();

    String root = "src/test/resources/googlecloud/googlecloud.json";
    File file = new File(root);
    Search search = new Search();
    Calculator calc;

    {
        try {
            calc = JacksonParser.getCalculator(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Verify email price")
    public void verifyEmailPriceTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
        yopmailPage.getPage();
        yopmailPage.getRandomEmail();
        String emailValue = yopmailPage.copyEmail();
        calculatorPage.openNewTab();
        calculatorPage.switchToNewTab(2);
        startPage.getPage();
        startPage.fillInSearchField(search.getSearchInput());
        searchResultsPage.openLink(search.getSearchInput());
        calculatorPage.switchToMyFrame();
        calculatorPage.setTab(calc.getTab());
        calculatorPage.setInstancesNumber(calc.getInstancesNumber());
        calculatorPage.setDropdownValue(calc.getOsField(), calc.getOsValue());
        calculatorPage.setMachineClassValue(calc.getMachineClassField(), calc.getMachineClassValue());
        calculatorPage.setDropdownValue(calc.getSeriesField(), calc.getSeriesValue());
        calculatorPage.setDropdownValue(calc.getMachineTypeField(), calc.getMachineTypeValue());
        calculatorPage.checkGPUCheckbox();
        calculatorPage.setGpuTypeValue(calc.getGpuTypeField(), calc.getGpuTypeValue());
        calculatorPage.setGpuNumberValue(calc.getGpuNumberField(), calc.getGpuNumberValue());
        calculatorPage.setDropdownValue(calc.getSsdField(), calc.getSsdValue());
        calculatorPage.setRegionValue(calc.getDatacenterField(), calc.getDatacenterValue());
        calculatorPage.pressButton(calc.getEstimate());
        calculatorPage.pressButton(calc.getEmailButtonValue());
        calculatorResultsPage.sendToEmail(emailValue);
        String expectedResult = "";
        expectedResult = calculatorResultsPage.getPriceResult();
        calculatorResultsPage.switchToNewTab(1);
        yopmailPage.openMailbox();
        yopmailPage.openEmail();
        String actualResult = "";
        actualResult = yopmailPage.getCost();
        LOGGER.debug(actualResult);

        Assert.assertTrue(expectedResult.contains(actualResult),
                String.format("Actual result is %s instead of %s", actualResult, expectedResult));

    }
}
