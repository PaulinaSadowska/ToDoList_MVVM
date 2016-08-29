package com.nekodev.paulina.sadowska.todolist_mvvm.data.remote;

import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

public interface ToDoService {


    String ENDPOINT = "http://127.0.0.1:3000/";

    @GET("/todo_list/")
    Observable<List<ToDoItem>> getTasks();


    @FormUrlEncoded
    @PUT("/todo_list/{id}")
    Observable<ResponseBody> putSavedTasks(@Field("task") String task,
                                           @Path("id") int id,
                                           @Field("completed") boolean completed);

}
