package com.opu.fxmlController;

import com.opu.database.controllers.EntitiesController;
import com.opu.database.entities.Note;
import com.opu.fxmlController.view.models.boxes.NoteBox;
import com.opu.fxmlController.view.models.sceneRes.Scene;
import com.opu.fxmlController.view.models.windows.AddNoteModal;
import com.opu.fxmlController.view.models.windows.EditNoteModal;
import com.opu.fxmlController.view.models.windows.ShowNoteModal;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by antipavitaly on 5/24/17.
 */
public class NotesPageController {

    @FXML
    private VBox notePanel;

    @FXML
    private Label categoryNameLabel;

    @FXML
    private Button backButton;

    @FXML
    private Button addNoteButton;

    private EntitiesController ec;
    private SceneController sc;

    @FXML
    public void initialize (int id){
        ec = new EntitiesController();
        sc = new SceneController();

        addNoteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AddNoteModal modal = new AddNoteModal();
                try {
                    modal.newWindow(id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sc.changeSceneWithId(Scene.NOTES_PAGE, notePanel,id);
                ec.closeConnection();
            }
        });

        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sc.changeScene(Scene.MAIN_PAGE,notePanel);
                ec.closeConnection();
            }
        });

        int noteNum = ec.getNotesNum(id);

        if (noteNum >= 0) {
            ArrayList<Note> notes;
            if(id==0) {
                categoryNameLabel.setText("All notes");
                notes = ec.getNotes();
            } else {
                categoryNameLabel.setText(ec.getCategoryName(id));
                notes = ec.getNotesByCategoryId(id);
            }
            int rowNum = (int) Math.ceil(noteNum/5.0);
            int i = 0;
            int j = 0;
            while (j!=rowNum){
                HBox row = new HBox();
                row.setId("box"+j);

                for (; i < noteNum; i++) {

                    NoteBox noteBox = new NoteBox(notes.get(i));

                    noteBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

                        @Override
                        public void handle(MouseEvent event) {
                            int id = noteBox.getNoteId();
                            ShowNoteModal modal = new ShowNoteModal();
                            try {
                                modal.newWindow(id);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });

                    HBox box = (HBox) noteBox.getChildren().get(0);
                    box.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            EditNoteModal modal = new EditNoteModal();
                            try {
                                modal.newWindow(noteBox.getNoteId());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ec.closeConnection();
                            sc.changeSceneWithId(Scene.NOTES_PAGE, notePanel,id);

                        }
                    });

                    box.getChildren().get(1).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            ec.deleteNote(noteBox.getNoteId());
                            ec.closeConnection();
                            new SceneController().changeSceneWithId(Scene.NOTES_PAGE, notePanel,id);

                        }
                    });
                    row.getChildren().add(noteBox);
                    HBox.setMargin(noteBox, new Insets(10, 0, 5, 10));
                    row.setPadding(new Insets(0,0,0,4));

                    if((i+1)%5==0){
                        i++;
                        break;
                    }
                }
                notePanel.getChildren().add(row);
                j++;
            }
        } else {
            Label noting = new Label("");
        }

    }
}