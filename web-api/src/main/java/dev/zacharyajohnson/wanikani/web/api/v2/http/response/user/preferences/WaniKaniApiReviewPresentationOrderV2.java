package dev.zacharyajohnson.wanikani.web.api.v2.http.response.user.preferences;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WaniKaniApiReviewPresentationOrderV2 {
    LOWER_LEVELS_FIRST("lower_levels_first"),
    SHUFFLED("shuffled");
    private final String value;

    WaniKaniApiReviewPresentationOrderV2(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }
}
