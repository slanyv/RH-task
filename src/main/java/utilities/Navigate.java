package utilities;

import org.openqa.selenium.WebDriver;

public class Navigate {

    public static void to(WebDriver driver, String url) {
        driver.navigate().to(url);
    }
}
