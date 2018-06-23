package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
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
        driver.navigate().to("https://trello.com/");
        Pause.until(driver, By.xpath("//*[@href=\"/login\"]"));
        driver.findElement(By.xpath("//*[@href=\"/login\"]")).click();
        driver.findElement(By.id("user")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login")).click();
        Pause.until(driver, By.xpath("//*[@class=\"header-btn-icon icon-lg icon-board light\"]"));
    }
}
