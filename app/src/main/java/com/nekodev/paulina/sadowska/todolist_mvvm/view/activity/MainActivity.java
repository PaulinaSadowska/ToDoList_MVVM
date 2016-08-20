package com.nekodev.paulina.sadowska.todolist_mvvm.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nekodev.paulina.sadowska.todolist_mvvm.R;
import com.nekodev.paulina.sadowska.todolist_mvvm.view.fragment.ToDoListFragment;

/**
 * Created by Paulina Sadowska on 20.08.2016.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if ((findViewById(R.id.content_frame) != null && savedInstanceState == null)
                || findViewById(R.id.content_frame) == null)
        {
            addToDoListFragment();
        }
    }

    private void addToDoListFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, new ToDoListFragment())
                .commit();
    }
}
