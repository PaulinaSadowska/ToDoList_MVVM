package com.nekodev.paulina.sadowska.todolist_mvvm.injection.module;

import android.content.Context;

import com.nekodev.paulina.sadowska.todolist_mvvm.injection.scope.PerDataManager;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Paulina Sadowska on 27.08.2016.
 */
@Module
public class RealmManagerModule {

    private Context mContext;

    public RealmManagerModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @PerDataManager
    Realm provideRealm() {
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(mContext).build());
        return Realm.getDefaultInstance();
    }

}
