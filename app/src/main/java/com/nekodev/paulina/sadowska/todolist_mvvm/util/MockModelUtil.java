package com.nekodev.paulina.sadowska.todolist_mvvm.util;

import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

public class MockModelUtil {

    public static final String DUMMY_TASK_TEXT = "Some task text";
    private static final int DUMMY_TASK_ID = 4;


    public static ToDoItem createMockTask() {
        return new ToDoItem(true, DUMMY_TASK_TEXT, DUMMY_TASK_ID);
    }

    public static List<ToDoItem> createMockTaskList(int size) {
        ArrayList<ToDoItem> tasks = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            tasks.add(new ToDoItem(i%2==0, DUMMY_TASK_TEXT + " " + i, i));
        }
        return tasks;
    }
}
