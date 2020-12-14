package com.github.zjohnson.wanikani.main;

import com.github.zjohnson.wanikani.gui.login.LoginStage;
import javafx.application.Application;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.h2.jdbcx.JdbcDataSource;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class WaniKani extends Application {

    public static void main(String[] args) {
        String homeDirectory = System.getProperty("user.home");
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:" + homeDirectory + "/.h2/wanikani-desktop/wanikani-desktop");
        ds.setUser("sa");
        ds.setPassword("sa");

        try {
            Connection conn = ds.getConnection();
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
            Liquibase liquibase = new Liquibase("h2/liquibase/db/changesets/dbchangelog.xml", new ClassLoaderResourceAccessor(),database);
            liquibase.update(new Contexts(), new LabelExpression());

        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
        } catch (LiquibaseException e) {
            e.printStackTrace();
        }

        launch();
    }


    @Override
    public void start(Stage stage) {
        LoginStage loginStage = new LoginStage();
        loginStage.show();
    }


}