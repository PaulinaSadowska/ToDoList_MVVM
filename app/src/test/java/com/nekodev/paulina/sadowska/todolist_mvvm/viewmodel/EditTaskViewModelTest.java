package com.nekodev.paulina.sadowska.todolist_mvvm.viewmodel;

import android.content.Context;

import com.nekodev.paulina.sadowska.todolist_mvvm.BuildConfig;
import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;
import com.nekodev.paulina.sadowska.todolist_mvvm.util.DefaultConfig;
import com.nekodev.paulina.sadowska.todolist_mvvm.util.MockModelUtil;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Paulina Sadowska on 25.08.2016.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK, manifest = DefaultConfig.MANIFEST)
public class EditTaskViewModelTest {

    private EditTaskViewModel mEditTaskViewModel;
    private ToDoItem mToDoItem;

    @Before
    public void setUp() {
        Context context = RuntimeEnvironment.application;
        mToDoItem = MockModelUtil.createMockTask();
        mEditTaskViewModel = new EditTaskViewModel(context, mToDoItem);
    }

    @Test
    public void shouldGetIsCompleted() {
        assertTrue(mEditTaskViewModel.isCompleted() == mToDoItem.isCompleted());
    }

    @Test
    public void shouldSetIsCompleted() {
        mEditTaskViewModel.setIsCompleted(false);
        assertTrue(mEditTaskViewModel.isCompleted() == mToDoItem.isCompleted());
        assertTrue(mToDoItem.getModified());
        mEditTaskViewModel.setIsCompleted(true);
        assertTrue(mEditTaskViewModel.isCompleted() == mToDoItem.isCompleted());
    }

    @Test
    public void shouldGetTaskText() {
        assertTrue(mEditTaskViewModel.getTask().equals(mToDoItem.getTask()));
    }

    @Test
    public void shouldSetTaskText(){
        String taskStr = "some task";
        mEditTaskViewModel.setTask(taskStr);
        assertTrue(mEditTaskViewModel.getTask().equals(mToDoItem.getTask()));
        assertTrue(mToDoItem.getModified());
    }

    @Test
    public void shouldGetId() {
        assertTrue(mEditTaskViewModel.getId().equals(mToDoItem.getId()+""));
    }

}