package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import trello.Constants.ListConstants;

/**
 * @author Viktor Slany
 */
public class List {

    private ChromeDriver driver;

    public List(ChromeDriver driver) {
        this.driver = driver;
    }

    public void openNewList() {
        //Pause.untilWithXPath(driver, By.xpath(ListConstants.CREATE_NEW_LIST));
        driver.findElement(By.xpath(ListConstants.CREATE_NEW_LIST)).click();
    }

    public void createNewList(String listName) {
        //Pause.untilWithXPath(driver, By.xpath(ListConstants.LIST_NAME_FIELD));
        driver.findElement(By.xpath(ListConstants.LIST_NAME_FIELD)).sendKeys(listName);
        driver.findElement(By.xpath(ListConstants.SAVE_LIST)).click();
        //Pause.untilWithXPath(driver, By.xpath(ListConstants.LIST));
    }

    public WebElement getCardByText(String cardName) {
        return driver.findElement(By.xpath("//*[contains(@class,\"list-card\") and text()=\"" + cardName + "\"]"));
    }
}
