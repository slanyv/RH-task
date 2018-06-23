package trello.Constants;

/**
 * @author Viktor Slany
 */
public class CardConstants {

    public static final String addNewCard = "//*[@class=\"open-card-composer js-open-card-composer\"]";
    public static final String cardNameField = "//*[@class=\"list-card-composer-textarea js-card-title\"]";
    public static final String saveNewCard = "//*[@class=\"primary confirm mod-compact js-add-card\"]";
    public static final String openCard = "//*[@class=\"list-card-title js-card-name\"]";
    public static final String descriptionField = "//*[@class=\"field js-description-draft\"]";
    public static final String saveDescription = "//*[@class=\"primary confirm mod-submit-edit js-save-edit\"]";
    public static final String addAttachment = "//*[@class=\"js-attach\"]";
    public static final String attachmentFromPC = "//*[@class=\"js-attach-file\"]";
    public static final String attachment = "//*[@class=\"attachment-thumbnail-name js-attachment-name can-edit-attachment-title\"]";
    public static final String createChecklist = "//*[@class=\"button-link js-add-checklist-menu\"]";
    public static final String saveChecklist = "//*[@class=\"primary wide confirm js-add-checklist\"]";
    public static final String checklistItemField = "//*[@class=\"checklist-new-item-text js-new-checklist-item-input\"]";
    public static final String saveChecklistItem = "//*[@class=\"primary confirm mod-submit-edit js-add-checklist-item\"]";
    public static final String commentField = "//*[@class=\"comment-box-input js-new-comment-input\"]";
    public static final String saveComment = "//*[@class=\"primary confirm mod-no-top-bottom-margin js-add-comment\"]";
}
