package com.opu.fxmlController;

import com.opu.database.controllers.EntitiesController;
import com.opu.database.entities.Note;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by oASIS on 28.05.2017.
 */
public class EditNotePageController {

    //Ссылки на элементы View, расположенные в xml файле
    @FXML
    private Button button;

    @FXML
    private TextField noteNameField;

    @FXML
    private Slider progressSlider;

    @FXML
    private TextArea noteSubnoteField;

    @FXML
    private TextArea noteCommentField;

    @FXML
    private DatePicker finalDatePicker;

    @FXML
    private ChoiceBox categoriesNameField;

    @FXML
    private TextField startDateField;

    @FXML
    private Label persentageLabel;

    //Необходимые для работы поля
    private String noteName;
    private String noteCategory;
    private String noteComment;
    private String noteSubnote;
    private String noteStartDate;
    private String noteFinalDate;
    private float progress;

    Note note;

    EntitiesController ec;


    public void initialize(int noteId) {

        ec = new EntitiesController();
        note = ec.getNoteById(noteId);

        getAndSetValues(note);
        initSlider(progressSlider);

        //Обработчик событий для кнопки добавление дела
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                //Валидация заполненных полей
                if (noteNameField.getText().equals("") || categoriesNameField.getValue().toString().equals(null) || noteCommentField.getText().equals("") || noteSubnoteField.getText().equals("") || finalDatePicker.getValue() == null) {

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
                    noteStartDate = startDateField.getText();

                    //Обновление дела в БД
                    ec.updateNote(noteId,noteName, noteSubnote, noteFinalDate,noteStartDate, noteComment, noteCategory, progress);

                    //Вывод диалогового окна с сообщением об успехе
                    Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                    dialog.setHeaderText("Success");
                    dialog.setContentText("The note was updated!");
                    dialog.showAndWait();

                    //Закрытие сцены
                    Stage stage = (Stage) button.getScene().getWindow();
                    stage.close();
                }

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

        choiceBoxValues(categoriesNameField);
        categoriesNameField.setValue(noteCategory);

        noteNameField.setText(noteName);
        noteCommentField.setText(noteComment);
        noteSubnoteField.setText(noteSubnote);
        finalDatePicker.setValue(localDate(noteFinalDate));
        startDateField.setText(noteStartDate);
        persentageLabel.setText(String.valueOf(note.cutProgress(progress)) + "%");

        startDateField.setEditable(false);


    }

    /*
    Метод для заполнения элемента с категориями, в котором мы получаем
    все категории из БД, а затем добавляем в список и добавляем в элемент
    View ChoiceBox.
     */
    private void choiceBoxValues(ChoiceBox chB){
        ArrayList<String> categoryNames =  ec.getCategoryNames();
        ObservableList<String> names = FXCollections.observableArrayList();

        for (String n: categoryNames)
        {

            names.add(n);
        }

        chB.setItems(names);

    }

    //Инициализация слайдера с прогрессом дела
    private void initSlider(Slider slider){

        slider.setMin(0.0);
        slider.setMax(100.0);
        slider.setValue(progress);
        slider.setShowTickMarks(true);
        slider.valueProperty().addListener(((observable, oldValue, newValue) -> {

            progress = newValue.floatValue();
            persentageLabel.setText(String.valueOf(note.cutProgress(progress)) + "%");

        }));
    }

    //Метод для преобразования даты из БД, чтобы засунуть значение в элементе View DatePicker
    private LocalDate localDate (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

}
