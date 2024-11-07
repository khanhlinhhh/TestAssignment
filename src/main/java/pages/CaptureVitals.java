package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CaptureVitals {
    private WebDriver driver;

    private By input = By.xpath("//input[@id='patient-search']");
    private By inputClearedBtn = By.xpath("//*[@id=\"patient-search-clear-button\"]");
    private By tableBody = By.xpath("//*[@id=\"patient-search-results-table\"]/tbody");
    private By tableBodyRows = By.xpath("//*[@id=\"patient-search-results-table\"]/tbody/tr");
    private String pagiNumberLink = "//*[@id=\"patient-search-results-table_paginate\"]/span/a[%d]";
    private By pagiFirstBtn = By.xpath("//*[@id=\"patient-search-results-table_first\"]");
    private By pagiPreviousBtn = By.xpath("//*[@id=\"patient-search-results-table_previous\"]");
    private By pagiNextBtn = By.xpath("//*[@id=\"patient-search-results-table_next\"]");
    private By pagiLastBtn = By.xpath("//*[@id=\"patient-search-results-table_last\"]");

    public CaptureVitals(WebDriver driver) {
        this.driver = driver;
    }

    public void setInput(String text) {
        driver.findElement(input).sendKeys(text);
    }

    public String getInput() {
        return driver.findElement(input).getText();
    }

    public List<WebElement> getRecords() {
        return driver.findElements(tableBodyRows);
    }

    public void clickClearedInputBtn() {
        driver.findElement(inputClearedBtn).click();
    }

    public void clickPagiNumberBtn(int page) {
        driver.findElement(By.xpath(String.format(pagiNumberLink, page))).click();
    }

    public void clickPagiFirstBtn() {
        driver.findElement(pagiFirstBtn).click();
    }

    public void clickPagiPreviousBtn() {
        driver.findElement(pagiPreviousBtn).click();
    }

    public void clickPagiNextBtn() {
        driver.findElement(pagiNextBtn).click();
    }

    public void clickPagiLastBtn() {
        driver.findElement(pagiLastBtn).click();
    }
}
