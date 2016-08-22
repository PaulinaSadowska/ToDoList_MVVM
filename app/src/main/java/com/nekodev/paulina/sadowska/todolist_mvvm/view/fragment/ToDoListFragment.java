package com.nekodev.paulina.sadowska.todolist_mvvm.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nekodev.paulina.sadowska.todolist_mvvm.R;
import com.nekodev.paulina.sadowska.todolist_mvvm.ToDoListApplication;
import com.nekodev.paulina.sadowska.todolist_mvvm.data.DataManager;
import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;
import com.nekodev.paulina.sadowska.todolist_mvvm.view.adapter.ToDoListAdapter;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Paulina Sadowska on 20.08.2016.
 */

public class ToDoListFragment extends Fragment {

    private static final String SAVED_TASKS_KEY = "SavedTasksKey";
    @BindView(R.id.recycler_todos)
    RecyclerView mToDoList;

    private ToDoListAdapter mToDoListAdapter;
    private DataManager mDataManager;
    private CompositeSubscription mCompositeSubscription;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToDoListAdapter = new ToDoListAdapter(getActivity());
        mDataManager = ToDoListApplication.get(getActivity()).getComponent().dataManager();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todolist, container, false);
        ButterKnife.bind(this, view);
        if(savedInstanceState!=null && savedInstanceState.containsKey(SAVED_TASKS_KEY)){
            Serializable tasks = savedInstanceState.getSerializable(SAVED_TASKS_KEY);
            if(tasks instanceof ArrayList) {
                mToDoListAdapter.addTasks((ArrayList)tasks);
            }
        }
        setupRecyclerView();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_TASKS_KEY, mToDoListAdapter.getTasks());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }

    private void setupRecyclerView() {
        mToDoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mToDoList.setHasFixedSize(true);
        mToDoList.setAdapter(mToDoListAdapter);
        if(mToDoListAdapter.getItemCount()==0) {
            getTaskList();
        }
    }

    private void getTaskList() {
        mCompositeSubscription.add(mDataManager.getTasks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mDataManager.getScheduler())
                .subscribe(new Subscriber<ToDoItem>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), R.string.connection_problem, Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ToDoItem task) {
                        mToDoListAdapter.addTask(task);
                    }
                }));
    }
}
