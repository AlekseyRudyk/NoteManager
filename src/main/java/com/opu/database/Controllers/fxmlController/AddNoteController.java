package com.opu.database.Controllers.fxmlController;

import com.opu.database.Controllers.EntitiesController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Created by antipavitaly on 4/27/17.
 */

public class AddNoteController {
    @FXML
    private AnchorPane pane;

    private String noteName;
    private String noteCategory;
    private String noteComment;
    private String noteSubnote;
    private String noteFinalDate;

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
    private DatePicker finalDatePicker;



    @FXML
    public void initialize() {



            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {


                @Override
                public void handle(MouseEvent mouseEvent) {


                    if (noteNameField.getText().equals("") || noteCategoryField.getText().equals("") || noteCommentField.getText().equals("") || noteSubnoteField.getText().equals("") || finalDatePicker.getValue() == null) {

                        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                        dialog.setHeaderText("Error");
                        dialog.setContentText("Fill all the fields!");
                        dialog.showAndWait();


                    } else {

                        noteName = noteNameField.getText();
                        noteCategory = noteCategoryField.getText();
                        noteComment = noteCommentField.getText();
                        noteSubnote = noteSubnoteField.getText();
                        noteFinalDate = finalDatePicker.getValue().toString();

                        new EntitiesController().addNote(noteName, noteSubnote, noteFinalDate, noteComment, noteCategory);

                        noteNameField.clear();
                        noteCategoryField.clear();
                        noteCommentField.clear();
                        noteSubnoteField.clear();
                        finalDatePicker.getEditor().clear();


                        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                        dialog.setHeaderText("Success");
                        dialog.setContentText("The note was created!");
                        dialog.showAndWait();
//                        dialog.setOnCloseRequest(new EventHandler<DialogEvent>() {
//                            @Override
//                            public void handle(DialogEvent event) {
//                                String fxmlFile = "/fxml/mainPage.fxml";
//                                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
//                                Parent root = null;
//                                try {
//                                    root = loader.load();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//
//                                pane.getScene().setRoot(root);
//                            }
//                        });

                    }
                }
            });
    }
}









