package com.nekodev.paulina.sadowska.test.injection.modules;

import com.nekodev.paulina.sadowska.todolist_mvvm.injection.scope.PerDataManager;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

import static org.mockito.Mockito.mock;

/**
 * Created by Paulina Sadowska on 27.08.2016.
 */

@Module
public class RealmManagerTestModule {

    public RealmManagerTestModule() { }

    @Provides
    @PerDataManager
    Realm provideRealm() {
        return mock(Realm.class);
    }

}

