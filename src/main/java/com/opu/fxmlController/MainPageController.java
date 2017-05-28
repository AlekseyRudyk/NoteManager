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

/**
 * Created by antipavitaly on 4/27/17.
 */
public class MainPageController {
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

    private EntitiesController ec;
    private SceneController sc;

    @FXML
    public void initialize (){
        ec = new EntitiesController();
        sc = new SceneController();
        notesNum.setText("" + ec.getNotesNum(0));

        allNotes.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sc.changeSceneWithId(Scene.NOTES_PAGE,categoryPanel,0);
                ec.closeConnection();
            }
        });

        addImage.setImage(new Image("/image/plus-icon.png"));
        addCategoryBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                TextInputDialog dialog = new TextInputDialog("");
                dialog.setTitle("Новая категория");
                dialog.setHeaderText("Введите название категории");
                dialog.setContentText("");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    ec.addCategory(result.get());
                }
                new SceneController().refresh(categoryPanel,1);
            }
        });

        int categoryNum = ec.getCategoryNum();
        if (categoryNum > 0) {
            ArrayList<Category> categories = ec.getCategories();
            int rowNum = (int) Math.ceil(categoryNum/5.0);
            int i = 0;
            int j = 0;
            while (j!=rowNum){
                HBox row = new HBox();
                row.setId("box"+j);

                for (; i < categoryNum; i++) {

                    CategoryBox categoryBox = new CategoryBox(categories.get(i));
                    categoryBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

                        @Override
                        public void handle(MouseEvent event) {
                            int id = categoryBox.getCategoryId();
                            sc.changeSceneWithId(Scene.NOTES_PAGE,categoryPanel,id);
                            //ec.closeConnection();
                        }
                    });

                    row.getChildren().add(categoryBox);
                    HBox.setMargin(categoryBox, new Insets(10, 0, 5, 10));
                    row.setPadding(new Insets(0,0,0,4));

                    if((i+1)%5==0){
                        i++;
                        break;
                    }
                }

                categoryPanel.getChildren().add(row);

                j++;
            }
        }
    }
}
