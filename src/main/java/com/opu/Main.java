package com.opu;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlFile));
        Parent root = loader.load();
        stage.setTitle("What To Do?");
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setMaxWidth(900);
        stage.setScene(new Scene(root));
        stage.show();

    }
}
