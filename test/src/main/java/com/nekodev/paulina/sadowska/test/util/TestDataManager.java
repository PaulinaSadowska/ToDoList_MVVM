package com.nekodev.paulina.sadowska.test.util;

import android.content.Context;

import com.nekodev.paulina.sadowska.test.injection.components.ApplicationTestComponent;
import com.nekodev.paulina.sadowska.test.injection.components.DaggerDataManagerTestComponent;
import com.nekodev.paulina.sadowska.test.injection.modules.DataManagerTestModule;
import com.nekodev.paulina.sadowska.todolist_mvvm.ToDoListApplication;
import com.nekodev.paulina.sadowska.todolist_mvvm.data.DataManager;
import com.nekodev.paulina.sadowska.todolist_mvvm.data.remote.ToDoService;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

public class TestDataManager extends DataManager {

    public TestDataManager(Context context) {
        super(context);
    }

    @Override
    protected void injectDependencies(Context context) {
        ApplicationTestComponent testComponent = (ApplicationTestComponent)
                ToDoListApplication.get(context).getComponent();
        DaggerDataManagerTestComponent.builder()
                .applicationTestComponent(testComponent)
                .dataManagerTestModule(new DataManagerTestModule())
                .build()
                .inject(this);
    }

    public ToDoService getToDoService() {
        return mToDoService;
    }

}