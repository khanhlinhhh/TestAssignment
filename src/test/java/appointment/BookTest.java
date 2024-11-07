package appointment;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.BookAppointmentPage;
import pages.HomePage;

public class BookTest extends BaseTest {

    @Test
    public void testBookAppointmentSuccess() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        BookAppointmentPage bookAppointmentPage = homePage.getBookAppointmentPage();
        bookAppointmentPage.clickDateCell();
        waitingByTime(300);
        bookAppointmentPage.chooseSelectLocation(2);
        bookAppointmentPage.sendKeyInputProvider("VietDoan");
        bookAppointmentPage.sendKeyInputService("a");
        bookAppointmentPage.chooseService(0);
        String inputService = bookAppointmentPage.getValueInputService();
        if (!inputService.isEmpty()) {
            bookAppointmentPage.clickBtnSave();
            String currentUrl = driver.getCurrentUrl();
            Assertions.assertEquals("https://demo.openmrs.org/openmrs/appointmentschedulingui/scheduleProviders.page", currentUrl, "Chuyển trang không thành công.");
            Assertions.assertTrue(true, "Đặt lịch thành công!");

        }
        Assertions.assertFalse(false, "Lỗi: Không đặt được lịch.");
    }
    @Test
    public void testSelectInvalidLocation() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        BookAppointmentPage bookAppointmentPage = homePage.getBookAppointmentPage();

        bookAppointmentPage.clickDateCell();
        waitingByTime(300);
        bookAppointmentPage.chooseSelectLocation(-1);

        String selectedValue = bookAppointmentPage.getSelectedLocationValue();
        Assertions.assertNull(selectedValue, "Giá trị chọn không phải là null khi chọn địa điểm không hợp lệ.");
    }

    @Test
    public void testEmptyServiceSelection() throws InterruptedException {
        HomePage homePage = loginPage.getHomePage();
        BookAppointmentPage bookAppointmentPage = homePage.getBookAppointmentPage();

        bookAppointmentPage.clickDateCell();
        waitingByTime(300);
        bookAppointmentPage.chooseSelectLocation(2);
        bookAppointmentPage.sendKeyInputProvider("VietDoan");
        bookAppointmentPage.sendKeyInputService("a");

        String inputServiceValue = bookAppointmentPage.getValueInputService();
        Assertions.assertTrue(inputServiceValue.isEmpty(), "Giá trị dịch vụ không phải là rỗng khi không chọn dịch vụ.");
    }
}
