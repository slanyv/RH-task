import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import trello.Board;
import trello.Card;
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
    private static final String API_BOARD_NAME = "new_board_created_via_api";
    private static final String API_LIST_NAME = "new_list_created_via_api";
    private static final String BOARD_NAME = "My test board";
    private static final String LIST_NAME = "My test list";
    private static final String CARD_NAME = "My test card";
    private static final String DESCRIPTION = "Some description";
    private static final String FILE_NAME = "manWithPipe.gif";
    private static final String FIRST_ITEM = "First item";
    private static final String SECOND_ITEM = "Second item";
    private static final String COMMENT = "Some comment";

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
        board.createNewBoard(BOARD_NAME);

        Assert.assertThat(board.getBoardHeader().getText(), is(BOARD_NAME));
    }

    @Test
    public void addListToExistingBoardTest() {
        String boardId = boardApi.createBoardViaAPI(API_BOARD_NAME, apiKey, token);
        String boardUrl = boardApi.getBoardUrlFromId(boardId, apiKey, token);

        board.goTo(boardUrl);
        list.openNewList();
        list.createNewList(LIST_NAME);

        Assert.assertTrue(board.getListByText(LIST_NAME).isDisplayed());
    }

    @Test
    public void addCardToExistingListTest() {
        String boardId = boardApi.createBoardViaAPI(API_BOARD_NAME, apiKey, token);
        String boardUrl = boardApi.getBoardUrlFromId(boardId, apiKey, token);
        listApi.createListViaAPI(API_LIST_NAME, boardId, apiKey, token);

        board.goTo(boardUrl);
        card.addCard(CARD_NAME);

        Assert.assertNotNull(list.getCardByText(CARD_NAME));

        card.openCard();
        card.addDescription(DESCRIPTION);
        card.addAttachment(FILE_NAME);
        card.createChecklist();
        card.addItemToChecklist(FIRST_ITEM);
        card.addItemToChecklist(SECOND_ITEM);
        card.addComment(COMMENT);

        Assert.assertThat(card.getDescription().getText(), is(DESCRIPTION));
        Assert.assertNotNull(card.getAttachmentByText(FILE_NAME));
        Assert.assertNotNull(card.getItemFromChecklistByText(FIRST_ITEM));
        Assert.assertNotNull(card.getItemFromChecklistByText(SECOND_ITEM));
        Assert.assertNotNull(card.getCommentByText(COMMENT));
    }

    @Test
    public void completeFlowTest() {
        board.openNewBoardDialog();
        board.createNewBoard(BOARD_NAME);

        Assert.assertThat(board.getBoardHeader().getText(), is(BOARD_NAME));

        list.createNewList(LIST_NAME);

        Assert.assertTrue(board.getListByText(LIST_NAME).isDisplayed());

        card.addCard(CARD_NAME);

        Assert.assertNotNull(list.getCardByText(CARD_NAME));

        card.openCard();
        card.addDescription(DESCRIPTION);
        card.addAttachment(FILE_NAME);
        card.createChecklist();
        card.addItemToChecklist(FIRST_ITEM);
        card.addItemToChecklist(SECOND_ITEM);
        card.addComment(COMMENT);

        Assert.assertThat(card.getDescription().getText(), is(DESCRIPTION));
        Assert.assertNotNull(card.getAttachmentByText(FILE_NAME));
        Assert.assertNotNull(card.getItemFromChecklistByText(FIRST_ITEM));
        Assert.assertNotNull(card.getItemFromChecklistByText(SECOND_ITEM));
        Assert.assertNotNull(card.getCommentByText(COMMENT));
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
