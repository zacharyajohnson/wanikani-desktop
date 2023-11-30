package dev.zacharyajohnson.wanikani.web.api.v2.http.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record WaniKaniApiUserV2(
        @JsonProperty("object")
        String object,
        @JsonProperty("url")
        String url,
        @JsonProperty("data_updated_at")
        ZonedDateTime dateUpdated,
        @JsonProperty("data")
        WaniKaniApiUserDataV2 data
) {
}
