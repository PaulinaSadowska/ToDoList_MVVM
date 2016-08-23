package com.nekodev.paulina.sadowska.todolist_mvvm.model;

import java.io.Serializable;

/**
 * Created by Paulina Sadowska on 20.08.2016.
 */

public class ToDoItem implements Serializable {

    private int id;
    private Boolean completed;
    private String task;

    public ToDoItem(Boolean completed, String task, int id){
        this.completed = completed;
        this.task = task;
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
