package com.nekodev.paulina.sadowska.todolist_mvvm.injection.components;

import com.nekodev.paulina.sadowska.todolist_mvvm.data.RealmManager;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.module.RealmManagerModule;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.scope.PerDataManager;

import dagger.Component;

/**
 * Created by Paulina Sadowska on 27.08.2016.
 */

@PerDataManager
@Component(dependencies = ApplicationComponent.class, modules = RealmManagerModule.class)
public interface RealmManagerComponent {

    void inject(RealmManager realmManager);
}
