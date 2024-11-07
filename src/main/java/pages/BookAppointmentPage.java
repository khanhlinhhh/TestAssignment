package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BookAppointmentPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By dateCell = By.cssSelector("[data-date='2024-10-01']");
    private final By selectLocation = By.xpath("//select[@ng-model='appointmentBlock.location']");
    private final By inputProvider = By.xpath("//input[@ng-model='appointmentBlock.provider']");
    private final By inputService = By.id("createAppointmentBlock");
    private final By ulServices = By.xpath("following-sibling::ul");
    private final By liServices = By.xpath("following-sibling::ul/li");
    private final By btnSave = By.xpath("//button[@ng-click='saveAppointmentBlock()']");

    public BookAppointmentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickDateCell() {
        driver.findElement(dateCell).click();
    }

    public void chooseSelectLocation(int index){
        Select select = new Select(driver.findElement(selectLocation));
        select.selectByIndex(index);
    }

    public void sendKeyInputProvider(String value){
        driver.findElement(inputProvider).sendKeys(value);
    }

    public void sendKeyInputService(String value){
        driver.findElement(inputService).sendKeys(value);
    }

    public String getValueInputService(){
       return driver.findElement(inputService).getAttribute("value");
    }

    public void chooseService(int index){
        wait.until(ExpectedConditions.visibilityOfElementLocated(ulServices));
        wait.until(ExpectedConditions.visibilityOfElementLocated(liServices));
        List<WebElement> suggestions = driver.findElements(liServices);
        if (!suggestions.isEmpty()) {
            suggestions.get(index).click();
        }
    }

    public void clickBtnSave(){
        driver.findElement(btnSave).click();
    }
    public String getSelectedLocationValue() {
        Select select = new Select(driver.findElement(selectLocation));
        WebElement selectedOption = select.getFirstSelectedOption();
        return selectedOption != null ? selectedOption.getText() : null;
    }

}
