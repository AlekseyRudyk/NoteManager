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
    private static final String URL = "jdbc:mysql://localhost:3306/coursework";
    private static final String USERNAME = "root";
    private static final String PASS = "root";

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
