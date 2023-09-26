package dev.zacharyajohnson.wanikani.local.backend.dao;


import dev.zacharyajohnson.wanikani.local.backend.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserDAO {
    User getUser(@Param("id") String id);

    void createUser(User user);

    User getLoggedInUser();

    void updateUser(User user);
}
