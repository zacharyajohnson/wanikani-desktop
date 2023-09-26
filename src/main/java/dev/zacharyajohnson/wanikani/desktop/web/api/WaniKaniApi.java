package dev.zacharyajohnson.wanikani.desktop.web.api;

import dev.zacharyajohnson.wanikani.desktop.web.api.exception.Http401Exception;
import dev.zacharyajohnson.wanikani.desktop.web.api.model.WaniKaniUser;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface WaniKaniApi {
    void setApiKey(String apiKey);
    String getApiKey();

    CompletableFuture<Optional<WaniKaniUser>> getUser() throws Http401Exception;
}
