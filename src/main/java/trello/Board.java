package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.Pause;

/**
 * @author Viktor Slany
 */
public class Board {

    private ChromeDriver driver;

    public Board(ChromeDriver driver) {
        this.driver = driver;
    }


    public void login(String userName, String password) {
        driver.navigate().to("https://trello.com/");
        Pause.until(driver, By.xpath("//*[@href=\"/login\"]"));
        driver.findElement(By.xpath("//*[@href=\"/login\"]")).click();
        driver.findElement(By.id("user")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login")).click();
    }

    public void openNewBoardDialog() {
        driver.findElement(By.xpath("//*[@class=\"header-btn-icon icon-lg icon-board light\"]")).click();
        driver.findElement(By.xpath("//*[@class=\"quiet-button js-add-board\"]")).click();
    }

    public void createNewBoard(String boardName) {
        //Pause.until doesn't work here so I had to use Thread.sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Thread was interrupted");
        }
        driver.findElement(By.xpath("//*[@class=\"subtle-input\"]")).sendKeys(boardName);
        driver.findElement(By.xpath("//*[@class=\"primary\"]")).click();
        Pause.until(driver, 20, By.xpath("//*[@class=\"board-header-btn board-header-btn-name js-rename-board\"]//span"));
    }
}
