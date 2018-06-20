package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Viktor Slany
 */
public class Pause {

    private static final int pauseTime = 5;

    /**
     * Wait until WebElement is loaded.
     *
     * @param driver  WebDriver
     * @param seconds Maximal wait time
     * @param xPath   xPath of the WebElement
     */
    public static void until(WebDriver driver, int seconds, By xPath) {
        new WebDriverWait(driver, seconds).until(
                webDriver -> (webDriver.findElement(xPath).isDisplayed()));
    }

    /**
     * Wait until WebElement is loaded with maximum waiting time 5 seconds.
     *
     * @param driver WebDriver
     * @param xPath  xPath of the WebElement
     */
    public static void until(WebDriver driver, By xPath) {
        until(driver, pauseTime, xPath);
    }
}
