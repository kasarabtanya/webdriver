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

import org.testng.annotations.Test;
import pages.googlecloud.GoogleCloudPricingCalculatorPage;
import pages.googlecloud.GoogleCloudResultsPage;
import pages.googlecloud.GoogleCloudStartPage;
import tests.BasicTestngTests;

public class HurtMePlentyTest extends BasicTestngTests {
    GoogleCloudStartPage startPage = new GoogleCloudStartPage();
    GoogleCloudResultsPage resultsPage = new GoogleCloudResultsPage();
    GoogleCloudPricingCalculatorPage calculatorPage = new GoogleCloudPricingCalculatorPage();

    String searchInput = "Google Cloud Platform Pricing Calculator";
    String instancesNumber = "4";
    String osField = "Operating System";
    String osValue = "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)";
    String osValue2 = "Paid: SLES";
    String mcField = "Machine Class";
    String mcValue = "Regular";
    String seriesField = "Series";
    String seriesValue = "N1";
    String mtField = "Machine type";
    String mtValue = "n1-standard-8";

    String ssdField = "SSD";
    String ssdValue = "2x375";
    String datacenterField = "Datacenter";
    String datacenterValue = "Frankfurt (europe-west3)";
    String committedUsageField = "Committed";
    String committedUsageValue = "1 Year";
    String estimate = "Add to Estimate";
    String email = "Email Estimate";


    @Test
    public void DebugTest() throws InterruptedException {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
        calculatorPage.getPage();
        calculatorPage.switchToMyFrame();
        calculatorPage.setInstancesNumber(instancesNumber);
        calculatorPage.openDropdown(osField);
        calculatorPage.setDropdownValue(osValue);

    //    calculatorPage.openDropdown(mcField);
     //   calculatorPage.setDropdownValue(mcValue);
        calculatorPage.openDropdown(seriesField);
        calculatorPage.setDropdownValue( seriesValue);
   //     calculatorPage.openDropdown(mtField);
//        calculatorPage.setDropdownValue(mtValue);

        calculatorPage.openDropdown(ssdField);
        calculatorPage.setDropdownValue(ssdValue);
  //      calculatorPage.openDropdown(datacenterField);
  //      calculatorPage.setDropdownValue(datacenterValue);
  //      calculatorPage.openDropdown(committedUsageField);
   //     calculatorPage.setDropdownValue(committedUsageValue);
        Thread.sleep(4000);
        calculatorPage.pressButton(estimate);
        LOGGER.debug(calculatorPage.getResult());
        System.out.println(calculatorPage.getResult());
        calculatorPage.pressButton(email);
    }


    @Test
    public void HurtMePlentyTest() throws InterruptedException {
        LOGGER.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + " is executing");
        startPage.getPage();
        startPage.fillInSearchField(searchInput);
        resultsPage.openLink(searchInput);
        //  calculatorPage.setComputeEngineTab();
        calculatorPage.setInstancesNumber("4");
        Thread.sleep(10000);


    }
}
