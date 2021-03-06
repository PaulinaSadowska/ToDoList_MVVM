package com.nekodev.paulina.sadowska.todolist_mvvm.viewmodel;

import android.content.Context;
import android.view.View;

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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK, manifest = DefaultConfig.MANIFEST)
public class TaskViewModelTest {

    private TaskViewModel mTaskViewModel;
    private ToDoItem mToDoItem;

    @Before
    public void setUp() {
        Context context = RuntimeEnvironment.application;
        mToDoItem = MockModelUtil.createMockTask();
        mTaskViewModel = new TaskViewModel(context, mToDoItem);
    }

    @Test
    public void shouldGetTaskText() throws Exception {
        assertEquals(mTaskViewModel.getTask(), mToDoItem.getTask());
    }

    @Test
    public void shouldGetIsCompleted() throws Exception {
        assertTrue(mTaskViewModel.isCompleted() == mToDoItem.isCompleted());
    }

    @Test
    public void shouldSetIsCompleted() {
        mTaskViewModel.setIsCompleted(false);
        assertTrue(mTaskViewModel.isCompleted() == mToDoItem.isCompleted());
        assertTrue(mToDoItem.getModified());
        mTaskViewModel.setIsCompleted(true);
        assertTrue(mTaskViewModel.isCompleted() == mToDoItem.isCompleted());
    }

    @Test
    public void shouldBeVisibleWhenItemWasModified(){
        mToDoItem.setModified();
        assertEquals(mTaskViewModel.wasModified(), View.VISIBLE);
    }

}
