package com.nekodev.paulina.sadowska.todolist_mvvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;

/**
 * Created by Paulina Sadowska on 20.08.2016.
 */

public class ListItemViewModel extends BaseObservable {

    private Context mContext;
    private ToDoItem mTask;

    public ListItemViewModel(Context context, ToDoItem task){
        this.mContext = context;
        this.mTask = task;
    }

    public Boolean isCompleted(){
        return mTask.isCompleted();
    }

    public void setIsCompleted(Boolean completed){
        mTask.setCompleted(completed);
    }

    public String getTask(){
        return mTask.getTask();
    }



}
