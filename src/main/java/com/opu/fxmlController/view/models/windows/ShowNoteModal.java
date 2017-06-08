package com.opu.fxmlController.view.models.windows;

import com.opu.fxmlController.ShowNotePageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/*
    Модальное окно для отображения ифнормации о деле
 */
public class ShowNoteModal {
    public  void  newWindow(int id) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        String fxmlFile = "/fxml/showNotePage.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        stage.setTitle("Note");
        stage.setMinWidth(500);
        stage.setMinHeight(450);
        stage.setMaxWidth(500);
        //Получение контроллера для отображения дела
        ShowNotePageController snpController = loader.getController();
        //Передача идентификатора дела
        snpController.initialize(id);
        stage.setScene(new javafx.scene.Scene(root));
        stage.showAndWait();
    }
}
