package trello.Constants;

import org.openqa.selenium.By;

/**
 * @author Viktor Slany
 */
public class CardConstants {

    public static final String ADD_NEW_CARD = "//*[contains(@class,\"open-card-composer\")]";
    public static final String CARD_NAME_FIELD = "//*[contains(@class,\"textarea\")]";
    public static final String SAVE_NEW_CARD = "//*[contains(@class,\"js-add-card\")]";

    public static final String DESCRIPTION_FIELD = "//*[contains(@class,\"description-draft\")]";
    public static final String SAVE_DESCRIPTION = "//*[contains(@class,\"submit-edit\")]";
    public static final String DESCRIPTION = "//*[contains(@class,\"current markeddown\")]//p";

    public static final String ADD_ATTACHMENT = "//*[@class=\"js-attach\"]";
    public static final String ATTACHMENT_FROM_PC = "//*[@class=\"js-attach-file\"]";
    public static final String ATTACHMENT = "//*[contains(@class,\"attachment-title\")]";
    public static final String ATTACHMENT_PATH = "src/main/resources/";

    public static final String CREATE_CHECKLIST = "//*[contains(@class,\"add-checklist\")]";
    public static final String SAVE_CHECKLIST = "//*[contains(@class,\"confirm js-add-checklist\")]";

    public static final String CHECKLIST_ITEM_FIELD = "//*[contains(@class,\"checklist-new-item-text\")]";
    public static final String SAVE_CHECKLIST_ITEM = "//*[contains(@class,\"add-checklist-item\")]";

    public static final String COMMENT_FIELD = "//*[contains(@class,\"comment-input\")]";
    public static final String SAVE_COMMENT = "//*[contains(@class,\"js-add-comment\")]";

    public static final String CARD_NAME = "My test card";
    public static final String DESCRIPTION_TEXT = "Some description";
    public static final String ATTACHMENT_FILE_NAME = "manWithPipe.gif";
    public static final String FIRST_ITEM = "First item";
    public static final String SECOND_ITEM = "Second item";
    public static final String COMMENT_TEXT = "Some comment";

    public static By getAttachmentByName(String attachmentName) {
        return By.xpath("//*[contains(@class,\"attachment-thumbnail-name\") and text()=\"" + attachmentName + "\"]");
    }

    public static By getChecklistItemByName(String itemName) {
        return By.xpath("//*[contains(@class,\"checklist-item\") and text()=\"" + itemName + "\"]");
    }

    public static By getCommentByName(String commentName) {
        return By.xpath("//*[contains(@class,\"card-detail\")]//*[contains(@class,\"current-comment\") and .//text()=\"" + commentName + "\"]");
    }
}