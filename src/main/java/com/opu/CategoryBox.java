package com.opu;

import com.opu.database.Controllers.EntitiesController;
import com.opu.database.entities.Category;
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
 * Created by oASIS on 25.05.2017.
 */
public class CategoryBox extends VBox{
    private Category category;

    public CategoryBox(Category category){

        this.category = category;

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
                ArrayList<Note> notes = ec.getNotesByCategoryId(category.getId());
                for(Note note : notes){
                    ec.deleteNote(note.getId());
                }
                ec.deleteCategory(category.getId());

                //refresh
                new Main().refresh(deleteButton);
            }
        });
        getChildren().add(deleteButton);

        Label name = new Label(category.getCategoryName());
        name.setMaxWidth(160);
        name.setAlignment(Pos.CENTER);
        name.setFont(Font.font(17));
        Label num = new Label(new EntitiesController().getNotesNum(category.getId()) + " ");
        num.setPrefWidth(160);
        num.setAlignment(Pos.CENTER);
        num.setFont(Font.font(14));

        getChildren().add(name);
        getChildren().add(num);
        setMargin(name,new Insets(30,15,0,0));
        setMargin(num,new Insets(10,15,0,0));


    }

    public int getCategoryId(){
        return this.category.getId();
    }
}
