package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RegisterPatientPage {
    private WebDriver driver;

    private By nextBtn = By.xpath("//button[@id='next-button']");
    private By givenInput = By.xpath("//input[@name='givenName']");
    private By givenInputError = By.xpath("//input[@name='givenName']/ancestor::*[1]//span[contains(@class, 'field-error')]");
    private By familyNameInputError = By.xpath("//input[@name='familyName']/ancestor::*[1]//span[contains(@class, 'field-error')]");
    private By genderSelectError = By.xpath("//select[@name='gender']/ancestor::*[1]//span[contains(@class, 'field-error')]");
    private By middleInput = By.xpath("//input[@name='middleName']");
    private By familyNameInput = By.xpath("//input[@name='familyName']");
    private By unidentifiedPatientCheckbox = By.xpath("//input[@id='checkbox-unknown-patient']");
    private By genderSelect = By.xpath("//select[@id='gender-field']");
    private By dayInput = By.xpath("//input[@id='birthdateDay-field']");
    private By dayInputError = By.xpath("//input[@id='birthdateDay-field']/ancestor::*[1]//span[contains(@class, 'field-error')]");
    private By monthSelect = By.xpath("//select[@id='birthdateMonth-field']");
    private By monthSelectError = By.xpath("//input[@id='birthdateMonth-field']/ancestor::*[1]//span[contains(@class, 'field-error')]");
    private By yearInput = By.xpath("//input[@id='birthdateYear-field']");
    private By yearInputError = By.xpath("//input[@id='birthdateYear-field']/ancestor::*[1]//span[contains(@class, 'field-error')]");
    private By yearsInput = By.xpath("//input[@id='birthdateYears-field']");
    private By monthsInput = By.xpath("//input[@id='birthdateMonths-field']");
    private By addressInput = By.xpath("//input[@id='address1']");
    private By phoneInput = By.xpath("//input[@name='phoneNumber']");
    private By cancelButton = By.xpath("//input[@id='cancelSubmission']");
    private By submitButton = By.xpath("//input[@id='submit']");

    public RegisterPatientPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickNextButton() {
        driver.findElement(nextBtn).click();
    }

    public void setGivenInput(String value) {
        driver.findElement(givenInput).sendKeys(value);
    }

    public void setMiddleInput(String value) {
        driver.findElement(middleInput).sendKeys(value);
    }

    public void setFamilyNameInput(String value) {
        driver.findElement(familyNameInput).sendKeys(value);
    }

    public void setUnidentifiedPatientCheckbox(Boolean checked) {
        WebElement checkbox = driver.findElement(unidentifiedPatientCheckbox);

        if (checkbox.isSelected() != checked) {
            checkbox.click();
        }
    }

    public void setGenderSelect(String value) {
        WebElement genderSelectElement = driver.findElement(genderSelect);

        Select genderSelect = new Select(genderSelectElement);

        genderSelect.selectByValue(value);
    }

    public void setDayInput(String value) {
        driver.findElement(dayInput).sendKeys(value);
    }

    public void setMonthSelect(String value) {
        WebElement monthSelectElement = driver.findElement(monthSelect);

        Select monthSelect = new Select(monthSelectElement);

        monthSelect.selectByValue(value);
    }

    public void setYearInput(String value) {
        driver.findElement(yearInput).sendKeys(value);
    }

    public void setYearsInput(String value) {
        driver.findElement(yearsInput).sendKeys(value);
    }

    public void setMonthsInput(String value) {
        driver.findElement(monthsInput).sendKeys(value);
    }

    public void setAddressInput(String value) {
        driver.findElement(addressInput).sendKeys(value);
    }

    public void setPhoneInput(String value) {
        driver.findElement(phoneInput).sendKeys(value);
    }

    public void clickCancelButton() {
        driver.findElement(cancelButton).click();
    }

    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    public Boolean checkToastMessage(String expectedMessage) {
        return driver.findElement(By.cssSelector(".toast-item p")).getText().contains(expectedMessage);
    }

    public Boolean checkGivenInputRequired() {
        return driver.findElement(givenInputError).getText().contains("Required");
    }

    public Boolean checkFamilyNameRequired() {
        return driver.findElement(familyNameInputError).getText().contains("Required");
    }

    public Boolean checkGenderSelectRequired() {
        return driver.findElement(genderSelectError).getText().contains("Required");
    }

    public Boolean checkMenuItemFocused(String value) {
        By menuItemFocused = By.xpath("//li[@class='question-legend focused']//span");

        return driver.findElement(menuItemFocused).getText().contains(value);
    }

    public Boolean checkDateOfBirthInvalid() {
        WebElement labelElement = driver.findElement(By.xpath("//label[contains(text(), \"What's the patient's birth date?\")]"));

        WebElement parentElement = labelElement.findElement(By.xpath("./ancestor::*[1]"));

        WebElement fieldError = parentElement.findElement(By.xpath(".//span[contains(@class, 'field-error')]"));

        return fieldError.getText().contains("You need to inform a valid date");
    }

    public Boolean checkDayInputInvalid() {
        return driver.findElement(dayInputError).getText().contains("Must be a number");
    }

    public Boolean checkDayInputMinimum() {
        return driver.findElement(dayInputError).getText().contains("Minimum: 1");
    }

    public Boolean checkDayInputMaximum() {
        return driver.findElement(dayInputError).getText().contains("Maximum: 31");
    }

    public Boolean checkYearInputInvalid() {
        return driver.findElement(yearInputError).getText().contains("Must be a number");
    }

    public Boolean checkYearInputMinimum() {
        return driver.findElement(yearInputError).getText().contains("Minimum: 1904");
    }

    public Boolean checkYearInputMaximum() {
        return driver.findElement(yearInputError).getText().contains("Maximum: " + LocalDate.now().getYear());
    }
}
