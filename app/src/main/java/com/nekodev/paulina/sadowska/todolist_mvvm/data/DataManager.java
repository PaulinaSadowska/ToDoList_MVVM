package com.nekodev.paulina.sadowska.todolist_mvvm.data;

import android.content.Context;

import com.nekodev.paulina.sadowska.todolist_mvvm.ToDoListApplication;
import com.nekodev.paulina.sadowska.todolist_mvvm.data.remote.ToDoService;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.components.DaggerDataManagerComponent;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.module.DataManagerModule;
import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;

import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

public class DataManager {

    @Inject
    protected ToDoService mToDoService;
    @Inject
    protected Scheduler mSubscribeScheduler;


    public DataManager(Context context) {
        injectDependencies(context);
    }

    /* This constructor is provided so we can set up a DataManager with mocks from unit test.
     * At the moment this is not possible to do with Dagger because the Gradle APT plugin doesn't
     * work for the unit test variant, plus Dagger 2 doesn't provide a nice way of overriding
     * modules */
    public DataManager(ToDoService gitHubService,
                       Scheduler subscribeScheduler) {
        mToDoService = gitHubService;
        mSubscribeScheduler = subscribeScheduler;
    }

    protected void injectDependencies(Context context) {
        DaggerDataManagerComponent.builder()
                .applicationComponent(ToDoListApplication.get(context).getComponent())
                .dataManagerModule(new DataManagerModule(context))
                .build()
                .inject(this);
    }

    public Observable<ToDoItem> getTasks() {
        return mToDoService.getTasks()
                .flatMap(Observable::from);
    }

    public Observable<ResponseBody> saveModifiedTasks(List<ToDoItem> tasks){
        return Observable
                .from(tasks)
                .flatMap(task -> mToDoService.putSavedTasks(task.getTask(), task.getId(), task.isCompleted()));
    }

    public Scheduler getScheduler() {
        return mSubscribeScheduler;
    }
}