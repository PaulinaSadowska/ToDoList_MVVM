package com.nekodev.paulina.sadowska.todolist_mvvm.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.nekodev.paulina.sadowska.todolist_mvvm.R;
import com.nekodev.paulina.sadowska.todolist_mvvm.controller.fragment.ToDoListFragment;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment nestedFragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if(nestedFragment!=null) {
           nestedFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
