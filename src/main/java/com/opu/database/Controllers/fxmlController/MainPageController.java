package com.opu.database.Controllers.fxmlController;

//import com.opu.Main;
import com.opu.database.Controllers.EntitiesController;
import com.opu.database.entities.Category;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
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
    private VBox addCategoryBox;

    @FXML
    public void initialize (){
        notesNum.setText("" + new EntitiesController().getNotesNum(0));
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
                    new EntitiesController().addCategory(result.get());
                }
                String fxmlFile = "/fxml/mainPage.fxml";
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                try {
                    Parent root = loader.load();
                    categoryBox.getScene().setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
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

                    VBox category = new VBox();

                    category.setMinWidth(160);
                    category.setMinHeight(170);
                    category.setPrefWidth(160);
                    category.setPrefHeight(170);
                    category.setPadding(new Insets(0, 0, 0, 20));
                    category.setStyle("-fx-background-color:#ccc;");
                    category.setCursor(Cursor.HAND);

                    Label name = new Label(categories.get(i).getCategoryName());
                    name.setMaxWidth(160);
                    name.setAlignment(Pos.CENTER);
                    name.setFont(Font.font(17));
                    Label num = new Label(new EntitiesController().getNotesNum(categories.get(i).getId()) + " ");
                    num.setPrefWidth(160);
                    num.setAlignment(Pos.CENTER);
                    num.setFont(Font.font(14));



                    category.getChildren().add(name);
                    category.getChildren().add(num);
                    category.setMargin(name,new Insets(30,15,0,0));
                    category.setMargin(num,new Insets(10,15,0,0));

                    Button deleteButton = new Button("X");
                    deleteButton.setMaxSize(10,10);
                    deleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {


                        }
                    });
                    category.getChildren().add(deleteButton);

                    category.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

                        @Override
                        public void handle(MouseEvent event) {

                        }
                    });

                    row.getChildren().add(category);
                    row.setMargin(category, new Insets(10, 0, 5, 10));
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
