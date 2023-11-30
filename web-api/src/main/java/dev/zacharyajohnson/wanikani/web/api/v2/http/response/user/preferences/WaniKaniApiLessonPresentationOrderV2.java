package dev.zacharyajohnson.wanikani.web.api.v2.http.response.user.preferences;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WaniKaniApiLessonPresentationOrderV2 {
    ASCENDING_LEVEL_THEN_SUBJECT("ascending_level_then_subject"),
    ASCENDING_LEVEL_THEN_SHUFFLED("ascending_level_then_shuffled"),
    SHUFFLED("shuffled");
    private final String value;

    WaniKaniApiLessonPresentationOrderV2(String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }
}
