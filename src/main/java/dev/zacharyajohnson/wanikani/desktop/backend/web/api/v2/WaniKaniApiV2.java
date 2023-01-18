package dev.zacharyajohnson.wanikani.desktop.backend.web.api.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.zacharyajohnson.wanikani.desktop.backend.model.User;
import dev.zacharyajohnson.wanikani.desktop.backend.web.api.Http401Exception;
import dev.zacharyajohnson.wanikani.desktop.backend.web.api.WaniKaniApi;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public final class WaniKaniApiV2 implements WaniKaniApi {
    private String apiKey;

    private static final HttpClient httpClient  = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10L))
            .version(HttpClient.Version.HTTP_1_1)
            .build();

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
    public CompletableFuture<Optional<User>> getUser() throws Http401Exception {

        HttpRequest request = this.createHttpRequestBuilder(apiKey, WaniKaniApiV2Endpoint.USER_ENDPOINT)
                .GET()
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply((response) -> {
                    if(response.statusCode() == 401) {
                        throw new Http401Exception();
                    }

                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode jsonNode = mapper.readTree(response.body()).get("data");


                        User user = new User();
                        user.setApiKey(apiKey);
                        user.setId(jsonNode.get("id").asText());
                        user.setLevel(jsonNode.get("level").asInt());
                        user.setUsername(jsonNode.get("username").asText());

                        return Optional.of(user);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return Optional.empty();
                });

    }

    private HttpRequest.Builder createHttpRequestBuilder(String apiKey, WaniKaniApiV2Endpoint endpoint) {
        URI uri = URI.create(endpoint.getURL());

        return HttpRequest.newBuilder(uri)
                .header("Authorization", "Bearer " + apiKey);
    }
}
