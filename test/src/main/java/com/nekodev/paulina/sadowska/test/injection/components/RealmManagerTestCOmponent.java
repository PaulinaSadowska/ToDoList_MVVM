package com.nekodev.paulina.sadowska.test.injection.components;

import com.nekodev.paulina.sadowska.test.injection.modules.RealmManagerTestModule;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.components.RealmManagerComponent;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.scope.PerDataManager;

import dagger.Component;

/**
 * Created by Paulina Sadowska on 27.08.2016.
 */

@PerDataManager
@Component(dependencies = ApplicationTestComponent.class, modules = RealmManagerTestModule.class)
public interface RealmManagerTestComponent extends RealmManagerComponent {
}