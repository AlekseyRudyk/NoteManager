package com.opu.database.entities;


/**
 * Created by antipavitaly on 4/19/17.
 */
public class Category {

    private int id;
    private String categoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String category_name) {
        this.categoryName = category_name;
    }

    public Category() {


    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(int id){
        this.id = id;
    }

    public Category(int id, String category_name) {

        this.id = id;
        this.categoryName = category_name;
    }
}
