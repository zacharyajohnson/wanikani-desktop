package dev.zacharyajohnson.wanikani.desktop.web.api.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.zacharyajohnson.wanikani.desktop.web.api.Http401Exception;
import dev.zacharyajohnson.wanikani.desktop.web.api.model.WaniKaniUser;
import dev.zacharyajohnson.wanikani.desktop.web.api.WaniKaniApi;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public final class WaniKaniApiV2 implements WaniKaniApi {
    private static String apiKey;

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
        WaniKaniApiV2.apiKey = apiKey;
    }

    @Override
    public String getApiKey() {
        return apiKey;
    }

    @Override
    public CompletableFuture<Optional<WaniKaniUser>> getUser() throws Http401Exception {

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

                        String id = jsonNode.get("id").asText();
                        int level = jsonNode.get("id").asInt();
                        String username = jsonNode.get("username").asText();

                        WaniKaniUser user = new WaniKaniUser(id,username,level,apiKey);

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
