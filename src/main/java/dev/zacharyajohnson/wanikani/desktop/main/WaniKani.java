package dev.zacharyajohnson.wanikani.desktop.main;

import dev.zacharyajohnson.wanikani.desktop.dao.common.sql.session.WaniKaniSqlSessionFactory;
import dev.zacharyajohnson.wanikani.desktop.gui.common.exception.ExceptionDialog;
import dev.zacharyajohnson.wanikani.desktop.gui.login.LoginStage;
import dev.zacharyajohnson.wanikani.desktop.model.User;
import dev.zacharyajohnson.wanikani.desktop.service.UserService;
import dev.zacharyajohnson.wanikani.desktop.web.api.v2.WaniKaniApiV2;
import javafx.application.Application;
import javafx.application.Platform;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class WaniKani extends Application {
    public static void main(String[] args) {
        // Set uncaught exceptions to show up in case some fuckery happens that we are not aware of.
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            if(Platform.isFxApplicationThread()) {
                ExceptionDialog exceptionDialog = new ExceptionDialog(throwable);
                exceptionDialog.showAndWait();
            }else {
                throwable.printStackTrace();
            }

        });

        launch();
    }

    @Override
    public void start(Stage stage) {
        initializeDBAndRunLiquibase();

        UserService userService = new UserService();
        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser == null) {
            LoginStage loginStage = new LoginStage();
            loginStage.show();

        } else {
            stage.show();
            WaniKaniApiV2.getInstance().setApiKey(loggedInUser.getApiKey());
        }

    }

    private void initializeDBAndRunLiquibase() {
        try(Connection conn = WaniKaniSqlSessionFactory.openSqlSession().getConnection()) {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
            Liquibase liquibase = new Liquibase("db/liquibase/changesets/dbchangelog.xml", new ClassLoaderResourceAccessor(),database);
            liquibase.update(new Contexts(), new LabelExpression());

        } catch (SQLException | LiquibaseException throwables) {
            throwables.printStackTrace();
            ExceptionDialog exceptionDialog = new ExceptionDialog(throwables);
            exceptionDialog.showAndWait();
            Platform.exit();
        }
    }


}