package com.nekodev.paulina.sadowska.test.injection.components;

import com.nekodev.paulina.sadowska.todolist_mvvm.injection.components.DataManagerComponent;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.scope.PerDataManager;
import com.nekodev.paulina.sadowska.test.injection.modules.DataManagerTestModule;

import dagger.Component;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

@PerDataManager
@Component(dependencies = ApplicationTestComponent.class, modules = DataManagerTestModule.class)
public interface DataManagerTestComponent extends DataManagerComponent {
}
