import com.cse110.apk404.myCalendar.MainActivity;
package com.cse110.apk404.myCalendar.sampleReference;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cse110.apk404.myCalendar.AddEventActivity;
import com.cse110.apk404.myCalendar.MainActivity;
import com.cse110.apk404.myCalendar.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;


@RunWith(AndroidJUnit4.class)
public class AddEventActivityTest
{
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Rule
    public ActivityTestRule<AddEventActivity> addActivityRule = new ActivityTestRule(AddEventActivity.class);


    @Test
    public void AddEventTest1()
    {
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(AddEventActivity.class.getName(), null, false);
        onView(withId(R.id.start_date_add_event)).perform(click());
        AddEventActivity nextAction = (AddEventActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 3000);
        assertNotNull(nextAction);
        nextAction.finish();
    }

    @Test
    public void AddEventTest2()
    {
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(AddEventActivity.class.getName(), null, false);
        onView(withId(R.id.end_date_add_event)).perform(click());
        AddEventActivity nextAction = (AddEventActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 3000);
        assertNotNull(nextAction);
        nextAction.finish();
    }

    @Test
    public void AddEventTest3()
    {
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(AddEventActivity.class.getName(), null, false);
        onView(withId(R.id.start_time_add_event)).perform(click());
        AddEventActivity nextAction = (AddEventActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 3000);
        assertNotNull(nextAction);
        nextAction.finish();
    }

    @Test
    public void AddEventTest4()
    {
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(AddEventActivity.class.getName(), null, false);
        onView(withId(R.id.end_time_add_event)).perform(click());
        AddEventActivity nextAction = (AddEventActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 3000);
        assertNotNull(nextAction);
        nextAction.finish();
    }

    @Test
    public void AddEventTest5()
    {
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(AddEventActivity.class.getName(), null, false);
        onView(withId(R.id.button5)).perform(click());
        AddEventActivity nextAction = (AddEventActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 3000);
        assertNotNull(nextAction);
        nextAction.finish();
    }
}