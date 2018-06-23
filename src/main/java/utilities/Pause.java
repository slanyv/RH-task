package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Viktor Slany
 */
public class Pause {

    private static final int PAUSE_TIME = 5;

    /**
     * Wait untilWithXPath WebElement is loaded.
     *
     * @param driver     WebDriver
     * @param seconds    Maximal wait time
     * @param webElement WebElement
     */
    public static void untilWithElement(WebDriver driver, int seconds, WebElement webElement) {
        new WebDriverWait(driver, seconds).until(
                webDriver -> (webElement.isDisplayed()));
    }

    /**
     * Wait untilWithXPath WebElement is loaded.
     *
     * @param driver     WebDriver
     * @param webElement WebElement
     */
    public static void untilWithElement(WebDriver driver, WebElement webElement) {
        untilWithElement(driver, PAUSE_TIME, webElement);
    }

    /**
     * Wait untilWithXPath WebElement is loaded.
     *
     * @param driver  WebDriver
     * @param seconds Maximal wait time
     * @param xPath   xPath of the WebElement
     */
    public static void untilWithXPath(WebDriver driver, int seconds, By xPath) {
        new WebDriverWait(driver, seconds).until(
                webDriver -> (webDriver.findElement(xPath).isDisplayed()));
    }

    /**
     * Wait untilWithXPath WebElement is loaded with maximum waiting time 5 seconds.
     *
     * @param driver WebDriver
     * @param xPath  xPath of the WebElement
     */
    public static void untilWithXPath(WebDriver driver, By xPath) {
        untilWithXPath(driver, PAUSE_TIME, xPath);
    }
}
