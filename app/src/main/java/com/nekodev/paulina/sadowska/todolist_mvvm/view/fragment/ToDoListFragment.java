package com.nekodev.paulina.sadowska.todolist_mvvm.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nekodev.paulina.sadowska.todolist_mvvm.R;
import com.nekodev.paulina.sadowska.todolist_mvvm.data.ToDoItem;
import com.nekodev.paulina.sadowska.todolist_mvvm.view.adapter.ToDoListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Paulina Sadowska on 20.08.2016.
 */

public class ToDoListFragment extends Fragment {

    @BindView(R.id.recycler_todos)
    RecyclerView mToDoList;

    private ToDoListAdapter mToDoListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToDoListAdapter = new ToDoListAdapter(getActivity());
        mToDoListAdapter.addTask(new ToDoItem(true, "Dummy task name"));
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
    }
}
