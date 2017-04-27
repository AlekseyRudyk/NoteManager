package com.opu.database.Controllers.fxmlController;

import com.opu.database.Controllers.EntitiesController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Created by antipavitaly on 4/27/17.
 */

public class AddNoteController {

    private String noteName;
    private String noteCategory;
    private String noteComment;
    private String noteSubnote;

    @FXML
    private Button button;

    @FXML
    private TextField noteNameField;

    @FXML
    private TextField noteCategoryField;

    @FXML
    private TextArea noteSubnoteField;

    @FXML
    private TextArea noteCommentField;



    @FXML
    public void initialize() {
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    noteName = noteNameField.getText();
                    noteCategory = noteCategoryField.getText();
                    noteComment = noteCommentField.getText();
                    noteSubnote = noteSubnoteField.getText();

                    new EntitiesController().addNote(noteName,noteSubnote,noteCategory,noteComment);

                }
            });
        }

    }

