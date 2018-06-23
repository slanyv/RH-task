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
        driver.findElement(By.xpath(CardConstants.ADD_NEW_CARD)).click();
        Pause.until(driver, By.xpath(CardConstants.CARD_NAME_FIELD));
        driver.findElement(By.xpath(CardConstants.CARD_NAME_FIELD)).sendKeys(cardName);
        driver.findElement(By.xpath(CardConstants.SAVE_NEW_CARD)).click();
    }

    public void openCard() {
        driver.findElement(By.xpath(CardConstants.OPEN_CARD)).click();
    }

    public void addDescription(String description) {
        Pause.until(driver, By.xpath(CardConstants.DESCRIPTION_FIELD));
        driver.findElement(By.xpath(CardConstants.DESCRIPTION_FIELD)).sendKeys(description);
        driver.findElement(By.xpath(CardConstants.SAVE_DESCRIPTION)).click();
    }

    public WebElement getDescription() {
        return driver.findElement(By.xpath("//*[@class=\"current markeddown hide-on-edit js-card-desc js-show-with-desc\"]//p"));
    }

    public void addAttachment(String fileName) {
        driver.findElement(By.xpath(CardConstants.ADD_ATTACHMENT)).click();
        String filePath = new File("src/main/resources/" + fileName).getAbsolutePath();
        driver.findElement(By.xpath(CardConstants.ATTACHMENT_FROM_PC)).sendKeys(filePath);
        Pause.until(driver, 10, By.xpath(CardConstants.ATTACHMENT));
    }

    public WebElement getAttachmentByText(String attachmentName) {
        return driver.findElement(By.xpath("//*[@class=\"ATTACHMENT-thumbnail-name js-ATTACHMENT-name can-edit-ATTACHMENT-title\" and text()=\"" + attachmentName + "\"]"));
    }

    public void createChecklist() {
        Pause.until(driver, By.xpath(CardConstants.CREATE_CHECKLIST));
        driver.findElement(By.xpath(CardConstants.CREATE_CHECKLIST)).click();
        driver.findElement(By.xpath(CardConstants.SAVE_CHECKLIST)).click();
    }

    public void addItemToChecklist(String itemName) {
        //Pause.until doesn't work here so I had to use Thread.sleep
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Thread was interrupted");
        }
        driver.findElement(By.xpath(CardConstants.CHECKLIST_ITEM_FIELD)).sendKeys(itemName);
        driver.findElement(By.xpath(CardConstants.SAVE_CHECKLIST_ITEM)).click();
    }

    public WebElement getItemFromChecklistByText(String itemName) {
        return driver.findElement(By.xpath("//*[@class=\"checklist-item-details-text markeddown js-checkitem-name\" and text()=\"" + itemName + "\"]"));
    }

    public void addComment(String comment) {
        Pause.until(driver, By.xpath(CardConstants.COMMENT_FIELD));
        driver.findElement(By.xpath(CardConstants.COMMENT_FIELD)).sendKeys(comment);
        driver.findElement(By.xpath(CardConstants.SAVE_COMMENT)).click();
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
