package com.nekodev.paulina.sadowska.todolist_mvvm.data.remote;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

public class RetrofitHelper {

    public ToDoService newToDoListService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ToDoService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(ToDoService.class);
    }

}
