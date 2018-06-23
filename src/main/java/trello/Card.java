package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import trello.Constants.CardConstants;
import utilities.Pause;

import java.io.File;

/**
 * @author Viktor Slany
 */
public class Card {

    private ChromeDriver driver;

    public Card(ChromeDriver driver) {
        this.driver = driver;
    }

    public void addCard(String cardName) {
        driver.findElement(By.xpath(CardConstants.addNewCard)).click();
        Pause.until(driver, By.xpath(CardConstants.cardNameField));
        driver.findElement(By.xpath(CardConstants.cardNameField)).sendKeys(cardName);
        driver.findElement(By.xpath(CardConstants.saveNewCard)).click();
    }

    public void openCard() {
        driver.findElement(By.xpath(CardConstants.openCard)).click();
    }

    public void addDescription(String description) {
        Pause.until(driver, By.xpath(CardConstants.descriptionField));
        driver.findElement(By.xpath(CardConstants.descriptionField)).sendKeys(description);
        driver.findElement(By.xpath(CardConstants.saveDescription)).click();
    }

    public WebElement getDescription() {
        return driver.findElement(By.xpath("//*[@class=\"current markeddown hide-on-edit js-card-desc js-show-with-desc\"]//p"));
    }

    public void addAttachment(String fileName) {
        driver.findElement(By.xpath(CardConstants.addAttachment)).click();
        String filePath = new File("src/main/resources/" + fileName).getAbsolutePath();
        driver.findElement(By.xpath(CardConstants.attachmentFromPC)).sendKeys(filePath);
        Pause.until(driver, 10, By.xpath(CardConstants.attachment));
    }

    public WebElement getAttachmentByText(String attachmentName) {
        return driver.findElement(By.xpath("//*[@class=\"attachment-thumbnail-name js-attachment-name can-edit-attachment-title\" and text()=\"" + attachmentName + "\"]"));
    }

    public void createChecklist() {
        Pause.until(driver, By.xpath(CardConstants.createChecklist));
        driver.findElement(By.xpath(CardConstants.createChecklist)).click();
        driver.findElement(By.xpath(CardConstants.saveChecklist)).click();
    }

    public void addItemToChecklist(String itemName) {
        //Pause.until doesn't work here so I had to use Thread.sleep
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Thread was interrupted");
        }
        driver.findElement(By.xpath(CardConstants.checklistItemField)).sendKeys(itemName);
        driver.findElement(By.xpath(CardConstants.saveChecklistItem)).click();
    }

    public WebElement getItemFromChecklistByText(String itemName) {
        return driver.findElement(By.xpath("//*[@class=\"checklist-item-details-text markeddown js-checkitem-name\" and text()=\"" + itemName + "\"]"));
    }

    public void addComment(String comment) {
        Pause.until(driver, By.xpath(CardConstants.commentField));
        driver.findElement(By.xpath(CardConstants.commentField)).sendKeys(comment);
        driver.findElement(By.xpath(CardConstants.saveComment)).click();
        //Pause.until doesn't work here so I had to use Thread.sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Thread was interrupted");
        }
    }

    public WebElement getCommentByText(String commentName) {
        return driver.findElement(By.xpath("//*[@class=\"card-detail-window u-clearfix\"]//*[@class=\"current-comment js-friendly-links js-open-card\" and .//text()=\"" + commentName + "\"]"));
    }
}
