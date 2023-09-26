package dev.zacharyajohnson.wanikani.javafx.ui.home;

import dev.zacharyajohnson.wanikani.javafx.ui.common.stage.WaniKaniStage;
import javafx.stage.Screen;

public class HomeStage extends WaniKaniStage {
    public HomeStage() {
        super();

        super.setStageTitle("Home");
        super.loadFXML("/home/home-page.fxml");

        this.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        this.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
    }
}
