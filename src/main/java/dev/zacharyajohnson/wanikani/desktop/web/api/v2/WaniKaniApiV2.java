package dev.zacharyajohnson.wanikani.desktop.web.api.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.zacharyajohnson.wanikani.desktop.gui.common.exception.ExceptionDialog;
import dev.zacharyajohnson.wanikani.desktop.model.User;
import dev.zacharyajohnson.wanikani.desktop.web.api.WaniKaniApi;
import dev.zacharyajohnson.wanikani.desktop.web.api.WaniKaniApiEndpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Optional;

public final class WaniKaniApiV2 implements WaniKaniApi {
    private String apiKey;
    private final static WaniKaniApiV2 INSTANCE = new WaniKaniApiV2();

    private WaniKaniApiV2() {}

    public static WaniKaniApi getInstance() {
        return INSTANCE;
    }

    @Override
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String getApiKey() {
        return this.apiKey;
    }

    @Override
    public final Optional<User> getUser() {


            Optional<HttpURLConnection> optional = createHttpURLConnection(apiKey,"GET", WaniKaniApiV2Endpoint.USER_ENDPOINT);

            if (optional.isPresent()) {
                HttpURLConnection con = optional.get();
                try(BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode jsonNode = mapper.readTree(in).get("data");

                    con.disconnect();
                    User user = new User();
                    user.setApiKey(apiKey);
                    user.setId(jsonNode.get("id").asText());
                    user.setLevel(jsonNode.get("level").asInt());
                    user.setUsername(jsonNode.get("username").asText());
                    return Optional.of(user);


                } catch (IOException e) {
                    e.printStackTrace();

                }
            }

            return Optional.empty();
    }

    private Optional<HttpURLConnection> createHttpURLConnection(String apiKey, String requestMethod, WaniKaniApiEndpoint endpoint) {
        try {
            URL url = new URL(endpoint.getURL());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(requestMethod);
            con.setRequestProperty("Authorization", "Bearer " + apiKey);

            if(con.getResponseCode() == 401) {
                con.disconnect();

                ExceptionDialog exceptionDialog = new ExceptionDialog(
                        "API key may not be valid or have the correct permissions. " +
                                "Please check it is correct and try again");
                exceptionDialog.showAndWait();
                return Optional.empty();
            }

            return Optional.of(con);
            //TODO Need to figure out how pass in toggles for this,
            // as we don't neccessary want to display these errors
            // for things that can be offline
        } catch (UnknownHostException | NoRouteToHostException e) {
            ExceptionDialog exceptionDialog = new ExceptionDialog(
                    "Could not connect to WaniKani API(api.wanikani.com). Please make sure you have an internet connection and try again");
            exceptionDialog.showAndWait();
            return Optional.empty();

        } catch (IOException e) {
            ExceptionDialog exceptionDialog = new ExceptionDialog(e);
            exceptionDialog.showAndWait();
            return Optional.empty();
        }

    }

    private Optional<JsonNode> getJsonNode(InputStream inputStream) {
        return Optional.empty();
    }



}
