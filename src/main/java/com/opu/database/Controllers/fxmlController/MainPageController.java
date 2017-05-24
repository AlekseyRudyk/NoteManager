package com.opu.database.Controllers.fxmlController;

import com.opu.database.Controllers.EntitiesController;
import com.opu.database.entities.Category;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by antipavitaly on 4/27/17.
 */
public class MainPageController {
    @FXML
    private VBox categoryBox;

    @FXML
    private Label notesNum;

    @FXML
    private ImageView addImage;

    @FXML
    private VBox addBox;

    @FXML
    public void initialize (){
        notesNum.setText("" + new EntitiesController().getNotesNum(0));
        addImage.setImage(new Image("/image/add-icon.png"));
        System.out.println("1");
        addBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                TextInputDialog dialog = new TextInputDialog("");
                dialog.setTitle("Новая категория");
                dialog.setHeaderText("Введите название категории");
                dialog.setContentText("Введите название категории:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    new EntitiesController().addCategory(result.get());
                }
            }
        });

        int categoryNum = new EntitiesController().getCategoryNum();
        if (categoryNum > 0) {
            ArrayList<Category> categories = new EntitiesController().getCategories();
            int rowNum = (int) Math.ceil(categoryNum/5.0);
            int i = 0;
            int j = 0;
            while (j!=rowNum){
                HBox row = new HBox();
                row.setId("box"+j);

                for (; i < categoryNum; i++) {

                    VBox categoryBox = new VBox();

                    categoryBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

                        @Override
                        public void handle(MouseEvent event) {

                        }
                    });
                    categoryBox.setMinWidth(160);
                    categoryBox.setMinHeight(170);
                    categoryBox.setPrefWidth(160);
                    categoryBox.setPrefHeight(170);
                    categoryBox.setPadding(new Insets(0, 0, 0, 20));
                    categoryBox.setStyle("-fx-background-color:#ccc;");
                    categoryBox.setCursor(Cursor.HAND);
                    Label name = new Label(categories.get(i).getCategoryName());
                    name.setMaxWidth(160);
                    name.setAlignment(Pos.CENTER);
                    name.setFont(Font.font(17));
                    Label num = new Label(new EntitiesController().getNotesNum(categories.get(i).getId()) + " ");
                    num.setPrefWidth(160);
                    num.setAlignment(Pos.CENTER);
                    num.setFont(Font.font(14));

                    categoryBox.getChildren().add(name);
                    categoryBox.getChildren().add(num);
                    categoryBox.setMargin(name,new Insets(30,15,0,0));
                    categoryBox.setMargin(num,new Insets(10,15,0,0));

                    row.getChildren().add(categoryBox);
                    row.setMargin(categoryBox, new Insets(10, 0, 5, 10));
                    row.setPadding(new Insets(0,0,0,4));


                    if((i+1)%5==0){
                        i++;
                        break;
                    }
                }

                categoryBox.getChildren().add(row);
                j++;
            }
        }
    }
}
