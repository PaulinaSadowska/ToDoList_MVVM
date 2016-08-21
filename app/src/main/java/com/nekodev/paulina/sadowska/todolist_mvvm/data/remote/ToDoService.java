package com.nekodev.paulina.sadowska.todolist_mvvm.data.remote;

import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

public interface ToDoService {


    String ENDPOINT = "http://127.0.0.1:3000/";

    @GET("/todo_list/")
    Observable<List<ToDoItem>> getTasks();

}
