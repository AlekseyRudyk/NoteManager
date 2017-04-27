package com.opu.database.entities;

/**
 * Created by antipavitaly on 4/19/17.
 */
public class Note {
    private int id;
    private String note_name;
    private String note_text;
    private String note_data;
    private String note_comment;
    private int note_category_id;

    public Note(int id, String note_name, String note_text, String note_comment, int note_category_id) {
        this.id = id;
        this.note_name = note_name;
        this.note_text = note_text;
        this.note_comment = note_comment;
        this.note_category_id = note_category_id;
    }

    public Note() {
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", note_name='" + note_name + '\'' +
                ", note_text='" + note_text + '\'' +
                ", note_comment='" + note_comment + '\'' +
                ", note_category_id=" + note_category_id +
                '}';
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

    public int getNote_category_id() {

        return note_category_id;
    }

    public void setNote_category_id(int note_category_id) {
        this.note_category_id = note_category_id;
    }
}
