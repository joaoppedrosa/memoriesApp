package com.jpp.memories.ui;

import android.app.Instrumentation;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.jpp.memories.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * @author João Pedro Pedrosa, memories on 14-03-2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void isCorrectTitleDisplayed() {
        onView(withText(R.string.app_name))
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void isFabIconClickable() {
        onView(withId(R.id.fab))
                .check(ViewAssertions.matches(isClickable()));
    }

    @Test
    public void isFabIconOpenCreateMemoryActivity() {
        onView(withId(R.id.fab))
                .check(ViewAssertions.matches(isClickable()));

        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(CreateMemoryActivity.class.getName(), null, false);

        onView(withId(R.id.fab))
                .perform(click());

        CreateMemoryActivity createMemoryActivity = (CreateMemoryActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(createMemoryActivity);
        createMemoryActivity.finish();
    }

    @Test
    public void onClickMemoryInRecyclerViewOpenDetailsActivity() {
        onView(withId(R.id.recycler_view))
                .perform(click());
        onView(withText(R.string.memory_details))
                .check(ViewAssertions.matches(isDisplayed()));
    }
}