package com.github.zjohnson.wanikani.gui.common;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WaniKaniStage extends Stage {

    private final String APP_TITLE = "Login";
    private final Image APP_ICON = new Image("/gui/common/app_icon.png");

    private static WaniKaniStage waniKaniStage;

    private WaniKaniStage() {
        this.setTitle(APP_TITLE);
        this.initStyle(StageStyle.DECORATED);
        this.getIcons().add(APP_ICON);

    }

    public static WaniKaniStage getInstance() {
        if(waniKaniStage == null) {
            waniKaniStage = new WaniKaniStage();
        }

        return waniKaniStage;
    }

}
