package com.opu.fxmlController;

import com.opu.database.Controllers.EntitiesController;
import com.opu.database.entities.Note;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by antipavitaly on 5/27/17.
 */
public class ShowNotePageController {

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

    int noteId;

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


        ec = new EntitiesController();

        note = ec.getNoteById(noteId);

        getAndSetValues(note);
        setUneditable();
    }

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
        progressLabel.setText(String.valueOf(note.cutProgress(progress)));

    }
    private void setUneditable(){
        noteNameField.setEditable(false);
        noteCommentField.setEditable(false);
        noteSubnoteField.setEditable(false);
        finalDateField.setEditable(false);
        categoryNameField.setEditable(false);
        startDateField.setEditable(false);

    }


}