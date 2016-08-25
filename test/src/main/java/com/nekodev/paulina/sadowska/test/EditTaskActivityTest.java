package com.nekodev.paulina.sadowska.test;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.nekodev.paulina.sadowska.test.injection.TestComponentRule;
import com.nekodev.paulina.sadowska.todolist_mvvm.R;
import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;
import com.nekodev.paulina.sadowska.todolist_mvvm.util.MockModelUtil;
import com.nekodev.paulina.sadowska.todolist_mvvm.controller.activity.EditTaskActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Paulina Sadowska on 23.08.2016.
 */

@RunWith(AndroidJUnit4.class)
public class EditTaskActivityTest {

    private ToDoItem mTask;
    private Intent mIntent;


    @Rule
    public final ActivityTestRule<EditTaskActivity> editTask =
            new ActivityTestRule<>(EditTaskActivity.class, false, false);

    @Rule
    public final TestComponentRule component = new TestComponentRule();

    @Before
    public void setUp() {
        mTask = MockModelUtil.createMockTask();
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        mIntent = new Intent(targetContext, EditTaskActivity.class);
        mIntent.putExtra(EditTaskActivity.EXTRA_TASK_DATA, mTask);
    }


    @Test
    public void testEditTaskActivityOpens() {
        editTask.launchActivity(null);
        onView(withId(R.id.content_frame)).check(matches(isDisplayed()));
    }

    @Test
    public void testEditTaskActivityDisplaysTaskName() {
        editTask.launchActivity(mIntent);
        checkDisplays(mTask.getTask());

    }

    @Test
    public void testEditTaskActivityDisplaysId() {
        editTask.launchActivity(mIntent);
        checkDisplays(mTask.getId()+"");

    }

    @Test
    public void testEditTaskActivityIsCompleted() {
        editTask.launchActivity(mIntent);
        if(mTask.isCompleted()) {
            onView(withId(R.id.task_item_checkbox))
                    .check(matches(isChecked()));
        }
        else{
            onView(withId(R.id.task_item_checkbox))
                    .check(matches(isNotChecked()));
        }
        assertTrue(mTask.isCompleted());

    }

    private void checkDisplays(String text){
        onView(withText(text))
                .check(matches(isDisplayed()));
    }
}
