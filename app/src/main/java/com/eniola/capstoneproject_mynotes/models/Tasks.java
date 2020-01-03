package com.eniola.capstoneproject_mynotes.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Tasks {

    private int id;
    private String status;
    private String description;


    public Tasks(int id, String description, String status){
        this.id = id;
        this.status = status;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
