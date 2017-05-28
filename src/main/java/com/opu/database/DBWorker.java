package com.opu.database;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by antipavitaly on 4/19/17.
 */
public class DBWorker {

    //Данные для подключения
    private static final String URL = "jdbc:mysql://o3iyl77734b9n3tg.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/q3z1su8xra1ueohs";
    private static final String USERNAME = "jqhc2jlm5i66af2a";
    private static final String PASS = "nn9tk568wkhbkktg";

    private Connection connection;

    public DBWorker() {
        try{
            connection = DriverManager.getConnection(URL,USERNAME,PASS);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database connection failed.");
            alert.setContentText("Cannot connect to database. Check your URL");
            alert.showAndWait();
        }
    }

    public Connection getConnection() {

        return connection;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
