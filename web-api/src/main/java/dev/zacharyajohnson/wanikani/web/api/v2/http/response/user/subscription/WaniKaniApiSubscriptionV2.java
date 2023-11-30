package dev.zacharyajohnson.wanikani.web.api.v2.http.response.user.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record WaniKaniApiSubscriptionV2(
        @JsonProperty("active")
        boolean active,
        @JsonProperty("type")
        WaniKaniApiSubscriptionTypeV2 type,
        @JsonProperty("max_level_granted")
        int maxLevel,
        @JsonProperty("period_ends_at")
        ZonedDateTime endDate
) {
}
