package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import trello.Constants.BoardConstants;
import trello.Constants.LoginConstants;
import utilities.Pause;

/**
 * @author Viktor Slany
 */
public class Login {

    private ChromeDriver driver;

    public Login(ChromeDriver driver) {
        this.driver = driver;
    }

    public void login(String userName, String password) {
        driver.navigate().to(LoginConstants.HOME_PAGE_URL);
        driver.findElement(By.xpath(LoginConstants.LOGIN_BUTTON)).click();
        driver.findElement(By.id(LoginConstants.USER)).sendKeys(userName);
        driver.findElement(By.id(LoginConstants.PASSWORD)).sendKeys(password);
        driver.findElement(By.id(LoginConstants.SUBMIT_LOGGIN)).click();
        Pause.untilWithXPath(driver, By.xpath(BoardConstants.BOARD_DIALOG));
    }
}
