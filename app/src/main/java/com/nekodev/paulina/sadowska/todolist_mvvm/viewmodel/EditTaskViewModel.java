package com.nekodev.paulina.sadowska.todolist_mvvm.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.nekodev.paulina.sadowska.todolist_mvvm.controller.activity.EditTaskActivity;
import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;

/**
 * Created by Paulina Sadowska on 25.08.2016.
 */

public class EditTaskViewModel  extends BaseObservable {

        private Context mContext;
        private ToDoItem mTask;

        public EditTaskViewModel(Context context, ToDoItem task){
            this.mContext = context;
            this.mTask = task;
        }

        public boolean isCompleted(){
            return mTask.isCompleted();
        }

        public void setIsCompleted(boolean completed){
            mTask.setCompleted(completed);
        }

        public String getTask(){
            return mTask.getTask();
        }

        public void setTask(String taskText){
            mTask.setTask(taskText);
        }

        public String getId(){  return mTask.getId() + ""; }

        public View.OnClickListener onClickSave(){
            return v -> sendActivityResult();
        }

    private void sendActivityResult() {
        ((Activity)mContext).setResult(
                EditTaskActivity.ACTIVITY_RESULT_CODE,
                EditTaskActivity.getResultIntent(mContext, mTask));
        ((Activity)mContext).finish();
    }

}