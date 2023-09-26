package dev.zacharyajohnson.wanikani.javafx.ui.login;

import dev.zacharyajohnson.wanikani.javafx.ui.common.stage.WaniKaniStage;
import javafx.stage.Screen;


public class LoginStage extends WaniKaniStage {
    public LoginStage() {
        super();

        super.setStageTitle("Login");
        super.loadFXML("/login/login.fxml");

        this.centerOnScreen();
        //this.setResizable(false);
        this.setHeight(Screen.getPrimary().getVisualBounds().getHeight() / 2);
        this.setWidth(Screen.getPrimary().getVisualBounds().getWidth() / 3);
    }
}
