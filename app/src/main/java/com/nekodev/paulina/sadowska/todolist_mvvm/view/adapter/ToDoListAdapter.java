package com.nekodev.paulina.sadowska.todolist_mvvm.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nekodev.paulina.sadowska.todolist_mvvm.R;
import com.nekodev.paulina.sadowska.todolist_mvvm.data.ToDoItem;
import com.nekodev.paulina.sadowska.todolist_mvvm.databinding.ItemTodoBinding;
import com.nekodev.paulina.sadowska.todolist_mvvm.viewmodel.ListItemViewModel;

import java.util.ArrayList;

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
        todoBinding.setViewModel(new ListItemViewModel(mContext, mToDoList.get(position)));
    }

    @Override
    public int getItemCount() {
        return mToDoList.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ItemTodoBinding binding;

        public BindingHolder(ItemTodoBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }
    }
}
