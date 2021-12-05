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
    * Committed usage: 1 Year
7. Нажать Add to Estimate
8. Проверить соответствие данных следующих полей: VM Class, Instance type, Region, local SSD, commitment term
9. Проверить что сумма аренды в месяц совпадает с суммой получаемой при ручном прохождении теста.
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
import tests.BasicTestngTests;
import utils.parsers.JacksonParser;

import java.io.File;
import java.io.IOException;

public class HurtMePlentyTest extends BasicTestngTests {
    GoogleCloudStartPage startPage = new GoogleCloudStartPage();
    GoogleCloudSearchResultsPage searchResultsPage = new GoogleCloudSearchResultsPage();
    GoogleCloudPricingCalculatorPage calculatorPage = new GoogleCloudPricingCalculatorPage();
    GoogleCloudCalculatorResultsPage calculatorResultsPage = new GoogleCloudCalculatorResultsPage();

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

    @Test(description = "Verify Instance type field")
    public void verifyMachineTypeTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
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

        String actualResult = calculatorResultsPage.getActualResult(calc.getMachineTypeField());
        Assert.assertTrue(actualResult.contains(calc.getMachineTypeValue()),
                String.format("Actual result is %s instead of %s", actualResult, calc.getMachineTypeValue()));
    }

    @Test(description = "Verify VM Class field")
    public void verifyMachineClassTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
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

        String actualResult = calculatorResultsPage.getActualResult(calc.getMachineClassField()).trim();
        String expectedResult = calc.getMachineClassValue().toLowerCase();
        Assert.assertTrue(actualResult.contains(expectedResult),
                String.format("Actual result is %s instead of %s", actualResult, expectedResult));
    }

    @Test(description = "Verify Region field")
    public void verifyRegionTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
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

        String actualResult = calculatorResultsPage.getActualResult(calc.getRegion()).trim();
        Assert.assertTrue(actualResult.contains(calc.getDatacenterValue()),
                String.format("Actual result is %s instead of %s", actualResult, calc.getDatacenterValue()));
    }

    @Test(description = "Verify local SSD field")
    public void verifySsdTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
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

        String actualResult = calculatorResultsPage.getActualResult(calc.getSsdField()).trim();
        Assert.assertTrue(actualResult.contains(calc.getSsdValue()),
                String.format("Actual result is %s instead of %s", actualResult, calc.getSsdValue()));
    }

    @Test(description = "Verify commitment term field")
    public void verifyTermOfCommitmentTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
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
        calculatorPage.setCommittedUsageValue(calc.getCommittedUsageField(), calc.getCommittedUsageValue());
        calculatorPage.pressButton(calc.getEstimate());

        String actualResult = calculatorResultsPage.getActualResult(calc.getCommittedUsageField()).trim();
        Assert.assertTrue(actualResult.contains(calc.getCommittedUsageValue()),
                String.format("Actual result is %s instead of %s", actualResult, calc.getCommittedUsageValue()));
    }

    @Test(description = "Verify Total Estimated Cost field")
    public void verifyTotalCostTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
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

        String actualResult = calculatorResultsPage.getPriceResult();
        LOGGER.debug(actualResult);
        Assert.assertTrue(actualResult.contains(calc.getExpectedCostValue()),
                String.format("Actual result is %s instead of %s", actualResult, calc.getExpectedCostValue()));
    }

}
