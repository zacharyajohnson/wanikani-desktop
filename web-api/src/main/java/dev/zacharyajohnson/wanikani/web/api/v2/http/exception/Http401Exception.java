package dev.zacharyajohnson.wanikani.web.api.v2.http.exception;

public class Http401Exception extends Exception {
    public Http401Exception() {
        super("""
                API key may not be valid or have the correct permissions.
                Please check it is correct and try again
                """);
    }
}
