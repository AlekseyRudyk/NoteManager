package com.opu.fxmlController.view.models.windows;

import com.opu.fxmlController.EditNotePageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by oASIS on 28.05.2017.
 */
public class EditNoteModal {
    public  void  newWindow(int id) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        String fxmlFile = "/fxml/editNotePage.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        stage.setTitle("Edit note");
        stage.setMinWidth(500);
        stage.setMinHeight(450);
        stage.setMaxWidth(500);
        EditNotePageController enpController = loader.getController();
        enpController.initialize(id);
        stage.setScene(new javafx.scene.Scene(root));
        stage.showAndWait();
    }
}
