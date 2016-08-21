package com.nekodev.paulina.sadowska.todolist_mvvm.injection.module;

import android.app.Application;

import com.nekodev.paulina.sadowska.todolist_mvvm.data.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return new DataManager(mApplication);
    }

}
