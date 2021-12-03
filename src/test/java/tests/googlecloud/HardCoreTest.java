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

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.googlecloud.GoogleCloudCalculatorResultsPage;
import pages.googlecloud.GoogleCloudPricingCalculatorPage;
import pages.googlecloud.GoogleCloudSearchResultsPage;
import pages.googlecloud.GoogleCloudStartPage;
import pages.yopmail.YopmailPage;
import tests.BasicTestngTests;

public class HardCoreTest extends BasicTestngTests {

    GoogleCloudStartPage startPage = new GoogleCloudStartPage();
    GoogleCloudSearchResultsPage searchResultsPage = new GoogleCloudSearchResultsPage();
    GoogleCloudPricingCalculatorPage calculatorPage = new GoogleCloudPricingCalculatorPage();
    GoogleCloudCalculatorResultsPage calculatorResultsPage = new GoogleCloudCalculatorResultsPage();
    YopmailPage yopmailPage = new YopmailPage();

    String searchInput = "Google Cloud Platform Pricing Calculator";
    String tab = "Compute Engine";
    String instancesNumber = "4";
    String osField = "Operating System";
    String osValue = "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)";
    String machineClassField = "VM";
    String machineClassValue = "regular";
    String seriesField = "Series";
    String seriesValue = "N1";
    String machineTypeField = "Instance type";
    String machineTypeValue = "n1-standard-8";
    String gpuTypeField = "GPU type";
    String gpuTypeValue = "NVIDIA Tesla P4";
    String gpuNumberField = "Number of GPU";
    int gpuNumberValue = 1;
    String ssdField = "SSD";
    String ssdValue = "2x375";
    String datacenterField = "Datacenter";
    String datacenterValue = "Frankfurt";
    String estimate = "Add to Estimate";
    String emailButtonValue = "Email Estimate";

    @Test
    public void verifyEmailPriceTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
        yopmailPage.getPage();
        yopmailPage.getRandomEmail();
        String emailValue = "";
        emailValue = yopmailPage.copyEmail();
        calculatorPage.openNewTab();
        calculatorPage.switchToNewTab(2);
        startPage.getPage();
        startPage.fillInSearchField(searchInput);
        searchResultsPage.openLink(searchInput);
        calculatorPage.switchToMyFrame();
        calculatorPage.setTab(tab);
        calculatorPage.setInstancesNumber(instancesNumber);
        calculatorPage.setDropdownValue(osField, osValue);
        calculatorPage.setMachineClassValue(machineClassField, machineClassValue);
        calculatorPage.setDropdownValue(seriesField, seriesValue);
        calculatorPage.setDropdownValue(machineTypeField, machineTypeValue);
        calculatorPage.checkGPUCheckbox();
        calculatorPage.setGpuTypeValue(gpuTypeField, gpuTypeValue);
        calculatorPage.setGpuNumberValue(gpuNumberField, gpuNumberValue);
        calculatorPage.setDropdownValue(ssdField, ssdValue);
        calculatorPage.setRegionValue(datacenterField, datacenterValue);
        calculatorPage.pressButton(estimate);
        calculatorPage.pressButton(emailButtonValue);
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
