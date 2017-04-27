package com.opu.database.entities;

/**
 * Created by antipavitaly on 4/19/17.
 */
public class Note {
    private int id;
    private String noteName;
    private String noteSubnote;
    private String noteStartDate;
    private String noteFinalDate;
    private String noteComment;
    private int noteCategoryId;

    public Note(int id, String noteName, String noteSubnote, String noteComment, int noteCategoryId) {
        this.id = id;
        this.noteName = noteName;
        this.noteSubnote = noteSubnote;
        this.noteComment = noteComment;
        this.noteCategoryId = noteCategoryId;
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
                ", note_name='" + noteName + '\'' +
                ", note_text='" + noteSubnote + '\'' +
                ", note_comment='" + noteComment + '\'' +
                ", note_category_id=" + noteCategoryId +
                '}';
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

    public Note(String noteName, String noteSubnote, String noteStartDate, String noteComment, int noteCategoryId) {
        this.noteName = noteName;
        this.noteSubnote = noteSubnote;
        this.noteStartDate = noteStartDate;
        this.noteComment = noteComment;
        this.noteCategoryId = noteCategoryId;
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

    public int getNoteCategoryId() {
        return noteCategoryId;
    }

    public void setNoteCategoryId(int noteCategoryId) {
        this.noteCategoryId = noteCategoryId;
    }
}
