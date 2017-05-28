package com.opu.fxmlController.view.models.boxes;


import com.opu.database.entities.Note;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Created by oASIS on 27.05.2017.
 */
public class NoteBox extends VBox {
    private Note note;

    public NoteBox(Note note){
        this.note = note;

        setMinWidth(160);
        setMinHeight(170);
        setPrefWidth(160);
        setPrefHeight(170);
        setStyle("-fx-background-color:#fff;");
        setStyle("-fx-border-color:#00c6d2;");
        setPadding(new Insets(0, 0, 0, 20));

        setCursor(Cursor.HAND);

        Button deleteButton = new Button();
        deleteButton.setStyle("-fx-background-image:  url('/image/close-icon.png')");
        deleteButton.setMinWidth(31);
        deleteButton.setMinHeight(30);
        deleteButton.setFocusTraversable(false);

        Button editButton = new Button();

        editButton.setStyle("-fx-background-image:  url('/image/edit-icon.png')");
        editButton.setMinWidth(31);
        editButton.setMinHeight(31);
        editButton.setFocusTraversable(false);

        HBox buttonLine = new HBox();
        buttonLine.getChildren().addAll(editButton,deleteButton);

        HBox.setMargin(deleteButton,new Insets(5,5,0,70));
        HBox.setMargin(editButton,new Insets(5,0,0,0));

        Label noteName = new Label(note.getNoteName());
        noteName.setMaxWidth(160);
        noteName.setAlignment(Pos.CENTER);
        noteName.setFont(Font.font(16));

        Label date = new Label(note.getNoteFinalDate());
        date.setMaxWidth(160);
        date.setAlignment(Pos.CENTER);
        date.setFont(Font.font(14));

        Label progress = new Label("Progress: " + String.valueOf(note.cutProgress(note.getProgress())) + "%");
        progress.setMaxWidth(160);
        progress.setAlignment(Pos.CENTER);
        progress.setFont(Font.font(14));

        getChildren().addAll(buttonLine, noteName ,date,progress);
        setMargin(buttonLine,new Insets(0,0,0,0));
        setMargin(noteName,new Insets(10,15,0,0));
        setMargin(date,new Insets(20,15,0,0));
        setMargin(progress,new Insets(30,15,0,0));



    }
    public int getNoteId(){
        return this.note.getId();
    }
}
