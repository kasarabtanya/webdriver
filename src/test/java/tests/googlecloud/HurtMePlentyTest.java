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
8. Проверить соответствие данных следующих полей: VM Class, Instance type, Region, local SSD, commitment term
9. Проверить что сумма аренды в месяц совпадает с суммой получаемой при ручном прохождении теста.
*/

package tests.googlecloud;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.googlecloud.GoogleCloudCalculatorResultsPage;
import pages.googlecloud.GoogleCloudPricingCalculatorPage;
import pages.googlecloud.GoogleCloudSearchResultsPage;
import pages.googlecloud.GoogleCloudStartPage;
import tests.BasicTestngTests;

public class HurtMePlentyTest extends BasicTestngTests {
    GoogleCloudStartPage startPage = new GoogleCloudStartPage();
    GoogleCloudSearchResultsPage searchResultsPage = new GoogleCloudSearchResultsPage();
    GoogleCloudPricingCalculatorPage calculatorPage = new GoogleCloudPricingCalculatorPage();
    GoogleCloudCalculatorResultsPage calculatorResultsPage = new GoogleCloudCalculatorResultsPage();

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
    String region = "Region";
    String datacenterValue = "Frankfurt";
    String committedUsageField = "Committ";
    String committedUsageValue = "1 Year";
    String estimate = "Add to Estimate";
    String expectedCostValue = "2,617.31";

    @Test(description = "Verify Instance type field")
    public void verifyMachineTypeTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
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

        String actualResult = calculatorResultsPage.getActualResult(machineTypeField);
        Assert.assertTrue(actualResult.contains(machineTypeValue),
                String.format("Actual result is %s instead of %s", actualResult, machineTypeValue));
    }

    @Test(description = "Verify VM Class field")
    public void verifyMachineClassTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
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

        String actualResult = calculatorResultsPage.getActualResult(machineClassField).trim();
        String expectedResult = machineClassValue.toLowerCase();
        Assert.assertTrue(actualResult.contains(expectedResult),
                String.format("Actual result is %s instead of %s", actualResult, expectedResult));
    }

    @Test(description = "Verify Region field")
    public void verifyRegionTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
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

        String actualResult = calculatorResultsPage.getActualResult(region).trim();
        Assert.assertTrue(actualResult.contains(datacenterValue),
                String.format("Actual result is %s instead of %s", actualResult, datacenterValue));
    }

    @Test(description = "Verify local SSD field")
    public void verifySsdTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
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

        String actualResult = calculatorResultsPage.getActualResult(ssdField).trim();
        Assert.assertTrue(actualResult.contains(ssdValue),
                String.format("Actual result is %s instead of %s", actualResult, ssdValue));
    }

    @Test(description = "Verify commitment term field")
    public void verifyTermOfCommitmentTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
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
        calculatorPage.setCommittedUsageValue(committedUsageField, committedUsageValue);
        calculatorPage.pressButton(estimate);

        String actualResult = calculatorResultsPage.getActualResult(committedUsageField).trim();
        Assert.assertTrue(actualResult.contains(committedUsageValue),
                String.format("Actual result is %s instead of %s", actualResult, committedUsageValue));
    }

    @Test(description = "Verify Total Estimated Cost field")
    public void verifyTotalCostTest() {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
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

        String actualResult = calculatorResultsPage.getPriceResult();
        LOGGER.debug(actualResult);
        Assert.assertTrue(actualResult.contains(expectedCostValue),
                String.format("Actual result is %s instead of %s", actualResult, expectedCostValue));
    }

}
