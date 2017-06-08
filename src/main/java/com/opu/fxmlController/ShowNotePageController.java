package com.opu.fxmlController;

import com.opu.database.controllers.EntitiesController;
import com.opu.database.entities.Note;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ShowNotePageController {

    //Ссылки на элементы View, расположенные в xml файле
    @FXML
    private Button button;

    @FXML
    private TextField noteNameField;

    @FXML
    private Label progressLabel;

    @FXML
    private TextArea noteSubnoteField;

    @FXML
    private TextArea noteCommentField;

    @FXML
    private TextField finalDateField;

    @FXML
    private TextField categoryNameField;

    @FXML
    private TextField startDateField;

    //Поля для выполнения необходимых операций
    private String noteName;
    private String noteCategory;
    private String noteComment;
    private String noteSubnote;
    private String noteStartDate;
    private String noteFinalDate;
    private float progress;

    Note note;

    EntitiesController ec;

    @FXML
    public void initialize(int noteId){

        //Инициализация объекта контроллера
        ec = new EntitiesController();

        //Получения дела из БД по идентификатору
        note = ec.getNoteById(noteId);

        getAndSetValues(note);
        setUneditable();

        //Обработчик событий для кнопки, который закрывает окно
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) button.getScene().getWindow();
                stage.close();
            }
        });
    }

    /*
    Метод, который принимает объект дела(Note),
    вытаскивает из него все необходимые значения,
    а затем заполняет View элементы страницы.
     */
    private void getAndSetValues(Note note){
        noteName = note.getNoteName();
        noteComment = note.getNoteComment();
        noteSubnote = note.getNoteSubnote();
        noteFinalDate = note.getNoteFinalDate();
        progress = note.getProgress();
        noteStartDate = note.getNoteStartDate();
        noteCategory = note.getCategory().getCategoryName();

        noteNameField.setText(noteName);
        noteCommentField.setText(noteComment);
        noteSubnoteField.setText(noteSubnote);
        finalDateField.setText(noteFinalDate);
        categoryNameField.setText(noteCategory);
        startDateField.setText(noteStartDate);
        progressLabel.setText(String.valueOf(note.cutProgress(progress)) +"%");

    }

    /*
    Метод, который делает все элементы View
    непригодынми для взаимодействия
     */
    private void setUneditable(){
        noteNameField.setEditable(false);
        noteCommentField.setEditable(false);
        noteSubnoteField.setEditable(false);
        finalDateField.setEditable(false);
        categoryNameField.setEditable(false);
        startDateField.setEditable(false);

    }


}