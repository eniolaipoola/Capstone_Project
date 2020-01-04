package com.eniola.capstoneproject_mynotes.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Tasks {

    private String id;
    private String status;
    private String description;
    private String username;

    public Tasks(String username, String description, String status){
        this.status = status;
        this.description = description;
        this.username = username;
    }

    public Tasks(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
