import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.plugin.dom.exception.InvalidStateException;
import trello.Board;
import trello.Card;
import trello.List;
import trello.api.BoardApi;
import trello.api.ListApi;
import utilities.Pause;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.hamcrest.core.Is.is;

/**
 * @author Viktor Slany
 */
public class TrelloTests {
    private ChromeDriver driver;
    private Board board;
    private BoardApi boardApi;
    private List list;
    private ListApi listApi;
    private Card card;
    private final String propertiesFileName = "UserInfo.properties";
    private String apiKey;
    private String token;

    @Before
    public void setUp() throws IOException {
        String username;
        String password;
        Properties properties = new Properties();
        try (InputStream fis = TrelloTests.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
            properties.load(fis);
            apiKey = properties.getProperty("apiKey");
            token = properties.getProperty("token");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        }

        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        board = new Board(driver);
        boardApi = new BoardApi();
        list = new List(driver);
        listApi = new ListApi();
        card = new Card(driver);
        board.login(username, password);
        Pause.until(driver, By.xpath("//*[@class=\"header-btn-icon icon-lg icon-board light\"]"));
    }

    @Test
    public void createNewBoardTest() {
        String boardName = "My test board";

        board.openNewBoardDialog();
        board.createNewBoard(boardName);

        //  Pause.until(driver, 20, By.xpath("//*[@class=\"board-header-btn board-header-btn-name js-rename-board\"]//span"));
        Assert.assertThat(driver.findElement(By.xpath("//*[@class=\"board-header-btn board-header-btn-name js-rename-board\"]//span")).getText(), is(boardName));
    }

    @Test
    public void addListToExistingBoardTest() {
        String boardName = "new_board_created_via_api";
        String listName = "My test list";
        String boardId = boardApi.createBoardViaAPI(boardName, apiKey, token);
        String boardUrl = boardApi.getBoardUrlFromId(boardId, apiKey, token);

        driver.navigate().to(boardUrl);
        list.openNewList();
        list.createNewList(listName);

        Assert.assertTrue(driver.findElement(By.xpath("//*[@class=\"list js-list-content\"][.//*[text()=\"" + listName + "\"]]")).isDisplayed());
    }

    @Test
    public void addCardToExistingListTest() {
        String boardName = "new_board_created_via_api";
        String listName = "new_list_created_via_api";
        String cardName = "New card name";
        String description = "Some description";
        String fileName = "manWithPipe.gif";
        String firstItem = "First item";
        String secondItem = "Second item";
        String comment = "Some comment";

        String boardId = boardApi.createBoardViaAPI(boardName, apiKey, token);
        String boardUrl = boardApi.getBoardUrlFromId(boardId, apiKey, token);
        listApi.createListViaAPI(listName, boardId, apiKey, token);

        driver.navigate().to(boardUrl);
        Pause.until(driver, By.xpath("//*[@class=\"placeholder js-open-add-list\"]"));
        card.addCard(cardName);
        card.openCard();
        Pause.until(driver, By.xpath("//*[@class=\"primary confirm mod-no-top-bottom-margin js-add-comment\"]"));
        card.addDescription(description);
        card.addAttachment(fileName);
        Pause.until(driver, 10, By.xpath("//*[@class=\"attachment-thumbnail-details js-open-viewer\"]"));
        card.createChecklist();
        Pause.until(driver, By.xpath("//*[@class=\"current hide-on-edit\"]"));
        card.addItemToChecklist(firstItem);
        card.addItemToChecklist(secondItem);
        card.addComment(comment);

        //Pause.until doesn't work here so I had to use Thread.sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new InvalidStateException("Thread was interrupted");
        }
        Assert.assertThat(driver.findElement(By.xpath("//*[@class=\"current markeddown hide-on-edit js-card-desc js-show-with-desc\"]//p")).getText(), is(description));
        Assert.assertThat(driver.findElement(By.xpath("//*[@class=\"attachment-thumbnail-name js-attachment-name can-edit-attachment-title\"]")).getText(), is(fileName));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class=\"checklist-item-details-text markeddown js-checkitem-name\" and text()=\"" + firstItem + "\"]")).getText().contains(firstItem));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class=\"checklist-item-details-text markeddown js-checkitem-name\" and text()=\"" + secondItem + "\"]")).getText().contains(secondItem));
        Assert.assertThat(driver.findElement(By.xpath("//*[@class=\"card-detail-window u-clearfix\"]//*[@class=\"current-comment js-friendly-links js-open-card\"]")).getText(), is(comment));
    }

    @Test
    public void completeFlowTest() {
        String boardName = "My test board";
        String listName = "My test list";
        String cardName = "My test card";
        String description = "Some description";
        String fileName = "manWithPipe.gif";
        String firstItem = "First item";
        String secondItem = "Second item";
        String comment = "Some comment";

        board.openNewBoardDialog();
        board.createNewBoard(boardName);

        //Pause.until doesn't work here so I had to use Thread.sleep
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new InvalidStateException("Thread was interrupted");
        }
        Assert.assertThat(driver.findElement(By.xpath("//*[@class=\"board-header-btn board-header-btn-name js-rename-board\"]//span")).getText(), is(boardName));

        list.createNewList(listName);

        Assert.assertTrue(driver.findElement(By.xpath("//*[@class=\"list js-list-content\"][.//*[text()=\"" + listName + "\"]]")).isDisplayed());

        //Pause.until doesn't work here so I had to use Thread.sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new InvalidStateException("Thread was interrupted");
        }
        card.addCard(cardName);
        card.openCard();
        card.addDescription(description);
        card.addAttachment(fileName);
        card.createChecklist();
        card.addItemToChecklist(firstItem);
        card.addItemToChecklist(secondItem);
        card.addComment(comment);

        Pause.until(driver, By.xpath("//*[@class=\"card-detail-window u-clearfix\"]//*[@class=\"current-comment js-friendly-links js-open-card\"]"));
        Assert.assertThat(driver.findElement(By.xpath("//*[@class=\"current markeddown hide-on-edit js-card-desc js-show-with-desc\"]//p")).getText(), is(description));
        Assert.assertThat(driver.findElement(By.xpath("//*[@class=\"attachment-thumbnail-name js-attachment-name can-edit-attachment-title\"]")).getText(), is(fileName));
        Assert.assertThat(driver.findElement(By.xpath("//*[@class=\"checklist-item-details-text markeddown js-checkitem-name\" and text()=\"" + firstItem + "\"]")).getText(), is(firstItem));
        Assert.assertThat(driver.findElement(By.xpath("//*[@class=\"checklist-item-details-text markeddown js-checkitem-name\" and text()=\"" + secondItem + "\"]")).getText(), is(secondItem));
        Assert.assertThat(driver.findElement(By.xpath("//*[@class=\"card-detail-window u-clearfix\"]//*[@class=\"current-comment js-friendly-links js-open-card\"]")).getText(), is(comment));
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
