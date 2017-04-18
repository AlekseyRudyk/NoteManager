package com.opu.database.entities;

/**
 * Created by antipavitaly on 4/19/17.
 */
public class SubNote {
    private int id;
    private String subNote_name;
    private String subNote_text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubNote_name() {
        return subNote_name;
    }

    public void setSubNote_name(String subNote_name) {
        this.subNote_name = subNote_name;
    }

    public String getSubNote_text() {
        return subNote_text;
    }

    public void setSubNote_text(String subNote_text) {
        this.subNote_text = subNote_text;
    }

    public SubNote() {

    }

    public SubNote(String subNote_name, String subNote_text) {

        this.subNote_name = subNote_name;
        this.subNote_text = subNote_text;
    }

    public SubNote(int id, String subNote_name, String subNote_text) {

        this.id = id;
        this.subNote_name = subNote_name;
        this.subNote_text = subNote_text;
    }
}
