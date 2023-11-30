package dev.zacharyajohnson.wanikani.web.api.v2.http.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.zacharyajohnson.wanikani.web.api.v2.http.response.user.preferences.WaniKaniApiUserPreferencesV2;
import dev.zacharyajohnson.wanikani.web.api.v2.http.response.user.subscription.WaniKaniApiSubscriptionV2;

import java.time.ZonedDateTime;

public record WaniKaniApiUserDataV2(
        @JsonProperty("id")
        String id,
        @JsonProperty("username")
        String username,
        @JsonProperty("level")
        int level,
        @JsonProperty("profile_url")
        String profileUrl,
        @JsonProperty("started_at")
        ZonedDateTime creationDate,
        @JsonProperty("current_vacation_started_at")
        ZonedDateTime currentVacationStartDate,
        @JsonProperty("subscription")
        WaniKaniApiSubscriptionV2 subscription,
        @JsonProperty("preferences")
        WaniKaniApiUserPreferencesV2 preferences

) {
}
