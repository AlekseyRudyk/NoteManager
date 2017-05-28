package com.opu;

import com.opu.database.Controllers.EntitiesController;
import com.opu.database.entities.Note;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

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
        setPadding(new Insets(0, 0, 0, 20));
        setStyle("-fx-background-color:#ccc;");
        setCursor(Cursor.HAND);

        Button deleteButton = new Button("X");
        deleteButton.setMaxSize(10,10);
        deleteButton.setFocusTraversable(false);
        setMargin(deleteButton,new Insets(5,5,0,100));
        deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EntitiesController ec = new EntitiesController();
                ec.deleteNote(note.getId());
                //refresh
                new Main().refresh(deleteButton,1);
            }
        });
        getChildren().add(deleteButton);

        Label name = new Label(note.getNoteName());
        name.setMaxWidth(160);
        name.setAlignment(Pos.CENTER);
        name.setFont(Font.font(17));

        getChildren().add(name);

        setMargin(name,new Insets(30,15,0,0));



    }
}
