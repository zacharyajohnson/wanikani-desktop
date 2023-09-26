package dev.zacharyajohnson.wanikani.javafx.ui.common.exception;

import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionDialog extends Alert {
    public ExceptionDialog(Stage stage, String message) {
        this();
        this.initOwner(stage);
        this.setContentText(message);
    }

    public ExceptionDialog(Throwable e) {
        this();
        this.createExceptionDisplayArea(e);
    }

    private ExceptionDialog() {
        super(AlertType.ERROR, "", ButtonType.OK);
        this.setHeaderText(null);
        this.setResizable(true);
    }

    private void createExceptionDisplayArea(Throwable e) {
        this.setContentText("An exception has occurred: ");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);

        pw.close();

        TextArea textArea = new TextArea(sw.toString());
        textArea.setWrapText(true);
        textArea.setEditable(false);

        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane gridPane = new GridPane();
        gridPane.add(textArea, 0, 0);

        this.getDialogPane().setExpandableContent(gridPane);
        this.getDialogPane().setExpanded(true);
    }
}
