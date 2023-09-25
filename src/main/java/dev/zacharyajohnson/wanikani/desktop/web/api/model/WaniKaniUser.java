package dev.zacharyajohnson.wanikani.desktop.web.api.model;

public record WaniKaniUser(String id,
                           String username,
                           int level,
                           String apiKey
) {

}
