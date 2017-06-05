package com.opu.fxmlController;

import com.opu.database.controllers.EntitiesController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by antipavitaly on 4/27/17.
 */

public class AddNoteController {

    //Ссылки на элементы View, расположенные в xml файле
    @FXML
    private AnchorPane pane;

    @FXML
    private Button button;

    @FXML
    private TextField noteNameField;

    @FXML
    private ChoiceBox categoriesNameField;

    @FXML
    private TextArea noteSubnoteField;

    @FXML
    private TextArea noteCommentField;

    @FXML
    private DatePicker finalDatePicker;

    //Необходимые для работы поля
    private String noteName;
    private String noteCategory;
    private String noteComment;
    private String noteSubnote;
    private String noteFinalDate;

    private EntitiesController ec;

    @FXML
    public void initialize(int catId) {

        ec = new EntitiesController();

        choiceBoxValues(categoriesNameField);
        noteCategory = ec.getCategoryName(catId);
        categoriesNameField.setValue(noteCategory);


        //Обработчик событий для добавление дела
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                //Валидация заполненных полей
                if (noteNameField.getText().equals("") || categoriesNameField.getValue().toString().equals(null)|| noteCommentField.getText().equals("") || noteSubnoteField.getText().equals("") || finalDatePicker.getValue() == null) {
                    //Вывод диалогового окна с сообщением об ошибке
                    Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                    dialog.setHeaderText("Error");
                    dialog.setContentText("Fill all the fields!");
                    dialog.showAndWait();

                } else {

                    noteName = noteNameField.getText();
                    noteCategory = categoriesNameField.getValue().toString();
                    noteComment = noteCommentField.getText();
                    noteSubnote = noteSubnoteField.getText();
                    noteFinalDate = finalDatePicker.getValue().toString();

                    //Добавление дела в БД
                    ec.addNote(noteName, noteSubnote, noteFinalDate, noteComment, noteCategory);

                    //Вывод диалогового окна с сообщением об успехе
                    noteNameField.clear();
                    categoriesNameField.setValue(null);
                    noteCommentField.clear();
                    noteSubnoteField.clear();
                    finalDatePicker.getEditor().clear();

                    Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                    dialog.setHeaderText("Success");
                    dialog.setContentText("The note was created!");
                    dialog.showAndWait();

                    //Закрытие окна
                    Stage stage = (Stage) pane.getScene().getWindow();
                    stage.close();
                }

            }
        });
    }

    /*
    Метод для заполнения элемента с категориями, в котором мы получаем
    все категории из БД, а затем добавляем в список и добавляем в элемент
    View ChoiceBox.
     */
    private void choiceBoxValues(ChoiceBox chB){
        ArrayList<String> categoryNames =  ec.getCategoryNames();
        ObservableList<String> names = FXCollections.observableArrayList();

        for (String n: categoryNames) {
            names.add(n);
        }

        chB.setItems(names);

    }
}