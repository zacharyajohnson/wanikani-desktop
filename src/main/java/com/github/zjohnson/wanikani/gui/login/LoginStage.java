package com.github.zjohnson.wanikani.gui.login;

import com.github.zjohnson.wanikani.gui.common.WaniKaniStage;
import com.github.zjohnson.wanikani.gui.exception.ExceptionDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class LoginStage extends WaniKaniStage {
    public LoginStage() {
        super();

        super.setStageTitle("Login");
        super.loadFXML("/gui/login/login.fxml");

        this.centerOnScreen();
//        this.setResizable(false);
        this.setHeight(Screen.getPrimary().getVisualBounds().getHeight() / 2);
        this.setWidth(Screen.getPrimary().getVisualBounds().getWidth() / 3);
    }
}
