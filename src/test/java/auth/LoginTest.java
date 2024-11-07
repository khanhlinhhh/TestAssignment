package auth;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;

public class LoginTest extends BaseTest {
    @Test
    public void testLoginSuccess() {
        loginPage.setUserName("Admin");
        loginPage.setPassword("Admin123");
        loginPage.setLocation();
        HomePage homePage = loginPage.clickLoginButton();

        String alertText = homePage.getAlertString();
        Assertions.assertTrue(alertText.contains("Logged in as Super User (admin) at Inpatient Ward."), "Alert text is incorrect!");
    }

    @Test
    public void testLoginFail() {
        loginPage.setUserName("Admin123");
        loginPage.setPassword("Admin123");
        loginPage.setLocation();
        loginPage.clickLoginButton();

        String alertText = loginPage.getErrorText();
        Assertions.assertTrue(alertText.contains("Invalid username/password. Please try again."), "Error text is incorrect!");
    }
}
