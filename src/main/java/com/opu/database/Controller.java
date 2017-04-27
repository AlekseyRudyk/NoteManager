package com.opu.database;

import com.opu.database.entities.Note;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * Created by antipavitaly on 4/26/17.
 */
public class Controller {

    Connection connection = new DBWorker().getConnection();


    public void addNote(String note_name, String note_text, String category_name){

        int id = 0;
        note_name = note_name.trim();
        category_name = category_name.trim();


        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM category WHERE category_name = '"+category_name+"'");

            while (resultSet.next()){
                id = resultSet.getInt("id");
            }

            if(id == 0){
                statement.execute("INSERT INTO category(category_name) VALUE ('"+category_name+"')");
                resultSet = statement.executeQuery("SELECT * FROM category WHERE category_name = '"+category_name+"'");

                while (resultSet.next()){
                    id = resultSet.getInt("id");
                }
            }

            statement.execute("INSERT INTO note(note_name, note_text, note_comment,note_category_id) VALUES ('"+note_name+"','"+note_text+"','comment','"+id+"')");


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
        int category_id;

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM note");

            while (resultSet.next()){

                id = resultSet.getInt("id");
                head = resultSet.getString("note_name");
                text = resultSet.getString("note_text");
                comment = resultSet.getString("note_text");
                category_id = resultSet.getInt("note_category_id");


                notes.add(new Note(id,head, text,comment,category_id));
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
