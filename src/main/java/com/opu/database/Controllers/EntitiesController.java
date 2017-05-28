package com.opu.database.Controllers;

import com.opu.database.DBWorker;
import com.opu.database.entities.Category;
import com.opu.database.entities.Note;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by antipavitaly on 4/26/17.
 */
public class EntitiesController {

    DBWorker database = new DBWorker();


    public void addNote(String nName, String nSubnote, String nFinaleDate, String nComment, String nCategoryName){

        int id = 0;
        nName = nName.trim();
        nCategoryName = nCategoryName.trim();


        Date upDate=new Date();
        java.sql.Date nStartDate=new java.sql.Date(upDate.getTime());

        try {
            Statement statement = database.getConnection().createStatement();

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

            statement.execute("INSERT INTO note(note_name, note_subNote, note_comment,note_category_id,note_startDate,note_finalDate) VALUES ('"+nName+"','"+nSubnote+"','"+nComment+"','"+id+"','"+nStartDate+"','"+nFinaleDate+"')");

            statement.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void updateNote(int id, String nName, String nSubnote, String nFinaleDate, String nComment, String nCategoryName, float progress){

        int catId = 0;
        nName = nName.trim();
        nCategoryName = nCategoryName.trim();


        Date upDate=new Date();
        java.sql.Date nStartDate=new java.sql.Date(upDate.getTime());

        try {
            Statement statement = database.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM category WHERE category_name = '"+nCategoryName+"'");

            if (resultSet.next()){
                catId = resultSet.getInt("id");
            }



            statement.execute("UPDATE  note SET note_name = '"+nName+"', note_subNote = '"+nSubnote+"', note_comment = '"+nComment+"',note_category_id = '"+catId+"',note_startDate = '"+nStartDate+"',note_finalDate = '"+nFinaleDate+"', note_progress ='"+progress+"' WHERE id = '"+id+"'");

            statement.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public void deleteNote(int id){
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute("DELETE FROM note WHERE id='"+id+"'");

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCategory(String categoryName){

        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute("INSERT INTO category(category_name) VALUE ('"+categoryName+"')");

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteCategory(int id){
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute("DELETE FROM category WHERE id='"+id+"'");

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getCategoryNames(){
        ArrayList<String> categoryNames = new ArrayList<>();
        String categoryName;

        try {
            Statement statement = database.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT category_name FROM category");

            while (resultSet.next()){
                categoryName = resultSet.getString("category_name");
                categoryNames.add(categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryNames;
    }

    public ArrayList<Category> getCategories(){
        ArrayList<Category> categories = new ArrayList<>();
        int id;
        String categoryName;

        try {
            Statement statement = database.getConnection().createStatement();

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

        try {
            Statement statement = database.getConnection().createStatement();

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

                notes.add(new Note( id,  noteName,  noteSubnote,  noteStartDate,  noteFinalDate,  noteComment, new Category(categoryId) ,noteProgress ));
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
            Statement statement = database.getConnection().createStatement();

            ResultSet categoryResultSet = statement.executeQuery("SELECT category_name FROM category WHERE id = '" + categoryId + "'");

            if(categoryResultSet.next()){
                noteCategory = categoryResultSet.getString("category_name");
            } else {
                noteCategory = null;
            }

            ResultSet resultSet = statement.executeQuery("SELECT * FROM note WHERE note_category_id = '"+categoryId+"'");

            while (resultSet.next()){
                id = resultSet.getInt("id");
                noteName = resultSet.getString("note_name");
                noteSubnote = resultSet.getString("note_subnote");
                noteStartDate = resultSet.getString("note_startDate");
                noteFinalDate = resultSet.getString("note_finalDate");
                noteComment = resultSet.getString("note_comment");
                noteProgress = resultSet.getFloat("note_progress");

                notes.add(new Note( id,  noteName,  noteSubnote,  noteStartDate,  noteFinalDate,  noteComment,new Category(noteCategory), noteProgress));
            }
            statement.close();
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
            Statement statement = database.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM note WHERE id = '"+id+"'");



            if(resultSet.next()) {
                noteName = resultSet.getString("note_name");
                noteSubnote = resultSet.getString("note_subnote");
                noteStartDate = resultSet.getDate("note_startDate").toString();
                noteFinalDate = resultSet.getString("note_finalDate");
                noteComment = resultSet.getString("note_comment");
                noteProgress = resultSet.getFloat("note_progress");

                categoryId = resultSet.getInt("note_category_id");

                ResultSet categoryResultSet = statement.executeQuery("SELECT category_name FROM category WHERE id = '" + categoryId + "'");

                if(categoryResultSet.next()) {
                    noteCategory = categoryResultSet.getString("category_name");
                } else {
                    noteCategory = " none";
                }
                note = new Note(id, noteName, noteSubnote, noteStartDate, noteFinalDate, noteComment, new Category(noteCategory), noteProgress);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return note;
    }

    public int getNotesNum(int categoryID){
        int notesNum = 0;
        if(categoryID == 0){
            try {
                Statement statement = database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT count(*) AS count FROM note ");

                while (resultSet.next()){
                    notesNum = resultSet.getInt("count");
                }
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return notesNum;
        } else {
            try {
                Statement statement = database.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT count(*) AS count FROM note WHERE note_category_id='" + categoryID + "'");

                while (resultSet.next()){
                    notesNum = resultSet.getInt("count");
                }

                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return notesNum;
        }
    }

    public int getCategoryNum(){
        int categoryNum = 0;

        try {
            Statement statement = database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT count(*) AS count FROM category ");

            while (resultSet.next()){
                categoryNum = resultSet.getInt("count");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryNum;
    }

    public String getCategoryName(int id){
        String categoryName = "None";

        try {
            Statement statement = database.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT category_name FROM category WHERE id = '"+ id +"'");

            if (resultSet.next()){
                categoryName = resultSet.getString("category_name");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return categoryName;
    }

    public void closeConnection(){
        database.closeConnection();
    }

}