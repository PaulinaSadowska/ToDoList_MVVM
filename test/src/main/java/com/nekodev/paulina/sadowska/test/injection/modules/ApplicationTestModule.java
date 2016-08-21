package com.nekodev.paulina.sadowska.test.injection.modules;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */
import android.app.Application;

import com.nekodev.paulina.sadowska.todolist_mvvm.data.DataManager;
import com.nekodev.paulina.sadowska.test.util.TestDataManager;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Provides application-level dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary.
 */
@Module
public class ApplicationTestModule {
    private final Application mApplication;

    public ApplicationTestModule(Application application) {
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
        return new TestDataManager(mApplication);
    }

}