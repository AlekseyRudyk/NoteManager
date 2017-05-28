package com.opu.fxmlController;

import com.opu.database.Controllers.EntitiesController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by antipavitaly on 5/24/17.
 */
public class NotesPageController {

    @FXML
    private VBox noteBox;

    @FXML
    private Label categoryNameLabel;

    @FXML
    private Button backButton;

    @FXML
    private Button addNoteButton;

    private EntitiesController ec;

    @FXML
    public void initialize (int id){
        ec = new EntitiesController();




    }
}