package dev.zacharyajohnson.wanikani.desktop.gui.controller.login;

import dev.zacharyajohnson.wanikani.desktop.gui.login.LoginStage;
import dev.zacharyajohnson.wanikani.desktop.model.User;
import dev.zacharyajohnson.wanikani.desktop.service.UserService;
import dev.zacharyajohnson.wanikani.desktop.web.api.WaniKaniApi;
import dev.zacharyajohnson.wanikani.desktop.web.api.v2.WaniKaniApiV2;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import java.util.Optional;

public class LoginController {

    @FXML
    private TextField apiV2Key;

    private UserService userService = new UserService();
    private WaniKaniApi waniKaniApi = WaniKaniApiV2.getInstance();

    @FXML
    public void authenticateUser() {
        LoginStage loginStage =  (LoginStage)apiV2Key.getScene().getWindow();
        waniKaniApi.setApiKey(apiV2Key.getText());

        Optional<User> optionalUser = waniKaniApi.getUser();

        optionalUser.ifPresent(user -> {
            System.out.println(user);
            loginStage.close();
            //TODO Replace this with the Home stage after we are done with the login stage
            LoginStage loginStage1 = new LoginStage();
            loginStage1.show();
        });
    }
}
