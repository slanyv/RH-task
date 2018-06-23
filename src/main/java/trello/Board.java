package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import trello.Constants.BoardConstants;
import utilities.Pause;

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
        Pause.untilWithXPath(driver, By.xpath(BoardConstants.BOARD_BACKGROUND_BUTTON));
        driver.findElement(By.xpath(BoardConstants.BOARD_NAME_FIELD)).sendKeys(boardName);
        driver.findElement(By.xpath(BoardConstants.BOARD_SAVE)).click();
        Pause.untilWithXPath(driver,10, By.xpath(BoardConstants.BOARD_HEADER_NAME));
    }

    public WebElement getBoardHeader() {
        return driver.findElement(By.xpath(BoardConstants.BOARD_HEADER_NAME));
    }

    public WebElement getListByText(String listName) {
        return driver.findElement(By.xpath("//*[@class=\"list js-list-content\"][.//*[text()=\"" + listName + "\"]]"));
    }

    public void goTo(String url) {
        driver.navigate().to(url);
    }
}
