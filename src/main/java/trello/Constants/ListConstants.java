package trello.Constants;

import org.openqa.selenium.By;

/**
 * @author Viktor Slany
 */
public class ListConstants {

    public static final String CREATE_NEW_LIST = "//*[contains(@class,\"open-add-list\")]";
    public static final String LIST_NAME_FIELD = "//*[@class=\"list-name-input\"]";
    public static final String SAVE_LIST = "//*[contains(@class,\"save-edit\")]";

    public static final String API_LIST_NAME = "new_list_created_via_api";
    public static final String LIST_NAME = "My test list";

    public static By getCardByName(String cardName) {
        return By.xpath("//*[contains(@class,\"list-card\") and text()=\"" + cardName + "\"]");
    }
}