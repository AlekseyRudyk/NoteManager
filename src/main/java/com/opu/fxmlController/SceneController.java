package com.opu.fxmlController;


import com.opu.fxmlController.view.models.sceneRes.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;

/**
 * Created by oASIS on 28.05.2017.
 */
public class SceneController {

    public SceneController(){}

    public void refresh(Node node, int flag){
        String fxmlFile;
        switch (flag){
            case 1:
                fxmlFile = "/fxml/mainPage.fxml";
                break;
            case 2:
                fxmlFile = "/fxml/notesPage.fxml";
                break;
            default:
                fxmlFile = "/fxml/mainPage.fxml";
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            Parent root = loader.load();
            node.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeScene(Scene sceneName, Node node) {
        Scene scene = sceneName;
        String fxmlFile = null;
        FXMLLoader loader;

        switch (scene) {

            case MAIN_PAGE:

                fxmlFile = "/fxml/mainPage.fxml";

                break;

            case ADD_NOTE_PAGE:

                fxmlFile = "/fxml/addNotePage.fxml";

                break;

        }

        loader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            Parent root = loader.load();
            node.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
