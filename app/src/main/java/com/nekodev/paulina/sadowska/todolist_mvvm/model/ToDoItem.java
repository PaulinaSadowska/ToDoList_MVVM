package com.nekodev.paulina.sadowska.todolist_mvvm.model;

/**
 * Created by Paulina Sadowska on 20.08.2016.
 */

public class ToDoItem {

    private int id;
    private boolean completed;
    private String task;

    public ToDoItem(Boolean completed, String task){
        this.completed = completed;
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
