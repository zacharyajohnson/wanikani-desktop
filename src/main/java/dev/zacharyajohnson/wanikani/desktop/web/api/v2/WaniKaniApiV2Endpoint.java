package dev.zacharyajohnson.wanikani.desktop.web.api.v2;

import dev.zacharyajohnson.wanikani.desktop.web.api.WaniKaniApiEndpoint;

public enum WaniKaniApiV2Endpoint implements WaniKaniApiEndpoint {
    USER_ENDPOINT("https://api.wanikani.com/v2/user");

    private String URL;


    WaniKaniApiV2Endpoint(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return this.URL;
    }
}
