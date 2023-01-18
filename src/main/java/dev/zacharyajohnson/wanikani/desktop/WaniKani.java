package dev.zacharyajohnson.wanikani.desktop;

import dev.zacharyajohnson.wanikani.desktop.ui.home.HomeStage;
import dev.zacharyajohnson.wanikani.desktop.ui.common.exception.ExceptionDialog;
import dev.zacharyajohnson.wanikani.desktop.ui.login.LoginStage;
import dev.zacharyajohnson.wanikani.desktop.backend.model.User;
import dev.zacharyajohnson.wanikani.desktop.backend.service.UserService;
import dev.zacharyajohnson.wanikani.desktop.backend.web.api.v2.WaniKaniApiV2;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;


import dev.zacharyajohnson.wanikani.desktop.backend.WaniKaniLiquibase;
import liquibase.exception.LiquibaseException;

import java.sql.SQLException;

public final class WaniKani extends Application {
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

        try {
            WaniKaniLiquibase.initializeDBAndRunLiquibase();
        } catch (SQLException  | LiquibaseException e) {
            System.err.println(WaniKani.class + " - Unable to create/update db");
            e.printStackTrace();
            System.exit(1);
        }

        launch();
    }

    @Override
    public void start(Stage stage) {
        LoginStage loginStage = new LoginStage();
        User loggedInUser = new UserService().getLoggedInUser();

        if (loggedInUser == null) {
            loginStage.show();
        } else {
            WaniKaniApiV2.getInstance().setApiKey(loggedInUser.getApiKey());
            HomeStage homeStage = new HomeStage();
            homeStage.show();
        }

    }

}
