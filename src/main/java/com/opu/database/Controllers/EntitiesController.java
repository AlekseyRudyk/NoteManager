package com.opu.database.Controllers;

import com.opu.database.DBWorker;
import com.opu.database.entities.Category;
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

    public ArrayList<Category> getCategories(){
        ArrayList<Category> categories = new ArrayList<>();
        int id;
        String categoryName;

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM category");

            while (resultSet.next()){
                id = resultSet.getInt("id");
                categoryName = resultSet.getString("category_name");
                categories.add(new Category(id,categoryName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public ArrayList<Note> getNotes(){
        ArrayList<Note> notes = new ArrayList<>();
      int id;
      String noteName;
      String noteSubnote;
      String noteStartDate;
      String noteFinalDate;
      String noteComment;
      float noteProgress;
      int categoryId;
      String noteCategory;

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM note");

            while (resultSet.next()){
                id = resultSet.getInt("id");
                noteName = resultSet.getString("note_name");
                noteSubnote = resultSet.getString("note_subnote");
                noteStartDate = resultSet.getString("note_startDate");
                noteFinalDate = resultSet.getString("note_finalDate");
                noteComment = resultSet.getString("note_comment");
                noteProgress = resultSet.getFloat("note_progress");

                categoryId = resultSet.getInt("note_category_id");

                ResultSet categoryResultSet = statement.executeQuery("SELECT category_name FROM category WHERE id = '" + categoryId + "'");
                noteCategory = categoryResultSet.getString("category_name");


                notes.add(new Note( id,  noteName,  noteSubnote,  noteStartDate,  noteFinalDate,  noteComment, new Category(noteCategory) ,noteProgress ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }

    public ArrayList<Note> getNotesByCategoryId(int catId){
        ArrayList<Note> notes = new ArrayList<>();
        int id;
        String noteName;
        String noteSubnote;
        String noteStartDate;
        String noteFinalDate;
        String noteComment;
        float noteProgress;
        int categoryId = catId;
        String noteCategory;

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM note WHERE note_category_id = '"+categoryId+"'");

            while (resultSet.next()){
                id = resultSet.getInt("id");
                noteName = resultSet.getString("note_name");
                noteSubnote = resultSet.getString("note_subnote");
                noteStartDate = resultSet.getString("note_startDate");
                noteFinalDate = resultSet.getString("note_finalDate");
                noteComment = resultSet.getString("note_comment");
                noteProgress = resultSet.getFloat("note_progress");

                ResultSet categoryResultSet = statement.executeQuery("SELECT category_name FROM category WHERE id = '" + categoryId + "'");
                noteCategory = categoryResultSet.getString("category_name");


                notes.add(new Note( id,  noteName,  noteSubnote,  noteStartDate,  noteFinalDate,  noteComment,new Category(noteCategory), noteProgress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }

    public Note getNoteById(int noteId){

        int id = noteId;
        String noteName;
        String noteSubnote;
        String noteStartDate;
        String noteFinalDate;
        String noteComment;
        float noteProgress;
        int categoryId;
        String noteCategory;

        Note note = new Note();

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM note WHERE id = '"+id+"'");


                noteName = resultSet.getString("note_name");
                noteSubnote = resultSet.getString("note_subnote");
                noteStartDate = resultSet.getString("note_startDate");
                noteFinalDate = resultSet.getString("note_finalDate");
                noteComment = resultSet.getString("note_comment");
                noteProgress = resultSet.getFloat("note_progress");

                categoryId = resultSet.getInt("note_category_id");

                ResultSet categoryResultSet = statement.executeQuery("SELECT category_name FROM category WHERE id = '" + categoryId + "'");
                noteCategory = categoryResultSet.getString("category_name");

                note = new Note( id,  noteName,  noteSubnote,  noteStartDate,  noteFinalDate,  noteComment,new Category(noteCategory) , noteProgress);

                return note;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return note;

    }

    public int getNotesNum(int categoryID){
        int notesNum = 0;
        if(categoryID == 0){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT count(*) AS count FROM note ");

                while (resultSet.next()){
                    notesNum = resultSet.getInt("count");
                }
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return notesNum;
        } else {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT count(*) AS count FROM note WHERE note_category_id='" + categoryID + "'");

                while (resultSet.next()){
                    notesNum = resultSet.getInt("count");
                }

                statement.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return notesNum;
        }
    }

    public int getCategoryNum(){
        int categoryNum = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT count(*) AS count FROM category ");

            while (resultSet.next()){
                categoryNum = resultSet.getInt("count");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryNum;
    }
}