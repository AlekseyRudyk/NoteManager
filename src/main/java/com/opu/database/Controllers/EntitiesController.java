package com.opu.database.controllers;

import com.opu.database.DBWorker;
import com.opu.database.entities.Category;
import com.opu.database.entities.Note;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/*
    Класс для работы с сущностями БД
 */
public class EntitiesController {

    //Объект для подключения к БД
    DBWorker database = new DBWorker();


    //Метод для добавления дела в БД
    public void addNote(String nName, String nSubnote, String nFinaleDate, String nComment, String nCategoryName){

        int id = 0;
        nName = nName.trim();
        nCategoryName = nCategoryName.trim();


        //Механизм для получения нынешней даты
        Date upDate=new Date();
        java.sql.Date nStartDate=new java.sql.Date(upDate.getTime());

        try {
            //Объект стэйтмента для SQL запросов к БД
            Statement statement = database.getConnection().createStatement();

            //Получения сущности Категория по имения категории по средством объекта ResultSet
            ResultSet resultSet = statement.executeQuery("SELECT * FROM category WHERE category_name = '"+nCategoryName+"'");

            if (resultSet.next()){
                //задаём идентификатор категории для дела
                id = resultSet.getInt("id");
            }

            //Добавляем сущность в БД
            statement.execute("INSERT INTO note(note_name, note_subNote, note_comment,note_category_id,note_startDate,note_finalDate) VALUES ('"+nName+"','"+nSubnote+"','"+nComment+"','"+id+"','"+nStartDate+"','"+nFinaleDate+"')");

            //Закрываем стэйтмент
            statement.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    //Метод для обновления дела в БД
    public void updateNote(int id, String nName, String nSubnote, String nFinaleDate,String nStartDate, String nComment, String nCategoryName, float progress){

        int catId = 0;
        nName = nName.trim();
        nCategoryName = nCategoryName.trim();


        try {
            //Объект стэйтмента для SQL запросов к БД
            Statement statement = database.getConnection().createStatement();

            //Получения сущности Категория по имения категории по средством объекта ResultSet
            ResultSet resultSet = statement.executeQuery("SELECT * FROM category WHERE category_name = '"+nCategoryName+"'");

            if (resultSet.next()){
                catId = resultSet.getInt("id");
            }

            //Обновляем сущность в БД
            statement.execute("UPDATE  note SET note_name = '"+nName+"', note_subNote = '"+nSubnote+"', note_comment = '"+nComment+"',note_category_id = '"+catId+"',note_startDate = '"+nStartDate+"',note_finalDate = '"+nFinaleDate+"', note_progress ='"+progress+"' WHERE id = '"+id+"'");

            //Закрываем стэйтмент
            statement.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    //Метод для удаления дела из БД по идентификатору
    public void deleteNote(int id){
        try {
            //Объект стэйтмента для SQL запросов к БД
            Statement statement = database.getConnection().createStatement();
            //Удаление дела
            statement.execute("DELETE FROM note WHERE id='"+id+"'");
            //Закрываем стэйтмент
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Метод для добавления категории в БД
    public void addCategory(String categoryName){

        try {
            //Объект стэйтмента для SQL запросов к БД
            Statement statement = database.getConnection().createStatement();
            //Выполнение запроса на добваления категории из БД
            statement.execute("INSERT INTO category(category_name) VALUE ('"+categoryName+"')");
            //Закрываем стэйтмент
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Метод для удаления категории по идентификатору
    public void deleteCategory(int id){
        try {
            //Объект стэйтмента для SQL запросов к БД
            Statement statement = database.getConnection().createStatement();
            //Выполнение запроса на удаление категории из БД по идентификатору
            statement.execute("DELETE FROM category WHERE id='"+id+"'");
            //Закрываем стэйтмент
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Метод для получения названий всех категорий
    public ArrayList<String> getCategoryNames(){
        ArrayList<String> categoryNames = new ArrayList<>();
        String categoryName;

        try {
            //Объект стэйтмента для SQL запросов к БД
            Statement statement = database.getConnection().createStatement();
            //Выполнение запроса с получение всех имён категорий из БД
            ResultSet resultSet = statement.executeQuery("SELECT category_name FROM category");

            //Цикл для вытаскивания занчений из таблицы сущности и присваивания
            while (resultSet.next()){
                categoryName = resultSet.getString("category_name");
                //Добавление имён категорий в ArrayList
                categoryNames.add(categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryNames;
    }

    //Метод для плучения категорий
    public ArrayList<Category> getCategories(){
        ArrayList<Category> categories = new ArrayList<>();
        int id;
        String categoryName;

        try {
            //Объект стэйтмента для SQL запросов к БД
            Statement statement = database.getConnection().createStatement();
            //Выполнение запроса с получение категорий из БД
            ResultSet resultSet = statement.executeQuery("SELECT * FROM category");

            //Цикл для вытаскивания занчений из таблицы сущности и присваивания
            while (resultSet.next()){
                id = resultSet.getInt("id");
                categoryName = resultSet.getString("category_name");
                //Добавление объектов категорий в ArrayList
                categories.add(new Category(id,categoryName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    //Метод для получения дел из БД
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
            //Объект стэйтмента для SQL запросов к БД
            Statement statement = database.getConnection().createStatement();
            //Выполнение запроса с получение дел из БД
            ResultSet resultSet = statement.executeQuery("SELECT * FROM note");

            //Цикл для вытаскивания занчений из таблицы сущности и присваивания
            while (resultSet.next()){
                id = resultSet.getInt("id");
                noteName = resultSet.getString("note_name");
                noteSubnote = resultSet.getString("note_subnote");
                noteStartDate = resultSet.getString("note_startDate");
                noteFinalDate = resultSet.getString("note_finalDate");
                noteComment = resultSet.getString("note_comment");
                noteProgress = resultSet.getFloat("note_progress");
                categoryId = resultSet.getInt("note_category_id");
                //Добавление объектов дел в ArrayList
                notes.add(new Note( id,  noteName,  noteSubnote,  noteStartDate,  noteFinalDate,  noteComment, new Category(categoryId) ,noteProgress ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }

    //Получение дел по идентификатору категории из БД
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
            //Объект стэйтмента для SQL запросов к БД
            Statement statement = database.getConnection().createStatement();
            //Выполнение запроса с получение категории из БД по идентификатору
            ResultSet categoryResultSet = statement.executeQuery("SELECT category_name FROM category WHERE id = '" + categoryId + "'");

            //Присваиваем полученное значение
            if(categoryResultSet.next()){
                noteCategory = categoryResultSet.getString("category_name");
            } else {
                noteCategory = null;
            }

            //Получаем дела по идентификатору категорий
            ResultSet resultSet = statement.executeQuery("SELECT * FROM note WHERE note_category_id = '"+categoryId+"'");

            //Цикл для вытаскивания занчений из таблицы сущности и присваивания
            while (resultSet.next()){
                id = resultSet.getInt("id");
                noteName = resultSet.getString("note_name");
                noteSubnote = resultSet.getString("note_subnote");
                noteStartDate = resultSet.getString("note_startDate");
                noteFinalDate = resultSet.getString("note_finalDate");
                noteComment = resultSet.getString("note_comment");
                noteProgress = resultSet.getFloat("note_progress");

                //Добавление объекта дела в ArrayList
                notes.add(new Note( id,  noteName,  noteSubnote,  noteStartDate,  noteFinalDate,  noteComment,new Category(noteCategory), noteProgress));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }

    //Метод для получения дела по идентификатору
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
            //Объект стэйтмента для SQL запросов к БД
            Statement statement = database.getConnection().createStatement();
            //Выполнение запроса с получение дела из БД по идентификатору
            ResultSet resultSet = statement.executeQuery("SELECT * FROM note WHERE id = '"+id+"'");


            //Условие для вытаскивания занчений из таблицы сущности и присваивания
            if(resultSet.next()) {
                noteName = resultSet.getString("note_name");
                noteSubnote = resultSet.getString("note_subnote");
                noteStartDate = resultSet.getDate("note_startDate").toString();
                noteFinalDate = resultSet.getString("note_finalDate");
                noteComment = resultSet.getString("note_comment");
                noteProgress = resultSet.getFloat("note_progress");

                categoryId = resultSet.getInt("note_category_id");

                //Получаем имя категории по её идентификатору
                ResultSet categoryResultSet = statement.executeQuery("SELECT category_name FROM category WHERE id = '" + categoryId + "'");

                //Условие для вытаскивания занчений из таблицы сущности и присваивания
                if(categoryResultSet.next()) {
                    noteCategory = categoryResultSet.getString("category_name");
                } else {
                    noteCategory = " none";
                }
                //Создаём объект  дела, передавая полученные значения
                note = new Note(id, noteName, noteSubnote, noteStartDate, noteFinalDate, noteComment, new Category(noteCategory), noteProgress);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return note;
    }

    //Метод для получения суммы дел по идентификатору категории
    public int getNotesNum(int categoryID){
        int notesNum = 0;
        if(categoryID == 0){
            try {
                //Объект стэйтмента для SQL запросов к БД
                Statement statement = database.getConnection().createStatement();
                //Выполнение запроса с получение количества дел из БД
                ResultSet resultSet = statement.executeQuery("SELECT count(*) AS count FROM note ");
                //Цикл для получения количества сущностей и присваивания
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
                //Объект стэйтмента для SQL запросов к БД
                Statement statement = database.getConnection().createStatement();
                //Выполнение запроса с получение количества дел из БД по идентификатору категории
                ResultSet resultSet = statement.executeQuery("SELECT count(*) AS count FROM note WHERE note_category_id='" + categoryID + "'");

                //Цикл для получения количества сущностей и присваивания
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

    //Метод для получения суммы категорий
    public int getCategoryNum(){
        int categoryNum = 0;

        try {
            //Объект стэйтмента для SQL запросов к БД
            Statement statement = database.getConnection().createStatement();
            //Выполнение запроса с получение количества категорий из БД
            ResultSet resultSet = statement.executeQuery("SELECT count(*) AS count FROM category ");
            //Цикл для получения количества сущностей и присваивания
            while (resultSet.next()){
                categoryNum = resultSet.getInt("count");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryNum;
    }

    //Метод для получения имени категории по её идентификатору
    public String getCategoryName(int id){
        String categoryName = "None";

        try {
            //Объект стэйтмента для SQL запросов к БД
            Statement statement = database.getConnection().createStatement();
            //Выполнение запроса с получение количества категорий из БД
            ResultSet resultSet = statement.executeQuery("SELECT category_name FROM category WHERE id = '"+ id +"'");
            //Условие для получения количества сущностей и присваивания
            if (resultSet.next()){
                categoryName = resultSet.getString("category_name");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return categoryName;
    }

    //Метод для закрытия подключения к БД
    public void closeConnection(){
        database.closeConnection();
    }

}