package com.nekodev.paulina.sadowska.todolist_mvvm.controller.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nekodev.paulina.sadowska.todolist_mvvm.R;
import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;
import com.nekodev.paulina.sadowska.todolist_mvvm.databinding.ItemTodoBinding;
import com.nekodev.paulina.sadowska.todolist_mvvm.viewmodel.TaskViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulina Sadowska on 20.08.2016.
 */

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.BindingHolder>
{

    private Context mContext;
    private ArrayList<ToDoItem> mToDoList;

    public ToDoListAdapter(Context context){
        this.mContext = context;
        mToDoList = new ArrayList<>();
    }

    @Override
    public ToDoListAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTodoBinding todoBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_todo,
                parent,
                false);
        return new BindingHolder(todoBinding);
    }

    public void addTask(ToDoItem task){
        this.mToDoList.add(task);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ToDoListAdapter.BindingHolder holder, int position) {
        ItemTodoBinding todoBinding = holder.binding;
        todoBinding.setViewModel(new TaskViewModel(mContext, mToDoList.get(position)));
    }

    @Override
    public int getItemCount() {
        return mToDoList.size();
    }

    public Serializable getTasks() {
        return mToDoList;
    }

    public void addTasks(ArrayList<ToDoItem> tasks) {
        mToDoList.addAll(tasks);
    }

    public void editTask(ToDoItem taskItem) {
        int id = taskItem.getId();
        if(id<mToDoList.size() && mToDoList.get(id-1).getId() == id){
            mToDoList.set(id-1, taskItem);
            notifyItemChanged(id-1);
        }
    }

    public List<ToDoItem> getModifiedTasks() {
        List<ToDoItem> modifiedTasks = new ArrayList<>();
        for (ToDoItem toDoItem : mToDoList) {
            if(toDoItem.getModified()) {
                modifiedTasks.add(toDoItem);
            }
        }
        return  modifiedTasks;
    }

    public void markAllAsNotModified() {
        for (int i = 0; i < mToDoList.size(); i++) {
            if(mToDoList.get(i).getModified()){
                mToDoList.get(i).setModified(false);
                notifyItemChanged(i);
            }
        }
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ItemTodoBinding binding;

        public BindingHolder(ItemTodoBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }
    }
}
