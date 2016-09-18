package com.nekodev.paulina.sadowska.todolist_mvvm.controller.fragment;

import android.content.Intent;
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
import com.nekodev.paulina.sadowska.todolist_mvvm.controller.activity.EditTaskActivity;
import com.nekodev.paulina.sadowska.todolist_mvvm.controller.adapter.ToDoListAdapter;
import com.nekodev.paulina.sadowska.todolist_mvvm.data.DataManager;
import com.nekodev.paulina.sadowska.todolist_mvvm.data.RealmManager;
import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static com.nekodev.paulina.sadowska.todolist_mvvm.controller.activity.EditTaskActivity.EXTRA_TASK_DATA;

/**
 * Created by Paulina Sadowska on 20.08.2016.
 */

public class ToDoListFragment extends Fragment {

    private static final String SAVED_TASKS_KEY = "SavedTasksKey";
    @BindView(R.id.recycler_todos)
    RecyclerView mToDoList;

    private ToDoListAdapter mToDoListAdapter;
    private DataManager mDataManager;
    private RealmManager mRealmManager;
    private CompositeSubscription mCompositeSubscription;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToDoListAdapter = new ToDoListAdapter(getActivity());
        mCompositeSubscription = new CompositeSubscription();
        mDataManager = ToDoListApplication.get(getActivity()).getComponent().dataManager();
        mRealmManager = ToDoListApplication.get(getActivity()).getComponent().realmManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todolist, container, false);
        ButterKnife.bind(this, view);
        if (savedInstanceState != null && savedInstanceState.containsKey(SAVED_TASKS_KEY)) {
            Serializable tasks = savedInstanceState.getSerializable(SAVED_TASKS_KEY);
            if (tasks instanceof ArrayList) {
                mToDoListAdapter.addTasks((ArrayList) tasks);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EditTaskActivity.ACTIVITY_RESULT_CODE && mToDoListAdapter != null && data != null) {
            if (data.hasExtra(EXTRA_TASK_DATA)) {
                ToDoItem task = (ToDoItem) data.getExtras().getSerializable(EXTRA_TASK_DATA);
                mToDoListAdapter.editTask(task);
                mRealmManager.saveTask(task);
            }
        }
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
        if (mToDoListAdapter.getItemCount() == 0) {
            getTaskList();
        }
    }

    private void getTaskList() {
        mCompositeSubscription.add(mDataManager.getTasks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mDataManager.getScheduler())
                .subscribe(
                        task -> { //onNext
                            ToDoItem savedTask = mRealmManager.getSavedTask(task.getId());
                            mToDoListAdapter.addTask(savedTask != null ? savedTask : task);
                        }, e -> { //onError
                            Toast.makeText(getActivity(), R.string.connection_problem, Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        })
        );
    }


    @OnClick(R.id.save_remotely_button)
    public void saveRemotely() {
        mCompositeSubscription
                .add(mDataManager.saveModifiedTasks(mToDoListAdapter.getModifiedTasks())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(mDataManager.getScheduler())
                        .subscribe(
                                responseBody -> { //onNext
                                },
                                e -> { //onError
                                    Toast.makeText(getActivity(), R.string.saving_data_problem, Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                },
                                () -> { //onComplete
                                    mToDoListAdapter.markAllAsNotModified();
                                    mRealmManager.removeAllSavedTasks();
                                    Toast.makeText(getActivity(), "saved", Toast.LENGTH_SHORT).show();
                                })
                );

    }
}
