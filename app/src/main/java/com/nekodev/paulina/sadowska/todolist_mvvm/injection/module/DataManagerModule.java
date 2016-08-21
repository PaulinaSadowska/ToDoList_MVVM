package com.nekodev.paulina.sadowska.todolist_mvvm.injection.module;

import com.nekodev.paulina.sadowska.todolist_mvvm.data.remote.RetrofitHelper;
import com.nekodev.paulina.sadowska.todolist_mvvm.data.remote.ToDoService;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.scope.PerDataManager;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

@Module
public class DataManagerModule {

    public DataManagerModule() {

    }

    @Provides
    @PerDataManager
    ToDoService provideToDoService() {
        return new RetrofitHelper().newHackerNewsService();
    }

    @Provides
    @PerDataManager
    Scheduler provideSubscribeScheduler() {
        return Schedulers.io();
    }
}
