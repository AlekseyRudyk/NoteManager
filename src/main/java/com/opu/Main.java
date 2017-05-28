package com.opu;

import com.opu.fxmlController.EditNotePageController;
import com.opu.fxmlController.NotesPageController;
import com.opu.fxmlController.ShowNotePage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by antipavitaly on 4/19/17.
 */
public class Main extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/fxml/mainPage.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        stage.setTitle("What To Do?");
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setMaxWidth(900);
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void refresh(Node node, int flag){
        String fxmlFile;
        switch (flag){
            case 1:
                fxmlFile = "/fxml/mainPage.fxml";
                break;
            case 2:
                fxmlFile = "/fxml/NotesPage.fxml";
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

    public void changeScene(String sceneName, Node node) {
        String scene = sceneName;
        String fxmlFile = null;
        FXMLLoader loader;

        switch (scene) {

            case "MainPage":

                fxmlFile = "/fxml/mainPage.fxml";

                break;

            case "AddNotePage":

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

    public void changeSceneWithId(String sceneName, Node node, int id){
        String scene = sceneName;
        String fxmlFile = null;
        FXMLLoader loader = null;
        int noteId = id;

        switch (scene){

            case "NotePage":

                fxmlFile = "/fxml/notesPage.fxml";
                loader = new FXMLLoader(getClass().getResource(fxmlFile));
                NotesPageController npController = loader.getController();
                npController.initialize(noteId);

                break;

            case "EditNotePage":

                fxmlFile = "/fxml/editNotePage.fxml";
                loader = new FXMLLoader(getClass().getResource(fxmlFile));
                EditNotePageController enpController = loader.getController();
                enpController.initialize(noteId);

                break;


            case "ShowNotePage":

                fxmlFile = "/fxml/showNotePage.fxml";
                loader = new FXMLLoader(getClass().getResource(fxmlFile));
                ShowNotePage snpController = loader.getController();
                snpController.initialize(noteId);

                break;
        }


        try {
            Parent root = loader.load();
            node.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }}