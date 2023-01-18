package dev.zacharyajohnson.wanikani.desktop.backend.dao;

import dev.zacharyajohnson.wanikani.desktop.backend.Config;
import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class WaniKaniSqlSessionFactory {

    private WaniKaniSqlSessionFactory() {}

    public static SqlSessionFactory newSqlSessionFactory() {
        return buildSqlSessionFactory(null);
    }

    public static SqlSessionFactory newSqlSessionFactory(String env) {
        return buildSqlSessionFactory(env);
    }

    private static SqlSessionFactory buildSqlSessionFactory(String env) {

        SqlSessionFactory factory = null;

        try (InputStream inputStream = Resources.getResourceAsStream(Config.myBatisConfigDefaultLocation)) {
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            if (env == null) {
                factory = builder.build(inputStream);
            } else {
                factory = builder.build(inputStream, env);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return factory;
    }

}
