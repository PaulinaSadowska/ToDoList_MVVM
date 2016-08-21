package com.nekodev.paulina.sadowska.todolist_mvvm;

import android.app.Application;
import android.content.Context;

import com.nekodev.paulina.sadowska.todolist_mvvm.injection.components.ApplicationComponent;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.components.DaggerApplicationComponent;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.module.ApplicationModule;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

public class ToDoListApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ToDoListApplication get(Context context) {
        return (ToDoListApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
