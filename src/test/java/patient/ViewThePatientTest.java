package patient;

import base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.HomePage;
import pages.FindPatientRecordPage;
import org.openqa.selenium.WebElement;
import pages.ViewThePatient;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ViewThePatientTest extends BaseTest {

    public void testViewPatient() {
        System.out.println("LoginPage in ViewThePatientTest: " + loginPage);
        // Đăng nhậpang
        HomePage homePage = loginPage.getHomePage();
        // Chuyển đến trang tìm kiếm bệnh nhân
        FindPatientRecordPage findPatientRecordPage = homePage.getFindPatientRecordPage();

        // Tìm kiếm bệnh nhân
        findPatientRecordPage.setInput("John");

        // Đợi một chút để kết quả tìm kiếm xuất hiện
        try {
            Thread.sleep(2000); // Chờ 2 giây
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Kiểm tra xem có kết quả tìm kiếm không
        assertTrue("No patient records found", findPatientRecordPage.getRecords().size() > 0);

        // Lấy tên bệnh nhân đầu tiên trong danh sách kết quả tìm kiếm
//        String expectedPatientName = viewThePatient.getPatientName();

        // Click vào bệnh nhân đầu tiên trong danh sách kết quả tìm kiếm
        WebElement firstPatient = findPatientRecordPage.getRecords().get(0);
        firstPatient.click();

        // Kiểm tra chi tiết bệnh nhân
        String actualPatientName = viewThePatient.getPatientName();
        assertEquals("Patient name does not match", "John", actualPatientName);
    }
    @Test
    public void testAddNewCondition() {
        testViewPatient();
        viewThePatient.clickIconCondition();
        viewThePatient.clickAddNewConditionIcon();
        viewThePatient.enterCondition("Diabetes");
        viewThePatient.saveCondition();

        driver.navigate().back();
        // Add assertions to verify the condition was added
//        assertTrue(driver.getPageSource().contains("Diabetes"));

    }

    @Test
    public void testAddNewAllergy() {
        testViewPatient();
        viewThePatient.clickIconAllergy();
        viewThePatient.clickAddNewAllergyIcon();
        viewThePatient.clickAddAllergyButton();
        viewThePatient.selectAllergen("Peanuts");
        viewThePatient.selectReaction("Rash");
        viewThePatient.selectSeverity("Moderate");
        viewThePatient.setCommentsField("normal");
        viewThePatient.saveAllergy();
        // Add assertions to verify the allergy was added
        assertTrue(driver.getPageSource().contains("Peanuts"));
    }

    @Test
    public void testEditAllergy() {
        testViewPatient();
        viewThePatient.clickIconAllergy();
        viewThePatient.clickEditAllergyIcon();
        viewThePatient.selectEidtReaction("Cough");
        viewThePatient.selectEditSeverity("Mild");
        viewThePatient.saveAllergy();
        // Add assertions to verify the allergy was edited
        
        assertTrue(driver.getPageSource().contains("Dust"));
    }

    @Test
    public void testDeleteAllergy() {
        testViewPatient();
        viewThePatient.clickIconAllergy();
        viewThePatient.clickDeleteAllergyIcon();
        // Add assertions to verify the allergy was deleted
        assertTrue(!driver.getPageSource().contains("Dust"));
    }

    @Test
    public void testUploadAttachments() {
        viewThePatient.clickUploadAttachmentsIcon();
        viewThePatient.uploadFile("C:/Users/ADMIN/Downloads/hello.jpg");
        viewThePatient.enterCaption("Test Caption");
        viewThePatient.saveAttachment();
        // Add assertions to verify the attachment was uploaded
        assertTrue(driver.getPageSource().contains("Test Caption"));
    }

    @Test
    public void testDeleteAttachment() {
        viewThePatient.clickAttachmentLink();
        assertTrue(driver.getPageSource().contains("Test Caption"));

        viewThePatient.clickUploadAttachmentsIcon();
        viewThePatient.deleteAttachment();
        // Add assertions to verify the attachment was deleted
        assertTrue(!driver.getPageSource().contains("Test Caption"));
    }
//    @After
//    public void tearDown() {
//        driver.quit();
//    }
}
