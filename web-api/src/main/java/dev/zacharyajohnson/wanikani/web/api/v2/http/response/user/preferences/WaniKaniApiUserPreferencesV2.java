package dev.zacharyajohnson.wanikani.web.api.v2.http.response.user.preferences;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WaniKaniApiUserPreferencesV2(
        @JsonProperty("default_voice_actor_id")
        int defaultVoiceActorId,
        @JsonProperty("extra_study_autoplay_audio")
        boolean autoplayAudioDuringExtraStudy,
        @JsonProperty("lessons_autoplay_audio")
        boolean autoplayAudioDuringLessons,
        @JsonProperty("lessons_batch_size")
        int lessonBatchSize,
        @JsonProperty("lessons_presentation_order")
        WaniKaniApiLessonPresentationOrderV2 lessonPresentationOrder,
        @JsonProperty("reviews_autoplay_audio")
        boolean autoplayAudioDuringReviews,
        @JsonProperty("reviews_display_srs_indicator")
        boolean displaySRSIndicatorWhenReviewing,
        @JsonProperty("reviews_presentation_order")
        WaniKaniApiReviewPresentationOrderV2 reviewPresentationOrder
) {
}
