import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import trello.Board;
import trello.Card;
import trello.Constants.BoardConstants;
import trello.Constants.CardConstants;
import trello.Constants.ListConstants;
import trello.List;
import trello.Login;
import trello.api.BoardApi;
import trello.api.ListApi;

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

        Login login = new Login(driver);
        login.login(username, password);
    }

    @Test
    public void createNewBoardTest() {
        board.openNewBoardDialog();
        board.createNewBoard(BoardConstants.BOARD_NAME);

        Assert.assertThat(board.getBoardHeader().getText(), is(BoardConstants.BOARD_NAME));
    }

    @Test
    public void addListToExistingBoardTest() {
        String boardId = boardApi.createBoardViaAPI(BoardConstants.API_BOARD_NAME, apiKey, token);
        String boardUrl = boardApi.getBoardUrlFromId(boardId, apiKey, token);

        board.goTo(boardUrl);
        list.openNewList();
        list.createNewList(ListConstants.LIST_NAME);

        Assert.assertTrue(board.getListByText(ListConstants.LIST_NAME).isDisplayed());
    }

    @Test
    public void addCardToExistingListTest() {
        String boardId = boardApi.createBoardViaAPI(BoardConstants.API_BOARD_NAME, apiKey, token);
        String boardUrl = boardApi.getBoardUrlFromId(boardId, apiKey, token);
        listApi.createListViaAPI(ListConstants.API_LIST_NAME, boardId, apiKey, token);

        board.goTo(boardUrl);
        card.addCard(CardConstants.CARD_NAME);

        Assert.assertNotNull(list.getCardByText(CardConstants.CARD_NAME));

        card.openCard();
        card.addDescription(CardConstants.DESCRIPTION);
        card.addAttachment(CardConstants.ATTACHMENT_FILE_NAME);
        card.createChecklist();
        card.addItemToChecklist(CardConstants.FIRST_ITEM);
        card.addItemToChecklist(CardConstants.SECOND_ITEM);
        card.addComment(CardConstants.COMMENT_TEXT);

        Assert.assertThat(card.getDescription().getText(), is(CardConstants.DESCRIPTION));
        Assert.assertNotNull(card.getAttachmentByText(CardConstants.ATTACHMENT_FILE_NAME));
        Assert.assertNotNull(card.getItemFromChecklistByText(CardConstants.FIRST_ITEM));
        Assert.assertNotNull(card.getItemFromChecklistByText(CardConstants.SECOND_ITEM));
        Assert.assertNotNull(card.getCommentByText(CardConstants.COMMENT_TEXT));
    }

    @Test
    public void completeFlowTest() {
        board.openNewBoardDialog();
        board.createNewBoard(BoardConstants.BOARD_NAME);

        Assert.assertThat(board.getBoardHeader().getText(), is(BoardConstants.BOARD_NAME));

        list.createNewList(ListConstants.LIST_NAME);

        Assert.assertTrue(board.getListByText(ListConstants.LIST_NAME).isDisplayed());

        card.addCard(CardConstants.CARD_NAME);

        Assert.assertNotNull(list.getCardByText(CardConstants.CARD_NAME));

        card.openCard();
        card.addDescription(CardConstants.DESCRIPTION);
        card.addAttachment(CardConstants.ATTACHMENT_FILE_NAME);
        card.createChecklist();
        card.addItemToChecklist(CardConstants.FIRST_ITEM);
        card.addItemToChecklist(CardConstants.SECOND_ITEM);
        card.addComment(CardConstants.COMMENT_TEXT);

        Assert.assertThat(card.getDescription().getText(), is(CardConstants.DESCRIPTION));
        Assert.assertNotNull(card.getAttachmentByText(CardConstants.ATTACHMENT_FILE_NAME));
        Assert.assertNotNull(card.getItemFromChecklistByText(CardConstants.FIRST_ITEM));
        Assert.assertNotNull(card.getItemFromChecklistByText(CardConstants.SECOND_ITEM));
        Assert.assertNotNull(card.getCommentByText(CardConstants.COMMENT_TEXT));
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
