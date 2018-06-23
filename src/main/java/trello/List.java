package trello;

import org.openqa.selenium.By;
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
        Pause.until(driver, By.xpath(ListConstants.createNewList));
        driver.findElement(By.xpath(ListConstants.createNewList)).click();
    }

    public void createNewList(String listName) {
        Pause.until(driver, By.xpath(ListConstants.listNameField));
        driver.findElement(By.xpath(ListConstants.listNameField)).sendKeys(listName);
        driver.findElement(By.xpath(ListConstants.saveList)).click();
        //Pause.until doesn't work here so I had to use Thread.sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Thread was interrupted");
        }
    }
}
