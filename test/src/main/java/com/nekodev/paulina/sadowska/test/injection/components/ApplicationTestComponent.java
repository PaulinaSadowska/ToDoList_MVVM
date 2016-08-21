package com.nekodev.paulina.sadowska.test.injection.components;


import com.nekodev.paulina.sadowska.todolist_mvvm.injection.components.ApplicationComponent;
import com.nekodev.paulina.sadowska.test.injection.modules.ApplicationTestModule;

import javax.inject.Singleton;
import dagger.Component;


/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface ApplicationTestComponent extends ApplicationComponent {

}
