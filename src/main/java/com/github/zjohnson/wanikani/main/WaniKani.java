package com.github.zjohnson.wanikani.main;

import com.github.zjohnson.wanikani.gui.common.WaniKaniStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class WaniKani extends Application {

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) {
        WaniKaniStage waniKaniStage = WaniKaniStage.getInstance();
        waniKaniStage.show();
    }


}