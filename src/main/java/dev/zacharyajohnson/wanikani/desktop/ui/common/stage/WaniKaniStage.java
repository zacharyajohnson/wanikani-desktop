package dev.zacharyajohnson.wanikani.desktop.ui.common.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.MalformedURLException;

public abstract class WaniKaniStage extends Stage {
    protected WaniKaniStage() {
        this.initStyle(StageStyle.DECORATED);
        this.getIcons().add(new Image("/gui/common/app_icon.png"));
        this.setTitle("WaniKani Desktop");
    }

    protected final void setStageTitle(String title) {
        this.setTitle(this.getTitle() + " - " + title);
    }

    protected final void loadFXML(String classPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(classPath));

        try {
            Scene scene = new Scene(loader.load());
            this.setScene(scene);
        } catch(MalformedURLException e) {
            System.err.println("Could not find background image from " + classPath + "\n\n" + e.getCause());
        } catch (IOException e) {
            //TODO Fix this to print actual stack traces and not just print to standard err
            System.err.println("Could not load background image from disk\n\n" + e.getCause());
        }
    }
}
