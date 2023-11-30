package dev.zacharyajohnson.wanikani.web.api.v2.http;

import dev.zacharyajohnson.wanikani.web.api.v2.http.exception.FailedRequestException;
import dev.zacharyajohnson.wanikani.web.api.v2.http.exception.Http401Exception;
import dev.zacharyajohnson.wanikani.web.api.v2.http.request.user.WaniKaniApiUpdateUserPreferencesRequestV2;
import dev.zacharyajohnson.wanikani.web.api.v2.http.response.user.WaniKaniApiUserV2;
import dev.zacharyajohnson.wanikani.web.api.v2.http.response.user.preferences.WaniKaniApiLessonPresentationOrderV2;
import dev.zacharyajohnson.wanikani.web.api.v2.http.response.user.preferences.WaniKaniApiReviewPresentationOrderV2;
import dev.zacharyajohnson.wanikani.web.api.v2.http.response.user.preferences.WaniKaniApiUserPreferencesV2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class WaniKaniApiV2Test {
    private static final String invalidJsonResponse = """
            {
                "test": "test"
            }
            """;
    private static final String userJsonResponse = """
            {
              "object": "user",
              "url": "https://api.wanikani.com/v2/user",
              "data_updated_at": "2018-04-06T14:26:53.022245Z",
              "data": {
                "id": "5a6a5234-a392-4a87-8f3f-33342afe8a42",
                "username": "test_user",
                "level": 5,
                "profile_url": "https://www.wanikani.com/users/test_user",
                "started_at": "2012-05-11T00:52:18.958466Z",
                "current_vacation_started_at": null,
                "subscription": {
                  "active": true,
                  "type": "recurring",
                  "max_level_granted": 60,
                  "period_ends_at": "2018-12-11T13:32:19.485748Z"
                },
                "preferences": {
                  "default_voice_actor_id": 1,
                  "extra_study_autoplay_audio": false,
                  "lessons_autoplay_audio": false,
                  "lessons_batch_size": 10,
                  "lessons_presentation_order": "ascending_level_then_subject",
                  "reviews_autoplay_audio": false,
                  "reviews_display_srs_indicator": true,
                  "reviews_presentation_order": "shuffled"
                }
              }
            }
            """;

    private AutoCloseable mocks;

    @InjectMocks
    private WaniKaniApiV2 waniKaniApi;

    @Mock
    HttpClient httpClient;

    @BeforeEach
    public void setup() {
        waniKaniApi = WaniKaniApiV2.getInstance();
        waniKaniApi.setApiKey("testApiKey");
        assertEquals(waniKaniApi.getApiKey(), "testApiKey");

        mocks = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    public void cleanup() throws Exception {
        mocks.close();
    }

    @Test
    public void testGetUser_Successful() throws IOException, InterruptedException, FailedRequestException, Http401Exception, IllegalAccessException, NoSuchFieldException {
        // Mock HttpResponse
        HttpResponse<Object> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(userJsonResponse);

        Field field = WaniKaniApiV2.getInstance().getClass().getDeclaredField("httpClient");
        field.setAccessible(true);
        when(httpClient.send(any(), any())).thenReturn(mockResponse);
        field.set(null,httpClient);

        WaniKaniApiUserV2 user = waniKaniApi.getUser();

        assertEquals("test_user", user.data().username());
    }

    @Test
    public void testGetUser_Unauthorized() throws IOException, InterruptedException, NoSuchFieldException, IllegalAccessException, FailedRequestException, Http401Exception {
        // Mock HttpResponse
        HttpResponse<Object> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(401);
        when(mockResponse.body()).thenReturn(userJsonResponse);

        Field field = WaniKaniApiV2.getInstance().getClass().getDeclaredField("httpClient");
        field.setAccessible(true);
        when(httpClient.send(any(), any())).thenReturn(mockResponse);
        field.set(null,httpClient);

        assertThrows(Http401Exception.class, () -> waniKaniApi.getUser());
    }

    @Test
    public void testGetUser_FailedRequestExecption() throws IOException, InterruptedException, NoSuchFieldException, IllegalAccessException, FailedRequestException, Http401Exception {
        // Mock HttpResponse
        HttpResponse<Object> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(400);
        when(mockResponse.body()).thenReturn(userJsonResponse);

        Field field = WaniKaniApiV2.getInstance().getClass().getDeclaredField("httpClient");
        field.setAccessible(true);
        when(httpClient.send(any(), any())).thenReturn(mockResponse);
        field.set(null,httpClient);

        assertThrows(FailedRequestException.class, () -> waniKaniApi.getUser());
    }

    @Test
    public void testUpdateUserPreferences_Successful() throws IOException, InterruptedException, FailedRequestException, Http401Exception, NoSuchFieldException, IllegalAccessException {
        WaniKaniApiUpdateUserPreferencesRequestV2 request = getUpdateUserPreferencesRequestV2();

        HttpResponse<Object> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(userJsonResponse);

        Field field = WaniKaniApiV2.getInstance().getClass().getDeclaredField("httpClient");
        field.setAccessible(true);
        when(httpClient.send(any(), any())).thenReturn(mockResponse);
        field.set(null,httpClient);

        WaniKaniApiUserV2 user = waniKaniApi.updateUserPreferences(request);
        WaniKaniApiUserPreferencesV2 responsePreferences = user.data().preferences();

        assertEquals("test_user", user.data().username());
        assertEquals(responsePreferences.defaultVoiceActorId(), request.getDefaultVoiceActorId());
        assertEquals(responsePreferences.autoplayAudioDuringExtraStudy(), request.autoplayAudioDuringExtraStudy());
        assertEquals(responsePreferences.autoplayAudioDuringLessons(), request.autoplayAudioDuringLessons());
        assertEquals(responsePreferences.lessonBatchSize(), request.getLessonBatchSize());
        assertEquals(responsePreferences.lessonPresentationOrder(), request.getLessonPresentationOrder());
        assertEquals(responsePreferences.autoplayAudioDuringReviews(), request.autoplayAudioDuringReviews());
        assertEquals(responsePreferences.displaySRSIndicatorWhenReviewing(), request.displaySRSIndicatorWhenReviewing());
        assertEquals(responsePreferences.reviewPresentationOrder(), request.getReviewPresentationOrder());
    }

    @Test
    public void when_response_cant_be_mapped_to_object_then_throws_json_processing_exception() throws NoSuchFieldException, IOException, InterruptedException, Http401Exception, IllegalAccessException {
        // Mock HttpResponse
        HttpResponse<Object> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(invalidJsonResponse);

        Field field = WaniKaniApiV2.getInstance().getClass().getDeclaredField("httpClient");
        field.setAccessible(true);
        when(httpClient.send(any(), any())).thenReturn(mockResponse);
        field.set(null,httpClient);

        assertThrows(IOException.class, () -> waniKaniApi.getUser());
    }

    private WaniKaniApiUpdateUserPreferencesRequestV2 getUpdateUserPreferencesRequestV2() {
        WaniKaniApiUpdateUserPreferencesRequestV2 request = new WaniKaniApiUpdateUserPreferencesRequestV2();
        request.setDefaultVoiceActorId(1);
        request.setAutoplayAudioDuringExtraStudy(false);
        request.setAutoplayAudioDuringLessons(false);
        request.setLessonBatchSize(10);
        request.setLessonPresentationOrder(WaniKaniApiLessonPresentationOrderV2.ASCENDING_LEVEL_THEN_SUBJECT);
        request.setAutoplayAudioDuringReviews(false);
        request.setDisplaySRSIndicatorWhenReviewing(true);
        request.setReviewPresentationOrder(WaniKaniApiReviewPresentationOrderV2.SHUFFLED);
        return request;
    }
}
