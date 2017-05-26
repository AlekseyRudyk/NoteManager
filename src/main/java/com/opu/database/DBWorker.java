package com.opu.database;

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
            e.printStackTrace();
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
