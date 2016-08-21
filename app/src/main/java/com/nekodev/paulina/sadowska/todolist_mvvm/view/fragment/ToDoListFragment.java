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

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Paulina Sadowska on 20.08.2016.
 */

public class ToDoListFragment extends Fragment {

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
        ButterKnife.setDebug(true);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        mToDoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mToDoList.setHasFixedSize(true);
        mToDoList.setAdapter(mToDoListAdapter);
        getTaskList();
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
