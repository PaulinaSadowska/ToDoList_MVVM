package com.nekodev.paulina.sadowska.todolist_mvvm.controller.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nekodev.paulina.sadowska.todolist_mvvm.R;
import com.nekodev.paulina.sadowska.todolist_mvvm.controller.activity.EditTaskActivity;
import com.nekodev.paulina.sadowska.todolist_mvvm.databinding.FragmentEditTaskBinding;
import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;
import com.nekodev.paulina.sadowska.todolist_mvvm.viewmodel.EditTaskViewModel;

import java.io.Serializable;

/**
 * Created by Paulina Sadowska on 23.08.2016.
 */

public class EditTaskFragment extends Fragment {

    private ToDoItem mTask;
    private FragmentEditTaskBinding mEditTaskBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mEditTaskBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_edit_task,
                container,
                false);
        return mEditTaskBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments() != null) {
            Serializable serializable = getArguments().getSerializable(EditTaskActivity.EXTRA_TASK_DATA);
            if(serializable!= null && serializable instanceof ToDoItem) {
                mTask = (ToDoItem) getArguments().getSerializable(EditTaskActivity.EXTRA_TASK_DATA);
                mEditTaskBinding.setViewModel(new EditTaskViewModel(getActivity(), mTask));
            }
        }
        else{
            Log.e(EditTaskActivity.class.getName(), "No data provided");
        }
    }
}
