package trello.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author Viktor Slany
 */
public class BoardApi {

    /**
     * Create new board via API.
     *
     * @param boardName name of the board
     * @param apiKey    API key
     * @param token     token
     * @return Id of the created board
     */
    public String createBoardViaAPI(String boardName, String apiKey, String token) {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest.post("https://api.trello.com/1/boards/" +
                    "?name=" + boardName + "&key=" + apiKey + "&token=" + token)
                    .asJson();
        } catch (UnirestException e) {
            throw new IllegalStateException(e);
        }
        if (response.getStatus() != 200) {
            throw new IllegalStateException("Request failed");
        }
        return response.getBody().getObject().get("id").toString();
    }

    /**
     * Find URL of the board.
     *
     * @param boardId Id of the board
     * @param apiKey  API key
     * @param token   token
     * @return Board URL form board Id
     */
    public String getBoardUrlFromId(String boardId, String apiKey, String token) {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest.get("https://api.trello.com/1/boards/" +
                    boardId + "?key=" + apiKey + "&token=" + token)
                    .asJson();
        } catch (UnirestException e) {
            throw new IllegalStateException(e);
        }
        return response.getBody().getObject().get("shortUrl").toString();
    }
}
