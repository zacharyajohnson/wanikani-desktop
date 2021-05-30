package dev.zacharyajohnson.wanikani.desktop.gui.login;

import dev.zacharyajohnson.wanikani.desktop.gui.common.exception.ExceptionDialog;
import dev.zacharyajohnson.wanikani.desktop.gui.login.LoginStage;
import dev.zacharyajohnson.wanikani.desktop.model.User;
import dev.zacharyajohnson.wanikani.desktop.service.UserService;
import dev.zacharyajohnson.wanikani.desktop.web.api.Http401Exception;
import dev.zacharyajohnson.wanikani.desktop.web.api.WaniKaniApi;
import dev.zacharyajohnson.wanikani.desktop.web.api.v2.WaniKaniApiV2;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import java.net.ConnectException;
import java.util.concurrent.ExecutionException;

public class LoginController {

    @FXML
    private TextField apiV2Key;

    private UserService userService = new UserService();
    private WaniKaniApi waniKaniApi = WaniKaniApiV2.getInstance();

    @FXML
    public void authenticateUser() {
        LoginStage loginStage =  (LoginStage)apiV2Key.getScene().getWindow();
        waniKaniApi.setApiKey(apiV2Key.getText());

        try {
            User user = waniKaniApi.getUser()
                    .get();
            System.out.println(user);
            loginStage.close();
            //TODO Replace this with the Home stage after we are done with the login stage
            LoginStage loginStage1 = new LoginStage();
            loginStage1.show();
        } catch (InterruptedException | ExecutionException e) {
            if(e.getCause() instanceof ConnectException)
            {
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
