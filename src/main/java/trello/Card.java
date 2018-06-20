package trello;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.plugin.dom.exception.InvalidStateException;
import utilities.Pause;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * @author Viktor Slany
 */
public class Card {

    private ChromeDriver driver;

    public Card(ChromeDriver driver) {
        this.driver = driver;
    }

    public void addCard(String cardName) {
        driver.findElement(By.xpath("//*[@class=\"open-card-composer js-open-card-composer\"]")).click();
        Pause.until(driver, By.xpath("//*[@class=\"list-card-composer-textarea js-card-title\"]"));
        driver.findElement(By.xpath("//*[@class=\"list-card-composer-textarea js-card-title\"]")).sendKeys(cardName);
        driver.findElement(By.xpath("//*[@class=\"primary confirm mod-compact js-add-card\"]")).click();
    }

    public void openCard() {
        driver.findElement(By.xpath("//*[@class=\"list-card-title js-card-name\"]")).click();
    }

    public void addDescription(String description) {
        Pause.until(driver, By.xpath("//*[@class=\"field js-description-draft\"]"));
        driver.findElement(By.xpath("//*[@class=\"field js-description-draft\"]")).sendKeys(description);
        driver.findElement(By.xpath("//*[@class=\"primary confirm mod-submit-edit js-save-edit\"]")).click();
    }

    public void addAttachment(String fileName) {
        driver.findElement(By.xpath("//*[@class=\"js-attach\"]")).click();
        driver.findElement(By.xpath("//*[@class=\"realfile\"]")).click();


        //put path to your image in a clipboard
        StringSelection ss = new StringSelection(new File("src/main/resources/" + fileName).getAbsolutePath());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        //Pause.until doesn't work here so I had to use Thread.sleep
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new InvalidStateException("Thread was interrupted");
        }

        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new IllegalStateException(e);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Pause.until(driver, 10, By.xpath("//*[@class=\"attachment-thumbnail-details js-open-viewer\"]"));
    }

    public void createChecklist() {
        Pause.until(driver, By.xpath("//*[@class=\"button-link js-add-checklist-menu\"]"));
        driver.findElement(By.xpath("//*[@class=\"button-link js-add-checklist-menu\"]")).click();
        driver.findElement(By.xpath("//*[@class=\"primary wide confirm js-add-checklist\"]")).click();
    }

    public void addItemToChecklist(String itemName) {
        //Pause.until doesn't work here so I had to use Thread.sleep
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new InvalidStateException("Thread was interrupted");
        }
        driver.findElement(By.xpath("//*[@class=\"checklist-new-item-text js-new-checklist-item-input\"]")).sendKeys(itemName);
        driver.findElement(By.xpath("//*[@class=\"primary confirm mod-submit-edit js-add-checklist-item\"]")).click();
    }

    public void addComment(String comment) {
        Pause.until(driver, By.xpath("//*[@class=\"comment-box-input js-new-comment-input\"]"));
        driver.findElement(By.xpath("//*[@class=\"comment-box-input js-new-comment-input\"]")).sendKeys(comment);
        driver.findElement(By.xpath("//*[@class=\"primary confirm mod-no-top-bottom-margin js-add-comment\"]")).click();
    }
}
