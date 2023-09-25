package dev.zacharyajohnson.wanikani.desktop.backend.web.api;

import dev.zacharyajohnson.wanikani.desktop.backend.model.User;
import dev.zacharyajohnson.wanikani.desktop.backend.web.api.model.WaniKaniUser;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface WaniKaniApi {
    void setApiKey(String apiKey);
    String getApiKey();

    CompletableFuture<Optional<WaniKaniUser>> getUser() throws Http401Exception;
}
