package com.nekodev.paulina.sadowska.todolist_mvvm.injection.scope;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the DataManager to be memorised in the
 * correct component.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerDataManager {
}
