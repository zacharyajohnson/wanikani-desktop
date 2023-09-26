package dev.zacharyajohnson.wanikani.desktop.web.api.exception;

public class Http401Exception extends RuntimeException {
    public Http401Exception() {
        super("API key may not be valid or have the correct permissions. " +
                "Please check it is correct and try again");
    }
}
