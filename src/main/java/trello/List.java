package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import trello.Constants.ListConstants;
import utilities.Pause;

/**
 * @author Viktor Slany
 */
public class List {

    private ChromeDriver driver;

    public List(ChromeDriver driver) {
        this.driver = driver;
    }

    public void openNewList() {
        Pause.until(driver, By.xpath(ListConstants.CREATE_NEW_LIST));
        driver.findElement(By.xpath(ListConstants.CREATE_NEW_LIST)).click();
    }

    public void createNewList(String listName) {
        Pause.until(driver, By.xpath(ListConstants.LIST_NAME_FIELD));
        driver.findElement(By.xpath(ListConstants.LIST_NAME_FIELD)).sendKeys(listName);
        driver.findElement(By.xpath(ListConstants.SAVE_LIST)).click();
        //Pause.until doesn't work here so I had to use Thread.sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Thread was interrupted");
        }
    }

    public WebElement getCardByText(String cardName) {
        return driver.findElement(By.xpath("//*[@class=\"list-card-title js-card-name\" and text()=\"" + cardName + "\"]"));
    }
}
