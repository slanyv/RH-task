package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import trello.Constants.BoardConstants;

/**
 * @author Viktor Slany
 */
public class Board {

    private ChromeDriver driver;

    public Board(ChromeDriver driver) {
        this.driver = driver;
    }

    public void openNewBoardDialog() {
        driver.findElement(By.xpath(BoardConstants.BOARD_DIALOG)).click();
        driver.findElement(By.xpath(BoardConstants.CREATE_NEW_BOARD_FROM_DIALOG)).click();
    }

    public void createNewBoard(String boardName) {
        //Pause.until doesn't work here so I had to use Thread.sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Thread was interrupted");
        }
        driver.findElement(By.xpath(BoardConstants.BOARD_NAME_FIELD)).sendKeys(boardName);
        driver.findElement(By.xpath(BoardConstants.BOARD_SAVE)).click();
        //Pause.until doesn't work here so I had to use Thread.sleep
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Thread was interrupted");
        }
    }

    public WebElement getBoardHeader() {
        return driver.findElement(By.xpath(BoardConstants.BOARD_NAME));
    }

    public WebElement getListByText(String listName) {
        return driver.findElement(By.xpath("//*[@class=\"list js-list-content\"][.//*[text()=\"" + listName + "\"]]"));
    }

    public void goTo(String url) {
        driver.navigate().to(url);
    }
}
