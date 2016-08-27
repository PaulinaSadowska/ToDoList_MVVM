package com.nekodev.paulina.sadowska.todolist_mvvm.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.nekodev.paulina.sadowska.todolist_mvvm.controller.activity.EditTaskActivity;
import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;

/**
 * Created by Paulina Sadowska on 20.08.2016.
 */

public class TaskViewModel extends BaseObservable {

    private Context mContext;
    private ToDoItem mTask;

    public TaskViewModel(Context context, ToDoItem task){
        this.mContext = context;
        this.mTask = task;
    }

    public boolean isCompleted(){
        return mTask.isCompleted();
    }

    public void setIsCompleted(boolean completed){
        mTask.setCompleted(completed);
        mTask.setModified();
    }

    public String getTask(){
        return mTask.getTask();
    }

    public void setTask(String taskText){
        mTask.setTask(taskText);
    }

    public String getId(){  return mTask.getId() + ""; }

    public View.OnClickListener onClickTask() {
        return v -> launchDetailsActivity();
    }

    private void launchDetailsActivity() {
        ((Activity) mContext).startActivityForResult(
                EditTaskActivity.getStartIntent(mContext, mTask),
                EditTaskActivity.ACTIVITY_RESULT_CODE);
    }

    public int wasModified() {
        if(mTask.getModified()) {
            return View.VISIBLE;
        }
        return View.INVISIBLE;
    }
}
