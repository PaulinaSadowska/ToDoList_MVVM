package com.nekodev.paulina.sadowska.todolist_mvvm.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nekodev.paulina.sadowska.todolist_mvvm.R;
import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;
import com.nekodev.paulina.sadowska.todolist_mvvm.view.fragment.EditTaskFragment;

/**
 * Created by Paulina Sadowska on 23.08.2016.
 */

public class EditTaskActivity extends AppCompatActivity {
    public static final String EXTRA_TASK_DATA = "extraTaskDataKey";

    public static Intent getStartIntent(Context context, ToDoItem task) {
        Intent intent = new Intent(context, EditTaskActivity.class);
        intent.putExtra(EXTRA_TASK_DATA, task);
        return intent;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        if ((findViewById(R.id.content_frame) != null && savedInstanceState == null)
                || findViewById(R.id.content_frame) == null)
        {
            addEditTaskFragment();
        }
    }

    private void addEditTaskFragment() {
        EditTaskFragment editTaskFragment = new EditTaskFragment();
        editTaskFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, editTaskFragment)
                .commit();
    }
}
