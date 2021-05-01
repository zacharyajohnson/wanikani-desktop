package dev.zacharyajohnson.wanikani.desktop.gui.controller.login;

import dev.zacharyajohnson.wanikani.desktop.gui.common.exception.ExceptionDialog;
import dev.zacharyajohnson.wanikani.desktop.gui.login.LoginStage;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import java.io.*;
import java.net.*;


public class LoginController {

    @FXML
    private TextField apiV2Key;

    @FXML
    public void authenticateUser() {
        LoginStage loginStage =  (LoginStage)apiV2Key.getScene().getWindow();
        try {
            URL url = new URL("https://api.wanikani.com/v2/user");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + apiV2Key.getText());
            con.connect();

            if(con.getResponseCode() == 401) {
                con.disconnect();

                ExceptionDialog exceptionDialog = new ExceptionDialog(loginStage,
                        "API key may not be valid. Please check it is correct and try again");
                exceptionDialog.show();
                return;
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            loginStage.close();

            //TODO Replace this with the Home stage after we are done with the login stage
            LoginStage loginStage1 = new LoginStage();
            loginStage1.show();
            System.out.println(content);
        }  catch (UnknownHostException | NoRouteToHostException e) {
            ExceptionDialog exceptionDialog = new ExceptionDialog(loginStage,
                    "Could not connect to WaniKani API(api.wanikani.com). Please make sure you have an internet connection and try again");
            exceptionDialog.show();

        } catch (IOException e) {
            ExceptionDialog exceptionDialog = new ExceptionDialog(loginStage, e);
            exceptionDialog.show();
        }
    }
}
