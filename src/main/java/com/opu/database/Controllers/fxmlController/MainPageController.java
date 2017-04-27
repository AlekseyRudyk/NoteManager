package com.opu.database.Controllers.fxmlController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * Created by antipavitaly on 4/27/17.
 */
public class MainPageController {
    private static int i = 1;

    @FXML
    private Label nNotes;
    public void onClick(MouseEvent mouseEvent) {

        nNotes.setText(i+" заметок");

    }
}
