package com.nekodev.paulina.sadowska.todolist_mvvm.util;

import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

public class MockModelUtil {

    private static final Boolean DUMMY_IS_COMPLETED = true;
    private static final String DUMMY_TASK_TEXT = "Some task text";


    public static ToDoItem createMockTask() {
        return new ToDoItem(DUMMY_IS_COMPLETED, DUMMY_TASK_TEXT);
    }
}
