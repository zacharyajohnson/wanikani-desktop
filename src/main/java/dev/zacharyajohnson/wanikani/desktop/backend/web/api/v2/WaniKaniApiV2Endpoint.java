package dev.zacharyajohnson.wanikani.desktop.backend.web.api.v2;

public enum WaniKaniApiV2Endpoint {
    USER_ENDPOINT("https://api.wanikani.com/v2/user");

    private final String URL;

    WaniKaniApiV2Endpoint(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return this.URL;
    }
}
