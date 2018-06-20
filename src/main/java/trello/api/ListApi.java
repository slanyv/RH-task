package trello.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author Viktor Slany
 */
public class ListApi {

    /**
     * Create new list in board.
     *
     * @param listName name of the list
     * @param boardId  Id of the board
     * @param apiKey   API key
     * @param token    token
     * @return Id of the created list
     */
    public String createListViaAPI(String listName, String boardId, String apiKey, String token) {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest.post("https://api.trello.com/1/lists?name=" + listName + "&idBoard=" + boardId + "&key=" + apiKey + "&token=" + token)
                    .asJson();
        } catch (UnirestException e) {
            throw new IllegalStateException(e);
        }
        if (response.getStatus() != 200) {
            throw new IllegalStateException("Request failed");
        }
        return response.getBody().getObject().get("id").toString();
    }
}
