package com.opu.database.entities;

/**
 * Created by antipavitaly on 4/19/17.
 */
public class Note {
    private int id;
    private String note_name;
    private  String note_text;
    private String note_data;
    private String note_comment;

    public Note(String note_name, String note_text) {
        this.note_name = note_name;
        this.note_text = note_text;
    }

    public Note() {
    }

    public Note(int id, String note_name, String note_text) {

        this.id = id;
        this.note_name = note_name;
        this.note_text = note_text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote_name() {
        return note_name;
    }

    public void setNote_name(String note_name) {
        this.note_name = note_name;
    }

    public String getNote_text() {
        return note_text;
    }

    public void setNote_text(String note_text) {
        this.note_text = note_text;
    }

    public String getNote_data() {
        return note_data;
    }

    public void setNote_data(String note_data) {
        this.note_data = note_data;
    }

    public String getNote_comment() {
        return note_comment;
    }

    public void setNote_comment(String note_comment) {
        this.note_comment = note_comment;
    }
}
