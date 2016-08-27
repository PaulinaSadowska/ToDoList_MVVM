package com.nekodev.paulina.sadowska.todolist_mvvm.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Paulina Sadowska on 20.08.2016.
 */
@RealmClass
public class ToDoItem extends RealmObject implements Serializable {

    @PrimaryKey
    @Expose
    private int id;
    @Expose
    private Boolean completed;
    @Expose
    private String task;
    @Expose
    private boolean modified;

    public ToDoItem(){
        this(false, "", 0, false);
    }

    public ToDoItem(Boolean completed, String task, int id){
        this(completed, task, id, false);
    }

    public ToDoItem(Boolean completed, String task, int id, boolean modified){
        this.completed = completed;
        this.task = task;
        this.id = id;
        this.modified = modified;
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

    public boolean getModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public void setModified() {
        this.modified = true;
    }
}
