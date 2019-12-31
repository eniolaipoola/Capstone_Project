package com.eniola.capstoneproject_mynotes.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Copyright (c) 2019 Eniola Ipoola
 * All rights reserved
 * Created on 29-Dec-2019
 */
@IgnoreExtraProperties
public class Notes {

    public String title;
    public String date_created;
    public String content;

    public Notes(){

    }

    public Notes(String title, String date_created, String content){
        this.title = title;
        this.date_created = date_created;
        this.content = content;
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
}
