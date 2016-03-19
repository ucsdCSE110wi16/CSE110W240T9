package com.cse110.apk404.myCalendar;

/**
 * Created by Kristinektran on 3/18/16.
 */


import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class DynamicEventTest
{
    private String eventName;
    private String eventLocation;
    private String eventNotes;
    private String eventDuration;
    //Instantiate ActivityTestRule - > with add_event
    @Rule
    public ActivityTestRule<AddEventActivity> mActivityRule = new ActivityTestRule(AddEventActivity.class);

    @Before
    public void initValidString()
    {
        // Specify a valid string for event name, location and notes
        eventName = "Espresso Dynamic Test Event Name";
        eventLocation = "Espresso Dynamic Test Event Location";
        eventNotes = "Espresso Dynamic Test Event Description";
        eventDuration = "5";
    }

    public static ViewAction setTime(final int hours, final int minutes)
    {

        return new ViewAction()
        {

            @Override
            public void perform(UiController uiController, View view) {
                final TimePicker timePicker = (TimePicker) view;
                timePicker.setCurrentHour(hours);
                timePicker.setCurrentMinute(minutes);
            }

            @Override
            public String getDescription()
            {
                return "set time";
            }

            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(TimePicker.class), isDisplayed());
            }
        };

    }

    public static ViewAction setDate(final int year, final int monthOfYear, final int dayOfMonth) {

        // monthOfYear which starts with zero in DatePicker widget.
        final int normalizedMonthOfYear = monthOfYear - 1;

        return new ViewAction() {

            @Override
            public void perform(UiController uiController, View view) {
                final DatePicker datePicker = (DatePicker) view;
                datePicker.updateDate(year, normalizedMonthOfYear, dayOfMonth);
            }

            @Override
            public String getDescription() {
                return "set date";
            }

            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(DatePicker.class), isDisplayed());
            }
        };

    }



    @Test
    public void testCreateEvent () throws InterruptedException
    {
        /*******************************************************************************************************
         *                     TEST2 : TEST FOR DYNAMIC EVENT
         *******************************************************************************************************/
        // Test for add event name
        onView(withId(R.id.event_name_add_event)).perform(typeText(eventName), ViewActions.closeSoftKeyboard());
        // Wait function
        synchronized (this) {
            Thread.sleep((long) 1000);
        }


        // Test for add event location
        onView(withId(R.id.event_location_add_event)).perform(typeText(eventLocation), ViewActions.closeSoftKeyboard());
        // Wait function
        synchronized (this) {
            Thread.sleep((long) 1000);
        }


        // Test for STATIC event
        onView(withId(R.id.type_of_event_add_event)).perform(click());
        onView(withText("DYNAMIC")).perform(click());
        // Wait function
        synchronized (this) {
            Thread.sleep((long) 1000);
        }

        // Test for event color
        onView(withId(R.id.color_dropdown_add_event)).perform(click());
        onView(withText("GREEN")).perform(click());
        // Wait function
        synchronized (this) {
            Thread.sleep((long) 1000);
        }

        // Test for add event duration
        onView(withId(R.id.event_duration_add_event)).perform(typeText(eventDuration), ViewActions.closeSoftKeyboard());
        // Wait function
        synchronized (this)
        {
            Thread.sleep((long) 1000);
        }


//        // Test for event start time
//        onView(withId(R.id.start_time_add_event)).perform(click());
//        onView(isAssignableFrom(TimePicker.class)).perform(setTime(11, 30));
//        // Wait function
//        onView(withText("OK")).perform(click());
//        synchronized (this) {
//            Thread.sleep((long) 1000);
//        }

        // Test for event end time
        onView(withId(R.id.end_time_add_event)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(15, 30));
        // Wait function
        onView(withText("OK")).perform(click());
        synchronized (this) {
            Thread.sleep((long) 1000);
        }

        // Test for event end date
        onView(withId(R.id.end_date_add_event)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(setDate(2016,03, 21));
        // Wait function
        onView(withText("OK")).perform(click());
        synchronized (this) {
            Thread.sleep((long) 1000);
        }


        // Test for add event description
        onView(withId(R.id.notes_add_event)).perform(typeText(eventNotes), ViewActions.closeSoftKeyboard());
        // Wait function
        synchronized (this)
        {
            Thread.sleep((long) 1000);
        }

        // Test for floating button - finished creating event
        onView(withId(R.id.fab_finished_event)).perform(click());
        synchronized (this) {
            Thread.sleep((long) 5000);
        }
    }
}

