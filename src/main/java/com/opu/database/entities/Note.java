package com.opu.database.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Note {
    private int id;
    private String noteName;
    private String noteSubnote;
    private String noteStartDate;
    private String noteFinalDate;
    private String noteComment;
    private Category category;
    private float progress;

    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteSubnote() {
        return noteSubnote;
    }

    public void setNoteSubnote(String noteSubnote) {
        this.noteSubnote = noteSubnote;
    }

    public String getNoteStartDate() {

        return noteStartDate;
    }

    public void setNoteStartDate(String noteStartDate) {
        this.noteStartDate = noteStartDate;
    }

    public String getNoteFinalDate() {
        return noteFinalDate;
    }

    public void setNoteFinalDate(String noteFinalDate) {
        this.noteFinalDate = noteFinalDate;
    }

    public String getNoteComment() {
        return noteComment;
    }

    public void setNoteComment(String noteComment) {
        this.noteComment = noteComment;
    }

    public Note(int id, String noteName, String noteSubnote, String noteStartDate, String noteFinalDate, String noteComment, Category category) {
        this.id = id;
        this.noteName = noteName;
        this.noteSubnote = noteSubnote;
        this.noteStartDate = noteStartDate;
        this.noteFinalDate = noteFinalDate;
        this.noteComment = noteComment;
        this.category = category;
    }

    public Note(int id, String noteName, String noteSubnote, String noteStartDate, String noteFinalDate, String noteComment, Category category, float progress) {

        this.id = id;
        this.noteName = noteName;
        this.noteSubnote = noteSubnote;
        this.noteStartDate = noteStartDate;
        this.noteFinalDate = noteFinalDate;
        this.noteComment = noteComment;
        this.category = category;
        this.progress = progress;
    }

    public Note(int id, String noteName, String noteSubnote, String noteFinalDate, String noteComment, Category category) {
        this.id = id;
        this.noteName = noteName;
        this.noteSubnote = noteSubnote;
        this.noteFinalDate = noteFinalDate;
        this.noteComment = noteComment;
        this.category = category;
    }


    public Category getCategory() {

        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public BigDecimal cutProgress(float progress){

        BigDecimal bd = new BigDecimal(progress);
        bd =   bd.setScale(2, RoundingMode.HALF_UP);

        return bd;
    }
}
