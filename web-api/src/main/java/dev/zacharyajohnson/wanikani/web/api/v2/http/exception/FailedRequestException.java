package dev.zacharyajohnson.wanikani.web.api.v2.http.exception;

public class FailedRequestException extends Throwable {
    public FailedRequestException(String message) {
        super(message);
    }
}
