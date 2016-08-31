package com.nekodev.paulina.sadowska.todolist_mvvm.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingConversion;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.nekodev.paulina.sadowska.todolist_mvvm.R;
import com.nekodev.paulina.sadowska.todolist_mvvm.controller.activity.EditTaskActivity;
import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Paulina Sadowska on 20.08.2016.
 */

public class TaskViewModel extends BaseObservable {

    private Context mContext;
    private ToDoItem mTask;
    final PublishSubject<Boolean> dataChanged;

    public TaskViewModel(Context context, ToDoItem task){
        this.mContext = context;
        this.mTask = task;
        dataChanged =  PublishSubject.create();
    }

    public boolean isCompleted(){
        return mTask.isCompleted();
    }

    public void setIsCompleted(boolean completed){
        mTask.setCompleted(completed);
        mTask.setModified();
        dataChanged.onCompleted();
    }

    public Observable<Boolean> getDataChangedObservable(){
        return dataChanged;
    }

    public String getTask(){
        return mTask.getTask();
    }

    public void setTask(String taskText){
        mTask.setTask(taskText);
    }

    public int getTaskTextColor(){
        if(mTask.isCompleted()){
            return ContextCompat.getColor(mContext, R.color.secondaryTextColor);
        }
        return ContextCompat.getColor(mContext, R.color.primaryTextColor);
    }

    @BindingConversion
    public static ColorDrawable convertColorToDrawable(String color) {
        return new ColorDrawable(Color.parseColor(color));
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
