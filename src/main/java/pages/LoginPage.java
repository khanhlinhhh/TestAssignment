package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By loginErrorText = By.xpath("//*[@id=\"error-message\"]");
    private By loginTitle = By.xpath("//*[@id=\"login-form\"]/fieldset/legend");
    private By userNameInput = By.xpath("//*[@id=\"username\"]");
    private By passWordInput = By.xpath("//*[@id=\"password\"]");
    private By locationItem = By.xpath("//*[@id=\"Inpatient Ward\"]");
    private By loginBtn = By.xpath("//*[@id=\"loginButton\"]");

     public LoginPage(WebDriver driver) {
         this.driver = driver;
     }

    public void setUserName(String userName) {
        driver.findElement(userNameInput).sendKeys(userName);
    }

    public void setPassword(String password) {
        driver.findElement(passWordInput).sendKeys(password);
    }

    public void setLocation() {
        driver.findElement(locationItem).click();
    }

    public String getErrorText() {
         return driver.findElement(loginErrorText).getText();
    }

    public String getLoginTitle() {
         return driver.findElement(loginTitle).getText();
    }

    public HomePage clickLoginButton() {
        driver.findElement(loginBtn).click();
        return new HomePage(driver);
    }

    public HomePage getHomePage() {
        setUserName("Admin");
        setPassword("Admin123");
        setLocation();
        HomePage homePage = clickLoginButton();

        return homePage;
    }
}
