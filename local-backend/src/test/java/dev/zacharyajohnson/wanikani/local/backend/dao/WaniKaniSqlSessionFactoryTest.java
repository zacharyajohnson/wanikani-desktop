package dev.zacharyajohnson.wanikani.local.backend.dao;

import org.apache.ibatis.session.SqlSessionFactory;

import org.junit.Assert;
import org.junit.Test;

public class WaniKaniSqlSessionFactoryTest {

    @Test
    public void testNewSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = WaniKaniSqlSessionFactory.newSqlSessionFactory();
        Assert.assertNotNull(sqlSessionFactory);
    }

    @Test
    public void testNewSqlSessionFactoryWithEnv() {
        SqlSessionFactory sqlSessionFactory = WaniKaniSqlSessionFactory.newSqlSessionFactory("test");
        Assert.assertNotNull(sqlSessionFactory);
    }

    @Test
    public void testGetInstanceWithNullEnv() {
        SqlSessionFactory sqlSessionFactory = WaniKaniSqlSessionFactory.newSqlSessionFactory(null);
        Assert.assertNotNull(sqlSessionFactory);
    }

    public void testGetInstanceWithUnknownEnvThrowsError() {
        SqlSessionFactory sqlSessionFactory = WaniKaniSqlSessionFactory.newSqlSessionFactory("not-real-env");
    }

}
