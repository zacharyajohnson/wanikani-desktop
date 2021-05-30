package dev.zacharyajohnson.wanikani.desktop.dao;


import dev.zacharyajohnson.wanikani.desktop.model.User;
import org.apache.ibatis.annotations.*;

public interface UserDAO {
    User getUser(@Param("id") String id);

    void createUser(User user);

    User getLoggedInUser();
}
