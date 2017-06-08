package com.opu.fxmlController;


import com.opu.fxmlController.view.models.sceneRes.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;

public class SceneController {

    public SceneController(){}

    /*
    Метод, который принимает элемент enum с именем сцены,
    а затем элемент View ветки сцены, а затем перезагружает
    fxml файл сцены. Данный метод помогает нам обновлять страницу,
    а так же менять сцену.
     */
    public void changeScene(Scene sceneName, Node node) {
        Scene scene = sceneName;
        String fxmlFile = null;
        FXMLLoader loader;

        switch (scene) {

            case MAIN_PAGE:

                //Путь к xml документу
                fxmlFile = "/fxml/mainPage.fxml";

                break;

            case ADD_NOTE_PAGE:

                fxmlFile = "/fxml/addNotePage.fxml";

                break;

        }

        //Загрузка документа
        loader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            Parent root = loader.load();
            //Присваивание пути сцены
            node.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Метод, который меняет сцену, передавая в неё идентификатор.
    Данный метод необходим для передачи идентификатора категории
    или дела.
     */
    public void changeSceneWithId(Scene sceneName, Node node, int id){
        Scene scene = sceneName;
        String fxmlFile;
        FXMLLoader loader;

        switch (scene){

            case NOTES_PAGE:

                fxmlFile = "/fxml/notesPage.fxml";
                loader = new FXMLLoader(getClass().getResource(fxmlFile));
                try {
                    Parent root = loader.load();
                    NotesPageController npController = loader.getController();
                    npController.initialize(id);
                    node.getScene().setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case EDIT_NOTE_PAGE:

                fxmlFile = "/fxml/editNotePage.fxml";
                loader = new FXMLLoader(getClass().getResource(fxmlFile));
                try {
                    Parent root = loader.load();
                    EditNotePageController enpController = loader.getController();
                    enpController.initialize(id);
                    node.getScene().setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;


            case SHOW_NOTE_PAGE:

                fxmlFile = "/fxml/showNotePage.fxml";
                loader = new FXMLLoader(getClass().getResource(fxmlFile));
                try {
                    Parent root = loader.load();
                    ShowNotePageController snpController = loader.getController();
                    snpController.initialize(id);
                    node.getScene().setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
