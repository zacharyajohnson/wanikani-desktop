package dev.zacharyajohnson.wanikani.web.api.v2.http;

public enum WaniKaniApiEndpointV2 {
    USER_ENDPOINT("https://api.wanikani.com/v2/user");

    private final String URL;

    WaniKaniApiEndpointV2(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return this.URL;
    }
}
