package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import trello.Constants.CardConstants;
import trello.Constants.ListConstants;
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
        Pause.untilWithXPath(driver, By.xpath(CardConstants.CARD_NAME_FIELD));
        driver.findElement(By.xpath(CardConstants.CARD_NAME_FIELD)).sendKeys(cardName);
        driver.findElement(By.xpath(CardConstants.SAVE_NEW_CARD)).click();
    }

    public void openCard(String cardName) {
        driver.findElement(ListConstants.getCardByName(cardName)).click();
    }

    public void addDescription(String description) {
        Pause.untilWithXPath(driver, By.xpath(CardConstants.DESCRIPTION_FIELD));
        driver.findElement(By.xpath(CardConstants.DESCRIPTION_FIELD)).sendKeys(description);
        driver.findElement(By.xpath(CardConstants.SAVE_DESCRIPTION)).click();
    }

    public WebElement getDescription() {
        return driver.findElement(By.xpath(CardConstants.DESCRIPTION));
    }

    public void addAttachment(String fileName) {
        driver.findElement(By.xpath(CardConstants.ADD_ATTACHMENT)).click();
        String filePath = new File(CardConstants.ATTACHMENT_PATH + fileName).getAbsolutePath();
        driver.findElement(By.xpath(CardConstants.ATTACHMENT_FROM_PC)).sendKeys(filePath);
        Pause.untilWithXPath(driver, 10, By.xpath(CardConstants.ATTACHMENT));
    }

    public WebElement getAttachmentByText(String attachmentName) {
        return driver.findElement(CardConstants.getAttachmentByName(attachmentName));
    }

    public void createChecklist() {
        Pause.untilWithXPath(driver, By.xpath(CardConstants.CREATE_CHECKLIST));
        driver.findElement(By.xpath(CardConstants.CREATE_CHECKLIST)).click();
        driver.findElement(By.xpath(CardConstants.SAVE_CHECKLIST)).click();
    }

    public void addItemToChecklist(String itemName) {
        driver.findElement(By.xpath(CardConstants.CHECKLIST_ITEM_FIELD)).sendKeys(itemName);
        driver.findElement(By.xpath(CardConstants.SAVE_CHECKLIST_ITEM)).click();
        Pause.untilWithElement(driver, getItemFromChecklistByText(itemName));
    }

    public WebElement getItemFromChecklistByText(String itemName) {
        return driver.findElement(CardConstants.getChecklistItemByName(itemName));
    }

    public void addComment(String comment) {
        Pause.untilWithXPath(driver, By.xpath(CardConstants.COMMENT_FIELD));
        driver.findElement(By.xpath(CardConstants.COMMENT_FIELD)).sendKeys(comment);
        driver.findElement(By.xpath(CardConstants.SAVE_COMMENT)).click();
        Pause.untilWithElement(driver, getCommentByText(comment));
    }

    public WebElement getCommentByText(String commentName) {
        return driver.findElement(CardConstants.getCommentByName(commentName));
    }
}
