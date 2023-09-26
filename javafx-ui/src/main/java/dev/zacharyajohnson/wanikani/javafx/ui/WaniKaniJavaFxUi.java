package dev.zacharyajohnson.wanikani.javafx.ui;

import dev.zacharyajohnson.wanikani.local.backend.WaniKaniLiquibase;
import dev.zacharyajohnson.wanikani.local.backend.model.User;
import dev.zacharyajohnson.wanikani.local.backend.service.Services;
import dev.zacharyajohnson.wanikani.javafx.ui.home.HomeStage;
import dev.zacharyajohnson.wanikani.javafx.ui.common.exception.ExceptionDialog;
import dev.zacharyajohnson.wanikani.javafx.ui.login.LoginStage;
import dev.zacharyajohnson.wanikani.web.api.v2.WaniKaniApiV2;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public final class WaniKaniJavaFxUi extends Application {
    public static void main(String[] args) {
        // Set uncaught exceptions to show up in case some fuckery happens that we are not aware of.
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            if (Platform.isFxApplicationThread()) {
                ExceptionDialog exceptionDialog = new ExceptionDialog(throwable);
                exceptionDialog.showAndWait();
            } else {
                throwable.printStackTrace();
                System.exit(1);
            }

        });

        WaniKaniLiquibase.initializeDBAndRunLiquibase();

        launch();
    }

    @Override
    public void start(Stage stage) {
        LoginStage loginStage = new LoginStage();
        User loggedInUser = Services.userService.getLoggedInUser();

        if (loggedInUser == null) {
            loginStage.show();
        } else {
            WaniKaniApiV2.getInstance().setApiKey(loggedInUser.getApiKey());
            HomeStage homeStage = new HomeStage();
            homeStage.show();
        }

    }

}
