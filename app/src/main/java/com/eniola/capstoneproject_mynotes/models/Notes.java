package com.eniola.capstoneproject_mynotes.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Notes implements Serializable {

    private String title;
    private String date_created;
    private String content;
    private String userEmail;
    private String id;

    public Notes(){}

    public Notes(String noteId, String userEmail, String title, String content, String date_created){
        this.id = noteId;
        this.title = title;
        this.date_created = date_created;
        this.content = content;
        this.userEmail = userEmail;
    }

    public Notes(String userEmail, String title, String content, String date_created){
        this.title = title;
        this.date_created = date_created;
        this.content = content;
        this.userEmail = userEmail;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("date_created", date_created);
        result.put("content", content);
        result.put("userEmail", userEmail);

        return result;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
