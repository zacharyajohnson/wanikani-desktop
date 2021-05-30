package dev.zacharyajohnson.wanikani.desktop.web.api;

import dev.zacharyajohnson.wanikani.desktop.model.User;

import java.util.concurrent.CompletableFuture;

public interface WaniKaniApi {
    void setApiKey(String apiKey);
    String getApiKey();

    CompletableFuture<User> getUser();
}
