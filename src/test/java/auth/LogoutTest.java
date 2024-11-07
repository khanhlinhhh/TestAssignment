package auth;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;

public class LogoutTest extends BaseTest {
    @Test
    public void testLogoutSuccess() {
        loginPage.setUserName("Admin");
        loginPage.setPassword("Admin123");
        loginPage.setLocation();
        HomePage homePage = loginPage.clickLoginButton();
        LoginPage afterLogoutLoginPage = homePage.clickLogoutBtn();

        String loginTitle = afterLogoutLoginPage.getLoginTitle();
        Assertions.assertTrue(loginTitle.contains("LOGIN"), "Login title is incorrect!");
    }
}
