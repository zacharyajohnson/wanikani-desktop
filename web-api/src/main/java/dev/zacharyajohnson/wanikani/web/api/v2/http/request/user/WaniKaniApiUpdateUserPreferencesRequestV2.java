package dev.zacharyajohnson.wanikani.web.api.v2.http.request.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.zacharyajohnson.wanikani.web.api.v2.http.response.user.preferences.WaniKaniApiLessonPresentationOrderV2;
import dev.zacharyajohnson.wanikani.web.api.v2.http.response.user.preferences.WaniKaniApiReviewPresentationOrderV2;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WaniKaniApiUpdateUserPreferencesRequestV2 {
    @JsonProperty("default_voice_actor_id")
    private Integer defaultVoiceActorId;

    @JsonProperty("extra_study_autoplay_audio")
    private Boolean autoplayAudioDuringExtraStudy;

    @JsonProperty("lessons_autoplay_audio")
    private Boolean autoplayAudioDuringLessons;

    @JsonProperty("lessons_batch_size")
    private Integer lessonBatchSize;

    @JsonProperty("lessons_presentation_order")
    private WaniKaniApiLessonPresentationOrderV2 lessonPresentationOrder;

    @JsonProperty("reviews_autoplay_audio")
    private Boolean autoplayAudioDuringReviews;

    @JsonProperty("reviews_display_srs_indicator")
    private Boolean displaySRSIndicatorWhenReviewing;

    @JsonProperty("reviews_presentation_order")
    private WaniKaniApiReviewPresentationOrderV2 reviewPresentationOrder;

    public Integer getDefaultVoiceActorId() {
        return defaultVoiceActorId;
    }

    public void setDefaultVoiceActorId(Integer defaultVoiceActorId) {
        this.defaultVoiceActorId = defaultVoiceActorId;
    }

    public Boolean autoplayAudioDuringExtraStudy() {
        return autoplayAudioDuringExtraStudy;
    }

    public void setAutoplayAudioDuringExtraStudy(Boolean autoplayAudioDuringExtraStudy) {
        this.autoplayAudioDuringExtraStudy = autoplayAudioDuringExtraStudy;
    }

    public Boolean autoplayAudioDuringLessons() {
        return autoplayAudioDuringLessons;
    }

    public void setAutoplayAudioDuringLessons(Boolean autoplayAudioDuringLessons) {
        this.autoplayAudioDuringLessons = autoplayAudioDuringLessons;
    }

    public Integer getLessonBatchSize() {
        return lessonBatchSize;
    }

    public void setLessonBatchSize(Integer lessonBatchSize) {
        this.lessonBatchSize = lessonBatchSize;
    }

    public WaniKaniApiLessonPresentationOrderV2 getLessonPresentationOrder() {
        return lessonPresentationOrder;
    }

    public void setLessonPresentationOrder(WaniKaniApiLessonPresentationOrderV2 lessonPresentationOrder) {
        this.lessonPresentationOrder = lessonPresentationOrder;
    }

    public Boolean autoplayAudioDuringReviews() {
        return autoplayAudioDuringReviews;
    }

    public void setAutoplayAudioDuringReviews(Boolean autoplayAudioDuringReviews) {
        this.autoplayAudioDuringReviews = autoplayAudioDuringReviews;
    }

    public Boolean displaySRSIndicatorWhenReviewing() {
        return displaySRSIndicatorWhenReviewing;
    }

    public void setDisplaySRSIndicatorWhenReviewing(Boolean displaySRSIndicatorWhenReviewing) {
        this.displaySRSIndicatorWhenReviewing = displaySRSIndicatorWhenReviewing;
    }

    public WaniKaniApiReviewPresentationOrderV2 getReviewPresentationOrder() {
        return reviewPresentationOrder;
    }

    public void setReviewPresentationOrder(WaniKaniApiReviewPresentationOrderV2 reviewPresentationOrder) {
        this.reviewPresentationOrder = reviewPresentationOrder;
    }
}
