package com.opu.database;

/**
 * Created by antipavitaly on 4/19/17.
 */
public class Main {



    public static void main(String[] args) {

        DBWorker db = new DBWorker();

        Controller controller = new Controller();

        controller.addNote("Note1","texttexttext","category");
//        controller.deleteNote(2);
//        controller.soutNotes();



    }
}
