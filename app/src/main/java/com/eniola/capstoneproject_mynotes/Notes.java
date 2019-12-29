package com.eniola.capstoneproject_mynotes;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Copyright (c) 2019 Eniola Ipoola
 * All rights reserved
 * Created on 29-Dec-2019
 */
@IgnoreExtraProperties
public class Notes {

    public String title;
    public Date date_created;
    public String content;

    public Notes(){

    }

    public Notes(String title, Date date_created, String content){
        this.title = title;
        this.date_created = date_created;
        this.content = content;
    }
}
