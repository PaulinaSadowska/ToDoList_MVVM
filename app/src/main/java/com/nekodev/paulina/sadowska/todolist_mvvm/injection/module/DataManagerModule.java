package com.nekodev.paulina.sadowska.todolist_mvvm.injection.module;


import android.content.Context;

import com.nekodev.paulina.sadowska.todolist_mvvm.data.remote.RetrofitHelper;
import com.nekodev.paulina.sadowska.todolist_mvvm.data.remote.ToDoService;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.scope.PerDataManager;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

@Module
public class DataManagerModule {

    private Context mContext;

    public DataManagerModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @PerDataManager
    ToDoService provideToDoService() {
        return new RetrofitHelper().newToDoListService();
    }

    @Provides
    @PerDataManager
    Scheduler provideSubscribeScheduler() {
        return Schedulers.io();
    }

    @Provides
    @PerDataManager
    Realm provideRealm() {
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(mContext).build());
        return Realm.getDefaultInstance();
    }
}
