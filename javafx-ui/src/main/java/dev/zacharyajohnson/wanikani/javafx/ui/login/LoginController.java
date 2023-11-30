package dev.zacharyajohnson.wanikani.javafx.ui.login;

import dev.zacharyajohnson.wanikani.local.backend.service.Services;
import dev.zacharyajohnson.wanikani.local.backend.service.UserService;
import dev.zacharyajohnson.wanikani.web.api.v2.http.WaniKaniApiV2;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField apiV2Key;

    private final UserService userService = Services.userService;
    private final WaniKaniApiV2 waniKaniApi = WaniKaniApiV2.getInstance();

    @FXML
    public void authenticateUser() {
        LoginStage loginStage = (LoginStage) apiV2Key.getScene().getWindow();
        waniKaniApi.setApiKey(apiV2Key.getText());

     //   try {
      //      WaniKaniUserV2 waniKaniUserOptional = waniKaniApi.getUser();
//
//
  //          UserDataV2 waniKaniUser = waniKaniUserOptional.data();
//
  //          User user = new User();
    //        user.setId(waniKaniUser.id());
      //      user.setUsername(waniKaniUser.username());
        //    user.setLevel(waniKaniUser.level());
          //  user.setLoggedIn(true);
//
  //          if (userService.getUserBy(user.getId()) != null) {
    //            userService.updateUser(user);
      //      } else {
        //        userService.createUser(user);
          //  }
//
  //          loginStage.close();
//
  //          HomeStage homeStage = new HomeStage();
    //        homeStage.show();
      //  } catch (InterruptedException e) {
        //    if(e.getCause() instanceof ConnectException) {
          //      // We need to ensure internet connection here as this is the initial login to the application.
            //    ExceptionDialog exceptionDialog = new ExceptionDialog(loginStage,
              //      "Could not connect to WaniKani API(api.wanikani.com). Please make sure you have an internet connection and try again");
                //exceptionDialog.showAndWait();
            //} else if (e.getCause() instanceof Http401Exception) {
            //    ExceptionDialog exceptionDialog = new ExceptionDialog(loginStage, e.getCause().getMessage());
            //    exceptionDialog.showAndWait();
            //}
            //e.printStackTrace();
        //} catch (FailedRequestException | IOException | Http401Exception e) {
        //    throw new RuntimeException(e);
        //}
    }
}
