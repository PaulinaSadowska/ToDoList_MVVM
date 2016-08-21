package com.nekodev.paulina.sadowska.todolist_mvvm;

import android.content.Context;

import com.nekodev.paulina.sadowska.todolist_mvvm.data.ToDoItem;
import com.nekodev.paulina.sadowska.todolist_mvvm.util.DefaultConfig;
import com.nekodev.paulina.sadowska.todolist_mvvm.util.MockModelUtil;
import com.nekodev.paulina.sadowska.todolist_mvvm.viewmodel.ListItemViewModel;

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
public class ListItemViewModelTest {

    private ListItemViewModel mListItemViewModel;
    private ToDoItem mToDoItem;

    @Before
    public void setUp() {
        Context context = RuntimeEnvironment.application;
        mToDoItem = MockModelUtil.createMockTask();
        mListItemViewModel = new ListItemViewModel(context, mToDoItem);
    }

    @Test
    public void shouldGetTaskText() throws Exception {
        assertEquals(mListItemViewModel.getTask(), mToDoItem.getTask());
    }

    @Test
    public void shouldGetIsCompleted() throws Exception {
        assertTrue(mListItemViewModel.isCompleted() == mToDoItem.isCompleted());
    }

}
