package patient;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.CaptureVitals;
import pages.HomePage;

import java.util.List;

public class CaptureVitalsTest extends BaseTest {
    public Boolean getTestStatusHasRecords(List<WebElement> records) {
        if(records.size() > 1 || !records.get(0).getText().contains("No matching records found")) return true;

        return false;
    }

    public Boolean getTestStatusNotHasRecords(List<WebElement> records) {
        System.out.println(records.get(0).getText());
        if(records.get(0).getText().contains("No matching records found")) return true;

        return false;
    }

    @Test
    public void testCaptureVitalsByEmptyInput() {
        HomePage homePage = loginPage.getHomePage();
        CaptureVitals captureVitalsPage = homePage.getCaptureVitals();

        List<WebElement> records = captureVitalsPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records By Empty Input Failed");
    }

    @Test
    public void testCaptureVitalsByName() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        CaptureVitals captureVitalsPage = homePage.getCaptureVitals();

        String partOfName = "Alan";
        captureVitalsPage.setInput(partOfName);
        waitingByTime(2000);

        List<WebElement> records = captureVitalsPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records By Existed Name Failed");
    }

    @Test
    public void testCaptureVitalsByID() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        CaptureVitals captureVitalsPage = homePage.getCaptureVitals();

        String partOfName = "100";
        captureVitalsPage.setInput(partOfName);
        waitingByTime(2000);

        List<WebElement> records = captureVitalsPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records By Existed ID Failed");
    }

    @Test
    public void testCaptureVitalsClearedBtn() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        CaptureVitals captureVitalsPage = homePage.getCaptureVitals();

        String partOfName = "100";
        captureVitalsPage.setInput(partOfName);
        waitingByTime(2000);
        captureVitalsPage.clickClearedInputBtn();
        waitingByTime(500);

        String inputText = captureVitalsPage.getInput();

        List<WebElement> records = captureVitalsPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        if(!inputText.contains("")) testStatus = false;

        Assertions.assertTrue(testStatus, "Find Patient Records When Click Cleared Button Failed");
    }


    @Test
    public void testCaptureVitalsByNonExistValue() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        CaptureVitals captureVitalsPage = homePage.getCaptureVitals();

        String partOfName = "asd";
        captureVitalsPage.setInput(partOfName);
        waitingByTime(2000);

        List<WebElement> records = captureVitalsPage.getRecords();
        boolean testStatus = getTestStatusNotHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records By Non Existed Value Failed");
    }

    @Test
    public void testCaptureVitalsNumberPage() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        CaptureVitals captureVitalsPage = homePage.getCaptureVitals();

        captureVitalsPage.clickPagiNumberBtn(3);
        waitingByTime(500);

        List<WebElement> records = captureVitalsPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records When Click Number Page Failed");
    }

    @Test
    public void testCaptureVitalsFirstPage() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        CaptureVitals captureVitalsPage = homePage.getCaptureVitals();

        captureVitalsPage.clickPagiNumberBtn(3);
        waitingByTime(500);
        captureVitalsPage.clickPagiFirstBtn();
        waitingByTime(500);

        List<WebElement> records = captureVitalsPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records When Click First Page Failed");
    }

    @Test
    public void testCaptureVitalsPreviousPage() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        CaptureVitals captureVitalsPage = homePage.getCaptureVitals();

        captureVitalsPage.clickPagiNumberBtn(3);
        waitingByTime(500);
        captureVitalsPage.clickPagiPreviousBtn();
        waitingByTime(500);

        List<WebElement> records = captureVitalsPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records When Click Previous Page Failed");
    }

    @Test
    public void testCaptureVitalsNextPage() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        CaptureVitals captureVitalsPage = homePage.getCaptureVitals();

        captureVitalsPage.clickPagiNextBtn();
        waitingByTime(500);

        List<WebElement> records = captureVitalsPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records When Click Next Page Failed");
    }

    @Test
    public void testCaptureVitalsLastPage() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        CaptureVitals captureVitalsPage = homePage.getCaptureVitals();

        captureVitalsPage.clickPagiLastBtn();
        waitingByTime(500);

        List<WebElement> records = captureVitalsPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records When Click Last Page Failed");
    }
}
