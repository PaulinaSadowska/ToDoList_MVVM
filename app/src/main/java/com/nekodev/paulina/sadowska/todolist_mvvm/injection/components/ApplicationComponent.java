package com.nekodev.paulina.sadowska.todolist_mvvm.injection.components;

import android.app.Application;

import com.nekodev.paulina.sadowska.todolist_mvvm.data.DataManager;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.module.ApplicationModule;
import com.nekodev.paulina.sadowska.todolist_mvvm.controller.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

    Application application();
    DataManager dataManager();
}