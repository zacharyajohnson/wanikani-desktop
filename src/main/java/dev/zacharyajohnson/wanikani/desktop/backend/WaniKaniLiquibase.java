package dev.zacharyajohnson.wanikani.desktop.backend;

import dev.zacharyajohnson.wanikani.desktop.backend.dao.WaniKaniSqlSessionFactory;
import dev.zacharyajohnson.wanikani.desktop.ui.common.exception.ExceptionDialog;
import javafx.application.Platform;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.naming.Context;
import java.sql.Connection;
import java.sql.SQLException;

public class WaniKaniLiquibase {
    public static void initializeDBAndRunLiquibase() throws SQLException, LiquibaseException {
        initializeDBAndRunLiquibase(null);
    }

    public static void initializeDBAndRunLiquibase(String env) throws SQLException, LiquibaseException {
        SqlSessionFactory factory;
        Contexts contexts;

        if (env == null) {
            factory = WaniKaniSqlSessionFactory.newSqlSessionFactory();
            contexts = new Contexts("production");
        } else {
            factory = WaniKaniSqlSessionFactory.newSqlSessionFactory(env);
            contexts = new Contexts(env);
        }

        try (SqlSession session = factory.openSession();
             Connection conn = session.getConnection()) {

            Database database = DatabaseFactory
                    .getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(conn));

            try (Liquibase liquibase = new Liquibase(Config.liquibaseChangelogFilePath, new ClassLoaderResourceAccessor(), database)) {
                liquibase.update(contexts, new LabelExpression());
            }
        }
    }
}
