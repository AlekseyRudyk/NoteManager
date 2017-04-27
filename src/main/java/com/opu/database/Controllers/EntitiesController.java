package com.opu.database.Controllers;

import com.opu.database.DBWorker;
import com.opu.database.entities.Note;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by antipavitaly on 4/26/17.
 */
public class EntitiesController {

    Connection connection = new DBWorker().getConnection();


    public void addNote(String nName, String nSubnote, String nCategoryName, String nComment){

        int id = 0;
        nName = nName.trim();
        nCategoryName = nCategoryName.trim();


        Date upDate=new Date();
        java.sql.Date nStartDate=new java.sql.Date(upDate.getTime());

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM category WHERE category_name = '"+nCategoryName+"'");

            while (resultSet.next()){
                id = resultSet.getInt("id");
            }

            if(id == 0){
                statement.execute("INSERT INTO category(category_name) VALUE ('"+nCategoryName+"')");
                resultSet = statement.executeQuery("SELECT * FROM category WHERE category_name = '"+nCategoryName+"'");

                while (resultSet.next()){
                    id = resultSet.getInt("id");
                }
            }

            statement.execute("INSERT INTO note(note_name, note_subNote, note_comment,note_category_id,note_startDate) VALUES ('"+nName+"','"+nSubnote+"','"+nComment+"','"+id+"','"+nStartDate+"')");

            statement.close();
            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void soutNotes(){

        ArrayList<Note> notes = new ArrayList<>();


        int id;
        String head;
        String text;
        String comment;
        int nCategoryId;

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM note");

            while (resultSet.next()){

                id = resultSet.getInt("id");
                head = resultSet.getString("note_name");
                text = resultSet.getString("note_subnote");
                comment = resultSet.getString("note_text");
                nCategoryId = resultSet.getInt("note_category_id");


                notes.add(new Note(id,head, text,comment,nCategoryId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Note n: notes) {
            System.out.println(n.toString());
        }

    }

    public void deleteNote(int id){
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM note WHERE id='"+id+"'");

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCategory(String categoryName){

        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO category(category_name) VALUE ('"+categoryName+"')");

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteCategory(int id){
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM category WHERE id='"+id+"'");

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
