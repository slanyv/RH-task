package trello.Constants;

import org.openqa.selenium.By;

/**
 * @author Viktor Slany
 */
public class BoardConstants {

    public static final String BOARD_DIALOG = "//*[contains(@class,\"boards-menu\")]";
    public static final String CREATE_NEW_BOARD_FROM_DIALOG = "//*[contains(@class,\"add-board\")]";
    public static final String BOARD_NAME_FIELD = "//*[@class=\"subtle-input\"]";
    public static final String BOARD_SAVE = "//*[@class=\"primary\"]";
    public static final String BOARD_HEADER_NAME = "//*[contains(@class,\"rename-board\")]//span";
    public static final String BOARD_BACKGROUND_BUTTON = "//*[contains(@class,\"is-photo selected\")]";

    public static final String API_BOARD_NAME = "new_board_created_via_api";
    public static final String BOARD_NAME = "My test board";

    public static By getListByName(String listName) {
        return By.xpath("//*[contains(@class,\"list-content\")][.//*[text()=\"" + listName + "\"]]");
    }
}