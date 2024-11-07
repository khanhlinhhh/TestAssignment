package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ViewThePatient {
    private WebDriver driver;

    // Locators for patient details
    private By patientName = By.cssSelector(".patient-header .demographics .name .PersonName-givenName");

    private By iconCondition = By.cssSelector(".dashboard .info-header .icon-pencil");
    private By iconAllergy = By.cssSelector(".dashboard .info-header #allergyui-editAllergies");

    // Locators for icons
    private By addNewConditionIcon = By.cssSelector("#condition .actions .confirm");
    private By addNewAllergyIcon = By.cssSelector(".container-fluid #allergyui-addNewAllergy");
    private By uploadAttachmentsIcon = By.cssSelector(".icon-plus-sign-alt[title='Upload attachments']");

    // Locators for Add New Condition form
    private By conditionField = By.id("conceptId-input");
    private By saveConditionBtn = By.cssSelector(".container-fluid #actions #addConditionBtn");

    // Locators for Add New Allergy form
    private By addAllergyBtn = By.cssSelector("#types > label:nth-child(2)");
    private By allergenField = By.cssSelector("#allergy-comment");
    private By reactionField = By.id("#reactions");
    private By severityField = By.id("#severities");
    private By commentsField = By.cssSelector("#comment #allergy-comment");
    private By saveAllergyBtn = By.cssSelector("#actions #addAllergyBtn");

    private By editAllergyIcon = By.cssSelector("#allergies > tbody > tr > td:nth-child(6) > i.icon-pencil.edit-action");

    private By deleteAllergyIcon = By.cssSelector("#allergies > tbody > tr > td:nth-child(6) > i.icon-remove.delete-action");

    // Locators for Upload Attachments form
    private By uploadFileInput = By.id("visit-documents-dropzone");
    private By captionField = By.id("visit-documents-caption");
    private By saveAttachmentBtn = By.cssSelector("button[type='submit']");
    private By attachmentLink = By.cssSelector(".att a");
    private By deleteAttachmentBtn = By.cssSelector(".att .delete");

    public ViewThePatient(WebDriver driver) {
        this.driver = driver;
    }

    // Method to get patient name
    public String getPatientName() {
        if (driver.findElements(patientName).size() > 0) {
            return driver.findElement(patientName).getText();
        } else {
            return "Patient name not found";
        }
    }

    public void clickIconCondition() {
        driver.findElement(iconCondition).click();
    }

    public void clickIconAllergy() {
        driver.findElement(iconAllergy).click();
    }

    // Methods to click icons
    public void clickAddNewConditionIcon() {
        driver.findElement(addNewConditionIcon).click();
    }

    public void clickAddNewAllergyIcon() {
        driver.findElement(addNewAllergyIcon).click();
    }

    public void clickUploadAttachmentsIcon() {
        driver.findElement(uploadAttachmentsIcon).click();
    }

    // Methods to interact with Add New Condition form
    public void enterCondition(String condition) {
        driver.findElement(conditionField).clear(); // Xóa nội dung hiện tại nếu có
        driver.findElement(conditionField).sendKeys(condition); // Nhập giá trị mới
    }

    public void saveCondition() {
        driver.findElement(saveConditionBtn).click();
    }

    // Methods to interact with Add New Allergy form
    public void clickAddAllergyButton() {
        driver.findElement(addAllergyBtn).click();
    }


    public void selectAllergen(String allergen) {
        new Select(driver.findElement(allergenField)).selectByVisibleText(allergen);
    }

    public void selectDropdownByIndex(By reactionField, int index, String text) {
        List<WebElement> dropdowns = driver.findElements(reactionField);
        if (index < dropdowns.size()) {
            new Select(dropdowns.get(index)).selectByVisibleText(text);
        } else {
            System.out.println("Không tìm thấy dropdown với index " + index);
        }
    }

    public void selectReaction(String reaction) {
//        new Select(driver.findElement(reactionField)).selectByVisibleText(reaction);
        selectDropdownByIndex(reactionField, 0, reaction);
    }

    public void selectEidtReaction(String reaction) {
//        new Select(driver.findElement(eidtReactionField)).selectByVisibleText(reaction);
        selectDropdownByIndex(reactionField, 1, reaction);
    }

    // Hàm chọn dropdown severity dựa trên index
    public void selectSeverityByIndex(By severityField, int index, String text) {
        List<WebElement> dropdowns = driver.findElements(severityField);
        if (index < dropdowns.size()) {
            new Select(dropdowns.get(index)).selectByVisibleText(text);
        } else {
            System.out.println("Không tìm thấy dropdown với index " + index);
        }
    }


    public void selectSeverity(String severity) {
//        new Select(driver.findElement(severityField)).selectByVisibleText(severity);
        selectSeverityByIndex(severityField, 0, severity);
    }

    public void selectEditSeverity(String severity) {
//        new Select(driver.findElement(severityField)).selectByVisibleText(severity);
        selectSeverityByIndex(severityField, 1, severity);
    }

    public void setCommentsField(String comments) {
        driver.findElement(commentsField).clear(); // Xóa nội dung hiện tại nếu có
        driver.findElement(commentsField).sendKeys(comments); // Nhập giá trị mới
    }

    public void saveAllergy() {
        driver.findElement(saveAllergyBtn).click();
    }

    public void clickEditAllergyIcon() {
        driver.findElement(editAllergyIcon).click();
    }

    public void clickDeleteAllergyIcon() {
        driver.findElement(deleteAllergyIcon).click();
    }

    // Methods to interact with Upload Attachments form
    public void uploadFile(String filePath) {
        driver.findElement(uploadFileInput).sendKeys(filePath);
    }

    public void enterCaption(String caption) {
        driver.findElement(captionField).sendKeys(caption);
    }

    public void saveAttachment() {
        driver.findElement(saveAttachmentBtn).click();
    }

    public void clickAttachmentLink() {
        driver.findElement(attachmentLink).click();
    }

    public void deleteAttachment() {
        driver.findElement(deleteAttachmentBtn).click();
    }
}