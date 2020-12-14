package com.github.zjohnson.wanikani.main;

import com.github.zjohnson.wanikani.gui.login.LoginStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class WaniKani extends Application {

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) {
        LoginStage loginStage = new LoginStage();
        loginStage.show();
    }


}