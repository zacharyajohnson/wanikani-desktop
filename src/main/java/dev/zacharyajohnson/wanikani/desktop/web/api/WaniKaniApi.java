package dev.zacharyajohnson.wanikani.desktop.web.api;

import dev.zacharyajohnson.wanikani.desktop.model.User;

import java.util.Optional;

public interface WaniKaniApi {
    void setApiKey(String apiKey);
    String getApiKey();

    Optional<User> getUser();
}
