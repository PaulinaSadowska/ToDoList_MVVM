package com.nekodev.paulina.sadowska.todolist_mvvm.data;

import android.content.Context;

import com.nekodev.paulina.sadowska.todolist_mvvm.ToDoListApplication;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.components.DaggerRealmManagerComponent;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.module.RealmManagerModule;
import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Paulina Sadowska on 27.08.2016.
 */

public class RealmManager {

    @Inject
    protected Realm mRealm;

    public RealmManager(Context context){
        injectDependencies(context);
    }

    public RealmManager(Realm realm){
        this.mRealm = realm;
    }

    protected void injectDependencies(Context context) {
        DaggerRealmManagerComponent.builder()
                .applicationComponent(ToDoListApplication.get(context).getComponent())
                .realmManagerModule(new RealmManagerModule(context))
                .build()
                .inject(this);
    }

    public ToDoItem getSavedTask(int id) {
        ToDoItem task = mRealm.where(ToDoItem.class).equalTo("id", id).findFirst();
        if(task!=null)
            return mRealm.copyFromRealm(task);
        return null;
    }


    public void saveTask(ToDoItem task) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(task);
        mRealm.commitTransaction();
    }

    public void removeAllSavedTasks() {
        mRealm.beginTransaction();
        mRealm.deleteAll();
        mRealm.commitTransaction();
    }
}
