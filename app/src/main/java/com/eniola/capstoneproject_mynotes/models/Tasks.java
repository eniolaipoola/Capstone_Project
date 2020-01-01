package com.eniola.capstoneproject_mynotes.models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Tasks {

    public int id;
    public List<SubTasks> subtasks;

    public Tasks(){

    }

    public Tasks(int id, List<SubTasks> subtasks){
        this.id = id;
        this.subtasks = subtasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SubTasks> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<SubTasks> subtasks) {
        this.subtasks = subtasks;
    }

    public class SubTasks {
        public String status;
        public String description;

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
}
