package dev.zacharyajohnson.wanikani.desktop.ui.login;

import dev.zacharyajohnson.wanikani.desktop.ui.home.HomeStage;
import dev.zacharyajohnson.wanikani.desktop.ui.common.exception.ExceptionDialog;
import dev.zacharyajohnson.wanikani.desktop.backend.model.User;
import dev.zacharyajohnson.wanikani.desktop.backend.service.UserService;
import dev.zacharyajohnson.wanikani.desktop.backend.web.api.Http401Exception;
import dev.zacharyajohnson.wanikani.desktop.backend.web.api.WaniKaniApi;
import dev.zacharyajohnson.wanikani.desktop.backend.web.api.v2.WaniKaniApiV2;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import java.net.ConnectException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class LoginController {

    @FXML
    private TextField apiV2Key;

    private final UserService userService = new UserService();
    private final WaniKaniApi waniKaniApi = WaniKaniApiV2.getInstance();

    @FXML
    public void authenticateUser() {
        LoginStage loginStage = (LoginStage) apiV2Key.getScene().getWindow();
        waniKaniApi.setApiKey(apiV2Key.getText());

        try {
            Optional<User> userOptional = waniKaniApi.getUser()
                    .get();

            if (userOptional.isEmpty()) {
                throw new RuntimeException("Could not get User info. Please try again.");
            }

            User user = userOptional.get();
            user.setLoggedIn(true);

            if (userService.getUserBy(user.getId()) != null) {
                userService.updateUser(user);
            } else {
                userService.createUser(user);
            }

            loginStage.close();

            HomeStage homeStage = new HomeStage();
            homeStage.show();
        } catch (InterruptedException | ExecutionException e) {
            if(e.getCause() instanceof ConnectException) {
                // We need to ensure internet connection here as this is the initial login to the application.
                ExceptionDialog exceptionDialog = new ExceptionDialog(loginStage,
                    "Could not connect to WaniKani API(api.wanikani.com). Please make sure you have an internet connection and try again");
                exceptionDialog.showAndWait();
            } else if (e.getCause() instanceof Http401Exception) {
                ExceptionDialog exceptionDialog = new ExceptionDialog(loginStage, e.getCause().getMessage());
                exceptionDialog.showAndWait();
            }
            e.printStackTrace();
        }
    }
}
