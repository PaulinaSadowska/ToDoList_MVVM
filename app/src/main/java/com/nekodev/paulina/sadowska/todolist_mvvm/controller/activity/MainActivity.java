package com.nekodev.paulina.sadowska.todolist_mvvm.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nekodev.paulina.sadowska.todolist_mvvm.R;
import com.nekodev.paulina.sadowska.todolist_mvvm.controller.fragment.ToDoListFragment;

/**
 * Created by Paulina Sadowska on 20.08.2016.
 */

public class MainActivity extends AppCompatActivity {

    private ToDoListFragment mToDoListFragment;

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
        mToDoListFragment = new ToDoListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, mToDoListFragment)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mToDoListFragment!=null) {
            mToDoListFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
