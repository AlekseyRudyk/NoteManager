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

public class NotesPageController {

    //Ссылки на элементы View, расположенные в xml файле
    @FXML
    private VBox notePanel;

    @FXML
    private Label categoryNameLabel;

    @FXML
    private Button backButton;

    @FXML
    private Button addNoteButton;

    //Объекты контроллеров
    private EntitiesController ec;
    private SceneController sc;

    @FXML
    public void initialize (int id){
        ec = new EntitiesController();
        sc = new SceneController();

        //Отбработчик событий для кнопки добавление дела, который открывает модальное окно
        addNoteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Объект модального окна
                AddNoteModal modal = new AddNoteModal();
                try {
                    //Открываем модальное окно и передаём туда идентификатор категории
                    modal.newWindow(id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Перезагружаем страницу(сцену) с делами, чтобы увидеть изменения на странице
                sc.changeSceneWithId(Scene.NOTES_PAGE, notePanel,id);
                //Закрываем подключение к БД
                ec.closeConnection();
            }
        });

        //Обработчки событий для кнопки назад, которая возвращает нас на страницу с категориями
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Изменение сцены
                sc.changeScene(Scene.MAIN_PAGE,notePanel);
                //Закрытие подключения к БД
                ec.closeConnection();
            }
        });

        //Получаем количество дел
        int noteNum = ec.getNotesNum(id);

        if (noteNum >= 0) {
            //Созадем ArrayList дел
            ArrayList<Note> notes;

            //Задаем название категории, которую мы выбрали на первой странице
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

                    //Обработчик событий для боксов, который вызывает модальное окно с информацией о деле
                    noteBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

                        @Override
                        public void handle(MouseEvent event) {
                            int id = noteBox.getNoteId();
                            //Создаем объект модального окна
                            ShowNoteModal modal = new ShowNoteModal();
                            try {
                                //Открываем окно и передаем идентификатор дела
                                modal.newWindow(id);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });

                    HBox box = (HBox) noteBox.getChildren().get(0);
                    //Добавление обработчика событий для кнопки для редактирования дела
                    box.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            //Создаем объект модального окна
                            EditNoteModal modal = new EditNoteModal();
                            try {
                                //Открываем окно и передаем идентификатор дела
                                modal.newWindow(noteBox.getNoteId());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //Закрываем соединение к БД
                            ec.closeConnection();
                            //перезагружаем сцену
                            sc.changeSceneWithId(Scene.NOTES_PAGE, notePanel,id);

                        }
                    });

                    //Обработчки событий для кнопки удаления дела
                    box.getChildren().get(1).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            //Вызываем мето удаления дела из БД
                            ec.deleteNote(noteBox.getNoteId());
                            //Закрываем подключение к бд
                            ec.closeConnection();
                            //Перезагружаем сцену
                            new SceneController().changeSceneWithId(Scene.NOTES_PAGE, notePanel,id);

                        }
                    });
                    row.getChildren().add(noteBox);
                    HBox.setMargin(noteBox, new Insets(10, 0, 5, 10));
                    row.setPadding(new Insets(0,0,0,4));

                    //Проверка количества боксов в строке
                    if((i+1)%5==0){
                        i++;
                        break;
                    }
                }
                //Добавление новой строки
                notePanel.getChildren().add(row);
                j++;
            }
        } 

    }
}