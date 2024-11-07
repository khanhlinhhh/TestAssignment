package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private By alertText = By.xpath("//*[@id=\"content\"]/div[2]/div/h4");
    private By logoutBtn = By.xpath("//a[normalize-space()='Logout']");
    private By findPatientRecordBtn = By.xpath("//*[@id='coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension']");
    private By registerPatientBtn = By.xpath("//a[@id='referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension']");
    private By captureVitalsBtn = By.xpath("//a[@id='referenceapplication-vitals-referenceapplication-vitals-extension']");

    private final By btnAppointment = By.id("appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension");
    private By btnAppointmentProvider = By.id("appointmentschedulingui-scheduleProviders-app");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getAlertString() {
        return driver.findElement(alertText).getText();
    }

    public FindPatientRecordPage getFindPatientRecordPage() {
        driver.findElement(findPatientRecordBtn).click();
        return new FindPatientRecordPage(driver);
    }

    public CaptureVitals getCaptureVitals() {
        driver.findElement(captureVitalsBtn).click();
        return new CaptureVitals(driver);
    }

    public LoginPage clickLogoutBtn() {
        driver.findElement(logoutBtn).click();
        return new LoginPage(driver);
    }

    public BookAppointmentPage getBookAppointmentPage() {
        driver.findElement(btnAppointment).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnAppointmentProvider));
        WebElement secondButton = wait.until(ExpectedConditions.elementToBeClickable(btnAppointmentProvider));
        secondButton.click();
        return new BookAppointmentPage(driver);
    }

    public RegisterPatientPage getRegisterPatientPage() {
        driver.findElement(registerPatientBtn).click();
        return new RegisterPatientPage(driver);
    }
}
