package com.eniola.capstoneproject_mynotes.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Copyright (c) 2019 Eniola Ipoola
 * All rights reserved
 * Created on 29-Dec-2019
 */
@IgnoreExtraProperties
public class Notes {

    private String title;
    private String date_created;
    private String content;
    private String userEmail;

    public Notes(){}

    public Notes(String userEmail, String title, String content, String date_created){
        this.title = title;
        this.date_created = date_created;
        this.content = content;
        this.userEmail = userEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
