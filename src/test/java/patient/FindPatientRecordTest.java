package patient;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.FindPatientRecordPage;
import pages.HomePage;

import java.util.List;

public class FindPatientRecordTest extends BaseTest {
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
    public void testFindPatientRecordsByEmptyInput() {
        HomePage homePage = loginPage.getHomePage();
        FindPatientRecordPage findPatientRecordPage = homePage.getFindPatientRecordPage();

        List<WebElement> records = findPatientRecordPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records By Empty Input Failed");
    }

    @Test
    public void testFindPatientRecordsByName() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        FindPatientRecordPage findPatientRecordPage = homePage.getFindPatientRecordPage();

        String partOfName = "Alan";
        findPatientRecordPage.setInput(partOfName);
        waitingByTime(2000);

        List<WebElement> records = findPatientRecordPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records By Existed Name Failed");
    }

    @Test
    public void testFindPatientRecordsByID() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        FindPatientRecordPage findPatientRecordPage = homePage.getFindPatientRecordPage();

        String partOfName = "100";
        findPatientRecordPage.setInput(partOfName);
        waitingByTime(2000);

        List<WebElement> records = findPatientRecordPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records By Existed ID Failed");
    }

    @Test
    public void testFindPatientRecordsClearedBtn() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        FindPatientRecordPage findPatientRecordPage = homePage.getFindPatientRecordPage();

        String partOfName = "100";
        findPatientRecordPage.setInput(partOfName);
        waitingByTime(2000);
        findPatientRecordPage.clickClearedInputBtn();
        waitingByTime(500);

        String inputText = findPatientRecordPage.getInput();

        List<WebElement> records = findPatientRecordPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        if(!inputText.contains("")) testStatus = false;

        Assertions.assertTrue(testStatus, "Find Patient Records When Click Cleared Button Failed");
    }


    @Test
    public void testFindPatientRecordsByNonExistValue() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        FindPatientRecordPage findPatientRecordPage = homePage.getFindPatientRecordPage();

        String partOfName = "asd";
        findPatientRecordPage.setInput(partOfName);
        waitingByTime(2000);

        List<WebElement> records = findPatientRecordPage.getRecords();
        boolean testStatus = getTestStatusNotHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records By Non Existed Value Failed");
    }

    @Test
    public void testFindPatientRecordsNumberPage() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        FindPatientRecordPage findPatientRecordPage = homePage.getFindPatientRecordPage();

        findPatientRecordPage.clickPagiNumberBtn(3);
        waitingByTime(500);

        List<WebElement> records = findPatientRecordPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records When Click Number Page Failed");
    }

    @Test
    public void testFindPatientRecordsFirstPage() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        FindPatientRecordPage findPatientRecordPage = homePage.getFindPatientRecordPage();

        findPatientRecordPage.clickPagiNumberBtn(3);
        waitingByTime(500);
        findPatientRecordPage.clickPagiFirstBtn();
        waitingByTime(500);

        List<WebElement> records = findPatientRecordPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records When Click First Page Failed");
    }

    @Test
    public void testFindPatientRecordsPreviousPage() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        FindPatientRecordPage findPatientRecordPage = homePage.getFindPatientRecordPage();

        findPatientRecordPage.clickPagiNumberBtn(3);
        waitingByTime(500);
        findPatientRecordPage.clickPagiPreviousBtn();
        waitingByTime(500);

        List<WebElement> records = findPatientRecordPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records When Click Previous Page Failed");
    }

    @Test
    public void testFindPatientRecordsNextPage() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        FindPatientRecordPage findPatientRecordPage = homePage.getFindPatientRecordPage();

        findPatientRecordPage.clickPagiNextBtn();
        waitingByTime(500);

        List<WebElement> records = findPatientRecordPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records When Click Next Page Failed");
    }

    @Test
    public void testFindPatientRecordsLastPage() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        FindPatientRecordPage findPatientRecordPage = homePage.getFindPatientRecordPage();

        findPatientRecordPage.clickPagiLastBtn();
        waitingByTime(500);

        List<WebElement> records = findPatientRecordPage.getRecords();
        boolean testStatus = getTestStatusHasRecords(records);

        Assertions.assertTrue(testStatus, "Find Patient Records When Click Last Page Failed");
    }
}
