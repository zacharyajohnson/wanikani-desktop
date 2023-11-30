package dev.zacharyajohnson.wanikani.web.api.v2.http.response.user.subscription;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WaniKaniApiSubscriptionTypeV2 {
    FREE("free"),
    RECURRING("recurring"),
    LIFETIME("lifetime");

    private final String value;

    WaniKaniApiSubscriptionTypeV2(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }
}
