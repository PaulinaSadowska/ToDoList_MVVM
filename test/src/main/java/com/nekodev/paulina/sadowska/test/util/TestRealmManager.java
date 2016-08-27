package com.nekodev.paulina.sadowska.test.util;

import android.content.Context;

import com.nekodev.paulina.sadowska.todolist_mvvm.data.RealmManager;
import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;

/**
 * Created by Paulina Sadowska on 27.08.2016.
 */

public class TestRealmManager extends RealmManager {

    public TestRealmManager(Context context) {
        super(context);
    }

    @Override
    protected void injectDependencies(Context context) {
        //do nothing, dont want to mock Realm, just do not use it
    }

    public ToDoItem getSavedTask(int id) {
        return null;
    }


    public void saveTask(ToDoItem task) {   }

}
