package com.nekodev.paulina.sadowska.test;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.nekodev.paulina.sadowska.test.injection.TestComponentRule;
import com.nekodev.paulina.sadowska.todolist_mvvm.R;
import com.nekodev.paulina.sadowska.todolist_mvvm.model.ToDoItem;
import com.nekodev.paulina.sadowska.todolist_mvvm.util.MockModelUtil;
import com.nekodev.paulina.sadowska.todolist_mvvm.view.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> main =
            new ActivityTestRule<>(MainActivity.class, false, false);

    @Rule
    public final TestComponentRule component = new TestComponentRule();

    @Test
    public void testToDoListShowAndAreScrollable() {
        List<ToDoItem> taskList = MockModelUtil.createMockTaskList(30);
        stubMockTasks(taskList);
        main.launchActivity(null);
        onView(withId(R.id.recycler_todos))
                .check(matches(isDisplayed()));
        checkTasksDisplayOnRecyclerView(taskList);
    }

    @Test
    public void testCheckboxCheckChangesData() {
        ToDoItem task = MockModelUtil.createMockTask();
        task.setCompleted(false);
        stubMockTask(task);
        main.launchActivity(null);
        onView(withId(R.id.recycler_todos))
                .check(matches(isDisplayed()));
        onView(withId(R.id.task_item_checkbox))
                .perform(ViewActions.click())
                .check(matches(isChecked()));
        assertTrue(task.isCompleted());
    }

    private void stubMockTask(ToDoItem task) {
        ArrayList<ToDoItem> tasks = new ArrayList<>();
        tasks.add(task);
        stubMockTasks(tasks);
    }


    private void stubMockTasks(List<ToDoItem> tasks) {
        when(component.getMockToDoService().getTasks())
                .thenReturn(Observable.just(tasks));
    }

    private void checkTasksDisplayOnRecyclerView(List<ToDoItem> tasksToCheck) {
        for (int i = 0; i < tasksToCheck.size(); i++) {
            //must be +1 because without it it scrolls to little and text is not fully visible
            onView(withId(R.id.recycler_todos))
                    .perform(RecyclerViewActions.scrollToPosition(i+1));
            checkTaskDisplays(tasksToCheck.get(i));
        }
    }


    private void checkTaskDisplays(ToDoItem task) {
        onView(withText(task.getTask()))
                .check(matches(isDisplayed()));
    }
}