package com.nekodev.paulina.sadowska.test.injection;

/**
 * Created by Paulina Sadowska on 21.08.2016.
 */

import android.support.test.InstrumentationRegistry;

import com.nekodev.paulina.sadowska.test.injection.components.ApplicationTestComponent;
import com.nekodev.paulina.sadowska.test.injection.modules.ApplicationTestModule;
import com.nekodev.paulina.sadowska.test.injection.components.DaggerApplicationTestComponent;
import com.nekodev.paulina.sadowska.test.util.TestDataManager;
import com.nekodev.paulina.sadowska.todolist_mvvm.ToDoListApplication;
import com.nekodev.paulina.sadowska.todolist_mvvm.data.remote.ToDoService;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Test rule that creates and sets a Dagger TestComponent into the application overriding the
 * existing application component.
 * Use this rule in your test case in order for the app to use mock dependencies.
 * It also exposes some of the dependencies so they can be easily accessed from the tests, e.g. to
 * stub mocks etc.
 */
public class TestComponentRule implements TestRule {

    private ApplicationTestComponent mTestComponent;

    public TestDataManager getDataManager() {
        return (TestDataManager) mTestComponent.dataManager();
    }

    public ToDoService getMockToDoService() {
        return getDataManager().getToDoService();
    }

    private void setupDaggerTestComponentInApplication() {
        ToDoListApplication application = ToDoListApplication
                .get(InstrumentationRegistry.getTargetContext());
        if (application.getComponent() instanceof ApplicationTestComponent) {
            mTestComponent = (ApplicationTestComponent) application.getComponent();
        } else {
            mTestComponent = DaggerApplicationTestComponent.builder()
                    .applicationTestModule(new ApplicationTestModule(application))
                    .build();
            application.setComponent(mTestComponent);
        }
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    setupDaggerTestComponentInApplication();
                    base.evaluate();
                } finally {
                    mTestComponent = null;
                }
            }
        };
    }
}
