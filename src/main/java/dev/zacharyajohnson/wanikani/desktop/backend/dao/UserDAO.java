package dev.zacharyajohnson.wanikani.desktop.backend.dao;


import dev.zacharyajohnson.wanikani.desktop.backend.model.User;
import org.apache.ibatis.annotations.*;

public interface UserDAO {
    User getUser(@Param("id") String id);

    void createUser(User user);

    User getLoggedInUser();

    void updateUser(User user);
}
