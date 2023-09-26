package dev.zacharyajohnson.wanikani.local.backend;

import dev.zacharyajohnson.wanikani.local.backend.dao.WaniKaniSqlSessionFactory;
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

import java.sql.Connection;
import java.sql.SQLException;

public class WaniKaniLiquibase {
    public static void initializeDBAndRunLiquibase()  {
        initializeDBAndRunLiquibase(null);
    }

    public static void initializeDBAndRunLiquibase(String env) {
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
        } catch (SQLException  | LiquibaseException e) {
            System.err.println(WaniKaniLiquibase.class + " - Unable to create/update db");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
