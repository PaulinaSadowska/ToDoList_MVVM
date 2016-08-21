package com.nekodev.paulina.sadowska.test.injection.modules;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */
import com.nekodev.paulina.sadowska.todolist_mvvm.data.remote.ToDoService;
import com.nekodev.paulina.sadowska.todolist_mvvm.injection.scope.PerDataManager;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;

/**
 * Provides dependencies for an app running on a testing environment
 * This allows injecting mocks if necessary
 */
@Module
public class DataManagerTestModule {

    public DataManagerTestModule() { }

    @Provides
    @PerDataManager
    ToDoService provideToDoService() {
        return mock(ToDoService.class);
    }

    @Provides
    @PerDataManager
    Scheduler provideSubscribeScheduler() {
        return Schedulers.immediate();
    }
}
