package com.opu.fxmlController;

import com.opu.CategoryBox;
import com.opu.Main;
import com.opu.database.Controllers.EntitiesController;
import com.opu.database.entities.Category;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by antipavitaly on 4/27/17.
 */
public class MainPageController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private VBox categoryPanel;

    @FXML
    private Label notesNum;

    @FXML
    private ImageView addImage;

    @FXML
    private VBox addCategoryBox;

    private EntitiesController ec;

    @FXML
    public void initialize (){
        ec = new EntitiesController();
        notesNum.setText("" + ec.getNotesNum(0));
        addImage.setImage(new Image("/image/add-icon.png"));

        addCategoryBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                TextInputDialog dialog = new TextInputDialog("");
                dialog.setTitle("Новая категория");
                dialog.setHeaderText("Введите название категории");
                dialog.setContentText("Введите название категории:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    ec.addCategory(result.get());
                }
                new Main().refresh(categoryPanel);
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
                            new Main().changeScene("EditNotePage", mainPane);
                        }
                    });

                    row.getChildren().add(categoryBox);
                    row.setMargin(categoryBox, new Insets(10, 0, 5, 10));
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
