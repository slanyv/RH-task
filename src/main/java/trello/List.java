package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
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
        Pause.until(driver, By.xpath("//*[@class=\"placeholder js-open-add-list\"]"));
        driver.findElement(By.xpath("//*[@class=\"placeholder js-open-add-list\"]")).click();
    }

    public void createNewList(String listName) {
        Pause.until(driver, By.xpath("//*[@class=\"list-name-input\"]"));
        driver.findElement(By.xpath("//*[@class=\"list-name-input\"]")).sendKeys(listName);
        driver.findElement(By.xpath("//*[@class=\"primary mod-list-add-button js-save-edit\"]")).click();
        //Pause.until doesn't work here so I had to use Thread.sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Thread was interrupted");
        }
    }
}
