package com.nekodev.paulina.sadowska.todolist_mvvm.injection.components;

import com.nekodev.paulina.sadowska.todolist_mvvm.data.DataManager;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.module.DataManagerModule;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.scope.PerDataManager;

import dagger.Component;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

@PerDataManager
@Component(dependencies = ApplicationComponent.class, modules = DataManagerModule.class)
public interface DataManagerComponent {

    void inject(DataManager dataManager);
}