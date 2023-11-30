package dev.zacharyajohnson.wanikani.web.api.v2.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.zacharyajohnson.wanikani.web.api.v2.http.exception.FailedRequestException;
import dev.zacharyajohnson.wanikani.web.api.v2.http.exception.Http401Exception;
import dev.zacharyajohnson.wanikani.web.api.v2.http.response.user.WaniKaniApiUserV2;
import dev.zacharyajohnson.wanikani.web.api.v2.http.request.user.WaniKaniApiUpdateUserPreferencesRequestV2;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public final class WaniKaniApiV2 {

    private static String apiKey;

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String WANIKANI_API_REVISION = "20170710";

    static {
        mapper.registerModule(new JavaTimeModule());
    }

    private static HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10L))
            .version(HttpClient.Version.HTTP_1_1)
            .build();

    private static final WaniKaniApiV2 INSTANCE = new WaniKaniApiV2();

    private WaniKaniApiV2() {
    }

    public static WaniKaniApiV2 getInstance() {
        return INSTANCE;
    }

    public void setApiKey(String apiKey) {
        WaniKaniApiV2.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public WaniKaniApiUserV2 getUser() throws Http401Exception, FailedRequestException, IOException, InterruptedException {

        HttpRequest request = this.createHttpRequestBuilder(apiKey, WaniKaniApiEndpointV2.USER_ENDPOINT)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return (WaniKaniApiUserV2) this.mapToObject(response, WaniKaniApiUserV2.class);
    }

    public WaniKaniApiUserV2 updateUserPreferences(WaniKaniApiUpdateUserPreferencesRequestV2 updateUserPreferencesRequest) throws Http401Exception, IOException, InterruptedException, FailedRequestException {

        ObjectNode rootNode = mapper.createObjectNode();
        ObjectNode userNode = mapper.createObjectNode();

        userNode.set("preferences", mapper.convertValue(updateUserPreferencesRequest, JsonNode.class));
        rootNode.set("user", userNode);

        HttpRequest request = this.createHttpRequestBuilder(apiKey, WaniKaniApiEndpointV2.USER_ENDPOINT)
                .header("Content-Type", "application/json; charset=utf8")
                .PUT(HttpRequest.BodyPublishers.ofString(rootNode.toString()))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return (WaniKaniApiUserV2) this.mapToObject(response, WaniKaniApiUserV2.class);
    }

    private HttpRequest.Builder createHttpRequestBuilder(String apiKey, WaniKaniApiEndpointV2 endpoint) {
        URI uri = URI.create(endpoint.getURL());

        return HttpRequest.newBuilder(uri)
                .header("Authorization", "Bearer " + apiKey)
                .header("Wanikani-Revision", WANIKANI_API_REVISION);
    }

    private Object mapToObject(HttpResponse<String> response, Class<?> clazz) throws Http401Exception, FailedRequestException, IOException {
        if (response.statusCode() == 401) {
            throw new Http401Exception();
        } else if (response.statusCode() != 200) {
            throw new FailedRequestException("""
                    Error trying to call endpoint %s
                    Response Body = %s
                    """.formatted(response, response.body()));
        }

        try {
            return mapper.readValue(response.body(), clazz);
        } catch (JsonProcessingException e) {
            throw new IOException(e);
        }
    }
}
