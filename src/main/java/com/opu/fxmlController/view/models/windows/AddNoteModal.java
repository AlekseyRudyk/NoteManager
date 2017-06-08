package com.opu.fxmlController.view.models.windows;

import com.opu.fxmlController.AddNoteController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


/*
 Модальное окно для добавления дела
 */
public class AddNoteModal {

    public  void  newWindow(int id) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        String fxmlFile = "/fxml/addNotePage.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        //Получение контроллера для добавления дела
        AddNoteController anpController = loader.getController();
        stage.setTitle("Add note");
        stage.setMinWidth(500);
        stage.setMinHeight(450);
        stage.setMaxWidth(500);
        stage.setScene(new Scene(root));
        //Передача идентификатора категории
        anpController.initialize(id);
        stage.showAndWait();
    }
}
