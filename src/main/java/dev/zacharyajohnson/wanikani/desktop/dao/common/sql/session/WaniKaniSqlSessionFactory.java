package dev.zacharyajohnson.wanikani.desktop.dao.common.sql.session;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class WaniKaniSqlSessionFactory {

    private static SqlSessionFactory sqlSessionFactory;

    private WaniKaniSqlSessionFactory() {}

    public static SqlSessionFactory getInstance() {
        if(sqlSessionFactory == null) {

            String configFile = "mybatis/dao/config.xml";
            try(InputStream inputStream = Resources.getResourceAsStream(configFile)) {
                SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
                sqlSessionFactory = builder.build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sqlSessionFactory;
    }

    public static SqlSession openSqlSession() {
        return getInstance().openSession();
    }

}
