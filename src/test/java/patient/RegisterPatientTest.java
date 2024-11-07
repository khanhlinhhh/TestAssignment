package patient;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.RegisterPatientPage;

import java.time.Duration;
import java.util.List;

public class RegisterPatientTest extends BaseTest {
    @Test
    public void testRegisterPatientSuccess() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.setGivenInput("Phan");
        registerPatientPage.setMiddleInput("Xuan");
        registerPatientPage.setFamilyNameInput("Sy");
        registerPatientPage.clickNextButton();

        registerPatientPage.setGenderSelect("M");
        registerPatientPage.clickNextButton();

        registerPatientPage.setDayInput("10");
        registerPatientPage.setMonthSelect("10");
        registerPatientPage.setYearInput("2023");
        registerPatientPage.clickNextButton();

        registerPatientPage.setAddressInput("Da Nang");
        registerPatientPage.clickNextButton();

        registerPatientPage.setPhoneInput("123123");
        registerPatientPage.clickNextButton();
        registerPatientPage.clickNextButton();

        registerPatientPage.clickSubmitButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-item")));

        String expectedMessage = "Created Patient Record";
        Assertions.assertTrue(registerPatientPage.checkToastMessage(expectedMessage), "Register patient failed");
    }

    @Test
    public void testRegisterUnidentifiedPatientSuccess() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.setUnidentifiedPatientCheckbox(true);

        registerPatientPage.setGenderSelect("M");
        registerPatientPage.clickNextButton();

        registerPatientPage.clickSubmitButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-item")));

        String expectedMessage = "Created Patient Record";
        Assertions.assertTrue(registerPatientPage.checkToastMessage(expectedMessage), "Register patient failed");
    }

    @Test
    public void testRegistePatientWithEmptyGivenInput() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.clickNextButton();

        Assertions.assertTrue(registerPatientPage.checkGivenInputRequired(), "Register patient failed");
    }

    @Test
    public void testRegistePatientWithEmptyFamilyNameInput() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.clickNextButton();

        Assertions.assertTrue(registerPatientPage.checkFamilyNameRequired(), "Register patient failed");
    }

    @Test
    public void testRegistePatientCheckboxWorks() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.setUnidentifiedPatientCheckbox(true);

        Assertions.assertTrue(registerPatientPage.checkMenuItemFocused("Gender"), "Register patient failed");
    }

    @Test
    public void testRegistePatientWithEmptyGenderSelect() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.setUnidentifiedPatientCheckbox(true);

        registerPatientPage.clickNextButton();

        Assertions.assertTrue(registerPatientPage.checkGenderSelectRequired(), "Register patient failed");
    }

    @Test
    public void testRegistePatientCancelButtonWorks() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.setUnidentifiedPatientCheckbox(true);

        registerPatientPage.setGenderSelect("M");
        registerPatientPage.clickNextButton();

        registerPatientPage.clickCancelButton();

        Assertions.assertTrue(registerPatientPage.checkMenuItemFocused("Name"), "Register patient failed");
    }

    @Test
    public void testRegistePatientWithMissingDateOfBirth() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.setGivenInput("Phan");
        registerPatientPage.setMiddleInput("Xuan");
        registerPatientPage.setFamilyNameInput("Sy");
        registerPatientPage.clickNextButton();

        registerPatientPage.setGenderSelect("M");
        registerPatientPage.clickNextButton();

        registerPatientPage.setDayInput("10");
        registerPatientPage.clickNextButton();

        Assertions.assertTrue(registerPatientPage.checkDateOfBirthInvalid(), "Register patient failed");
    }

    @Test
    public void testRegistePatientWithDayInputNonNumber() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.setGivenInput("Phan");
        registerPatientPage.setMiddleInput("Xuan");
        registerPatientPage.setFamilyNameInput("Sy");
        registerPatientPage.clickNextButton();

        registerPatientPage.setGenderSelect("M");
        registerPatientPage.clickNextButton();

        registerPatientPage.setDayInput("as");
        registerPatientPage.clickNextButton();

        Assertions.assertTrue(registerPatientPage.checkDayInputInvalid(), "Register patient failed");
    }

    @Test
    public void testRegistePatientWithDayInputGreaterThanMax() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.setGivenInput("Phan");
        registerPatientPage.setMiddleInput("Xuan");
        registerPatientPage.setFamilyNameInput("Sy");
        registerPatientPage.clickNextButton();

        registerPatientPage.setGenderSelect("M");
        registerPatientPage.clickNextButton();

        registerPatientPage.setDayInput("32");
        registerPatientPage.clickNextButton();

        Assertions.assertTrue(registerPatientPage.checkDayInputMaximum(), "Register patient failed");
    }

    @Test
    public void testRegistePatientWithDayInputLessThanMin() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.setGivenInput("Phan");
        registerPatientPage.setMiddleInput("Xuan");
        registerPatientPage.setFamilyNameInput("Sy");
        registerPatientPage.clickNextButton();

        registerPatientPage.setGenderSelect("M");
        registerPatientPage.clickNextButton();

        registerPatientPage.setDayInput("0");
        registerPatientPage.clickNextButton();

        Assertions.assertTrue(registerPatientPage.checkDayInputMinimum(), "Register patient failed");
    }

    @Test
    public void testRegistePatientWithYearInputNonNumber() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.setGivenInput("Phan");
        registerPatientPage.setMiddleInput("Xuan");
        registerPatientPage.setFamilyNameInput("Sy");
        registerPatientPage.clickNextButton();

        registerPatientPage.setGenderSelect("M");
        registerPatientPage.clickNextButton();

        registerPatientPage.setYearInput("asda");
        registerPatientPage.clickNextButton();

        Assertions.assertTrue(registerPatientPage.checkYearInputInvalid(), "Register patient failed");
    }

    @Test
    public void testRegistePatientWithYearInputGreaterThanMax() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.setGivenInput("Phan");
        registerPatientPage.setMiddleInput("Xuan");
        registerPatientPage.setFamilyNameInput("Sy");
        registerPatientPage.clickNextButton();

        registerPatientPage.setGenderSelect("M");
        registerPatientPage.clickNextButton();

        registerPatientPage.setYearInput("2025");
        registerPatientPage.clickNextButton();

        Assertions.assertTrue(registerPatientPage.checkYearInputMaximum(), "Register patient failed");
    }

    @Test
    public void testRegistePatientWithYearInputLessThanMin() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.setGivenInput("Phan");
        registerPatientPage.setMiddleInput("Xuan");
        registerPatientPage.setFamilyNameInput("Sy");
        registerPatientPage.clickNextButton();

        registerPatientPage.setGenderSelect("M");
        registerPatientPage.clickNextButton();

        registerPatientPage.setYearInput("1000");
        registerPatientPage.clickNextButton();

        Assertions.assertTrue(registerPatientPage.checkYearInputMinimum(), "Register patient failed");
    }

    @Test
    public void testRegistePatientWithMonthSelectEmpty() {
        HomePage homePage = loginPage.getHomePage();
        RegisterPatientPage registerPatientPage = homePage.getRegisterPatientPage();

        registerPatientPage.setGivenInput("Phan");
        registerPatientPage.setMiddleInput("Xuan");
        registerPatientPage.setFamilyNameInput("Sy");
        registerPatientPage.clickNextButton();

        registerPatientPage.setGenderSelect("M");
        registerPatientPage.clickNextButton();

        registerPatientPage.setDayInput("10");
        registerPatientPage.setYearInput("2015");
        registerPatientPage.clickNextButton();

        Assertions.assertTrue(registerPatientPage.checkDateOfBirthInvalid(), "Register patient failed");
    }
}
