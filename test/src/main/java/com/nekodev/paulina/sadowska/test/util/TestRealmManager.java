package com.nekodev.paulina.sadowska.test.util;

import android.content.Context;

import com.nekodev.paulina.sadowska.test.injection.components.ApplicationTestComponent;
import com.nekodev.paulina.sadowska.test.injection.components.DaggerRealmManagerTestComponent;
import com.nekodev.paulina.sadowska.test.injection.modules.RealmManagerTestModule;
import com.nekodev.paulina.sadowska.todolist_mvvm.ToDoListApplication;
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
        ApplicationTestComponent testComponent = (ApplicationTestComponent)
                ToDoListApplication.get(context).getComponent();
        DaggerRealmManagerTestComponent.builder()
                .applicationTestComponent(testComponent)
                .realmManagerTestModule(new RealmManagerTestModule())
                .build()
                .inject(this);
    }

    public ToDoItem getSavedTask(int id) {
        return null;
    }


    public void saveTask(ToDoItem task) {   }

}
