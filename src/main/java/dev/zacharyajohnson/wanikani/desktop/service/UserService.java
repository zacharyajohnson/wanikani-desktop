package dev.zacharyajohnson.wanikani.desktop.service;


import dev.zacharyajohnson.wanikani.desktop.dao.UserDAO;
import dev.zacharyajohnson.wanikani.desktop.dao.common.sql.session.WaniKaniSqlSessionFactory;
import dev.zacharyajohnson.wanikani.desktop.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {

    private SqlSessionFactory sqlSessionFactory = WaniKaniSqlSessionFactory.getInstance();

    public User getUserBy(String id) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            return userDAO.getUser(id);
        }
    }

    public void createUser(User user) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            userDAO.createUser(user);
            sqlSession.commit();
        }
    }

    public User getLoggedInUser() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);

            return userDAO.getLoggedInUser();
        }
    }

    public void updateUser(User user) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);

            userDAO.updateUser(user);
            sqlSession.commit();
        }
    }
}
