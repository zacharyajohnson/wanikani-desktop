package dev.zacharyajohnson.wanikani.local.backend.service;

import dev.zacharyajohnson.wanikani.local.backend.WaniKaniLiquibase;
import dev.zacharyajohnson.wanikani.local.backend.model.User;
import org.junit.*;

public class UserServiceTest {

    private final UserService userService = new UserService("unit-test");

    @BeforeClass
    public static void initializeTestDB() {
        WaniKaniLiquibase.initializeDBAndRunLiquibase("unit-test");
    }

    @Test
    public void testGetUserBy() {
        User user = new User("1","Username1",1,"apiKey",false);
        User returnedUser = userService.getUserBy("1");
        Assert.assertEquals(user, returnedUser);
    }

    @Test
    public void testCreateUser() {
        User user = new User("2","Username2",1,"apiKey",false);
        userService.createUser(user);

        User returnedUser = userService.getUserBy("2");
        Assert.assertEquals(user, returnedUser);
    }

    @Test
    public void testGetLoggedInUser() {
        User loggedInUser = new User("3","Username3",1,"apiKey",true);

        User returnedUser = userService.getLoggedInUser();
        Assert.assertEquals(loggedInUser, returnedUser);
    }

    @Test
    public void testUpdateUser() {
        User user = new User("4","Username4",1,"apiKey",false);

        user.setLevel(2);
        userService.updateUser(user);

        User returnedUser = userService.getUserBy("4");
        Assert.assertEquals(user, returnedUser);
    }

}
