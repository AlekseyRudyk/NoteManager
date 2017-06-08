package com.opu.fxmlController;

import com.opu.database.controllers.EntitiesController;
import com.opu.database.entities.Category;
import com.opu.fxmlController.view.models.boxes.CategoryBox;
import com.opu.fxmlController.view.models.sceneRes.Scene;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Optional;

import static com.opu.fxmlController.view.models.sceneRes.Scene.MAIN_PAGE;

public class MainPageController {

    //Ссылки на элементы View, расположенные в xml файле
    @FXML
    private VBox categoryPanel;

    @FXML
    private VBox allNotes;

    @FXML
    private Label notesNum;

    @FXML
    private ImageView addImage;

    @FXML
    private VBox addCategoryBox;

    //Объекты контроллеров
    private EntitiesController ec;
    private SceneController sc;

    @FXML
    public void initialize (){
        ec = new EntitiesController();
        sc = new SceneController();
        notesNum.setText("" + ec.getNotesNum(0));

        // Обработчик кнопки "Все заметки" (смена сцены)
        allNotes.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sc.changeSceneWithId(Scene.NOTES_PAGE,categoryPanel,0);
                ec.closeConnection();
            }
        });

        addImage.setImage(new Image("/image/plus-icon.png"));
        //Обработчик кнопки "Добавить категорию"
        addCategoryBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                //Создание диалогового окна с текстовым полем
                TextInputDialog dialog = new TextInputDialog("");
                dialog.setTitle("Новая категория");
                dialog.setHeaderText("Введите название категории");
                dialog.setContentText("");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    ec.addCategory(result.get());
                }
                sc.changeScene(MAIN_PAGE,categoryPanel);
            }
        });

        int categoryNum = ec.getCategoryNum();
        // Создание блоков с категориями по пять в ряд
        if (categoryNum > 0) {
            ArrayList<Category> categories = ec.getCategories();

            int rowNum = (int) Math.ceil(categoryNum/5.0); // Количество строк
            int i = 0;
            int j = 0;
            while (j!=rowNum){
                HBox row = new HBox();

                for (; i < categoryNum; i++) {
                    // Создание блока категории
                    CategoryBox categoryBox = new CategoryBox(categories.get(i));
                    // Обработчик нажатия на категорию
                    categoryBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

                        @Override
                        public void handle(MouseEvent event) {
                            int id = categoryBox.getCategoryId();
                            // Переход к заметкам этой категории
                            sc.changeSceneWithId(Scene.NOTES_PAGE,categoryPanel,id);
                            ec.closeConnection();
                        }
                    });
                    // Добавление блока в строку
                    row.getChildren().add(categoryBox);
                    HBox.setMargin(categoryBox, new Insets(10, 0, 5, 10));
                    row.setPadding(new Insets(0,0,0,4));
                    //Если 5 блоков в строке, выйти с цикла for и перейти на новую строку
                    if((i+1)%5==0){
                        i++;
                        break;
                    }
                }

                categoryPanel.getChildren().add(row); // добавление строки на панель

                j++;
            }
        }
    }
}
