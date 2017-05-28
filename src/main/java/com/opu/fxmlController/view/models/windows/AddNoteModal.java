package com.opu.fxmlController.view.models.windows;

import com.opu.fxmlController.AddNoteController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by oASIS on 28.05.2017.
 */
public class AddNoteModal {

    public  void  newWindow(int id) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        String fxmlFile = "/fxml/addNotePage.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        AddNoteController anpController = loader.getController();
        stage.setTitle("Add note");
        stage.setMinWidth(500);
        stage.setMinHeight(450);
        stage.setMaxWidth(500);
        stage.setScene(new Scene(root));
        anpController.initialize(id);
        stage.showAndWait();
    }
}
